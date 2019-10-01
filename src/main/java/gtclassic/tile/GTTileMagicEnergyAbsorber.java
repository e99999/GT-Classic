package gtclassic.tile;

import java.util.List;

import gtclassic.container.GTContainerMagicEnergyAbsorber;
import gtclassic.helpers.GTHelperPlayer;
import gtclassic.helpers.GTHelperStack;
import gtclassic.material.GTMaterialGen;
import gtclassic.util.GTLang;
import gtclassic.util.int3;
import ic2.api.classic.energy.tile.IEnergySourceInfo;
import ic2.api.classic.item.IElectricTool;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.tile.machine.IEUStorage;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.network.INetworkClientTileEntityEventListener;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.block.base.util.info.misc.IEmitterTile;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;

public class GTTileMagicEnergyAbsorber extends TileEntityMachine implements ITickable, IHasGui, IEUStorage,
		IEmitterTile, IEnergySourceInfo, INetworkClientTileEntityEventListener {

	@NetworkField(index = 4)
	public int storage = 0;
	public int maxStorage = 1000000;
	public int production = 128;
	int slotInput = 0;
	int slotOutput = 1;
	boolean enet = false;
	public boolean potionMode = false;
	public boolean xpMode = false;

	public GTTileMagicEnergyAbsorber() {
		super(2);
		this.addGuiFields("potionMode", "xpMode");
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.UP.invert());
		handler.registerDefaultSlotAccess(AccessRule.Import, 0);
		handler.registerDefaultSlotAccess(AccessRule.Export, 1);
		handler.registerDefaultSlotsForSide(RotationList.DOWN.invert(), 0);
		handler.registerDefaultSlotsForSide(RotationList.UP.invert(), 1);
		handler.registerSlotType(SlotType.Input, 0);
		handler.registerSlotType(SlotType.Output, 1);
	}

	@Override
	public LocaleComp getBlockName() {
		return GTLang.MAGIC_ENERGY_ABSORBER;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.storage = nbt.getInteger("storage");
		this.potionMode = nbt.getBoolean("potionMode");
		this.xpMode = nbt.getBoolean("xpMode");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("storage", this.storage);
		nbt.setBoolean("potionMode", this.potionMode);
		nbt.setBoolean("xpMode", this.xpMode);
		return nbt;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing() && facing.getAxis().isHorizontal();
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	public boolean canInteractWith(EntityPlayer paramEntityPlayer) {
		return !this.isInvalid();
	}

	@Override
	public boolean hasGui(EntityPlayer paramEntityPlayer) {
		return true;
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer var1) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerMagicEnergyAbsorber(player.inventory, this);
	}

	@Override
	public void onGuiClosed(EntityPlayer var1) {
	}

	@Override
	public void update() {
		boolean hasPower = this.storage > 0;
		if ((hasPower) != this.getActive()) {
			this.setActive(hasPower);
		}
		handleTools();
		handleBooks();
		if (potionMode) {
			handleAreaEffectCloud();
		}
		if (xpMode) {
			handlePlayerXP();
		}
	}

	public void handleTools() {
		ItemStack inputStack = this.getStackInSlot(slotInput);
		if (inputStack.isItemEnchanted() && !(inputStack.getItem() instanceof IElectricTool)) {
			int level = 0;
			for (int i : EnchantmentHelper.getEnchantments(this.getStackInSlot(slotInput)).values()) {
				level = level + i;
			}
			if (level != 0) {
				int generate = (int) (20000 * level * world.rand.nextFloat());
				if (generate + this.storage > this.maxStorage
						|| !GTHelperStack.canMerge(inputStack, this.getStackInSlot(slotOutput))) {
					return;
				}
				inputStack.getTagCompound().removeTag("ench");
				this.storage = this.storage + generate;
				this.setStackInSlot(slotOutput, StackUtil.copyWithSize(inputStack, this.getStackInSlot(slotOutput).getCount()
						+ 1));
				inputStack.shrink(1);
			}
		}
	}

	public void handleBooks() {
		ItemStack inputStack = this.getStackInSlot(slotInput);
		if (inputStack.getItem() instanceof ItemEnchantedBook) {
			NBTTagList nbttaglist = ItemEnchantedBook.getEnchantments(inputStack);
			for (int i = 0; i < nbttaglist.tagCount(); ++i) {
				NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
				int j = nbttagcompound.getShort("id");
				Enchantment enchantment = Enchantment.getEnchantmentByID(j);
				if (enchantment != null) {
					int level = nbttagcompound.getShort("lvl");
					if (level != 0) {
						int generate = (int) (20000 * level * world.rand.nextFloat());
						ItemStack blankBook = GTMaterialGen.get(Items.BOOK);
						if (generate + this.storage > this.maxStorage
								|| !GTHelperStack.canMerge(blankBook, this.getStackInSlot(slotOutput))) {
							return;
						}
						this.storage = this.storage + generate;
						this.setStackInSlot(slotOutput, StackUtil.copyWithSize(blankBook, this.getStackInSlot(slotOutput).getCount()
								+ 1));
						inputStack.shrink(1);
					}
				}
			}
		}
	}

	public void handleAreaEffectCloud() {
		AxisAlignedBB area = new AxisAlignedBB(new int3(pos, getFacing()).up(1).asBlockPos());
		List<EntityAreaEffectCloud> list = world.<EntityAreaEffectCloud>getEntitiesWithinAABB(EntityAreaEffectCloud.class, area);
		if (!list.isEmpty()) {
			if (this.storage + 128 <= this.maxStorage) {
				this.storage = this.storage + 128;
			}
		}
	}

	public void handlePlayerXP() {
		AxisAlignedBB area = new AxisAlignedBB(new int3(pos, getFacing()).up(1).asBlockPos());
		List<EntityPlayer> players = (world.getEntitiesWithinAABB(EntityPlayer.class, area));
		if (!players.isEmpty()) {
			EntityPlayer activePlayer = players.get(0);
			int playerXP = GTHelperPlayer.getPlayerXP(activePlayer);
			if (playerXP <= 0) {
				return;
			}
			if (this.storage + 128 <= this.maxStorage) {
				this.storage = this.storage + 128;
				GTHelperPlayer.addPlayerXP(activePlayer, -1);
				if (world.getTotalWorldTime() % 4 == 0) {
					world.playSound((EntityPlayer) null, activePlayer.posX, activePlayer.posY, activePlayer.posZ, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.NEUTRAL, 0.1F, 0.5F
							+ world.rand.nextFloat());
				}
			}
		}
	}

	@Override
	public int getStoredEU() {
		return this.storage;
	}

	@Override
	public int getMaxEU() {
		return this.maxStorage;
	}

	@Override
	public int getOutput() {
		return this.production;
	}

	@Override
	public void drawEnergy(double amount) {
		this.storage = (int) ((double) this.storage - amount);
	}

	@Override
	public double getOfferedEnergy() {
		return (double) Math.min(this.storage, this.production);
	}

	@Override
	public int getSourceTier() {
		return 2;
	}

	public int getMaxSendingEnergy() {
		return this.production + 1;
	}

	@Override
	public boolean emitsEnergyTo(IEnergyAcceptor var1, EnumFacing facing) {
		return true;
	}

	public void updateGui() {
		this.getNetwork().updateTileGuiField(this, "potionMode");
		this.getNetwork().updateTileGuiField(this, "xpMode");
	}

	@Override
	public void onNetworkEvent(EntityPlayer var1, int event) {
		if (event == 1) {
			this.xpMode = !this.xpMode;
			this.updateGui();
		}
		if (event == 2) {
			this.potionMode = !this.potionMode;
			this.updateGui();
		}
	}
}
