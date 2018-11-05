package gtclassic.blocks.resources;

import gtclassic.GTClassic;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ITexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class GTBlockMetals extends Block implements ITexturedBlock {
    public enum GTBlockMetalsVariants{
        RUBY(0), SAPPHIRE(1), ALUMINUM(2), TITANIUM(3), CHROME(4), STEEL(5), BRASS(6), LEAD(7), ELECTRUM(8), ZINC(9), OLIVINE(10), GREEN_SAPPHIRE(11), PLATINUM(12), TUNGSTEN(13), NICKEL(14), TUNGSTENSTEEL(15), IRIDIUM_REINFORCED_TUNGSTENSTEEL(16), INVAR(17), OSMIUM(18), IRIDIUM(19);
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
        super(Material.ROCK);
        this.variant = variant;
        setRegistryName(variant.toString().toLowerCase() + "_block");
        setUnlocalizedName(GTClassic.MODID + "." + variant.toString().toLowerCase() + "_block");
        setCreativeTab(GTClassic.creativeTabGT);
        setHardness(5.0F);
        setSoundType(SoundType.STONE);
        setHarvestLevel("pickaxe", 1);
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
