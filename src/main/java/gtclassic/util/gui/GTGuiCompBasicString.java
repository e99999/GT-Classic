package gtclassic.util.gui;

import java.util.Arrays;
import java.util.List;

import gtclassic.util.GTValues;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.registry.Ic2GuiComp;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompBasicString extends GuiComponent {

	private int posX;
	private int posY;
	private String title;

	public GTGuiCompBasicString(String title, Integer posX, Integer posY) {
		super(Ic2GuiComp.nullBox);
		this.posX = posX;
		this.posY = posY;
		this.title = title;
	}

	@Override
	public List<ActionRequest> getNeededRequests() {
		return Arrays.asList(ActionRequest.FrontgroundDraw);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawFrontground(GuiIC2 gui, int mouseX, int mouseY) {
		gui.drawString(this.title, this.posX, this.posY, GTValues.grey);
	}

}
