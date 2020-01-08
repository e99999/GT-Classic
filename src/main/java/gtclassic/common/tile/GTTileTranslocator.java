package gtclassic.common.tile;

import java.util.Collections;
import java.util.List;

import gtclassic.common.GTLang;
import gtclassic.common.container.GTContainerTranslocator;
import gtclassic.common.util.GTFilterTranslocator;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.transport.IItemTransporter;
import ic2.core.inventory.transport.TransporterManager;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileTranslocator extends GTTileBufferBase implements IHasGui {

	public GTFilterTranslocator filter = new GTFilterTranslocator(this);

	public GTTileTranslocator() {
		super(9);
		this.hasRedstone = false;
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
	public void onBufferTick() {
		IItemTransporter in = TransporterManager.manager.getTransporter(world.getTileEntity(getImportTilePos()), true);
		if (in == null) {
			return;
		}
		IItemTransporter out = TransporterManager.manager.getTransporter(world.getTileEntity(getExportTilePos()), true);
		if (out == null) {
			return;
		}
		int limit = out.getSizeInventory(getFacing());
		for (int i = 0; i < limit; ++i) {
			ItemStack stack = in.removeItem(this.filter, getFacing(), 64, false);
			if (stack.isEmpty()) {
				break;
			}
			ItemStack added = out.addItem(stack, getFacing(), true);
			if (added.getCount() <= 0) {
				break;
			}
			in.removeItem(new BasicItemFilter(added), getFacing(), added.getCount(), true);
		}
	}

	@Override
	public List<ItemStack> getDrops() {
		return Collections.emptyList();
	}

	@Override
	public boolean isInventoryFull() {
		return false;
	}
}
