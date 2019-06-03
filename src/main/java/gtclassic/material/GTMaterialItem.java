package gtclassic.material;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.color.GTColorItemInterface;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ILayeredItemModel;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTMaterialItem extends Item implements IStaticTexturedItem, GTColorItemInterface, ILayeredItemModel {

	private GTMaterial material;
	private GTMaterialFlag flag;

	public GTMaterialItem(GTMaterial material, GTMaterialFlag flag) {
		this.material = material;
		this.flag = flag;
		setRegistryName(this.material.getName() + this.flag.getSuffix());
		setUnlocalizedName(GTMod.MODID + "." + this.material.getName() + this.flag.getSuffix());
		setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("Melting: " + material.getTemp() + "K"));
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return GTMaterial.isRadioactive(material) ? true : super.hasEffect(stack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int i) {
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[flag.getTextureID()];
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
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[flag.getTextureID() + index];
	}

	public GTMaterial getMaterial() {
		return this.material;
	}

	public GTMaterialFlag getFlag() {
		return this.flag;
	}
}
