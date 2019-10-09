package gtclassic;

import java.util.Random;

import net.minecraft.block.Block;
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
		// Any Biome
		generate(GTBlocks.oreIridium, GTConfig.iridiumGenerate, GTConfig.iridiumSize, GTConfig.iridiumWeight, 0, 128, Blocks.STONE, world, random, chunkX, chunkZ);
		// Jungle Biomes
		if (BiomeDictionary.hasType(biomegenbase, Type.JUNGLE)) {
			generate(GTBlocks.oreSheldonite, GTConfig.sheldoniteGenerate, GTConfig.sheldoniteSize, GTConfig.sheldoniteWeight, 10, 30, Blocks.STONE, world, random, chunkX, chunkZ);
		}
		// Hot Biomes
		if (BiomeDictionary.hasType(biomegenbase, Type.HOT)) {
			generate(GTBlocks.oreRuby, GTConfig.rubyGenerate, GTConfig.rubySize, GTConfig.rubyWeight, 0, 48, Blocks.STONE, world, random, chunkX, chunkZ);
		}
		// Ocean Biomes
		if (BiomeDictionary.hasType(biomegenbase, Type.OCEAN) || BiomeDictionary.hasType(biomegenbase, Type.BEACH)) {
			generate(GTBlocks.oreSapphire, GTConfig.sapphireGenerate, GTConfig.sapphireSize, GTConfig.sapphireWeight, 0, 48, Blocks.STONE, world, random, chunkX, chunkZ);
		}
		// Forest or Plains Biomes
		if (BiomeDictionary.hasType(biomegenbase, Type.FOREST)
				|| (BiomeDictionary.hasType(biomegenbase, Type.PLAINS))) {
			generate(GTBlocks.oreBauxite, GTConfig.bauxiteGenerate, GTConfig.bauxiteSize, GTConfig.bauxiteWeight, 50, 120, Blocks.STONE, world, random, chunkX, chunkZ);
		}
	}

	/** default ore generator **/
	public void generate(Block block, boolean generate, int blockAmount, int chancesToSpawn, int minHeight,
			int maxHeight, Block blockToReplace, World world, Random rand, int chunkX, int chunkZ) {
		if (generate) {
			WorldGenMinable generator = new WorldGenMinable(block.getDefaultState(), blockAmount, BlockMatcher.forBlock(blockToReplace));
			int heightdiff = maxHeight - minHeight + 1;
			for (int i = 0; i < chancesToSpawn; i++) {
				int x = chunkX * 16 + rand.nextInt(16);
				int y = minHeight + rand.nextInt(heightdiff);
				int z = chunkZ * 16 + rand.nextInt(16);
				generator.generate(world, rand, new BlockPos(x, y, z));
			}
		}
	}

	/** overloaded version for anyone using the old generator **/
	@Deprecated
	public void generate(Block block, int blockAmount, int chancesToSpawn, int minHeight, int maxHeight,
			Block blockToReplace, World world, Random rand, int chunkX, int chunkZ) {
		generate(block, true, blockAmount, chancesToSpawn, minHeight, maxHeight, blockToReplace, world, rand, chunkX, chunkZ);
	}
}
