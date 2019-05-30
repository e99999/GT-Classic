package gtclassic.util.energy;

import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.info.ILocatable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EnergyConsumer implements IEnergySink, ILocatable
{
	World world;
	BlockPos pos;
	IEnergySink accept;
	
	public EnergyConsumer(World world, BlockPos pos, IEnergySink source)
	{
		this.world = world;
		this.pos = pos;
		accept = source;
	}

	@Override
	public boolean acceptsEnergyFrom(IEnergyEmitter emitter, EnumFacing side)
	{
		return true;
	}

	@Override
	public double getDemandedEnergy()
	{
		return accept.getDemandedEnergy();
	}

	@Override
	public int getSinkTier()
	{
		return accept.getSinkTier();
	}

	@Override
	public double injectEnergy(EnumFacing directionFrom, double amount, double voltage)
	{
		return accept.injectEnergy(directionFrom, amount, voltage);
	}

	@Override
	public BlockPos getPosition()
	{
		return pos;
	}

	@Override
	public World getWorldObj()
	{
		return world;
	}
	
}
