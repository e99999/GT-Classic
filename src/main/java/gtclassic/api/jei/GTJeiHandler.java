package gtclassic.api.jei;

import java.util.ArrayList;
import java.util.List;

public class GTJeiHandler {

	private static List<GTJeiEntry> registry = new ArrayList<>();

	public static void addEntry(GTJeiEntry entry) {
		registry.add(entry);
	}

	public static List<GTJeiEntry> getRegistry() {
		return registry;
	}
}
