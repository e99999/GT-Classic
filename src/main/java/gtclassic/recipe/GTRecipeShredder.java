package gtclassic.recipe;

import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileShredder;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

public class GTRecipeShredder {

	static GTMaterialGen GT;
	static GTMaterial M;
	static GTTileShredder Shredder;

	public static void recipeShredder() {

		if (!Loader.isModLoaded("ic2c_extras")) {
			/*
			 * This is for Trinsdar so he can use his own crushed ores in this without
			 * removing all my recipes one by one. Cause I am such a nice guy : )
			 */
			Shredder.addGrinderRecipe("oreBauxite", 1, GT.getDust(M.Bauxite, 4), GT.getDust(M.Alumina, 1));

			Shredder.addGrinderRecipe("oreChromite", 1, GT.getDust(M.Chromite, 2), GT.getSmallDust(M.Chrome, 1),
					GT.getSmallDust(M.Iron, 1));

			Shredder.addGrinderRecipe("oreGalena", 1, GT.getDust(M.Galena, 2), GT.getSmallDust(M.Silver, 2));

			Shredder.addGrinderRecipe("oreGarnierite", 1, GT.getDust(M.Garnierite, 2), GT.getSmallDust(M.Platinum, 1),
					GT.getSmallDust(M.Copper, 1));

			Shredder.addGrinderRecipe("oreLimonite", 1, GT.getDust(M.Limonite, 2), GT.getSmallDust(M.Iron, 2));

			Shredder.addGrinderRecipe("oreMalachite", 1, GT.getDust(M.Malachite, 2), GT.getDust(M.Calcite, 1),
					GT.getSmallDust(M.Copper, 2));

			Shredder.addGrinderRecipe("orePyrite", 1, GT.getDust(M.Pyrite, 2), GT.getSmallDust(M.Iron, 2));

			Shredder.addGrinderRecipe("oreMagnetite", 1, GT.getDust(M.Magnetite, 2), GT.getSmallDust(M.Iron, 1),
					GT.getSmallDust(M.Gold, 1));

			Shredder.addGrinderRecipe("oreSheldonite", 1, GT.getDust(M.Sheldonite, 2), GT.getSmallDust(M.Nickel, 1),
					GT.getSmallDust(M.Platinum, 1));

			Shredder.addGrinderRecipe("oreSphalerite", 1, GT.getDust(M.Sphalerite, 2), GT.getDust(M.Zinc, 1),
					GT.getSmallDust(M.GarnetYellow, 1));

			Shredder.addGrinderRecipe("oreTantalite", 1, GT.getDust(M.Tantalite, 2), GT.getSmallDust(M.Niobium, 2),
					GT.getSmallDust(M.Tantalum, 1));

			Shredder.addGrinderRecipe("oreTetrahedrite", 1, GT.getDust(M.Tetrahedrite, 2), GT.getSmallDust(M.Copper, 2),
					GT.getSmallDust(M.Antimony, 1));

			Shredder.addGrinderRecipe("oreTungstate", 1, GT.getDust(M.Tungstate, 2), GT.getSmallDust(M.Iron, 3),
					GT.getSmallDust(M.Manganese, 3));

			Shredder.addGrinderRecipe("orePyrolusite", 1, GT.getDust(M.Pyrolusite, 2), GT.getSmallDust(M.Manganese, 2));

			Shredder.addGrinderRecipe("oreMolybdenite", 1, GT.getDust(M.Molybdenite, 2),
					GT.getSmallDust(M.Molybdenum, 2));

			Shredder.addGrinderRecipe("oreIridium", 1, GT.getIc2(Ic2Items.iridiumOre, 2),
					GT.getSmallDust(M.Platinum, 2));

			Shredder.addGrinderRecipe("oreCassiterite", 1, GT.getDust(M.Cassiterite, 2), GT.getIc2(Ic2Items.tinDust, 1),
					GT.getSmallDust(M.Tantalum, 1));

			Shredder.addGrinderRecipe("oreBismuthtine", 1, GT.getDust(M.Bismuthtine, 2), GT.getSmallDust(M.Bismuth, 2),
					GT.getSmallDust(M.Antimony, 1));

			// and some vanilla ic2 ores

			Shredder.addGrinderRecipe("oreCopper", 1, GT.getIc2(Ic2Items.copperDust, 2), GT.getSmallDust(M.Gold, 1),
					GT.getSmallDust(M.Nickel, 1));

			Shredder.addGrinderRecipe("oreUranium", 1, GT.getDust(M.Uranium, 2), GT.getSmallDust(M.Uranium, 2));

			Shredder.addGrinderRecipe("oreIron", 1, GT.getIc2(Ic2Items.ironDust, 2), GT.getSmallDust(M.Iron, 1),
					GT.getSmallDust(M.Nickel, 1));

			Shredder.addGrinderRecipe("oreGold", 1, GT.getIc2(Ic2Items.goldDust, 2), GT.getSmallDust(M.Copper, 1),
					GT.getSmallDust(M.Nickel, 1));

			Shredder.addGrinderRecipe("oreTin", 1, GT.getIc2(Ic2Items.tinDust, 2), GT.getSmallDust(M.Iron, 1),
					GT.getSmallDust(M.Zinc, 1));

			Shredder.addGrinderRecipe("oreSilver", 1, GT.getIc2(Ic2Items.silverDust, 2), GT.getSmallDust(M.Lead, 2));

		}

		/*
		 * These recipes will be added regardless of Ic2 extras
		 */

		Shredder.addGrinderRecipe("netherrack", 16, GT.getIc2(Ic2Items.netherrackDust, 16),
				GT.get(Items.GOLD_NUGGET, 1), GT.getDust(M.Phosphorus, 1));

		Shredder.addGrinderRecipe("obsidian", 1, GT.getIc2(Ic2Items.obsidianDust, 2), GT.getSmallDust(M.Iron, 1),
				GT.getSmallDust(M.Magnesium, 1));

		Shredder.addGrinderRecipe("oreQuartz", 1, GT.get(Items.QUARTZ, 3), GT.getIc2(Ic2Items.netherrackDust, 1));

		Shredder.addGrinderRecipe("oreSaltpeter", 1, GT.getDust(M.Saltpeter, 4));

		Shredder.addGrinderRecipe("oreSalt", 1, GT.getDust(M.Salt, 4));

		Shredder.addGrinderRecipe("oreCalcite", 1, GT.getDust(M.Calcite, 3), GT.getDust(M.Phosphorus, 1));

		Shredder.addGrinderRecipe("oreCryolite", 1, GT.getDust(M.Cryolite, 4));

		Shredder.addGrinderRecipe("oreGraphite", 1, GT.getDust(M.Graphite, 2), GT.getSmallDust(M.Carbon, 1),
				GT.getSmallDust(M.Diamond, 1));

		Shredder.addGrinderRecipe("oreRuby", 1, GT.getGem(M.Ruby, 2), GT.getSmallDust(M.Ruby, 6),
				GT.getSmallDust(M.GarnetRed, 2));

		Shredder.addGrinderRecipe("oreSapphire", 1, GT.getGem(M.Sapphire, 2), GT.getSmallDust(M.Sapphire, 6),
				GT.getSmallDust(M.SapphireGreen, 2));

		Shredder.addGrinderRecipe("oreCoal", 1, GT.get(Items.COAL, 2), GT.getIc2(Ic2Items.coalDust, 1),
				GT.getSmallDust(M.Thorium, 1));

		Shredder.addGrinderRecipe("oreLapis", 1, new ItemStack(Items.DYE, 12, 4), GT.getDust(M.Lazurite, 3));

		Shredder.addGrinderRecipe("oreRedstone", 1, GT.get(Items.REDSTONE, 16), GT.getSmallDust(M.Glowstone, 2));

		Shredder.addGrinderRecipe("oreDiamond", 1, GT.get(Items.DIAMOND, 2), GT.getSmallDust(M.Diamond, 6),
				GT.getIc2(Ic2Items.hydratedCoalDust, 1));

		Shredder.addGrinderRecipe("oreEmerald", 1, GT.get(Items.EMERALD, 2), GT.getSmallDust(M.Emerald, 6),
				GT.getSmallDust(M.Olivine, 2));

		Shredder.addGrinderRecipe("oreCinnabar", 1, GT.getDust(M.Cinnabar, 5), GT.getSmallDust(M.Redstone, 2),
				GT.getSmallDust(M.Glowstone, 1));

		Shredder.addGrinderRecipe("oreOlivine", 1, GT.getGem(M.Olivine, 2), GT.getSmallDust(M.Olivine, 6),
				GT.getSmallDust(M.Emerald, 2));

		Shredder.addGrinderRecipe("oreSodalite", 1, GT.getDust(M.Lazurite, 12), GT.getDust(M.Alumina, 3));
	}

}
