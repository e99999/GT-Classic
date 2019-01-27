package gtclassic.util.gui;

import java.util.Arrays;
import java.util.List;

import gtclassic.tileentity.GTTileEntityDigitalChest;
import gtclassic.util.GTValues;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.registry.Ic2GuiComp;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompQuantumChest extends GuiComponent {

	GTTileEntityDigitalChest block;

	public GTGuiCompQuantumChest(GTTileEntityDigitalChest tile) {
		super(Ic2GuiComp.nullBox);
		this.block = tile;
	}

	@Override
	public List<ActionRequest> getNeededRequests() {
		return Arrays.asList(ActionRequest.FrontgroundDraw);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawFrontground(GuiIC2 gui, int mouseX, int mouseY) {
		gui.drawString("Items:", 12, 20, GTValues.white);

		int stored = this.block.getQuantumCount();
		gui.drawString("" + stored, 12, 30, GTValues.white);
	}

}
