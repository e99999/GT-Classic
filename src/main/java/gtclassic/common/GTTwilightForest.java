package gtclassic.common;

import gtclassic.GTMod;
import gtclassic.api.helpers.GTValues;
import gtclassic.common.event.GTEventLootTableLoad;
import ic2.core.IC2;
import ic2.core.platform.registry.Ic2States;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import twilightforest.world.TFWorld;
import twilightforest.world.feature.TFGenCaveStalactite;

public class GTTwilightForest {

	private static final String[] TFOREST_LOOTTABLES = { "hill_1", "hill_2", "hill_3", "hedge_maze", "labyrinth_room",
			"labyrinth_dead_end", "tower_room", "tower_library", "basement", "labyrinth_vault", "darktower_cache",
			"darktower_key", "tree_cache", "stronghold_cache", "stronghold_room", "aurora_cache", "aurora_room",
			"troll_garden", "troll_vault" };

	public static void initStalactites() {
		/*
		 * Dont feel like adding IMC so im calling it directly, just needed to make my
		 * mod loaded after TForest or this gets reset
		 */
		GTMod.logger.info("Adding ores to Twilight Forest hollow hills");
		if (GTConfig.generation.iridiumGenerate) {
			TFGenCaveStalactite.addStalactite(3, GTBlocks.oreIridium.getDefaultState(), 0.5F, 4, 16, 30);
		}
		if (GTConfig.generation.sheldoniteGenerate) {
			TFGenCaveStalactite.addStalactite(2, GTBlocks.oreSheldonite.getDefaultState(), 0.5F, 8, 1, 12);
		}
		if (GTConfig.generation.sapphireGenerate) {
			TFGenCaveStalactite.addStalactite(2, GTBlocks.oreSapphire.getDefaultState(), 0.6F, 6, 1, 20);
		}
		if (GTConfig.generation.rubyGenerate) {
			TFGenCaveStalactite.addStalactite(2, GTBlocks.oreRuby.getDefaultState(), 0.6F, 6, 1, 20);
		}
		if (GTConfig.generation.bauxiteGenerate) {
			TFGenCaveStalactite.addStalactite(1, GTBlocks.oreBauxite.getDefaultState(), 0.8F, 12, 1, 24);
		}
		if (IC2.config.getFlag("WorldGenOreCopper")) {
			TFGenCaveStalactite.addStalactite(1, Ic2States.copperOre, 0.7F, 9, 1, 24);
		}
		if (IC2.config.getFlag("WorldGenOreTin")) {
			TFGenCaveStalactite.addStalactite(1, Ic2States.tinOre, 0.7F, 9, 1, 24);
		}
		if (IC2.config.getFlag("WorldGenOreSilver")) {
			TFGenCaveStalactite.addStalactite(1, Ic2States.silverOre, 0.5F, 8, 1, 12);
		}
		if (IC2.config.getFlag("WorldGenOreUranium")) {
			TFGenCaveStalactite.addStalactite(3, Ic2States.uraniumOre, 0.5F, 3, 12, 15);
		}
	}

	public static void initLootTables() {
		/* Iterates the above array and grabs them from the forge loottable */
		for (ResourceLocation loc : LootTableList.getAll()) {
			for (String name : TFOREST_LOOTTABLES) {
				if (loc.getResourceDomain().equals(GTValues.MOD_ID_TFOREST) && loc.getResourcePath().contains(name)) {
					GTEventLootTableLoad.addTable(loc, "common");
					GTMod.logger.info("Added GT Loot to Twilight Forest loot table: " + name);
				}
			}
		}
	}

	public static void initRecipes() {
		/* Recipes specific to TForest mod compat */
	}

	public static boolean isTwilightForest(World world) {
		return TFWorld.isTwilightForest(world);
	}
}
