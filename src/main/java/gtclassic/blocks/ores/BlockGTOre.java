package gtclassic.blocks.ores;

import gtclassic.GTItems;
import gtclassic.GTMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGTOre extends Block {
    public enum BlockGTOreVariants{
        GALENA, IRIDIUM, RUBY, SAPPHIRE, BAUXITE, PYRITE, CINNABAR, SPHALERITE, TUNGSTATE, SHELDONITE, OLIVINE, SODALITE;
    }

    BlockGTOreVariants variant;
    public BlockGTOre(BlockGTOreVariants variant) {
        super(Material.ROCK);
        this.variant = variant;
        setRegistryName(variant.toString().toLowerCase() + "_ore");
        setUnlocalizedName(GTMod.MODID + "." + variant.toString().toLowerCase() + "_ore");
        setCreativeTab(GTItems.tabGTClassic);
        setHardness(5.0F);
        setSoundType(SoundType.STONE);
        setHarvestLevel("pickaxe", 1);
    }

    //initializes the block texture as an item texture
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));

    }
}
