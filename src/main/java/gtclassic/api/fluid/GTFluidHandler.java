package gtclassic.api.fluid;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.fluids.Fluid;

public class GTFluidHandler {

	private static List<String> burnableToolTip = new ArrayList<>();

	public static void addBurnableToolTip(String fluidname) {
		burnableToolTip.add(fluidname);
	}

	public static void addBurnableToolTip(Fluid fluid) {
		burnableToolTip.add(fluid.getName());
	}

	public static List<String> getBurnableToolTipList() {
		return burnableToolTip;
	}
}
