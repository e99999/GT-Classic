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

public class GTJeiCentrifugeCategory implements IRecipeCategory<GTJeiCentrifugeWrapper> {

	ItemStack displayName;
	IDrawable draw;
	IDrawable slot;
	IDrawable arrow;

	public GTJeiCentrifugeCategory(IGuiHelper helper) {
		displayName = new ItemStack(GTBlocks.industrialCentrifuge);
		ResourceLocation texture = new ResourceLocation(GTClassic.MODID, "textures/gui/industrialcentrifuge.png");
		this.draw = helper.createDrawable(texture, 49, 4, 78, 78);
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
		return "centrifuge";
	}

	@Override
	public void setRecipe(IRecipeLayout layout, GTJeiCentrifugeWrapper arg1, IIngredients ingridient) {
		IGuiItemStackGroup guiItemStacks = layout.getItemStacks();
		guiItemStacks.init(0, true, 30, 30); // input
		guiItemStacks.init(1, true, 0, 0); // cell slot
		guiItemStacks.init(2, false, 0, 30); // outputs
		guiItemStacks.init(3, false, 30, 0);
		guiItemStacks.init(4, false, 60, 30);
		guiItemStacks.init(5, false, 30, 60);
		guiItemStacks.set(ingridient);

	}

}
