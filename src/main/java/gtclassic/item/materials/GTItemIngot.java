package gtclassic.item.materials;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.util.GTValues;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemIngot extends Item implements IStaticTexturedItem {
	public enum GTItemIngotTypes {

        HOT_TUNGSTENSTEEL(1),
        TUNGSTENSTEEL(2),
        IRIDIUM(3),
        ALUMINIUM(4),
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

		GTItemIngotTypes(int id) {
			this.id = id;
		}

		public int getID() {
			return id;
		}
	}

	GTItemIngotTypes variant;

	public GTItemIngot(GTItemIngotTypes variant) {
		this.variant = variant;
		setRegistryName(variant.toString().toLowerCase() + "_ingot");
		setUnlocalizedName(GTMod.MODID + "." + variant.toString().toLowerCase() + "_ingot");
		setCreativeTab(GTMod.creativeTabGT);
	}
	
	public String getFormatName() {
		String name = variant.toString().toLowerCase();
		String output = name.substring(0, 1).toUpperCase() + name.substring(1);
		if (GTValues.debugMode){
			GTMod.logger.info("Creating ingots: "+ output);
			}
		return output;
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int i) {
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[variant.getID()];
	}
}
