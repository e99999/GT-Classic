package gtclassic.api.material;

import java.util.ArrayList;
import java.util.List;

import ic2.api.classic.recipe.crafting.RecipeInputFluid;
import ic2.api.recipe.IRecipeInput;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.misc.StackUtil;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

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
			if (mat.getElementNumber() > 0 && mat.getElementNumber() < 300) {
				if (mat.hasFlag(GTMaterialFlag.FLUID) || mat.hasFlag(GTMaterialFlag.GAS)) {
					addElement(mat.getElementNumber(), GTMaterialGen.getFluid(mat));
				} else if (mat.hasFlag(GTMaterialFlag.DUST)) {
					addElement(mat.getElementNumber(), "dust" + mat.getDisplayName(), GTMaterialGen.getDust(mat, 1));
				}
			}
		}
	}

	public static void addElement(int number, String input, ItemStack output) {
		GTMaterialElement element = new GTMaterialElement(number, new RecipeInputOreDict(input, 1), output.copy());
		elements.add(element);
	}

	public static void addElement(int number, ItemStack stack) {
		GTMaterialElement element = new GTMaterialElement(number, new RecipeInputItemStack(StackUtil.copyWithSize(stack, 1)), stack.copy());
		elements.add(element);
	}

	public static void addElement(int number, Fluid fluid) {
		GTMaterialElement element = new GTMaterialElement(number, new RecipeInputFluid(fluid), GTMaterialGen.getModdedTube(fluid.getName(), 1));
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
	boolean fluid;

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
		this.fluid = input instanceof RecipeInputFluid;
	}

	public int getNumber() {
		return this.number;
	}

	public int getAmplifierValue() {
		return this.number * 1000;
	}

	/** gets the IRecipeInput can be a fluid/oredict entry for recipes etc.. **/
	public IRecipeInput getInput() {
		return this.input;
	}

	/** Gets the ItemStack output of the element for recipes **/
	public ItemStack getOutput() {
		return this.output;
	}

	/** Gets the ItemStack output of the element as an IRecipeInput object **/
	public IRecipeInput getOutputAsInput() {
		return new RecipeInputItemStack(this.output.copy());
	}

	/**
	 * Checks if the IRecipeInput used to make the element is instanceof
	 * RecipeInputFluid
	 **/
	public boolean isFluid() {
		return this.fluid;
	}
}
