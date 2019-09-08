package gtclassic;

import java.util.Random;

import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.worldgen.GTWorldGenOceanMineable;
import gtclassic.worldgen.GTWorldGenOreOcean;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
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
		// End Generation
		if (world.provider.getDimensionType().equals(DimensionType.THE_END)) {
			// generateFluidSphere(GTMaterial.Helium, 1, 48, 20, 60, random, chunkZ, chunkZ,
			// world);
			// generateFluidSphere(GTMaterial.Helium3, 0, 32, 20, 60, random, chunkZ,
			// chunkZ, world);
			// generateFluidSphere(GTMaterial.Deuterium, 0, 32, 20, 60, random, chunkZ,
			// chunkZ, world);
			generate(GTMaterialGen.getFluidBlock(GTMaterial.Helium), 8, 1, 20, 60, Blocks.END_STONE, world, random, chunkX, chunkZ);
			generate(GTMaterialGen.getFluidBlock(GTMaterial.Helium3), 5, 1, 20, 60, Blocks.END_STONE, world, random, chunkX, chunkZ);
			generate(GTMaterialGen.getFluidBlock(GTMaterial.Deuterium), 5, 1, 20, 60, Blocks.END_STONE, world, random, chunkX, chunkZ);
		}
		// Default World Gen
		if (GTConfig.iridiumGenerate) {
			generate(GTBlocks.oreIridium, GTConfig.iridiumSize, GTConfig.iridiumWeight, 0, 128, Blocks.STONE, world, random, chunkX, chunkZ);
		}
		if (GTConfig.platinumGenerate && BiomeDictionary.hasType(biomegenbase, Type.JUNGLE)) {
			generate(GTBlocks.orePlatinum, GTConfig.platinumSize, GTConfig.platinumWeight, 10, 30, Blocks.STONE, world, random, chunkX, chunkZ);
		}
		if (GTConfig.rubyGenerate && BiomeDictionary.hasType(biomegenbase, Type.HOT)) {
			generate(GTBlocks.oreRuby, GTConfig.rubySize, GTConfig.rubyWeight, 0, 48, Blocks.STONE, world, random, chunkX, chunkZ);
		}
		if (BiomeDictionary.hasType(biomegenbase, Type.OCEAN) || BiomeDictionary.hasType(biomegenbase, Type.BEACH)) {
			if (GTConfig.sapphireGenerate) {
				generate(GTBlocks.oreSapphire, GTConfig.sapphireSize, GTConfig.sapphireWeight, 0, 48, Blocks.STONE, world, random, chunkX, chunkZ);
			}
			for (IBlockState state : GTWorldGenOreOcean.oreDepositList) {
				generateOceanDeposit(state, 36, 1, 28, 38, world, random, chunkX, chunkZ);
			}
		}
		if (GTConfig.bauxiteGenerate && (BiomeDictionary.hasType(biomegenbase, Type.FOREST)
				|| (BiomeDictionary.hasType(biomegenbase, Type.PLAINS)))) {
			generate(GTBlocks.oreBauxite, GTConfig.bauxiteSize, GTConfig.bauxiteWeight, 50, 120, Blocks.STONE, world, random, chunkX, chunkZ);
		}
		if (BiomeDictionary.hasType(biomegenbase, Type.SNOWY) || BiomeDictionary.hasType(biomegenbase, Type.COLD)) {
			generateRare(GTMaterialGen.getFluidBlock(GTMaterial.Methane), 32, 2, 10, 30, Blocks.STONE, world, random, chunkX, chunkZ);
			generateRare(GTMaterialGen.getFluidBlock(GTMaterial.Methane), 64, 2, 10, 30, Blocks.STONE, world, random, chunkX, chunkZ);
		}
	}

	/**
	 * Generates ore veins in a vanilla style.
	 * 
	 * @param Blockstate     - state of the ore block to gen
	 * @param blockAmount    int - amount of ore in a vein
	 * @param chancesToSpawn int - chances to spawn
	 * @param minHeight      int - min Y level
	 * @param maxHeight      int - max Y level
	 * @param blockToReplace - the blockstate to replace
	 * @param world          World - pass the world arg
	 * @param rand           Rand - pass the rand arg
	 * @param chunkX         - pass chunkX arg
	 * @param chunkZ         - pass chunkY arg
	 */
	public void generate(Block block, int blockAmount, int chancesToSpawn, int minHeight, int maxHeight,
			Block blockToReplace, World world, Random rand, int chunkX, int chunkZ) {
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
			throw new IllegalArgumentException("Illegal Height Arguments for OreGenerator");
		WorldGenMinable generator = new WorldGenMinable(block.getDefaultState(), blockAmount, BlockMatcher.forBlock(blockToReplace));
		int heightdiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i++) {
			int x = chunkX * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightdiff);
			int z = chunkZ * 16 + rand.nextInt(16);
			generator.generate(world, rand, new BlockPos(x, y, z));
		}
	}

	public void generateRare(Block block, int blockAmount, int chancesToSpawn, int minHeight, int maxHeight,
			Block blockToReplace, World world, Random rand, int chunkX, int chunkZ) {
		WorldGenMinable generator = new WorldGenMinable(block.getDefaultState(), blockAmount, BlockMatcher.forBlock(blockToReplace));
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

	public void generateOceanDeposit(IBlockState state, int blockAmount, int chancesToSpawn, int minHeight,
			int maxHeight, World world, Random rand, int chunkX, int chunkZ) {
		GTWorldGenOceanMineable generator = new GTWorldGenOceanMineable(state, blockAmount);
		int heightdiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i++) {
			int var1 = rand.nextInt(96);
			if (var1 == 0) {
				int x = chunkX * 16 + rand.nextInt(16);
				int y = minHeight + rand.nextInt(heightdiff);
				int z = chunkZ * 16 + rand.nextInt(16);
				generator.generate(world, rand, new BlockPos(x, y, z));
			}
		}
	}

	public void generateOceanDeposit(Block block, int blockAmount, int chancesToSpawn, int minHeight, int maxHeight,
			World world, Random rand, int chunkX, int chunkZ) {
		generateOceanDeposit(block.getDefaultState(), blockAmount, chancesToSpawn, minHeight, maxHeight, world, rand, chunkX, chunkZ);
	}
}
