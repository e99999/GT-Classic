package gtclassic.toxicdimension.blocks;

import gtclassic.GTMod;
import gtclassic.GTItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockToxicPortalFrame extends Block {

    //basic information about this block
	public BlockToxicPortalFrame() {
        super(Material.IRON);
        setRegistryName("toxic_portal_frame");
        setUnlocalizedName(GTMod.MODID + ".toxicPortalFrame");
        setCreativeTab(GTItems.tabGTClassic);
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
