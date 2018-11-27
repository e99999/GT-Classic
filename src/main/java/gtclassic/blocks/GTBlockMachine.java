package gtclassic.blocks;

import gtclassic.GTClassic;
import gtclassic.tileentity.GTTileEntityComputerCube;
import gtclassic.tileentity.GTTileEntityIndustrialCentrifuge;
import gtclassic.tileentity.GTTileEntityLargeBuffer;
import gtclassic.tileentity.GTTileEntitySmallBuffer;
import gtclassic.tileentity.GTTileEntityTranslocator;
import gtclassic.util.GTBlocks;
import ic2.core.block.base.BlockMultiID;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GTBlockMachine extends BlockMultiID {
    public enum GTBlockMachineVariants
    {
        SMALLBUFFER,
    	LARGEBUFFER, 
        TRANSLOCATOR,
        COMPUTERCUBE,
        ELECTRICCRAFTER,
        INDUSTRIALCENTRIFUGE;
    }

    GTBlockMachineVariants variant;
    public GTBlockMachine(GTBlockMachineVariants variant)
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
    	 if (this == GTBlocks.smallBuffer) 
    	 {
    		 return new GTTileEntitySmallBuffer();
    	 }
    	 
    	 if (this == GTBlocks.largeBuffer) 
    	 {
    		 return new GTTileEntityLargeBuffer();
    	 }
    	 
    	 if (this == GTBlocks.translocator) 
    	 {
    		 return new GTTileEntityTranslocator();
    	 }
    	 
    	 if (this == GTBlocks.computerCube) 
    	 {
    		 return new GTTileEntityComputerCube();
    	 }
    	 
    	 if (this == GTBlocks.computerCube) 
    	 {
    		 return new GTTileEntityComputerCube();
    	 }
    	 
    	 if (this == GTBlocks.industrialCentrifuge) 
    	 {
    		 return new GTTileEntityIndustrialCentrifuge();
    	 }
    	 
    	 return new TileEntityBlock();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite[] getIconSheet(int meta)
    {
    	if (this == GTBlocks.smallBuffer) 
   	 	{
    		return Ic2Icons.getTextures("gtclassic_smallbuffer");
   	 	}
   	 
   	 	if (this == GTBlocks.largeBuffer) 
   	 	{
   	 		return Ic2Icons.getTextures("gtclassic_largebuffer");
   	 	}
   	 
   	 	if (this == GTBlocks.translocator) 
   	 	{
   	 		return Ic2Icons.getTextures("gtclassic_translocator");
   	 	}
   	 
   	 	if (this == GTBlocks.computerCube) 
   	 	{
   	 		return Ic2Icons.getTextures("gtclassic_computercube");
   	 	}
   	 	
   	 	if (this == GTBlocks.industrialCentrifuge) 
   	 	{
   	 		return Ic2Icons.getTextures("gtclassic_industrialcentrifuge");
   	 	}
    	
    	return Ic2Icons.getTextures("gtclassic_builder");
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