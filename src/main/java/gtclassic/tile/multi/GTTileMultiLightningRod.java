package gtclassic.tile.multi;

import gtclassic.GTBlocks;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import gtclassic.material.GTMaterialGen;
import gtclassic.util.int3;
import ic2.core.block.base.tile.TileEntityGeneratorBase;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.util.math.Box2D;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;

public class GTTileMultiLightningRod extends TileEntityGeneratorBase {

	public int casingheight;
	public int chance;

	public GTTileMultiLightningRod() {
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
		updateActive();
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
		return heighest > (pos.getY() + 7) && checkTungstenCasings();
	}

	public boolean checkTungstenCasings() {
		int3 dir = new int3(getPos(), getFacing());
		if (!(isMachineCasing(dir.forward(1)) && isCoil(dir.up(1)) && isMachineCasing(dir.up(1))
				&& isMachineCasing(dir.left(1)) && isCoil(dir.down(1)) && isMachineCasing(dir.down(1))
				&& isMachineCasing(dir.back(1)) && isCoil(dir.up(1)) && isMachineCasing(dir.up(1))
				&& isMachineCasing(dir.back(1)) && isCoil(dir.down(1)) && isMachineCasing(dir.down(1))
				&& isMachineCasing(dir.right(1)) && isCoil(dir.up(1)) && isMachineCasing(dir.up(1))
				&& isMachineCasing(dir.right(1)) && isCoil(dir.down(1)) && isMachineCasing(dir.down(1))
				&& isMachineCasing(dir.forward(1)) && isCoil(dir.up(1)) && isMachineCasing(dir.up(1))
				&& isMachineCasing(dir.forward(1)) && isCoil(dir.down(1)) && isMachineCasing(dir.down(1)))) {
			return false;
		}
		return true;
	}

	public boolean isMachineCasing(int3 pos) {
		return world.getBlockState(pos.asBlockPos()) == GTMaterialGen
				.getBlock(GTMaterial.Tungsten, GTMaterialFlag.CASING).getDefaultState();
	}

	public boolean isCoil(int3 pos) {
		return world.getBlockState(pos.asBlockPos()) == GTMaterialGen
				.getBlock(GTMaterial.NiobiumTitanium, GTMaterialFlag.COIL).getDefaultState();
	}

	public boolean checkPos(BlockPos pos) {
		return world.getBlockState(pos) == GTBlocks.casingLightning.getDefaultState();
	}

	public void updateActive() {
		if (this.getStoredEU() > 0) {
			this.setActive(true);
		} else {
			this.setActive(false);
		}
	}
}
