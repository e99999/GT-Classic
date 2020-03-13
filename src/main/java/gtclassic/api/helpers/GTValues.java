package gtclassic.api.helpers;

import gtclassic.api.recipe.GTRecipeCraftingHandler;
import gtclassic.common.GTItems;
import ic2.api.recipe.IRecipeInput;
import ic2.core.IC2;
import ic2.core.item.recipe.entry.RecipeInputCombined;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.init.Blocks;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class GTValues {

	private GTValues() {
		throw new IllegalStateException("Utility class");
	}

	/** Static references to mod ids **/
	public static final String MOD_ID_BUILDCRAFT = "buildcraftbuilders", MOD_ID_FORESTRY = "forestry",
			MOD_ID_HARVESTCRAFT = "harvestcraft", MOD_ID_THERMAL = "thermalfoundation",
			MOD_ID_IE = "immersiveengineering", MOD_ID_IC2_EXTRAS = "ic2c_extras", MOD_ID_BAUBLES = "baubles",
			MOD_ID_ENDERIO = "enderio", MOD_ID_TFOREST = "twilightforest", MOD_ID_GTCX = "gtc_expansion",
			MOD_ID_FASTLEAF = "fastleafdecay";
	/** Below is some tooltip stuff **/
	public static final String TOOLTIP_NOMOBS = "tooltip.gtclassic.nomobs",
			TOOLTIP_BEACON = "tooltip.gtclassic.beaconbase";
	/** Static ore dict vars that SonarLint hates me reusing **/
	public static final String INGOT_REFINEDIRON = IC2.getRefinedIron(), DUST_SULFUR = "dustSulfur",
			DUST_PHOSPHORUS = "dustPhosphorus", PAPER = "paper", DUST_WOOD = "pulpWood", BLOCK_COAL = "blockCoal",
			BLOCK_CHARCOAL = "blockCharcoal", INGOT_TUNGSTEN = "ingotTungsten", CIRCUIT_BASIC = "circuitBasic",
			CIRCUIT_ADVANCED = "circuitAdvanced", GEM_DIAMOND = "gemDiamond", INGOT_ALUMINIUM = "ingotAluminium",
			CIRCUIT_DATA = "circuitData", DUST_REDSTONE = "dustRedstone", BLOCK_GLASS = "blockGlass",
			STICK_WOOD = "stickWood", INGOT_TIN = "ingotTin", PLATE_IRIDIUM_ALLOY = "plateIridiumAlloy",
			CIRCUIT_MASTER = "circuitMaster", CIRCUIT_ELITE = "circuitElite",
			CRAFTING_SUPERCONDUCTOR = "craftingSuperconductor", BATTERY_ULTIMATE = "batteryUltimate",
			INGOT_CHROME = "ingotChrome", MACHINE_ELITE = "machineBlockElite", MACHINE_ADV = "machineBlockAdvanced",
			INGOT_TITANIUM = "ingotTitanium", MACHINE_BASIC = "machineBlockBasic", CIRCUIT_ULTIMATE = "circuitUltimate",
			CHEST_WOOD = "chestWood", DUCT_TAPE = "craftingToolDuctTape", DUST_GLOWSTONE = "dustGlowstone",
			INGOT_PLATINUM = "ingotPlatinum", INGOT_BRONZE = "ingotBronze", INGOT_SILICON = "itemSilicon",
			INGOT_ELECTRUM = "ingotElectrum", INGOT_SILVER = "ingotSilver", INGOT_INVAR = "ingotInvar",
			INGOT_NICKEL = "ingotNickel", INGOT_GOLD = "ingotGold", ENDER_CHEST = "chestEnder";
	/** Generic fluidstack holders **/
	public static final FluidStack FS_WATER = new FluidStack(FluidRegistry.WATER, 1000),
			FS_LAVA = new FluidStack(FluidRegistry.LAVA, 1000);
	/** Stores combine recipe input vars **/
	public static final IRecipeInput INPUT_INGOT_MACHINE = GTRecipeCraftingHandler.combineRecipeObjects(INGOT_REFINEDIRON, INGOT_ALUMINIUM),
			INPUT_INGOT_ELECTRIC = GTRecipeCraftingHandler.combineRecipeObjects(INGOT_REFINEDIRON, INGOT_SILICON, INGOT_ALUMINIUM, INGOT_SILVER, INGOT_ELECTRUM, INGOT_PLATINUM, INGOT_GOLD),
			INPUT_INGOT_ANYIRON = GTRecipeCraftingHandler.combineRecipeObjects("ingotRefinedIron", "ingotIron", "ingotSteel"),
			INPUT_INGOT_ANYIRONORBRONZE = GTRecipeCraftingHandler.combineRecipeObjects("ingotRefinedIron", "ingotIron", "ingotSteel", INGOT_BRONZE),
			INPUT_INGOT_MIXED = GTRecipeCraftingHandler.combineRecipeObjects(INGOT_SILVER, INGOT_ALUMINIUM, INGOT_ELECTRUM, INGOT_INVAR),
			INPUT_CRYSTAL_LOW = GTRecipeCraftingHandler.combineRecipeObjects(GEM_DIAMOND, "gemRuby"),
			INPUT_CRYSTAL_HIGH = GTRecipeCraftingHandler.combineRecipeObjects("gemSapphire", Ic2Items.energyCrystal.copy()),
			INPUT_LAPIS_ANY = GTRecipeCraftingHandler.combineRecipeObjects("gemLapis", "dustLazurite"),
			INPUT_INGOT_SILVER = GTRecipeCraftingHandler.combineRecipeObjects(INGOT_SILVER, INGOT_ELECTRUM),
			INPUT_PISTON_ANY = GTRecipeCraftingHandler.combineRecipeObjects(Blocks.STICKY_PISTON, Blocks.PISTON),
			INPUT_INGOT_HIGH = GTRecipeCraftingHandler.combineRecipeObjects(INGOT_TUNGSTEN, INGOT_TITANIUM),
			INPUT_INGOT_DIGITAL = GTRecipeCraftingHandler.combineRecipeObjects(INGOT_CHROME, INGOT_TITANIUM, INGOT_PLATINUM),
			INPUT_COOLANT_SUPERCONDUCTOTR = GTRecipeCraftingHandler.combineRecipeObjects(Ic2Items.reactorCoolantCellSix, GTItems.heatStorageHelium1),
			INPUT_BATTERY_ADVANCED = GTRecipeCraftingHandler.combineRecipeObjects(Ic2Items.energyCrystal.copy(), GTItems.lithiumBattery),
			INPUT_DIAMOND_OR_TUNGSTEN = GTRecipeCraftingHandler.combineRecipeObjects(INGOT_TUNGSTEN, GEM_DIAMOND),
			INPUT_BLOCK_COAL = GTRecipeCraftingHandler.combineRecipeObjects(BLOCK_COAL, BLOCK_CHARCOAL),
			INPUT_CIRCUIT_BASIC_X2 = new RecipeInputCombined(2, new IRecipeInput[] {
					new RecipeInputOreDict(CIRCUIT_BASIC) });
	private static final String[] TIERS = { "N/A", "LV", "MV", "HV", "EV", "IV", "LuV", "ZPM", "UV", "MAX" };

	/** Get the name of a tier via its int value **/
	public static String getTierString(int tier) {
		return (tier < 0 || tier > 9) ? TIERS[0] : TIERS[tier];
	}
}
