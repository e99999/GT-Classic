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

public class GTJeiFusionCategory implements IRecipeCategory<GTJeiFusionWrapper> {

	ItemStack displayName;
	IDrawable draw;
	IDrawable slot;
	IDrawable progress;

	public GTJeiFusionCategory(IGuiHelper helper) {
		displayName = new ItemStack(GTBlocks.fusionComputer);
		ResourceLocation texture = new ResourceLocation(GTMod.MODID, "textures/gui/fusioncomputer.png");
		this.draw = helper.createDrawable(texture, 86, 25, 82, 36);
		IDrawableStatic progressPic = helper.createDrawable(texture, 176, 0, 25, 17);
		this.progress = helper.createAnimatedDrawable(progressPic, 150, IDrawableAnimated.StartDirection.LEFT, false);
	}

	@SideOnly(Side.CLIENT)
	public void drawExtras(Minecraft arg0) {
		this.progress.draw(arg0, 25, 10);
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
		return "fusion";
	}

	@Override
	public void setRecipe(IRecipeLayout layout, GTJeiFusionWrapper arg1, IIngredients ingridient) {
		IGuiItemStackGroup guiItemStacks = layout.getItemStacks();
		guiItemStacks.init(0, true, 1, 0); // input
		guiItemStacks.init(1, true, 1, 18); // cell slot
		guiItemStacks.init(2, false, 61, 9); // outputs
		guiItemStacks.set(ingridient);

	}

}
