package gtclassic.item.materials;

import gtclassic.GTMod;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.List;

public class GTItemPlate extends Item implements IStaticTexturedItem {
    public enum GTItemPlateTypes{
    	SILICON(21),
    	IRON(27),
        GOLD(28),
        REFINEDIRON(29),
        TIN(30),
        COPPER(31),
        SILVER(32),
        BRONZE(33),
        ELECTRUM(34),
        NICKEL(35),
        INVAR(36),
        LEAD(37),
        ALUMINIUM(38),
        CHROME(39),
        TITANIUM(40),
        STEEL(41),
        PLATINUM(42),
        TUNGSTEN(43),
        BRASS(44),
        ZINC(45),
        TUNGSTENSTEEL(46),
        OSMIUM(47),
        MAGNALIUM(48);
        
    	private int id;

        GTItemPlateTypes(int id){
            this.id = id;
        }

        public int getID(){
            return id;
        }
    }

    GTItemPlateTypes variant;
    public GTItemPlate(GTItemPlateTypes variant){
        this.variant = variant;
        setRegistryName(variant.toString().toLowerCase() + "_plate");
        setUnlocalizedName(GTMod.MODID + "." + variant.toString().toLowerCase() + "_plate");
        setCreativeTab(GTMod.creativeTabGT);
    }

    @Override
    public List<Integer> getValidVariants() {
        return Arrays.asList(0);
    }

    @Override
    public TextureAtlasSprite getTexture(int i) {
        return Ic2Icons.getTextures(GTMod.MODID + "_materials")[variant.getID()];
    }
}

