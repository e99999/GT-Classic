package gtclassic.recipe;

import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileMultiIndustrialProcessor;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.init.Items;

public class GTRecipeIndustrialProcessor {

	static GTMaterialGen GT;
	static GTMaterial M;
	static GTTileMultiIndustrialProcessor Processor;

	public static void recipeIndustrialProcessor1() {
		Processor.addGrinderRecipe("oreIron", 1, GT.getIc2(Ic2Items.ironDust, 2), GT.getSmallDust(M.Iron, 1),
				GT.getSmallDust(M.Nickel, 1));
		Processor.addGrinderRecipe("oreGold", 1, GT.getIc2(Ic2Items.goldDust, 2), GT.getSmallDust(M.Copper, 1),
				GT.getSmallDust(M.Nickel, 1));
		Processor.addGrinderRecipe("oreCopper", 1, GT.getIc2(Ic2Items.copperDust, 2), GT.getSmallDust(M.Gold, 1),
				GT.getSmallDust(M.Nickel, 1));
		Processor.addGrinderRecipe("oreTin", 1, GT.getIc2(Ic2Items.tinDust, 2), GT.getSmallDust(M.Iron, 1),
				GT.getSmallDust(M.Zinc, 1));
	}

	public static void recipeIndustrialProcessor2() {
		Processor.addMagnetRecipe("gravel", 24, GT.get(Items.IRON_NUGGET, 1), GT.getNugget(M.Nickel, 1),
				GT.getNugget(M.Cobalt, 1));
		Processor.addMagnetRecipe("dustSlag", 12, GT.get(Items.IRON_NUGGET, 1), GT.getNugget(M.Nickel, 1));
	}

}
