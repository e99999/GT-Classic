package gtclassic.util.recipe;

import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import ic2.api.recipe.IRecipeInput;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.item.ItemStack;

public class GTRecipeElementObject {

	int number;
	IRecipeInput input;
	ItemStack output;
	/** Vanilla and IC2 Materials **/
	public static final GTRecipeElementObject iron = new GTRecipeElementObject(26, input("dustIron", 1), GTMaterialGen.getIc2(Ic2Items.ironDust, 1));
	public static final GTRecipeElementObject gold = new GTRecipeElementObject(79, input("dustGold", 1), GTMaterialGen.getIc2(Ic2Items.goldDust, 1));
	public static final GTRecipeElementObject copper = new GTRecipeElementObject(29, input("dustCopper", 1), GTMaterialGen.getIc2(Ic2Items.copperDust, 1));
	public static final GTRecipeElementObject tin = new GTRecipeElementObject(50, input("dustTin", 1), GTMaterialGen.getIc2(Ic2Items.tinDust, 1));
	public static final GTRecipeElementObject silver = new GTRecipeElementObject(47, input("dustSilver", 1), GTMaterialGen.getIc2(Ic2Items.silverDust, 1));
	/** GT Materials **/
	public static final GTRecipeElementObject aluminium = new GTRecipeElementObject(13, input("dustAluminium", 1), GTMaterialGen.getDust(GTMaterial.Aluminium, 1));
	public static final GTRecipeElementObject beryllium = new GTRecipeElementObject(4, GTMaterialGen.getFluid(GTMaterial.Beryllium, 1));
	public static final GTRecipeElementObject calcium = new GTRecipeElementObject(20, GTMaterialGen.getFluid(GTMaterial.Calcium, 1));
	public static final GTRecipeElementObject carbon = new GTRecipeElementObject(6, input("dustCarbon", 1), GTMaterialGen.getDust(GTMaterial.Carbon, 1));
	public static final GTRecipeElementObject chlorine = new GTRecipeElementObject(17, GTMaterialGen.getFluid(GTMaterial.Chlorine, 1));
	public static final GTRecipeElementObject chrome = new GTRecipeElementObject(24, input("dustChrome", 1), GTMaterialGen.getDust(GTMaterial.Chrome, 1));
	public static final GTRecipeElementObject helium = new GTRecipeElementObject(2, GTMaterialGen.getFluid(GTMaterial.Helium, 1));
	public static final GTRecipeElementObject hydrogen = new GTRecipeElementObject(1, GTMaterialGen.getFluid(GTMaterial.Hydrogen, 1));
	public static final GTRecipeElementObject iridium = new GTRecipeElementObject(77, input("dustIridium", 1), GTMaterialGen.getIc2(Ic2Items.iridiumOre, 1));
	public static final GTRecipeElementObject lithium = new GTRecipeElementObject(3, input("dustLithium", 1), GTMaterialGen.getDust(GTMaterial.Lithium, 1));
	public static final GTRecipeElementObject mercury = new GTRecipeElementObject(88, GTMaterialGen.getFluid(GTMaterial.Mercury, 1));
	public static final GTRecipeElementObject nitrogen = new GTRecipeElementObject(7, GTMaterialGen.getFluid(GTMaterial.Nitrogen, 1));
	public static final GTRecipeElementObject oxygen = new GTRecipeElementObject(8, GTMaterialGen.getFluid(GTMaterial.Oxygen, 1));
	public static final GTRecipeElementObject potassium = new GTRecipeElementObject(19, GTMaterialGen.getFluid(GTMaterial.Potassium, 1));
	public static final GTRecipeElementObject silicon = new GTRecipeElementObject(14, input("dustSilicon", 1), GTMaterialGen.getDust(GTMaterial.Silicon, 1));
	public static final GTRecipeElementObject sodium = new GTRecipeElementObject(11, GTMaterialGen.getFluid(GTMaterial.Sodium, 1));
	public static final GTRecipeElementObject titanium = new GTRecipeElementObject(22, input("dustTitanium", 1), GTMaterialGen.getDust(GTMaterial.Titanium, 1));
	public static final GTRecipeElementObject tungsten = new GTRecipeElementObject(74, input("dustTungsten", 1), GTMaterialGen.getDust(GTMaterial.Tungsten, 1));
	public static final GTRecipeElementObject thorium = new GTRecipeElementObject(90, input("dustThorium", 1), GTMaterialGen.getDust(GTMaterial.Thorium, 1));
	public static final GTRecipeElementObject uranium = new GTRecipeElementObject(92, input("dustUranium", 1), GTMaterialGen.getDust(GTMaterial.Uranium, 1));
	public static final GTRecipeElementObject plutonium = new GTRecipeElementObject(94, input("dustPlutonium", 1), GTMaterialGen.getDust(GTMaterial.Plutonium, 1));
	public static final GTRecipeElementObject[] fusionObjects = { iron, gold, copper, tin, silver, aluminium, beryllium,
			calcium, carbon, chlorine, chrome, helium, hydrogen, iridium, lithium, mercury, nitrogen, oxygen, potassium,
			silicon, sodium, titanium, tungsten, thorium , uranium, plutonium };

	/**
	 * Element object constructor used for ore dict input and ItemStack output.
	 * 
	 * @param number - int atomic number on the periodic table
	 * @param input  - IRecipeinput for oredict whatever you want as the input
	 * @param output - ItemStack output for fusion recipes
	 */
	public GTRecipeElementObject(int number, IRecipeInput input, ItemStack output) {
		this.number = number;
		this.input = input;
		this.output = output;
	}

	/**
	 * Element object constructor where the input and output are the same stack.
	 * 
	 * @param number- int atomic number on the periodic table
	 * @param stack- ItemStack input and fusion output stack
	 */
	public GTRecipeElementObject(int number, ItemStack stack) {
		this(number, input(stack), stack);
	}

	public int getNumber() {
		return this.number;
	}

	public IRecipeInput getInput() {
		return this.input;
	}

	public ItemStack getOutput() {
		return this.output;
	}

	static IRecipeInput input(ItemStack stack) {
		return new RecipeInputItemStack(stack);
	}

	static IRecipeInput input(String name, int amount) {
		return new RecipeInputOreDict(name, amount);
	}
}
