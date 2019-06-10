package gtclassic.gui;

import java.util.Arrays;
import java.util.List;

import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileBaseBuffer;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.buttons.IconButton;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.registry.Ic2GuiComp;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompBuffer extends GuiComponent {

	public static final ItemStack cable = GTMaterialGen.getIc2(Ic2Items.insulatedCopperCable, 1);;
	boolean lastMode;
	GTTileBaseBuffer tile;

	public GTGuiCompBuffer(GTTileBaseBuffer tile) {
		super(Ic2GuiComp.nullBox);
		this.tile = tile;
	}

	@Override
	public List<ActionRequest> getNeededRequests() {
		return Arrays.asList(ActionRequest.GuiInit, ActionRequest.ButtonNotify, ActionRequest.GuiTick, ActionRequest.FrontgroundDraw, ActionRequest.BackgroundDraw);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiInit(GuiIC2 gui) {
		this.lastMode = this.tile.conduct;
		gui.registerButton((new IconButton(0, gui.getXOffset() + 7, gui.getYOffset()
				+ 60, 20, 20)).setItemStack(cable).addText("Enable/Disable Power Output"));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiTick(GuiIC2 gui) {
		if (this.lastMode != this.tile.conduct) {
			this.lastMode = this.tile.conduct;
			gui.getCastedButton(0, IconButton.class).clearText().addText("Outputs Power: " + this.tile.conduct);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onButtonClick(GuiIC2 gui, GuiButton button) {
		this.tile.getNetwork().initiateClientTileEntityEvent(this.tile, 0);
	}
}
