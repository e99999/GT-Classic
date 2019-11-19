package gtclassic.common.tile;

import gtclassic.GTMod;
import gtclassic.api.helpers.int3;
import gtclassic.common.container.GTContainerAutocrafter;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.inventory.transport.IItemTransporter;
import ic2.core.inventory.transport.TransporterManager;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileAutocrafter extends TileEntityElecMachine implements ITickable, IHasGui {

	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID, "textures/gui/autocrafter.png");
	protected static final int[] slotInputs = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
	protected static final int[] slotContainer = { 9, 10, 11, 12, 13, 14, 15, 16, 17 };
	protected static final int[] slotOutputs = { 28 };

	public GTTileAutocrafter() {
		super(29, 32);
		this.maxEnergy = 10000;
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Import, slotInputs);
		handler.registerDefaultSlotAccess(AccessRule.Export, slotOutputs);
		handler.registerDefaultSlotAccess(AccessRule.Export, slotContainer);
		handler.registerDefaultSlotsForSide(RotationList.DOWN.invert(), slotInputs);
		handler.registerDefaultSlotsForSide(RotationList.UP.invert(), slotOutputs);
		handler.registerDefaultSlotsForSide(RotationList.DOWN, slotContainer);
		handler.registerInputFilter(CommonFilters.Anything, slotInputs);
		handler.registerSlotType(SlotType.Input, slotInputs);
		handler.registerSlotType(SlotType.Output, slotOutputs);
		handler.registerSlotType(SlotType.Output, slotContainer);
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return !this.isInvalid();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerAutocrafter(player.inventory, this);
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
	public void onGuiClosed(EntityPlayer arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update() {
		if (world.getTotalWorldTime() % 20 == 0) {
			tryImportItems();
			// TODO ALL STUFF BUT BETTER
			if (!this.inventory.get(19).isEmpty()) {
				tryExportItems();
			}
		}
	}

	public boolean canAttemptToCraft() {
		// if there is not enough power return
		if (this.energy < 50) {
			return false;
		}
		return true;
	}

	public TileEntity getImportTile() {
		int3 dir = new int3(getPos(), getFacing());
		return world.getTileEntity(dir.back(1).asBlockPos());
	}

	public TileEntity getExportTile() {
		int3 dir = new int3(getPos(), getFacing());
		return world.getTileEntity(dir.forward(1).asBlockPos());
	}

	@SuppressWarnings("static-access")
	public void tryImportItems() {
		IItemTransporter slave = TransporterManager.manager.getTransporter(getImportTile(), true);
		if (slave == null) {
			return;
		}
		IItemTransporter controller = TransporterManager.manager.getTransporter(this, true);
		int limit = 64;
		for (int i = 0; i < limit; ++i) {
			ItemStack stack = slave.removeItem(CommonFilters.Anything, getFacing().getOpposite(), 1, false);
			if (stack.isEmpty()) {
				break;
			}
			ItemStack added = controller.addItem(stack, getFacing().UP, true);
			if (added.getCount() <= 0) {
				break;
			}
			slave.removeItem(CommonFilters.Anything, getFacing().getOpposite(), 1, true);
		}
	}

	@SuppressWarnings("static-access")
	public void tryExportItems() {
		IItemTransporter slave = TransporterManager.manager.getTransporter(getExportTile(), true);
		if (slave == null) {
			return;
		}
		IItemTransporter controller = TransporterManager.manager.getTransporter(this, true);
		int limit = 64;
		for (int i = 0; i < limit; ++i) {
			ItemStack stack = controller.removeItem(CommonFilters.Anything, getFacing().EAST, 1, false);
			if (stack.isEmpty()) {
				break;
			}
			ItemStack added = slave.addItem(stack, getFacing().UP, true);
			if (added.getCount() <= 0) {
				break;
			}
			controller.removeItem(CommonFilters.Anything, getFacing().getOpposite(), 1, true);
		}
	}

	@Override
	public boolean supportsNotify() {
		return true;
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	public ResourceLocation getGuiTexture() {
		return GUI_LOCATION;
	}
}
