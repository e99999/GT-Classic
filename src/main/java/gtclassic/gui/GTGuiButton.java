package gtclassic.gui;

import java.awt.Color;

import ic2.core.inventory.gui.buttons.IC2Button;
import net.minecraft.client.Minecraft;

public class GTGuiButton extends IC2Button {

	public GTGuiButton(int buttonId, int x, int y, int widthIn, int heightIn) {
		super(buttonId, x, y, widthIn, heightIn, "");
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		hovered = ((mouseX >= x) && (mouseY >= y) && (mouseX < x + width) && (mouseY < y + height));
		int i = getHoverState(hovered);
		if (i > 1) {
			drawRect(x, y + 2, x + 18, y + 20, new Color(255, 255, 255, 80).hashCode());
		}
	}
}
