package gtclassic.util.gui;

import java.util.Arrays;
import java.util.List;

import gtclassic.tileentity.GTTileEntityFusionComputer;
import gtclassic.util.GTValues;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.registry.Ic2GuiComp;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompFusion extends GuiComponent {

	GTTileEntityFusionComputer block;

	public GTGuiCompFusion(GTTileEntityFusionComputer tile) {
		super(Ic2GuiComp.nullBox);
		this.block = tile;
	}

	@Override
	public List<ActionRequest> getNeededRequests() {
		return Arrays.asList(ActionRequest.GuiInit, ActionRequest.ButtonNotify, ActionRequest.GuiTick,
				ActionRequest.FrontgroundDraw, ActionRequest.BackgroundDraw);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawFrontground(GuiIC2 gui, int mouseX, int mouseY) {

		if (this.block.getStatus() == 1) {
			gui.drawCenteredString("Shape Invalid", 114, 69, GTValues.red);
		}

		else if (this.block.getStatus() == 2) {
			gui.drawCenteredString("Shape Valid", 114, 69, GTValues.green);
		}

		else if (this.block.getStatus() == 0) {
			gui.drawCenteredString("Scanning...", 114, 69, GTValues.white);
		}
	}

}
