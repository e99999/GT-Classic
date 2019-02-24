package gtclassic.util.jei;

import gtclassic.GTBlocks;
import gtclassic.GTMod;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTJeiAlloySmelterCategory implements IRecipeCategory<GTJeiAlloySmelterWrapper> {

	ItemStack displayName;
	IDrawable draw;
	IDrawable slot;
	IDrawable progress;

	public GTJeiAlloySmelterCategory(IGuiHelper helper) {
		displayName = new ItemStack(GTBlocks.alloySmelter);
		ResourceLocation texture = new ResourceLocation(GTMod.MODID, "textures/gui/alloysmelter.png");
		this.draw = helper.createDrawable(texture, 25, 25, 120, 36);
		IDrawableStatic progressPic = helper.createDrawable(texture, 176, 0, 10, 10);
		this.progress = helper.createAnimatedDrawable(progressPic, 150, IDrawableAnimated.StartDirection.LEFT, false);
	}

	@SideOnly(Side.CLIENT)
	public void drawExtras(Minecraft arg0) {
		this.progress.draw(arg0, 37, 4);
	}

	@Override
	public IDrawable getBackground() {
		return this.draw;
	}

	@Override
	public String getModName() {
		return GTMod.MODID;
	}

	@Override
	public String getTitle() {
		return displayName.getDisplayName();
	}

	@Override
	public String getUid() {
		return "alloysmelter";
	}

	@Override
	public void setRecipe(IRecipeLayout layout, GTJeiAlloySmelterWrapper arg1, IIngredients ingridient) {
		IGuiItemStackGroup guiItemStacks = layout.getItemStacks();
		guiItemStacks.init(0, true, 0, 0); // input1
		guiItemStacks.init(1, true, 18, 0); // input2
		guiItemStacks.init(2, false, 48, 0); // outputs
		guiItemStacks.set(ingridient);

	}

}
