package gtclassic.tool;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.materialsnew.GTMaterial;
import gtclassic.util.color.GTColorItemInterface;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ILayeredItemModel;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTToolFile extends Item implements IStaticTexturedItem, GTColorItemInterface, ILayeredItemModel {

	GTMaterial material;

	public GTToolFile(GTMaterial material) {
		this.maxStackSize = 1;
		this.material = material;
		this.setMaxDamage(this.material.getDurability());
		setRegistryName(this.material.getName() + "_file");
		setUnlocalizedName(GTMod.MODID + "." + this.material.getName() + "_file");
		setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public boolean hasContainerItem(ItemStack itemStack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		ItemStack copy = itemStack.copy();
		return copy.attemptDamageItem(1, itemRand, null) ? ItemStack.EMPTY : copy;
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int meta) {
		return Ic2Icons.getTextures("gtclassic_items")[8];
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
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[8 + var1];
	}

}
