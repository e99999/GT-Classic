package gtclassic.common.gui;

import java.awt.Color;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import gtclassic.api.helpers.GTHelperMath;
import gtclassic.common.GTLang;
import gtclassic.common.tile.GTTileUUMAssembler;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.util.math.Box2D;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompUUMAssembler extends GuiComponent {

	GTTileUUMAssembler block;
	private static final Box2D BOX = new Box2D(97, 57, 57, 18);

	public GTGuiCompUUMAssembler(GTTileUUMAssembler tile) {
		super(BOX);
		this.block = tile;
	}

	@Override
	public List<ActionRequest> getNeededRequests() {
		return Arrays.asList(ActionRequest.FrontgroundDraw, ActionRequest.ToolTip);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawFrontground(GuiIC2 gui, int mouseX, int mouseY) {
		gui.drawString("EU: "
				+ NumberFormat.getNumberInstance(Locale.US).format(this.block.energy), 12, 18, Color.cyan.hashCode());
		int stored = this.block.getStoredUU();
		gui.drawString("UU: "
				+ NumberFormat.getNumberInstance(Locale.US).format(stored), 12, 28, Color.cyan.hashCode());
		int cost = this.block.getCurrentCost();
		gui.drawString("Cost: " + cost, 12, 38, Color.cyan.hashCode());
		int per = this.block.getAmountPer();
		gui.drawString("Produced: " + per, 12, 48, Color.cyan.hashCode());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onToolTipCollecting(GuiIC2 gui, int mouseX, int mouseY, List<String> tooltips) {
		if (this.isMouseOver(mouseX, mouseY)) {
			if (mouseX < 114) {
				tooltips.add(I18n.format(GTLang.BUTTON_UUM_CLEAR));
			}
			if (GTHelperMath.within(mouseX, 115, 130)) {
				tooltips.add(I18n.format(GTLang.BUTTON_UUM_SCAN));
			}
			if (mouseX > 130 && this.block.inventory.get(11).isEmpty()) {
				tooltips.add(I18n.format(GTLang.BUTTON_UUM_CLEARTARGET));
			}
		}
	}
}
