package gtclassic.blocks.resources;

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

public class GTBlockMetals extends Block {
    public enum GTBlockMetalsVariants{
        RUBY, SAPPHIRE, ALUMINUM, TITANIUM, CHROME, STEEL, BRASS, LEAD, ELECTRUM, ZINC, OLIVINE, GREEN_SAPPHIRE, PLATINUM, TUNGSTEN, NICKEL, TUNGSTENSTEEL, IRIDIUM_REINFORCED_TUNGSTENSTEEL, INVAR, OSMIUM, IRIDIUM
    }

    GTBlockMetalsVariants variant;
    public GTBlockMetals(GTBlockMetalsVariants variant){
        super(Material.ROCK);
        this.variant = variant;
        setRegistryName(variant.toString().toLowerCase() + "_block");
        setUnlocalizedName(GTMod.MODID + "." + variant.toString().toLowerCase() + "_block");
        setCreativeTab(GTItems.tabGTClassic);
        setHardness(5.0F);
        setSoundType(SoundType.STONE);
        setHarvestLevel("pickaxe", 1);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));

    }
}
