package gtclassic.util.crafttweaker;

import crafttweaker.api.item.IIngredient;
import crafttweaker.api.minecraft.CraftTweakerMC;
import ic2.api.recipe.IRecipeInput;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import java.util.Arrays;
import java.util.List;

public class GTCraftTweakerIngredientInput implements IRecipeInput {
    private final IIngredient ingredient;

    GTCraftTweakerIngredientInput(IIngredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public boolean matches(ItemStack item) {
        return this.ingredient.matches(CraftTweakerMC.getIItemStack(item));
    }

    @Override
    public int getAmount() {
        return this.ingredient.getAmount();
    }

    @Override
    public List<ItemStack> getInputs() {
        return Arrays.asList(this.toNatives());
    }

    @Override
    public Ingredient getIngredient() {
        return Ingredient.fromStacks(this.toNatives());
    }

    private ItemStack[] toNatives() {
        return CraftTweakerMC.getItemStacks(this.ingredient.getItems());
    }
}
