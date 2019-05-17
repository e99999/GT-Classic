package gtclassic.util.jei.category;

import gtclassic.GTMod;
import gtclassic.util.jei.wrapper.GTJeiDryingWrapper;
import ic2.api.recipe.IRecipeInput;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GTJeiDryingCategory implements IRecipeCategory<GTJeiDryingWrapper> {

	private static int xStart = 26, yStart = 26;

	protected String name, displayName;
	protected ResourceLocation backgroundTexture;
	private IDrawable background, progress;

	public GTJeiDryingCategory(IGuiHelper helper, String name, Block block) {
		this.name = name;
		displayName = "Solar Evaporation";
		backgroundTexture = new ResourceLocation(GTMod.MODID, "textures/gui/drying.png");
		background = helper.createDrawable(backgroundTexture, 16, 16, 144, 90);
		IDrawableStatic progressPic = helper.createDrawable(backgroundTexture, 176, 0, 12, 12);
		progress = helper.createAnimatedDrawable(progressPic, 150, IDrawableAnimated.StartDirection.LEFT, false);
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
	public void drawExtras(Minecraft minecraft) {
		progress.draw(minecraft, 70, 0);
	}

	@Override
	public void setRecipe(IRecipeLayout layout, GTJeiDryingWrapper wrapper, IIngredients ingredients) {
		IGuiItemStackGroup itemGroup = layout.getItemStacks();

		int index = 0;
		int actualIndex = 0;
		for (IRecipeInput list : wrapper.getMultiRecipe().getInputs()) {
			int x = index % 3;
			int y = index / 3;
			itemGroup.init(actualIndex, true, 36 + (18 * x), (18 * y));
			itemGroup.set(actualIndex, list.getInputs());
			index++;
			actualIndex++;
			if (index >= 2) {
				break;
			}
		}
		index = 0;
		for (ItemStack stack : wrapper.getMultiRecipe().getOutputs().getAllOutputs()) {
			int x = index % 3;
			int y = index / 3;
			itemGroup.init(actualIndex, false, 98 + (18 * x), (18 * y));
			itemGroup.set(actualIndex, stack);
			index++;
			actualIndex++;
			if (index >= 2) {
				break;
			}
		}
	}
}
