package gtclassic.tileentity;

import java.util.Random;

import gtclassic.util.GTBlocks;
import ic2.core.block.base.tile.TileEntityGeneratorBase;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2States;
import ic2.core.util.math.Box2D;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTTileEntityLightningRod extends TileEntityGeneratorBase {

	public static final IBlockState rodState = Ic2States.ironFence;

	public GTTileEntityLightningRod() {
		super(0);
		this.maxStorage = 100000000;
		this.production = 8096;

	}

	public static boolean correctWeather(World world, BlockPos pos) {
		if (world.provider.hasSkyLight()) {
			if (!world.canBlockSeeSky(pos)) {
				return false;
			} else {
				return world.isRaining() /* && world.isThundering() */;
			}
		} else {
			return false;
		}
	}

	@Override
	public void update() {
		Random rand = world instanceof World ? world.rand : new Random();

		if (world.getTotalWorldTime() % 256 == 0 /* && correctWeather(this.world, this.getPos().up()) */
				&& rand.nextInt(10) == 0) {
			if (checkStructure()) {
				this.world.addWeatherEffect(new EntityLightningBolt(this.world, this.getPos().getX(),
						this.getPos().getY(), this.getPos().getZ(), false));
				if (this.storage < this.maxStorage) {

					this.storage = Math.min(this.maxStorage, this.storage + (production));
				}
			}
		}

	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer var1) {
		return null;
	}

	@Override
	public ResourceLocation getTexture() {
		return null;
	}

	@Override
	public Box2D getEnergyBox() {
		return null;
	}

	@Override
	public boolean gainFuel() {
		return false;
	}

	@Override
	public boolean hasGui(EntityPlayer player) {
		return false;
	}

	public boolean checkStructure() {
		// if (world.isRemote) return false; //Return false if on client side
		if (!world.isAreaLoaded(pos, 3))
			return false;

		BlockPos working;

		// vertical line upward
		working = pos.offset(EnumFacing.UP, 1);
		if (!(checkPos(working))) {
			return false;
		}
		working = pos.offset(EnumFacing.UP, 2);
		if (!(checkPos(working))) {
			return false;
		}
		working = pos.offset(EnumFacing.UP, 3);
		if (!(checkPos(working))) {
			return false;
		}
		working = pos.offset(EnumFacing.UP, 4);
		if (!(checkPos(working))) {
			return false;
		}
		working = pos.offset(EnumFacing.UP, 5);
		if (!(checkPos(working))) {
			return false;
		}
		working = pos.offset(EnumFacing.UP, 6);
		if (!(checkPos(working))) {
			return false;
		}
		working = pos.offset(EnumFacing.UP, 7);
		if (!(checkPos(working))) {
			return false;
		}
		working = pos.offset(EnumFacing.UP, 8);
		if (!(checkPos(working))) {
			return false;
		}

		return true;
	}

	public boolean checkPos(BlockPos pos) {
		return world.getBlockState(pos) == rodState;
	}

	public boolean checkPos(BlockPos pos, EnumFacing facing, int offset) {
		return checkPos(pos.offset(facing, offset));
	}

}
