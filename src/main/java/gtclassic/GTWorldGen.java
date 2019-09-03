package gtclassic;

import java.util.Random;

import com.google.common.base.Predicate;

import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.worldgen.GTWorldGenFluidSphere;
import gtclassic.worldgen.GTWorldGenOreOcean;
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
import net.minecraft.world.gen.feature.WorldGenerator;
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
			generateFluidSphere(GTMaterial.Helium, 1, 48, 20, 60, random, chunkZ, chunkZ, world);
			generateFluidSphere(GTMaterial.Helium3, 0, 32, 20, 60, random, chunkZ, chunkZ, world);
			generateFluidSphere(GTMaterial.Deuterium, 0, 32, 20, 60, random, chunkZ, chunkZ, world);
		}
		// Default World Gen
		if (GTConfig.bauxiteGenerate && (BiomeDictionary.hasType(biomegenbase, Type.FOREST)
				|| (BiomeDictionary.hasType(biomegenbase, Type.PLAINS)))) {
			generateOre(GTBlocks.oreBauxite.getDefaultState(), GTConfig.bauxiteSize, GTConfig.bauxiteWeight, 50, 120, BlockMatcher.forBlock(Blocks.STONE), world, random, chunkX, chunkZ);
		}
		if (GTConfig.iridiumGenerate) {
			generateOre(GTBlocks.oreIridium.getDefaultState(), GTConfig.iridiumSize, GTConfig.iridiumWeight, 0, 128, BlockMatcher.forBlock(Blocks.STONE), world, random, chunkX, chunkZ);
		}
		if (GTConfig.rubyGenerate && BiomeDictionary.hasType(biomegenbase, Type.HOT)) {
			generateOre(GTBlocks.oreRuby.getDefaultState(), GTConfig.rubySize, GTConfig.rubyWeight, 0, 48, BlockMatcher.forBlock(Blocks.STONE), world, random, chunkX, chunkZ);
		}
		if (BiomeDictionary.hasType(biomegenbase, Type.OCEAN) || BiomeDictionary.hasType(biomegenbase, Type.BEACH)) {
			if (GTConfig.sapphireGenerate) {
				generateOre(GTBlocks.oreSapphire.getDefaultState(), GTConfig.sapphireSize, GTConfig.sapphireWeight, 0, 48, BlockMatcher.forBlock(Blocks.STONE), world, random, chunkX, chunkZ);
			}
			WorldGenerator genOceanDeposit = new GTWorldGenOreOcean();
			genOceanDeposit.generate(world, random, new BlockPos(chunkX * 16 + 16, 32, chunkZ * 16 + 16));
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
	public void generateOre(IBlockState blockToGen, int blockAmount, int chancesToSpawn, int minHeight, int maxHeight,
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

	public void generateFluidSphere(GTMaterial mat, int size, int rarityToSpawn, int minHeight, int maxHeight,
			Random rand, int chunkX, int chunkZ, World world) {
		if (rand.nextInt(rarityToSpawn) == 1) {
			WorldGenerator generator = new GTWorldGenFluidSphere(GTMaterialGen.getFluidBlock(mat), size);
			int heightdiff = maxHeight - minHeight + 1;
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
