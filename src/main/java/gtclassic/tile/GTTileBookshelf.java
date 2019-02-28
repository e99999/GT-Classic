package gtclassic.tile;

import java.util.HashSet;
import java.util.Set;

import gtclassic.container.GTContainerBookshelf;
import gtclassic.util.GTBookshelfFilter;
import gtclassic.util.GTValues;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.util.math.MathUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileBookshelf extends TileEntityMachine implements IHasGui {

	Set<Integer> storedSlots = new HashSet<Integer>();

	public GTTileBookshelf() {
		super(8);
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	public LocaleComp getBlockName() {
		return GTValues.bookshelf;
	}

	@Override
	public boolean canUpdate() {
		return false;
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
		handler.registerInputFilter(new GTBookshelfFilter(this), MathUtil.fromTo(0, 8));
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return !this.isInvalid();
	}

	@Override
	public void onLoaded() {
		super.onLoaded();
		for (int i = 0; i < 8; i++) {
			if (getStackInSlot(i).getCount() > 0) {
				storedSlots.add(i);
				continue;
			}
			storedSlots.remove(i);
		}
		setActive(storedSlots.size() > 0);
	}

	@Override
	public void setStackInSlot(int slot, ItemStack stack) {
		super.setStackInSlot(slot, stack);
		if (isSimulating()) {
			if (stack.getCount() > 0) {
				storedSlots.add(slot);
			} else {
				storedSlots.remove(slot);
			}
			setActive(storedSlots.size() > 0);
		}
	}

	@Override
	public void onGuiClosed(EntityPlayer entityPlayer) {
	}

	@Override
	public boolean hasGui(EntityPlayer player) {
		return true;
	}
}
