package gtclassic.common;

import java.util.Random;

import gtclassic.api.world.GTOreGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.IWorldGenerator;

public class GTWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		Biome biomegenbase = world.getBiome(new BlockPos(chunkX * 16 + 16, 128, chunkZ * 16 + 16));
		// Any Biome
		GTOreGenerator.generateBasicVein(GTBlocks.oreIridium, GTConfig.iridiumGenerate, GTConfig.iridiumSize, GTConfig.iridiumWeight, 0, 128, Blocks.STONE, world, random, chunkX, chunkZ);
		// Jungle Biomes
		if (BiomeDictionary.hasType(biomegenbase, Type.JUNGLE)) {
			GTOreGenerator.generateBasicVein(GTBlocks.oreSheldonite, GTConfig.sheldoniteGenerate, GTConfig.sheldoniteSize, GTConfig.sheldoniteWeight, 10, 30, Blocks.STONE, world, random, chunkX, chunkZ);
		}
		// Hot Biomes
		if (BiomeDictionary.hasType(biomegenbase, Type.HOT)) {
			GTOreGenerator.generateBasicVein(GTBlocks.oreRuby, GTConfig.rubyGenerate, GTConfig.rubySize, GTConfig.rubyWeight, 0, 48, Blocks.STONE, world, random, chunkX, chunkZ);
		}
		// Ocean Biomes
		if (BiomeDictionary.hasType(biomegenbase, Type.OCEAN) || BiomeDictionary.hasType(biomegenbase, Type.BEACH)) {
			GTOreGenerator.generateBasicVein(GTBlocks.oreSapphire, GTConfig.sapphireGenerate, GTConfig.sapphireSize, GTConfig.sapphireWeight, 0, 48, Blocks.STONE, world, random, chunkX, chunkZ);
		}
		// Forest or Plains Biomes
		if (BiomeDictionary.hasType(biomegenbase, Type.FOREST)
				|| (BiomeDictionary.hasType(biomegenbase, Type.PLAINS))) {
			GTOreGenerator.generateBasicVein(GTBlocks.oreBauxite, GTConfig.bauxiteGenerate, GTConfig.bauxiteSize, GTConfig.bauxiteWeight, 50, 120, Blocks.STONE, world, random, chunkX, chunkZ);
		}
	}
}
