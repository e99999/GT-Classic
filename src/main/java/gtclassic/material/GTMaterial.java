package gtclassic.material;

import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;

public class GTMaterial {

	static GTMaterialFlag particle = GTMaterialFlag.PARTICLE;
	static GTMaterialFlag fluid = GTMaterialFlag.FLUID;
	static GTMaterialFlag gas = GTMaterialFlag.GAS;
	static GTMaterialFlag smalldust = GTMaterialFlag.SMALLDUST;
	static GTMaterialFlag dust = GTMaterialFlag.DUST;
	static GTMaterialFlag gem = GTMaterialFlag.GEM;
	static GTMaterialFlag ingot = GTMaterialFlag.INGOT;
	static GTMaterialFlag nugget = GTMaterialFlag.NUGGET;
	static GTMaterialFlag plate = GTMaterialFlag.PLATE;
	static GTMaterialFlag smallplate = GTMaterialFlag.SMALLPLATE;
	static GTMaterialFlag stick = GTMaterialFlag.STICK;
	static GTMaterialFlag magnetic = GTMaterialFlag.MAGNETICSTICK;
	static GTMaterialFlag wire = GTMaterialFlag.WIRE;
	static GTMaterialFlag gear = GTMaterialFlag.GEAR;
	static GTMaterialFlag foil = GTMaterialFlag.FOIL;
	static GTMaterialFlag block = GTMaterialFlag.BLOCK;
	static GTMaterialFlag casing = GTMaterialFlag.CASING;
	static GTMaterialFlag coil = GTMaterialFlag.COIL;

	static GTMaterialFlag[] dustAll = { smalldust, dust };
	static GTMaterialFlag[] gemAll = { smalldust, dust, gem, block };
	static GTMaterialFlag[] metalFull = { smalldust, dust, nugget, ingot, plate, stick, gear, block, casing };
	static GTMaterialFlag[] metalBase = { smalldust, dust, nugget, ingot, plate, stick };
	static GTMaterialFlag[] metalIc2 = { smalldust, nugget, plate, stick, gear, casing };
	static GTMaterialFlag[] metalMC = { smalldust, plate, stick, gear, casing };
	static GTMaterialFlag[] slurryBase = { smalldust, dust, fluid };

	/** Master Material Map **/
	private static HashMap<String, GTMaterial> generatedMap = new HashMap<>();

	/** Material Instances **/
	// @formatter:off
	public static final GTMaterial 
	AnnealedCopper = new GTMaterial("AnnealedCopper", 255, 120, 20, 3014, 1.0F, 0, 1, ingot, nugget, stick, wire),
	Almandine = new GTMaterial("Almandine", 255, 0, 0, 1759, 1.0F, 0, 1, dustAll),
	Alumina = new GTMaterial("Alumina", 200, 232, 255, 2345, 1.0F, 0, 1, dustAll),
	Aluminium = new GTMaterial("Aluminium", 128, 200, 240, 933, 10.0F, 128, 2, smalldust, dust, nugget, ingot, plate, stick, wire, gear, block, casing, foil),
	Andradite = new GTMaterial("Andradite", 150, 120, 0, 1258, 1.0F, 0, 1, dustAll),
	Antimony = new GTMaterial("Antimony", 220, 220, 240, 903, 1.0F, 0, 1, dustAll),
	Ashes = new GTMaterial("Ashes",192, 192, 192, 1000, 1.0F, 0, 1, dustAll),
	Aquaregia = new GTMaterial("AquaRegia", 255, 185, 0, 200, 1.0F, 0, 1, fluid), 
	Basalt = new GTMaterial("Basalt", 30, 20, 20, 1672, 5.0F, 0, 2, dustAll),
	Bauxite = new GTMaterial("Bauxite", 200, 100, 0, 2800, 3.0F, 0, 1, dustAll),
	BauxiteTailings = new GTMaterial("BauxiteTailings", 172, 0, 0, 1000, 1.0F, 0, 1, slurryBase),
	Beryllium = new GTMaterial("Beryllium",30, 80, 50, 1560, 1.0F, 0, 1, fluid),
	Bismuth = new GTMaterial("Bismuth",100, 160, 160, 1837, 6.0F, 96, 2, metalFull),
	BismuthBronze = new GTMaterial("BismuthBronze", 100, 125, 125, 1036, 6.0F, 128, 2, smalldust, dust, nugget, ingot, plate),
	Bismuthtine = new GTMaterial("Bismuthtine", 75, 135, 135, 737, 3.0F, 0, 1, dustAll),
	Brass = new GTMaterial("Brass", 255, 180, 0, 1160, 7.0F, 96, 1, metalFull),
	Brine = new GTMaterial("Brine", 0, 255, 221, 300, 1.0F, 0 , 1, fluid, dust, smalldust),
	Bronze = new GTMaterial("Bronze", 230, 83, 34, 1357, 6.0F, 192, 2, metalIc2),
	Calcite = new GTMaterial("Calcite", 250, 230, 220, 1612, 3.0F, 0, 1, dustAll),
	Calcium = new GTMaterial("Calcium", 155, 96, 80, 1115, 1.0F, 0, 1,fluid),
	Carbon = new GTMaterial("Carbon", 0, 0, 0, 3800, 1.0F, 0, 1, dustAll),
	CarbonDioxide = new GTMaterial("CarbonDioxide", 48, 48, 48, 1929, 1.0F, 0, 1, gas),
	Cassiterite = new GTMaterial("Cassiterite", 220, 220, 220, 757, 3.0F, 0, 1, dustAll),
	Charcoal = new GTMaterial("Charcoal", 100, 70, 70, 3800, 1.0F, 0, 1,smalldust),
	Chlorine = new GTMaterial("Chlorine", 50, 150, 150, 171, 1.0F, 0, 1, fluid),
	Chloroplatinicacid = new GTMaterial("Chloroplatinicacid",255, 0, 0, 14, 1.0F, 0, 1, fluid),
	Chrome = new GTMaterial("Chrome", 255, 230, 230, 2180, 11.0F, 256, 3, metalFull),
	Chromite = new GTMaterial("Chromite", 35, 20, 15, 912, 5.0F, 0, 3,  dustAll),
	Cinnabar = new GTMaterial("Cinnabar", 150, 0, 0, 311, 3.0F, 0, 2, dustAll),
	Clay = new GTMaterial("Clay", 200, 200, 220, 2000, 1.0F, 0, 1,  smalldust),
	Coal = new GTMaterial("Coal", 70, 70, 70, 3800, 3.0F, 0, 1, smalldust),
	Cobalt = new GTMaterial("Cobalt", 80, 80, 250, 1768, 8.0F, 512, 4, metalBase),
	Constantan = new GTMaterial("Constantan", 196, 116, 77, 1542, 8.0F, 128, 2, smalldust, dust, nugget, ingot, plate, stick, coil),
	Copper = new GTMaterial("Copper", 180, 113, 61, 1357, 1.0F, 0, 1, smalldust, nugget, plate, stick, wire, gear, casing, coil),
	Cryolite = new GTMaterial("Cryolite", 255, 255, 255, 1285, 3.0F, 0, 1, fluid, smalldust, dust),
	DarkAshes = new GTMaterial("DarkAshes", 50, 50, 50, 1000, 1.0F, 0, 1,  dustAll),
	Diamond = new GTMaterial("Diamond", 51, 235, 203, 3800, 8.0F, 1280, 3,  dustAll),
	DirtyResin = new GTMaterial("DirtyResin", 170, 124, 49, 300, 1.0F, 0, 1,  dustAll),
	Deuterium = new GTMaterial("Deuterium", 255, 255, 0, 14, 1.0F, 0, 1, gas),
	Electrum = new GTMaterial("Electrum", 255, 255, 100, 1285, 12.0F, 64, 2,  metalFull),
	Emerald = new GTMaterial("Emerald", 80, 255, 80, 1803, 7.0F, 256, 3, dustAll),
	EnderEye = new GTMaterial("EnderEye", 160, 250, 230, 3447, 1.0F, 0, 1, dustAll),
	EnderPearl = new GTMaterial("Enderpearl", 108, 220, 200, 2723, 1.0F, 0, 1, dustAll),
	Endstone = new GTMaterial("Endstone", 250, 250, 198, 1200, 1.0F, 0, 1,  dustAll),
	Flint = new GTMaterial("Flint", 0, 32, 64, 1986, 2.5F, 64, 1, dustAll),
	Galena = new GTMaterial("Galena", 100, 60, 100, 784, 3.0F, 0, 1,  dustAll),
	GarnetRed = new GTMaterial("RedGarnet", 200, 80, 80 ,1574, 7.0F, 128, 2,  dustAll),
	GarnetYellow = new GTMaterial("YellowGarnet",200, 200, 80, 1574, 7.0F, 128, 2, dustAll),
	Garnierite = new GTMaterial("Garnierite", 50, 200, 70, 891, 3.0F, 0, 1,  dustAll),
	Germanium = new GTMaterial("Germanium", 250, 250, 250, 1211, 8.0F, 64, 1,   smalldust, dust, nugget, ingot, plate, smallplate, stick, gear, block, casing),
	Glowstone = new GTMaterial("Glowstone", 255, 255, 0, 987, 1.0F, 0, 1,  smalldust),
	Gold = new GTMaterial("Gold", 255, 255, 30, 1337, 12.0F, 64, 2, smalldust, plate, stick, wire, gear, casing),
	Granite = new GTMaterial("Granite", 165, 89, 39, 1811, 1.0F, 0, 1, dustAll),
	Graphite = new GTMaterial("Graphite", 96, 96, 96, 2000, 5.0F, 32, 2, smalldust, dust, nugget, ingot, stick, plate, stick, coil),
	Grossular = new GTMaterial("Grossular", 200, 100, 0, 1655, 1.0F, 0, 1, dustAll),
	Gunpowder = new GTMaterial("Gunpowder", 128, 128, 128, 2026, 1.0F, 0, 1,  smalldust),
	Helium = new GTMaterial("Helium", 255, 255, 0, 1, 1.0F, 0, 1, gas),
	Helium3 = new GTMaterial("Helium3", 255, 255, 0, 1, 1.0F, 0, 1, gas),
	Hydrochloricacid = new GTMaterial("Hydrochloricacid", 127, 255, 142, 100, 1.0F, 0, 1, fluid),
	Hydrogen = new GTMaterial("Hydrogen", 0, 38, 255, 14, 1.0F, 0, 1, gas),
	Iridium = new GTMaterial("Iridium", 255, 255, 255, 2719, 6.0F, 5120, 4,  smalldust, dust, nugget, ingot, gear, stick, casing, block),
	Iron = new GTMaterial("Iron", 184, 184, 184, 1811, 6.0F, 256, 2, smalldust, plate, stick, gear, casing, magnetic),
	Invar = new GTMaterial("Invar", 220, 220, 150, 1916, 6.0F, 256, 2,  metalFull),
	Lazurite = new GTMaterial("Lazurite", 100, 120, 255, 1352, 1.0F, 0, 1, dustAll),
	Lead = new GTMaterial("Lead", 140, 100, 140, 600, 8.0F, 64, 1, smalldust, dust, nugget, ingot, plate, stick, wire, gear, block, casing),
	Limonite = new GTMaterial("Limonite", 200, 100, 0, 1207, 3.0F, 0, 1,  dustAll),
	Lithium = new GTMaterial("Lithium", 87, 150, 204, 453, 1.0F, 0, 1, dustAll),
	LithiumCarbonate = new GTMaterial("LithiumCarbonate", 87, 150, 204, 453, 1.0F, 0, 1, fluid),
	LithiumChloride = new GTMaterial("LithiumChloride", 85, 204, 204, 453, 1.0F, 0, 1, dustAll),
	Malachite = new GTMaterial("Malachite", 5, 95, 5, 754, 3.0F, 0, 1,  dustAll),
	Maganlium = new GTMaterial("Magnalium", 200, 190, 255, 929, 6.0F, 256, 2, metalBase),
	MagnesiaCarbon = new GTMaterial("MagnesiaCarbon", 0, 0, 0, 3000, 1.0F, 0, 1, dustAll),
	Magnesium = new GTMaterial("Magnesium", 255, 200, 200, 923, 1.0F, 0, 1,  dustAll),
	Magnetite = new GTMaterial("Magnetite", 0, 0, 0, 1811, 3.0F, 0, 1, dustAll),
	Manganese = new GTMaterial("Manganese", 250, 235, 250, 1519, 1.0F, 0, 1,  smalldust, dust, nugget, ingot, plate, block),
	Marble = new GTMaterial("Marble", 200, 200, 200, 1525, 1.0F, 0, 1,  dustAll),
	Methane = new GTMaterial("Methane", 255, 50, 130, 100, 1.0F, 0, 1, gas),
	Mercury = new GTMaterial("Mercury", 250, 250, 250, 234, 1.0F, 0, 1, fluid),
	Molybdenite = new GTMaterial("Molybdenite", 35, 20, 15, 1224, 5.0F, 0, 3, dustAll),
	Molybdenum = new GTMaterial("Molybdenum", 180, 180, 220, 2896, 1.0F, 0, 1,  metalBase),
	Neodymium = new GTMaterial("Neodymium", 100, 100, 100, 1297, 7.0F, 3347, 3, smalldust, dust, nugget, ingot, plate, stick, magnetic ),
	Netherrack = new GTMaterial("Netherrack", 200, 0, 0, 1500, 1.0F, 0, 1,  smalldust),
	Nichrome = new GTMaterial("Nichrome", 205, 206, 246, 1818, 10.0F, 256, 3,  smalldust, dust, nugget, ingot, plate, stick, coil),
	Nickel = new GTMaterial("Nickel", 200, 200, 250, 1728, 6.0F, 64, 2, smalldust, dust, nugget, ingot, plate, stick, wire, gear, block, casing),
	Niobium = new GTMaterial("Niobium", 200, 200, 200, 2750, 1.0F, 0, 1,  smalldust, dust, nugget, ingot, plate, stick, foil),
	NiobiumTitanium = new GTMaterial("NiobiumTitanium", 29, 29, 41, 2345, 1.0F, 0, 1, smalldust, dust, nugget, ingot, plate, stick, wire, coil),
	NitricAcid = new GTMaterial("NitricAcid", 34, 185, 55, 255, 1.0F, 0, 1, fluid),
	Nitrogen = new GTMaterial("Nitrogen", 0, 190, 190, 63, 1.0F, 0, 1, gas),
	Obsidian = new GTMaterial("Obsidian", 80, 50, 100, 1300, 1.0F, 0, 1, smalldust),
	Oil = new GTMaterial("Oil", 0, 0, 0, 100, 1.0F, 0, 1,  fluid),
	OilCrude = new GTMaterial("Crude_Oil", 0, 0, 0, 100, 1.0F, 0, 1, fluid),
	Olivine = new GTMaterial("Olivine", 150, 255, 150, 1525, 7.0F, 256, 2, gemAll),
	Osmium = new GTMaterial("Osmium", 50, 50, 255, 3306, 16.0F, 1280, 4,  metalFull),
	Oxygen = new GTMaterial("Oxygen", 100, 160, 220, 54, 1.0F, 0, 1, gas),
	Phosphorus = new GTMaterial("Phosphorus", 190, 0, 0, 317, 1.0F, 0, 1,  dustAll),
	Plastic = new GTMaterial("Plastic", 235, 235, 235, 423, 1.0F, 0, 1,  dust, smalldust, plate),
	Platinum = new GTMaterial("Platinum", 100, 180, 250, 2041, 12.0F, 64, 2,  smalldust, dust, nugget, ingot, plate, stick, wire, gear, block, casing),
	PlatinumGroupSludge = new GTMaterial("PlatinumGroupSludge", 50,  50,  80, 317, 1.0F, 0, 1,  dustAll),
	Plutonium = new GTMaterial("Plutonium", 240, 50, 50, 912, 6.0F, 512, 3,  smalldust, dust, nugget, ingot, plate),
	Potassium = new GTMaterial("Potassium", 250, 250, 250, 336, 1.0F, 0, 1, fluid),
	Pyrite = new GTMaterial("Pyrite", 150, 120, 40, 862, 3.0F, 0, 1,dustAll),
	Pyrolusite = new GTMaterial("Pyrolusite", 70, 70, 90, 808, 5.0F, 0, 2, dustAll),
	Pyrope = new GTMaterial("Pyrope", 120, 50, 100, 1626, 1.0F, 0, 1, dustAll),
	RedAlloy = new GTMaterial("RedAlloy", 200, 0, 0, 1400, 1.0F, 0, 1, smalldust, dust, ingot, nugget, stick, wire),
	RedRock = new GTMaterial("RedRock", 255, 80, 50, 1802, 1.0F, 0, 1, dustAll),
	Redstone = new GTMaterial("Redstone", 200, 0, 0, 500, 1.0F, 0, 1, smalldust),
	RefinedIron = new GTMaterial("RefinedIron", 220, 235, 235, 2011, 6.0F, 384, 2, stick, plate, gear, casing),
	Resin = new GTMaterial("Resin", 233, 194, 70, 300, 1.0F, 0, 1, dustAll),
	Ruby = new GTMaterial("Ruby", 255, 100, 100, 2317, 7.0F, 256, 2,  gemAll),
	Salt = new GTMaterial("Salt", 160, 190, 200, 1074, 4.0F, 0, 1, dustAll),
	Saltpeter = new GTMaterial("Saltpeter", 230, 230, 230, 119, 3.0F, 0, 1,  dustAll),
	Sapphire = new GTMaterial("Sapphire", 100, 100, 200, 2345, 7.0F, 256, 2, gemAll),
	SapphireGreen = new GTMaterial("GreenSapphire", 100, 200, 130, 2108, 7.0F, 256, 2, gemAll),
	Sheldonite = new GTMaterial("Sheldonite", 215, 212, 137, 1677, 3.5F, 0, 3, dustAll),
	Silicon = new GTMaterial("Silicon", 60, 60, 80, 1687, 1.0F, 0, 1, fluid, smalldust, dust, nugget, ingot, plate),
	Silver = new GTMaterial("Silver", 215, 225, 230, 1234, 10.0F, 64, 2, smalldust, nugget, plate, stick, wire, gear, casing),
	Slag = new GTMaterial("Slag", 64, 48, 0, 1000, 1.0F, 0, 1, dustAll),
	Sodalite = new GTMaterial("Sodalite", 20, 20, 255, 1331, 3.0F, 0, 2, dustAll),
	Sodium = new GTMaterial("Sodium", 0, 38, 255, 370, 1.0F, 0, 1, fluid),
	SodiumCarbonate = new GTMaterial("SodiumCarbonate", 255, 255, 255, 591, 1.0F, 0, 1, fluid),
	SodiumHydroxide = new GTMaterial("SodiumHydroxide", 255, 255, 255, 591, 1.0F, 0, 1, fluid),
	Spessartine = new GTMaterial("Spessartine", 255, 100, 100, 1715, 1.0F, 0, 1, dustAll),
	Sphalerite = new GTMaterial("Sphalerite", 200, 140, 40, 540, 2.0F, 0, 1, dustAll),
	StainlessSteel = new GTMaterial("StainlessSteel", 200, 200, 220, 2032, 7.0F, 480, 2, smalldust, dust, nugget, ingot, plate, stick, gear, block, casing),
	Steel = new GTMaterial("Steel", 128, 128, 128, 2046, 6.0F, 512, 2, smalldust, dust, nugget, ingot, plate, stick, gear, block, casing, magnetic),
	Stone = new GTMaterial("Stone", 196, 196, 196, 1100, 1.0F, 0, 1, dustAll),
	Sulfur = new GTMaterial("Sulfur", 200, 200, 0, 100, 2.0F, 0, 1, smalldust, dust),
	SulfurDioxide = new GTMaterial("SulfurDioxide", 200, 200, 0, 100, 1.0F, 0, 1, gas),
	SulfuricAcid = new GTMaterial("SulfuricAcid", 255, 106, 0, 200, 1.0F, 0, 1, fluid),
	Tantalite = new GTMaterial("Tantalite", 145, 80, 40, 935, 5.0F, 0, 2, dustAll),
	Tantalum = new GTMaterial("Tantalum", 96, 96, 96, 3290, 8.0F, 5120, 3,  dust, smalldust, nugget, ingot, stick, plate, block, foil),
	Tetrahedrite = new GTMaterial("Tetrahedrite", 200, 32, 0, 993, 3.0F, 0, 1, dustAll),
	Thorium = new GTMaterial("Thorium", 0, 30, 0, 2115, 6.0F, 512, 2, smalldust, dust, nugget, ingot),
	Tin = new GTMaterial("Tin", 220, 220, 220, 505, 1.0F, 0, 1, smalldust, nugget, plate, stick, wire, gear, casing),
	Titanium = new GTMaterial("Titanium", 170, 143, 222, 1941, 8.0F, 2560, 3,  smalldust, dust, nugget, ingot, plate, stick, gear, block, casing),
	Tritium = new GTMaterial("Tritium", 255, 0, 0, 14, 1.0F, 0, 1, gas),
	Tungstate = new GTMaterial("Tungstate", 60, 60, 60, 688, 4.0F, 0, 3,  dustAll),
	Tungsten = new GTMaterial("Tungsten", 50, 50, 50, 3695, 8.0F, 5120, 3,  smalldust, dust, nugget, ingot, plate, stick, wire, gear, block, casing),
	TungstenSteel = new GTMaterial("Tungstensteel", 100, 100, 160, 3000, 10.0F, 5120, 4, smalldust, dust, nugget, ingot, plate, stick, gear, block, casing),
	TungsticAcid = new GTMaterial("TungsticAcid", 188, 200, 0, 1746, 1.0F, 0, 1, dustAll),
	Ultimet = new GTMaterial("Ultimet", 180, 180, 230, 1980, 9.0F, 2048, 4,  smalldust, dust, nugget, ingot, plate, stick, gear, block, casing),
	Uranium = new GTMaterial("Uranium", 50, 240, 50, 1405, 6.0F, 512, 3, dustAll),
	Uvarovite = new GTMaterial("Uvarovite", 180, 255, 180, 1295, 1.0F, 0, 1, dustAll),
	Vibranium = new GTMaterial("Vibranium", 178, 0, 255, 4852, 1000.0F, 512, 4, gemAll),
	VitriolBlue = new GTMaterial("BlueVitriol", 0, 20, 200, 255, 1.0F, 0, 1, fluid),
	VitriolCyan = new GTMaterial("CyanVitriol", 0, 150, 150, 255, 1.0F, 0, 1, fluid),
	VitriolGreen = new GTMaterial("GreenVitriol", 34, 185, 55, 255, 1.0F, 0, 1, fluid),
	VitriolRed = new GTMaterial("RedVitriol", 196, 0, 0, 255, 1.0F, 0, 1, fluid),
	Wood = new GTMaterial("Wood", 137, 103, 39, 400, 1.0F, 0, 1, dustAll),
	Zinc = new GTMaterial("Zinc", 250, 240, 240, 692, 1.0F, 0, 1, metalFull),
	Zircaloy = new GTMaterial("Zircaloy" , 190, 190, 175, 2032, 9.0F, 512, 2, smalldust, dust, nugget, ingot, plate, stick, gear, block, casing),
	Zirconium = new GTMaterial("Zirconium", 180, 210, 210, 2128, 1.0F, 0, 1,  dustAll);
	// @formatter:on

	/** Instance Members **/
	private String name, displayName;
	private int mask;
	private float speed;
	private int temp;
	private int durability;
	private int level;
	private Color color;
	// Expand for needs

	/**
	 * 
	 * @param displayName - The name of the material
	 * @param r           - red value 0-255
	 * @param g           - green value 0-255
	 * @param b           - blue value 0-255
	 * @param temp        - The melting point of the material
	 * @param speed       - Speed the material for tools
	 * @param durability  - Durability for material tools
	 * @param level       - Harvest Level for material tools
	 * @param flags       - Types of items and blocks to generate from material
	 */

	public GTMaterial(String displayName, int r, int g, int b, int temp, float speed, int durability, int level,
			GTMaterialFlag... flags) {
		this.displayName = displayName;
		this.name = displayName.toLowerCase().replaceAll("-", "_").replaceAll(" ", "_");
		this.color = new Color(r, g, b);
		this.speed = speed;
		this.temp = temp;
		this.durability = durability;
		this.level = level;
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

	public static boolean isLowHeat(GTMaterial mat) {
		return mat.getTemp() < 2046;
	}

}
