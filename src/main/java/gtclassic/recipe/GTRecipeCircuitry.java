package gtclassic.recipe;

import gtclassic.GTBlocks;
import gtclassic.GTItems;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.util.GTValues;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.api.recipe.IRecipeInput;
import ic2.core.item.recipe.entry.RecipeInputCombined;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.init.Items;

public class GTRecipeCircuitry {

	static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;
	static GTMaterialGen GT;
	static GTMaterial M;

	public static IRecipeInput basicCircuitPlate = new RecipeInputCombined(1,
			new IRecipeInput[] { new RecipeInputOreDict("plateSilicon"), new RecipeInputOreDict("plateGermanium") });

	public static void recipesCircutry() {

		/*
		 * Many of the recipes are subject to change as the list of machines populates.
		 * For now they allow testing and progression to happen.
		 */

		// recipes.addRecipe(GT.get(GTBlocks.tinyEnergium, 1),
		// new Object[] { "RRR", "RDR", "RRR", 'D', "gemRuby", 'R', "dustRedstone" });

		// recipes.addRecipe(GT.get(GTBlocks.tinyLapotron, 1),
		// new Object[] { "LCL", "LDL", "LCL", 'D', "gemSapphire", 'C', "circuitBasic",
		// 'L', "gemLapis" });

		// recipes.addRecipe(GT.get(GTBlocks.tinyLapotron, 1),
		// new Object[] { "LCL", "LDL", "LCL", 'D', "gemSapphire", 'C', "circuitBasic",
		// 'L', "dustLazurite" });

		recipes.addShapelessRecipe(GT.get(GTItems.magnifyingGlass), new Object[] { "paneGlass", "stickIron" });

		recipes.addRecipe(GT.get(GTItems.ironKnife, 1), new Object[] { "PPS", 'P', "plateIron", 'S', "stickWood" });

		recipes.addShapelessRecipe(GT.get(GTBlocks.resinBottle),
				new Object[] { Items.GLASS_BOTTLE, GT.getIc2(Ic2Items.stickyResin, 1), GTValues.water });

		recipes.addShapelessRecipe(GT.get(GTItems.resinPCB), new Object[] { GTItems.ironKnife, GTBlocks.resinBoard });
		recipes.addShapelessRecipe(GT.get(GTItems.germaniumSubstrate),
				new Object[] { GTItems.ironKnife, "plateGermanium" });

		recipes.addRecipe(GT.get(GTBlocks.blastFurnace, 1), new Object[] { "PCP", "PFP", "PCP", 'P', "plateRefinedIron",
				'B', Ic2Items.battery.copy(), 'F', Ic2Items.ironFurnace.copy(), 'C', GTBlocks.copperCoilBlock });

		recipes.addRecipe(GT.get(GTItems.basicTransistor, 1),
				new Object[] { "WPW", 'W', GT.getIc2(Ic2Items.tinCable, 1), 'P', GTItems.germaniumSubstrate });

		recipes.addShapelessRecipe(GT.get(GTItems.basicCapacitor),
				new Object[] { "dustRedstone", "plateManganese", "stickTantalum", "dustTantalum" });

		recipes.overrideRecipe("shaped_Electronic Circuit", GT.getIc2(Ic2Items.electricCircuit, 1), "WWW", "TSC", "WWW",
				'T', GTItems.basicTransistor, 'C', GTItems.basicCapacitor, 'W', Ic2Items.copperCable.copy(), 'S',
				GTItems.resinPCB);

		recipes.overrideRecipe("shaped_Electronic Circuit_1", GT.getIc2(Ic2Items.electricCircuit, 1), "WWW", "CST",
				"WWW", 'T', GTItems.basicTransistor, 'C', GTItems.basicCapacitor, 'W', Ic2Items.copperCable.copy(), 'S',
				GTItems.resinPCB);

		recipes.overrideRecipe("shaped_RE-Battery", GT.getIc2(Ic2Items.battery, 1), " C ", "TRT", "TRT", 'T',
				"ingotTin", 'R', "dustRedstone", 'C', Ic2Items.copperCable.copy());

	}

}
