package gtclassic;

import gtclassic.items.ItemCreditAlk;
import gtclassic.items.ItemCreditDoge;
import gtclassic.items.ItemHammerIron;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class GTItems {
	
	public static final CreativeTabs tabGTClassic = new CreativeTabs("tabGTClassic") {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(GTItems.creditDoge);
		}
		
	};
	
	//not required stored references to items
	@GameRegistry.ObjectHolder("gtclassic:doge_credit")
    public static ItemCreditDoge creditDoge;
	
	//not required stored references to items
	@GameRegistry.ObjectHolder("gtclassic:alk_credit")
	public static ItemCreditAlk creditAlk;
	
	//not required stored references to items
	@GameRegistry.ObjectHolder("gtclassic:iron_hammer")
	public static ItemHammerIron hammerIron;
	
	//inits textures for items
	@SideOnly(Side.CLIENT)
    public static void initModels() {
        creditDoge.initModel();
        creditAlk.initModel();
        hammerIron.initModel();
    }
}
