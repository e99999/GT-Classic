package gtclassic.tileentity;

import gtclassic.GTBlocks;
import ic2.core.block.base.tile.TileEntityGeneratorBase;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.util.math.Box2D;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;

public class GTTileEntityLightningRod extends TileEntityGeneratorBase {

	public int casingheight;
	public int chance;

	public GTTileEntityLightningRod() {
		super(0);
		this.maxStorage = 100000000;
		this.production = 8096;
		this.casingheight = 0;
		this.chance = 262;

	}

	@Override
	public void update() {
		if (world.getTotalWorldTime() % 256 == 0 && world.rand.nextInt(chance) == 0) {
			if (world.isThundering() && (world.getPrecipitationHeight(pos).getY() <= (casingheight + 3))
					&& checkStructure()) {
				this.world.addWeatherEffect(new EntityLightningBolt(this.world, this.getPos().getX(), casingheight,
						this.getPos().getZ(), false));
				if (this.storage < this.maxStorage) {
					this.storage = Math.min(this.maxStorage, storage + 25000000);
					getNetwork().updateTileGuiField(this, "storage");
				}
			}
		}
		updateComparators();
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

	public void fenceCheck() {
		if (casingheight > 254) {
			casingheight = 254;
		}
		// keeps strange circumstances from borking the logic
	}

	public void chanceCheck() {
		if (chance < 10) {
			chance = 10;
		}
		// keeps strange circumstances from borking the logic
	}

	public boolean checkStructure() {
		MutableBlockPos position = new MutableBlockPos(pos);
		int heighest = 0;
		for (int i = pos.getY() + 1; i < (world.provider.getActualHeight() + 1); i++) {
			position.setY(i);
			if (!checkPos(position)) {
				heighest = i - 1;
				this.casingheight = i;
				fenceCheck();
				this.chance = 262 - (casingheight - (this.getPos().getY() + 1));
				chanceCheck();
				break;
			}
		}
		return heighest > (pos.getY() + 7);
	}

	public boolean checkPos(BlockPos pos) {
		return world.getBlockState(pos) == GTBlocks.ironCasingBlock.getDefaultState();
	}
}
