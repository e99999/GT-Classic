package gtclassic;

import java.util.Random;

import com.google.common.base.Predicate;

import gtclassic.block.GTBlockOreSand;
import gtclassic.block.GTBlockOreStone;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
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

		Biome biomegenbase = world.getBiome(new BlockPos(chunkX * 16 + 16, 128, chunkZ * 16 + 16));

		switch (world.provider.getDimensionType()) {

		default:
			for (Block block : Block.REGISTRY) {
				if (block instanceof GTBlockOreStone) {
					GTBlockOreStone ore = (GTBlockOreStone) block;
					GTOreRegistry entry = ore.getOreEntry();
					runGenerator(ore.getDefaultState(), entry.getSize(), entry.getChance(), entry.getMinY(),
							entry.getMaxY(), BlockMatcher.forBlock(ore.getOreEntry().getType()), world, random, chunkX,
							chunkZ);
				}
				if (block instanceof GTBlockOreSand) {
					GTBlockOreSand sand = (GTBlockOreSand) block;
					GTOreRegistry entry = sand.getOreEntry();
					runGenerator(sand.getDefaultState(), entry.getSize(), entry.getChance(), entry.getMinY(),
							entry.getMaxY(), BlockMatcher.forBlock(sand.getOreEntry().getType()), world, random, chunkX,
							chunkZ);
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
