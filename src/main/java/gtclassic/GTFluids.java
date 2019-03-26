package gtclassic;

import gtclassic.fluid.GTFluid;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import net.minecraftforge.fluids.FluidRegistry;

public class GTFluids {

	public static void registerFluids() {
		for (GTMaterial mat : GTMaterial.values()) {
			if (mat.hasFlag(GTMaterialFlag.FLUID)) {
				FluidRegistry.registerFluid(new GTFluid(mat, GTMaterialFlag.FLUID));
			}
			if (mat.hasFlag(GTMaterialFlag.PLASMA)) {
				FluidRegistry.registerFluid(new GTFluid(mat, GTMaterialFlag.PLASMA));
			}
		}

	}

}
