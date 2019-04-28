package gtclassic.material;

import net.minecraftforge.oredict.OreDictionary;

public class GTMaterialDict {

	public static void init() {

		for (GTMaterial mat : GTMaterial.values()) {

			// Iterating through resource blocks
			if (mat.hasFlag(GTMaterialFlag.CASING)) {
				OreDictionary.registerOre("casingMachine" + mat.getDisplayName(), GTMaterialGen.getCasing(mat, 1));
			}

			// Iterating through resource blocks
			if (mat.hasFlag(GTMaterialFlag.BLOCK)) {
				OreDictionary.registerOre("block" + mat.getDisplayName(), GTMaterialGen.getMaterialBlock(mat, 1));
			}

			// Small dusts
			if (mat.hasFlag(GTMaterialFlag.SMALLDUST)) {
				OreDictionary.registerOre("dustSmall" + mat.getDisplayName(), GTMaterialGen.getSmallDust(mat, 1));
			}

			// Regular dusts
			if (mat.hasFlag(GTMaterialFlag.DUST)) {
				OreDictionary.registerOre("dust" + mat.getDisplayName(), GTMaterialGen.getDust(mat, 1));
			}

			// Gems
			if (mat.hasFlag(GTMaterialFlag.GEM)) {
				OreDictionary.registerOre("gem" + mat.getDisplayName(), GTMaterialGen.getGem(mat, 1));
			}

			// Ingots
			if (mat.hasFlag(GTMaterialFlag.INGOT)) {
				OreDictionary.registerOre("ingot" + mat.getDisplayName(), GTMaterialGen.getIngot(mat, 1));
			}

			// Nuggets
			if (mat.hasFlag(GTMaterialFlag.NUGGET)) {
				OreDictionary.registerOre("nugget" + mat.getDisplayName(), GTMaterialGen.getNugget(mat, 1));
			}

			// Plates
			if (mat.hasFlag(GTMaterialFlag.PLATE)) {
				OreDictionary.registerOre("plate" + mat.getDisplayName(), GTMaterialGen.getPlate(mat, 1));
			}

			// Sticks
			if (mat.hasFlag(GTMaterialFlag.STICK)) {
				OreDictionary.registerOre("stick" + mat.getDisplayName(), GTMaterialGen.getStick(mat, 1));
				OreDictionary.registerOre("rod" + mat.getDisplayName(), GTMaterialGen.getStick(mat, 1));
			}
			if (mat.hasFlag(GTMaterialFlag.WIRE)) {
				OreDictionary.registerOre("fineWire" + mat.getDisplayName(), GTMaterialGen.getWire(mat, 1));
				OreDictionary.registerOre("fineWireAny", GTMaterialGen.getWire(mat, 1));
			}

			// Gears
			if (mat.hasFlag(GTMaterialFlag.GEAR)) {
				OreDictionary.registerOre("gear" + mat.getDisplayName(), GTMaterialGen.getGear(mat, 1));
			}

		}

	}

}
