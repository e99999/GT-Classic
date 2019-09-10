package gtclassic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.worldgen.GTWorldGenFluid;
import gtclassic.worldgen.GTWorldGenOceanDeposit;
import ic2.core.platform.registry.Ic2States;
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

	protected static List<IBlockState> oreDepositList = new ArrayList<>();

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		Biome biomegenbase = world.getBiome(new BlockPos(chunkX * 16 + 16, 128, chunkZ * 16 + 16));
		if (world.provider.getDimensionType().equals(DimensionType.NETHER)) {
			generateFluid(GTMaterialGen.getFluidBlock(GTMaterial.Iron), 5, 2, 0, 128, Blocks.NETHERRACK, world, random, chunkX, chunkZ);
			generateFluid(GTMaterialGen.getFluidBlock(GTMaterial.Gold), 3, 1, 0, 64, Blocks.NETHERRACK, world, random, chunkX, chunkZ);
			generateFluid(GTMaterialGen.getFluidBlock(GTMaterial.Silver), 3, 1, 0, 64, Blocks.NETHERRACK, world, random, chunkX, chunkZ);
			generateFluid(GTMaterialGen.getFluidBlock(GTMaterial.Electrum), 3, 1, 0, 64, Blocks.NETHERRACK, world, random, chunkX, chunkZ);
			generateFluid(GTMaterialGen.getFluidBlock(GTMaterial.Uranium), 3, 1, 0, 40, Blocks.NETHERRACK, world, random, chunkX, chunkZ);
			generateFluid(GTMaterialGen.getFluidBlock(GTMaterial.Platinum), 3, 1, 0, 50, Blocks.NETHERRACK, world, random, chunkX, chunkZ);
		}
		// End Generation
		if (world.provider.getDimensionType().equals(DimensionType.THE_END)) {
			generateFluid(GTMaterialGen.getFluidBlock(GTMaterial.Helium), 8, 1, 20, 60, Blocks.END_STONE, world, random, chunkX, chunkZ);
			generateFluid(GTMaterialGen.getFluidBlock(GTMaterial.Helium3), 5, 1, 20, 60, Blocks.END_STONE, world, random, chunkX, chunkZ);
			generateFluid(GTMaterialGen.getFluidBlock(GTMaterial.Deuterium), 5, 1, 20, 60, Blocks.END_STONE, world, random, chunkX, chunkZ);
		}
		// Default World Gen
		if (GTConfig.iridiumGenerate) {
			generate(GTBlocks.oreIridium, GTConfig.iridiumSize, GTConfig.iridiumWeight, 0, 128, Blocks.STONE, world, random, chunkX, chunkZ);
		}
		if (GTConfig.platinumGenerate && BiomeDictionary.hasType(biomegenbase, Type.JUNGLE)) {
			generate(GTBlocks.orePlatinum, GTConfig.platinumSize, GTConfig.platinumWeight, 10, 30, Blocks.STONE, world, random, chunkX, chunkZ);
		}
		if (BiomeDictionary.hasType(biomegenbase, Type.HOT)) {
			if (GTConfig.rubyGenerate) {
				generate(GTBlocks.oreRuby, GTConfig.rubySize, GTConfig.rubyWeight, 0, 48, Blocks.STONE, world, random, chunkX, chunkZ);
			}
			generate(GTMaterialGen.getFluidBlock(GTMaterial.Mercury), 3, 1, 5, 15, Blocks.STONE, world, random, chunkX, chunkZ);
		}
		if (BiomeDictionary.hasType(biomegenbase, Type.OCEAN) || BiomeDictionary.hasType(biomegenbase, Type.BEACH)) {
			if (GTConfig.sapphireGenerate) {
				generate(GTBlocks.oreSapphire, GTConfig.sapphireSize, GTConfig.sapphireWeight, 0, 48, Blocks.STONE, world, random, chunkX, chunkZ);
			}
			for (IBlockState state : oreDepositList) {
				generateOceanDeposit(state, 36, 1, 28, 38, world, random, chunkX, chunkZ);
			}
		}
		if (BiomeDictionary.hasType(biomegenbase, Type.FOREST) || (BiomeDictionary.hasType(biomegenbase, Type.PLAINS))) {
			if (GTConfig.bauxiteGenerate) {
				generate(GTBlocks.oreBauxite, GTConfig.bauxiteSize, GTConfig.bauxiteWeight, 50, 120, Blocks.STONE, world, random, chunkX, chunkZ);
			}
			generateRareFluid(GTMaterialGen.getFluidBlock(GTMaterial.Methane), 16, 4, 10, 30, Blocks.STONE, world, random, chunkX, chunkZ);
		}
		if (BiomeDictionary.hasType(biomegenbase, Type.SNOWY) || BiomeDictionary.hasType(biomegenbase, Type.COLD)) {
			generateRareFluid(GTMaterialGen.getFluidBlock(GTMaterial.Methane), 32, 2, 10, 30, Blocks.STONE, world, random, chunkX, chunkZ);
			generateRareFluid(GTMaterialGen.getFluidBlock(GTMaterial.Methane), 64, 2, 10, 30, Blocks.STONE, world, random, chunkX, chunkZ);
		}
		if (BiomeDictionary.hasType(biomegenbase, Type.MAGICAL)) {
			generateRareFluid(GTMaterialGen.getFluidBlock(GTMaterial.Neon), 5, 3, 8, 24, Blocks.STONE, world, random, chunkX, chunkZ);
			generateRareFluid(GTMaterialGen.getFluidBlock(GTMaterial.Argon), 5, 3, 8, 24, Blocks.STONE, world, random, chunkX, chunkZ);
		}
	}

	public static void initDepositOres() {
		if (GTConfig.sapphireGenerate) {
			addOreDeposit(GTBlocks.oreSapphire);
		}
		if (GTConfig.rubyGenerate) {
			addOreDeposit(GTBlocks.oreRuby);
		}
		if (GTConfig.bauxiteGenerate) {
			addOreDeposit(GTBlocks.oreBauxite);
		}
		if (GTConfig.platinumGenerate) {
			addOreDeposit(GTBlocks.orePlatinum);
		}
		addOreDeposit(Blocks.COAL_ORE);
		addOreDeposit(Blocks.DIAMOND_ORE);
		addOreDeposit(Blocks.EMERALD_ORE);
		addOreDeposit(Blocks.GOLD_ORE);
		addOreDeposit(Blocks.IRON_ORE);
		addOreDeposit(Blocks.LAPIS_ORE);
		addOreDeposit(Blocks.REDSTONE_ORE);
		addOreDeposit(Blocks.STONE);
		addOreDeposit(Ic2States.copperOre);
		addOreDeposit(Ic2States.tinOre);
		addOreDeposit(Ic2States.silverOre);
		addOreDeposit(Ic2States.uraniumOre);
	}

	/** default ore generator **/
	public void generate(Block block, int blockAmount, int chancesToSpawn, int minHeight, int maxHeight,
			Block blockToReplace, World world, Random rand, int chunkX, int chunkZ) {
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
			throw new IllegalArgumentException("Illegal Height Arguments for Ore Generator");
		WorldGenMinable generator = new WorldGenMinable(block.getDefaultState(), blockAmount, BlockMatcher.forBlock(blockToReplace));
		int heightdiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i++) {
			int x = chunkX * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightdiff);
			int z = chunkZ * 16 + rand.nextInt(16);
			generator.generate(world, rand, new BlockPos(x, y, z));
		}
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
	public void generateFluid(Block block, int blockAmount, int chancesToSpawn, int minHeight, int maxHeight,
			Block blockToReplace, World world, Random rand, int chunkX, int chunkZ) {
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
			throw new IllegalArgumentException("Illegal Height Arguments for Fluid Generator");
		GTWorldGenFluid generator = new GTWorldGenFluid(block.getDefaultState(), blockAmount, BlockMatcher.forBlock(blockToReplace));
		int heightdiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i++) {
			int x = chunkX * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightdiff);
			int z = chunkZ * 16 + rand.nextInt(16);
			generator.generate(world, rand, new BlockPos(x, y, z));
		}
	}

	/** infrequent fluid generator **/
	public void generateRareFluid(Block block, int blockAmount, int chancesToSpawn, int minHeight, int maxHeight,
			Block blockToReplace, World world, Random rand, int chunkX, int chunkZ) {
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
			throw new IllegalArgumentException("Illegal Height Arguments for Fluid Generator");
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

	/** ocean deposit/vein generator not for you to use, add to the list **/
	private void generateOceanDeposit(IBlockState state, int blockAmount, int chancesToSpawn, int minHeight,
			int maxHeight, World world, Random rand, int chunkX, int chunkZ) {
		GTWorldGenOceanDeposit generator = new GTWorldGenOceanDeposit(state, blockAmount);
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

	/**
	 * Adds a block and return the default state for possible ocean floor ore
	 * deposits
	 * 
	 * @param block
	 */
	public static void addOreDeposit(Block block) {
		oreDepositList.add(block.getDefaultState());
	}

	/**
	 * Adds a blockstate directly to the ocean floor ore deposits list
	 * 
	 * @param block
	 */
	public static void addOreDeposit(IBlockState state) {
		oreDepositList.add(state);
	}

	public static void removeOreDeposit(IBlockState state) {
		oreDepositList.remove(state);
	}

	public static void removeOreDeposit(Block block) {
		oreDepositList.remove(block.getDefaultState());
	}
}
