package gtclassic;

import java.util.Random;

import com.google.common.base.Predicate;

import gtclassic.ore.GTOreFalling;
import gtclassic.ore.GTOreFlag;
import gtclassic.ore.GTOreRegistry;
import gtclassic.ore.GTOreStone;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class GTWorldGen implements IWorldGenerator {

	/*
	 * The block to generate. (IBlockState) The amount of blocks to generate. (int)
	 * The spawnChance. (int) The minimumHeight. (int) The maximumHeight. (int) The
	 * block to replace. (Predicate<IBlockState>) The world that is being generated.
	 * (World) The random object. (Random) The chunk�s x position. (int) The
	 * chunk�s z position. (int)
	 */

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {

		// Biome biomegenbase = world.getBiome(new BlockPos(chunkX * 16 + 16, 128,
		// chunkZ * 16 + 16));

		switch (world.provider.getDimensionType()) {

		default:
			for (Block block : Block.REGISTRY) {
				if (block instanceof GTOreStone) {

					GTOreStone ore = (GTOreStone) block;
					GTOreRegistry entry = ore.getOreEntry();
					GTOreFlag flag = ore.getOreFlag();

					if (flag.equals(GTOreFlag.STONE) && GTConfig.genOverworldOre) {
						runGenerator(ore.getDefaultState(), entry.getSize(), entry.getChance(), entry.getMinY(), entry.getMaxY(), BlockMatcher.forBlock(flag.getTargetBlock()), world, random, chunkX, chunkZ);
					}

					if (flag.equals(GTOreFlag.NETHER) && GTConfig.genNetherOre) {
						runGenerator(ore.getDefaultState(), clip16(entry.getSize()), entry.getChance(), 0, 128, BlockMatcher.forBlock(flag.getTargetBlock()), world, random, chunkX, chunkZ);
					}

					if (flag.equals(GTOreFlag.END) && GTConfig.genEndOre) {
						runGenerator(ore.getDefaultState(), clip16(entry.getSize()), entry.getChance(), 8, 70, BlockMatcher.forBlock(flag.getTargetBlock()), world, random, chunkX, chunkZ);
					}
					if (flag.equals(GTOreFlag.BEDROCK) && GTConfig.genBedrockOre) {
						runRareGenerator(ore.getDefaultState(), 32, 1, 0, 5, BlockMatcher.forBlock(flag.getTargetBlock()), world, random, chunkX, chunkZ);
					}

				}

				if (block instanceof GTOreFalling) {

					GTOreFalling ore = (GTOreFalling) block;
					GTOreRegistry entry = ore.getOreEntry();
					GTOreFlag flag = ore.getOreFlag();

					if (flag.equals(GTOreFlag.SAND) && GTConfig.genOverworldOre) {
						runGenerator(ore.getDefaultState(), clip16(entry.getSize()), entry.getChance(), entry.getMinY(), entry.getMaxY(), BlockMatcher.forBlock(flag.getTargetBlock()), world, random, chunkX, chunkZ);
					}
					if (flag.equals(GTOreFlag.GRAVEL) && GTConfig.genOverworldOre) {
						runGenerator(ore.getDefaultState(), clip16(entry.getSize()), entry.getChance(), entry.getMinY(), entry.getMaxY(), BlockMatcher.forBlock(flag.getTargetBlock()), world, random, chunkX, chunkZ);
					}
				}
			}
			break;
		}
	}

	private void runGenerator(IBlockState blockToGen, int blockAmount, int chancesToSpawn, int minHeight, int maxHeight,
			Predicate<IBlockState> blockToReplace, World world, Random rand, int chunkX, int chunkZ) {
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
			throw new IllegalArgumentException("Illegal Height Arguments for OreGenerator");

		WorldGenMinable generator = new WorldGenMinable(blockToGen, blockAmount, blockToReplace);
		int heightdiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i++) {
			int x = chunkX * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightdiff);
			int z = chunkZ * 16 + rand.nextInt(16);

			generator.generate(world, rand, new BlockPos(x, y, z));
			// GTMod.logger.info("GregtTech generated " +
			// blockToGen.getBlock().getLocalizedName() + "at X:"+ x + " Y:"+y+" Z:"+z);
		}
	}

	private void runRareGenerator(IBlockState blockToGen, int blockAmount, int chancesToSpawn, int minHeight,
			int maxHeight, Predicate<IBlockState> blockToReplace, World world, Random rand, int chunkX, int chunkZ) {

		WorldGenMinable generator = new WorldGenMinable(blockToGen, blockAmount, blockToReplace);
		int heightdiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i++) {
			int var1 = rand.nextInt(256);
			if (var1 == 0) {
				int x = chunkX * 16 + rand.nextInt(16);
				int y = minHeight + rand.nextInt(heightdiff);
				int z = chunkZ * 16 + rand.nextInt(16);

				generator.generate(world, rand, new BlockPos(x, y, z));
			}
		}
	}

	public static int clip16(int i) {
		return Math.min(i, 16);
	}

}
