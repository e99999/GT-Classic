package gtclassic.api.world;

import java.util.Random;

import gtclassic.common.GTConfig;
import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class GTOreGenerator {

	/** default ore generator **/
	public static void generateBasicVein(Block block, boolean generate, int blockAmount, int chancesToSpawn,
			int minHeight, int maxHeight, Block blockToReplace, World world, Random rand, int chunkX, int chunkZ) {
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

	public static void generateBedrockVein(Block block, World world, Random rand, int chunkX, int chunkZ) {
		GTBedrockOreMineable generator = new GTBedrockOreMineable(block.getDefaultState(), GTConfig.generation.bedrockOreSize, BlockMatcher.forBlock(Blocks.BEDROCK));
		int minHeight = 0;
		int maxHeight = 10;
		int heightdiff = maxHeight - minHeight + 1;
		int chance = 8192;
		for (int i = 0; i < GTConfig.generation.bedrockOreWeight; i++) {
			int var1 = rand.nextInt(chance);
			if (var1 == 0) {
				int x = chunkX * 16 + rand.nextInt(16);
				int y = 0 + rand.nextInt(heightdiff);
				int z = chunkZ * 16 + rand.nextInt(16);
				generator.generate(world, rand, new BlockPos(x, y, z));
			}
		}
	}

	/** rare ore generator **/
	public static void generateRareVein(Block block, boolean generate, int blockAmount, int chancesToSpawn,
			int minHeight, int maxHeight, Block blockToReplace, World world, Random rand, int chunkX, int chunkZ) {
		if (generate) {
			WorldGenMinable generator = new WorldGenMinable(block.getDefaultState(), blockAmount, BlockMatcher.forBlock(blockToReplace));
			int heightdiff = maxHeight - minHeight + 1;
			for (int i = 0; i < chancesToSpawn; i++) {
				int var1 = rand.nextInt(1024);
				if (var1 == 0) {
					int x = chunkX * 16 + rand.nextInt(16);
					int y = minHeight + rand.nextInt(heightdiff);
					int z = chunkZ * 16 + rand.nextInt(16);
					generator.generate(world, rand, new BlockPos(x, y, z));
				}
			}
		}
	}
}
