package gtclassic.common.util.datanet;

public class GTDataNet {

	/**
	 * Ok I really tried my best to avoid aimless util classes but since my brain
	 * has trouble getting stoned and making this data net stuff, I have given in
	 * and will store helpers and just all sorts of random shit with the data net
	 * here
	 **/
	/** Tick rate for INPUT NODES to attempt to move items to the OUTPUT NODES **/
	public static final int TICK_RATE = 10;
	/**
	 * Tick rate for that a network tries to validate itself doing recurrsive checks
	 **/
	public static final int SEARCH_RATE = 128;
	/** Tick rate for networks to reset and require revalidation **/
	public static final int RESET_RATE = 126;
	public static final String NBT_CHANNEL = "channel";
	
	public static final String TOOLTIP = "Currently work in progress!";

	/** Enum for node types **/
	public enum NodeType {
		INPUT(),
		OUTPUT();
	}

	/** Enum for data types **/
	public enum DataType {
		ITEM(),
		FLUID(),
		BOTH;
	}
}
