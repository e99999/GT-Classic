package gtclassic.api.recipe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import gtclassic.GTMod;
import ic2.api.recipe.IRecipeInput;
import ic2.core.item.recipe.AdvRecipe;
import ic2.core.item.recipe.AdvRecipeBase;
import ic2.core.item.recipe.AdvShapelessRecipe;
import ic2.core.item.recipe.entry.RecipeInputCombined;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

public class GTRecipeCraftingHandler {

	@SuppressWarnings("unchecked")
	public static void overrideGTRecipe(String modid, String recipeId, ItemStack output, Object... input) {
		Loader loader = Loader.instance();
		ModContainer old = loader.activeModContainer();
		loader.setActiveModContainer(loader.getIndexedModList().get(modid));
		((ForgeRegistry<?>) ForgeRegistries.RECIPES).remove(new ResourceLocation(modid, recipeId));
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
	public static void overrideShapelessGTRecipe(String modid, String recipeId, ItemStack output, Object... input) {
		Loader loader = Loader.instance();
		ModContainer old = loader.activeModContainer();
		loader.setActiveModContainer(loader.getIndexedModList().get(modid));
		((ForgeRegistry<?>) ForgeRegistries.RECIPES).remove(new ResourceLocation(modid, recipeId));
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
		((ForgeRegistry<?>) ForgeRegistries.RECIPES).remove(new ResourceLocation(modid, recipeId));
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

	public static IRecipeInput combineRecipeObjects(Object... entries) {
		List<IRecipeInput> parsedEntries = new ArrayList<>();
		for (Object object : entries) {
			if (object instanceof String) {
				parsedEntries.add(new RecipeInputOreDict((String) object));
			}
			if (object instanceof Block) {
				parsedEntries.add(new RecipeInputItemStack(new ItemStack((Block) object)));
			}
			if (object instanceof Item) {
				parsedEntries.add(new RecipeInputItemStack(new ItemStack((Item) object)));
			}
			if (object instanceof ItemStack) {
				parsedEntries.add(new RecipeInputItemStack(((ItemStack) object).copy()));
			}
			if (object instanceof IRecipeInput) {
				parsedEntries.add((IRecipeInput) object);
			} else {
				GTMod.logger.info("Oi bruv you avin a laugh bruv? Are you fuckin mental m8? You shovin a random object in my var args m8, you fuckin wanker");
			}
		}
		IRecipeInput[] parsedFinal = new IRecipeInput[entries.length];
		parsedEntries.toArray(parsedFinal);
		return new RecipeInputCombined(1, parsedFinal);
	}
}
