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
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;


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
        setResistance(10.0F);
        setSoundType(SoundType.STONE);
        //setHarvestLevel("pickaxe", 2);
    }

    public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState blockstate, int fortune){
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        
        //Nether Ores
        if (this == GTBlocks.cinnabarOre) {
        drops.add(new ItemStack(GTItems.dustCinnabar, 2));
        drops.add(new ItemStack(Items.REDSTONE, 1));
        }
        
        if (this == GTBlocks.pyriteOre) {
        drops.add(new ItemStack(GTItems.dustPyrite, 2));
        }
        
        if (this == GTBlocks.sphaleriteOre) {
        drops.add(new ItemStack(GTItems.dustSphalerite, 1));
        	if(RANDOM.nextFloat()<0.25f) {
        		drops.add(new ItemStack(GTItems.dustZincGT, 1));
        	}
        	if(RANDOM.nextFloat()<0.125f) {
        		drops.add(new ItemStack(GTItems.yellowGarnet, 1));
        	}
        }
        
        //End Ores
        if (this == GTBlocks.tungstateOre) {
        drops.add(new ItemStack(GTBlocks.tungstateOre, 1));
        }
        
        if (this == GTBlocks.sheldoniteOre) {
        drops.add(new ItemStack(GTBlocks.sheldoniteOre, 1));
        }
        
        if (this == GTBlocks.sodaliteOre) {
        drops.add(new ItemStack(GTItems.dustSodalite, 6));
        }
        
        if (this == GTBlocks.olivineOre) {
        drops.add(new ItemStack(GTItems.olivine, 1));
        }
        
        
        //Default Ores
        if (this == GTBlocks.galenaOre) {
        drops.add(new ItemStack(GTBlocks.galenaOre, 1));
        }
        
        if (this == GTBlocks.iridiumOreGT) {
        drops.add(new ItemStack(GTBlocks.iridiumOreGT, 1));
        }
        
        if (this == GTBlocks.rubyOre) {
        	if(RANDOM.nextFloat()>0.10f) {
        		drops.add(new ItemStack(GTItems.rubyGT, 1));
        		}
        	else {
        		drops.add(new ItemStack(GTItems.redGarnet, 1));
        	}
        }
        
        if (this == GTBlocks.sapphireOre) {//iron and add xp
        	if(RANDOM.nextFloat()>0.10f) {
        		drops.add(new ItemStack(GTItems.sapphireGT, 1));
        		}
        	else {
        		drops.add(new ItemStack(GTItems.greenSapphire, 1));
        	}
        }
        
        if (this == GTBlocks.bauxiteOre) {//stone
            drops.add(new ItemStack(GTBlocks.bauxiteOre, 1));
            }
        
        return drops;
    }
    
    
    @Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune){
            if (this == GTBlocks.sphaleriteOre){
                return 1;
            }
            if (this == GTBlocks.iridiumOreGT){
                return 3;
            }
            if (this == GTBlocks.rubyOre){
                return 2;
            }
            if (this == GTBlocks.sapphireOre){
                return 2;
            }
            return 0;
    }
    
    
    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
        return !(entity instanceof EntityDragon);
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
