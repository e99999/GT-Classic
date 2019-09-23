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

	public static final CropCard cropAluminium = new GTBlockCrop(GTCropType.ALUMINIUM);
	public static final CropCard cropPlatinum = new GTBlockCrop(GTCropType.PLATINUM);
	public static final CropCard cropRuby = new GTBlockCrop(GTCropType.RUBY);
	public static final CropCard cropSapphire = new GTBlockCrop(GTCropType.SAPPHIRE);
	public static final CropCard cropThorium = new GTBlockCrop(GTCropType.THORIUM);
	public static final CropCard cropTitanium = new GTBlockCrop(GTCropType.TITANIUM);
	public static final CropCard cropTungsten = new GTBlockCrop(GTCropType.TUNGSTEN);

	public static void init() {
		registerCrop(cropAluminium, GTMaterial.Aluminium);
		registerCrop(cropPlatinum, GTMaterial.Platinum);
		registerCrop(cropRuby, GTMaterial.Ruby);
		registerCrop(cropSapphire, GTMaterial.Sapphire);
		registerCrop(cropThorium, GTMaterial.Thorium);
		registerCrop(cropTitanium, GTMaterial.Titanium);
		registerCrop(cropTungsten, GTMaterial.Tungsten);
	}

	private static void registerCrop(CropCard card, GTMaterial mat) {
		ClassicCrops crop = ClassicCrops.instance;
		crop.registerCrop(card);
		crop.registerCropDisplayItem(card, GTMaterialGen.getDust(mat, 1));
	}
}
