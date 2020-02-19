package gtclassic.api.helpers;

import java.util.List;

import ic2.core.item.armor.electric.ItemArmorQuantumSuit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!! Just a few Utility Functions I use.
 */
public class GTUtility {

	/**
	 * Checks to see if a player is fully equipped in quantum gear
	 * 
	 * @param entity - usually the player in this case
	 * @return
	 */
	public static boolean hasFullQuantumSuit(EntityLivingBase entity) {
		if (!(entity instanceof EntityPlayer)) {
			return false;
		}
		EntityPlayer player = (EntityPlayer) entity;
		for (int i = 0; i < 4; i++) {
			if (!(player.inventory.armorInventory.get(i).getItem() instanceof ItemArmorQuantumSuit)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Pushes entities away from target within an area, code adapted from
	 * EE/ProjectE.
	 */
	public static void repelEntitiesInAABBFromPoint(World world, AxisAlignedBB boundingbox, double x, double y,
			double z) {
		List<Entity> list = world.getEntitiesWithinAABB(Entity.class, boundingbox);
		if (list.isEmpty()) {
			return;
		}
		for (Entity entity : list) {
			if ((entity instanceof EntityLiving) || (entity instanceof IProjectile)) {
				if (entity instanceof EntityArrow && ((EntityArrow) entity).onGround) {
					continue;
				}
				if (entity instanceof EntityArmorStand) {
					continue;
				}
				Vec3d p = new Vec3d(x, y, z);
				Vec3d t = new Vec3d(entity.posX, entity.posY, entity.posZ);
				double distance = p.distanceTo(t) + 0.1D;
				Vec3d r = new Vec3d(t.x - p.x, t.y - p.y, t.z - p.z);
				entity.motionX += r.x / 1.5D / distance;
				entity.motionY += r.y / 1.5D / distance;
				entity.motionZ += r.z / 1.5D / distance;
			}
		}
	}
}
