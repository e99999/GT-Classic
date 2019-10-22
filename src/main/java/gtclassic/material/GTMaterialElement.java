package gtclassic.material;

import java.util.ArrayList;
import java.util.List;

import ic2.api.recipe.IRecipeInput;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.item.ItemStack;

public class GTMaterialElement {

	static List<GTMaterialElement> elements = new ArrayList<>();
	public static final GTMaterialElement hydrogen = addElement(1, GTMaterialGen.getTube(GTMaterial.Hydrogen, 1));
	public static final GTMaterialElement helium = addElement(2, GTMaterialGen.getTube(GTMaterial.Helium, 1));
	public static final GTMaterialElement lithium = addElement(3, "dustLithium", GTMaterialGen.getDust(GTMaterial.Lithium, 1));
	public static final GTMaterialElement beryllium = addElement(4, GTMaterialGen.getTube(GTMaterial.Beryllium, 1));
	public static final GTMaterialElement carbon = addElement(6, "dustCarbon", GTMaterialGen.getDust(GTMaterial.Carbon, 1));
	public static final GTMaterialElement nitrogen = addElement(7, GTMaterialGen.getTube(GTMaterial.Nitrogen, 1));
	public static final GTMaterialElement oxygen = addElement(8, GTMaterialGen.getTube(GTMaterial.Oxygen, 1));
	public static final GTMaterialElement neon = addElement(10, GTMaterialGen.getTube(GTMaterial.Neon, 1));
	public static final GTMaterialElement sodium = addElement(11, GTMaterialGen.getTube(GTMaterial.Sodium, 1));
	public static final GTMaterialElement aluminium = addElement(13, "dustAluminium", GTMaterialGen.getDust(GTMaterial.Aluminium, 1));
	public static final GTMaterialElement silicon = addElement(14, "dustSilicon", GTMaterialGen.getDust(GTMaterial.Silicon, 1));
	public static final GTMaterialElement chlorine = addElement(17, GTMaterialGen.getTube(GTMaterial.Chlorine, 1));
	public static final GTMaterialElement argon = addElement(18, GTMaterialGen.getTube(GTMaterial.Argon, 1));
	public static final GTMaterialElement potassium = addElement(19, GTMaterialGen.getTube(GTMaterial.Potassium, 1));
	public static final GTMaterialElement calcium = addElement(20, GTMaterialGen.getTube(GTMaterial.Calcium, 1));
	public static final GTMaterialElement titanium = addElement(22, "dustTitanium", GTMaterialGen.getDust(GTMaterial.Titanium, 1));
	public static final GTMaterialElement chrome = addElement(24, "dustChrome", GTMaterialGen.getDust(GTMaterial.Chrome, 1));
	public static final GTMaterialElement iron = addElement(26, "dustIron", GTMaterialGen.getIc2(Ic2Items.ironDust, 1));
	public static final GTMaterialElement copper = addElement(29, "dustCopper", GTMaterialGen.getIc2(Ic2Items.copperDust, 1));
	public static final GTMaterialElement silver = addElement(47, "dustSilver", GTMaterialGen.getIc2(Ic2Items.silverDust, 1));
	public static final GTMaterialElement tin = addElement(50, "dustTin", GTMaterialGen.getIc2(Ic2Items.tinDust, 1));
	public static final GTMaterialElement tungsten = addElement(74, "dustTungsten", GTMaterialGen.getDust(GTMaterial.Tungsten, 1));
	public static final GTMaterialElement iridium = addElement(77, "dustIridium", GTMaterialGen.getIc2(Ic2Items.iridiumOre, 1));
	public static final GTMaterialElement platinum = addElement(78, "dustPlatinum", GTMaterialGen.getDust(GTMaterial.Platinum, 1));
	public static final GTMaterialElement gold = addElement(79, "dustGold", GTMaterialGen.getIc2(Ic2Items.goldDust, 1));
	public static final GTMaterialElement mercury = addElement(88, GTMaterialGen.getTube(GTMaterial.Mercury, 1));
	public static final GTMaterialElement thorium = addElement(90, "dustThorium", GTMaterialGen.getDust(GTMaterial.Thorium, 1));
	public static final GTMaterialElement uranium = addElement(92, "dustUranium", GTMaterialGen.getDust(GTMaterial.Uranium, 1));
	public static final GTMaterialElement plutonium = addElement(94, "dustPlutonium", GTMaterialGen.getDust(GTMaterial.Plutonium, 1));

	public static GTMaterialElement addElement(int number, String input, ItemStack output) {
		GTMaterialElement element = new GTMaterialElement(number, new RecipeInputOreDict(input, 1), output);
		elements.add(element);
		return element;
	}

	public static GTMaterialElement addElement(int number, ItemStack output) {
		GTMaterialElement element = new GTMaterialElement(number, new RecipeInputItemStack(output), output);
		elements.add(element);
		return element;
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
	 * @param stack- ItemStack input and fusion output stack
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
