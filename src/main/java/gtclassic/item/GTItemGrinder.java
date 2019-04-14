package gtclassic.item;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.color.GTColorItemInterface;
import gtclassic.material.GTMaterial;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ILayeredItemModel;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemGrinder extends Item implements IStaticTexturedItem, GTColorItemInterface, ILayeredItemModel {

	GTMaterial material;

	public GTItemGrinder(GTMaterial material) {
		this.material = material;
		setRegistryName(this.material.getName() + "_grinder");
		setUnlocalizedName(GTMod.MODID + "." + this.material.getName() + "_grinder");
		this.setMaxDamage((this.material.getDurability() * 10));
		this.setMaxStackSize(1);
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
	public int getLayers(ItemStack arg0) {
		return 2;
	}

	@Override
	public TextureAtlasSprite getTexture(int layer, ItemStack arg1) {
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[38 + layer];
	}

	@Override
	public boolean isLayered(ItemStack arg0) {
		return true;
	}

	@Override
	public Color getColor(ItemStack stack, int index) {
		return (index == 0) ? material.getColor() : Color.white;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int i) {
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[38];
	}

}
