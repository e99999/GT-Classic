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

public class GTToolHammer extends ItemPickaxe
		implements IStaticTexturedItem, GTColorItemInterface, ILayeredItemModel, ICustomItemCameraTransform {

	GTMaterial material;

	public GTToolHammer(ToolMaterial tmat) {
		super(ToolMaterial.IRON);
		this.material = GTToolMaterial.getGTMaterial(tmat);
		this.efficiency = this.material.getSpeed() * (this.material.getLevel() / 2);
		this.setHarvestLevel("pickaxe", this.material.getLevel());
		this.setMaxDamage((this.material.getDurability() * 2) + 64);
		setRegistryName(this.material.getName() + "_hammer");
		setUnlocalizedName(GTMod.MODID + "." + this.material.getName() + "_hammer");
		setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		ItemStack copy = itemStack.copy();
		return copy.attemptDamageItem(1, itemRand, null) ? ItemStack.EMPTY : copy;
	}

	@Override
	@Deprecated
	public boolean hasContainerItem() {
		return true;
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int i) {
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[16];
	}

	@Override
	public Color getColor(ItemStack stack, int index) {
		if (index == 0) {
			return GTMaterial.Wood.getColor();
		} else {
			return this.material.getColor();
		}
	}

	@Override
	public boolean isLayered(ItemStack var1) {
		return true;
	}

	@Override
	public int getLayers(ItemStack var1) {
		return 2;
	}

	@Override
	public TextureAtlasSprite getTexture(int var1, ItemStack var2) {
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[16 + var1];
	}

	public GTMaterial getMaterial() {
		return this.material;
	}

	public ResourceLocation getCustomTransform(int meta) {
		return new ResourceLocation("minecraft:models/item/handheld");
	}

	@Override
	public boolean hasCustomTransform(int var1) {
		return true;
	}

}
