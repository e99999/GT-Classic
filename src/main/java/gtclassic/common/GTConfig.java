package gtclassic.common;

import gtclassic.GTMod;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;

@Config(modid = GTMod.MODID, name = "ic2/gtclassic", category = "")
public class GTConfig {

	@Comment("General")
	@Config.RequiresMcRestart
	public static General general = new General();

	public static class General {

		public boolean debugMode = false;
		public boolean animatedTextures = true;
		public boolean addLootItems = true;
		public boolean preventMobSpawnsCloseToSpawn = true;
		public boolean removeIC2MassFab = true;
		public boolean removeIC2Plasmafier = true;
		public boolean addBasicCircuitRecipes = true;
		public boolean addAdvCircuitRecipes = true;
		public boolean vanillaRailRecipes = true;
		public boolean harderIC2Macerator = true;
		public boolean betterIC2SolarRecipes = true;
		public boolean gregtechUURecipes = true;
		public boolean oreDictWroughtIron = true;
		public boolean replacePistonRecipe = true;
		public boolean replaceHopperRecipe = true;
		public boolean clearerWater = true;
		public boolean replaceOceanGravelWithSand = true;
		public boolean caveZombiesSpawnWithPickaxe = true;
		public boolean generateUUMAssemblerRecipes = true;
		public boolean enableDisassembler = true;
		public boolean harderJetpacks = false;
		public boolean hideBedrockOresInJei = true;
		public boolean addCompressorRecipesForBlocks = true;
		public boolean addHydrogenAsLiquidFuel = true;
		public boolean enableSuperSolidFuels = true;
		public boolean enableMagnifyingGlassGivesEUTooltips = true;
		public boolean reduceGrassOnWorldGen = false;
		public boolean oneMagicAbsorberPerEndPortal = true;
		public boolean enableQuickerLeafDecay = true;
		public boolean enableBetterTwilightDurability = true;
	}

	@Comment("World Generation")
	@Config.RequiresMcRestart
	public static Generation generation = new Generation();

	public static class Generation {

		public boolean iridiumGenerate = true;
		public int iridiumSize = 3;
		public int iridiumWeight = 2;
		public boolean sheldoniteGenerate = true;
		public int sheldoniteSize = 8;
		public int sheldoniteWeight = 3;
		public boolean rubyGenerate = true;
		public int rubySize = 6;
		public int rubyWeight = 2;
		public boolean sapphireGenerate = true;
		public int sapphireSize = 6;
		public int sapphireWeight = 2;
		public boolean bauxiteGenerate = true;
		public int bauxiteSize = 16;
		public int bauxiteWeight = 4;
		public boolean bedrockOreGenerate = true;
		public int bedrockOreSize = 24;
		public int bedrockOreWeight = 4;
	}

	@Comment("Mod Compatability")
	@Config.RequiresMcRestart
	public static ModCompat modcompat = new ModCompat();

	public static class ModCompat {

		public boolean compatBaubles = true;
		public boolean compatBuildcraft = true;
		public boolean compatEnderIO = true;
		public boolean compatForestry = true;
		public boolean compatIc2Extras = true;
		public boolean compatIE = true;
		public boolean compatThermal = true;
		public boolean compatTwilightForest = true;
		public String[] extraTypeFilters = { "empty", "empty", "empty", "empty", "empty" };
	}
}