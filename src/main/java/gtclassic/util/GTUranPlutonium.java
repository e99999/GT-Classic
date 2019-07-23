package gtclassic.util;

import java.awt.Color;

import gtclassic.GTItems;
import ic2.core.item.reactor.uranTypes.UranBaseType;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Lang;
import net.minecraft.item.ItemStack;

public class GTUranPlutonium extends UranBaseType {

	public GTUranPlutonium() {
		this.loadDefaults();
	}

	@Override
	public float getEUPerPulse() {
		return 1.0f;
	}

	@Override
	public float getExplosionEffectModifier() {
		return 6.0f;
	}

	@Override
	public float getHeatModifier() {
		return 4.0f;
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
		return 10000;
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
			return 1219;
		case DualRod:
			return 1220;
		case QuadRod:
			return 1221;
		default:
			return 0;
		}
	}

	@Override
	public ItemStack getRodType(RodType type) {
		switch (type) {
		case SingleRod:
			return new ItemStack(GTItems.rodPlutonium1).copy();
		case DualRod:
			return new ItemStack(GTItems.rodPlutonium2).copy();
		case QuadRod:
			return new ItemStack(GTItems.rodPlutonium4).copy();
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
