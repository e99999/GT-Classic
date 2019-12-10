package gtclassic.common.tile;

import java.util.Collections;
import java.util.List;

import gtclassic.api.helpers.int3;
import gtclassic.common.GTLang;
import gtclassic.common.container.GTContainerTranslocator;
import gtclassic.common.util.GTFilterTranslocator;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.transport.IItemTransporter;
import ic2.core.inventory.transport.TransporterManager;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileTranslocator extends GTTileBaseBuffer implements IHasGui {

	public GTFilterTranslocator filter = new GTFilterTranslocator(this);

	public GTTileTranslocator() {
		super(9);
	}

	@Override
	public LocaleComp getBlockName() {
		return GTLang.TRANSLOCATOR;
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
		if (world.getTotalWorldTime() % 10 == 0) {
			tryMoveItems();
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

	public void tryMoveItems() {
		IItemTransporter slave = TransporterManager.manager.getTransporter(getImportTile(), true);
		if (slave == null) {
			return;
		}
		IItemTransporter controller = TransporterManager.manager.getTransporter(getExportTile(), true);
		if (controller == null) {
			return;
		}
		tryBlacklistPipe(this, getFacing());
		int limit = 64;
		for (int i = 0; i < limit; ++i) {
			ItemStack stack = slave.removeItem(this.filter, getFacing(), 1, false);
			if (stack.isEmpty()) {
				break;
			}
			ItemStack added = controller.addItem(stack, getFacing(), true);
			if (added.getCount() <= 0) {
				break;
			}
			slave.removeItem(this.filter, getFacing(), 1, true);
		}
	}

	@Override
	public List<ItemStack> getDrops() {
		return Collections.emptyList();
	}
}
