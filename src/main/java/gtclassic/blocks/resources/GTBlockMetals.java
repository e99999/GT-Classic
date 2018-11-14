package gtclassic.blocks.resources;

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
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class GTBlockMetals extends Block implements ITexturedBlock {
    public enum GTBlockMetalsVariants{
        RUBY(0), 
        SAPPHIRE(1),
        ALUMINUM(2), 
        TITANIUM(3), 
        CHROME(4), 
        STEEL(5), 
        BRASS(6), 
        LEAD(7), 
        ELECTRUM(8), 
        ZINC(9), 
        OLIVINE(10), 
        GREEN_SAPPHIRE(11), 
        PLATINUM(12), 
        TUNGSTEN(13), 
        NICKEL(14), 
        TUNGSTENSTEEL(15), 
        IRIDIUM_REINFORCED_TUNGSTENSTEEL(16), 
        INVAR(17), 
        OSMIUM(18), 
        IRIDIUM(19);
        
    	private int id;

        GTBlockMetalsVariants(int id){
            this.id = id;
        }

        public int getID(){
            return id;
        }
    }

    GTBlockMetalsVariants variant;
    public GTBlockMetals(GTBlockMetalsVariants variant){
        super(Material.IRON);
        this.variant = variant;
        setRegistryName(variant.toString().toLowerCase() + "_block");
        setUnlocalizedName(GTClassic.MODID + "." + variant.toString().toLowerCase() + "_block");
        setCreativeTab(GTClassic.creativeTabGT);
        setHardness(5.0F);
        setResistance(15.0F);
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
    
    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
    	tooltip.add(I18n.format("tooltip."+ GTClassic.MODID +".nomobs"));
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
