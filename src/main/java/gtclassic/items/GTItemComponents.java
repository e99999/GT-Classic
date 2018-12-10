package gtclassic.items;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTClassic;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemComponents extends Item implements IStaticTexturedItem {
	public enum GTItemComponentTypes {
		ENERGY_FLOW_CIRCUIT(38), DATA_CONTROL_CIRCUIT(39), SUPERCONDUCTOR(42), DATA_STORAGE_CIRCUIT(41), DATA_ORB(43),
		SILICON_PLATE(55), GLASS_TUBE(63);

		private int id;

		GTItemComponentTypes(int id) {
			this.id = id;
		}

		public int getID() {
			return id;
		}
	}

	GTItemComponentTypes variant;

	public GTItemComponents(GTItemComponentTypes variant) {
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
