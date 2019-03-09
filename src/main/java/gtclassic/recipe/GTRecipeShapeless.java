package gtclassic.recipe;

import gtclassic.GTBlocks;
import gtclassic.GTItems;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.core.platform.registry.Ic2Items;

public class GTRecipeShapeless {

	static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;
	static GTMaterialGen GT;
	static GTMaterial M;

	public static void recipeShapeless1() {

		// LV item storage

		/*
		 * recipes.addShapelessRecipe(GT.get(GTBlocks.smallChestLV, 1), new Object[] {
		 * "casingMachineIron", "chestWood" });
		 * 
		 * recipes.addShapelessRecipe(GT.get(GTBlocks.largeChestLV, 1), new Object[] {
		 * GTBlocks.smallChestLV, GTBlocks.smallChestLV });
		 * 
		 * recipes.addShapelessRecipe(GT.get(GTBlocks.largeChestLV, 1), new Object[] {
		 * "chestWood", "chestWood", "casingMachineIron", "casingMachineIron" });
		 * 
		 * recipes.addShapelessRecipe(GT.get(GTBlocks.bookShelfLV, 1), new Object[] {
		 * "casingMachineIron", Items.BOOK, Items.BOOK, Items.BOOK });
		 * 
		 * recipes.addShapelessRecipe(GT.get(GTBlocks.workBenchLV, 1), new Object[] {
		 * "casingMachineIron", "workbench" });
		 * 
		 * // MV item storage
		 * 
		 * recipes.addShapelessRecipe(GT.get(GTBlocks.smallChestMV, 1), new Object[] {
		 * "casingMachineAluminium", "chestWood" });
		 * 
		 * recipes.addShapelessRecipe(GT.get(GTBlocks.largeChestMV, 1), new Object[] {
		 * GTBlocks.smallChestMV, GTBlocks.smallChestMV });
		 * 
		 * recipes.addShapelessRecipe(GT.get(GTBlocks.largeChestMV, 1), new Object[] {
		 * "chestWood", "chestWood", "casingMachineAluminium", "casingMachineAluminium"
		 * });
		 * 
		 * recipes.addShapelessRecipe(GT.get(GTBlocks.bookShelfMV, 1), new Object[] {
		 * "casingMachineAluminium", Items.BOOK, Items.BOOK, Items.BOOK });
		 * 
		 * recipes.addShapelessRecipe(GT.get(GTBlocks.workBenchMV, 1), new Object[] {
		 * "casingMachineAluminium", "workbench" });
		 */

		// Duct Tape

		recipes.addShapelessRecipe(GT.get(GTBlocks.DuctTape, 1), new Object[] { GT.getIc2(Ic2Items.rubber, 64),
				GT.getIc2(Ic2Items.rubber, 64), GT.getIc2(Ic2Items.rubber, 64), GT.getIc2(Ic2Items.rubber, 64) });

		// Filing down GregTech crystals into Ic2 ones

		recipes.addShapelessRecipe(Ic2Items.energyCrystal.copy(),
				new Object[] { "craftingToolFile", GTBlocks.tinyEnergium });

		recipes.addShapelessRecipe(Ic2Items.lapotronCrystal,
				new Object[] { "craftingToolFile", GTBlocks.tinyLapotron });

		// Converting Oxygen back to empty tubes

		recipes.addShapelessRecipe(GT.get(GTItems.glassTube, 1), new Object[] { GT.getChemical(M.Oxygen, 1) });

		// Dust Recipes
		recipes.addShapelessRecipe(GT.getDust(M.Invar, 2), new Object[] { "dustIron", "dustIron", "dustNickel" });
		recipes.addShapelessRecipe(GT.getIc2(Ic2Items.bronzeDust, 1),
				new Object[] { "dustSmallCopper", "dustSmallCopper", "dustSmallCopper", "dustSmallTin" });
		recipes.addShapelessRecipe(GT.getDust(M.Electrum, 2), new Object[] { "dustGold", "dustSilver" });
		recipes.addShapelessRecipe(GT.getDust(M.Electrum, 1),
				new Object[] { "dustSmallGold", "dustSmallGold", "dustSmallSilver", "dustSmallSilver" });
		recipes.addShapelessRecipe(GT.getDust(M.Constantan, 2), new Object[] { "dustCopper", "dustNickel" });
		recipes.addShapelessRecipe(GT.getDust(M.Constantan, 1),
				new Object[] { "dustSmallCopper", "dustSmallCopper", "dustSmallNickel", "dustSmallNickel" });

		recipes.addShapelessRecipe(GT.getSmallDust(M.Nichrome, 15),
				new Object[] { "dustNickel", "dustNickel", "dustNickel", "dustNickel", "dustChrome" });

		// In world process, recipe equivalents

		recipes.addShapelessRecipe(GT.getCasing(M.RefinedIron, 1),
				new Object[] { "craftingToolFile", Ic2Items.machine.copy() });

		recipes.addShapelessRecipe(GT.get(GTBlocks.slagSand),
				new Object[] { "sand", "dustSlag", "dustSlag", "dustSlag" });

	}

}
