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
	private GTMaterialFlag flag;

	public GTMaterialItem(GTMaterial material, GTMaterialFlag flag) {
		this.material = material;
		this.flag = flag;
		setRegistryName(this.material.getName() + flag.getSuffix());
		setUnlocalizedName(GTMod.MODID + "." + this.material.getName() + this.getSuffix());
		setCreativeTab(GTMod.creativeTabGT);
	}

	public String getSuffix() {
		return flag.getSuffix();
	}
	
	public int getIDFromFlag() {
		if (this.flag == (GTMaterialFlag.SMALLDUST)) {
			return 0;
		}
		if (this.flag == (GTMaterialFlag.DUST)) {
			return 1;
		}
		if (this.flag == (GTMaterialFlag.GEM)) {
			return 2;
		}
		if (this.flag == (GTMaterialFlag.INGOT)) {
			return 3;
		}
		if (this.flag == (GTMaterialFlag.NUGGET)) {
			return 4;
		}
		if (this.flag == (GTMaterialFlag.PLATE)) {
			return 5;
		}
		if (this.flag == (GTMaterialFlag.STICK)) {
			return 6;
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
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[getIDFromFlag()];
	}

	@Override
	public Color getColor(ItemStack stack, int index) {
		return this.material.getColor();
	}

}
