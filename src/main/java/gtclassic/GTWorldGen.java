package gtclassic;

import java.util.Random;

import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.worldgen.GTWorldGenFluid;
import net.minecraft.block.Block;
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
		// Any Biome
		generate(GTBlocks.oreIridium, GTConfig.iridiumGenerate, GTConfig.iridiumSize, GTConfig.iridiumWeight, 0, 128, Blocks.STONE, world, random, chunkX, chunkZ);
		// Jungle Biomes
		if (BiomeDictionary.hasType(biomegenbase, Type.JUNGLE)) {
			generate(GTBlocks.oreSheldonite, GTConfig.sheldoniteGenerate, GTConfig.sheldoniteSize, GTConfig.sheldoniteWeight, 10, 30, Blocks.STONE, world, random, chunkX, chunkZ);
		}
		// Hot Biomes
		if (BiomeDictionary.hasType(biomegenbase, Type.HOT)) {
			generate(GTBlocks.oreRuby, GTConfig.rubyGenerate, GTConfig.rubySize, GTConfig.rubyWeight, 0, 48, Blocks.STONE, world, random, chunkX, chunkZ);
			generate(GTMaterialGen.getFluidBlock(GTMaterial.Mercury), GTConfig.fluidOverworldGenerate, 3, 2, 5, 15, Blocks.STONE, world, random, chunkX, chunkZ);
		}
		// Ocean Biomes
		if (BiomeDictionary.hasType(biomegenbase, Type.OCEAN) || BiomeDictionary.hasType(biomegenbase, Type.BEACH)) {
			generate(GTBlocks.oreSapphire, GTConfig.sapphireGenerate, GTConfig.sapphireSize, GTConfig.sapphireWeight, 0, 48, Blocks.STONE, world, random, chunkX, chunkZ);
			// if (GTConfig.depositGenerate) {
			// for (IBlockState state : GTWorldGenOceanDeposit.getOreDepositList()) {
			// generateOceanDeposit(state, GTConfig.depositSize, GTConfig.depositWeight, 28,
			// 38, world, random, chunkX, chunkZ);
			// }
			// }
		}
		// Forest or Plains Biomes
		if (BiomeDictionary.hasType(biomegenbase, Type.FOREST)
				|| (BiomeDictionary.hasType(biomegenbase, Type.PLAINS))) {
			generate(GTBlocks.oreBauxite, GTConfig.bauxiteGenerate, GTConfig.bauxiteSize, GTConfig.bauxiteWeight, 50, 120, Blocks.STONE, world, random, chunkX, chunkZ);
			generateRareFluid(GTMaterialGen.getFluidBlock(GTMaterial.Methane), GTConfig.fluidOverworldGenerate, 16, 5, 10, 30, Blocks.STONE, world, random, chunkX, chunkZ);
		}
		// Cold Biomes
		if (BiomeDictionary.hasType(biomegenbase, Type.SNOWY) || BiomeDictionary.hasType(biomegenbase, Type.COLD)) {
			generateRareFluid(GTMaterialGen.getFluidBlock(GTMaterial.Methane), GTConfig.fluidOverworldGenerate, 32, 2, 10, 30, Blocks.STONE, world, random, chunkX, chunkZ);
			generateRareFluid(GTMaterialGen.getFluidBlock(GTMaterial.Methane), GTConfig.fluidOverworldGenerate, 64, 2, 10, 30, Blocks.STONE, world, random, chunkX, chunkZ);
		}
		// Magical Biomes
		if (BiomeDictionary.hasType(biomegenbase, Type.MAGICAL)) {
			generateRareFluid(GTMaterialGen.getFluidBlock(GTMaterial.Neon), GTConfig.fluidOverworldGenerate, 5, 3, 8, 24, Blocks.STONE, world, random, chunkX, chunkZ);
			generateRareFluid(GTMaterialGen.getFluidBlock(GTMaterial.Argon), GTConfig.fluidOverworldGenerate, 5, 3, 8, 24, Blocks.STONE, world, random, chunkX, chunkZ);
		}
		// Nether Generation
		if (GTConfig.fluidNetherGenerate && world.provider.getDimensionType().equals(DimensionType.NETHER)) {
			generateFluid(GTMaterialGen.getFluidBlock(GTMaterial.Iron), 5, 2, 0, 128, Blocks.NETHERRACK, world, random, chunkX, chunkZ);
			generateFluid(GTMaterialGen.getFluidBlock(GTMaterial.Gold), 3, 1, 0, 64, Blocks.NETHERRACK, world, random, chunkX, chunkZ);
			generateFluid(GTMaterialGen.getFluidBlock(GTMaterial.Silver), 3, 1, 0, 64, Blocks.NETHERRACK, world, random, chunkX, chunkZ);
			generateFluid(GTMaterialGen.getFluidBlock(GTMaterial.Electrum), 3, 1, 0, 64, Blocks.NETHERRACK, world, random, chunkX, chunkZ);
			generateFluid(GTMaterialGen.getFluidBlock(GTMaterial.Uranium), 3, 1, 0, 40, Blocks.NETHERRACK, world, random, chunkX, chunkZ);
			generateFluid(GTMaterialGen.getFluidBlock(GTMaterial.Platinum), 3, 1, 0, 50, Blocks.NETHERRACK, world, random, chunkX, chunkZ);
		}
		// End Generation
		if (GTConfig.fluidEndGenerate && world.provider.getDimensionType().equals(DimensionType.THE_END)) {
			generateFluid(GTMaterialGen.getFluidBlock(GTMaterial.Helium), 8, 1, 20, 60, Blocks.END_STONE, world, random, chunkX, chunkZ);
			generateFluid(GTMaterialGen.getFluidBlock(GTMaterial.Helium3), 5, 1, 20, 60, Blocks.END_STONE, world, random, chunkX, chunkZ);
			generateFluid(GTMaterialGen.getFluidBlock(GTMaterial.Deuterium), 5, 1, 20, 60, Blocks.END_STONE, world, random, chunkX, chunkZ);
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
	public void generate(Block block, int blockAmount, int chancesToSpawn, int minHeight, int maxHeight,
			Block blockToReplace, World world, Random rand, int chunkX, int chunkZ) {
		generate(block, true, blockAmount, chancesToSpawn, minHeight, maxHeight, blockToReplace, world, rand, chunkX, chunkZ);
	}

	/** infrequent ore generator **/
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

	/** default fluid generator **/
	public void generateFluid(Block block, boolean generate, int blockAmount, int chancesToSpawn, int minHeight,
			int maxHeight, Block blockToReplace, World world, Random rand, int chunkX, int chunkZ) {
		if (generate) {
			GTWorldGenFluid generator = new GTWorldGenFluid(block.getDefaultState(), blockAmount, BlockMatcher.forBlock(blockToReplace));
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
	public void generateFluid(Block block, int blockAmount, int chancesToSpawn, int minHeight, int maxHeight,
			Block blockToReplace, World world, Random rand, int chunkX, int chunkZ) {
		generateFluid(block, true, blockAmount, chancesToSpawn, minHeight, maxHeight, blockToReplace, world, rand, chunkX, chunkZ);
	}

	/** infrequent fluid generator **/
	public void generateRareFluid(Block block, boolean generate, int blockAmount, int chancesToSpawn, int minHeight,
			int maxHeight, Block blockToReplace, World world, Random rand, int chunkX, int chunkZ) {
		if (generate) {
			GTWorldGenFluid generator = new GTWorldGenFluid(block.getDefaultState(), blockAmount, BlockMatcher.forBlock(blockToReplace));
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

	/** overloaded version for anyone using the old generator **/
	public void generateRareFluid(Block block, int blockAmount, int chancesToSpawn, int minHeight, int maxHeight,
			Block blockToReplace, World world, Random rand, int chunkX, int chunkZ) {
		generateRareFluid(block, true, blockAmount, chancesToSpawn, minHeight, maxHeight, blockToReplace, world, rand, chunkX, chunkZ);
	}
//	/** ocean deposit/vein generator not for you to use, add to the list **/
//	private void generateOceanDeposit(IBlockState state, int blockAmount, int chancesToSpawn, int minHeight,
//			int maxHeight, World world, Random rand, int chunkX, int chunkZ) {
//		GTWorldGenOceanDeposit generator = new GTWorldGenOceanDeposit(state, blockAmount);
//		int heightdiff = maxHeight - minHeight + 1;
//		for (int i = 0; i < chancesToSpawn; i++) {
//			int var1 = rand.nextInt(512);
//			if (var1 == 0) {
//				int x = chunkX * 16 + rand.nextInt(16);
//				int y = minHeight + rand.nextInt(heightdiff);
//				int z = chunkZ * 16 + rand.nextInt(16);
//				generator.generate(world, rand, new BlockPos(x, y, z));
//			}
//		}
//	}
}
