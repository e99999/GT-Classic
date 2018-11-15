package gtclassic.items.resources;

import gtclassic.GTClassic;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.List;

public class GTItemIngot extends Item implements IStaticTexturedItem {
    public enum GTItemIngotTypes{
        IRIDIUM_ALLOY(0),
        HOT_TUNGSTENSTEEL(1),
        TUNGSTENSTEEL(2),
        IRIDIUM(3),
        ALUMINUM(4),
        TITANIUM(5),
        CHROME(6),
        ELECTRUM(7),
        TUNGSTEN(8),
        LEAD(9),
        ZINC(10),
        BRASS(11),
        STEEL(12),
        PLATINUM(13),
        NICKEL(14),
        INVAR(15),
        OSMIUM(16),
        THORIUM(23),
        PLUTONIUM(24);
        
    	private int id;

        GTItemIngotTypes(int id){
            this.id = id;
        }

        public int getID(){
            return id;
        }
    }

    GTItemIngotTypes variant;
    public GTItemIngot(GTItemIngotTypes variant){
        this.variant = variant;
        setRegistryName(variant.toString().toLowerCase() + "_ingot");
        setUnlocalizedName(GTClassic.MODID + "." + variant.toString().toLowerCase() + "_ingot");
        setCreativeTab(GTClassic.creativeTabGT);
    }

    @Override
    public List<Integer> getValidVariants() {
        return Arrays.asList(0);
    }

    @Override
    public TextureAtlasSprite getTexture(int i) {
        return Ic2Icons.getTextures("gtclassic_materials")[variant.getID()];
    }
}

