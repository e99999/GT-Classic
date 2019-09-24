package gtclassic;

import gtclassic.block.GTBlockCrop;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.util.GTCropType;
import ic2.api.classic.crops.ClassicCrops;
import ic2.api.crops.CropCard;

public class GTCrops {

	public static void init() {
		registerCrop(0, GTMaterial.Aluminium, "Bauxia", 6, "Metal", "Aluminium", "Reed", "Aluminium", "Leaves");
		registerCrop(1, GTMaterial.Platinum, "Platina", 11, " Metal", "Shiny", "Platinum", "Leaves");
		registerCrop(2, GTMaterial.Ruby, "Rubeus", 4, "Crystal", "Shiny", "Metal", "Ruby", "Leaves");
		registerCrop(3, GTMaterial.Sapphire, "Sapphirum", 4, "Crystal", "Shiny", "Metal", "Sapphire", "Leaves");
		registerCrop(4, GTMaterial.Thorium, "GodOfThunder", 9, "Radioactive", "Metal", "Coal", "Thorium", "Leaves");
		registerCrop(5, GTMaterial.Titanium, "Titania", 9, "Metal", "Heavy", "Titanium", "Leaves");
		registerCrop(6, GTMaterial.Tungsten, "Scheelinium", 12, "Metal", "Hard", "Tungsten", "Leaves");
	}

	/** Private method only for GTClassic **/
	private static void registerCrop(int id, GTMaterial mat, String name, int tier, String... attributes) {
		registerCrop(32 + id, GTMod.MODID + "_materials", mat, name, "e99999", tier, attributes);
	}

	/** Public method for constructing basic crops easily **/
	public static void registerCrop(int id, String sprite, GTMaterial mat, String name, String discoverer, int tier,
			String... attributes) {
		ClassicCrops crop = ClassicCrops.instance;
		GTCropType type = new GTCropType(id, sprite, mat, name, discoverer, tier, attributes);
		CropCard card = new GTBlockCrop(type);
		crop.registerCrop(card);
		crop.registerCropDisplayItem(card, GTMaterialGen.getDust(type.getMaterial(), 1));
	}
}
