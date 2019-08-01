package gtclassic.util;

import java.util.ArrayList;
import java.util.List;

import gtclassic.tile.GTTileMobRepeller;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GTSpawnEventHandler {

	public static volatile List<int[]> mobReps = new ArrayList<>();

	public GTSpawnEventHandler() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void denyMobSpawn(CheckSpawn event) {
		if (event.getResult() == Event.Result.ALLOW) {
			return;
		}
		if (event.getEntityLiving().isCreatureType(EnumCreatureType.MONSTER, false)) {
			for (int[] rep : mobReps) {
				World world = event.getEntity().getEntityWorld();
				if (rep[3] == world.provider.getDimension()) {
					TileEntity tile = world.getTileEntity(new BlockPos(rep[0], rep[1], rep[2]));
					if (tile instanceof GTTileMobRepeller) {
						int r = ((GTTileMobRepeller) tile).range;
						double dx = rep[0] + 0.5F - event.getEntity().posX;
						double dy = rep[1] + 0.5F - event.getEntity().posY;
						double dz = rep[2] + 0.5F - event.getEntity().posZ;
						if ((dx * dx + dz * dz + dy * dy) <= Math.pow(r, 2)) {
							event.setResult(Event.Result.DENY);
						}
					}
				}
			}
		}
	}
}