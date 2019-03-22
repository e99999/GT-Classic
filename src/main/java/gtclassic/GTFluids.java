package gtclassic;

import gtclassic.fluid.GTFluidSlurry;
import net.minecraftforge.fluids.FluidRegistry;

public class GTFluids {

	public static final GTFluidSlurry fluidSlurry = new GTFluidSlurry("slurry");

	public static void registerFluids() {
		FluidRegistry.registerFluid(fluidSlurry);
	}

}
