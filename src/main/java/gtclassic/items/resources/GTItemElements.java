package gtclassic.items.resources;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTClassic;
import gtclassic.items.resources.GTItemDust.GTItemDustTypes;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemElements extends Item implements IStaticTexturedItem {
    public enum GTItemCellTypes{
        HYDROGEN(0), 
        DEUTERIUM(1), 
        TRITIUM(2), 
        HELIUM(3), 
        TUNGSTEN(4), 
        LITHIUM(5), 
        HELIUM3(6), 
        SILICON(7), 
        CARBON(8), 
        METHANE(9), 
        BERILIUM(10), 
        CALCIUM(11), 
        SODIUM(12), 
        CHLORINE(13), 
        POTASSIUM(14), 
        NITROGEN(15),
        OXYGEN(5);
        
    	private int id;

        GTItemCellTypes(int id){
            this.id = id;
        }

        public int getID(){
            return id;
        }
    }

    GTItemCellTypes variant;
    public GTItemElements(GTItemCellTypes variant){
        this.variant = variant;
        setRegistryName(variant.toString().toLowerCase());
        setUnlocalizedName(GTClassic.MODID + "." + variant.toString().toLowerCase());
        setCreativeTab(GTClassic.creativeTabGT);
    }


    @Override
    public List<Integer> getValidVariants() {
        return Arrays.asList(0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture(int i) {
        return Ic2Icons.getTextures("gtclassic_items")[variant.getID()];
    }
}

