package gtclassic.api.interfaces;

public interface IGTMultiTileStatus {

	/**
	 * Do not put the actual structure check here, use a cached boolean.
	 * 
	 * @return
	 */
	boolean getStructureValid();
}
