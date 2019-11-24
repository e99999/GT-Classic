package gtclassic.common.jei;

import gtclassic.GTMod;
import gtclassic.common.GTConfig;
import ic2.api.classic.recipe.crafting.RecipeInputFluid;
import ic2.api.recipe.IRecipeInput;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class GTJeiMagicFuelCategory implements IRecipeCategory<GTJeiMagicFuelWrapper> {

	protected String name, displayName;
	protected ResourceLocation backgroundTexture;
	private IDrawable background, progress;

	public GTJeiMagicFuelCategory(IGuiHelper helper, String name, Block block) {
		this.name = name;
		displayName = "Magic Fuel";
		backgroundTexture = new ResourceLocation(GTMod.MODID, "textures/gui/default.png");
		background = helper.createDrawable(backgroundTexture, 16, 16, 144, getHeight());
		IDrawableStatic progressPic = helper.createDrawable(backgroundTexture, 176, 0, 20, 18);
		progress = helper.createAnimatedDrawable(progressPic, 150, IDrawableAnimated.StartDirection.LEFT, false);
	}

	private int getHeight() {
		return GTConfig.general.debugMode ? 80 : 70;
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
		progress.draw(minecraft, 62, 9);
	}

	@Override
	public void setRecipe(IRecipeLayout layout, GTJeiMagicFuelWrapper wrapper, IIngredients ingredients) {
		IGuiItemStackGroup itemGroup = layout.getItemStacks();
		IGuiFluidStackGroup fluidGroup = layout.getFluidStacks();
		int index = 0;
		int actualIndex = 0;
		for (IRecipeInput list : wrapper.getMultiRecipe().getInputs()) {
			int x = index % 3;
			int y = index / 3;
			if (list instanceof RecipeInputFluid) {
				fluidGroup.init(actualIndex, true, (18 * x) + 1, (18 * y)
						+ 1, 16, 16, ((RecipeInputFluid) list).fluid.amount, true, null);
				fluidGroup.set(actualIndex, ((RecipeInputFluid) list).fluid);
			} else {
				itemGroup.init(actualIndex, true, (18 * x), (18 * y));
				itemGroup.set(actualIndex, list.getInputs());
			}
			index++;
			actualIndex++;
			if (index >= 6) {
				break;
			}
		}
	}
}