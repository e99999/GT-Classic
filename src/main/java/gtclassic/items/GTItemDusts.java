package gtclassic.items;

import gtclassic.GTClassic;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.List;

public class GTItemDusts extends Item implements IStaticTexturedItem {
    public enum GTItemDustTypes{
        ENDERPEARL(0), 
        ENDER_EYE(1), 
        LAZURITE(2), 
        PYRITE(3), 
        CALCITE(4), 
        SODALITE(5), 
        NETHERRACK(6), 
        FLINT(7), 
        SULFUR(8), 
        SALTPETER(9), 
        ENDSTONE(10), 
        CINNABAR(11), 
        MAGANESE(12), 
        MAGNESIUM(13), 
        SPHALERITE(14), 
        WOOD_PULP(15), 
        URANIUM(16), 
        BAUXITE(17), 
        ALUMINUM(18), 
        TITANIUM(19), 
        CHROME(20), 
        ELECTRUM(21), 
        TUNGSTEN(22), 
        LEAD(23), 
        ZINC(24), 
        BRASS(25), 
        STEEL(26), 
        PLATINUM(27), 
        NICKEL(28), 
        INVAR(29), 
        OSMIUM(30), 
        RUBY(31), 
        SAPPHIRE(32), 
        GREEN_SAPPHIRE(33), 
        EMERALD(34), 
        DIAMOND(35), 
        OLIVINE(36), 
        GALENA(37), 
        PHOSPHOR(38), 
        RED_GARNET(39), 
        YELLOW_GARNET(40), 
        PYROPE(41), 
        ALMANDINE(42), 
        SPESSARTINE(43), 
        ANDRADITE(44), 
        GROSSULAR(45), 
        UVAROVITE(46), 
        ASHES(47), 
        DARK_ASHES(48), 
        REDROCK(49), 
        MARBLE(50), 
        BASALT(51), 
        THORIUM(52), 
        PLUTONIUM(53);
        
    	private int id;

        GTItemDustTypes(int id){
            this.id = id;
        }

        public int getID(){
            return id;
        }
    }

    GTItemDustTypes variant;
    public GTItemDusts(GTItemDustTypes variant){
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
