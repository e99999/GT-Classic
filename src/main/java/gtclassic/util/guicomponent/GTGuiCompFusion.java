package gtclassic.util.guicomponent;

import java.util.Arrays;
import java.util.List;

import gtclassic.block.tileentity.GTTileEntityFusionComputer;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.registry.Ic2GuiComp;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompFusion extends GuiComponent {

	GTTileEntityFusionComputer block;
	int white = 16777215;
	int grey = 4210752;
	int red = 15599112;
	int green = 9567352;

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
		gui.drawString(block.getBlockName(), 89, 6, grey);

		if (this.block.getStatus() == 666) {
			gui.drawString("!", 12, 58, red);
		}

		else if (this.block.getStatus() == 1) {
			gui.drawString(": )", 12, 58, green);
		}

		else if (this.block.getStatus() == 0) {
			gui.drawString("n", 12, 58, white);
		}
	}

}