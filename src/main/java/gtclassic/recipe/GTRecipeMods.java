package gtclassic.recipe;

import gtclassic.GTItems;
import gtclassic.GTMod;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileCentrifuge;
import gtclassic.tile.multi.GTTileMultiBlastFurnace;
import gtclassic.util.GTValues;
import ic2.api.recipe.IRecipeInput;
import ic2.core.item.recipe.entry.RecipeInputCombined;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

public class GTRecipeMods {

	public static void postInit() {
		/** Forestry Sub Module **/
		if (Loader.isModLoaded(GTValues.FORESTRY)) {
			GTMod.logger.info("OH GOD..... NOT THE BEES!");
			GTMod.logger.info("Haha JK, let me get those centrifuge recipes");
			GTRecipeForestry.notTheBees();
		}
		/** Draconic Evolution **/
		if (Loader.isModLoaded(GTValues.DRACONIC)) {
			GTMod.logger.info("Adding Draconium to Gregtech Blast Furnace...");
			GTRecipeProcessing.removeSmelting(GTMaterialGen.getModItem(GTValues.DRACONIC, "draconium_ingot"));
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] {
					input("dustDraconium", 1) }, GTTileMultiBlastFurnace.COST_HIGH, GTMaterialGen.getModItem(GTValues.DRACONIC, "draconium_ingot"));
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] {
					input("oreDraconium", 1) }, GTTileMultiBlastFurnace.COST_HIGH, GTMaterialGen.getModItem(GTValues.DRACONIC, "draconium_ingot"));
		}
		/** Thermal Mods **/
		if (Loader.isModLoaded(GTValues.THERMAL)) {
			GTMod.logger.info("Adding Thermal alloys to the blast furnace");
			// Invar
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { metal("Iron", 2),
					metal("Nickel", 1) }, GTTileMultiBlastFurnace.COST_TINY, GTMaterialGen.getModMetaItem(GTValues.THERMAL, "material", 162, 3));
			// Constantan
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { metal("Copper", 1),
					metal("Nickel", 1) }, GTTileMultiBlastFurnace.COST_TINY, GTMaterialGen.getModMetaItem(GTValues.THERMAL, "material", 164, 2));
			// Signalum
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { metal("Copper", 3), metal("Silver", 1),
					input("dustRedstone", 9) }, GTTileMultiBlastFurnace.COST_SMALL, GTMaterialGen.getModMetaItem(GTValues.THERMAL, "material", 165, 4));
			// Lumium
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { metal("Tin", 3), metal("Silver", 1),
					input("dustGlowstone", 4) }, GTTileMultiBlastFurnace.COST_MED, GTMaterialGen.getModMetaItem(GTValues.THERMAL, "material", 166, 4));
			// Enderium
			GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { metal("Lead", 3), metal("Platinum", 1),
					input("dustEnderPearl", 4) }, GTTileMultiBlastFurnace.COST_MED, GTMaterialGen.getModMetaItem(GTValues.THERMAL, "material", 167, 4));
		}
		/** Immersive Engineering **/
		if (Loader.isModLoaded(GTValues.IMMERSIVE_ENGINEERING)) {
			GTMod.logger.info("Doing a few things with IE");
			GTTileCentrifuge.addRecipe("dustCoke", 8, 0, GTTileCentrifuge.totalEu(7500), GTMaterialGen.getModMetaItem(GTValues.IMMERSIVE_ENGINEERING, "material", 18, 1));
			// Adds alloys if thermal is not present
			if (!Loader.isModLoaded(GTValues.THERMAL)) {
				// Constantan
				GTTileMultiBlastFurnace.addRecipe(new IRecipeInput[] { metal("Copper", 1),
						metal("Nickel", 1) }, GTTileMultiBlastFurnace.COST_TINY, GTMaterialGen.getModMetaItem(GTValues.IMMERSIVE_ENGINEERING, "metal", 6, 2));
			}
		}
	}

	public static IRecipeInput input(ItemStack stack) {
		return new RecipeInputItemStack(stack);
	}

	public static IRecipeInput input(String name, int amount) {
		return new RecipeInputOreDict(name, amount);
	}

	public static IRecipeInput tubes(int amount) {
		return new RecipeInputItemStack(GTMaterialGen.get(GTItems.testTube, amount));
	}

	public static IRecipeInput metal(String type, int amount) {
		return new RecipeInputCombined(amount, new IRecipeInput[] { new RecipeInputOreDict("ingot" + type, amount),
				new RecipeInputOreDict("dust" + type, amount), });
	}
}
