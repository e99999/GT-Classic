package gtclassic.common.gui;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import gtclassic.common.tile.datanet.GTTileComputerCube;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.registry.Ic2GuiComp;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompComputerCube extends GuiComponent {

	GTTileComputerCube block;

	public GTGuiCompComputerCube(GTTileComputerCube tile) {
		super(Ic2GuiComp.nullBox);
		this.block = tile;
	}

	@Override
	public List<ActionRequest> getNeededRequests() {
		return Arrays.asList(ActionRequest.GuiInit, ActionRequest.ButtonNotify, ActionRequest.GuiTick, ActionRequest.FrontgroundDraw, ActionRequest.BackgroundDraw);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawFrontground(GuiIC2 gui, int mouseX, int mouseY) {
		gui.drawString("Network Info:", 52, 8, Color.CYAN.hashCode());
		int count = this.block.nodeCount;
		gui.drawString("EU: " + this.block.energy, 52, 18, Color.CYAN.hashCode());
		gui.drawString("Nodes: " + count + "/512", 52, 28, Color.CYAN.hashCode());
		gui.drawString("Cost: -" + count + " EU/t", 52, 38, Color.CYAN.hashCode());
	}
}
