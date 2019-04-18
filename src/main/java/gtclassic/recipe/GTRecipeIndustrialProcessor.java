package gtclassic.recipe;

import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileMultiIndustrialProcessor;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

public class GTRecipeIndustrialProcessor {

	static GTMaterialGen GT;
	static GTMaterial M;
	static GTTileMultiIndustrialProcessor Processor;

	public static void recipeIndustrialProcessor1() {

		if (!Loader.isModLoaded("ic2c_extras")) {
			/*
			 * This is for Trinsdar so he can use his own crushed ores in this without
			 * removing all my recipes one by one. Cause I am such a nice guy : )
			 */
			Processor.addGrinderRecipe("oreBauxite", 1, GT.getDust(M.Bauxite, 4), GT.getDust(M.Alumina, 1));

			Processor.addGrinderRecipe("oreChromite", 1, GT.getDust(M.Chromite, 2), GT.getSmallDust(M.Chrome, 1),
					GT.getSmallDust(M.Iron, 1));

			Processor.addGrinderRecipe("oreGalena", 1, GT.getDust(M.Galena, 2), GT.getSmallDust(M.Silver, 2));

			Processor.addGrinderRecipe("oreGarnierite", 1, GT.getDust(M.Garnierite, 2), GT.getSmallDust(M.Platinum, 1),
					GT.getSmallDust(M.Copper, 1));

			Processor.addGrinderRecipe("oreLimonite", 1, GT.getDust(M.Limonite, 2), GT.getSmallDust(M.Iron, 2));

			Processor.addGrinderRecipe("oreMalachite", 1, GT.getDust(M.Malachite, 2), GT.getDust(M.Calcite, 1),
					GT.getSmallDust(M.Copper, 2));

			Processor.addGrinderRecipe("orePyrite", 1, GT.getDust(M.Pyrite, 2), GT.getSmallDust(M.Iron, 2));

			Processor.addGrinderRecipe("oreSheldonite", 1, GT.getDust(M.Sheldonite, 2), GT.getSmallDust(M.Nickel, 1),
					GT.getSmallDust(M.Platinum, 1));

			Processor.addGrinderRecipe("oreSphalerite", 1, GT.getDust(M.Sphalerite, 2), GT.getDust(M.Zinc, 1),
					GT.getSmallDust(M.GarnetYellow, 1));

			Processor.addGrinderRecipe("oreTantalite", 1, GT.getDust(M.Tantalite, 2), GT.getSmallDust(M.Niobium, 2),
					GT.getSmallDust(M.Tantalum, 1));

			Processor.addGrinderRecipe("oreTetrahedrite", 1, GT.getDust(M.Tetrahedrite, 2),
					GT.getSmallDust(M.Copper, 2), GT.getSmallDust(M.Zinc, 1));

			Processor.addGrinderRecipe("oreTungstate", 1, GT.getDust(M.Tungstate, 2), GT.getSmallDust(M.Iron, 3),
					GT.getSmallDust(M.Manganese, 3));

			Processor.addGrinderRecipe("orePyrolusite", 1, GT.getDust(M.Pyrolusite, 2),
					GT.getSmallDust(M.Manganese, 2));

			Processor.addGrinderRecipe("oreMolybdenite", 1, GT.getDust(M.Molybdenite, 2),
					GT.getSmallDust(M.Molybdenum, 2));

			Processor.addGrinderRecipe("oreIridium", 1, GT.getIc2(Ic2Items.iridiumOre, 2),
					GT.getSmallDust(M.Platinum, 2));

			// and some vanilla ic2 ores

			Processor.addGrinderRecipe("oreCopper", 1, GT.getIc2(Ic2Items.copperDust, 2), GT.getSmallDust(M.Gold, 1),
					GT.getSmallDust(M.Nickel, 1));

			Processor.addGrinderRecipe("oreUranium", 1, GT.getDust(M.Uranium, 2), GT.getSmallDust(M.Uranium, 2));

			Processor.addGrinderRecipe("oreIron", 1, GT.getIc2(Ic2Items.ironDust, 2), GT.getSmallDust(M.Iron, 1),
					GT.getSmallDust(M.Nickel, 1));

			Processor.addGrinderRecipe("oreGold", 1, GT.getIc2(Ic2Items.goldDust, 2), GT.getSmallDust(M.Copper, 1),
					GT.getSmallDust(M.Nickel, 1));

			Processor.addGrinderRecipe("oreTin", 1, GT.getIc2(Ic2Items.tinDust, 2), GT.getSmallDust(M.Iron, 1),
					GT.getSmallDust(M.Zinc, 1));

			Processor.addGrinderRecipe("oreSilver", 1, GT.getIc2(Ic2Items.silverDust, 2), GT.getSmallDust(M.Lead, 2));

		}

		/*
		 * These recipes will be added regardless of Ic2 extras
		 */

		Processor.addGrinderRecipe("netherrack", 16, GT.getIc2(Ic2Items.netherrackDust, 16),
				GT.get(Items.GOLD_NUGGET, 1));

		Processor.addGrinderRecipe("obsidian", 1, GT.getIc2(Ic2Items.obsidianDust, 2), GT.getSmallDust(M.Iron, 1),
				GT.getSmallDust(M.Magnesium, 1));

		Processor.addGrinderRecipe("oreQuartz", 1, GT.get(Items.QUARTZ, 3), GT.getIc2(Ic2Items.netherrackDust, 1));

		Processor.addGrinderRecipe("oreSaltpeter", 1, GT.getDust(M.Saltpeter, 4));

		Processor.addGrinderRecipe("oreSalt", 1, GT.getDust(M.Salt, 4));

		Processor.addGrinderRecipe("oreCalcite", 1, GT.getDust(M.Calcite, 4));

		Processor.addGrinderRecipe("oreCryolite", 1, GT.getDust(M.Cryolite, 4));

		Processor.addGrinderRecipe("oreGraphite", 1, GT.getDust(M.Graphite, 2), GT.getSmallDust(M.Carbon, 1),
				GT.getSmallDust(M.Diamond, 1));

		Processor.addGrinderRecipe("oreRuby", 1, GT.getGem(M.Ruby, 2), GT.getSmallDust(M.Ruby, 6),
				GT.getSmallDust(M.GarnetRed, 2));

		Processor.addGrinderRecipe("oreSapphire", 1, GT.getGem(M.Sapphire, 2), GT.getSmallDust(M.Sapphire, 6),
				GT.getSmallDust(M.SapphireGreen, 2));

		Processor.addGrinderRecipe("oreCoal", 1, GT.get(Items.COAL, 2), GT.getIc2(Ic2Items.coalDust, 1),
				GT.getSmallDust(M.Thorium, 1));

		Processor.addGrinderRecipe("oreLapis", 1, new ItemStack(Items.DYE, 12, 4), GT.getDust(M.Lazurite, 3));

		Processor.addGrinderRecipe("oreRedstone", 1, GT.get(Items.REDSTONE, 16), GT.getSmallDust(M.Glowstone, 2));

		Processor.addGrinderRecipe("oreDiamond", 1, GT.get(Items.DIAMOND, 2), GT.getSmallDust(M.Diamond, 6),
				GT.getIc2(Ic2Items.hydratedCoalDust, 1));

		Processor.addGrinderRecipe("oreEmerald", 1, GT.get(Items.EMERALD, 2), GT.getSmallDust(M.Emerald, 6),
				GT.getSmallDust(M.Olivine, 2));

		Processor.addGrinderRecipe("oreCinnabar", 1, GT.getDust(M.Cinnabar, 5), GT.getSmallDust(M.Redstone, 2),
				GT.getSmallDust(M.Glowstone, 1));

		Processor.addGrinderRecipe("oreOlivine", 1, GT.getGem(M.Olivine, 2), GT.getSmallDust(M.Olivine, 6),
				GT.getSmallDust(M.Emerald, 2));

		Processor.addGrinderRecipe("oreSodalite", 1, GT.getDust(M.Lazurite, 12), GT.getDust(M.Alumina, 3));
	}

	public static void recipeIndustrialProcessor2() {
		Processor.addMagnetRecipe("gravel", 12, GT.get(Items.IRON_NUGGET, 2), GT.getNugget(M.Nickel, 1),
				GT.getNugget(M.Cobalt, 1));
		Processor.addMagnetRecipe("dustSlag", 4, GT.get(Items.IRON_NUGGET, 2), GT.getNugget(M.Nickel, 1));
		Processor.addMagnetGrinderRecipe("oreCopper", 1, GT.getIc2(Ic2Items.copperDust, 2), GT.getNugget(M.Nickel, 2),
				GT.getNugget(M.Cobalt, 2));
		Processor.addMagnetGrinderRecipe("oreTin", 1, GT.getIc2(Ic2Items.tinDust, 2), GT.getIc2(Ic2Items.ironDust, 1),
				GT.get(Items.IRON_NUGGET, 3));
		Processor.addMagnetGrinderRecipe("oreIron", 1, GT.getIc2(Ic2Items.ironDust, 2), GT.getNugget(M.Nickel, 4),
				GT.get(Items.IRON_NUGGET, 4));
	}

}
