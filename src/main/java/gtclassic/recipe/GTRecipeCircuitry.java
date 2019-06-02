package gtclassic.recipe;

import gtclassic.GTBlocks;
import gtclassic.GTItems;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.machine.low.TileEntityCompressor;
import ic2.core.block.machine.low.TileEntityMacerator;
import ic2.core.block.machine.low.TileEntitySawMill;
import ic2.core.item.recipe.entry.RecipeInputCombined;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.init.Items;

public class GTRecipeCircuitry {

	static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;

	public static void recipesCircutry() {

		/*
		 * Many of the recipes are subject to change as the list of machines populates.
		 * For now they allow testing and progression to happen.
		 */

		recipes.addShapelessRecipe(GTMaterialGen.getDust(GTMaterial.MagnesiaCarbon, 4), new Object[] { "dustMagnesium",
				"dustCarbon", "dustSilicon", "dustClay" });

		TileEntityCompressor.addRecipe("dustMagnesiaCarbon", 1, GTMaterialGen.get(GTItems.magnesiaBrick), 0);

		recipes.addShapelessRecipe(GTMaterialGen.get(GTBlocks.stoneMagnesia, 1), new Object[] { GTItems.magnesiaBrick,
				GTItems.magnesiaBrick, GTItems.magnesiaBrick, GTItems.magnesiaBrick });

		TileEntitySawMill.addRecipe(GTMaterialGen.get(GTBlocks.driedResin), GTMaterialGen.get(GTItems.resinPCB), 0.0F);
		TileEntitySawMill.addRecipe(GTMaterialGen.getPlate(GTMaterial.Silicon, 1), GTMaterialGen.getSmallPlate(GTMaterial.Silicon, 4), 0.0F);
		TileEntityCompressor.addRecipe("dustWood", 1, GTMaterialGen.get(GTItems.woodPlate));
		TileEntityMacerator.addRecipe(GTMaterialGen.getIc2(Ic2Items.stickyResin, 1), GTMaterialGen.getDust(GTMaterial.DirtyResin, 2));
		TileEntityMacerator.addRecipe("plankWood", 1, GTMaterialGen.getDust(GTMaterial.Wood, 2));
		TileEntityMacerator.addRecipe("logWood", 1, GTMaterialGen.getDust(GTMaterial.Wood, 8));

		String knife = "craftingToolKnife";

		recipes.addShapelessRecipe(GTMaterialGen.getDust(GTMaterial.DirtyResin, 1), new Object[] { "craftingToolKnife",
				GTMaterialGen.getIc2(Ic2Items.stickyResin, 1) });
		recipes.addShapelessRecipe(GTMaterialGen.get(GTItems.resinPCB), new Object[] { knife, GTBlocks.driedResin });

		recipes.addRecipe(GTMaterialGen.get(GTItems.basicTransistor, 1), new Object[] { "WPW", 'W', "wireFineAny", 'P',
				"plateSmallGermanium" });

		recipes.addRecipe(GTMaterialGen.get(GTItems.basicCapacitor, 1), new Object[] { "FPF", "L L", 'F',
				"foilTantalum", 'P', Items.PAPER, 'L', "wireFineAny" });

		recipes.addRecipe(GTMaterialGen.get(GTItems.basicCapacitor, 1), new Object[] { "FPF", "L L", 'F', "foilNiobium",
				'P', Items.PAPER, 'L', "wireFineAny" });

		recipes.addRecipe(GTMaterialGen.get(GTItems.basicCapacitor, 1), new Object[] { "FPF", "L L", 'F',
				"foilAluminium", 'P', Items.PAPER, 'L', "wireFineAny" });

		recipes.addRecipe(GTMaterialGen.get(GTItems.advancedTransistor, 1), new Object[] { " G ", "WPW", " G ", 'W',
				"wireFineAny", 'P', "plateSmallSilicon", 'G', "dustSmallGlowstone" });

		recipes.addRecipe(GTMaterialGen.get(GTItems.advancedCapacitor, 1), new Object[] { "LDL", "FPF", "LDL", 'F',
				"foilTantalum", 'P', "plateSmallSilicon", 'L', "wireFineAny", 'D', "dustSmallLazurite" });

		recipes.addRecipe(GTMaterialGen.get(GTItems.advancedCapacitor, 1), new Object[] { "LDL", "FPF", "LDL", 'F',
				"foilNiobium", 'P', "plateSmallSilicon", 'L', "wireFineAny", 'D', "dustSmallLazurite" });

		recipes.addRecipe(GTMaterialGen.get(GTItems.advancedCapacitor, 1), new Object[] { "LDL", "FPF", "LDL", 'F',
				"foilAluminium", 'P', "plateSmallSilicon", 'L', "wireFineAny", 'D', "dustSmallLazurite" });

		recipes.addRecipe(GTMaterialGen.get(GTItems.advancedCapacitor, 1), new Object[] { "LDL", "FPF", "LDL", 'F',
				"foilSilver", 'P', "plateSmallSilicon", 'L', "wireFineAny", 'D', "dustSmallLazurite" });

		// Gating basic circuits

		recipes.overrideRecipe("shaped_item.itemPartCircuit_1058514721", GTMaterialGen.getIc2(Ic2Items.electricCircuit, 1), "WWW", "TSC", "WWW", 'T', GTItems.basicTransistor, 'C', GTItems.basicCapacitor, 'W', "wireFineRedAlloy", 'S', "pcbAny");

		recipes.overrideRecipe("shaped_item.itemPartCircuit_1521116961", GTMaterialGen.getIc2(Ic2Items.electricCircuit, 1), "WWW", "CST", "WWW", 'T', GTItems.basicTransistor, 'C', GTItems.basicCapacitor, 'W', "wireFineRedAlloy", 'S', "pcbAny");

		// Temporary Adv Circuit Recipes

		recipes.overrideRecipe("shaped_item.itemPartCircuitAdv_-1948043137", GTMaterialGen.getIc2(Ic2Items.advancedCircuit, 1), "WWW", "TSC", "WWW", 'T', GTItems.advancedTransistor, 'C', GTItems.advancedCapacitor, 'W', "wireFinePlatinum", 'S', "pcbAdvanced");

		recipes.overrideRecipe("shaped_item.itemPartCircuitAdv_-205948801", GTMaterialGen.getIc2(Ic2Items.advancedCircuit, 1), "WWW", "CST", "WWW", 'T', GTItems.advancedTransistor, 'C', GTItems.advancedCapacitor, 'W', "wireFinePlatinum", 'S', "pcbAdvanced");

		recipes.overrideRecipe("shaped_item.itemBatRE_2077392104", GTMaterialGen.getIc2(Ic2Items.battery, 1), " C ", "TRT", "TRT", 'T', "ingotTin", 'R', "dustRedstone", 'C', Ic2Items.copperCable.copy());

		// stuff with lapotron and energium

		recipes.overrideRecipe("shaped_item.itemBatCrystal_-1564046631", GTMaterialGen.getIc2(Ic2Items.energyCrystal, 1), "F ", " C", 'F', "craftingToolFile", 'C', GTBlocks.batteryEnergiumTiny);

		TileEntitySawMill.addRecipe(GTMaterialGen.get(GTBlocks.batteryEnergiumTiny), GTMaterialGen.getIc2(Ic2Items.energyCrystal, 1), 0.1F);

		IRecipeInput lowCrystal = new RecipeInputCombined(1, new IRecipeInput[] { new RecipeInputOreDict("gemDiamond"),
				new RecipeInputOreDict("gemRuby") });

		recipes.addRecipe(GTMaterialGen.get(GTBlocks.batteryEnergiumTiny), new Object[] { "RRR", "RDR", "RRR", 'D',
				lowCrystal, 'R', "dustRedstone" });

		IRecipeInput highCrystal = new RecipeInputCombined(1, new IRecipeInput[] {
				new RecipeInputOreDict("gemSapphire"), new RecipeInputItemStack(Ic2Items.energyCrystal.copy()) });

		IRecipeInput lapis = new RecipeInputCombined(1, new IRecipeInput[] { new RecipeInputOreDict("gemLapis"),
				new RecipeInputOreDict("dustLazurite") });

		recipes.overrideRecipe("shaped_item.itemBatLamaCrystal_1330077638", GTMaterialGen.getIc2(Ic2Items.lapotronCrystal, 1), "F ", " C", 'F', "craftingToolFile", 'C', GTBlocks.batteryLapotronTiny);

		TileEntitySawMill.addRecipe(GTMaterialGen.get(GTBlocks.batteryLapotronTiny), GTMaterialGen.getIc2(Ic2Items.lapotronCrystal, 1), 0.1F);

		recipes.addRecipe(GTMaterialGen.get(GTBlocks.batteryLapotronTiny), new Object[] { "LCL", "LDL", "LCL", 'D',
				highCrystal, 'C', "circuitBasic", 'L', lapis });

		recipes.addRecipe(GTMaterialGen.get(GTBlocks.batteryLapotronSmall, 1), new Object[] { "LLL", "LPL", "LLL", 'L',
				GTBlocks.batteryLapotronTiny, 'P', "plateIridium" });

	}

}
