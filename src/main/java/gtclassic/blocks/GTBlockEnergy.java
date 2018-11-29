package gtclassic.blocks;

import gtclassic.GTClassic;
import gtclassic.tileentity.GTTileEntityHESU;
import gtclassic.tileentity.GTTileEntitySuperCondensator;
import gtclassic.util.GTBlocks;
import ic2.core.block.base.BlockMultiID;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GTBlockEnergy extends BlockMultiID {
    public enum GTBlockEnergyVariants
    {
        //Generators
    	LIGHTNINGROD,
    	FUSIONREACTOR, 

        //Transformer
        SUPERCONDENSATOR,

    	//Storage
    	LESU,
        IESU,
        HESU;
    }

    GTBlockEnergyVariants variant;
    public GTBlockEnergy(GTBlockEnergyVariants variant)
    {
        super(Material.IRON);
        this.variant = variant;
        setRegistryName(variant.toString().toLowerCase());
        setUnlocalizedName(GTClassic.MODID + "." + variant.toString().toLowerCase());
        setCreativeTab(GTClassic.creativeTabGT);
        setHardness(4.0F);
        setResistance(20.0F);
        setSoundType(SoundType.METAL);
        setHarvestLevel("pickaxe", 2);
    }
    
    @Override
    public List<Integer> getValidMetas() 
	{
        return Arrays.asList(0);
    }

    @Override
    public TileEntityBlock createNewTileEntity(World worldIn, int meta)
    {
        if (this == GTBlocks.HESU){
            return new GTTileEntityHESU();
        }else if (this == GTBlocks.superCondensator){
            return new GTTileEntitySuperCondensator();
        }else{
            return new TileEntityBlock();
        }

    }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
    	if (this == GTBlocks.IESU) 
   	 	{
    		for (int i = 0; i < 3; ++i)
    		{
            int j = rand.nextInt(2) * 2 - 1;
            int k = rand.nextInt(2) * 2 - 1;
            double d0 = (double)pos.getX() + 0.5D + 0.25D * (double)j;
            double d1 = (double)((float)pos.getY() + rand.nextFloat());
            double d2 = (double)pos.getZ() + 0.5D + 0.25D * (double)k;
            double d3 = (double)(rand.nextFloat() * (float)j);
            double d4 = ((double)rand.nextFloat() - 0.5D) * 0.125D;
            double d5 = (double)(rand.nextFloat() * (float)k);
            worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
    		}
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite[] getIconSheet(int meta)
    {
    	//Generators
    	if (this == GTBlocks.lightningRod) 
   	 	{
    		return Ic2Icons.getTextures("gtclassic_lightningrod");
   	 	}
   	 
   	 	else if (this == GTBlocks.fusionReactor)
   	 	{
   	 		return Ic2Icons.getTextures("gtclassic_fusionreactor");
   	 	}

   	 	else if (this == GTBlocks.superCondensator){
            return Ic2Icons.getTextures("gtclassic_supercondensator");
        }
   	 
   	 	//Storage
   	 	else if (this == GTBlocks.LESU)
   	 	{
   	 		return Ic2Icons.getTextures("gtclassic_lapotronicenergysu");
   	 	}
   	 
   	 	else if (this == GTBlocks.IESU)
   	 	{
   	 		return Ic2Icons.getTextures("gtclassic_interdimensionalenergysu");
   	 	}
   	 	
   	 	else if (this == GTBlocks.HESU)
	 	{
   	 		return Ic2Icons.getTextures("gtclassic_hugeenergysu");
	 	}
    	else{
            return Ic2Icons.getTextures("gtclassic_builder");
        }

    }
    
    @Override
    public int getMaxSheetSize(int meta)
    {
        return 1;
    }

    @Override
    public List<IBlockState> getValidStateList()
    {
        IBlockState def = getDefaultState();
        List<IBlockState> states = new ArrayList<>();
        for(EnumFacing side : EnumFacing.VALUES)
        {
        	states.add(def.withProperty(getMetadataProperty(), 0).withProperty(allFacings, side).withProperty(active, false));
        	states.add(def.withProperty(getMetadataProperty(), 0).withProperty(allFacings, side).withProperty(active, true));
        }
        return states;
    }

    @Override
    public List<IBlockState> getValidStates()
    {
        return getBlockState().getValidStates();
    }

}