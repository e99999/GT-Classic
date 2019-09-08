package gtclassic.worldgen;

import java.util.ArrayList;
import java.util.List;

import gtclassic.GTBlocks;
import gtclassic.GTConfig;
import ic2.core.platform.registry.Ic2States;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class GTWorldGenOreOcean {

	public static List<IBlockState> oreDepositList = new ArrayList<>();

	public static void initDepositOres() {
		if (GTConfig.sapphireGenerate) {
			addOreDeposit(GTBlocks.oreSapphire);
		}
		if (GTConfig.rubyGenerate) {
			addOreDeposit(GTBlocks.oreRuby);
		}
		if (GTConfig.bauxiteGenerate) {
			addOreDeposit(GTBlocks.oreBauxite);
		}
		if (GTConfig.platinumGenerate) {
			addOreDeposit(GTBlocks.orePlatinum);
		}
		addOreDeposit(Blocks.COAL_ORE);
		addOreDeposit(Blocks.DIAMOND_ORE);
		addOreDeposit(Blocks.EMERALD_ORE);
		addOreDeposit(Blocks.GOLD_ORE);
		addOreDeposit(Blocks.IRON_ORE);
		addOreDeposit(Blocks.LAPIS_ORE);
		addOreDeposit(Blocks.REDSTONE_ORE);
		addOreDeposit(Blocks.STONE);
		addOreDeposit(Ic2States.copperOre);
		addOreDeposit(Ic2States.tinOre);
		addOreDeposit(Ic2States.silverOre);
		addOreDeposit(Ic2States.uraniumOre);
	}

	public static void addOreDeposit(Block block) {
		oreDepositList.add(block.getDefaultState());
	}

	public static void addOreDeposit(IBlockState state) {
		oreDepositList.add(state);
	}
}
