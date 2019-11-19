package gtclassic.api.interfaces;

import ic2.core.item.block.ItemBlockRare;

public interface IGTItemBlock {

	// Speiger being a Dickhead and forcing on e99999 the ItemBlockRare class
	public Class<? extends ItemBlockRare> getCustomItemBlock();
}
