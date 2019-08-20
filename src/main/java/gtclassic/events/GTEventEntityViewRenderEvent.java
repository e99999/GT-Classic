package gtclassic.events;

import gtclassic.GTConfig;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTEventEntityViewRenderEvent {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRenderFog(EntityViewRenderEvent.FogDensity event) {
		if (GTConfig.clearerWater) {
			if (event.getState().getMaterial() == Material.WATER) {
				GlStateManager.setFog(GlStateManager.FogMode.EXP);
				event.setDensity(0.02F);
				event.setCanceled(true);
			}
		}
	}
}
