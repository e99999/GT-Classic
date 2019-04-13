package gtclassic.recipe;

import gtclassic.GTBlocks;
import gtclassic.GTItems;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.util.GTValues;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GTRecipeShapeless {

	static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;
	static GTMaterialGen GT;
	static GTMaterial M;

	public static void recipeShapeless1() {

		recipes.addShapelessRecipe(GT.get(GTItems.magnifyingGlass), new Object[] { "paneGlass", "stickIron" });

		recipes.addShapelessRecipe(GT.get(GTBlocks.plastic4CasingBlock, 4), new Object[] { GTBlocks.plastic1CasingBlock,
				GTBlocks.plastic1CasingBlock, GTBlocks.plastic1CasingBlock, GTBlocks.plastic1CasingBlock });

		recipes.addShapelessRecipe(GT.get(GTBlocks.plastic16CasingBlock, 4),
				new Object[] { GTBlocks.plastic4CasingBlock, GTBlocks.plastic4CasingBlock, GTBlocks.plastic4CasingBlock,
						GTBlocks.plastic4CasingBlock });

		// Duct Tape

		recipes.addShapelessRecipe(GT.get(GTBlocks.DuctTape, 1), new Object[] { GT.getIc2(Ic2Items.rubber, 64),
				GT.getIc2(Ic2Items.rubber, 64), GT.getIc2(Ic2Items.rubber, 64), GT.getIc2(Ic2Items.rubber, 64) });

		// Dust Recipes
		recipes.addShapelessRecipe(GT.getDust(M.Invar, 3), new Object[] { "dustIron", "dustIron", "dustNickel" });

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

		recipes.addShapelessRecipe(GT.getDust(M.BismuthBronze, 4),
				new Object[] { "dustBismuth", "dustBrass", "dustBrass", "dustBrass" });
		recipes.addShapelessRecipe(GT.getDust(M.BismuthBronze, 1),
				new Object[] { "dustSmallBismuth", "dustSmallBrass", "dustSmallBrass", "dustSmallBrass" });

		recipes.addShapelessRecipe(GT.getDust(M.StainlessSteel, 9), new Object[] { "dustIron", "dustIron", "dustIron",
				"dustIron", "dustIron", "dustIron", "dustNickel", "dustManganese", "dustChrome" });
		recipes.addShapelessRecipe(GT.getDust(M.StainlessSteel, 9), new Object[] { "dustIron", "dustIron", "dustIron",
				"dustIron", "dustIron", "dustIron", "dustNickel", "dustNiobium", "dustChrome" });
		recipes.addShapelessRecipe(GT.getDust(M.StainlessSteel, 9), new Object[] { "dustIron", "dustIron", "dustIron",
				"dustIron", "dustInvar", "dustInvar", "dustInvar", "dustManganese", "dustChrome" });
		recipes.addShapelessRecipe(GT.getDust(M.StainlessSteel, 9), new Object[] { "dustIron", "dustIron", "dustIron",
				"dustIron", "dustInvar", "dustInvar", "dustInvar", "dustNiobium", "dustChrome" });
		recipes.addShapelessRecipe(GT.getDust(M.StainlessSteel, 1),
				new Object[] { "dustSmallIron", "dustSmallIron", "dustSmallIron", "dustSmallIron", "dustSmallIron",
						"dustSmallIron", "dustSmallNickel", "dustSmallManganese", "dustSmallChrome" });
		recipes.addShapelessRecipe(GT.getDust(M.StainlessSteel, 1),
				new Object[] { "dustSmallIron", "dustSmallIron", "dustSmallIron", "dustSmallIron", "dustSmallIron",
						"dustSmallIron", "dustSmallNickel", "dustSmallNiobium", "dustSmallChrome" });
		recipes.addShapelessRecipe(GT.getDust(M.StainlessSteel, 1),
				new Object[] { "dustSmallIron", "dustSmallIron", "dustSmallIron", "dustSmallIron", "dustSmallInvar",
						"dustSmallInvar", "dustSmallInvar", "dustSmallManganese", "dustSmallChrome" });
		recipes.addShapelessRecipe(GT.getDust(M.StainlessSteel, 1),
				new Object[] { "dustSmallIron", "dustSmallIron", "dustSmallIron", "dustSmallIron", "dustSmallInvar",
						"dustSmallInvar", "dustSmallInvar", "dustSmallNiobium", "dustSmallChrome" });

		recipes.addShapelessRecipe(GT.getDust(M.Ultimet, 9), new Object[] { "dustCobalt", "dustCobalt", "dustCobalt",
				"dustCobalt", "dustCobalt", "dustChrome", "dustChrome", "dustNickel", "dustMolybdenum" });
		recipes.addShapelessRecipe(GT.getDust(M.Ultimet, 1),
				new Object[] { "dustSmallCobalt", "dustSmallCobalt", "dustSmallCobalt", "dustSmallCobalt",
						"dustSmallCobalt", "dustSmallChrome", "dustSmallChrome", "dustSmallNickel",
						"dustSmallMolybdenum" });

		// In world process, recipe equivalents

		recipes.addShapelessRecipe(GT.getCasing(M.RefinedIron, 1),
				new Object[] { "craftingToolFile", Ic2Items.machine.copy() });

		recipes.addShapelessRecipe(GT.get(GTBlocks.slagSand),
				new Object[] { "sand", "dustSlag", "dustSlag", "dustSlag" });

		// random stuff
		recipes.addShapelessRecipe(GT.get(Items.IRON_INGOT), new Object[] { "ingotRefinedIron", "dustAsh" });

		recipes.addShapelessRecipe(GT.getIc2(Ic2Items.fertilizer, 1),
				new Object[] { "dustSulfur", "dustAsh", "dustCalcite" });

		recipes.addShapelessRecipe(GT.getIc2(Ic2Items.fertilizer, 2),
				new Object[] { "dustSulfur", "dustDarkAshes", "dustCalcite" });

		recipes.addShapelessRecipe(GT.getIc2(Ic2Items.constructionFoam, 3),
				new Object[] { "dustClay", GTValues.water, "dustAsh", "dustCoal" });

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
