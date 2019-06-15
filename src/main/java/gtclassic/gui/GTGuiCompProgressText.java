package gtclassic.gui;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.registry.Ic2GuiComp;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompProgressText extends GuiComponent {

	IProgressMachine tile;
	int x;
	int y;

	public GTGuiCompProgressText(IProgressMachine tile, int x, int y) {
		super(Ic2GuiComp.nullBox);
		this.tile = tile;
		this.x = x;
		this.y = y;
	}

	@Override
	public List<ActionRequest> getNeededRequests() {
		return Arrays.asList(ActionRequest.FrontgroundDraw);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawFrontground(GuiIC2 gui, int mouseX, int mouseY) {
		float progress = this.tile.getProgress() / this.tile.getMaxProgress() * 100;
		if (progress > this.tile.getMaxProgress()) {
			progress = this.tile.getMaxProgress();
		}
		gui.drawString(Math.round(progress) + "%", this.x, this.y, Color.cyan.hashCode());
	}
}
