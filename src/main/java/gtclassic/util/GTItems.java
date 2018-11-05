package gtclassic.util;

import gtclassic.GTClassic;
import gtclassic.items.GTItemCreditAlk;
import gtclassic.items.GTItemCreditDoge;
import gtclassic.items.GTItemHammerIron;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@GameRegistry.ObjectHolder(GTClassic.MODID)
public class GTItems {

    public static final GTItemCreditDoge creditDoge = new GTItemCreditDoge();
	public static final GTItemCreditAlk creditAlk = new GTItemCreditAlk();
	public static final GTItemHammerIron hammerIron = new GTItemHammerIron();
	@Mod.EventBusSubscriber(modid = GTClassic.MODID)
	public static class RegistrationHandler {
		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {

			//items
			GTClassic.logger.info("Registering Items");
			event.getRegistry().registerAll(creditDoge, creditAlk, hammerIron);
		}
	}
	//inits textures for items
	@SideOnly(Side.CLIENT)
    public static void initModels() {
        creditDoge.initModel();
        creditAlk.initModel();
        hammerIron.initModel();
    }
}
