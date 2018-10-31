package gtclassic.items;

import gtclassic.ModCore;
import gtclassic.ModItems;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DogeCoin extends Item {

    //basic information about this item
	public DogeCoin() {
        setRegistryName("dogecoin");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName(ModCore.MODID + ".dogecoin");     // Used for localization (en_US.lang)
        setCreativeTab(ModItems.tabGTClassic);
    }
    
    //init texture
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
