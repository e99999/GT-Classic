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

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		Biome biomegenbase = world.getBiome(new BlockPos(chunkX * 16 + 16, 128, chunkZ * 16 + 16));
		if (GTConfig.bauxiteGenerate && BiomeDictionary.hasType(biomegenbase, Type.FOREST)
				|| (BiomeDictionary.hasType(biomegenbase, Type.PLAINS))) {
			runGenerator(GTBlocks.oreBauxite.getDefaultState(), GTConfig.bauxiteSize, GTConfig.bauxiteWeight, 50, 120, BlockMatcher.forBlock(Blocks.STONE), world, random, chunkX, chunkZ);
		}
		if (GTConfig.iridiumEndGenerate) {
			runGenerator(GTBlocks.oreEnd.getDefaultState(), GTConfig.iridiumEndSize, GTConfig.iridiumEndWeight, 10, 80, BlockMatcher.forBlock(Blocks.END_STONE), world, random, chunkX, chunkZ);
		}
		if (GTConfig.iridiumOverworldGenerate) {
			runGenerator(GTBlocks.oreIridium.getDefaultState(), GTConfig.iridiumOverworldSize, GTConfig.iridiumOverworldWeight, 0, 128, BlockMatcher.forBlock(Blocks.STONE), world, random, chunkX, chunkZ);
		}
		if (GTConfig.rubyGenerate && BiomeDictionary.hasType(biomegenbase, Type.HOT)) {
			runGenerator(GTBlocks.oreRuby.getDefaultState(), GTConfig.rubySize, GTConfig.rubyWeight, 0, 48, BlockMatcher.forBlock(Blocks.STONE), world, random, chunkX, chunkZ);
		}
		if (GTConfig.sapphireGenerate && BiomeDictionary.hasType(biomegenbase, Type.OCEAN)) {
			runGenerator(GTBlocks.oreSapphire.getDefaultState(), GTConfig.sapphireSize, GTConfig.sapphireWeight, 0, 48, BlockMatcher.forBlock(Blocks.STONE), world, random, chunkX, chunkZ);
		}
	}

	/**
	 * Generates ore veins in a vanilla style.
	 * 
	 * @param Blockstate     - state of the ore block to gen
	 * @param                int - amount of ore in a vein
	 * @param chancesToSpawn int - chances to spawn
	 * @param minHeight      int - min Y level
	 * @param maxHeight      int - max Y level
	 * @param blockToReplace - the blockstate to replace
	 * @param world          World - pass the world arg
	 * @param rand           Rand - pass the rand arg
	 * @param chunkX         - pass chunkX arg
	 * @param chunkZ         - pass chunkY arg
	 */
	public void runGenerator(IBlockState blockToGen, int blockAmount, int chancesToSpawn, int minHeight, int maxHeight,
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

	public static int clip16(int i) {
		return Math.min(i, 16);
	}
}
