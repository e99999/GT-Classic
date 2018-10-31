package gtclassic;

import gtclassic.items.DogeCoin;
import gtclassic.items.IronMultiTool;
import gtclassic.toxicdimension.items.AlkCoin;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class ModItems {
	
	public static final CreativeTabs tabGTClassic = new CreativeTabs("tabGTClassic") {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModItems.dogeCoin);
		}
		
	};
	
	//not required stored references to items
	@GameRegistry.ObjectHolder("gtclassic:dogecoin")
    public static DogeCoin dogeCoin;
	
	//not required stored references to items
	@GameRegistry.ObjectHolder("gtclassic:alkcoin")
	public static AlkCoin alkCoin;
	
	//not required stored references to items
	@GameRegistry.ObjectHolder("gtclassic:ironmultitool")
	public static IronMultiTool ironMultiTool;
	
	//inits textures for items
	@SideOnly(Side.CLIENT)
    public static void initModels() {
        dogeCoin.initModel();
        alkCoin.initModel();
        ironMultiTool.initModel();
    }
}
