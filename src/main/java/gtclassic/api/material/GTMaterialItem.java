package gtclassic.api.material;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.api.interfaces.IGTColorItem;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ILayeredItemModel;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTMaterialItem extends Item implements IStaticTexturedItem, IGTColorItem, ILayeredItemModel {

	private GTMaterial material;
	private GTMaterialFlag flag;

	public GTMaterialItem(GTMaterial material, GTMaterialFlag flag) {
		this.material = material;
		this.flag = flag;
		setRegistryName(this.material.getName() + this.flag.getSuffix());
		setTranslationKey(GTMod.MODID + "." + this.flag.getPrefix() + this.material.getDisplayName());
		setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int i) {
		return Ic2Icons.getTextures(flag.getTexture())[flag.getTextureID()];
	}

	@Override
	public Color getColor(ItemStack stack, int index) {
		if (index == 0) {
			return this.material.getColor();
		} else {
			return Color.white;
		}
	}

	@Override
	public boolean isLayered(ItemStack var1) {
		return flag.isLayered();
	}

	@Override
	public int getLayers(ItemStack var1) {
		return 2;
	}

	@Override
	public TextureAtlasSprite getTexture(int index, ItemStack var2) {
		if (flag.isCrafttweaker()) {
			String add = index == 0 ? "" : "1";
			return Ic2Icons.getTextures(flag.getTexture() + add)[0];
		}
		return Ic2Icons.getTextures(flag.getTexture())[flag.getTextureID() + index];
	}

	@Override
	public int getItemBurnTime(ItemStack itemstack) {
		if (this.material.equals(GTMaterial.Lithium) && this.flag.equals(GTMaterialFlag.DUST)) {
			return 2000;
		}
		if (this.material.equals(GTMaterial.Carbon) && this.flag.equals(GTMaterialFlag.DUST)) {
			return 1000;
		}
		if (this.material.equals(GTMaterial.Sulfur) && this.flag.equals(GTMaterialFlag.DUST)) {
			return 1600;
		}
		return 0;
	}

	public GTMaterial getMaterial() {
		return this.material;
	}

	public GTMaterialFlag getFlag() {
		return this.flag;
	}
}
