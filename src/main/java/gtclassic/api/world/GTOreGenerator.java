package gtclassic.api.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockMatcher;
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

	/** rare ore generator **/
	public static void generateRareVein(Block block, boolean generate, int blockAmount, int chancesToSpawn,
			int minHeight, int maxHeight, Block blockToReplace, World world, Random rand, int chunkX, int chunkZ) {
		if (generate) {
			WorldGenMinable generator = new WorldGenMinable(block.getDefaultState(), blockAmount, BlockMatcher.forBlock(blockToReplace));
			int heightdiff = maxHeight - minHeight + 1;
			for (int i = 0; i < chancesToSpawn; i++) {
				int var1 = rand.nextInt(256);
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
