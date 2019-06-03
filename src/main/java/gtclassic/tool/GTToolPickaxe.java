package gtclassic.tool;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.color.GTColorItemInterface;
import gtclassic.material.GTMaterial;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ICustomItemCameraTransform;
import ic2.core.platform.textures.obj.ILayeredItemModel;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTToolPickaxe extends ItemPickaxe
		implements IStaticTexturedItem, GTColorItemInterface, ILayeredItemModel, ICustomItemCameraTransform {

	ToolMaterial tmaterial;
	GTMaterial material;

	public GTToolPickaxe(ToolMaterial tmaterial) {
		super(tmaterial);
		this.tmaterial = tmaterial;
		this.material = GTToolMaterial.getGTMaterial(tmaterial);
		this.efficiency = this.material.getSpeed() * this.material.getLevel();
		this.setHarvestLevel("pickaxe", this.material.getLevel());
		setRegistryName(tmaterial.toString().toLowerCase() + "_pickaxe");
		setUnlocalizedName(GTMod.MODID + "." + tmaterial.toString().toLowerCase() + "_pickaxe");
		setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return GTMaterial.isRadioactive(material) ? true : super.hasEffect(stack);
	}

	@Override
	public int getLayers(ItemStack arg0) {
		return 2;
	}

	@Override
	public TextureAtlasSprite getTexture(int var1, ItemStack var2) {
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[29 + var1];
	}

	@Override
	public boolean isLayered(ItemStack arg0) {
		return true;
	}

	@Override
	public Color getColor(ItemStack stack, int index) {
		if (index == 0) {
			return GTMaterial.Wood.getColor();
		} else {
			return material.getColor();
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int meta) {
		return Ic2Icons.getTextures("gtclassic_items")[29];
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	public ResourceLocation getCustomTransform(int meta) {
		return new ResourceLocation("minecraft:models/item/handheld");
	}

	@Override
	public boolean hasCustomTransform(int var1) {
		return true;
	}

	public GTMaterial getMaterial() {
		return GTToolMaterial.getGTMaterial(tmaterial);
	}
}
