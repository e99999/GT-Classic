package gtclassic.worldgen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gtclassic.GTBlocks;
import gtclassic.GTConfig;
import gtclassic.helpers.GTHelperWorld;
import ic2.core.IC2;
import ic2.core.platform.registry.Ic2States;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class GTWorldGenOceanDeposit extends WorldGenerator {

	private final IBlockState oreBlock;
	/** The number of blocks to generate. */
	private final int numberOfBlocks;
	private static List<IBlockState> oreDepositList = new ArrayList<>();

	public GTWorldGenOceanDeposit(IBlockState state, int blockCount) {
		this.oreBlock = state;
		this.numberOfBlocks = blockCount;
	}

	public boolean generate(World worldIn, Random rand, BlockPos position) {
		if (worldIn.getBlockState(position.up()).getMaterial() != Material.WATER) {
			return false;
		}
		float f = rand.nextFloat() * (float) Math.PI;
		double d0 = (double) ((float) (position.getX() + 8) + MathHelper.sin(f) * (float) this.numberOfBlocks / 8.0F);
		double d1 = (double) ((float) (position.getX() + 8) - MathHelper.sin(f) * (float) this.numberOfBlocks / 8.0F);
		double d2 = (double) ((float) (position.getZ() + 8) + MathHelper.cos(f) * (float) this.numberOfBlocks / 8.0F);
		double d3 = (double) ((float) (position.getZ() + 8) - MathHelper.cos(f) * (float) this.numberOfBlocks / 8.0F);
		double d4 = (double) (position.getY() + rand.nextInt(2) - 1);
		double d5 = (double) (position.getY() + rand.nextInt(2) - 1);
		for (int i = 0; i < this.numberOfBlocks; ++i) {
			float f1 = (float) i / (float) this.numberOfBlocks;
			double d6 = d0 + (d1 - d0) * (double) f1;
			double d7 = d4 + (d5 - d4) * (double) f1;
			double d8 = d2 + (d3 - d2) * (double) f1;
			double d9 = rand.nextDouble() * (double) this.numberOfBlocks / 16.0D;
			double d10 = (double) (MathHelper.sin((float) Math.PI * f1) + 1.0F) * d9 + 1.0D;
			double d11 = (double) (MathHelper.sin((float) Math.PI * f1) + 1.0F) * d9 + 1.0D;
			int j = MathHelper.floor(d6 - d10 / 2.0D);
			int k = MathHelper.floor(d7 - d11 / 2.0D);
			int l = MathHelper.floor(d8 - d10 / 2.0D);
			int i1 = MathHelper.floor(d6 + d10 / 2.0D);
			int j1 = MathHelper.floor(d7 + d11 / 2.0D);
			int k1 = MathHelper.floor(d8 + d10 / 2.0D);
			for (int l1 = j; l1 <= i1; ++l1) {
				double d12 = ((double) l1 + 0.5D - d6) / (d10 / 2.0D);
				if (d12 * d12 < 1.0D) {
					for (int i2 = k; i2 <= j1; ++i2) {
						double d13 = ((double) i2 + 0.5D - d7) / (d11 / 2.0D);
						if (d12 * d12 + d13 * d13 < 1.0D) {
							for (int j2 = l; j2 <= k1; ++j2) {
								double d14 = ((double) j2 + 0.5D - d8) / (d10 / 2.0D);
								if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D) {
									BlockPos blockpos = new BlockPos(l1, i2, j2);
									IBlockState state = worldIn.getBlockState(blockpos);
									if (isReplaceableOceanGen(state.getBlock())) {
										worldIn.setBlockState(blockpos, getRandomStateForGen(rand), 2);
									}
								}
							}
						}
					}
				}
			}
		}
		GTHelperWorld.notifyPlayersOfGeneration(worldIn, position, "OceanDeposit", this.oreBlock.getBlock().getLocalizedName());
		return true;
	}

	private IBlockState getRandomStateForGen(Random rand) {
		switch (rand.nextInt(6)) {
		case 0:
			return Blocks.MAGMA.getDefaultState();
		case 1:
			return Blocks.OBSIDIAN.getDefaultState();
		case 2:
			return Blocks.SANDSTONE.getDefaultState();
		case 3:
			return Blocks.STONE.getDefaultState();
		default:
			return this.oreBlock;
		}
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
		if (GTConfig.sheldoniteGenerate) {
			addOreDeposit(GTBlocks.oreSheldonite);
		}
		if (IC2.config.getFlag("WorldGenOreCopper")) {
			addOreDeposit(Ic2States.copperOre);
		}
		if (IC2.config.getFlag("WorldGenOreTin")) {
			addOreDeposit(Ic2States.tinOre);
		}
		if (IC2.config.getFlag("WorldGenOreSilver")) {
			addOreDeposit(Ic2States.silverOre);
		}
		if (IC2.config.getFlag("WorldGenOreUranium")) {
			addOreDeposit(Ic2States.uraniumOre);
		}
		addOreDeposit(Blocks.COAL_ORE);
		addOreDeposit(Blocks.DIAMOND_ORE);
		addOreDeposit(Blocks.EMERALD_ORE);
		addOreDeposit(Blocks.GOLD_ORE);
		addOreDeposit(Blocks.IRON_ORE);
		addOreDeposit(Blocks.LAPIS_ORE);
		addOreDeposit(Blocks.REDSTONE_ORE);
		addOreDeposit(Blocks.STONE);
		addOreDeposit(Blocks.OBSIDIAN);
	}

	private boolean isReplaceableOceanGen(Block block) {
		return block == Blocks.GRAVEL || block == Blocks.SAND || block == Blocks.STONE;
	}
	
	public static List<IBlockState> getOreDepositList(){
		return oreDepositList;
	}

	public static void addOreDeposit(Block block) {
		oreDepositList.add(block.getDefaultState());
	}

	public static void addOreDeposit(IBlockState state) {
		oreDepositList.add(state);
	}

	public static void removeOreDeposit(IBlockState state) {
		oreDepositList.remove(state);
	}

	public static void removeOreDeposit(Block block) {
		oreDepositList.remove(block.getDefaultState());
	}
}
