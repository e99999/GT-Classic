package gtclassic.item;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTClassic;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemGem extends Item implements IStaticTexturedItem {
	public enum GTItemGemTypes {
		RUBY(21), SAPPHIRE(22);

		private int id;

		GTItemGemTypes(int id) {
			this.id = id;
		}

		public int getID() {
			return id;
		}
	}

	GTItemGemTypes variant;

	public GTItemGem(GTItemGemTypes variant) {
		this.variant = variant;
		setRegistryName(variant.toString().toLowerCase() + "_gem");
		setUnlocalizedName(GTClassic.MODID + "." + variant.toString().toLowerCase() + "_gem");
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
