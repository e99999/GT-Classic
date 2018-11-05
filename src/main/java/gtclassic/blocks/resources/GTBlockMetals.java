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
        RUBY(0), SAPPHIRE(16), ALUMINUM(32), TITANIUM(48), CHROME(64), STEEL(80), BRASS(96), LEAD(112), ELECTRUM(128), ZINC(144), OLIVINE(160), GREEN_SAPPHIRE(176), PLATINUM(192), TUNGSTEN(208), NICKEL(224), TUNGSTENSTEEL(240), IRIDIUM_REINFORCED_TUNGSTENSTEEL(1), INVAR(17), OSMIUM(33), IRIDIUM(49);
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
//        for (int i = 0; i < 20; i++){
//            return Ic2Icons.getTextures("gtclassic_blocks")[16 * i];
//        }
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

//    @SideOnly(Side.CLIENT)
//    public void initModel() {
//
//        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
//
//    }
}
