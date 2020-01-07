package gtclassic.common.tile.datanet;

import gtclassic.common.util.datanet.GTDataNet.DataType;

public class GTTileConstructorItem extends GTTileOutputNodeBase {

	/** Literally just a pointer on the network to where an output pos is **/
	public GTTileConstructorItem() {
		super(0);
	}

	@Override
	public DataType dataType() {
		return DataType.ITEM;
	}
}
