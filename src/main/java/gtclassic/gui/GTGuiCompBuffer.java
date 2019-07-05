package gtclassic.gui;

import java.util.Arrays;
import java.util.List;

import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileBaseBuffer;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.buttons.ToggleButton;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.registry.Ic2GuiComp;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompBuffer extends GuiComponent {

	public static final ItemStack cable = GTMaterialGen.getIc2(Ic2Items.insulatedCopperCable, 1);
	GTTileBaseBuffer tile;

	public GTGuiCompBuffer(GTTileBaseBuffer tile) {
		super(Ic2GuiComp.nullBox);
		this.tile = tile;
	}

	@Override
	public List<ActionRequest> getNeededRequests() {
		return Arrays.asList(ActionRequest.GuiInit, ActionRequest.ButtonNotify, ActionRequest.GuiTick);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiInit(GuiIC2 gui) {
		gui.registerButton(new ToggleButton(0, gui.getXOffset() + 7, gui.getYOffset()
				+ 60, 20, 20).setIcon(cable).setTextVisiblity(false, false));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiTick(GuiIC2 gui) {
		gui.getCastedButton(0, ToggleButton.class).setState(this.tile.conduct);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onButtonClick(GuiIC2 gui, GuiButton button) {
		this.tile.getNetwork().initiateClientTileEntityEvent(this.tile, 0);
	}
}
