package gtclassic;

import gtclassic.fluid.GTFluid;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import net.minecraftforge.fluids.FluidRegistry;

public class GTFluids {

	public static void registerFluids() {
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
