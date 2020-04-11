package gtclassic.common.tile;

import gtclassic.api.tile.GTTileBaseRecolorableTile;
import gtclassic.common.GTBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.ITickable;

public class GTTileLamp extends GTTileBaseRecolorableTile implements ITickable {

	private boolean shouldUpdate = true;

	public GTTileLamp() {
		super(0);
	}

	@Override
	public Block getBlockDrop() {
		return GTBlocks.tileLamp;
	}

	@Override
	public void onBlockUpdate(Block block) {
		this.shouldUpdate = true;
	}

	@Override
	public void update() {
		if (this.shouldUpdate) {
			this.setActive(world.isBlockPowered(this.pos));
		}
		this.shouldUpdate = false;
	}
}
