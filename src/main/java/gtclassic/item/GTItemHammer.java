package gtclassic.item;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.util.GTValues;
import gtclassic.util.color.GTColorItemInterface;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ILayeredItemModel;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemHammer extends ItemPickaxe implements IStaticTexturedItem, GTColorItemInterface, ILayeredItemModel {

	String material;

	public GTItemHammer(String material, Float speed, Integer durability, Integer level) {
		super(ToolMaterial.IRON);
		this.material = material;
		this.efficiency = speed;
		this.setHarvestLevel("pickaxe", level);
		this.setMaxDamage(durability * 2);
		setRegistryName(this.material + "_hammer");
		setUnlocalizedName(GTMod.MODID + "." + this.material + "_hammer");
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
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[6];
	}

	@Override
	public Color getColor(ItemStack stack, int index) {
		if (index == 0) {
			return GTValues.getColor("Wood");
		} else {
			return GTValues.getColor(this.material);
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
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[6 + var1];
	}

}
