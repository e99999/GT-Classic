package gtclassic.common.gui;

import java.util.Arrays;
import java.util.List;

import gtclassic.api.gui.GTGuiButton;
import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.registry.Ic2GuiComp;
import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompAESU extends GuiComponent {

	TileEntityElectricBlock block;

	public GTGuiCompAESU(TileEntityElectricBlock tile) {
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

	private int bX(GuiIC2 gui, int position) {
		return gui.getXOffset() + position;
	}

	private int bY(GuiIC2 gui, int position) {
		return gui.getYOffset() + position;
	}
}
