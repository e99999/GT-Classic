package gtclassic.common;

import gtclassic.GTMod;
import gtclassic.api.helpers.GTValues;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.common.event.GTEventLootTableLoad;
import gtclassic.common.tile.GTTileDragonEggEnergySiphon;
import gtclassic.common.tile.GTTileMagicEnergyConverter;
import ic2.core.IC2;
import ic2.core.block.machine.low.TileEntityExtractor;
import ic2.core.platform.registry.Ic2States;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import twilightforest.enums.BossVariant;
import twilightforest.item.TFItems;
import twilightforest.tileentity.TileEntityTFTrophy;
import twilightforest.world.TFWorld;
import twilightforest.world.feature.TFGenCaveStalactite;

public class GTTwilightForest {

	private static final String[] TFOREST_LOOTTABLES = { "hill_1", "hill_2", "hill_3", "hedge_maze", "labyrinth_room",
			"labyrinth_dead_end", "tower_room", "tower_library", "basement", "labyrinth_vault", "darktower_cache",
			"darktower_key", "tree_cache", "stronghold_cache", "stronghold_room", "aurora_cache", "aurora_room",
			"troll_garden", "troll_vault" };
	private static final int[] TROPHY_PRODUCTION = { 4, 8, 48, 80, 64, 96, 32, 0, 16 };
	// WITHER SKULL - 1EU
	// DRAGON HEAD - 8EU
	// DRAGON EGG - 128EU
	// NAGA - 4EU - meta 0
	// LICH - 8EU - meta 1
	// QUESTING RAM - 16EU - meta 8
	// MINOSHROOM - 32EU - meta 6
	// HYDRA - 48EU - meta 2
	// KNIGHT PHANTOM - 64EU - meta 4
	// UR-GHAST - 80EU - meta 3
	// SNOW QUEEN - 96EU - meta 5
	// CASTLE - 128EU - none

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
				if (loc.getNamespace().equals(GTValues.MOD_ID_TFOREST) && loc.getPath().contains(name)) {
					GTEventLootTableLoad.addTable(loc, "common");
					GTMod.logger.info("Added GT Loot to Twilight Forest loot table: " + name);
				}
			}
		}
	}

	public static void initRecipes() {
		/* Recipes specific to TForest mod compat */
		TileEntityExtractor.addRecipe(GTMaterialGen.getModMetaItem(GTValues.MOD_ID_TFOREST, "root", 1, 1), GTMaterialGen.getModItem(GTValues.MOD_ID_TFOREST, "liveroot", 3));
		GTTileMagicEnergyConverter.addRecipe(GTMaterialGen.getModItem(GTValues.MOD_ID_TFOREST, "transformation_powder"), 36000);
		GTTileMagicEnergyConverter.addRecipe(GTMaterialGen.getModItem(GTValues.MOD_ID_TFOREST, "borer_essence"), 32000);
		if (!GTConfig.general.energySiphonJustSucksEggs) {
			GTTileDragonEggEnergySiphon.addFakeRecipe(GTMaterialGen.getModMetaItem(GTValues.MOD_ID_TFOREST, "trophy", 0, 1), TROPHY_PRODUCTION[0]);
			GTTileDragonEggEnergySiphon.addFakeRecipe(GTMaterialGen.getModMetaItem(GTValues.MOD_ID_TFOREST, "trophy", 1, 1), TROPHY_PRODUCTION[1]);
			GTTileDragonEggEnergySiphon.addFakeRecipe(GTMaterialGen.getModMetaItem(GTValues.MOD_ID_TFOREST, "trophy", 8, 1), TROPHY_PRODUCTION[8]);
			GTTileDragonEggEnergySiphon.addFakeRecipe(GTMaterialGen.getModMetaItem(GTValues.MOD_ID_TFOREST, "trophy", 6, 1), TROPHY_PRODUCTION[6]);
			GTTileDragonEggEnergySiphon.addFakeRecipe(GTMaterialGen.getModMetaItem(GTValues.MOD_ID_TFOREST, "trophy", 2, 1), TROPHY_PRODUCTION[2]);
			GTTileDragonEggEnergySiphon.addFakeRecipe(GTMaterialGen.getModMetaItem(GTValues.MOD_ID_TFOREST, "trophy", 4, 1), TROPHY_PRODUCTION[4]);
			GTTileDragonEggEnergySiphon.addFakeRecipe(GTMaterialGen.getModMetaItem(GTValues.MOD_ID_TFOREST, "trophy", 3, 1), TROPHY_PRODUCTION[3]);
			GTTileDragonEggEnergySiphon.addFakeRecipe(GTMaterialGen.getModMetaItem(GTValues.MOD_ID_TFOREST, "trophy", 5, 1), TROPHY_PRODUCTION[5]);
		}
		/* Increasing durability of some items from TForest like GT6 */
		if (GTConfig.general.enableBetterTwilightDurability) {
			TFItems.crumble_horn.setMaxDamage(10000);
			TFItems.peacock_fan.setMaxDamage(10000);
			TFItems.ore_magnet.setMaxDamage(10000);
			TFItems.giant_pickaxe.setMaxDamage(10000);
		}
	}

	public static boolean isTwilightForest(World world) {
		return TFWorld.isTwilightForest(world);
	}

	public static int getTrophyProduction(World world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof TileEntityTFTrophy) {
			TileEntityTFTrophy trophy = (TileEntityTFTrophy) tile;
			BossVariant.getVariant(trophy.getSkullType());
			int type = trophy.getSkullType();
			return TROPHY_PRODUCTION[type];
		}
		return 0;
	}
}
