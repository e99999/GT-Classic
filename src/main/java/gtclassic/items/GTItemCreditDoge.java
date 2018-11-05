package gtclassic.items;

import gtclassic.GTClassic;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemCreditDoge extends Item {

    //basic information about this item
	public GTItemCreditDoge() {
        setRegistryName("doge_credit");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName(GTClassic.MODID + ".creditDoge");     // Used for localization (en_US.lang)
        setCreativeTab(GTClassic.creativeTabGT);
    }
    
    //init texture
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
