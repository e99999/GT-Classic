package gtclassic.api.gui;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import gtclassic.api.interfaces.IGTMultiTileStatus;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.util.math.Box2D;
import net.minecraft.client.gui.Gui;

public class GTGuiCompMultiTileStatus extends GuiComponent {

	IGTMultiTileStatus tile;

	public GTGuiCompMultiTileStatus(IGTMultiTileStatus tile, Box2D box) {
		super(box);
		this.tile = tile;
	}

	@Override
	public List<ActionRequest> getNeededRequests() {
		return Arrays.asList(ActionRequest.BackgroundDraw);
	}

	@Override
	public void drawBackground(GuiIC2 gui, int mouseX, int mouseY, float particalTicks) {
		int x = gui.getXOffset() + position.getX();
		int y = gui.getYOffset() + position.getY();
		if (!tile.getStructureValid()) {
			Gui.drawRect(x, y, x + position.getLenght(), y + position.getHeight(), new Color(255, 0, 0, 64).hashCode());
		}
	}
}
