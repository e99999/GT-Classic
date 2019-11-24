package gtclassic.common.event;

import gtclassic.common.GTConfig;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GTEventPopulateChunk {

	public static final Block fromBlock = Blocks.GRAVEL; // change this to suit your need
	public static final Block toBlock = Blocks.SAND; // change this to suit your need

	/*
	 * Makes oceans have sand, thanks to Jabelar for finding this work around
	 * https://www.minecraftforge.net/forum/topic/62021-replace-blocks-while-the-
	 * world-generates/
	 */
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onEvent(PopulateChunkEvent.Post event) {
		if (GTConfig.general.replaceOceanGravelWithSand) {
			Chunk chunk = event.getWorld().getChunkFromChunkCoords(event.getChunkX(), event.getChunkZ());
			for (int x = 0; x < 16; ++x) {
				for (int z = 0; z < 16; ++z) {
					Biome biomegenbase = event.getWorld().getBiome(new BlockPos(chunk.x * 16 + x, 128, chunk.z * 16
							+ z));
					if (BiomeDictionary.hasType(biomegenbase, Type.OCEAN)
							|| BiomeDictionary.hasType(biomegenbase, Type.BEACH)) {
						for (int y = 30; y < 60; ++y) {
							if (chunk.getBlockState(x, y, z).getBlock() == fromBlock) {
								chunk.setBlockState(new BlockPos(x, y, z), toBlock.getDefaultState());
							}
						}
					}
				}
			}
			chunk.markDirty();
		}
	}
}
