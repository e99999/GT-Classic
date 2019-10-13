package gtclassic.gui;

import java.util.Arrays;
import java.util.List;

import gtclassic.tile.GTTileMagicEnergyAbsorber;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.registry.Ic2GuiComp;
import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompMagicEnergyAbsorber extends GuiComponent {

	GTTileMagicEnergyAbsorber block;

	public GTGuiCompMagicEnergyAbsorber(GTTileMagicEnergyAbsorber tile) {
		super(Ic2GuiComp.nullBox);
		this.block = tile;
	}

	@Override
	public List<ActionRequest> getNeededRequests() {
		return Arrays.asList(ActionRequest.GuiInit, ActionRequest.ButtonNotify, ActionRequest.BackgroundDraw);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiInit(GuiIC2 gui) {
		gui.registerButton((new GTGuiButton(1, bX(gui, 11), bY(gui, 16), 15, 18))); // xp mode
		gui.registerButton((new GTGuiButton(2, bX(gui, 11), bY(gui, 33), 15, 18))); // potion mode
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
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawBackground(GuiIC2 gui, int mouseX, int mouseY, float particalTicks) {
		if (this.block.xpMode) {
			gui.drawTexturedModalRect(gui.getXOffset() + 10, gui.getYOffset() + 18, 176, 0, 17, 17);
		}
		if (this.block.potionMode) {
			gui.drawTexturedModalRect(gui.getXOffset() + 10, gui.getYOffset() + 35, 176, 0, 17, 17);
		}
	}

	private int bX(GuiIC2 gui, int position) {
		return gui.getXOffset() + position;
	}

	private int bY(GuiIC2 gui, int position) {
		return gui.getYOffset() + position;
	}
}
