package gtclassic.item.materials;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;

public class GTItemDust extends Item implements IStaticTexturedItem {
	
	    public enum GTItemDustTypes{
	        ENDERPEARL(0), 
	        ENDEREYE(1), 
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
	        WOODPULP(15), 
	        URANIUM(16), 
	        BAUXITE(17), 
	        ALUMINIUM(18), 
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
	        GREENSAPPHIRE(33), 
	        EMERALD(34), 
	        DIAMOND(35), 
	        OLIVINE(36), 
	        GALENA(37), 
	        PHOSPHOR(38), 
	        REDGARNET(39), 
	        YELLOWGARNET(40), 
	        PYROPE(41), 
	        ALMANDINE(42), 
	        SPESSARTINE(43), 
	        ANDRADITE(44), 
	        GROSSULAR(45), 
	        UVAROVITE(46), 
	        ASHES(47), 
	        DARKASHES(48), 
	        REDROCK(49), 
	        MARBLE(50), 
	        BASALT(51), 
	        THORIUM(52), 
	        PLUTONIUM(53),
	    	PLASTIC(54);
	        
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
	        setUnlocalizedName(GTMod.MODID + "." + variant.toString().toLowerCase() + "_dust");     // Used for localization (en_US.lang)
	        setCreativeTab(GTMod.creativeTabGT);
	    }


	    @Override
	    public List<Integer> getValidVariants() {
	        return Arrays.asList(0);
	    }

	    @Override
	    public TextureAtlasSprite getTexture(int i) {
	        return Ic2Icons.getTextures(GTMod.MODID + "_dusts")[variant.getID()];
	    }
	}
