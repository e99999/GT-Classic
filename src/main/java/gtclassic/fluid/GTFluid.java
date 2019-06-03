package gtclassic.fluid;

import java.awt.Color;

import gtclassic.GTMod;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import net.minecraft.block.material.Material;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class GTFluid extends Fluid {

	int mapColor;
	protected static float overlayAlpha = 0.2F;
	protected static SoundEvent emptySound = SoundEvents.ITEM_BUCKET_EMPTY;
	protected static SoundEvent fillSound = SoundEvents.ITEM_BUCKET_FILL;
	protected static Material material = Material.WATER;
	GTMaterial mat = null;

	public GTFluid(GTMaterial mat, String base, GTMaterialFlag flag) {
		super(mat.getDisplayName().toLowerCase() + flag.getSuffix(), new ResourceLocation(GTMod.MODID, "fluids/"
				+ base), new ResourceLocation(GTMod.MODID, "fluids/flowing"));
		this.mat = mat;
		this.temperature = mat.getTemp();
		this.mapColor = Color.white.hashCode();
		this.setGaseous(flag.equals(GTMaterialFlag.GAS));
	}

	public GTFluid(GTMaterial mat, String base) {
		super(mat.getDisplayName().toLowerCase(), new ResourceLocation(GTMod.MODID, "fluids/"
				+ base), new ResourceLocation(GTMod.MODID, "fluids/flowing"));
		this.mat = mat;
		this.temperature = mat.getTemp();
		this.mapColor = mat.getColor().hashCode();
		this.setGaseous(this.mat.hasFlag(GTMaterialFlag.GAS));
	}

	public GTMaterial getGTMaterial() {
		return this.mat != null ? this.mat : GTMaterial.Mercury;
	}

	@Override
	public int getColor() {
		return mapColor;
	}

	@Override
	public GTFluid setColor(int parColor) {
		mapColor = parColor;
		return this;
	}

	public float getAlpha() {
		return overlayAlpha;
	}

	public GTFluid setAlpha(float parOverlayAlpha) {
		overlayAlpha = parOverlayAlpha;
		return this;
	}

	@Override
	public GTFluid setEmptySound(SoundEvent parSound) {
		emptySound = parSound;
		return this;
	}

	@Override
	public SoundEvent getEmptySound() {
		return emptySound;
	}

	@Override
	public GTFluid setFillSound(SoundEvent parSound) {
		fillSound = parSound;
		return this;
	}

	@Override
	public SoundEvent getFillSound() {
		return fillSound;
	}

	public GTFluid setMaterial(Material parMaterial) {
		material = parMaterial;
		return this;
	}

	public Material getMaterial() {
		return material;
	}

	@Override
	public boolean doesVaporize(FluidStack fluidStack) {
		if (block == null)
			return false;
		return block.getDefaultState().getMaterial() == getMaterial();
	}
}
