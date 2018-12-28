package gtclassic.util.jei;

import gtclassic.GTBlocks;
import gtclassic.GTClassic;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.*;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTJeiCentrifugeCategory implements IRecipeCategory<GTJeiCentrifugeWrapper> {

	ItemStack displayName;
	IDrawable draw;
	IDrawable slot;
	IDrawable progress;

	public GTJeiCentrifugeCategory(IGuiHelper helper) {
		displayName = new ItemStack(GTBlocks.industrialCentrifuge);
		ResourceLocation texture = new ResourceLocation(GTClassic.MODID, "textures/gui/industrialcentrifuge.png");
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
		guiItemStacks.init(0, true, 0, 0); // input
		guiItemStacks.init(1, true, 18, 0); // cell slot
		guiItemStacks.init(2, false, 48, 0); // outputs
		guiItemStacks.init(3, false, 66, 0);
		guiItemStacks.init(4, false, 84, 0);
		guiItemStacks.init(5, false, 102, 0);
		guiItemStacks.set(ingridient);

	}

}
