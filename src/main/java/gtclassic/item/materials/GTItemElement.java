package gtclassic.item.materials;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTItems;
import gtclassic.GTMod;
import gtclassic.util.color.GTColorItemInterface;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ILayeredItemModel;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemElement extends Item implements IStaticTexturedItem, GTColorItemInterface, ILayeredItemModel {

	String name;
	Color color;

	public GTItemElement(String name, Color color) {
		this.name = name;
		this.color = color;
		setRegistryName(this.name.toLowerCase());
		setUnlocalizedName(GTMod.MODID + "." + this.name.toLowerCase());
		setCreativeTab(GTMod.creativeTabGT);
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
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int i) {
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[10];
	}

	@Override
	public Color getColor(ItemStack stack, int index) {
		if (index == 0) {
			return Color.white;
		} else {
			return this.color;
		}
	}

	@Override
	public boolean isLayered(ItemStack var1) {
		if (this == GTItems.glassTube) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public int getLayers(ItemStack var1) {
		return 2;
	}

	@Override
	public TextureAtlasSprite getTexture(int var1, ItemStack var2) {
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[10 + var1];
	}
}
