package gtclassic.toxicdimension.blocks;

import gtclassic.GTClassic;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockToxicPortalFrame extends Block {

    //basic information about this block
	public GTBlockToxicPortalFrame() {
        super(Material.IRON);
        setRegistryName("toxic_portal_frame");
        setUnlocalizedName(GTClassic.MODID + ".toxicPortalFrame");
        setCreativeTab(GTClassic.creativeTabGT);
        setHardness(3.0F);
        setResistance(40.0F);
        setHarvestLevel("pickaxe", 2);
        this.setLightLevel(0.5F);  
    }
    
    //initializes the block texture as an item texture
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    
    }
}
