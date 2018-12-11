package gtclassic.item.material;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTClassic;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemPlasma extends Item implements IStaticTexturedItem {

	public enum GTItemPlasmaTypes {
		HYDROGEN(16), NITROGEN(15), OXYGEN(18), HELIUM(3), IRON(14), UU(9);

		private int id;

		GTItemPlasmaTypes(int id) {
			this.id = id;
		}

		public int getID() {
			return id;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public EnumRarity getRarity(ItemStack thisItem) {
		return EnumRarity.RARE;
	}

	GTItemPlasmaTypes variant;

	public GTItemPlasma(GTItemPlasmaTypes variant) {
		this.variant = variant;
		setRegistryName(variant.toString().toLowerCase() + "_plasma");
		setUnlocalizedName(GTClassic.MODID + "." + variant.toString().toLowerCase() + "_plasma");
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
