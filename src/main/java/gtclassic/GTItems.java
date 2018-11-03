package gtclassic;

import gtclassic.items.ItemCreditAlk;
import gtclassic.items.ItemCreditDoge;
import gtclassic.items.ItemHammerIron;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@GameRegistry.ObjectHolder(GTMod.MODID)
public class GTItems {
	
	public static final CreativeTabs tabGTClassic = new CreativeTabs("tabGTClassic") {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(GTItems.creditDoge);
		}
		
	};

    public static final ItemCreditDoge creditDoge = new ItemCreditDoge();
	public static final ItemCreditAlk creditAlk = new ItemCreditAlk();
	public static final ItemHammerIron hammerIron = new ItemHammerIron();
	@Mod.EventBusSubscriber(modid = GTMod.MODID)
	public static class RegistrationHandler {
		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {

			//items
			GTMod.logger.info("Registering Items");
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
