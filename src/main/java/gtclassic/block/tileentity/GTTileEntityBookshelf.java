package gtclassic.block.tileentity;

import gtclassic.block.container.GTContainerBookshelf;
import gtclassic.util.GTLang;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileEntityBookshelf extends TileEntityMachine implements IHasGui {

	public GTTileEntityBookshelf() {
		super(8);
	}

	@Override
	public LocaleComp getBlockName() {
		return GTLang.bookshelf;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerBookshelf(player.inventory, this);
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);

		for (int i = 0; i < 7; i++) {
			handler.registerInputFilter(new BasicItemFilter(Items.BOOK), i);
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return !this.isInvalid();
	}

	@Override
	public void onLoaded() {
		hasBooks();
		super.onLoaded();
	}

	@Override
	public void onGuiClosed(EntityPlayer entityPlayer) {
		hasBooks();
	}

	@Override
	public boolean hasGui(EntityPlayer player) {
		return true;
	}

	public void hasBooks() {
		for (int i = 0; i < 8; i++) {
			if (this.getStackInSlot(i).getCount() > 0) {
				this.setActive(true);
				break;
			} else {
				this.setActive(false);
			}
		}
	}

}
