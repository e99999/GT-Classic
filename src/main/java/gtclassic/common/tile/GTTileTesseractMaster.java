package gtclassic.common.tile;

import java.util.Map;
import java.util.Random;

import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.api.interfaces.IGTDisplayTickTile;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.block.wiring.misc.EntityChargePadAuraFX;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileTesseractMaster extends TileEntityElecMachine implements ITickable, IGTDebuggableTile, IGTDisplayTickTile {

	TileEntity tesseractTile;
	private int redstoneLevel = -1;
	private int demand = 64;

	public GTTileTesseractMaster() {
		super(0, 128);
		this.maxEnergy = 10000;
	}

	public BlockPos getInventoryPos() {
		return this.pos.offset(this.getFacing().getOpposite());
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing();
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	public double getWrenchDropRate() {
		return 1.0D;
	}

	@Override
	public boolean supportsNotify() {
		return true;
	}

	@Override
	public void update() {
		this.handleEnergy();
	}

	@Override
	public void onLoaded() {
		super.onLoaded();
		this.updateTile();
	}

	@Override
	public void onBlockUpdate(Block block) {
		this.updateTile();
	}

	private void handleEnergy() {
		if (this.energy >= demand && tesseractTile != null && this.redstoneLevel < 1) {
			this.setActive(true);
			this.useEnergy(demand);
		} else {
			this.setActive(false);
		}
	}

	public void updateTile() {
		tesseractTile = world.getTileEntity(getInventoryPos());
		if (tesseractTile instanceof GTTileBlockExtender || tesseractTile instanceof GTTileTesseractSlave) {
			tesseractTile = null;
		}
		this.redstoneLevel = world.getRedstonePower(this.pos.offset(this.getFacing()), this.getFacing());
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		// TODO make the strings static vars
		boolean running = this.getActive();
		String status = running ? "Tesseract is being generated!" : "Tesseract not being generated because:";
		data.put(status, true);
		int level = this.redstoneLevel;
		if (!running) {
			if (this.tesseractTile == null) {
				data.put("No tile to connect with", true);
				data.put("Place a valid machine, inventory, or tank behind this block", true);
			}
			if (this.energy < demand) {
				data.put("Not enough energy to generate tesseract field", true);
			}
			if (level > 0) {
				data.put("Redstone has disabled this machine", true);
			}
		}
		data.put("Redstone Level: " + level, true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomTickDisplay(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (this.isActive) {
			BlockPos invPos = this.getInventoryPos();
			for (int k = 6; k > 0; --k) {
				ParticleManager er = Minecraft.getMinecraft().effectRenderer;
				float multPos = (float) (.1 * 2) + 0.9F;
				double x = (double) ((float) invPos.getX() + 0.05F + rand.nextFloat() * multPos);
				double y = (double) ((float) invPos.getY() + 1.0F + rand.nextFloat() * 0.2F);
				double z = (double) ((float) invPos.getZ() + 0.05F + rand.nextFloat() * multPos);
				double[] velocity = new double[] { 0.0D, 7.6D, 0.0D };
				if (k < 4) {
					velocity[2] *= 0.55D;
				}
				float[] colour = new float[] { 0.0F, .9F + rand.nextFloat() * .1F, 1.0F };
				er.addEffect(new EntityChargePadAuraFX(this.world, x, y, z, 8, velocity, colour, false));
			}
		}
	}
}
