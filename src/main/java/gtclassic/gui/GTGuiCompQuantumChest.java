package gtclassic.gui;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import gtclassic.tile.GTTileQuantumChest;
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
		gui.drawString("Items:", 12, 20, Color.white.hashCode());
		int stored = this.block.getQuantumCount();
		gui.drawString("" + stored, 12, 30, Color.white.hashCode());
	}
}
