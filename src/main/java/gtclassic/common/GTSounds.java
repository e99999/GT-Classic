package gtclassic.common;

import gtclassic.GTMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GTSounds {

	/** Static references just used inside ic2c client side only **/
	public static final ResourceLocation SONAR = new ResourceLocation(GTMod.MODID, "sounds/sonar.ogg");
	public static final ResourceLocation CLOAK = new ResourceLocation(GTMod.MODID, "sounds/cloak.ogg");
	/**
	 * Springs need to be heard by all players so its being done with normal forge
	 * loading
	 **/
	public static final SoundEvent SPRING = new SoundEvent(new ResourceLocation(GTMod.MODID, "spring")).setRegistryName("spring");

	@SubscribeEvent
	public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
		event.getRegistry().register(SPRING);
	}
}
