package gtclassic;

import java.util.ArrayList;
import java.util.List;

import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import ic2.api.recipe.IRecipeInput;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.item.ItemStack;

public class GTElement {

	static List<GTElement> elements = new ArrayList<>();
	/** Vanilla and IC2 Materials **/
	public static final GTElement iron = addElement(26, "dustIron", 1, GTMaterialGen.getIc2(Ic2Items.ironDust, 1));
	public static final GTElement gold = addElement(79, "dustGold", 1, GTMaterialGen.getIc2(Ic2Items.goldDust, 1));
	public static final GTElement copper = addElement(29, "dustCopper", 1, GTMaterialGen.getIc2(Ic2Items.copperDust, 1));
	public static final GTElement tin = addElement(50, "dustTin", 1, GTMaterialGen.getIc2(Ic2Items.tinDust, 1));
	public static final GTElement silver = addElement(47, "dustSilver", 1, GTMaterialGen.getIc2(Ic2Items.silverDust, 1));
	/** GT Materials **/
	public static final GTElement aluminium = addElement(13, "dustAluminium", 1, GTMaterialGen.getDust(GTMaterial.Aluminium, 1));
	public static final GTElement beryllium = addElement(4, GTMaterialGen.getTube(GTMaterial.Beryllium, 1));
	public static final GTElement calcium = addElement(20, GTMaterialGen.getTube(GTMaterial.Calcium, 1));
	public static final GTElement carbon = addElement(6, "dustCarbon", 1, GTMaterialGen.getDust(GTMaterial.Carbon, 1));
	public static final GTElement chlorine = addElement(17, GTMaterialGen.getTube(GTMaterial.Chlorine, 1));
	public static final GTElement chrome = addElement(24, "dustChrome", 1, GTMaterialGen.getDust(GTMaterial.Chrome, 1));
	public static final GTElement helium = addElement(2, GTMaterialGen.getTube(GTMaterial.Helium, 1));
	public static final GTElement hydrogen = addElement(1, GTMaterialGen.getTube(GTMaterial.Hydrogen, 1));
	public static final GTElement iridium = addElement(77, "dustIridium", 1, GTMaterialGen.getIc2(Ic2Items.iridiumOre, 1));
	public static final GTElement lithium = addElement(3, "dustLithium", 1, GTMaterialGen.getDust(GTMaterial.Lithium, 1));
	public static final GTElement mercury = addElement(88, GTMaterialGen.getTube(GTMaterial.Mercury, 1));
	public static final GTElement nitrogen = addElement(7, GTMaterialGen.getTube(GTMaterial.Nitrogen, 1));
	public static final GTElement oxygen = addElement(8, GTMaterialGen.getTube(GTMaterial.Oxygen, 1));
	public static final GTElement potassium = addElement(19, GTMaterialGen.getTube(GTMaterial.Potassium, 1));
	public static final GTElement silicon = addElement(14, "dustSilicon", 1, GTMaterialGen.getDust(GTMaterial.Silicon, 1));
	public static final GTElement sodium = addElement(11, GTMaterialGen.getTube(GTMaterial.Sodium, 1));
	public static final GTElement titanium = addElement(22, "dustTitanium", 1, GTMaterialGen.getDust(GTMaterial.Titanium, 1));
	public static final GTElement tungsten = addElement(74, "dustTungsten", 1, GTMaterialGen.getDust(GTMaterial.Tungsten, 1));
	public static final GTElement thorium = addElement(90, "dustThorium", 1, GTMaterialGen.getDust(GTMaterial.Thorium, 1));
	public static final GTElement uranium = addElement(92, "dustUranium", 1, GTMaterialGen.getDust(GTMaterial.Uranium, 1));
	public static final GTElement plutonium = addElement(94, "dustPlutonium", 1, GTMaterialGen.getDust(GTMaterial.Plutonium, 1));

	public static GTElement addElement(int number, String input, int amount, ItemStack output) {
		GTElement element = new GTElement(number, new RecipeInputOreDict(input, amount), output);
		elements.add(element);
		return element;
	}

	public static GTElement addElement(int number, ItemStack output) {
		GTElement element = new GTElement(number, new RecipeInputItemStack(output), output);
		elements.add(element);
		return element;
	}

	public static void addElement(GTElement element) {
		elements.add(element);
	}

	public static List<GTElement> getElementList() {
		return elements;
	}

	int number;
	IRecipeInput input;
	ItemStack output;

	/**
	 * Element object constructor used for ore dict input and ItemStack output.
	 * 
	 * @param number - int atomic number on the periodic table
	 * @param input  - IRecipeinput for oredict whatever you want as the input
	 * @param output - ItemStack output for fusion recipes
	 */
	public GTElement(int number, IRecipeInput input, ItemStack output) {
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
	public GTElement(int number, ItemStack stack) {
		this(number, new RecipeInputItemStack(stack), stack);
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
}
