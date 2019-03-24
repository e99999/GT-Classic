package gtclassic;

import gtclassic.fluid.GTFluid;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import net.minecraftforge.fluids.FluidRegistry;

public class GTFluids {
	
	public static final String[] textureFluids = {"slurry"};

	public static final GTFluid slurryFluid = new GTFluid("slurry");

	public static void registerFluids() {
		FluidRegistry.registerFluid(slurryFluid);
		for (GTMaterial mat : GTMaterial.values()){
			if (mat.hasFlag(GTMaterialFlag.FLUID)){
				FluidRegistry.registerFluid(new GTFluid(mat.getDisplayName().toLowerCase()));
			}
		}

	}

}
