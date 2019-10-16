package gtclassic.gui;

import java.util.Arrays;
import java.util.List;

import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.registry.Ic2GuiComp;
import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompDigitalChest extends GuiComponent {

	TileEntityMachine block;

	public GTGuiCompDigitalChest(TileEntityMachine tile) {
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

	private int bX(GuiIC2 gui, int position) {
		return gui.getXOffset() + position;
	}

	private int bY(GuiIC2 gui, int position) {
		return gui.getYOffset() + position;
	}
}
