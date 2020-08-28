package gtclassic.common;

import gtclassic.GTMod;
import gtclassic.api.crops.GTCropHandler;
import gtclassic.api.material.GTMaterial;

public class GTCrops {

	public static void init() {
		registerCrop(0, GTMaterial.Aluminium, "Bauxia", 0, "Metal", "Aluminium", "Reed", "Aluminium", "Leaves");
		registerCrop(1, GTMaterial.Platinum, "Platina", 1, " Metal", "Shiny", "Platinum", "Leaves");
		registerCrop(2, GTMaterial.Ruby, "Rubeus", 2, "Crystal", "Shiny", "Metal", "Ruby", "Leaves");
		registerCrop(3, GTMaterial.Sapphire, "Sapphirum", 3, "Crystal", "Shiny", "Metal", "Sapphire", "Leaves");
		registerCrop(4, GTMaterial.Thorium, "GodOfThunder", 4, "Radioactive", "Metal", "Coal", "Thorium", "Leaves");
		registerCrop(5, GTMaterial.Titanium, "Titania", 5, "Metal", "Heavy", "Titanium", "Leaves");
		registerCrop(6, GTMaterial.Tungsten, "Scheelinium", 6, "Metal", "Hard", "Tungsten", "Leaves");
		registerCrop(7, GTMaterial.UUAmplifier, "Transformium", 4, "Transform", "Coal", "Reed");
	}

	/** Private method only for GTClassic **/
	private static void registerCrop(int id, GTMaterial mat, String name, int tier, String... attributes) {
		GTCropHandler.registerCrop(id * 4, GTMod.MODID + "_crops", mat, name, "e99999", tier, attributes);
	}
}
