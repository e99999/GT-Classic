package gtclassic.items.resources;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTClassic;
import gtclassic.items.resources.GTItemDust.GTItemDustTypes;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;

public class GTItemCell extends Item implements IStaticTexturedItem {
    public enum GTItemCellTypes{
        H(0), 
        D(1), 
        T(2), 
        HE(3), 
        W(4), 
        LI(5), 
        HE3(6), 
        SI(7), 
        C(8), 
        ME(9), 
        BE(10), 
        CA(11), 
        NA(12), 
        CL(13), 
        K(14), 
        N(15);
        
    	private int id;

        GTItemCellTypes(int id){
            this.id = id;
        }

        public int getID(){
            return id;
        }
    }

    GTItemCellTypes variant;
    public GTItemCell(GTItemCellTypes variant){
        this.variant = variant;
        setRegistryName(variant.toString().toLowerCase() + "_cell");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName(GTClassic.MODID + "." + variant.toString().toLowerCase() + "_cell");     // Used for localization (en_US.lang)
        setCreativeTab(GTClassic.creativeTabGT);
    }


    @Override
    public List<Integer> getValidVariants() {
        return Arrays.asList(0);
    }

    @Override
    public TextureAtlasSprite getTexture(int i) {
        return Ic2Icons.getTextures("gtclassic_items")[variant.getID()];
    }
}

