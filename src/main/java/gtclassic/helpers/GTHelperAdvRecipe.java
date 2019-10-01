package gtclassic.helpers;

import gtclassic.GTMod;
import ic2.core.item.recipe.AdvRecipe;
import ic2.core.item.recipe.AdvRecipeBase;
import ic2.core.item.recipe.AdvShapelessRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

import java.lang.reflect.Field;
import java.util.Set;

public class GTHelperAdvRecipe {
    @SuppressWarnings("unchecked")
    public void overrideGTRecipe(String modid, String recipeId, ItemStack output, Object... input) {
        Loader loader = Loader.instance();
        ModContainer old = loader.activeModContainer();
        loader.setActiveModContainer(loader.getIndexedModList().get(modid));
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
        AdvRecipe.overrideAndGet(recipeId, output, input);
        loader.setActiveModContainer(old);
    }

    @SuppressWarnings("unchecked")
    public void overrideShapelessGTRecipe(String modid, String recipeId, ItemStack output, Object... input) {
        Loader loader = Loader.instance();
        ModContainer old = loader.activeModContainer();
        loader.setActiveModContainer(loader.getIndexedModList().get(modid));
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
        AdvShapelessRecipe.overrideAndGet(recipeId, output, input);
        loader.setActiveModContainer(old);
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
