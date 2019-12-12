package gtclassic.api.interfaces;

import java.util.Map;

public interface IGTDebuggableTile {

	/**
	 * Allows the magnifying glass or scanners to get special information
	 * 
	 * @param data - String to display, Boolean if it needs a scanner.
	 */
	public void getData(Map<String, Boolean> data);
}
