package gtclassic.item;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemComponents extends Item implements IStaticTexturedItem {
	public enum GTItemComponentTypes {
		
		
		BOULE_SILICON(32, false),
		PLATE_SILICON(33, false),
		PLATE_PLASTIC(34, false),
		LENS_DIAMOND(35, true),
		LENS_RUBY(36, true),
		LENS_EMERALD(37, true),
		LENS_SAPPHIRE(38, true),
		CHIP_DIAMOND(39, false),
		CHIP_RUBY(40, false),
		CHIP_EMERALD(41, false),
		CHIP_SAPPHIRE(42, false),
		CIRCUIT_EMPTY(43, false),
		CIRCUIT_DIAMOND(44, false),
		CIRCUIT_RUBY(45, false),
		CIRCUIT_EMERALD(46, false),
		CIRCUIT_SAPPHIRE(47, false);

		private int id;
		private boolean containeritem;

		GTItemComponentTypes(int id, boolean containeritem) {
			this.id = id;
			this.containeritem = containeritem;
		}

		public int getID() {
			return id;
		}
		
		public boolean isContainerItem() {
			return containeritem;
		}
	}

	GTItemComponentTypes variant;

	public GTItemComponents(GTItemComponentTypes variant) {
		this.variant = variant;
		setRegistryName(variant.toString().toLowerCase());
		setUnlocalizedName(GTMod.MODID + "." + variant.toString().toLowerCase());
		setCreativeTab(GTMod.creativeTabGT);
	}
	
	@Override
	public boolean hasContainerItem(ItemStack itemStack) {
		return variant.isContainerItem();
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		return itemStack.copy();
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
