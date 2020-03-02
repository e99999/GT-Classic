package gtclassic.common.gui;

import java.util.Arrays;
import java.util.List;

import gtclassic.api.gui.GTGuiButton;
import gtclassic.api.helpers.GTHelperMath;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.util.math.Box2D;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompDigitalChest extends GuiComponent {

	TileEntityMachine block;
	private static final Box2D BOX = new Box2D(7, 118, 96, 18);

	public GTGuiCompDigitalChest(TileEntityMachine tile) {
		super(BOX);
		this.block = tile;
	}

	@Override
	public List<ActionRequest> getNeededRequests() {
		return Arrays.asList(ActionRequest.GuiInit, ActionRequest.ButtonNotify, ActionRequest.ToolTip);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiInit(GuiIC2 gui) {
		gui.registerButton((new GTGuiButton(0, bX(gui, 7), bY(gui, 116), 18, 20)));
		gui.registerButton((new GTGuiButton(1, bX(gui, 25), bY(gui, 116), 18, 20)));
		gui.registerButton((new GTGuiButton(2, bX(gui, 43), bY(gui, 116), 18, 20)));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onButtonClick(GuiIC2 gui, GuiButton button) {
		if (button.id == 0) {
			this.block.getNetwork().initiateClientTileEntityEvent(this.block, 0);
		}
		if (button.id == 1) {
			this.block.getNetwork().initiateClientTileEntityEvent(this.block, 1);
		}
		if (button.id == 2) {
			this.block.getNetwork().initiateClientTileEntityEvent(this.block, 2);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onToolTipCollecting(GuiIC2 gui, int mouseX, int mouseY, List<String> tooltips) {
		if (this.isMouseOver(mouseX, mouseY)) {
			if (mouseX < 25) {
				tooltips.add(I18n.format("button.digitalchest0"));
			}
			if (GTHelperMath.within(mouseX, 25, 42)) {
				tooltips.add(I18n.format("button.digitalchest1"));
			}
			if (GTHelperMath.within(mouseX, 43, 60)) {
				tooltips.add(I18n.format("button.sort"));
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
