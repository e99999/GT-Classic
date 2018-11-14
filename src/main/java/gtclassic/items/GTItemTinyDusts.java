package gtclassic.items;

import gtclassic.GTClassic;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import ic2.core.platform.textures.obj.ITexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class GTItemTinyDusts extends Item implements IStaticTexturedItem {
    public enum GTItemTinyDustTypes{
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
        OBSIDIAN(39),
        CHARCOAL(40),
        RED_GARNET(41),
        YELLOW_GARNET(42),
        PYROPE(43),
        ALMANDINE(44),
        SPESSARTINE(45),
        ANDRADITE(46),
        GROSSULAR(47),
        UVAROVITE(48),
        ASHES(49),
        DARK_ASHES(50),
        REDROCK(51),
        MARBLE(52),
        BASALT(53),
        THORIUM(54),
        PLUTONIUM(55),
        COAL(56),
        IRON(57),
        GOLD(58),
        COPPER(59),
        TIN(60),
        BRONZE(61),
        SILVER(62),
        CLAY(63),
        GUNPOWDER(64),
        REDSTONE(65),
        GLOWSTONE(66);
        
    	private int id;

        GTItemTinyDustTypes(int id){
            this.id = id;
        }

        public int getID(){
            return id;
        }
    }

    GTItemTinyDustTypes variant;
    public GTItemTinyDusts(GTItemTinyDustTypes variant){
        this.variant = variant;
        setRegistryName(variant.toString().toLowerCase() + "_tiny_dust");
        setUnlocalizedName(GTClassic.MODID + "." + variant.toString().toLowerCase() + "_tiny_dust");
        setCreativeTab(GTClassic.creativeTabGT);
    }

    @Override
    public List<Integer> getValidVariants() {
        return Arrays.asList(0);
    }

    @Override
    public TextureAtlasSprite getTexture(int i) {
        return Ic2Icons.getTextures("gtclassic_duststiny")[variant.getID()];
    }
}
