package gtclassic.common;

import gtclassic.GTMod;
import gtclassic.api.fluid.GTFluid;
import gtclassic.api.fluid.GTFluidHandler;
import gtclassic.api.material.GTMaterial;
import gtclassic.api.material.GTMaterialFlag;
import ic2.api.classic.recipe.ClassicRecipes;
import net.minecraftforge.fluids.Fluid;
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

	public static void postInitProperities() {
		for (Fluid entry : ClassicRecipes.fluidGenerator.getBurnMap().keySet()) {
			GTFluidHandler.addBurnableToolTip(entry);
		}
	}
}
