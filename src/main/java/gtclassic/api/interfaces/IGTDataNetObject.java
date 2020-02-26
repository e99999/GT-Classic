package gtclassic.api.interfaces;

public interface IGTDataNetObject {

	/**
	 * The EU cost this data object will have on the network drain, 0 will be added
	 * to the data net and no cost like a cable.
	 * 
	 * @return - The EU Cost per tick
	 */
	public int getCost();
}
