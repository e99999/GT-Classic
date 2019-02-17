package gtclassic.recipe;

import gtclassic.GTBlocks;
import gtclassic.GTItems;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GTRecipeShapeless {

	static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;
	static GTMaterialGen GT;
	static GTMaterial M;

	public static void recipeShapeless1() {

		// LV item storage

		recipes.addShapelessRecipe(new ItemStack(GTBlocks.smallChestLV, 1),
				new Object[] { "casingMachineSteel", "chestWood" });

		recipes.addShapelessRecipe(new ItemStack(GTBlocks.largeChestLV, 1),
				new Object[] { GTBlocks.smallChestLV, GTBlocks.smallChestLV });

		recipes.addShapelessRecipe(new ItemStack(GTBlocks.largeChestLV, 1),
				new Object[] { "chestWood", "chestWood", "casingMachineSteel", "casingMachineSteel" });

		recipes.addShapelessRecipe(new ItemStack(GTBlocks.bookShelfLV, 1),
				new Object[] { "casingMachineSteel", Items.BOOK, Items.BOOK, Items.BOOK });

		recipes.addShapelessRecipe(new ItemStack(GTBlocks.workBenchLV, 1),
				new Object[] { "casingMachineSteel", "workbench" });

		// MV item storage

		recipes.addShapelessRecipe(new ItemStack(GTBlocks.smallChestMV, 1),
				new Object[] { "casingMachineAluminium", "chestWood" });

		recipes.addShapelessRecipe(new ItemStack(GTBlocks.largeChestMV, 1),
				new Object[] { GTBlocks.smallChestMV, GTBlocks.smallChestMV });

		recipes.addShapelessRecipe(new ItemStack(GTBlocks.largeChestMV, 1),
				new Object[] { "chestWood", "chestWood", "casingMachineAluminium", "casingMachineAluminium" });

		recipes.addShapelessRecipe(new ItemStack(GTBlocks.bookShelfMV, 1),
				new Object[] { "casingMachineAluminium", Items.BOOK, Items.BOOK, Items.BOOK });

		recipes.addShapelessRecipe(new ItemStack(GTBlocks.workBenchMV, 1),
				new Object[] { "casingMachineAluminium", "workbench" });

		// Duct Tape

		recipes.addShapelessRecipe(new ItemStack(GTItems.braintechAerospaceARDT, 1),
				new Object[] { GT.getIc2(Ic2Items.rubber, 64), GT.getIc2(Ic2Items.rubber, 64),
						GT.getIc2(Ic2Items.rubber, 64), GT.getIc2(Ic2Items.rubber, 64) });

		// Filing down GregTech crystals into Ic2 ones

		recipes.addShapelessRecipe(Ic2Items.energyCrystal.copy(),
				new Object[] { "craftingToolFile", GTBlocks.tinyEnergium });

		recipes.addShapelessRecipe(Ic2Items.lapotronCrystal,
				new Object[] { "craftingToolFile", GTBlocks.tinyLapotron });

		// Converting Oxygen back to empty tubes

		recipes.addShapelessRecipe(new ItemStack(GTItems.glassTube, 1), new Object[] { GT.getChemical(M.Oxygen, 1) });

	}

}
