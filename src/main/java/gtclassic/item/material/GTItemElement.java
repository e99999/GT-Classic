package gtclassic.item.material;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTClassic;
import gtclassic.util.GTItems;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemElement extends Item implements IStaticTexturedItem {
	public enum GTItemElementTypes {
		HYDROGEN(0), DEUTERIUM(1), TRITIUM(2), HELIUM(3), TUNGSTEN(4), LITHIUM(5), HELIUM3(6), SILICON(7), CARBON(8),
		METHANE(9), BERILIUM(10), CALCIUM(11), SODIUM(12), CHLORINE(13), POTASSIUM(14), NITROGEN(15), OXYGEN(18),

		WATER(16), LAVA(17), PROTON(19), NEUTRON(20);

		private int id;

		GTItemElementTypes(int id) {
			this.id = id;
		}

		public int getID() {
			return id;
		}
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		if (this == GTItems.hydrogen) {
			return (12000 / 20);
		}
		if (this == GTItems.lithium) {
			return (24000 / 20);
		}
		if (this == GTItems.carbon) {
			return (6000 / 20);
		}
		if (this == GTItems.methane) {
			return (24000 / 20);
		} else {
			return 0;
		}
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (this == GTItems.proton || this == GTItems.neutron) {
			tooltip.add(TextFormatting.RED + I18n.format("tooltip." + GTClassic.MODID + ".wip"));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		if (this == GTItems.proton || this == GTItems.neutron) {
			return true;
		}
		return false;
	}

	GTItemElementTypes variant;

	public GTItemElement(GTItemElementTypes variant) {
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