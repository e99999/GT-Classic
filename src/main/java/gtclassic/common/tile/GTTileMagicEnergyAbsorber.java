package gtclassic.common.tile;

import java.util.List;

import gtclassic.api.helpers.GTHelperStack;
import gtclassic.api.helpers.int3;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.common.GTConfig;
import gtclassic.common.GTLang;
import gtclassic.common.container.GTContainerMagicEnergyAbsorber;
import ic2.api.classic.audio.PositionSpec;
import ic2.api.classic.energy.tile.IEnergySourceInfo;
import ic2.api.classic.item.IElectricTool;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.tile.machine.IEUStorage;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.network.INetworkClientTileEntityEventListener;
import ic2.core.IC2;
import ic2.core.RotationList;
import ic2.core.audio.AudioSource;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.block.base.util.info.misc.IEmitterTile;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;

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
	public boolean portalMode = false;
	public boolean isAbovePortal = false;
	public AudioSource audioSource = null;
	private static final String NBT_POTIONMODE = "potionMode";
	private static final String NBT_XPMODE = "xpMode";
	private static final String NBT_PORTALMODE = "portalMode";
	private static final String NBT_ABOVEPORTAL = "isAbovePortal";

	public GTTileMagicEnergyAbsorber() {
		super(2);
		this.addGuiFields(NBT_POTIONMODE, NBT_XPMODE, NBT_PORTALMODE, NBT_ABOVEPORTAL);
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
		this.potionMode = nbt.getBoolean(NBT_POTIONMODE);
		this.xpMode = nbt.getBoolean(NBT_XPMODE);
		this.portalMode = nbt.getBoolean(NBT_PORTALMODE);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("storage", this.storage);
		nbt.setBoolean(NBT_POTIONMODE, this.potionMode);
		nbt.setBoolean(NBT_XPMODE, this.xpMode);
		nbt.setBoolean(NBT_PORTALMODE, this.portalMode);
		return nbt;
	}

	@Override
	public void onLoaded() {
		super.onLoaded();
		if (this.isSimulating() && !this.enet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			this.enet = true;
		}
	}

	@Override
	public void onUnloaded() {
		if (this.isSimulating() && this.enet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			this.enet = false;
		}
		if (this.isRendering() && this.audioSource != null) {
			IC2.audioManager.removeSources(this);
			this.audioSource = null;
		}
		super.onUnloaded();
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return false;
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
		structureCheck();
		if (isConvertingToolItem() || isConvertingBookItem()) {
			return;
		}
		if (xpMode && isConvertingXP()) {
			this.setActive(true);
			return;
		}
		if (potionMode && isConvertingPotion()) {
			this.setActive(true);
			return;
		}
		if (portalMode && isDrawingEnergyFromADarkPlace()) {
			this.setActive(true);
			return;
		}
		this.setActive(false);
	}

	public boolean isConvertingToolItem() {
		ItemStack inputStack = this.getStackInSlot(slotInput);
		if (inputStack.isEmpty()) {
			return false;
		}
		if (inputStack.isItemEnchanted() && !(inputStack.getItem() instanceof IElectricTool)) {
			int level = 0;
			for (int i : EnchantmentHelper.getEnchantments(this.getStackInSlot(slotInput)).values()) {
				level = level + i;
			}
			if (level != 0) {
				int generate = (int) (20000 * level * world.rand.nextFloat());
				if (generate + this.storage > this.maxStorage
						|| !GTHelperStack.canMerge(inputStack, this.getStackInSlot(slotOutput))) {
					return false;
				}
				inputStack.getTagCompound().removeTag("ench");
				this.storage = this.storage + generate;
				this.setStackInSlot(slotOutput, StackUtil.copyWithSize(inputStack, this.getStackInSlot(slotOutput).getCount()
						+ 1));
				inputStack.shrink(1);
				world.playSound((EntityPlayer) null, this.pos, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 0.5F, 0.75F
						+ world.rand.nextFloat());
				return true;
			}
		}
		return false;
	}

	public boolean isConvertingBookItem() {
		ItemStack inputStack = this.getStackInSlot(slotInput);
		if (inputStack.isEmpty()) {
			return false;
		}
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
							return false;
						}
						this.storage = this.storage + generate;
						this.setStackInSlot(slotOutput, StackUtil.copyWithSize(blankBook, this.getStackInSlot(slotOutput).getCount()
								+ 1));
						inputStack.shrink(1);
						world.playSound((EntityPlayer) null, this.pos, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 0.5F, 0.75F
								+ world.rand.nextFloat());
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean isConvertingXP() {
		AxisAlignedBB area = new AxisAlignedBB(new int3(pos, getFacing()).up(1).asBlockPos());
		List<EntityPlayer> players = (world.getEntitiesWithinAABB(EntityPlayer.class, area));
		if (!players.isEmpty()) {
			EntityPlayer activePlayer = players.get(0);
			int playerXP = getPlayerXP(activePlayer);
			if (playerXP <= 0) {
				return false;
			}
			if (this.storage + 128 <= this.maxStorage) {
				this.storage = this.storage + 128;
				this.setActive(true);
				addPlayerXP(activePlayer, -1);
				if (world.getTotalWorldTime() % 4 == 0) {
					world.playSound((EntityPlayer) null, this.pos, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.BLOCKS, 0.1F, 0.5F
							+ world.rand.nextFloat());
				}
				return true;
			}
		}
		return false;
	}

	public boolean isConvertingPotion() {
		AxisAlignedBB area = new AxisAlignedBB(new int3(pos, getFacing()).up(1).asBlockPos());
		List<EntityAreaEffectCloud> list = world.<EntityAreaEffectCloud>getEntitiesWithinAABB(EntityAreaEffectCloud.class, area);
		if (!list.isEmpty() && this.storage + 128 <= this.maxStorage) {
			this.storage = this.storage + 128;
			return true;
		}
		return false;
	}

	private void structureCheck() {
		if (this.portalMode && world.getTotalWorldTime() % 100 == 0) {
			boolean correctPos = world.getBlockState(this.pos.offset(EnumFacing.DOWN)).getBlock() == Blocks.END_PORTAL;
			this.isAbovePortal = correctPos;
			this.getNetwork().updateTileGuiField(this, NBT_PORTALMODE);
			// Now that its estabishled your getting cheaty power, less make sure not a
			// really digusting cheater : )
			if (this.isAbovePortal && GTConfig.general.oneMagicAbsorberPerEndPortal) {
				for (BlockPos nearby : getSurroundingBlocks()) {
					if (nearby.equals(this.pos)) {
						continue;
					}
					if (isThereAnother(nearby)) {
						world.setBlockToAir(nearby);
						world.removeTileEntity(nearby);
						world.createExplosion(null, nearby.getX(), nearby.getY(), nearby.getZ(), 8.0F, true);
						break;
					}
				}
			}
		}
	}

	private boolean isThereAnother(BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof GTTileMagicEnergyAbsorber) {
			GTTileMagicEnergyAbsorber absorber = (GTTileMagicEnergyAbsorber) tile;
			return absorber.portalMode && absorber.isAbovePortal;
		}
		return false;
	}

	private Iterable<BlockPos> getSurroundingBlocks() {
		int radius = 4;
		return BlockPos.getAllInBox(pos.offset(EnumFacing.SOUTH, radius).offset(EnumFacing.WEST, radius), pos.offset(EnumFacing.NORTH, radius).offset(EnumFacing.EAST, radius));
	}

	private boolean isDrawingEnergyFromADarkPlace() {
		if (this.isAbovePortal && this.storage + 128 <= this.maxStorage) {
			this.storage = this.storage + 128;
			return true;
		}
		return false;
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

	/**
	 * Thanks to OpenMods/OpenBlocks for being MIT which allows me to use the code
	 * below
	 */
	public static int getPlayerXP(EntityPlayer player) {
		return (int) (getExperienceForLevel(player.experienceLevel) + (player.experience * player.xpBarCap()));
	}

	public static void addPlayerXP(EntityPlayer player, int amount) {
		int experience = getPlayerXP(player) + amount;
		player.experienceTotal = experience;
		player.experienceLevel = getLevelForExperience(experience);
		int expForLevel = getExperienceForLevel(player.experienceLevel);
		player.experience = (float) (experience - expForLevel) / (float) player.xpBarCap();
	}

	public static int getLevelForExperience(int targetXp) {
		int level = 0;
		while (true) {
			final int xpToNextLevel = xpBarCap(level);
			if (targetXp < xpToNextLevel)
				return level;
			level++;
			targetXp -= xpToNextLevel;
		}
	}

	public static int getExperienceForLevel(int level) {
		if (level == 0)
			return 0;
		if (level <= 15)
			return sum(level, 7, 2);
		if (level <= 30)
			return 315 + sum(level - 15, 37, 5);
		return 1395 + sum(level - 30, 112, 9);
	}

	public static int xpBarCap(int level) {
		if (level >= 30)
			return 112 + (level - 30) * 9;
		if (level >= 15)
			return 37 + (level - 15) * 5;
		return 7 + level * 2;
	}

	private static int sum(int n, int a0, int d) {
		return n * (2 * a0 + (n - 1) * d) / 2;
	}

	public void updateGui() {
		this.getNetwork().updateTileGuiField(this, NBT_POTIONMODE);
		this.getNetwork().updateTileGuiField(this, NBT_XPMODE);
		this.getNetwork().updateTileGuiField(this, NBT_PORTALMODE);
	}

	@Override
	public void onNetworkUpdate(String field) {
		if (field.equals("isActive") && this.isActiveChanged()) {
			if (this.audioSource != null && this.audioSource.isRemoved()) {
				this.audioSource = null;
			}
			if (this.audioSource == null && this.getOperationSoundFile() != null) {
				this.audioSource = IC2.audioManager.createSource(this, PositionSpec.Center, this.getOperationSoundFile(), true, false, IC2.audioManager.defaultVolume);
			}
			if (this.getActive()) {
				if (this.audioSource != null) {
					this.audioSource.play();
				}
			} else if (this.audioSource != null) {
				this.audioSource.stop();
			}
		}
		super.onNetworkUpdate(field);
	}

	public ResourceLocation getOperationSoundFile() {
		return Ic2Sounds.generatorLoop;
	}

	@Override
	public void onNetworkEvent(EntityPlayer player, int event) {
		if (event == 1) {
			this.xpMode = !this.xpMode;
			this.setActive(false);
			this.updateGui();
		}
		if (event == 2) {
			this.potionMode = !this.potionMode;
			this.setActive(false);
			this.updateGui();
		}
		if (event == 3) {
			this.portalMode = !this.portalMode;
			this.setActive(false);
			this.updateGui();
		}
	}
}
