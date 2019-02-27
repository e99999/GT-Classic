package gtclassic.tile;

import ic2.core.block.base.tile.TileEntityMachine;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class GTTileBloomery extends TileEntityMachine{
	
	IBlockState brick = Blocks.BRICK_BLOCK.getDefaultState();
	IBlockState door = Blocks.IRON_TRAPDOOR.getDefaultState();
	//IBlockState air = blockType.isAir(state, world, pos);
	
	String failure = "null";

	public GTTileBloomery() {
		super(0);
	}
	
	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}
	
	public boolean checkStructure() {
		failure = "Checking...";
		return checkLayer0();
		
	}
	
	public boolean checkLayer0() {
		BlockPos working;
		working = pos.offset(EnumFacing.DOWN, 1);
		
		//the block below it
		if (!(checkBrick(working) 
				//the block to the left of that
				&& checkBrick(working, EnumFacing.EAST, 1) 
				//the block to the right of that
				&& checkBrick(working, EnumFacing.WEST, 1))) {
			
			this.setActive(false);
			failure = "Layer -1 Failed";
			return false;
		}
		
		failure = "Correct!";
		this.setActive(true);
		return true;
	}
	
	public boolean checkBrick(BlockPos pos) {
		return world.getBlockState(pos) == brick;
	}

	public boolean checkBrick(BlockPos pos, EnumFacing facing, int offset) {
		return checkBrick(pos.offset(facing, offset));
	}
	
	public String structureStatus() {
		return this.failure;
	}

}
