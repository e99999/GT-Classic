package gtclassic.material;

import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;

public class GTMaterial {

	static GTMaterialFlag PARTICLE = GTMaterialFlag.PARTICLE;
	static GTMaterialFlag FLUID = GTMaterialFlag.FLUID;
	static GTMaterialFlag PLASMA = GTMaterialFlag.PLASMA;
	static GTMaterialFlag SMALLDUST = GTMaterialFlag.SMALLDUST;
	static GTMaterialFlag DUST = GTMaterialFlag.DUST;
	static GTMaterialFlag GEM = GTMaterialFlag.GEM;
	static GTMaterialFlag INGOT = GTMaterialFlag.INGOT;
	static GTMaterialFlag NUGGET = GTMaterialFlag.NUGGET;
	static GTMaterialFlag PLATE = GTMaterialFlag.PLATE;
	static GTMaterialFlag STICK = GTMaterialFlag.STICK;
	static GTMaterialFlag WIRE = GTMaterialFlag.WIRE;
	static GTMaterialFlag GEAR = GTMaterialFlag.GEAR;
	static GTMaterialFlag BLOCK = GTMaterialFlag.BLOCK;
	static GTMaterialFlag CASING = GTMaterialFlag.CASING;

	static GTMaterialFlag[] DUST_ALL = { SMALLDUST, DUST };
	static GTMaterialFlag[] GEM_ALL = { SMALLDUST, DUST, GEM, BLOCK };
	static GTMaterialFlag[] METAL_ALL = { SMALLDUST, DUST, NUGGET, INGOT, PLATE, STICK, GEAR, BLOCK, CASING };
	static GTMaterialFlag[] METAL_BASE = { SMALLDUST, DUST, NUGGET, INGOT, PLATE, STICK };
	static GTMaterialFlag[] METAL_IC2 = { SMALLDUST, NUGGET, PLATE, STICK, GEAR, CASING };
	static GTMaterialFlag[] METAL_MC = { SMALLDUST, PLATE, STICK, GEAR, CASING };

	/** Master Material Map **/
	private static HashMap<String, GTMaterial> generatedMap = new HashMap<>();

	/** Material Instances **/
	public static GTMaterial Almandine = new GTMaterial("Almandine", 1759, 1.0F, 0, 1, new Color(255, 0, 0), false,
			DUST_ALL);
	public static GTMaterial Alumina = new GTMaterial("Alumina", 2345, 1.0F, 0, 1, new Color(200, 232, 255), false,
			DUST_ALL);
	public static GTMaterial Aluminium = new GTMaterial("Aluminium", 933, 10.0F, 128, 2, new Color(128, 200, 240), true,
			SMALLDUST, DUST, NUGGET, INGOT, PLATE, STICK, WIRE, GEAR, BLOCK, CASING);
	public static GTMaterial Andradite = new GTMaterial("Andradite", 1258, 1.0F, 0, 1, new Color(150, 120, 0), false,
			DUST_ALL);
	public static GTMaterial Antimony = new GTMaterial("Antimony", 903, 1.0F, 0, 1, new Color(220, 220, 240), false,
			DUST_ALL);
	public static GTMaterial Ashes = new GTMaterial("Ashes", 1000, 1.0F, 0, 1, new Color(192, 192, 192), false,
			DUST_ALL);
	public static GTMaterial Basalt = new GTMaterial("Basalt", 1672, 5.0F, 0, 2, new Color(30, 20, 20), false,
			DUST_ALL);//
	public static GTMaterial Bauxite = new GTMaterial("Bauxite", 2800, 3.0F, 0, 1, new Color(200, 100, 0), false,
			DUST_ALL);
	public static GTMaterial Beryllium = new GTMaterial("Beryllium", 1560, 1.0F, 0, 1, new Color(30, 80, 50), false,
			FLUID);
	public static GTMaterial Bismuth = new GTMaterial("Bismuth", 1837, 6.0F, 96, 2, new Color(100, 160, 160), true,
			METAL_ALL);
	public static GTMaterial BismuthBronze = new GTMaterial("BismuthBronze", 1036, 6.0F, 128, 2, new Color(225, 150, 0),
			true, SMALLDUST, DUST, NUGGET, INGOT, PLATE);
	public static GTMaterial Bismuthtine = new GTMaterial("Bismuthtine", 737, 3.0F, 0, 1, new Color(75, 135, 135),
			false, DUST_ALL);
	public static GTMaterial Brass = new GTMaterial("Brass", 1160, 7.0F, 96, 1, new Color(255, 180, 0), true,
			METAL_ALL);
	public static GTMaterial Bronze = new GTMaterial("Bronze", 1357, 6.0F, 192, 2, new Color(230, 83, 34), true,
			METAL_IC2);
	public static GTMaterial Calcite = new GTMaterial("Calcite", 1612, 3.0F, 0, 1, new Color(250, 230, 220), false,
			DUST_ALL);
	public static GTMaterial Calcium = new GTMaterial("Calcium", 1115, 1.0F, 0, 1, new Color(155, 96, 80), false,
			FLUID);
	public static GTMaterial Carbon = new GTMaterial("Carbon", 3800, 1.0F, 0, 1, Color.black, false, DUST_ALL);
	public static GTMaterial Cassiterite = new GTMaterial("Cassiterite", 757, 3.0F, 0, 1, new Color(220, 220, 220),
			false, DUST_ALL);
	public static GTMaterial Charcoal = new GTMaterial("Charcoal", 3800, 1.0F, 0, 1, new Color(100, 70, 70), false,
			SMALLDUST);
	public static GTMaterial Chlorine = new GTMaterial("Chlorine", 171, 1.0F, 0, 1, new Color(50, 150, 150), false,
			FLUID);
	public static GTMaterial Chrome = new GTMaterial("Chrome", 2180, 11.0F, 256, 3, new Color(245, 206, 227), false,
			METAL_ALL);
	public static GTMaterial Chromite = new GTMaterial("Chromite", 912, 5.0F, 0, 3, new Color(35, 20, 15), false,
			DUST_ALL);
	public static GTMaterial Cinnabar = new GTMaterial("Cinnabar", 311, 3.0F, 0, 2, new Color(150, 0, 0), false,
			DUST_ALL);
	public static GTMaterial Clay = new GTMaterial("Clay", 2000, 1.0F, 0, 1, new Color(200, 200, 220), false,
			SMALLDUST);
	public static GTMaterial Coal = new GTMaterial("Coal", 3800, 3.0F, 0, 1, new Color(70, 70, 70), false, SMALLDUST);
	public static GTMaterial Cobalt = new GTMaterial("Cobalt", 1768, 8.0F, 512, 4, new Color(80, 80, 250), true,
			METAL_BASE);
	public static GTMaterial Constantan = new GTMaterial("Constantan", 1542, 8.0F, 128, 2, new Color(196, 116, 77),
			true, METAL_BASE);
	public static GTMaterial Copper = new GTMaterial("Copper", 1357, 1.0F, 0, 1, new Color(180, 113, 61), true,
			SMALLDUST, NUGGET, PLATE, STICK, WIRE, GEAR, CASING);
	public static GTMaterial Cryolite = new GTMaterial("Cryolite", 1285, 3.0F, 0, 1, Color.white, false, FLUID,
			SMALLDUST, DUST);
	public static GTMaterial DarkAshes = new GTMaterial("DarkAshes", 1000, 1.0F, 0, 1, new Color(50, 50, 50), false,
			DUST_ALL);
	public static GTMaterial Diamond = new GTMaterial("Diamond", 3800, 8.0F, 1280, 3, new Color(51, 235, 203), false,
			DUST_ALL);
	public static GTMaterial DirtyResin = new GTMaterial("DirtyResin", 300, 1.0F, 0, 1, new Color(170, 124, 49), false,
			DUST_ALL);
	public static GTMaterial Deuterium = new GTMaterial("Deuterium", 14, 1.0F, 0, 1, Color.yellow, false, FLUID);
	public static GTMaterial Electrum = new GTMaterial("Electrum", 1285, 12.0F, 64, 2, new Color(255, 255, 100), true,
			METAL_ALL);
	public static GTMaterial Emerald = new GTMaterial("Emerald", 1803, 7.0F, 256, 3, new Color(80, 255, 80), false,
			DUST_ALL);
	public static GTMaterial EnderEye = new GTMaterial("EnderEye", 3447, 1.0F, 0, 1, new Color(160, 250, 230), false,
			DUST_ALL);
	public static GTMaterial EnderPearl = new GTMaterial("Enderpearl", 2723, 1.0F, 0, 1, new Color(108, 220, 200),
			false, DUST_ALL);
	public static GTMaterial Endstone = new GTMaterial("Endstone", 1200, 1.0F, 0, 1, new Color(250, 250, 198), false,
			DUST_ALL);
	public static GTMaterial Flint = new GTMaterial("Flint", 1986, 2.5F, 64, 1, new Color(0, 32, 64), false, DUST_ALL);
	public static GTMaterial Galena = new GTMaterial("Galena", 784, 3.0F, 0, 1, new Color(100, 60, 100), false,
			DUST_ALL);
	public static GTMaterial GarnetRed = new GTMaterial("RedGarnet", 1574, 7.0F, 128, 2, new Color(200, 80, 80), false,
			DUST_ALL);
	public static GTMaterial GarnetYellow = new GTMaterial("YellowGarnet", 1574, 7.0F, 128, 2, new Color(200, 200, 80),
			false, DUST_ALL);
	public static GTMaterial Garnierite = new GTMaterial("Garnierite", 891, 3.0F, 0, 1, new Color(50, 200, 70), false,
			DUST_ALL);
	public static GTMaterial Germanium = new GTMaterial("Germanium", 1211, 8.0F, 64, 1, new Color(250, 250, 250), true,
			METAL_ALL);
	public static GTMaterial Glowstone = new GTMaterial("Glowstone", 987, 1.0F, 0, 1, new Color(255, 255, 0), false,
			SMALLDUST);
	public static GTMaterial Gold = new GTMaterial("Gold", 1337, 12.0F, 64, 2, new Color(255, 255, 30), true, SMALLDUST,
			PLATE, STICK, WIRE, GEAR, CASING);
	public static GTMaterial Granite = new GTMaterial("Granite", 1811, 1.0F, 0, 1, new Color(165, 89, 39), false,
			DUST_ALL);
	public static GTMaterial Graphite = new GTMaterial("Graphite", 2000, 5.0F, 32, 2, new Color(96, 96, 96), false,
			SMALLDUST, DUST, NUGGET, INGOT, STICK, PLATE, STICK);
	public static GTMaterial Grossular = new GTMaterial("Grossular", 1655, 1.0F, 0, 1, new Color(200, 100, 0), false,
			DUST_ALL);
	public static GTMaterial Gunpowder = new GTMaterial("Gunpowder", 2026, 1.0F, 0, 1, new Color(128, 128, 128), false,
			SMALLDUST);
	public static GTMaterial Helium = new GTMaterial("Helium", 1, 1.0F, 0, 1, Color.yellow, false, FLUID, PLASMA);
	public static GTMaterial Helium3 = new GTMaterial("Helium3", 1, 1.0F, 0, 1, Color.yellow, false, FLUID);
	public static GTMaterial Hydrochloricacid = new GTMaterial("Hydrochloricacid", 100, 1.0F, 0, 1,
			new Color(127, 255, 142), false, FLUID);
	public static GTMaterial Hydrogen = new GTMaterial("Hydrogen", 14, 1.0F, 0, 1, Color.blue, false, FLUID);
	public static GTMaterial Iridium = new GTMaterial("Iridium", 2719, 6.0F, 5120, 4, new Color(255, 255, 255), false,
			SMALLDUST, DUST, NUGGET, INGOT, GEAR, STICK, CASING, BLOCK);
	public static GTMaterial Iron = new GTMaterial("Iron", 1811, 6.0F, 256, 2, new Color(184, 184, 184), true,
			SMALLDUST, PLATE, STICK, GEAR, CASING);
	public static GTMaterial Invar = new GTMaterial("Invar", 1916, 6.0F, 256, 2, new Color(180, 180, 120), true,
			METAL_ALL);
	public static GTMaterial Lazurite = new GTMaterial("Lazurite", 1352, 1.0F, 0, 1, new Color(100, 120, 255), false,
			DUST_ALL);
	public static GTMaterial Lead = new GTMaterial("Lead", 600, 8.0F, 64, 1, new Color(140, 100, 140), true, SMALLDUST,
			DUST, NUGGET, INGOT, PLATE, STICK, WIRE, GEAR, BLOCK, CASING);
	public static GTMaterial Limonite = new GTMaterial("Limonite", 1207, 3.0F, 0, 1, new Color(200, 100, 0), false,
			DUST_ALL);
	public static GTMaterial Lithium = new GTMaterial("Lithium", 453, 1.0F, 0, 1, new Color(87, 150, 204), false,
			FLUID);
	public static GTMaterial Malachite = new GTMaterial("Malachite", 754, 3.0F, 0, 1, new Color(5, 95, 5), false,
			DUST_ALL);
	public static GTMaterial Maganlium = new GTMaterial("Magnalium", 929, 6.0F, 256, 2, new Color(200, 190, 255), false,
			INGOT, NUGGET, PLATE);
	public static GTMaterial Magnesium = new GTMaterial("Magnesium", 923, 1.0F, 0, 1, new Color(255, 200, 200), false,
			DUST_ALL);
	public static GTMaterial Magnetite = new GTMaterial("Magnetite", 1811, 3.0F, 0, 1, new Color(0, 0, 0), true,
			DUST_ALL);
	public static GTMaterial Manganese = new GTMaterial("Manganese", 1519, 1.0F, 0, 1, new Color(250, 235, 250), true,
			SMALLDUST, DUST, NUGGET, INGOT, PLATE, BLOCK);
	public static GTMaterial Marble = new GTMaterial("Marble", 1525, 1.0F, 0, 1, new Color(200, 200, 200), false,
			DUST_ALL);//
	public static GTMaterial Methane = new GTMaterial("Methane", 100, 1.0F, 0, 1, new Color(255, 50, 130), false,
			FLUID);
	public static GTMaterial Mercury = new GTMaterial("Mercury", 234, 1.0F, 0, 1, Color.white, false, FLUID);
	public static GTMaterial Molybdenite = new GTMaterial("Molybdenite", 1224, 5.0F, 0, 3, new Color(35, 20, 15), false,
			DUST_ALL);
	public static GTMaterial Molybdenum = new GTMaterial("Molybdenum", 2896, 1.0F, 0, 1, new Color(180, 180, 220),
			false, METAL_BASE);
	public static GTMaterial Netherrack = new GTMaterial("Netherrack", 1500, 1.0F, 0, 1, new Color(200, 0, 0), false,
			SMALLDUST);
	public static GTMaterial Nichrome = new GTMaterial("Nichrome", 1818, 10.0F, 256, 3, new Color(88, 133, 188), false,
			METAL_BASE);
	public static GTMaterial Nickel = new GTMaterial("Nickel", 1728, 6.0F, 64, 2, new Color(200, 200, 250), true,
			SMALLDUST, DUST, NUGGET, INGOT, PLATE, STICK, WIRE, GEAR, BLOCK, CASING);
	public static GTMaterial Niobium = new GTMaterial("Niobium", 2750, 1.0F, 0, 1, new Color(200, 200, 200), false,
			METAL_BASE);
	public static GTMaterial NiobiumTitanium = new GTMaterial("NiobiumTitanium", 2345, 1.0F, 0, 1,
			new Color(40, 40, 50), false, SMALLDUST, DUST, NUGGET, INGOT, PLATE, STICK, WIRE);
	public static GTMaterial Nitrogen = new GTMaterial("Nitrogen", 63, 1.0F, 0, 1, new Color(0, 190, 190), false, FLUID,
			PLASMA);
	public static GTMaterial Obsidian = new GTMaterial("Obsidian", 1300, 1.0F, 0, 1, new Color(80, 50, 100), false,
			SMALLDUST);
	public static GTMaterial Oil = new GTMaterial("Oil", 100, 1.0F, 0, 1, new Color(0, 0, 0), false, FLUID);
	public static GTMaterial OilCrude = new GTMaterial("CrudeOil", 100, 1.0F, 0, 1, new Color(0, 0, 0), false, FLUID);
	public static GTMaterial Olivine = new GTMaterial("Olivine", 1525, 7.0F, 256, 2, new Color(150, 255, 150), false,
			GEM_ALL);
	public static GTMaterial Osmium = new GTMaterial("Osmium", 3306, 16.0F, 1280, 4, new Color(50, 50, 255), false,
			METAL_ALL);
	public static GTMaterial Oxygen = new GTMaterial("Oxygen", 54, 1.0F, 0, 1, new Color(100, 160, 220), false, FLUID);
	public static GTMaterial Phosphorus = new GTMaterial("Phosphorus", 317, 1.0F, 0, 1, new Color(190, 0, 0), false,
			DUST_ALL);
	public static GTMaterial Plastic = new GTMaterial("Plastic", 423, 1.0F, 0, 1, new Color(235, 235, 235), false, DUST,
			SMALLDUST, PLATE);
	public static GTMaterial Platinum = new GTMaterial("Platinum", 2041, 12.0F, 64, 2, new Color(255, 255, 200), true,
			SMALLDUST, DUST, NUGGET, INGOT, PLATE, STICK, WIRE, GEAR, BLOCK, CASING);
	public static GTMaterial Plutonium = new GTMaterial("Plutonium", 912, 6.0F, 512, 3, new Color(240, 50, 50), false,
			SMALLDUST, DUST, NUGGET, INGOT, PLATE);
	public static GTMaterial Potassium = new GTMaterial("Potassium", 336, 1.0F, 0, 1, Color.white, false, FLUID);
	public static GTMaterial Pyrite = new GTMaterial("Pyrite", 862, 3.0F, 0, 1, new Color(150, 120, 40), false,
			DUST_ALL);
	public static GTMaterial Pyrolusite = new GTMaterial("Pyrolusite", 808, 5.0F, 0, 2, new Color(70, 70, 90), false,
			DUST_ALL);
	public static GTMaterial Pyrope = new GTMaterial("Pyrope", 1626, 1.0F, 0, 1, new Color(120, 50, 100), false,
			DUST_ALL);
	public static GTMaterial RedAlloy = new GTMaterial("RedAlloy", 1400, 1.0F, 0, 1, new Color(200, 0, 0), true,
			SMALLDUST, DUST, INGOT, NUGGET, STICK, WIRE);
	public static GTMaterial RedMud = new GTMaterial("RedMud", 1000, 1.0F, 0, 1, new Color(145, 47, 2), false,
			DUST_ALL);
	public static GTMaterial RedRock = new GTMaterial("RedRock", 1802, 1.0F, 0, 1, new Color(255, 80, 50), false,
			DUST_ALL);
	public static GTMaterial Redstone = new GTMaterial("Redstone", 500, 1.0F, 0, 1, new Color(200, 0, 0), false,
			SMALLDUST);
	public static GTMaterial RefinedIron = new GTMaterial("RefinedIron", 2011, 6.0F, 384, 2, new Color(220, 235, 235),
			true, STICK, PLATE, CASING);
	public static GTMaterial Resin = new GTMaterial("Resin", 300, 1.0F, 0, 1, new Color(233, 194, 70), false, DUST_ALL);
	public static GTMaterial Ruby = new GTMaterial("Ruby", 2317, 7.0F, 256, 2, new Color(255, 100, 100), false,
			GEM_ALL);
	public static GTMaterial Rutile = new GTMaterial("Rutile", 2041, 1.0F, 0, 1, new Color(212, 13, 92), false,
			DUST_ALL);
	public static GTMaterial Salt = new GTMaterial("Salt", 1074, 4.0F, 0, 1, new Color(160, 190, 200), false, DUST_ALL);
	public static GTMaterial Saltpeter = new GTMaterial("Saltpeter", 119, 3.0F, 0, 1, new Color(230, 230, 230), false,
			DUST_ALL);
	public static GTMaterial Sapphire = new GTMaterial("Sapphire", 2345, 7.0F, 256, 2, new Color(100, 100, 200), false,
			GEM_ALL);
	public static GTMaterial SapphireGreen = new GTMaterial("GreenSapphire", 2108, 7.0F, 256, 2,
			new Color(100, 200, 130), false, GEM_ALL);
	public static GTMaterial Sheldonite = new GTMaterial("Sheldonite", 1677, 3.5F, 0, 3, new Color(215, 212, 137),
			false, DUST_ALL);
	public static GTMaterial Silicon = new GTMaterial("Silicon", 1687, 1.0F, 0, 1, new Color(60, 60, 80), true, FLUID,
			SMALLDUST, DUST, NUGGET, INGOT, PLATE);
	public static GTMaterial Silver = new GTMaterial("Silver", 1234, 10.0F, 64, 2, new Color(215, 225, 230), true,
			SMALLDUST, NUGGET, PLATE, STICK, WIRE, GEAR, CASING);
	public static GTMaterial Slag = new GTMaterial("Slag", 1000, 1.0F, 0, 1, new Color(64, 48, 0), false, DUST_ALL);
	public static GTMaterial Slurry = new GTMaterial("Slurry", 300, 1.0F, 0, 1, new Color(135, 92, 50), false, FLUID);
	public static GTMaterial Sodalite = new GTMaterial("Sodalite", 1331, 3.0F, 0, 2, new Color(20, 20, 255), false,
			DUST_ALL);
	public static GTMaterial Sodium = new GTMaterial("Sodium", 370, 1.0F, 0, 1, Color.blue, false, FLUID);
	public static GTMaterial Spessartine = new GTMaterial("Spessartine", 1715, 1.0F, 0, 1, new Color(255, 100, 100),
			false, DUST_ALL);
	public static GTMaterial Sphalerite = new GTMaterial("Sphalerite", 540, 2.0F, 0, 1, new Color(200, 140, 40), false,
			DUST_ALL);
	public static GTMaterial StainlessSteel = new GTMaterial("StainlessSteel", 2032, 7.0F, 480, 2,
			new Color(200, 200, 220), false, METAL_ALL);
	public static GTMaterial Steel = new GTMaterial("Steel", 2046, 6.0F, 512, 2, new Color(128, 128, 128), false,
			METAL_ALL);
	public static GTMaterial Stone = new GTMaterial("Stone", 1100, 1.0F, 0, 1, new Color(196, 196, 196), false,
			DUST_ALL);
	public static GTMaterial Sulfur = new GTMaterial("Sulfur", 100, 2.0F, 0, 1, new Color(200, 200, 0), false,
			SMALLDUST, DUST);
	public static GTMaterial SulfurDioxide = new GTMaterial("SulfurDioxide", 100, 1.0F, 0, 1, new Color(200, 200, 0),
			false, FLUID);
	public static GTMaterial SulfuricAcid = new GTMaterial("SulfuricAcid", 200, 1.0F, 0, 1, Color.orange, false, FLUID);
	public static GTMaterial Tantalite = new GTMaterial("Tantalite", 935, 5.0F, 0, 2, new Color(102, 77, 61), false,
			DUST_ALL);
	public static GTMaterial Tantalum = new GTMaterial("Tantalum", 3290, 8.0F, 5120, 3, new Color(96, 96, 96), false,
			DUST, SMALLDUST, NUGGET, INGOT, STICK, PLATE, BLOCK);
	public static GTMaterial Tetrahedrite = new GTMaterial("Tetrahedrite", 993, 3.0F, 0, 1, new Color(200, 32, 0),
			false, DUST_ALL);
	public static GTMaterial Thorium = new GTMaterial("Thorium", 2115, 6.0F, 512, 2, new Color(0, 30, 0), false,
			SMALLDUST, DUST, NUGGET, INGOT);
	public static GTMaterial Tin = new GTMaterial("Tin", 505, 1.0F, 0, 1, new Color(220, 220, 220), true, SMALLDUST,
			NUGGET, PLATE, STICK, WIRE, GEAR, CASING);
	public static GTMaterial Titanium = new GTMaterial("Titanium", 1941, 8.0F, 2560, 3, new Color(170, 143, 222), true,
			METAL_ALL);
	public static GTMaterial Tritium = new GTMaterial("Tritium", 14, 1.0F, 0, 1, Color.red, false, FLUID);
	public static GTMaterial Tungstate = new GTMaterial("Tungstate", 688, 4.0F, 0, 3, new Color(60, 60, 60), false,
			DUST_ALL);
	public static GTMaterial Tungsten = new GTMaterial("Tungsten", 3695, 8.0F, 5120, 3, new Color(50, 50, 50), false,
			SMALLDUST, DUST, NUGGET, INGOT, PLATE, STICK, WIRE, GEAR, BLOCK, CASING);
	public static GTMaterial TungstenSteel = new GTMaterial("Tungstensteel", 2870, 10.0F, 5120, 4,
			new Color(100, 100, 160), false, METAL_ALL);
	public static GTMaterial Ultimet = new GTMaterial("Ultimet", 1980, 9.0F, 2048, 4, new Color(180, 180, 230), false,
			METAL_ALL);
	public static GTMaterial Uranium = new GTMaterial("Uranium", 1405, 6.0F, 512, 3, new Color(50, 240, 50), false,
			DUST_ALL);
	public static GTMaterial Uvarovite = new GTMaterial("Uvarovite", 1295, 1.0F, 0, 1, new Color(180, 255, 180), false,
			DUST_ALL);
	public static GTMaterial Vibranium = new GTMaterial("Vibranium", 4852, 1000.0F, 512, 4, new Color(178, 0, 255),
			false, GEM_ALL);
	public static GTMaterial Wood = new GTMaterial("Wood", 400, 1.0F, 0, 1, new Color(137, 103, 39), false, DUST_ALL);
	public static GTMaterial Zinc = new GTMaterial("Zinc", 692, 1.0F, 0, 1, new Color(250, 240, 240), true, METAL_ALL);
	public static GTMaterial Zirconium = new GTMaterial("Zirconium", 2128, 1.0F, 0, 1, new Color(180, 210, 210), false,
			DUST_ALL);

	/** Instance Members **/
	private String name, displayName;
	private int mask;
	private float speed;
	private String formula;
	private int temp;
	private int durability;
	private int level;
	private boolean smeltable;
	private Color color;
	// Expand for needs

	/**
	 * 
	 * @param displayName - The name of the material
	 * @param formula     - The chemical makeup to show in the tooltip
	 * @param temp        - The melting point of the material
	 * @param speed       - Speed the material for tools
	 * @param durability  - Durability for material tools
	 * @param level       - Harvest Level for material tools
	 * @param color       - Color of material
	 * @param smeltable   - Determines if a material requires sepcial processing
	 * @param flags       - Types of items and blocks to generate from material
	 */
	public GTMaterial(String displayName, int temp, float speed, int durability, int level, Color color,
			boolean smeltable, GTMaterialFlag... flags) {
		this.displayName = displayName;
		this.name = displayName.toLowerCase().replaceAll("-", "_").replaceAll(" ", "_");
		this.speed = speed;
		this.formula = "null";
		this.temp = temp;
		this.durability = durability;
		this.level = level;
		this.smeltable = smeltable;
		this.color = color;
		for (GTMaterialFlag flag : flags) {
			mask |= flag.getMask();
		}
		generatedMap.put(name, this);
	}

	public boolean hasFlag(GTMaterialFlag flag) {
		return (mask & flag.getMask()) != 0;
	}

	/** Getters/Setters **/

	public String getDisplayName() {
		return displayName;
	}

	public String getName() {
		return name;
	}

	public String getFormula() {
		return formula;
	}

	public int getTemp() {
		return temp;
	}

	public float getSpeed() {
		return speed;
	}

	public int getDurability() {
		return durability;
	}

	public int getLevel() {
		return level;
	}

	public boolean getSmeltable() {
		return smeltable;
	}

	public Color getColor() {
		return color;
	}

	/** Map Get **/
	public GTMaterial get(String name) {
		// This is where you could do error handling if you want
		return generatedMap.get(name);
	}

	/** Helper for looping **/
	public static Collection<GTMaterial> values() {
		return generatedMap.values();
	}

}
