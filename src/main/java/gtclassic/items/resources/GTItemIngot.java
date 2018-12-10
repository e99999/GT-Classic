package gtclassic.items.resources;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTClassic;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemIngot extends Item implements IStaticTexturedItem {
	public enum GTItemIngotTypes {
		IRIDIUM(23), ALUMINIUM(24), TITANIUM(28), CHROME(30);

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
		setUnlocalizedName(GTClassic.MODID + "." + variant.toString().toLowerCase() + "_ingot");
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
