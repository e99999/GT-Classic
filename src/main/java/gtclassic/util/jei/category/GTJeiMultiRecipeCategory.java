package gtclassic.util.jei.category;

import gtclassic.GTMod;
import gtclassic.util.jei.wrapper.GTJeiMultiRecipeWrapper;
import ic2.core.util.math.Vec2i;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public abstract class GTJeiMultiRecipeCategory implements IRecipeCategory<GTJeiMultiRecipeWrapper> {

    private static int SLOT_OFFSET_X = 26, SLOT_OFFSET_Y = 26;

    protected String name, displayName;
    protected ResourceLocation backgroundTexture;
    protected Vec2i[] slots;

    public GTJeiMultiRecipeCategory(String name, Block block, Vec2i[] slots) {
        this.name = name;
        displayName = new ItemStack(block).getDisplayName();
        backgroundTexture = new ResourceLocation(GTMod.MODID, "textures/gui/" + name + ".png");
        this.slots = slots;
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
    public abstract IDrawable getBackground();

    @Override
    public void setRecipe(IRecipeLayout layout, GTJeiMultiRecipeWrapper wrapper, IIngredients ingredients) {
        IGuiItemStackGroup itemGroup = layout.getItemStacks();

        int index = 0;
        for (List<ItemStack> list : ingredients.getInputs(VanillaTypes.ITEM)) {
            for (ItemStack stack : list) {
                itemGroup.init(index, true, slots[index].getX() - SLOT_OFFSET_X, slots[index].getY() - SLOT_OFFSET_Y);
                itemGroup.set(index++, stack);
            }
        }
        for (List<ItemStack> list : ingredients.getOutputs(VanillaTypes.ITEM)) {
            for (ItemStack stack : list) {
                itemGroup.init(index, false, slots[index].getX() - SLOT_OFFSET_X, slots[index].getY() - SLOT_OFFSET_Y);
                itemGroup.set(index++, stack);
            }
        }
    }
}
