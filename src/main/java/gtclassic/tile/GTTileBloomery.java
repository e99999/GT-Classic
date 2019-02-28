package gtclassic.tile;

import java.util.List;

import gtclassic.util.int3;
import ic2.core.block.base.tile.TileEntityMachine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;

public class GTTileBloomery extends TileEntityMachine {

	String status = "null";
	String recipe = "null";
	
	IBlockState brick = Blocks.BRICK_BLOCK.getDefaultState();
	IBlockState door = Blocks.IRON_TRAPDOOR.getDefaultState();
	
	AxisAlignedBB recipeBB = null;

	public GTTileBloomery() {
		super(0);
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	public boolean checkRecipeBoundingBox() {
		recipeBB = new AxisAlignedBB(new int3(pos, getFacing()).back(1).asBlockPos());
		List<EntityItem> items = world.getEntitiesWithinAABB(EntityItem.class, recipeBB);
		for (EntityItem item : items) {
			if (item.getItem() == new ItemStack(Items.IRON_INGOT)) {
				recipe = "Recipe Valid!";
				return true;
			}
		}

		if (items.isEmpty()) {
			recipe = "Recipe is finding nothing at all";
			return false;
		
		} else {
			recipe = items.toString();
			return false;
		}
	}

	public boolean checkStructure() {
		/*
		 * Important to remember this structure check steps to the last position, so the
		 * next step is based on the previous pos position.
		 */
		status = "Checking...";
		int3 dir = new int3(getPos(), getFacing());
		if (!(isBrick(dir.down(1)) &&
		// layer -1
				isBrick(dir.left(1)) && isBrick(dir.back(1)) && isBrick(dir.back(1)) && isBrick(dir.right(1))
				&& isBrick(dir.forward(1)) && isBrick(dir.forward(1)) && isBrick(dir.right(1)) && isBrick(dir.back(2))
				// layer 0
				&& isBrick(dir.up(1)) && isDoor(dir.forward(1)) && isBrick(dir.forward(1)) && isBrick(dir.left(2))
				&& isBrick(dir.back(1)) && isBrick(dir.back(1)) && isBrick(dir.right(1)) && isBrick(dir.up(1))
				// layer 1
				&& isBrick(dir.forward(2)) && isBrick(dir.left(1)) && isBrick(dir.back(1)) && isBrick(dir.back(1))
				&& isBrick(dir.right(1)) && isBrick(dir.right(1)) && isBrick(dir.forward(2)) && isBrick(dir.back(1))
				// chimney
				&& isBrick(dir.up(1)) && isBrick(dir.up(1)) && isBrick(dir.left(2)) && isBrick(dir.down(1))
				&& isBrick(dir.down(1)) && isBrick(dir.forward(1)) && isBrick(dir.right(1)) && isBrick(dir.up(1))
				&& isBrick(dir.up(1)) && isBrick(dir.back(2)) && isBrick(dir.down(1)))) {

			this.setActive(false);
			status = "false";
			return false;
		}
		status = "true";
		this.setActive(true);
		return true;
	}

	public boolean isBrick(int3 pos) {
		return world.getBlockState(pos.asBlockPos()) == brick;
		// world.setBlockState(pos.asBlockPos(), brick);
		// return true;
	}

	public boolean isDoor(int3 pos) {
		return world.getBlockState(pos.asBlockPos()) == door;
		// world.setBlockState(pos.asBlockPos(), door);
		// return true;
	}

	public String getStatus() {
		return this.status;
	}

	public String getRecipe() {
		return this.recipe;
	}

}
