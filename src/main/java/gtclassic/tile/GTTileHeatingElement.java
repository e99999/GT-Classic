package gtclassic.tile;

import java.util.List;

import gtclassic.GTBlocks;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.util.int3;
import ic2.core.block.base.tile.TileEntityMachine;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;

public class GTTileHeatingElement extends TileEntityMachine implements ITickable {

	public GTTileHeatingElement() {
		super(0);
		setWorld(world);
	}

	@Override
	public void update() {
		if (world.getTotalWorldTime() % 200 == 0) {
			checkHeat();
		}
		if (world.getTotalWorldTime() % 100 == 0 && world.rand.nextInt(4) == 0) {
			if (this.isActive && resinPresent()) {
				completeProcess();
			}
		}
	}

	public void checkHeat() {
		int3 dir = new int3(getPos(), getFacing());
		this.setActive(isHeatSource(dir.down(1)));
	}

	public boolean isHeatSource(int3 pos) {
		return world.getBlockState(pos.asBlockPos()).getMaterial() == Material.LAVA
				|| world.getBlockState(pos.asBlockPos()).getMaterial() == Material.FIRE;
	}

	public boolean resinPresent() {
		AxisAlignedBB blockUp = new AxisAlignedBB(new int3(pos, getFacing()).up(1).asBlockPos());
		List<EntityItem> items = world.getEntitiesWithinAABB(EntityItem.class, blockUp);
		for (EntityItem item : items) {
			if (item.getItem().isItemEqual(GTMaterialGen.getDust(GTMaterial.Resin, 1))) {
				world.removeEntity(item);
				return true;
			}
		}
		return false;
	}

	public void completeProcess() {
		int3 dir = new int3(getPos(), getFacing());
		placeResinBlock(dir.up(1));
		world.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_LAVA_POP, SoundCategory.BLOCKS, 1.0F, 1.0F);
	}

	public void placeResinBlock(int3 pos) {
		world.setBlockState(pos.asBlockPos(), GTBlocks.driedResin.getDefaultState());
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != EnumFacing.UP && facing != EnumFacing.DOWN;
	}
}
