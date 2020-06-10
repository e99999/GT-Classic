package gtclassic.common.gui;

import java.util.Arrays;
import java.util.List;

import gtclassic.api.gui.GTGuiButton;
import gtclassic.api.helpers.GTHelperMath;
import gtclassic.common.GTLang;
import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.util.math.Box2D;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompAESU extends GuiComponent {

	TileEntityElectricBlock block;
	private static final Box2D BOX = new Box2D(106, 4, 18, 72);

	public GTGuiCompAESU(TileEntityElectricBlock tile) {
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
		gui.registerButton((new GTGuiButton(4, bX(gui, 106), bY(gui, 2), 18, 20)));// +64
		gui.registerButton((new GTGuiButton(3, bX(gui, 106), bY(gui, 20), 18, 20)));// +1
		gui.registerButton((new GTGuiButton(2, bX(gui, 106), bY(gui, 38), 18, 20)));// -1
		gui.registerButton((new GTGuiButton(1, bX(gui, 106), bY(gui, 56), 18, 20)));// -64
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onButtonClick(GuiIC2 gui, GuiButton button) {
		if (button.id == 4) {
			this.block.getNetwork().initiateClientTileEntityEvent(this.block, 4);
		}
		if (button.id == 3) {
			this.block.getNetwork().initiateClientTileEntityEvent(this.block, 3);
		}
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
			if (GTHelperMath.within(mouseY, 0, 21)) {
				tooltips.add(I18n.format(GTLang.BUTTON_AESU_ADD64));
			}
			if (GTHelperMath.within(mouseY, 22, 39)) {
				tooltips.add(I18n.format(GTLang.BUTTON_AESU_ADD1));
			}
			if (GTHelperMath.within(mouseY, 40, 56)) {
				tooltips.add(I18n.format(GTLang.BUTTON_AESU_SUB1));
			}
			if (GTHelperMath.within(mouseY, 57, 72)) {
				tooltips.add(I18n.format(GTLang.BUTTON_AESU_SUB64));
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
