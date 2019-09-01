package gtclassic.worldgen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gtclassic.GTBlocks;
import gtclassic.GTConfig;
import ic2.core.platform.registry.Ic2States;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class GTWorldGenOceanOreDeposit extends WorldGenerator {

	static List<IBlockState> oreDepositList = new ArrayList<>();

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		if (rand.nextInt(32) == 1) {
			generateOreDeposit(worldIn, rand, position);
		}
		return true;
	}

	public void generateOreDeposit(World worldIn, Random rand, BlockPos position) {
		IBlockState ore = getRandomOreState(rand);
		// new center pos for the deposite
		BlockPos newPos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(8)
				- rand.nextInt(8), rand.nextInt(8) - rand.nextInt(8));
		// iterating block place attempts
		for (int i = 0; i < 256; ++i) {
			// pos for the specific block in the deposit being generated
			BlockPos blockpos = newPos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4)
					- rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
			// is on the ocean floor surface
			boolean isInWater = worldIn.getBlockState(blockpos.up()).getMaterial() == Material.WATER;
			// has valid ground to make deposit on
			boolean canBePlaced = worldIn.getBlockState(blockpos).getBlock() == Blocks.GRAVEL
					|| worldIn.getBlockState(blockpos).getBlock() == Blocks.SAND;
			// creates the top layer seen by the player
			if (isInWater && canBePlaced) {
				if (rand.nextInt(3) == 0) {
					worldIn.setBlockState(blockpos, ore, 2);
				} else {
					worldIn.setBlockState(blockpos, getRandomFillerState(rand), 2);
				}
			}
			// creates noise below the surface generation
			if (worldIn.getBlockState(blockpos).getBlock() == Blocks.GRAVEL
					|| worldIn.getBlockState(blockpos).getBlock() == Blocks.SAND
					|| worldIn.getBlockState(blockpos).getBlock() == Blocks.STONE) {
				if (rand.nextInt(3) == 0) {
					worldIn.setBlockState(blockpos, ore, 2);
				} else {
					worldIn.setBlockState(blockpos, getRandomFillerState(rand), 2);
				}
			}
		}
	}

	public IBlockState getRandomFillerState(Random rand) {
		switch (rand.nextInt(4)) {
		case 0:
			return Blocks.MAGMA.getDefaultState();
		case 1:
			return Blocks.OBSIDIAN.getDefaultState();
		case 2:
			return Blocks.SANDSTONE.getDefaultState();
		default:
			return Blocks.STONE.getDefaultState();
		}
	}

	public IBlockState getRandomOreState(Random rand) {
		return !oreDepositList.isEmpty() ? oreDepositList.get(rand.nextInt(oreDepositList.size()))
				: Blocks.STONE.getDefaultState();
	}

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
