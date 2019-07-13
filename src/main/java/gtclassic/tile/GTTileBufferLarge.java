package gtclassic.tile;

import gtclassic.container.GTContainerBufferLarge;
import gtclassic.util.GTLang;
import gtclassic.util.int3;
import ic2.core.RotationList;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.inventory.transport.IItemTransporter;
import ic2.core.inventory.transport.TransporterManager;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.util.math.MathUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileBufferLarge extends GTTileBaseBuffer implements IHasGui, ITickable {

	public GTTileBufferLarge() {
		super(27);
	}

	@Override
	public LocaleComp getBlockName() {
		return GTLang.BUFFER_LARGE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerBufferLarge(player.inventory, this);
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		int[] array = MathUtil.fromTo(0, 27);
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Both, array);
		handler.registerDefaultSlotsForSide(RotationList.ALL, array);
		handler.registerSlotType(SlotType.Storage, array);
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
	public void update() {
		if (world.getTotalWorldTime() % 20 == 0) {
			tryExportItems();
		}
	}

	public TileEntity getExportTile() {
		int3 dir = new int3(getPos(), getFacing());
		return world.getTileEntity(dir.forward(1).asBlockPos());
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
