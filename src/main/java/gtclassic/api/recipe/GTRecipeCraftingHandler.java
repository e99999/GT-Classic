package gtclassic.api.recipe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import gtclassic.GTMod;
import gtclassic.api.helpers.GTValues;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.common.GTConfig;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.recipe.IRecipeInput;
import ic2.core.IC2;
import ic2.core.item.recipe.AdvRecipe;
import ic2.core.item.recipe.AdvRecipeBase;
import ic2.core.item.recipe.AdvShapelessRecipe;
import ic2.core.item.recipe.entry.RecipeInputCombined;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.misc.StackUtil;
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

	/**
	 * A Utility Method by Trinsdar for automatically constructing nuclear fuel rod
	 * recipes.
	 * 
	 * @param single       - Single Rod ItemStack
	 * @param dual         - Dual Rod ItemStack
	 * @param quad         - Quad Rod ItemStack
	 * @param isotope      - Isotopic Rod ItemStack
	 * @param reEnriched   - ReEnchrinched Rod ItemStack
	 * @param nearDepleted - Near Depleated Rod ItemStack
	 * @param ingredient   - Material to craft basic rod ItemStack
	 */
	public static void rodUtil(ItemStack single, ItemStack dual, ItemStack quad, ItemStack isotope,
			ItemStack reEnriched, ItemStack nearDepleted, ItemStack ingredient) {
		ItemStack emptyRod = getEmptyRod();
		IRecipeInput coal = GTRecipeCraftingHandler.combineRecipeObjects("dustCoal", "dustCharcoal", "dustCarbon");
		/** re enriched to single **/
		ClassicRecipes.advCrafting.addShapelessRecipe(single, coal, reEnriched);
		/** near depleted **/
		ClassicRecipes.advCrafting.addRecipe(StackUtil.copyWithSize(nearDepleted, 8), "RRR", "RIR", "RRR", 'R', emptyRod, 'I', ingredient);
		if (!Loader.isModLoaded(GTValues.MOD_ID_GTCX)) {
			/** single to dual **/
			ClassicRecipes.advCrafting.addRecipe(dual, "RPR", 'R', single, 'P', Ic2Items.denseCopperPlate);
			/** dual to quad **/
			ClassicRecipes.advCrafting.addRecipe(quad, " R ", "PPP", " R ", 'R', dual, 'P', Ic2Items.denseCopperPlate);
			/** single to quad **/
			ClassicRecipes.advCrafting.addRecipe(quad, "RPR", "PPP", "RPR", 'R', single, 'P', Ic2Items.denseCopperPlate);
		}
		/** near depleted to isotope **/
		ClassicRecipes.advCrafting.addShapelessRecipe(isotope, nearDepleted, coal);
		if (!IC2.config.getFlag("HardEnrichedUran")) {
			/** single **/
			ClassicRecipes.advCrafting.addShapelessRecipe(single, emptyRod, ingredient);
			ClassicRecipes.canningMachine.registerCannerItem(emptyRod, new RecipeInputItemStack(ingredient), single);
		}
	}

	static ItemStack getEmptyRod() {
		return GTConfig.modcompat.compatIc2Extras && Loader.isModLoaded(GTValues.MOD_ID_IC2_EXTRAS)
				? GTMaterialGen.getModItem(GTValues.MOD_ID_IC2_EXTRAS, "emptyfuelrod")
				: Ic2Items.emptyCell.copy();
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
			}
		}
		IRecipeInput[] parsedFinal = new IRecipeInput[entries.length];
		parsedEntries.toArray(parsedFinal);
		return new RecipeInputCombined(1, parsedFinal);
	}
}
