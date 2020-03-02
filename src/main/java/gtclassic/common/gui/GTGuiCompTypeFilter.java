package gtclassic.common.gui;

import java.util.List;

import gtclassic.api.helpers.GTHelperMath;
import gtclassic.common.tile.GTTileTypeFilter;
import ic2.core.inventory.gui.GuiIC2;
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
	public void onToolTipCollecting(GuiIC2 gui, int mouseX, int mouseY, List<String> tooltips) {
		super.onToolTipCollecting(gui, mouseX, mouseY, tooltips);
		if (GTHelperMath.within(mouseX, 54, 94) && GTHelperMath.within(mouseY, 22, 39)) {
			tooltips.add(I18n.format("Current Filter: " + this.filterTile.getCurrentFilter()));
		}
	}
}
