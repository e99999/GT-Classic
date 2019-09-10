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
	protected static Material material = GTFluidMaterial.GAS;
	GTMaterial mat = null;
	String base = null;

	private GTFluid(GTMaterial mat, String base, String suffix, boolean isGaseous) {
		super(mat.getDisplayName().toLowerCase() + suffix, new ResourceLocation(GTMod.MODID, "fluids/"
				+ base), new ResourceLocation(GTMod.MODID, "fluids/" + base + "flowing"));
		this.mat = mat;
		this.base = base;
		this.temperature = calculateTemperature();
		this.mapColor = caluclateMapColor();
		this.setGaseous(isGaseous);
	}

	/**
	 * Constructor for a GTFluid with added suffix control.
	 * 
	 * @param mat  - GTMaterial to create, grabs the color and fluid type
	 * @param base - String for background, can be "fluid" or "gas"
	 * @param flag - GTMaterialFlag to get a custom suffix, used for plasma etc..
	 */
	public GTFluid(GTMaterial mat, String base, GTMaterialFlag flag) {
		this(mat, base, flag.getSuffix(), flag.equals(GTMaterialFlag.GAS));
	}

	/**
	 * Constructor for a GTFluid.
	 * 
	 * @param mat  - GTMaterial to create, grabs the color and fluid type
	 * @param base - String for background, "fluid" for flowing texture and "gas"
	 *             for gas texture
	 */
	public GTFluid(GTMaterial mat, String base) {
		this(mat, base, "", mat.hasFlag(GTMaterialFlag.GAS));
	}

	public GTMaterial getGTMaterial() {
		return this.mat != null ? this.mat : GTMaterial.Mercury;
	}
	
	public int caluclateMapColor() {
		if (this.base.equals("molten") && (this.mat.equals(GTMaterial.Steel) || this.mat.equals(GTMaterial.Iron))){
			return Color.red.getRGB();
		}
		return this.mat.getColor().getRGB();
	}
	
	public int calculateTemperature() {
		if (this.base.equals("molten")) {
			return 2000;
		}
		return 300;
				
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
