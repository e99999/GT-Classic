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
			if (screen.facing == 3 || screen.facing == 4) {
				screen.applyRotation((float) x, (float) y, (float) z + 0.001F);
			} else {
				screen.applyRotation((float) x + 0.001F, (float) y, (float) z);
			}
			int bright = 0xF0;
			int brightX = bright % 65536;
			int brightY = bright / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, brightX, brightY);
			GlStateManager.scale(0.01F, 0.01F, 2.5F);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 0.0F);
			// Here i would grab the information from the tile and iterate the list to make
			// the text here
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
