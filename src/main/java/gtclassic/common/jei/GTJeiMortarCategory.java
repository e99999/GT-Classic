package gtclassic.common.jei;

import gtclassic.GTMod;
import gtclassic.api.jei.GTJeiMultiRecipeCategory;
import ic2.api.recipe.IRecipeInput;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GTJeiMortarCategory implements IRecipeCategory<GTJeiMortarWrapper> {

	protected String name, displayName;
	protected ResourceLocation backgroundTexture;
	private IDrawable background;

	public GTJeiMortarCategory(IGuiHelper helper, String name, Block block) {
		this.name = name;
		displayName = "Mortar";
		backgroundTexture = GTJeiMultiRecipeCategory.DEFAULT_TEXTURE;
		background = helper.createDrawable(backgroundTexture, 16, 16, 144, 40);
	}

	@Override
	public String getUid() {
		return name;
	}

	@Override
	public String getTitle() {
		return displayName;
	}

	@Override
	public String getModName() {
		return GTMod.MODID;
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public void setRecipe(IRecipeLayout layout, GTJeiMortarWrapper wrapper, IIngredients ingredients) {
		IGuiItemStackGroup itemGroup = layout.getItemStacks();
		int index = 0;
		int actualIndex = 0;
		for (IRecipeInput list : wrapper.getMultiRecipe().getInputs()) {
			int x = index % 3;
			int y = index / 3;
			itemGroup.init(actualIndex, true, (18 * x), (18 * y));
			itemGroup.set(actualIndex, list.getInputs());
			index++;
			actualIndex++;
			if (index >= 6) {
				break;
			}
		}
		index = 0;
		for (ItemStack stack : wrapper.getMultiRecipe().getOutputs().getAllOutputs()) {
			int x = index % 3;
			int y = index / 3;
			itemGroup.init(actualIndex, false, 90 + (18 * x), (18 * y));
			itemGroup.set(actualIndex, stack);
			index++;
			actualIndex++;
			if (index >= 6) {
				break;
			}
		}
	}
}