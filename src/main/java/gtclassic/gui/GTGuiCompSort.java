package gtclassic.gui;

import java.util.Arrays;
import java.util.List;

import gtclassic.tile.GTTileCabinet;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.registry.Ic2GuiComp;
import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompSort extends GuiComponent {

	GTTileCabinet block;

	public GTGuiCompSort(GTTileCabinet tile) {
		super(Ic2GuiComp.nullBox);
		this.block = tile;
	}

	@Override
	public List<ActionRequest> getNeededRequests() {
		return Arrays.asList(ActionRequest.GuiInit, ActionRequest.ButtonNotify);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiInit(GuiIC2 gui) {
		gui.registerButton((new GTGuiButton(1, bX(gui, 150), bY(gui, 4), 19, 10)));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onButtonClick(GuiIC2 gui, GuiButton button) {
		if (button.id == 1) {
			this.block.getNetwork().initiateClientTileEntityEvent(this.block, 1);
		}
	}

	private int bX(GuiIC2 gui, int position) {
		return gui.getXOffset() + position;
	}

	private int bY(GuiIC2 gui, int position) {
		return gui.getYOffset() + position;
	}
}
