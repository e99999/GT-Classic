package gtclassic.tileentity;

import java.util.Random;

import ic2.core.block.base.tile.TileEntityGeneratorBase;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.util.math.Box2D;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTTileEntityLightningRod extends TileEntityGeneratorBase {

	public GTTileEntityLightningRod() {
		super(0);
		this.maxStorage = 100000000;
		this.production = 2048;

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
		Random rand = world instanceof World ? ((World) world).rand : new Random();

		if (world.getTotalWorldTime() % 256 == 0 && correctWeather(this.world, this.getPos().up())
				&& (rand.nextInt(10) == 0)) {
			this.world.spawnEntity(new EntityLightningBolt(this.world, this.getPos().getX(), this.getPos().getY(),
					this.getPos().getZ(), false));
			if (this.storage < this.maxStorage) {

				this.storage = Math.min(this.maxStorage, this.storage + (production));
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

}
