package gtclassic.util;

import gtclassic.GTClassic;
import gtclassic.items.ItemCreditAlk;
import gtclassic.items.ItemCreditDoge;
import gtclassic.items.ItemHammerIron;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@GameRegistry.ObjectHolder(GTClassic.MODID)
public class GTItems {

    public static final ItemCreditDoge creditDoge = new ItemCreditDoge();
	public static final ItemCreditAlk creditAlk = new ItemCreditAlk();
	public static final ItemHammerIron hammerIron = new ItemHammerIron();
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
