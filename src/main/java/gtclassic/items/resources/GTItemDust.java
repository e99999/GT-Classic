package gtclassic.items.resources;

import gtclassic.GTClassic;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.List;

public class GTItemDust extends Item implements IStaticTexturedItem {
    public enum GTItemDustTypes{
        ENDERPEARL(0), 
        ENDER_EYE(1), 
        LAZURITE(2), 
        PYRITE(3), 
        CALCITE(4), 
        SODALITE(5), 
        NETHERRACK(6), 
        FLINT(7), 
        URANIUM(16), 
        BAUXITE(17), 
        ALUMINUM(18), 
        TITANIUM(19), 
        CHROME(20), 
        RUBY(31), 
        SAPPHIRE(32), 
        GREEN_SAPPHIRE(33), 
        EMERALD(34);
        //DIAMOND(35);
        
        //mabye need to add clay?
        
    	private int id;

        GTItemDustTypes(int id){
            this.id = id;
        }

        public int getID(){
            return id;
        }
    }

    GTItemDustTypes variant;
    public GTItemDust(GTItemDustTypes variant){
        this.variant = variant;
        setRegistryName(variant.toString().toLowerCase() + "_dust");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName(GTClassic.MODID + "." + variant.toString().toLowerCase() + "_dust");     // Used for localization (en_US.lang)
        setCreativeTab(GTClassic.creativeTabGT);
    }


    @Override
    public List<Integer> getValidVariants() {
        return Arrays.asList(0);
    }

    @Override
    public TextureAtlasSprite getTexture(int i) {
        return Ic2Icons.getTextures("gtclassic_dusts")[variant.getID()];
    }
}
