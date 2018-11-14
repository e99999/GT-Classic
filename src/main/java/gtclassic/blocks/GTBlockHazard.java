package gtclassic.blocks;

import java.util.List;

import gtclassic.GTClassic;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ITexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockHazard extends Block implements ITexturedBlock {
    public enum GTBlockHazardVariants{
    	METAL(108),
        FIRE(109),
        RADIOACTIVE(110),
        CAUTION(111);
        
    	private int id;

    	GTBlockHazardVariants(int id){
            this.id = id;
        }

        public int getID(){
            return id;
        }
    }

    GTBlockHazardVariants variant;
    public GTBlockHazard(GTBlockHazardVariants variant){
        super(Material.IRON);
        this.variant = variant;
        setRegistryName(variant.toString().toLowerCase() + "_hazardblock");
        setUnlocalizedName(GTClassic.MODID + "." + variant.toString().toLowerCase() + "_hazardblock");
        setCreativeTab(GTClassic.creativeTabGT);
        setHardness(90.0F);
        setResistance(180.0F);
        setSoundType(SoundType.METAL);
        setHarvestLevel("pickaxe", 2);
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
    @Deprecated
    public boolean canEntitySpawn(IBlockState state, Entity entityIn){
        return false;
    }
    
    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
        return !(entity instanceof EntityWither);
    }
    
    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
    	tooltip.add(I18n.format("tooltip."+ GTClassic.MODID +".nomobs"));
    	tooltip.add(I18n.format("tooltip."+ GTClassic.MODID +".witherproof"));
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

