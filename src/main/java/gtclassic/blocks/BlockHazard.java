package gtclassic.blocks;

import gtclassic.GTMod;
import gtclassic.GTItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockHazard extends Block {

    //basic information about this block
	public BlockHazard() {
        super(Material.ROCK);
        setRegistryName("hazard_block");
        setUnlocalizedName(GTMod.MODID + ".blockHazard");
        setHardness(5.0F);
        setResistance(60.0F);
        setHarvestLevel("pickaxe", 2);
        setCreativeTab(GTItems.tabGTClassic);
    }
    
    //initializes the block texture as an item texture
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    
    }
    
    //this makes the block wither proof
    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
        return !(entity instanceof EntityWither);
    }
}