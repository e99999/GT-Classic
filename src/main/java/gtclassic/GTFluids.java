package gtclassic;

import gtclassic.fluid.GTFluid;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import net.minecraftforge.fluids.FluidRegistry;
import scala.actors.threadpool.Arrays;

import java.util.ArrayList;
import java.util.List;

public class GTFluids {

	public static List<String> burnableFluids = new ArrayList<>();

	public static void registerFluids() {
		burnableFluids.add("hydrogen");
		burnableFluids.add("methane");
		burnableFluids.add("sodium");
		for (GTMaterial mat : GTMaterial.values()) {
			if (mat.hasFlag(GTMaterialFlag.GAS)) {
				GTMod.debugLogger("Generating GregTech gas: " + mat.getDisplayName());
				FluidRegistry.registerFluid(new GTFluid(mat, "gas"));
			}
			if (mat.hasFlag(GTMaterialFlag.FLUID)) {
				GTMod.debugLogger("Generating GregTech fluid: " + mat.getDisplayName());
				FluidRegistry.registerFluid(new GTFluid(mat, "fluid"));
			}
		}
	}
}
