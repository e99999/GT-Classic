package gtclassic.material;

import net.minecraftforge.oredict.OreDictionary;

public class GTMaterialDict {

	public static void init() {
		for (GTMaterial mat : GTMaterial.values()) {
			// Iterating through resource blocks
			if (mat.hasFlag(GTMaterialFlag.BLOCK)) {
				OreDictionary.registerOre("block" + mat.getDisplayName(), GTMaterialGen.getMaterialBlock(mat, 1));
			}
			// Regular dusts
			if (mat.hasFlag(GTMaterialFlag.DUST)) {
				OreDictionary.registerOre("dust" + mat.getDisplayName(), GTMaterialGen.getDust(mat, 1));
			}
			// Gems
			if (GTMaterial.isGem(mat)) {
				OreDictionary.registerOre("gem" + mat.getDisplayName(), GTMaterialGen.getGem(mat, 1));
			}
			// Ingots
			if (mat.hasFlag(GTMaterialFlag.INGOT)) {
				OreDictionary.registerOre("ingot" + mat.getDisplayName(), GTMaterialGen.getIngot(mat, 1));
			}
		}
	}
}
