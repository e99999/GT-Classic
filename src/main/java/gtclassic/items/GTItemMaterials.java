package gtclassic.items;

import gtclassic.GTClassic;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.List;

public class GTItemMaterials extends Item implements IStaticTexturedItem {
    public enum GTItemMaterialTypes{
        IRIDIUM_ALLOY_INGOT(0),
        HOT_TUNGSTENSTEEL_INGOT(1),
        TUNGSTENSTEEL_INGOT(2),
        IRIDIUM_INGOT(3),
        ALUMINUM_INGOT(4),
        TITANIUM_INGOT(5),
        CHROME_INGOT(6),
        ELECTRUM_INGOT(7),
        TUNGSTEN_INGOT(8),
        LEAD_INGOT(9),
        ZINC_INGOT(10),
        BRASS_INGOT(11),
        STEEL_INGOT(12),
        PLATINUM_INGOT(13),
        NICKEL_INGOT(14),
        INVAR_INGOT(15),
        OSMIUM_INGOT(16),
        RUBY(17),
        SAPPHIRE(18),
        GREEN_SAPPHIRE(19),
        LAZURITE_CHUNK(20),
        SILICON_PLATE(21),
        OLIVINE(22),
        THORIUM_INGOT(23),
        PLUTONIUM_INGOT(24),
        RED_GARNET(25),
        YELLOW_GARNET(26),
        IRON_PLATE(27),
        GOLD_PLATE(28),
        REFINED_IRON_PLATE(29),
        TIN_PLATE(30),
        COPPER_PLATE(31),
        SILVER_PLATE(32),
        BRONZE_PLATE(33),
        ELECTRUM_PLATE(34),
        NICKEL_PLATE(35),
        INVAR_PLATE(36),
        LEAD_PLATE(37),
        ALUMINUM_PLATE(38),
        CHROME_PLATE(39),
        TITANIUM_PLATE(40),
        STEEL_PLATE(41),
        PLATINUM_PLATE(42),
        TUNGSTEN_PLATE(43),
        BRASS_PLATE(44),
        ZINC_PLATE(45),
        TUNGSTENSTEEL_PLATE(46),
        OSMIUM_PLATE(47),
        MAGNALIUM_PLATE(48),
        FLOUR(49),
        WOOD_PLATE(50);
        
    	private int id;

        GTItemMaterialTypes(int id){
            this.id = id;
        }

        public int getID(){
            return id;
        }
    }

    GTItemMaterialTypes variant;
    public GTItemMaterials(GTItemMaterialTypes variant){
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
    public TextureAtlasSprite getTexture(int i) {
        return Ic2Icons.getTextures("gtclassic_materials")[variant.getID()];
    }
}
