package gtclassic.common.gui;

import java.util.Arrays;
import java.util.List;

import gtclassic.api.gui.GTGuiButton;
import gtclassic.api.helpers.GTHelperMath;
import gtclassic.common.GTConfig;
import gtclassic.common.tile.GTTileMagicEnergyAbsorber;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.util.math.Box2D;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompMagicEnergyAbsorber extends GuiComponent {

	GTTileMagicEnergyAbsorber block;
	private static final Box2D BOX = new Box2D(10, 18, 16, 70);

	public GTGuiCompMagicEnergyAbsorber(GTTileMagicEnergyAbsorber tile) {
		super(BOX);
		this.block = tile;
	}

	@Override
	public List<ActionRequest> getNeededRequests() {
		return Arrays.asList(ActionRequest.GuiInit, ActionRequest.ButtonNotify, ActionRequest.BackgroundDraw, ActionRequest.ToolTip);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiInit(GuiIC2 gui) {
		gui.registerButton((new GTGuiButton(1, bX(gui, 11), bY(gui, 16), 15, 18))); // xp mode
		gui.registerButton((new GTGuiButton(2, bX(gui, 11), bY(gui, 33), 15, 18))); // potion mode
		//gui.registerButton((new GTGuiButton(3, bX(gui, 11), bY(gui, 50), 15, 18))); // portal mode
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onButtonClick(GuiIC2 gui, GuiButton button) {
		if (button.id == 1) {
			this.block.getNetwork().initiateClientTileEntityEvent(this.block, 1);
		}
		if (button.id == 2) {
			this.block.getNetwork().initiateClientTileEntityEvent(this.block, 2);
		}
		if (button.id == 3) {
			this.block.getNetwork().initiateClientTileEntityEvent(this.block, 3);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawBackground(GuiIC2 gui, int mouseX, int mouseY, float particalTicks) {
		if (this.block.xpMode) {
			gui.drawTexturedModalRect(gui.getXOffset() + 10, gui.getYOffset() + 18, 176, 0, 17, 16);
		}
		if (this.block.potionMode) {
			gui.drawTexturedModalRect(gui.getXOffset() + 10, gui.getYOffset() + 35, 176, 0, 17, 16);
		}
		if (this.block.portalMode) {
			gui.drawTexturedModalRect(gui.getXOffset() + 10, gui.getYOffset() + 52, 176, 0, 17, 16);
			if (GTConfig.general.oneMagicAbsorberPerEndPortal) {
				gui.drawTexturedModalRect(gui.getXOffset() + 27, gui.getYOffset() + 52, 176, 16, 17, 16);
			}
		}
		//TODO Remove this after release
		gui.drawTexturedModalRect(gui.getXOffset() + 10, gui.getYOffset() + 52, 200, 0, 34, 16);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onToolTipCollecting(GuiIC2 gui, int mouseX, int mouseY, List<String> tooltips) {
		if (this.isMouseOver(mouseX, mouseY)) {
			if (mouseY < 33) {
				tooltips.add(I18n.format("button.magicabsorber0"));
			}
			if (GTHelperMath.within(mouseY, 33, 50)) {
				tooltips.add(I18n.format("button.magicabsorber1"));
			}
			//if (GTHelperMath.within(mouseY, 51, 68)) {
			//	tooltips.add(I18n.format("button.magicabsorber3"));
			//	tooltips.add(I18n.format("button.magicabsorber4"));
			//}
		}
	}

	private int bX(GuiIC2 gui, int position) {
		return gui.getXOffset() + position;
	}

	private int bY(GuiIC2 gui, int position) {
		return gui.getYOffset() + position;
	}
}
