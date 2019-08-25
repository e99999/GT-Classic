package gtclassic.gui;

import java.util.Arrays;
import java.util.List;

import gtclassic.tile.GTTileBaseMachine;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.registry.Ic2GuiComp;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompMachinePower extends GuiComponent {

	GTTileBaseMachine block;
	int x;
	int y;

	public GTGuiCompMachinePower(GTTileBaseMachine tile) {
		super(Ic2GuiComp.nullBox);
		this.block = tile;
		this.x = 80;
		this.y = 40;
	}
	
	public GTGuiCompMachinePower(GTTileBaseMachine tile, int x, int y) {
		super(Ic2GuiComp.nullBox);
		this.block = tile;
		this.x = x;
		this.y = y;
	}

	@Override
	public List<ActionRequest> getNeededRequests() {
		return Arrays.asList(ActionRequest.BackgroundDraw);
	}


	@Override
	@SideOnly(Side.CLIENT)
	public void drawBackground(GuiIC2 gui, int mouseX, int mouseY, float particalTicks) {
		if (this.block.energy < this.block.energyConsume) {
			gui.drawTexturedModalRect(gui.getXOffset() + this.x, gui.getYOffset() + this.y, 176, 54, 18, 18);
		}
	}
}
