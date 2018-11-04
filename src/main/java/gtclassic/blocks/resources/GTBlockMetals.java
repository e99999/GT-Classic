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
        RUBY, SAPPHIRE, ALUMINUM, TITANIUM, CHROME, STEEL, BRASS, LEAD, ELECTRUM, ZINC, OLIVINE, GREEN_SAPPHIRE, PLATINUM, TUNGSTEN, NICKEL, TUNGSTENSTEEL, IRIDIUM_REINFORCED_TUNGSTENSTEEL, INVAR, OSMIUM, IRIDIUM
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
//        for (int i = 0; i < 20; i++){
//            return Ic2Icons.getTextures("gtclassic_blocks")[16 * i];
//        }
        return Ic2Icons.getTextures("gtclassic_blocks")[16];
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

//    @SideOnly(Side.CLIENT)
//    public void initModel() {
//
//        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
//
//    }
}
