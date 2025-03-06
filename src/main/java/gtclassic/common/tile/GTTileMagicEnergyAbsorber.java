package gtclassic.common.tile;

import java.util.Random;

import gtclassic.api.helpers.GTValues;
import gtclassic.api.interfaces.IGTDisplayTickTile;
import gtclassic.common.GTConfig;
import gtclassic.common.GTTwilightForest;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergySource;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.block.base.util.info.misc.IEmitterTile;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;

public class GTTileMagicEnergyAbsorber extends TileEntityMachine
		implements IEnergySource, IEmitterTile, IGTDisplayTickTile, ITickable {

	protected double production = 128.0D;
	int storage;
	boolean enet = false;

	public GTTileMagicEnergyAbsorber() {
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
		this.storage = (int)(this.storage - amount);
	}

	@Override
	public double getOfferedEnergy() {
		return this.storage;
	}

	@Override
	public int getSourceTier() {
		return 2;
	}

	@Override
	public void onLoaded() {
		super.onLoaded();
		if (this.isSimulating() && !this.enet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			this.enet = true;
		}
		this.checkForEgg();
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
	

	@Override
	public void update() {
		if (getActive()) {
			this.storage = (int) this.production;
		}
	}

	private void checkForEgg() {
		boolean canAbsorb = isValidAbsorberBlock(world, pos.up());
		//this.production = canAbsorb ? 128 : 0;
		this.setActive(canAbsorb);
	}
	
	/**
	 * Returns if a block is a valid generator for magical energy absorbing
	 * 
	 * @param world - the World param to pass
	 * @param pos   - the BlockPos to check
	 * @return - true or false
	 */
	public static boolean isValidAbsorberBlock(World world, BlockPos pos) {
		if (world.isAirBlock(pos)) {
			return false;
		}
		if (world.getBlockState(pos).equals(Blocks.DRAGON_EGG.getDefaultState())) {
			return true;
		}
		if (GTConfig.modcompat.compatTwilightForest && Loader.isModLoaded(GTValues.MOD_ID_TFOREST)) {
			return GTTwilightForest.isValidTwilightForestAbsorberBlock(world, pos);
		}
		return false;
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