package gtclassic.api.material;

import java.util.ArrayList;
import java.util.List;

import ic2.api.recipe.IRecipeInput;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.item.ItemStack;

public class GTMaterialElement {

	static List<GTMaterialElement> elements = new ArrayList<>();

	public static void init() {
		addElement(26, "dustIron", GTMaterialGen.getIc2(Ic2Items.ironDust, 1));
		addElement(29, "dustCopper", GTMaterialGen.getIc2(Ic2Items.copperDust, 1));
		addElement(47, "dustSilver", GTMaterialGen.getIc2(Ic2Items.silverDust, 1));
		addElement(50, "dustTin", GTMaterialGen.getIc2(Ic2Items.tinDust, 1));
		addElement(79, "dustGold", GTMaterialGen.getIc2(Ic2Items.goldDust, 1));
		addElement(92, "dustUranium", GTMaterialGen.getDust(GTMaterial.Uranium, 1));
		for (GTMaterial mat : GTMaterial.values()) {
			if (mat.getElementNumber() != -1) {
				if (mat.hasFlag(GTMaterialFlag.FLUID) || mat.hasFlag(GTMaterialFlag.GAS)) {
					addElement(mat.getElementNumber(), GTMaterialGen.getTube(mat, 1));
				} else if (mat.hasFlag(GTMaterialFlag.DUST)) {
					addElement(mat.getElementNumber(), "dust" + mat.getDisplayName(), GTMaterialGen.getDust(mat, 1));
				}
			}
		}
	}

	public static void addElement(int number, String input, ItemStack output) {
		GTMaterialElement element = new GTMaterialElement(number, new RecipeInputOreDict(input, 1), output);
		elements.add(element);
	}

	public static void addElement(int number, ItemStack output) {
		GTMaterialElement element = new GTMaterialElement(number, new RecipeInputItemStack(output), output);
		elements.add(element);
	}

	public static void addElement(GTMaterialElement element) {
		elements.add(element);
	}

	public static List<GTMaterialElement> getElementList() {
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
	public GTMaterialElement(int number, IRecipeInput input, ItemStack output) {
		this.number = number;
		this.input = input;
		this.output = output;
	}

	/**
	 * Element object constructor where the input and output are the same stack.
	 * 
	 * @param number- int atomic number on the periodic table
	 * @param stack-  ItemStack input and fusion output stack
	 */
	public GTMaterialElement(int number, ItemStack stack) {
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
