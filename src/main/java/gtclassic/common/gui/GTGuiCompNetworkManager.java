package gtclassic.common.gui;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import gtclassic.common.tile.datanet.GTTileNetworkManager;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.registry.Ic2GuiComp;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompNetworkManager extends GuiComponent {

	GTTileNetworkManager block;

	public GTGuiCompNetworkManager(GTTileNetworkManager tile) {
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
		gui.drawString("Data Network Info:", 11, 8, Color.CYAN.hashCode());
		int count = this.block.getNodeCount();
		int drain = this.block.getEnergyCost();
		gui.drawString("EU: " + this.block.energy, 11, 18, Color.CYAN.hashCode());
		gui.drawString("Nodes: " + count + "/64", 11, 28, Color.CYAN.hashCode());
		gui.drawString("Cost: -" + drain + " EU/t", 11, 38, Color.CYAN.hashCode());
	}
}
