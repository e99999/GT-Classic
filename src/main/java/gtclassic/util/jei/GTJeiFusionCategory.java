package gtclassic.util.jei;

import gtclassic.GTClassic;
import gtclassic.util.GTBlocks;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GTJeiFusionCategory implements IRecipeCategory<GTJeiFusionWrapper> {

	ItemStack displayName;
	IDrawable draw;
	IDrawable slot;
	IDrawable arrow;

	public GTJeiFusionCategory(IGuiHelper helper) {
		displayName = new ItemStack(GTBlocks.fusionComputer);
		ResourceLocation texture = new ResourceLocation(GTClassic.MODID, "textures/gui/fusioncomputer.png");
		this.draw = helper.createDrawable(texture, 86, 15, 84, 56);
	}

	@Override
	public IDrawable getBackground() {
		return this.draw;
	}

	@Override
	public String getModName() {
		return "gtclassic";
	}

	@Override
	public String getTitle() {
		return displayName.getDisplayName();
	}

	@Override
	public String getUid() {
		return "fusion";
	}

	@Override
	public void setRecipe(IRecipeLayout layout, GTJeiFusionWrapper arg1, IIngredients ingridient) {
		IGuiItemStackGroup guiItemStacks = layout.getItemStacks();
		guiItemStacks.init(0, true, 1, 1); // input
		guiItemStacks.init(1, true, 1, 37); // cell slot
		guiItemStacks.init(2, false, 61, 19); // outputs
		guiItemStacks.set(ingridient);

	}

}
