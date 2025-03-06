package gtclassic.common.tile;

import java.util.Random;

import gtclassic.api.interfaces.IGTDisplayTickTile;
import gtclassic.common.GTBlocks;
import gtclassic.common.GTConfig;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergySource;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.block.base.util.info.misc.IEmitterTile;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class GTTileDragonEggEnergySiphon extends TileEntityMachine
		implements IEnergySource, IEmitterTile, IGTDisplayTickTile {

	protected double production = 128.0D;
	int storage;
	boolean enet = false;

	public GTTileDragonEggEnergySiphon() {
		super(0);
	}

	@Override
	public boolean emitsEnergyTo(IEnergyAcceptor var1, EnumFacing facing) {
		return true;
	}

	@Override
	public int getOutput() {
		return 128;
	}

	@Override
	public void drawEnergy(double amount) {
	}

	@Override
	public double getOfferedEnergy() {
		return this.getActive() ? this.production : 0.0D;
	}

	@Override
	public int getSourceTier() {
		return 2;
	}

	@Override
	public void onLoaded() {
		if (GTConfig.general.replaceOldSiphonWithNewAbsorber) {
			world.setBlockState(pos, GTBlocks.tileMagicEnergyAbsorber.getDefaultBlockState());
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof GTTileMagicEnergyAbsorber) {
				GTTileMagicEnergyAbsorber absorber = (GTTileMagicEnergyAbsorber) tile;
				absorber.onLoaded();
			}
		} else {
			super.onLoaded();
			if (this.isSimulating() && !this.enet) {
				MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
				this.enet = true;
			}
			this.checkForEgg();
		}
	}

	@Override
	public void onUnloaded() {
		if (this.isSimulating() && this.enet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			this.enet = false;
		}
		super.onUnloaded();
	}

	@Override
	public void onBlockUpdate(Block block) {
		this.checkForEgg();
	}

	private void checkForEgg() {
		boolean canAbsorb = GTTileMagicEnergyAbsorber.isValidAbsorberBlock(world, pos.up());
		this.production = canAbsorb ? 128 : 0;
		this.setActive(this.production > 0);
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return false;
	}

	@Override
	public void randomTickDisplay(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (this.isActive) {
			for (int i = 0; i < 3; ++i) {
				double j = (rand.nextInt(2) * 2 - 1) * .5;
				double k = (rand.nextInt(2) * 2 - 1) * .5;
				double d0 = (double) pos.getX() + 0.5D + 0.25D * (double) j;
				double d1 = (double) ((float) pos.getY() + rand.nextFloat() + .5);
				double d2 = (double) pos.getZ() + 0.5D + 0.25D * (double) k;
				double d3 = (double) (rand.nextFloat() * (float) j);
				double d4 = ((double) rand.nextFloat() - 0.5D) * 0.125D;
				double d5 = (double) (rand.nextFloat() * (float) k);
				worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
			}
		}
	}
}
