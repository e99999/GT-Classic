package gtclassic.common.event;

import org.lwjgl.opengl.GL11;

import gtclassic.GTMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GTEventCapeRenderer extends RenderPlayer {

	private final static ResourceLocation mCape = new ResourceLocation(GTMod.MODID, "textures/GregTechCape.png");

	public GTEventCapeRenderer() {
		super(Minecraft.getMinecraft().getRenderManager());
	}
	// Remember to actually register this before you try to use it

	@SubscribeEvent
	public void receiveRenderSpecialsEvent(@SuppressWarnings("deprecation") RenderPlayerEvent.Specials.Pre aEvent) {
		AbstractClientPlayer aPlayer = (AbstractClientPlayer) aEvent.getEntityPlayer();
		float aPartialTicks = aEvent.getPartialRenderTick();
		if (aPlayer.isInvisible()) {
			return;
		}
		// check if the player has disabled capes client side as well
		try {
			ResourceLocation tResource = mCape;
//            if (aPlayer.getDisplayName().toString().equalsIgnoreCase("Friedi4321")) {
//                tResource = this.mCapes[0];
//            }
//            if (aPlayer.getDisplayName().toString().equalsIgnoreCase("Mr_Brain")) {
//                tResource = this.mCapes[2];
//            }
//            if (aPlayer.getDisplayName().toString().equalsIgnoreCase("GregoriusT")) {
//                tResource = this.mCapes[3];
//            }
			if (tResource != null) {
				bindTexture(tResource);
				GL11.glPushMatrix();
				GL11.glTranslatef(0.0F, 0.0F, 0.125F);
				double d0 = aPlayer.prevChasingPosX + (aPlayer.chasingPosX - aPlayer.prevChasingPosX) * aPartialTicks
						- (aPlayer.prevPosX + (aPlayer.posX - aPlayer.prevPosX) * aPartialTicks);
				double d1 = aPlayer.prevChasingPosY + (aPlayer.chasingPosY - aPlayer.prevChasingPosY) * aPartialTicks
						- (aPlayer.prevPosY + (aPlayer.posY - aPlayer.prevPosY) * aPartialTicks);
				double d2 = aPlayer.prevChasingPosZ + (aPlayer.chasingPosZ - aPlayer.prevChasingPosZ) * aPartialTicks
						- (aPlayer.prevPosZ + (aPlayer.posZ - aPlayer.prevPosZ) * aPartialTicks);
				float f6 = aPlayer.prevRenderYawOffset
						+ (aPlayer.renderYawOffset - aPlayer.prevRenderYawOffset) * aPartialTicks;
				double d3 = MathHelper.sin(f6 * 3.141593F / 180.0F);
				double d4 = -MathHelper.cos(f6 * 3.141593F / 180.0F);
				float f7 = (float) d1 * 10.0F;
				float f8 = (float) (d0 * d3 + d2 * d4) * 100.0F;
				float f9 = (float) (d0 * d4 - d2 * d3) * 100.0F;
				if (f7 < -6.0F) {
					f7 = -6.0F;
				}
				if (f7 > 32.0F) {
					f7 = 32.0F;
				}
				if (f8 < 0.0F) {
					f8 = 0.0F;
				}
				float f10 = aPlayer.prevCameraYaw + (aPlayer.cameraYaw - aPlayer.prevCameraYaw) * aPartialTicks;
				f7 += MathHelper.sin((aPlayer.prevDistanceWalkedModified
						+ (aPlayer.distanceWalkedModified - aPlayer.prevDistanceWalkedModified) * aPartialTicks) * 6.0F)
						* 32.0F * f10;
				if (aPlayer.isSneaking()) {
					f7 += 25.0F;
				}
				GL11.glRotatef(6.0F + f8 / 2.0F + f7, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(f9 / 2.0F, 0.0F, 0.0F, 1.0F);
				GL11.glRotatef(-f9 / 2.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				aEvent.getRenderer().getMainModel().renderCape(0.0625F);
				GL11.glPopMatrix();
			}
		} catch (Throwable e) {
			// TODO something here
		}
	}
}
