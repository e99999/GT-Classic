package gtclassic.common.tile;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Random;

import gtclassic.api.interfaces.IGTCoordinateTile;
import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.api.interfaces.IGTDisplayTickTile;
import ic2.api.energy.EnergyNet;
import ic2.api.energy.tile.IEnergySink;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.block.wiring.misc.EntityChargePadAuraFX;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileEnergyTransmitter extends TileEntityElecMachine
		implements ITickable, IGTCoordinateTile, IGTDebuggableTile, IGTDisplayTickTile {

	private int[] targetPos;
	private BlockPos tPos;
	private static final String NBT_TARGET = "target";
	private static final String NBT_POS = "tPos";
	public static final double EU_LOSS = 0.01D;

	public GTTileEnergyTransmitter() {
		super(0, 512);
		this.maxEnergy = 1000000;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.targetPos = nbt.getIntArray(NBT_TARGET);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		if (this.targetPos != null) {
			nbt.setIntArray(NBT_TARGET, this.targetPos);
		}
		return nbt;
	}

	@Override
	public boolean applyCoordinates(BlockPos pos, int dimensionId) {
		if (!pos.toString().equals(this.pos.toString())) {
			this.targetPos = new int[] { pos.getX(), pos.getY(), pos.getZ(), dimensionId };
			return true;
		}
		return false;
	}

	@Override
	public boolean isInterdimensional() {
		return false;
	}

	@Override
	public BlockPos getAppliedPos() {
		return new BlockPos(this.targetPos[0], this.targetPos[1], this.targetPos[2]);
	}

	@Override
	public int getAppliedDimId() {
		return this.world.provider.getDimension();
	}

	@Override
	public void update() {
		if (this.targetPos == null || getAppliedPos() == null) {
			this.setActive(false);
			return;
		}
		if (world.getTotalWorldTime() % 20 == 0) {
			TileEntity tile = world.getTileEntity(getAppliedPos());
			this.setActive(world.isBlockLoaded(getAppliedPos()) && tile instanceof IEnergySink && this.energy > 0);
		}
		if (this.isActive) {
			TileEntity tile = world.getTileEntity(getAppliedPos());
			if (!world.isBlockLoaded(getAppliedPos()) || tile == null || this.redstoneEnabled()) {
				this.setActive(false);
				return;
			}
			tryTransmitEnergy(tile);
		}
	}

	public void tryTransmitEnergy(TileEntity tile) {
		if (tile instanceof IEnergySink) {
			IEnergySink sink = (IEnergySink) tile;
			double needed = sink.getDemandedEnergy();
			if (needed > 0 && this.energy >= 512) {
				double energyBase = EnergyNet.instance.getPowerFromTier(sink.getSinkTier());
				if (energyBase > 512.0D) {
					energyBase = 512.0D;
				}
				double distance = getDistanceToTarget(this.pos, this.getAppliedPos());
				double energyWithLoss = energyBase - distance * EU_LOSS;
				if (energyWithLoss > 0.0D) {
					sink.injectEnergy(EnumFacing.UP, energyWithLoss, 0.0D);
				}
				this.useEnergy((int) energyBase);
			}
		}
	}

	@Override
	public boolean supportsNotify() {
		return true;
	}

	public static double getDistanceToTarget(BlockPos pos1, BlockPos pos2) {
		return Math.round(pos1.getDistance(pos2.getX(), pos2.getY(), pos2.getZ()));
	}

	@Override
	public double getWrenchDropRate() {
		return 1.0D;
	}

	public boolean redstoneEnabled() {
		return this.world.isBlockPowered(this.getPos());
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		if (this.targetPos != null) {
			BlockPos tPos = this.getAppliedPos();
			double distance = getDistanceToTarget(this.pos, tPos);
			double loss = distance * EU_LOSS;
			DecimalFormat df = new DecimalFormat("0.0");
			data.put("Connected to: " + world.getBlockState(tPos).getBlock().getLocalizedName(), false);
			data.put("Distance to target: " + distance, true);
			data.put("Total Loss from distance: " + df.format(loss) + "EU", true);
		} else {
			data.put("No Target Block", false);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomTickDisplay(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (this.isActive) {
			for (int k = 6; k > 0; --k) {
				ParticleManager er = Minecraft.getMinecraft().effectRenderer;
				float multPos = (float) (.1 * 2) + 0.9F;
				double x = (double) ((float) pos.getX() + 0.05F + rand.nextFloat() * multPos);
				double y = (double) ((float) pos.getY() + 1.0F + rand.nextFloat() * 0.2F);
				double z = (double) ((float) pos.getZ() + 0.05F + rand.nextFloat() * multPos);
				double[] velocity = new double[] { 0.0D, 7.6D, 0.0D };
				if (k < 4) {
					velocity[2] *= 0.55D;
				}
				float[] colour = new float[] { 0.0F, .5F + rand.nextFloat() * .5F, 1.0F };
				er.addEffect(new EntityChargePadAuraFX(this.world, x, y, z, 8, velocity, colour, false));
			}
		}
	}
}
