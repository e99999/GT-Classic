package gtclassic.gui;

import java.util.Arrays;
import java.util.List;

import gtclassic.tile.GTTileComputerCube;
import gtclassic.util.GTValues;
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
		return Arrays.asList(ActionRequest.GuiInit, ActionRequest.ButtonNotify, ActionRequest.GuiTick,
				ActionRequest.FrontgroundDraw, ActionRequest.BackgroundDraw);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawFrontground(GuiIC2 gui, int mouseX, int mouseY) {
		gui.drawCenteredString("[" + getTime(gui) + "]", 89, 9, GTValues.grey);
		gui.drawCenteredString("G.L.A.D.-OS", 91, 59, GTValues.white);
	}

	public String getTime(GuiIC2 gui) {
		long time = gui.getPlayer().getEntityWorld().getWorldTime();
		int hours = (int) ((time / 1000 + 7) % 24);
		int minutes = (int) (60 * (time % 1000) / 1000);
		return String.format("%02d:%02d", hours, minutes);

	}

}
