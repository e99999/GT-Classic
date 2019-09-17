package gtclassic.worldgen;

import gtclassic.GTBlocks;
import gtclassic.GTMod;
import ic2.core.platform.registry.Ic2States;
import twilightforest.world.feature.TFGenCaveStalactite;

public class GTWorldTwilightForest {

	public static void initStalactites() {
		GTMod.logger.info("Adding GregTech and IC2C ores to Twilight Forest hollow hills");
		TFGenCaveStalactite.addStalactite(3, GTBlocks.oreIridium.getDefaultState(), 0.5F, 4, 16, 30);
		TFGenCaveStalactite.addStalactite(2, GTBlocks.oreSheldonite.getDefaultState(), 0.5F, 8, 1, 12);
		TFGenCaveStalactite.addStalactite(2, GTBlocks.oreSapphire.getDefaultState(), 0.6F, 6, 1, 20);
		TFGenCaveStalactite.addStalactite(2, GTBlocks.oreRuby.getDefaultState(), 0.6F, 6, 1, 20);
		TFGenCaveStalactite.addStalactite(1, GTBlocks.oreBauxite.getDefaultState(), 0.8F, 12, 1, 24);
		TFGenCaveStalactite.addStalactite(3, Ic2States.uraniumOre, 0.5F, 3, 12, 15);
		TFGenCaveStalactite.addStalactite(1, Ic2States.copperOre, 0.7F, 9, 1, 24);
		TFGenCaveStalactite.addStalactite(1, Ic2States.tinOre, 0.7F, 9, 1, 24);
		TFGenCaveStalactite.addStalactite(1, Ic2States.silverOre, 0.5F, 8, 1, 12);
	}
}
