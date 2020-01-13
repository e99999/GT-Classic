package gtclassic.api.fluid;

import gtclassic.api.material.GTMaterial;
import gtclassic.api.material.GTMaterialFlag;
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
	GTMaterial mat;
	GTMaterialFlag flag;

	public GTFluid(GTMaterial mat, GTMaterialFlag flag) {
		super(mat.getDisplayName().toLowerCase(), new ResourceLocation(flag.getModID(), "fluids/"
				+ flag.getPrefix()), new ResourceLocation(flag.getModID(), "fluids/" + flag.getPrefix() + "flowing"));
		this.mat = mat;
		this.flag = flag;
		this.temperature = this.mat.hasFlag(GTMaterialFlag.INGOT) ? 2000 : 300;
		this.mapColor = caluclateMapColor();
		this.setGaseous(this.flag == GTMaterialFlag.GAS);
		if (this.mat == GTMaterial.Argon || this.mat == GTMaterial.Neon || this.mat == GTMaterial.Mercury) {
			this.setLuminosity(15);
		}
	}

	public GTMaterial getGTMaterial() {
		return this.mat != null ? this.mat : GTMaterial.Mercury;
	}

	public int caluclateMapColor() {
		return this.mat.getColor().getRGB();
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
