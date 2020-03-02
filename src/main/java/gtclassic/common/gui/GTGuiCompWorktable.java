package gtclassic.common.gui;

import java.util.Arrays;
import java.util.List;

import gtclassic.api.gui.GTGuiButton;
import gtclassic.common.container.GTContainerWorktable;
import gtclassic.common.tile.GTTileWorktable;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.util.math.Box2D;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompWorktable extends GuiComponent {

	GTContainerWorktable container;
	GTTileWorktable block;
	private static final Box2D BOX = new Box2D(135, 25, 36, 20);

	public GTGuiCompWorktable(GTTileWorktable tile, GTContainerWorktable container) {
		super(BOX);
		this.block = tile;
		this.container = container;
	}

	@Override
	public List<ActionRequest> getNeededRequests() {
		return Arrays.asList(ActionRequest.GuiInit, ActionRequest.ButtonNotify, ActionRequest.ToolTip);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiInit(GuiIC2 gui) {
		gui.registerButton((new GTGuiButton(2, bX(gui, 153), bY(gui, 25), 18, 20)));// shift crafting grid items into
																					// player inventory
		gui.registerButton((new GTGuiButton(1, bX(gui, 135), bY(gui, 25), 18, 20)));// shift crafting grid items into
																					// main inventory
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onButtonClick(GuiIC2 gui, GuiButton button) {
		if (button.id == 2) {
			this.block.getNetwork().initiateClientTileEntityEvent(this.block, 2);
		}
		if (button.id == 1) {
			this.block.getNetwork().initiateClientTileEntityEvent(this.block, 1);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onToolTipCollecting(GuiIC2 gui, int mouseX, int mouseY, List<String> tooltips) {
		if (this.isMouseOver(mouseX, mouseY)) {
			if (mouseX > 153) {
				tooltips.add(I18n.format("button.worktable0"));
			} else {
				tooltips.add(I18n.format("button.worktable1"));
			}
		}
	}

	private int bX(GuiIC2 gui, int position) {
		return gui.getXOffset() + position;
	}

	private int bY(GuiIC2 gui, int position) {
		return gui.getYOffset() + position;
	}
}
