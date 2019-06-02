package gtclassic.util.recipe;

import net.minecraft.nbt.NBTTagCompound;

public class GTRecipeHelpers {
	public static enum ModifierType {
		RECIPE_LENGTH("RecipeTime", "RecipeTimeModifier"),
		RECIPE_ENERGY("RecipeEnergy", "RecipeEnergyModifier");

		String intEffect;
		String doubleEffect;

		private ModifierType(String intEffect, String doubleEffect) {
			this.intEffect = intEffect;
			this.doubleEffect = doubleEffect;
		}

		public RecipeModifierDouble create(double amount) {
			return new RecipeModifierDouble(this, amount);
		}

		public RecipeModifierInt create(int amount) {
			return new RecipeModifierInt(this, amount);
		}
	}

	public static interface IRecipeModifier {
		public void apply(NBTTagCompound compound);
	}

	public static class RecipeModifierInt implements IRecipeModifier {
		ModifierType type;
		int amount;

		public RecipeModifierInt(ModifierType type, int amount) {
			this.type = type;
			this.amount = amount;
		}

		public void apply(NBTTagCompound compound) {
			compound.setInteger(type.intEffect, amount);
		}
	}

	public static class RecipeModifierDouble implements IRecipeModifier {
		ModifierType type;
		double amount;

		public RecipeModifierDouble(ModifierType type, double amount) {
			this.type = type;
			this.amount = amount;
		}

		public void apply(NBTTagCompound compound) {
			compound.setDouble(type.doubleEffect, amount);
		}
	}
}
