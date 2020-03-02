package gtclassic.api.gui;

import java.util.Arrays;
import java.util.List;

import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.util.math.Box2D;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompSort extends GuiComponent {

	TileEntityMachine block;
	// private static final Box2D BOX = new Box2D(7, 62, 18, 18);
	int x;
	int y;

	public GTGuiCompSort(TileEntityMachine tile, int x, int y) {
		super(new Box2D(x, y, 18, 18));
		this.block = tile;
		this.x = x;
		this.y = y;
	}

	@Override
	public List<ActionRequest> getNeededRequests() {
		return Arrays.asList(ActionRequest.GuiInit, ActionRequest.ButtonNotify, ActionRequest.ToolTip);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiInit(GuiIC2 gui) {
		gui.registerButton((new GTGuiButton(1, bX(gui, x), bY(gui, y), 18, 20)));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onButtonClick(GuiIC2 gui, GuiButton button) {
		if (button.id == 1) {
			this.block.getNetwork().initiateClientTileEntityEvent(this.block, 1);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onToolTipCollecting(GuiIC2 gui, int mouseX, int mouseY, List<String> tooltips) {
		if (this.isMouseOver(mouseX, mouseY)) {
			tooltips.add(I18n.format("button.sort"));
		}
	}

	private int bX(GuiIC2 gui, int position) {
		return gui.getXOffset() + position;
	}

	private int bY(GuiIC2 gui, int position) {
		return gui.getYOffset() + position;
	}
}
