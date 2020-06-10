package gtclassic.api.helpers;

import java.util.List;

import javax.annotation.Nullable;

import gtclassic.common.tile.GTTileMagicEnergyAbsorber;
import gtclassic.common.util.GTIBlockFilters;
import gtclassic.common.util.GTIFilters;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.fluid.IC2Tank;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.transport.IItemTransporter;
import ic2.core.inventory.transport.TransporterManager;
import ic2.core.item.armor.electric.ItemArmorQuantumSuit;
import ic2.core.util.helpers.AabbUtil;
import ic2.core.util.math.MathUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;

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

	/**
	 * Import ItemStacks from another tile into a TileEntityMachine.
	 * 
	 * @param machine - the TileEntityMachine to import into.
	 * @param side    - the EnumFacing side to try pull into.
	 * @param filter  - the IFilter to filter items
	 */
	public static void importFromSideIntoMachine(TileEntityMachine machine, EnumFacing side, IFilter filter) {
		BlockPos importPos = machine.getPos().offset(side);
		if (!machine.getWorld().isBlockLoaded(importPos)) {
			return;
		}
		IItemTransporter slave = TransporterManager.manager.getTransporter(machine.getWorld().getTileEntity(importPos), true);
		IItemTransporter controller = TransporterManager.manager.getTransporter(machine, true);
		if (slave != null && controller != null) {
			int limit = controller.getSizeInventory(side);
			for (int i = 0; i < limit; ++i) {
				ItemStack stack = slave.removeItem(filter, side.getOpposite(), 1, false);
				if (stack.isEmpty()) {
					break;
				}
				ItemStack added = controller.addItem(stack, side, true);
				if (added.getCount() <= 0) {
					break;
				}
				slave.removeItem(new GTIFilters.BetterBasicItemFilter(added), side.getOpposite(), 1, true);
			}
		}
	}

	/** Simpler version that automatically imports any item **/
	public static void importFromSideIntoMachine(TileEntityMachine machine, EnumFacing side) {
		importFromSideIntoMachine(machine, side, CommonFilters.Anything);
	}

	/**
	 * Import a FluidStack from another tile into a TileEntityMachine tank.
	 *
	 * @param machine - The TileEntityMachine which has the tank, provides World and
	 *                BlockPos data.
	 * @param tank    - the IC2Tank to try to import into.
	 * @param side    - the EnumFacing to try to import fluids from.
	 * @param amount  - the amount of fluid to transfer
	 */
	public static void importFluidFromSideToMachine(TileEntityMachine machine, IC2Tank tank, EnumFacing side,
			int amount) {
		BlockPos importPos = machine.getPos().offset(side);
		if (!machine.getWorld().isBlockLoaded(importPos)) {
			return;
		}
		IFluidHandler fluidTile = FluidUtil.getFluidHandler(machine.getWorld(), importPos, side.getOpposite());
		if (fluidTile != null && tank.getFluidAmount() < tank.getCapacity()) {
			FluidUtil.tryFluidTransfer(tank, fluidTile, amount, true);
		}
	}

	/**
	 * Export ItemStacks from a TileEntityMachine to another tile.
	 * 
	 * @param machine - The TileEntityMachine to export from.
	 * @param side    - the EnumFacing side to export out of.
	 * @param slots   - the slots you want to export from, use other constructor if
	 *                you want all slots.
	 */
	public static void exportFromMachineToSide(TileEntityMachine machine, EnumFacing side, int... slots) {
		BlockPos exportPos = machine.getPos().offset(side);
		if (!machine.getWorld().isBlockLoaded(exportPos)) {
			return;
		}
		IItemTransporter slave = TransporterManager.manager.getTransporter(machine.getWorld().getTileEntity(exportPos), false);
		if (slave != null) {
			for (int i : slots) {
				int added = slave.addItem(machine.getStackInSlot(i).copy(), side.getOpposite(), true).getCount();
				if (added > 0) {
					machine.getStackInSlot(i).shrink(added);
					break;
				}
			}
		}
	}

	/** Simpier version that automatically exports from every possible slot **/
	public static void exportFromMachineToSide(TileEntityMachine machine, EnumFacing side) {
		exportFromMachineToSide(machine, side, MathUtil.fromTo(0, machine.inventory.size()));
	}

	/**
	 * Export a FluidStack from a TileEntityMachine tank to another tile.
	 * 
	 * @param machine - The TileEntityMachine which has the tank, provides World and
	 *                BlockPos data.
	 * @param tank    - the IC2Tank to try to export from.
	 * @param side    - the EnumFacing to try to export fluids out of.
	 * @param amount  - the amount of fluid to transfer
	 */
	public static void exportFluidFromMachineToSide(TileEntityMachine machine, IC2Tank tank, EnumFacing side,
			int amount) {
		BlockPos exportPos = machine.getPos().offset(side);
		if (!machine.getWorld().isBlockLoaded(exportPos)) {
			return;
		}
		IFluidHandler fluidTile = FluidUtil.getFluidHandler(machine.getWorld(), exportPos, side.getOpposite());
		boolean canExport = tank.getFluid() != null && fluidTile != null;
		if (canExport) {
			FluidUtil.tryFluidTransfer(fluidTile, tank, amount, true);
		}
	}

	public static boolean tryResetStrongholdPortal(World world, BlockPos pos) {
		List<BlockPos> portalBlockPos = AabbUtil.getTargets(world, pos, 5, new GTIBlockFilters.EndPortalFilter(), false, false, RotationList.ALL);
		if (portalBlockPos.isEmpty()) {
			return false;
		}
		BlockPos selectedPos = portalBlockPos.get(world.rand.nextInt(portalBlockPos.size() - 1));
		IBlockState nearbyState = world.getBlockState(selectedPos);
		if (resetEndPortalFrame(world, selectedPos, nearbyState)) {
			boolean found = false;
			for (BlockPos portalPos : portalBlockPos) {
				if (world.getBlockState(portalPos).getBlock() == Blocks.END_PORTAL) {
					world.setBlockToAir(portalPos);
					world.removeTileEntity(portalPos);
					found = true;
				}
			}
			return found;
		}
		return false;
	}

	public static boolean resetEndPortalFrame(World world, BlockPos pos, IBlockState portalFrameState) {
		if (portalFrameState.getBlock() == Blocks.END_PORTAL_FRAME
				&& portalFrameState.getValue(BlockEndPortalFrame.EYE).booleanValue()) {
			world.setBlockState(pos, portalFrameState.withProperty(BlockEndPortalFrame.EYE, false));
			world.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundCategory.BLOCKS, 0.5F, 0.5F
					+ world.rand.nextFloat());
			return true;
		}
		return false;
	}

	public static boolean tryExplodeOtherAbsorbers(World world, BlockPos checkPos) {
		Iterable<BlockPos> surroundingPos = BlockPos.getAllInBox(checkPos.offset(EnumFacing.SOUTH, 4).offset(EnumFacing.WEST, 4), checkPos.offset(EnumFacing.NORTH, 4).offset(EnumFacing.EAST, 4));
		for (BlockPos absorberPos : surroundingPos) {
			if (absorberPos.equals(checkPos)) {
				continue;
			}
			TileEntity tile = world.getTileEntity(absorberPos);
			if (tile instanceof GTTileMagicEnergyAbsorber) {
				GTTileMagicEnergyAbsorber absorber = (GTTileMagicEnergyAbsorber) tile;
				if (absorber.portalMode && absorber.isAbovePortal) {
					world.setBlockToAir(absorberPos);
					world.removeTileEntity(absorberPos);
					world.createExplosion(null, absorberPos.getX(), absorberPos.getY(), absorberPos.getZ(), 8.0F, true);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * A way to update surrounding blocks. this does not update the original block!
	 * 
	 * @param world     - The world to update in
	 * @param pos       - the block pos the update should originate
	 * @param blockType - block type which can be null
	 * @param sides     - the list of sides to iterate an update
	 */
	public static void updateNeighbors(World world, BlockPos pos, @Nullable Block blockType, RotationList sides) {
		if (blockType == null) {
			blockType = Blocks.AIR;
		}
		for (EnumFacing side : sides) {
			BlockPos newPos = pos.offset(side);
			if (world.isBlockLoaded(newPos)) {
				world.neighborChanged(newPos, blockType, pos);
			}
		}
	}

	/**
	 * A way to update surrounding blocks and also their surrounding blocks. this
	 * does not update the original block!
	 * 
	 * @param world     - The world to update in
	 * @param pos       - the block pos the update should originate
	 * @param blockType - block type which can be null
	 * @param sides     - the list of sides to iterate an update
	 */
	public static void updateNeighborhood(World world, BlockPos pos, @Nullable Block blockType, RotationList sides) {
		for (EnumFacing side : sides) {
			if (world.isBlockLoaded(pos.offset(side))) {
				GTUtility.updateNeighbors(world, pos.offset(side), blockType, (side.getAxis().isHorizontal()
						? RotationList.HORIZONTAL.remove(side.rotateY())
						: RotationList.ALL).remove(side.getOpposite()));
			}
		}
	}
}
