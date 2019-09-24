package gtclassic;

import gtclassic.block.GTBlockCrop;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import ic2.api.classic.crops.ClassicCrops;
import ic2.api.crops.CropCard;

public class GTCrops {

	public enum GTCropType {
		ALUMINIUM(0, GTMaterial.Aluminium, "Bauxia", 6, "Metal", "Aluminium", "Reed", "Aluminium", "Leaves"),
		PLATINUM(1, GTMaterial.Platinum, "Platina", 11, " Metal", "Shiny", "Platinum", "Leaves"),
		RUBY(2, GTMaterial.Ruby, "Rubeus", 4, "Crystal", "Shiny", "Metal", "Ruby", "Leaves"),
		SAPPHIRE(3, GTMaterial.Sapphire, "Sapphirum", 4, "Crystal", "Shiny", "Metal", "Sapphire", "Leaves"),
		THORIUM(4, GTMaterial.Thorium, "GodOfThunder", 9, "Radioactive", "Metal", "Coal", "Thorium", "Leaves"),
		TITANIUM(5, GTMaterial.Titanium, "Titania", 9, "Metal", "Heavy", "Titanium", "Leaves"),
		TUNGSTEN(6, GTMaterial.Tungsten, "Scheelinium", 12, "Metal", "Hard", "Tungsten", "Leaves");

		private int id;
		private GTMaterial mat;
		private String name;
		private int tier;
		private String[] attributes;

		GTCropType(int id, GTMaterial mat, String name, int tier, String... attributes) {
			this.id = id;
			this.mat = mat;
			this.name = name;
			this.tier = tier;
			this.attributes = attributes;
		}

		public int getId() {
			return this.id;
		}

		public GTMaterial getMaterial() {
			return this.mat;
		}

		public String getName() {
			return this.name;
		}

		public int getTier() {
			return this.tier;
		}

		public String[] getAttributes() {
			return this.attributes;
		}
	}

	public static void init() {
		registerCrop(GTCropType.ALUMINIUM);
		registerCrop(GTCropType.PLATINUM);
		registerCrop(GTCropType.RUBY);
		registerCrop(GTCropType.SAPPHIRE);
		registerCrop(GTCropType.THORIUM);
		registerCrop(GTCropType.TITANIUM);
		registerCrop(GTCropType.TUNGSTEN);
	}
	
	private static void registerCrop(GTCropType type) {
		ClassicCrops crop = ClassicCrops.instance;
		CropCard card = new GTBlockCrop(type);
		crop.registerCrop(card);
		crop.registerCropDisplayItem(card, GTMaterialGen.getDust(type.getMaterial(), 1));
	}
}
