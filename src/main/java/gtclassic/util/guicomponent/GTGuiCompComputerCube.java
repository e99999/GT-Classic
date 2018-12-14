package gtclassic.util.guicomponent;

import java.util.Arrays;
import java.util.List;

import gtclassic.block.tileentity.GTTileEntityComputerCube;
import gtclassic.block.tileentity.GTTileEntityFusionComputer;
import gtclassic.util.GTBlocks;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.buttons.IconButton;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.registry.Ic2GuiComp;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompComputerCube extends GuiComponent {

	GTTileEntityComputerCube block;
	int white = 16777215;
	int grey = 4210752;
	int red = 15599112;
	int green = 9567352;

	public GTGuiCompComputerCube(GTTileEntityComputerCube tile) {
		super(Ic2GuiComp.nullBox);
		this.block = tile;
	}

	@Override
	public List<ActionRequest> getNeededRequests() {
		return Arrays.asList(ActionRequest.GuiInit, ActionRequest.ButtonNotify, ActionRequest.GuiTick,
				ActionRequest.FrontgroundDraw, ActionRequest.BackgroundDraw);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiInit(GuiIC2 gui) {
		gui.registerButton((new IconButton(0, gui.getXOffset() + 147, gui.getYOffset() + 6, 20, 20)).setItemStack(new ItemStack(GTBlocks.computerCube))
				.addText("Mode"));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onButtonClick(GuiIC2 gui, GuiButton button) {
		this.block.advanceIndex();
	}

}
