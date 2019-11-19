package gtclassic.common.gui;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import gtclassic.common.tile.GTTileQuantumChest;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.registry.Ic2GuiComp;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompQuantumChest extends GuiComponent {

	GTTileQuantumChest block;

	public GTGuiCompQuantumChest(GTTileQuantumChest tile) {
		super(Ic2GuiComp.nullBox);
		this.block = tile;
	}

	@Override
	public List<ActionRequest> getNeededRequests() {
		return Arrays.asList(ActionRequest.FrontgroundDraw);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawFrontground(GuiIC2 gui, int mouseX, int mouseY) {
		gui.drawString("Items:", 11, 20, Color.cyan.hashCode());
		int stored = this.block.getQuantumCount();
		gui.drawString("" + stored, 11, 30, Color.cyan.hashCode());
	}
}
