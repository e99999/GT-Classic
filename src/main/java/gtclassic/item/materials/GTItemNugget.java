package gtclassic.item.materials;

import gtclassic.GTMod;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.List;

public class GTItemNugget extends Item implements IStaticTexturedItem {
    public enum GTItemNuggetTypes{
        IRIDIUM(0),
        SILVER(1),
        ALUMINIUM(2),
        TITANIUM(3),
        CHROME(4),
        ELECTRUM(5),
        TUNGSTEN(6),
        LEAD(7),
        ZINC(8),
        BRASS(9),
        STEEL(10),
        PLATINUM(11),
        NICKEL(12),
        INVAR(13),
        OSMIUM(14),
        COPPER(15),
        TIN(16),
        BRONZE(17);
        
    	private int id;

        GTItemNuggetTypes(int id){
            this.id = id;
        }

        public int getID(){
            return id;
        }
    }

    GTItemNuggetTypes variant;
    public GTItemNugget(GTItemNuggetTypes variant){
        this.variant = variant;
        setRegistryName(variant.toString().toLowerCase() + "_nugget");
        setUnlocalizedName(GTMod.MODID + "." + variant.toString().toLowerCase() + "_nugget");
        setCreativeTab(GTMod.creativeTabGT);
    }

    @Override
    public List<Integer> getValidVariants() {
        return Arrays.asList(0);
    }

    @Override
    public TextureAtlasSprite getTexture(int i) {
        return Ic2Icons.getTextures(GTMod.MODID + "_nuggets")[variant.getID()];
    }
}