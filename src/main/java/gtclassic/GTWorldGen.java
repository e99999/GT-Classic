package gtclassic;

import java.util.Random;

import com.google.common.base.Predicate;

import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
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

		Biome biomegenbase = world.getBiome(new BlockPos(chunkX * 16 + 16, 128, chunkZ * 16 + 16));

		switch (world.provider.getDimensionType()) {

		case NETHER:
			if (GTConfig.genNetherTantalite) {
				runGenerator(GTBlocks.tantaliteOre.getDefaultState(), 16, 4, 32, 96,
						BlockMatcher.forBlock(Blocks.NETHERRACK), world, random, chunkX, chunkZ);
			}
			if (GTConfig.genNetherPyrite) {
				runGenerator(GTBlocks.pyriteOre.getDefaultState(), 16, 4, 0, 64,
						BlockMatcher.forBlock(Blocks.NETHERRACK), world, random, chunkX, chunkZ);
			}
			if (GTConfig.genNetherCinnabar) {
				runGenerator(GTBlocks.cinnabarOre.getDefaultState(), 16, 4, 64, 128,
						BlockMatcher.forBlock(Blocks.NETHERRACK), world, random, chunkX, chunkZ);
			}
			if (GTConfig.genNetherSphalerite) {
				runGenerator(GTBlocks.sphaleriteOre.getDefaultState(), 16, 4, 32, 96,
						BlockMatcher.forBlock(Blocks.NETHERRACK), world, random, chunkX, chunkZ);
			}
			break;

		case THE_END:
			if (GTConfig.genEndAsteroids) {
				runAsteroidGenerator(Blocks.END_STONE.getDefaultState(), 64, 1, 80, 127,
						BlockMatcher.forBlock(Blocks.AIR), world, random, chunkX, chunkZ);
				runAsteroidGenerator(Blocks.OBSIDIAN.getDefaultState(), 32, 2, 80, 127,
						BlockMatcher.forBlock(Blocks.AIR), world, random, chunkX, chunkZ);
			}
			if (GTConfig.genEndAsteroids && GTConfig.genEndTungstate) {
				runGenerator(GTBlocks.tungstateOre.getDefaultState(), 16, 32, 80, 127,
						BlockMatcher.forBlock(Blocks.END_STONE), world, random, chunkX, chunkZ);
			}
			if (GTConfig.genEndAsteroids && GTConfig.genEndSheldonite) {
				runGenerator(GTBlocks.sheldoniteOre.getDefaultState(), 8, 16, 80, 127,
						BlockMatcher.forBlock(Blocks.END_STONE), world, random, chunkX, chunkZ);
			}
			if (GTConfig.genEndAsteroids && GTConfig.genEndSodalite) {
				runGenerator(GTBlocks.sodaliteOre.getDefaultState(), 16, 32, 80, 127,
						BlockMatcher.forBlock(Blocks.END_STONE), world, random, chunkX, chunkZ);
			}
			if (GTConfig.genEndAsteroids && GTConfig.genEndOlivine) {
				runGenerator(GTBlocks.olivineOre.getDefaultState(), 16, 32, 80, 127,
						BlockMatcher.forBlock(Blocks.END_STONE), world, random, chunkX, chunkZ);
			}
			if (GTConfig.genEndAsteroids) {
				runGenerator(Blocks.OBSIDIAN.getDefaultState(), 4, 2048, 80, 127,
						BlockMatcher.forBlock(Blocks.END_STONE), world, random, chunkX, chunkZ);
			}
			if (GTConfig.genEndTungstate) {
				runGenerator(GTBlocks.tungstateOre.getDefaultState(), 16, 2, 10, 64,
						BlockMatcher.forBlock(Blocks.END_STONE), world, random, chunkX, chunkZ);
			}
			if (GTConfig.genEndSheldonite) {
				runGenerator(GTBlocks.sheldoniteOre.getDefaultState(), 8, 2, 10, 40,
						BlockMatcher.forBlock(Blocks.END_STONE), world, random, chunkX, chunkZ);
			}
			if (GTConfig.genEndSodalite) {
				runGenerator(GTBlocks.sodaliteOre.getDefaultState(), 16, 2, 10, 64,
						BlockMatcher.forBlock(Blocks.END_STONE), world, random, chunkX, chunkZ);
			}
			if (GTConfig.genEndOlivine) {
				runGenerator(GTBlocks.olivineOre.getDefaultState(), 16, 2, 10, 64,
						BlockMatcher.forBlock(Blocks.END_STONE), world, random, chunkX, chunkZ);
			}
			break;

		default:

			if (GTConfig.genOverworldGalena) {
				runGenerator(GTBlocks.galenaOre.getDefaultState(), 8, 2, 0, 48, BlockMatcher.forBlock(Blocks.STONE),
						world, random, chunkX, chunkZ);
			}
			if (GTConfig.genOverworldCalcite) {
				runGenerator(GTBlocks.calciteOre.getDefaultState(), 12, 2, 0, 48, BlockMatcher.forBlock(Blocks.STONE),
						world, random, chunkX, chunkZ);
			}

			if (GTConfig.genOverworldIridium) {
				runGenerator(GTBlocks.iridiumOre.getDefaultState(), 2, 2, 0, 128, BlockMatcher.forBlock(Blocks.STONE),
						world, random, chunkX, chunkZ);
			}

			if (BiomeDictionary.hasType(biomegenbase, Type.HOT) && (GTConfig.genOverworldRuby)) {
				runGenerator(GTBlocks.rubyOre.getDefaultState(), 3, 16, 0, 48, BlockMatcher.forBlock(Blocks.STONE),
						world, random, chunkX, chunkZ);
			}

			if ((BiomeDictionary.hasType(biomegenbase, Type.OCEAN)
					|| (BiomeDictionary.hasType(biomegenbase, Type.COLD) && ((GTConfig.genOverworldSapphire))))) {
				runGenerator(GTBlocks.sapphireOre.getDefaultState(), 3, 16, 0, 48, BlockMatcher.forBlock(Blocks.STONE),
						world, random, chunkX, chunkZ);
			}

			if (BiomeDictionary.hasType(biomegenbase, Type.RIVER) && ((GTConfig.genOverworldBlackSand))) {
				runGenerator(GTBlocks.magnetiteSand.getDefaultState(), 32, 4, 48, 72,
						BlockMatcher.forBlock(Blocks.SAND), world, random, chunkX, chunkZ);
			}

			if ((BiomeDictionary.hasType(biomegenbase, Type.FOREST)
					|| (BiomeDictionary.hasType(biomegenbase, Type.PLAINS)) && (GTConfig.genOverworldBauxite))) {
				runGenerator(GTBlocks.bauxiteOre.getDefaultState(), 16, 8, 40, 120, BlockMatcher.forBlock(Blocks.STONE),
						world, random, chunkX, chunkZ);
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
		}
	}

	private void runAsteroidGenerator(IBlockState blockToGen, int blockAmount, int chancesToSpawn, int minHeight,
			int maxHeight, Predicate<IBlockState> blockToReplace, World world, Random rand, int chunkX, int chunkZ) {

		WorldGenMinable generator = new WorldGenMinable(blockToGen, blockAmount, blockToReplace);
		int heightdiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i++) {
			int var1 = rand.nextInt(128);
			if (var1 == 0) {
				int x = chunkX * 16 + rand.nextInt(16);
				int y = minHeight + rand.nextInt(heightdiff);
				int z = chunkZ * 16 + rand.nextInt(16);

				generator.generate(world, rand, new BlockPos(x, y, z));
			}
		}
	}

}
