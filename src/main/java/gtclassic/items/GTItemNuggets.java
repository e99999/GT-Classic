package gtclassic.items;

import gtclassic.GTClassic;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ITexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class GTItemNuggets extends Item implements ITexturedItem {
    public enum GTItemNuggetTypes{
        IRIDIUM(0), SILVER(1), ALUMINUM(2), TITANIUM(3), CHROME(4), ELECTRUM(5), TUNGSTEN(6), LEAD(7), ZINC(8), BRASS(9), STEEL(10), PLATINUM(11), NICKEL(12), INVAR(13), OSMIUM(14), COPPER(15), TIN(16), BRONZE(17);
        private int id;

        GTItemNuggetTypes(int id){
            this.id = id;
        }

        public int getID(){
            return id;
        }
    }

    GTItemNuggetTypes variant;
    public GTItemNuggets(GTItemNuggetTypes variant){
        this.variant = variant;
        setRegistryName(variant.toString().toLowerCase() + "_nugget");
        setUnlocalizedName(GTClassic.MODID + "." + variant.toString().toLowerCase() + "_nugget");
        setCreativeTab(GTClassic.creativeTabGT);
    }

    @Override
    public List<ItemStack> getValidItemVariants() {
        return null;
    }

    @Override
    public TextureAtlasSprite getTexture(ItemStack itemStack) {
        return Ic2Icons.getTextures("gtclassic_nuggets")[variant.getID()];
    }
}
