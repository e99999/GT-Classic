package gtclassic.item.materials;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.GTRecipes;
import gtclassic.util.GTItemColorInterface;
import gtclassic.util.GTValues;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemStick extends Item implements IStaticTexturedItem, GTItemColorInterface {

	private String material;

	public GTItemStick(String material) {
		this.material = material;
		setRegistryName(this.material + "_stick");
		setUnlocalizedName(GTMod.MODID + "." + this.material + "_stick");
		setCreativeTab(GTMod.creativeTabGT);
		setRecipes();
	}

	public void setRecipes() {
		String input = "ingot" + this.material;
		GTRecipes.recipes.addShapelessRecipe(new ItemStack(this, 1), new Object[] { input, "craftingToolFile" });
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
		return GTValues.getColor(this.material);
	}
	
	

}
