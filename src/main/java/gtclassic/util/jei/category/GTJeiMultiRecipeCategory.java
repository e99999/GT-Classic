package gtclassic.util.jei.category;

import gtclassic.GTMod;
import gtclassic.util.jei.wrapper.GTJeiMultiRecipeWrapper;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.*;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class GTJeiMultiRecipeCategory implements IRecipeCategory<GTJeiMultiRecipeWrapper> {

    private static int xStart = 26, yStart = 26;

    protected String name, displayName;
    protected ResourceLocation backgroundTexture;
    private IDrawable background, progress;

    public GTJeiMultiRecipeCategory(IGuiHelper helper, String name, Block block) {
        this.name = name;
        displayName = new ItemStack(block).getDisplayName();
        backgroundTexture = new ResourceLocation(GTMod.MODID, "textures/gui/default.png");
        background = helper.createDrawable(backgroundTexture, 25, 25, 120, 36);
        IDrawableStatic progressPic = helper.createDrawable(backgroundTexture, 176, 0, 20, 18);
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
        progress.draw(minecraft, 53, 0);
    }

    @Override
    public void setRecipe(IRecipeLayout layout, GTJeiMultiRecipeWrapper wrapper, IIngredients ingredients) {
        IGuiItemStackGroup itemGroup = layout.getItemStacks();

        int index = 0;
        for (List<ItemStack> list : ingredients.getInputs(VanillaTypes.ITEM)) {
            for (ItemStack stack : list) {
                itemGroup.init(index, true, 17 * index, 0);
                itemGroup.set(index++, stack);
            }
        }
        for (List<ItemStack> list : ingredients.getOutputs(VanillaTypes.ITEM)) {
            for (ItemStack stack : list) {
                itemGroup.init(index, false, 45 + (18 * index), 0);
                itemGroup.set(index++, stack);
            }
        }
    }
}
