package gtclassic;

import java.util.Random;

import com.google.common.base.Predicate;

import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
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
		if (GTConfig.genOverworldOre) {
			runGenerator(GTBlocks.oreEnd.getDefaultState(), 4, 4, 10, 80, BlockMatcher.forBlock(Blocks.END_STONE), world, random, chunkX, chunkZ);
			runGenerator(GTBlocks.oreIridium.getDefaultState(), 2, 1, 0, 128, BlockMatcher.forBlock(Blocks.STONE), world, random, chunkX, chunkZ);
			runGenerator(GTBlocks.oreRuby.getDefaultState(), 4, 2, 0, 48, BlockMatcher.forBlock(Blocks.STONE), world, random, chunkX, chunkZ);
			runGenerator(GTBlocks.oreSapphire.getDefaultState(), 4, 2, 0, 48, BlockMatcher.forBlock(Blocks.STONE), world, random, chunkX, chunkZ);
			runGenerator(GTBlocks.oreBauxite.getDefaultState(), 16, 4, 50, 120, BlockMatcher.forBlock(Blocks.STONE), world, random, chunkX, chunkZ);
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

	public static int clip16(int i) {
		return Math.min(i, 16);
	}
}
