package gtclassic.blocks.resources;

import gtclassic.GTClassic;
import gtclassic.blocks.resources.GTBlockOre.GTBlockOreVariants;
import gtclassic.util.GTBlocks;
import gtclassic.util.GTItems;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ITexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class GTBlockOre extends Block implements ITexturedBlock {
    public enum GTBlockOreVariants{
        GALENA(96), IRIDIUM(97), RUBY(98), SAPPHIRE(99), BAUXITE(100), PYRITE(101), CINNABAR(102), SPHALERITE(103), TUNGSTATE(104), SHELDONITE(105), OLIVINE(106), SODALITE(107);
        private int id;

        GTBlockOreVariants(int id){
            this.id = id;
        }

        public int getID(){
            return id;
        }
    }

    GTBlockOreVariants variant;
    public GTBlockOre(GTBlockOreVariants variant) {
        super(Material.ROCK);
        this.variant = variant;
        setRegistryName(variant.toString().toLowerCase() + "_ore");
        setUnlocalizedName(GTClassic.MODID + "." + variant.toString().toLowerCase() + "_ore");
        setCreativeTab(GTClassic.creativeTabGT);
        setHardness(3.0F);
        setSoundType(SoundType.STONE);
        setHarvestLevel("pickaxe", 1);
    }
    
    //TODO switch to get drops list
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
    	if (this == GTBlocks.pyriteOre)
        {
            return GTItems.dustPyrite;
        }
    	if (this == GTBlocks.cinnabarOre)
        {
            return  GTItems.dustCinnabar;
        }
    	if (this == GTBlocks.sphaleriteOre)
        {
            return GTItems.dustSphalerite;
        }
		
    	return Item.getItemFromBlock(this);
    }
    
    public int quantityDropped(Random random)
    {
    	if (this == (GTBlocks.pyriteOre))
        {
            return 2;
        }
    	if (this == (GTBlocks.cinnabarOre))
        {
            return 2;
        }
    	return 1;
    }
    
    @Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this))
        {
            int i = 0;

            if (this == GTBlocks.sphaleriteOre)
            {
                i = MathHelper.getInt(rand, 0, 2);
            }
            
            return i;
        }
        return 0;
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox(IBlockState iBlockState) {
        return FULL_BLOCK_AABB;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite getTextureFromState(IBlockState iBlockState, EnumFacing enumFacing) {
        return Ic2Icons.getTextures("gtclassic_blocks")[variant.getID()];
    }

    @Override
    public TextureAtlasSprite getParticleTexture(IBlockState state) {
        return this.getTextureFromState(state, EnumFacing.SOUTH);
    }

    @Override
    public List<IBlockState> getValidStates() {
        return this.blockState.getValidStates();
    }

    @Override
    public IBlockState getStateFromStack(ItemStack stack) {
        return this.getStateFromMeta(stack.getMetadata());
    }
}
