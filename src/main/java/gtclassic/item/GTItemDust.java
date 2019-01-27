package gtclassic.item;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemDust extends Item implements IStaticTexturedItem {
	public enum GTItemDustTypes {
		ENDERPEARL(68), ENDER_EYE(69), LAZURITE(72), PYRITE(73), CALCITE(74), FLINT(71), URANIUM(27), BAUXITE(26),
		ALUMINIUM(25), TITANIUM(29), CHROME(31), RUBY(64), SAPPHIRE(65), GREEN_SAPPHIRE(66), EMERALD(67), SODALITE(75), 
		
		PLATINUM(19), TUNGSTEN(21), PLASTIC(70);

		private int id;

		GTItemDustTypes(int id) {
			this.id = id;
		}

		public int getID() {
			return id;
		}
	}

	GTItemDustTypes variant;

	public GTItemDust(GTItemDustTypes variant) {
		this.variant = variant;
		setRegistryName(variant.toString().toLowerCase() + "_dust"); // The unique name (within your mod) that
																		// identifies this item
		setUnlocalizedName(GTMod.MODID + "." + variant.toString().toLowerCase() + "_dust"); // Used for localization
																								// (en_US.lang)
		setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int i) {
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[variant.getID()];
	}
}
