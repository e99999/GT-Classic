package gtclassic.tile;

import gtclassic.container.GTContainerTranslocator;
import gtclassic.util.int3;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.inventory.transport.IItemTransporter;
import ic2.core.inventory.transport.TransporterManager;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileTranslocator extends TileEntityMachine implements IHasGui, ITickable {

	// Import and Export Booleans
	boolean allowImport = true;
	boolean allowExport = true;

	public GTTileTranslocator() {
		super(9);
		setWorld(world);
	}

	@Override
	public LocaleComp getBlockName() {
		return new LocaleBlockComp(this.getBlockType().getUnlocalizedName());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerTranslocator(player.inventory, this);
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		for (int i = 0; i < 8; i++) {
			handler.registerDefaultSlotAccess(AccessRule.Both, i);
			handler.registerDefaultSlotsForSide(RotationList.ALL, i);
			handler.registerSlotType(SlotType.Input, i);
			handler.registerSlotType(SlotType.Output, i);
		}
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
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	public void update() {
		if (world.getTotalWorldTime() % 20 == 0) {
			tryImportItems();
			tryExportItems();
		}
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
		if (this.allowImport && world.getTotalWorldTime() % 20 == 0) {
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
				ItemStack added = controller.addItem(stack, getFacing().SOUTH, true);
				if (added.getCount() <= 0) {
					break;
				}
				slave.removeItem(CommonFilters.Anything, getFacing().getOpposite(), 1, true);
			}
		}
	}

	@SuppressWarnings("static-access")
	public void tryExportItems() {
		if (this.allowImport && world.getTotalWorldTime() % 20 == 0) {
			IItemTransporter slave = TransporterManager.manager.getTransporter(getExportTile(), true);
			if (slave == null) {
				return;
			}
			IItemTransporter controller = TransporterManager.manager.getTransporter(this, true);
			int limit = 64;
			for (int i = 0; i < limit; ++i) {
				ItemStack stack = controller.removeItem(CommonFilters.Anything, getFacing().NORTH, 1, false);
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
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return this.getFacing() != facing;
	}
}
