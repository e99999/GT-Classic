package gtclassic.common.util;

import java.awt.Color;

import gtclassic.common.GTItems;
import ic2.core.item.reactor.uranTypes.UranBaseType;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Lang;
import net.minecraft.item.ItemStack;

public class GTUranThorium extends UranBaseType {

	public GTUranThorium() {
		this.loadDefaults();
	}

	@Override
	public float getEUPerPulse() {
		return 0.6f;
	}

	@Override
	public float getExplosionEffectModifier() {
		return 2.0f;
	}

	@Override
	public float getHeatModifier() {
		return 1.0f;
	}

	@Override
	public ItemStack getIngridient() {
		return null;
	}

	@Override
	public int getIngridientCost() {
		return 0;
	}

	@Override
	public int getIngrientPoints() {
		return 0;
	}

	@Override
	public int getMaxDurability() {
		return 12500;
	}

	@Override
	public LocaleComp getName(RodType rodType) {
		return Ic2Lang.nullKey;
	}

	@Override
	public ItemStack getNewIsotopicRod() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPulsesForConnection() {
		return 1;
	}

	@Override
	public int getPulsesPerTick() {
		return 1;
	}

	@Override
	public Color getReEnrichedColor() {
		return null;
	}

	@Override
	public short getRodID(RodType type) {
		switch (type) {
		case SingleRod:
			return 1216;
		case DualRod:
			return 1217;
		case QuadRod:
			return 1218;
		default:
			return 0;
		}
	}

	@Override
	public ItemStack getRodType(RodType type) {
		switch (type) {
		case SingleRod:
			return new ItemStack(GTItems.rodThorium1).copy();
		case DualRod:
			return new ItemStack(GTItems.rodThorium2).copy();
		case QuadRod:
			return new ItemStack(GTItems.rodThorium4).copy();
		default:
			return ItemStack.EMPTY;
		}
	}

	@Override
	public ItemStack getUraniumIngot() {
		return null;
	}

	@Override
	public boolean isReEnrichedUran() {
		return false;
	}

	@Override
	public int getRow() {
		return 0;
	}
}
