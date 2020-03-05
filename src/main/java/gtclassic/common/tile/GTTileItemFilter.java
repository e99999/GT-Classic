package gtclassic.common.tile;

import java.util.List;

import gtclassic.api.helpers.GTUtility;
import gtclassic.common.GTLang;
import gtclassic.common.container.GTContainerItemFilter;
import gtclassic.common.util.GTIFilters;
import ic2.core.RotationList;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileItemFilter extends GTTileBufferBase implements IHasGui {

	public GTIFilters.ItemFilter filter = new GTIFilters.ItemFilter(this);
	private static final int[] outputs = { 9, 10, 11, 12, 13, 14, 15, 16, 17 };

	public GTTileItemFilter() {
		super(18);
		this.hasInvertFilter = true;
		this.hasNbtFilter = true;
	}

	@Override
	public LocaleComp getBlockName() {
		return GTLang.ITEM_FILTER;
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Both, outputs);
		handler.registerDefaultSlotsForSide(RotationList.ALL, outputs);
		handler.registerInputFilter(new GTIFilters.ItemFilter(this), outputs);
		handler.registerSlotType(SlotType.Storage, outputs);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerItemFilter(player.inventory, this);
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
		GTUtility.exportFromMachineToSide(this, this.getFacing(), outputs);
	}

	@Override
	public List<ItemStack> getDrops() {
		List<ItemStack> drops = super.getDrops();
		drops.clear();
		for (int slot : outputs) {
			if (!this.inventory.get(slot).isEmpty()) {
				drops.add(this.inventory.get(slot).copy());
			}
		}
		return drops;
	}

	@Override
	public boolean isInventoryFull() {
		for (int slot : outputs) {
			if (this.inventory.get(slot).isEmpty()) {
				return false;
			}
		}
		return true;
	}
}
