package gtclassic.recipe;

import gtclassic.GTBlocks;
import gtclassic.GTItems;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class GTRecipeShapeless {

	static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;
	static GTMaterialGen GT;
	static GTMaterial M;

	public static void recipeShapeless1() {

		recipes.addShapelessRecipe(GT.get(GTItems.magnifyingGlass), new Object[] { "paneGlass", "stickIron" });

		// Duct Tape

		recipes.addShapelessRecipe(GT.get(GTBlocks.DuctTape, 1), new Object[] { GT.getIc2(Ic2Items.rubber, 64),
				GT.getIc2(Ic2Items.rubber, 64), GT.getIc2(Ic2Items.rubber, 64), GT.getIc2(Ic2Items.rubber, 64) });

		// Converting Oxygen back to empty tubes

		recipes.addShapelessRecipe(GT.get(GTItems.glassTube, 1), new Object[] { GT.getChemical(M.Oxygen, 1) });

		// Dust Recipes
		recipes.addShapelessRecipe(GT.getDust(M.Invar, 2), new Object[] { "dustIron", "dustIron", "dustNickel" });
		recipes.addShapelessRecipe(GT.getIc2(Ic2Items.bronzeDust, 1),
				new Object[] { "dustSmallCopper", "dustSmallCopper", "dustSmallCopper", "dustSmallTin" });
		recipes.addShapelessRecipe(GT.getDust(M.Brass, 4),
				new Object[] { "dustCopper", "dustCopper", "dustCopper", "dustZinc" });
		recipes.addShapelessRecipe(GT.getDust(M.Brass, 1),
				new Object[] { "dustSmallCopper", "dustSmallCopper", "dustSmallCopper", "dustSmallZinc" });
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

		String g = "gravel";
		ItemStack s = GT.get(GTBlocks.slagSand);
		recipes.addShapelessRecipe(new ItemStack(Blocks.CONCRETE_POWDER, 16, 0),
				new Object[] { "dyeWhite", s, s, s, s, g, g, g, g });
		recipes.addShapelessRecipe(new ItemStack(Blocks.CONCRETE_POWDER, 16, 1),
				new Object[] { "dyeOrange", s, s, s, s, g, g, g, g });
		recipes.addShapelessRecipe(new ItemStack(Blocks.CONCRETE_POWDER, 16, 2),
				new Object[] { "dyeMagenta", s, s, s, s, g, g, g, g });
		recipes.addShapelessRecipe(new ItemStack(Blocks.CONCRETE_POWDER, 16, 3),
				new Object[] { "dyeLightBlue", s, s, s, s, g, g, g, g });
		recipes.addShapelessRecipe(new ItemStack(Blocks.CONCRETE_POWDER, 16, 4),
				new Object[] { "dyeYellow", s, s, s, s, g, g, g, g });
		recipes.addShapelessRecipe(new ItemStack(Blocks.CONCRETE_POWDER, 16, 5),
				new Object[] { "dyeLime", s, s, s, s, g, g, g, g });
		recipes.addShapelessRecipe(new ItemStack(Blocks.CONCRETE_POWDER, 16, 6),
				new Object[] { "dyePink", s, s, s, s, g, g, g, g });
		recipes.addShapelessRecipe(new ItemStack(Blocks.CONCRETE_POWDER, 16, 7),
				new Object[] { "dyeGray", s, s, s, s, g, g, g, g });
		recipes.addShapelessRecipe(new ItemStack(Blocks.CONCRETE_POWDER, 16, 8),
				new Object[] { "dyeLightGray", s, s, s, s, g, g, g, g });
		recipes.addShapelessRecipe(new ItemStack(Blocks.CONCRETE_POWDER, 16, 9),
				new Object[] { "dyeCyan", s, s, s, s, g, g, g, g });
		recipes.addShapelessRecipe(new ItemStack(Blocks.CONCRETE_POWDER, 16, 10),
				new Object[] { "dyePurple", s, s, s, s, g, g, g, g });
		recipes.addShapelessRecipe(new ItemStack(Blocks.CONCRETE_POWDER, 16, 11),
				new Object[] { "dyeBlue", s, s, s, s, g, g, g, g });
		recipes.addShapelessRecipe(new ItemStack(Blocks.CONCRETE_POWDER, 16, 12),
				new Object[] { "dyeBrown", s, s, s, s, g, g, g, g });
		recipes.addShapelessRecipe(new ItemStack(Blocks.CONCRETE_POWDER, 16, 13),
				new Object[] { "dyeGreen", s, s, s, s, g, g, g, g });
		recipes.addShapelessRecipe(new ItemStack(Blocks.CONCRETE_POWDER, 16, 14),
				new Object[] { "dyeRed", s, s, s, s, g, g, g, g });
		recipes.addShapelessRecipe(new ItemStack(Blocks.CONCRETE_POWDER, 16, 15),
				new Object[] { "dyeBlack", s, s, s, s, g, g, g, g });

	}

}
