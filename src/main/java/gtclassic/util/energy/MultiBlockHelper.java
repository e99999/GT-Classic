package gtclassic.util.energy;

import java.util.List;
import java.util.Map;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MultiBlockHelper {

	public static final MultiBlockHelper INSTANCE = new MultiBlockHelper();
	Int2ObjectMap<WorldStructureEntry> serverMap = new Int2ObjectOpenHashMap<WorldStructureEntry>();
	Int2ObjectMap<WorldStructureEntry> clientMap = new Int2ObjectOpenHashMap<WorldStructureEntry>();

	public void init() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	public Int2ObjectMap<WorldStructureEntry> getMap(World world) {
		return world.isRemote ? clientMap : serverMap;
	}

	@SubscribeEvent
	public void onWorldUnload(WorldEvent.Unload unload) {
		World world = unload.getWorld();
		getMap(world).remove(world.provider.getDimension());
	}

	@SubscribeEvent
	public void onBlockClickEvent(RightClickBlock event) {
		if (event.getEntityPlayer().isSneaking()) {
			return;
		}
		WorldStructureEntry entry = getMap(event.getWorld()).get(event.getWorld().provider.getDimension());
		if (entry == null) {
			return;
		}
		BlockPos core = entry.getCore(event.getPos());
		if (core == null) {
			return;
		}
		IBlockState state = event.getWorld().getBlockState(core);
		Vec3d hit = event.getHitVec();
		state.getBlock().onBlockActivated(event.getWorld(), core, state, event.getEntityPlayer(), event.getHand(), event.getFace(), (float) hit.x, (float) hit.y, (float) hit.z);
		event.setCanceled(true);
	}

	public void addCore(World world, BlockPos core, List<BlockPos> subPos) {
		WorldStructureEntry entry = getMap(world).get(world.provider.getDimension());
		if (entry == null) {
			entry = new WorldStructureEntry(world.provider.getDimension());
			getMap(world).put(entry.dimID, entry);
		}
		entry.addCore(core, subPos);
	}

	public void removeCore(World world, BlockPos core) {
		WorldStructureEntry entry = getMap(world).get(world.provider.getDimension());
		if (entry == null) {
			return;
		}
		if (entry.removeCore(core)) {
			getMap(world).remove(entry.dimID);
		}
	}

	public static class WorldStructureEntry {

		int dimID;
		Map<BlockPos, BlockPos> structureToCore = new Object2ObjectOpenHashMap<BlockPos, BlockPos>();
		Map<BlockPos, List<BlockPos>> coreToStructure = new Object2ObjectOpenHashMap<BlockPos, List<BlockPos>>();

		public WorldStructureEntry(int dim) {
			this.dimID = dim;
		}

		public void addCore(BlockPos pos, List<BlockPos> subPos) {
			coreToStructure.put(pos, subPos);
			for (BlockPos entry : subPos) {
				structureToCore.put(entry, pos);
			}
		}

		public boolean removeCore(BlockPos pos) {
			List<BlockPos> sub = coreToStructure.remove(pos);
			if (sub != null) {
				for (BlockPos entry : sub) {
					structureToCore.remove(entry);
				}
			}
			return coreToStructure.isEmpty();
		}

		public BlockPos getCore(BlockPos pos) {
			return structureToCore.get(pos);
		}
	}
}
