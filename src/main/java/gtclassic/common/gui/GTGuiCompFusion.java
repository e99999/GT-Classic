package gtclassic.common.gui;

import java.awt.Color;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import gtclassic.common.tile.multi.GTTileMultiFusionReactor;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.registry.Ic2GuiComp;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompFusion extends GuiComponent {

	GTTileMultiFusionReactor block;

	public GTGuiCompFusion(GTTileMultiFusionReactor tile) {
		super(Ic2GuiComp.nullBox);
		this.block = tile;
	}

	@Override
	public List<ActionRequest> getNeededRequests() {
		return Arrays.asList(ActionRequest.FrontgroundDraw, ActionRequest.BackgroundDraw);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawFrontground(GuiIC2 gui, int mouseX, int mouseY) {
		float progress = this.block.progress / this.block.recipeOperation * 100;
		if (progress > 100) {
			progress = 100;
		}
		gui.drawString("EU: "
				+ NumberFormat.getNumberInstance(Locale.US).format(this.block.energy), 10, 7, Color.cyan.hashCode());
		gui.drawString("Coils: " + this.block.status, 10, 17, Color.cyan.hashCode());
		gui.drawString("Progress: " + Math.round(progress) + "%", 10, 27, Color.cyan.hashCode());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawBackground(GuiIC2 gui, int mouseX, int mouseY, float particalTicks) {
		if (this.block.isActive) {
			gui.drawTexturedModalRect(gui.getXOffset() + 88, gui.getYOffset() + 36, 176, 0, 14, 14);
		}
	}
}
