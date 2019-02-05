package gtclassic.materialsnew;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.util.color.GTColorItemInterface;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTMaterialItem extends Item implements IStaticTexturedItem, GTColorItemInterface {

	private GTMaterial material;
	private int type;

	public GTMaterialItem(GTMaterial material, int type) {
		this.material = material;
		this.type = type;
		setRegistryName(this.material.getName() + this.getSuffix());
		setUnlocalizedName(GTMod.MODID + "." + this.material.getName() + this.getSuffix());
		setCreativeTab(GTMod.creativeTabGT);
	}

	public String getSuffix() {
		if (this.type == 0) {
			return "_dustsmall";
		}
		if (this.type == 1) {
			return "_dust";
		}
		if (this.type == 2) {
			return "_gem";
		}
		if (this.type == 3) {
			return "_ingot";
		}
		if (this.type == 4) {
			return "_nugget";
		}
		if (this.type == 5) {
			return "_plate";
		}
		if (this.type == 6) {
			return "_stick";
		} else {
			return "_null";
		}

	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int i) {
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[this.type];
	}

	@Override
	public Color getColor(ItemStack stack, int index) {
		return this.material.getColor();
	}

}
