package gtclassic.block;

import gtclassic.GTMod;
import gtclassic.material.GTMaterialGen;
import gtclassic.util.GTCropType;
import ic2.api.crops.CropProperties;
import ic2.api.crops.ICropTile;
import ic2.core.block.crop.crops.CropCardBase;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockCrop extends CropCardBase {

	GTCropType entry;

	public GTBlockCrop(GTCropType entry) {
		super(new CropProperties(entry.getTier(), 2, 0, 0, 2, 0));
		this.entry = entry;
	}

	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int state) {
		return state == 4 ? this.getSprite(GTMod.MODID + "_materials")[32 + this.entry.getId()]
				: this.getSprite("bc")[31 + state];
	}

	@Override
	public String getDiscoveredBy() {
		return "e99999";
	}

	public String getId() {
		return this.entry.getName();
	}

	public int getMaxSize() {
		return 4;
	}

	public ItemStack getGain(ICropTile crop) {
		return GTMaterialGen.getDust(entry.getMaterial(), 1).copy();
	}

	@Override
	public double dropGainChance() {
		return super.dropGainChance() / 2.0D;
	}

	@Override
	public String[] getAttributes() {
		return entry.getAttributes();
	}

	@Override
	public int getGrowthDuration(ICropTile cropTile) {
		return cropTile.getCurrentSize() == 3 ? 2200 : 1000;
	}

	@Override
	public boolean canGrow(ICropTile cropTile) {
		String name = entry.getMaterial().getDisplayName();
		return cropTile.getCurrentSize() < 3 || cropTile.getCurrentSize() == 3
				&& (cropTile.isBlockBelow("ore" + name) || cropTile.isBlockBelow("block" + name));
	}

	@Override
	public int getOptimalHarvestSize(ICropTile cropTile) {
		return 4;
	}

	@Override
	public boolean canBeHarvested(ICropTile cropTile) {
		return cropTile.getCurrentSize() == 4;
	}

	@Override
	public int getSizeAfterHarvest(ICropTile cropTile) {
		return 2;
	}
}
