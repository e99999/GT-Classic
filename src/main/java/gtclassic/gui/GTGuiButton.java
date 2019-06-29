package gtclassic.gui;

import ic2.core.inventory.gui.buttons.IC2Button;
import net.minecraft.client.Minecraft;

public class GTGuiButton extends IC2Button {

	public GTGuiButton(int buttonId, int x, int y, int widthIn, int heightIn) {
		super(buttonId, x, y, widthIn, heightIn, "");
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
	}
}
