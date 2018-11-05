package gtclassic.toxicdimension.blocks;

import java.util.Random;

import gtclassic.GTClassic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockToxicGrass extends Block {

	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	
	public GTBlockToxicGrass() {
		super(Material.GRASS);
		setRegistryName("toxic_grass");
		setUnlocalizedName(GTClassic.MODID + ".grassToxic");
        setCreativeTab(GTClassic.creativeTabGT);
        setHardness(1.0F);
        setSoundType(SoundType.GROUND);
        setHarvestLevel("shovel", 0);
       
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Blocks.DIRT.getItemDropped(Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT), rand, fortune);
    }
	
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
