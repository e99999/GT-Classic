package gtclassic.api.jei;

import gtclassic.GTMod;
import gtclassic.api.recipe.GTFluidMachineOutput;
import gtclassic.common.GTConfig;
import ic2.api.classic.recipe.crafting.RecipeInputFluid;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.item.misc.ItemDisplayIcon;
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
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

public class GTJeiMultiRecipeCategory implements IRecipeCategory<GTJeiMultiRecipeWrapper> {

	protected String name, displayName;
	protected ResourceLocation backgroundTexture;
	private IDrawable background, progress;

	public GTJeiMultiRecipeCategory(IGuiHelper helper, String name, Block block) {
		this.name = name;
		displayName = new ItemStack(block).getDisplayName().replace(" Controller", "");
		backgroundTexture = new ResourceLocation(GTMod.MODID, "textures/gui/default.png");
		background = helper.createDrawable(backgroundTexture, 16, 16, 144, getHeight());
		IDrawableStatic progressPic = helper.createDrawable(backgroundTexture, 176, 0, 20, 18);
		progress = helper.createAnimatedDrawable(progressPic, 150, IDrawableAnimated.StartDirection.LEFT, false);
	}

	protected int getHeight() {
		int baseHeight = this.name.equals("gt.fusion") ? 100 : 90;
		int extraHeight = GTConfig.general.debugMode ? 10 : 0;
		return baseHeight + extraHeight;
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
	public void setRecipe(IRecipeLayout layout, GTJeiMultiRecipeWrapper wrapper, IIngredients ingredients) {
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
		index = 0;
		MachineOutput output = wrapper.getMultiRecipe().getOutputs();
		if (output instanceof GTFluidMachineOutput) {
			for (FluidStack stack : ((GTFluidMachineOutput) output).getFluids()) {
				int x = index % 3;
				int y = index / 3;
				fluidGroup.init(actualIndex, false, 91 + (18 * x), (18 * y) + 1, 16, 16, stack.amount, true, null);
				fluidGroup.set(actualIndex, stack);
				index++;
				actualIndex++;
				if (index >= 6) {
					break;
				}
			}
			for (ItemStack stack : output.getAllOutputs()) {
				if (index >= 6) {
					break;
				}
				if (stack.getItem() instanceof ItemDisplayIcon) {
					continue;
				}
				int x = index % 3;
				int y = index / 3;
				itemGroup.init(actualIndex, false, 90 + (18 * x), (18 * y));
				itemGroup.set(actualIndex, stack);
				index++;
				actualIndex++;
				if (index == 6) {
					break;
				}
			}
		} else {
			for (ItemStack stack : output.getAllOutputs()) {
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
}
