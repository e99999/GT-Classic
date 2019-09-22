package gtclassic.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gtclassic.GTConfig;
import gtclassic.helpers.GTHelperMath;
import gtclassic.tile.GTTileMobRepeller;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GTEventCheckSpawn {

	public static volatile List<int[]> mobReps = new ArrayList<>();

	public GTEventCheckSpawn() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onSpawn(CheckSpawn event) {
		if (event.getResult() == Event.Result.ALLOW) {
			return;
		}
		if (event.getEntityLiving().isCreatureType(EnumCreatureType.MONSTER, false)) {
			Entity entity = event.getEntity();
			BlockPos spawn = entity.getEntityWorld().getSpawnPoint();
			// This is the code for the safe spawn zone
			if (GTConfig.preventMobSpawnsCloseToSpawn
					&& entity.getEntityWorld().provider.getDimensionType().equals(DimensionType.OVERWORLD)
					&& entity.getPosition().distanceSq(spawn.getX(), spawn.getY(), spawn.getZ()) <= 128 * 128) {
				event.setResult(Event.Result.DENY);
			}
			// this is code for zombies spawning with pickaxes
			if (GTConfig.caveZombiesSpawnWithPickaxe && entity instanceof EntityZombie && event.getY() <= 50.0F
					&& event.getWorld().rand.nextInt(2) == 0) {
				EntityZombie zombie = (EntityZombie) entity;
				ItemStack tool = getRandomPickaxe(event.getWorld().rand);
				int damage = event.getWorld().rand.nextInt(tool.getMaxDamage() + 1);
				tool.damageItem(GTHelperMath.clip(damage, 1, tool.getMaxDamage() - 1), zombie);
				zombie.setHeldItem(EnumHand.MAIN_HAND, tool);
			}
			// This is the code for the mob repellator
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

	public ItemStack getRandomPickaxe(Random rand) {
		switch (rand.nextInt(4)) {
		case 1:
			return new ItemStack(Items.STONE_PICKAXE);
		case 2:
			return new ItemStack(Items.IRON_PICKAXE);
		case 3:
			return new ItemStack(Items.GOLDEN_PICKAXE);
		default:
			return new ItemStack(Items.WOODEN_PICKAXE);
		}
	}
}