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
        IRIDIUM(3),
        ALUMINUM(4),
        TITANIUM(5),
    	CHROME(6);
        
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

