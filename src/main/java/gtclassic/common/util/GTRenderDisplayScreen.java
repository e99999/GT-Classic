package gtclassic.common.util;

import java.awt.Color;

import gtclassic.common.tile.GTTileDisplayScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class GTRenderDisplayScreen extends TileEntitySpecialRenderer<GTTileDisplayScreen> {

	@Override
	public void render(GTTileDisplayScreen screen, double x, double y, double z, float partialTicks, int destroyStage,
			float alpha) {
		if (screen != null && screen.shouldDraw) {
			FontRenderer renderer = Minecraft.getMinecraft().fontRenderer;
			GlStateManager.pushMatrix();
			screen.applyRotation((float) x, (float) y, (float) z);
			screen.applySize(2.5F, 4D, 4D, -0.001D);
			int bright = 0xF0;
			int brightX = bright % 65536;
			int brightY = bright / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, brightX, brightY);
			int i = 0;
			for (String text : screen.information.getWrapperList()) {
				i++;
				if (i > 8) {
					break;
				}
				renderer.drawString(text, (int) x + 10, (int) (y - 2) + (10 * i), Color.CYAN.getRGB());
			}
			GlStateManager.popMatrix();
		}
	}
}
