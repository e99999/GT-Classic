package gtclassic.util;

import gtclassic.GTMod;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.core.item.recipe.AdvRecipe;
import ic2.core.item.recipe.AdvRecipeBase;
import ic2.core.item.recipe.AdvShapelessRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class GTAdvRecipeMods {
    static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;

    @SuppressWarnings("unchecked")
    public void overrideGTRecipe(String modid, String recipeId, ItemStack output, Object... input) {
        Loader loader = Loader.instance();
        ModContainer old = loader.activeModContainer();
        loader.setActiveModContainer(loader.getIndexedModList().get(modid));
        Constructor<AdvRecipe> constructor = null;
        try {
            constructor = AdvRecipe.class.getDeclaredConstructor(String.class, Boolean.class, ItemStack.class, Object[].class);
        } catch (NoSuchMethodException e) {
            GTMod.logger.info("Trying to access AdvRecipe constructor has failed : (");
        } catch (SecurityException e) {
            GTMod.logger.info("AdvRecipe security deployed");
        }
        if (constructor !=null){
            constructor.setAccessible(true);
        }
        AdvRecipe recipe = null;
        try {
            if (constructor != null){
                recipe = constructor.newInstance(recipeId, true, output, input);
            }
        } catch (IllegalArgumentException e) {
            GTMod.logger.info("Accessed AdvRecipe class but constructor getter failed");
        } catch (IllegalAccessException e) {
            GTMod.logger.info("Accessed AdvRecipe class but access denied");
        } catch (InstantiationException | InvocationTargetException e){
            e.printStackTrace();
        }
        recipes.getRecipes().add(overrideRecipe(recipe, modid)); recipes.getRecipes().add(overrideRecipe(recipe, modid));
        loader.setActiveModContainer(old);
    }

    @SuppressWarnings("unchecked")
    public void overrideShapelessGTRecipe(String modid, String recipeId, ItemStack output, Object... input) {
        Loader loader = Loader.instance();
        ModContainer old = loader.activeModContainer();
        loader.setActiveModContainer(loader.getIndexedModList().get(modid));
        Constructor<AdvShapelessRecipe> constructor = null;
        try {
            constructor = AdvShapelessRecipe.class.getDeclaredConstructor(String.class, Boolean.class, ItemStack.class, Object[].class);
        } catch (NoSuchMethodException e) {
            GTMod.logger.info("Trying to access AdvRecipeShapeless constructor has failed : (");
        } catch (SecurityException e) {
            GTMod.logger.info("AdvRecipeShapeless security deployed");
        }
        if (constructor !=null){
            constructor.setAccessible(true);
        }
        AdvShapelessRecipe recipe = null;
        try {
            if (constructor != null){
                recipe = constructor.newInstance(recipeId, true, output, input);
            }
        } catch (IllegalArgumentException e) {
            GTMod.logger.info("Accessed AdvRecipeShapeless class but constructor getter failed");
        } catch (IllegalAccessException e) {
            GTMod.logger.info("Accessed AdvRecipeShapeless class but access denied");
        } catch (InstantiationException | InvocationTargetException e){
            e.printStackTrace();
        }
        recipes.getRecipes().add(overrideRecipe(recipe, modid));
        loader.setActiveModContainer(old);
    }

    @SuppressWarnings("unchecked")
    public static <T extends AdvRecipeBase> T overrideRecipe(T recipe, String modid) {
        ((ForgeRegistry) ForgeRegistries.RECIPES).remove(new ResourceLocation(modid, recipe.getRecipeID()));
        Field duplicatesf = null;
        try {
            duplicatesf = AdvRecipeBase.class.getDeclaredField("duplicates");
        } catch (NoSuchFieldException e) {
            GTMod.logger.info("Trying to access Advanced recipes has failed : (");
        } catch (SecurityException e) {
            GTMod.logger.info("AdvRecipeBase security deployed");
        }
        if (duplicatesf != null) {
            duplicatesf.setAccessible(true);
        }
        try {
            if (duplicatesf != null) {
                ((Set<ResourceLocation>) duplicatesf.get(duplicatesf)).remove(new ResourceLocation(modid, recipe.getRecipeID()));
            }
        } catch (IllegalArgumentException e) {
            GTMod.logger.info("Accessed AdvRecipeBase class but field getter failed");
        } catch (IllegalAccessException e) {
            GTMod.logger.info("Accessed AdvRecipeBase class but access denied");
        }
        return AdvRecipeBase.registerRecipe(recipe);
    }

    @SuppressWarnings("unchecked")
    public static void removeRecipe(String modid, String recipeId) {
        ((ForgeRegistry) ForgeRegistries.RECIPES).remove(new ResourceLocation(modid, recipeId));
        Field duplicatesf = null;
        try {
            duplicatesf = AdvRecipeBase.class.getDeclaredField("duplicates");
        } catch (NoSuchFieldException e) {
            GTMod.logger.info("Trying to access Advanced recipes has failed : (");
        } catch (SecurityException e) {
            GTMod.logger.info("AdvRecipeBase security deployed");
        }
        if (duplicatesf != null) {
            duplicatesf.setAccessible(true);
        }
        try {
            if (duplicatesf != null) {
                ((Set<ResourceLocation>) duplicatesf.get(duplicatesf)).remove(new ResourceLocation(modid, recipeId));
            }
        } catch (IllegalArgumentException e) {
            GTMod.logger.info("Accessed AdvRecipeBase class but field getter failed");
        } catch (IllegalAccessException e) {
            GTMod.logger.info("Accessed AdvRecipeBase class but access denied");
        }
    }
}
