package gtclassic.common.tile;

import gtclassic.api.helpers.GTHelperStack;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.common.GTItems;
import gtclassic.common.GTLang;
import gtclassic.common.container.GTContainerDigitalChest;
import gtclassic.common.util.GTIFilters;
import ic2.api.network.INetworkClientTileEntityEventListener;
import ic2.core.IC2;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.util.math.MathUtil;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileDigitalChest extends TileEntityMachine implements IHasGui, INetworkClientTileEntityEventListener {

	public GTTileDigitalChest() {
		super(55);
	}

	@Override
	public LocaleComp getBlockName() {
		return GTLang.DIGITAL_CHEST;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerDigitalChest(player.inventory, this);
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		int[] array = MathUtil.fromTo(0, 54);
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Both, array);
		handler.registerDefaultSlotsForSide(RotationList.ALL, array);
		handler.registerSlotType(SlotType.Storage, array);
		handler.registerInputFilter(new GTIFilters.DigitalChestFilter(), array);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return !this.isInvalid();
	}

	@Override
	public void onGuiClosed(EntityPlayer entityPlayer) {
		// needed for construction
	}

	@Override
	public boolean hasGui(EntityPlayer player) {
		return true;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing() && facing.getAxis().isHorizontal();
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	public ItemStack dataSlot() {
		return this.getStackInSlot(54);
	}

	public void dataSlot(ItemStack stack) {
		this.setStackInSlot(54, stack);
	}

	public void tryReadOrbData(EntityPlayer player) {
		if (dataSlot().isEmpty()) {
			IC2.platform.messagePlayer(player, "No Data Orb present!");
			return;
		}
		if (StackUtil.isStackEqual(dataSlot(), GTMaterialGen.get(GTItems.orbDataStorage), false, true)) {
			if (dataSlot().getCount() > 1) {
				IC2.platform.messagePlayer(player, "Read Failed: Too many orbs");
				return;
			}
			for (int i = 0; i < 54; ++i) {
				ItemStack stack = inventory.get(i);
				if (!stack.isEmpty()) {
					IC2.platform.messagePlayer(player, "Read Failed: Chest is not empty");
					return;
				}
			}
			NBTTagCompound nbt = StackUtil.getNbtData(dataSlot());
			NBTTagList list = nbt.getTagList("Items", 10);
			if (list.isEmpty()) {
				IC2.platform.messagePlayer(player, "Read Failed: No data to read");
				return;
			}
			for (int i = 0; i < list.tagCount(); ++i) {
				NBTTagCompound data = list.getCompoundTagAt(i);
				int slot = data.getInteger("Slot");
				this.inventory.set(slot, new ItemStack(data));
			}
			dataSlot(GTMaterialGen.get(GTItems.orbData));
		}
	}

	public void tryWriteOrbData(EntityPlayer player) {
		if (dataSlot().isEmpty()) {
			IC2.platform.messagePlayer(player, "No Data Orb present!");
			return;
		}
		if (GTHelperStack.isEqual(dataSlot(), GTMaterialGen.get(GTItems.orbData))) {
			if (dataSlot().getCount() > 1) {
				IC2.platform.messagePlayer(player, "Write Failed: too many orbs");
				return;
			}
			dataSlot(GTMaterialGen.get(GTItems.orbDataStorage));
			NBTTagCompound nbt = StackUtil.getOrCreateNbtData(dataSlot());
			NBTTagList list = new NBTTagList();
			for (int i = 0; i < 54; ++i) {
				if (this.inventory.get(i).isEmpty()) {
					continue;
				}
				NBTTagCompound data = new NBTTagCompound();
				this.inventory.get(i).writeToNBT(data);
				data.setInteger("Slot", i);
				list.appendTag(data);
				this.inventory.set(i, ItemStack.EMPTY);
			}
			nbt.setTag("Items", list);
		}
	}

	@Override
	public void onNetworkEvent(EntityPlayer player, int event) {
		if (event == 0) {
			tryReadOrbData(player);
		}
		if (event == 1) {
			tryWriteOrbData(player);
		}
		if (event == 2) {
			GTHelperStack.tryCondenseInventory(this, 0, this.inventory.size() - 1);
		}
	}
}
