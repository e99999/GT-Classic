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
	int mapColor = Color.white.hashCode();
	protected static float overlayAlpha = 0.2F;
	protected static SoundEvent emptySound = SoundEvents.ITEM_BUCKET_EMPTY;
	protected static SoundEvent fillSound = SoundEvents.ITEM_BUCKET_FILL;
	protected static Material material = Material.WATER;
	GTMaterial mat = null;

	public GTFluid(GTMaterial mat, GTMaterialFlag flag) {
		super(mat.getDisplayName().toLowerCase() + flag.getSuffix(),
				new ResourceLocation(GTMod.MODID, "fluids/" + mat.getDisplayName().toLowerCase() + flag.getSuffix()),
				new ResourceLocation(GTMod.MODID, "fluids/" + mat.getDisplayName().toLowerCase() + flag.getSuffix()));
		this.mat = mat;
		this.temperature = mat.getTemp();
	}

	public GTFluid(String name, Color color) {
		super(name, new ResourceLocation(GTMod.MODID, "fluids/" + name),
				new ResourceLocation(GTMod.MODID, "fluids/" + name));
		this.mapColor = color.hashCode();
	}

	public GTMaterial getGTMaterial() {
		return this.mat != null ? this.mat : GTMaterial.Mercury;
	}

	@Override
	public int getColor() {
		return mapColor;
	}

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
