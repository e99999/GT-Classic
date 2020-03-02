package gtclassic.common.gui;

import java.util.List;

import gtclassic.api.gui.GTGuiButton;
import gtclassic.api.helpers.GTHelperMath;
import gtclassic.common.tile.GTTileTypeFilter;
import ic2.core.inventory.gui.GuiIC2;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompTypeFilter extends GTGuiCompBuffer {

	GTTileTypeFilter filterTile;

	public GTGuiCompTypeFilter(GTTileTypeFilter tile, InventoryPlayer player) {
		super(tile, player);
		this.filterTile = tile;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiInit(GuiIC2 gui) {
		super.onGuiInit(gui);
		gui.registerButton(new GTGuiButton(4, bX(gui, 34), bY(gui, 20), 18, 20));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onButtonClick(GuiIC2 gui, GuiButton button) {
		super.onButtonClick(gui, button);
		if (button.id == 4) {
			this.tile.getNetwork().initiateClientTileEntityEvent(this.tile, 4);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onToolTipCollecting(GuiIC2 gui, int mouseX, int mouseY, List<String> tooltips) {
		super.onToolTipCollecting(gui, mouseX, mouseY, tooltips);
		if (GTHelperMath.within(mouseX, 34, 52) && GTHelperMath.within(mouseY, 20, 40)) {
			tooltips.add(I18n.format("Current Filter: " + this.filterTile.getCurrentFilter() + " ("
					+ this.filterTile.getCurrentIndex() + "/" + (GTTileTypeFilter.getFilterListSize() - 1) + ")"));
		}
	}
}
