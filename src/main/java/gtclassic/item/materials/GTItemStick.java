package gtclassic.item.materials;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTItems;
import gtclassic.GTMod;
import gtclassic.GTRecipes;
import gtclassic.util.GTValues;
import gtclassic.util.GTItemColorInterface;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IColorEffectedTexture;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class GTItemStick extends Item implements IStaticTexturedItem, GTItemColorInterface {

	private String name;
	private Color color;

	public GTItemStick(String name, Color color) {
		this.name = name;
		this.color = color;
		setRegistryName(this.name + "_stick");
		setUnlocalizedName(GTMod.MODID + "." + this.name + "_stick");
		setCreativeTab(GTMod.creativeTabGT);
		setRecipe();
	}

	public void setRecipe() {
		String input = "ingot" + this.name;
		GTRecipes.recipes.addShapelessRecipe(new ItemStack(this, 1), new Object[] { input, "craftingToolFile" });
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int i) {
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[48];
	}

	// COLOR STUFF

	public boolean hasColor(ItemStack stack) {
		return true;
	}

	public boolean hasOverlay(ItemStack stack) {
		return true;
	}

	public void setColor(ItemStack stack, int color) {
		stack.getOrCreateSubCompound("display").setInteger("color", GTValues.red);
	}

	public int getColor(ItemStack stack) {
		return StackUtil.getNbtData(stack).getCompoundTag("display").getInteger("color");
	}

	@Override
	public Color getColor(ItemStack stack, int index) {
		return this.color;
	}

}
