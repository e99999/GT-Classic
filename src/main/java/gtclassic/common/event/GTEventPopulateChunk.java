package gtclassic.common.event;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GTEventPopulateChunk {

	public static final Block BLOCK_GRAVEL = Blocks.GRAVEL; // change this to suit your need
	public static final Block BLOCK_SAND = Blocks.SAND; // change this to suit your need
	// protected static final IBlockState BLOCKSTATE_RED_SAND =
	// Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT,
	// BlockSand.EnumType.RED_SAND);

	/*
	 * Makes oceans have sand, thanks to Jabelar for finding this work around
	 * https://www.minecraftforge.net/forum/topic/62021-replace-blocks-while-the-
	 * world-generates/
	 */
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onEvent(PopulateChunkEvent.Post event) {
		World world = event.getWorld();
		if (!world.provider.getDimensionType().equals(DimensionType.OVERWORLD)) {
			return;
		}
		Chunk chunk = world.getChunk(event.getChunkX(), event.getChunkZ());
		for (int x = 0; x < 16; ++x) {
			for (int z = 0; z < 16; ++z) {
				Biome biomegenbase = world.getBiome(new BlockPos(chunk.x * 16 + x, 128, chunk.z * 16 + z));
				if (BiomeDictionary.hasType(biomegenbase, Type.OCEAN)
						|| BiomeDictionary.hasType(biomegenbase, Type.BEACH)) {
					for (int y = 30; y < 60; ++y) {
						if (chunk.getBlockState(x, y, z).getBlock() == BLOCK_GRAVEL) {
							chunk.setBlockState(new BlockPos(x, y, z), BLOCK_SAND.getDefaultState());
						}
					}
				}
			}
		}
		chunk.markDirty();
//		if (GTConfig.general.redSandInForestsAndPlains) {
//			Chunk chunk = world.getChunkFromChunkCoords(event.getChunkX(), event.getChunkZ());
//			for (int x = 0; x < 16; ++x) {
//				for (int z = 0; z < 16; ++z) {
//					Biome biomegenbase = world.getBiome(new BlockPos(chunk.x * 16 + x, 128, chunk.z * 16
//							+ z));
//					if (BiomeDictionary.hasType(biomegenbase, Type.FOREST)
//							|| BiomeDictionary.hasType(biomegenbase, Type.PLAINS)) {
//						for (int y = 30; y < 80; ++y) {
//							if (chunk.getBlockState(x, y, z).getBlock() == BLOCK_SAND) {
//								chunk.setBlockState(new BlockPos(x, y, z), BLOCKSTATE_RED_SAND);
//							}
//						}
//					}
//				}
//			}
//			chunk.markDirty();
//		}
	}
}
