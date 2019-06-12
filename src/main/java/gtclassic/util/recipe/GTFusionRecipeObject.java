package gtclassic.util.recipe;

import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import ic2.api.recipe.IRecipeInput;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.item.ItemStack;

public class GTFusionRecipeObject {

	int number;
	IRecipeInput input;
	ItemStack output;
	public static final GTFusionRecipeObject
	/** Vanilla & IC2 **/
	iron = new GTFusionRecipeObject(26, input("dustIron", 1), GTMaterialGen.getIc2(Ic2Items.ironDust, 1)),
			gold = new GTFusionRecipeObject(79, input("dustGold", 1), GTMaterialGen.getIc2(Ic2Items.goldDust, 1)),
			copper = new GTFusionRecipeObject(29, input("dustCopper", 1), GTMaterialGen.getIc2(Ic2Items.copperDust, 1)),
			tin = new GTFusionRecipeObject(50, input("dustTin", 1), GTMaterialGen.getIc2(Ic2Items.tinDust, 1)),
			silver = new GTFusionRecipeObject(47, input("dustSilver", 1), GTMaterialGen.getIc2(Ic2Items.silverDust, 1)),
			/** GT Materials **/
			aluminium = new GTFusionRecipeObject(13, input("dustAluminium", 1), GTMaterialGen.getDust(GTMaterial.Aluminium, 1)),
			beryllium = new GTFusionRecipeObject(4, GTMaterialGen.getFluid(GTMaterial.Beryllium, 1)),
			calcium = new GTFusionRecipeObject(20, GTMaterialGen.getFluid(GTMaterial.Calcium, 1)),
			carbon = new GTFusionRecipeObject(6, input("dustCarbon", 1), GTMaterialGen.getDust(GTMaterial.Carbon, 1)),
			chlorine = new GTFusionRecipeObject(17, GTMaterialGen.getFluid(GTMaterial.Chlorine, 1)),
			chrome = new GTFusionRecipeObject(24, input("dustChrome", 1), GTMaterialGen.getDust(GTMaterial.Chrome, 1)),
			helium = new GTFusionRecipeObject(2, GTMaterialGen.getFluid(GTMaterial.Helium, 1)),
			hydrogen = new GTFusionRecipeObject(1, GTMaterialGen.getFluid(GTMaterial.Hydrogen, 1)),
			iridium = new GTFusionRecipeObject(77, input("dustIridium", 1), GTMaterialGen.getIc2(Ic2Items.iridiumOre, 1)),
			lithium = new GTFusionRecipeObject(3, input("dustLithium", 1), GTMaterialGen.getDust(GTMaterial.Lithium, 1)),
			mercury = new GTFusionRecipeObject(88, GTMaterialGen.getFluid(GTMaterial.Mercury, 1)),
			nitrogen = new GTFusionRecipeObject(7, GTMaterialGen.getFluid(GTMaterial.Nitrogen, 1)),
			oxygen = new GTFusionRecipeObject(8, GTMaterialGen.getFluid(GTMaterial.Oxygen, 1)),
			potassium = new GTFusionRecipeObject(19, GTMaterialGen.getFluid(GTMaterial.Potassium, 1)),
			silicon = new GTFusionRecipeObject(14, input("dustSilicon", 1), GTMaterialGen.getDust(GTMaterial.Silicon, 1)),
			sodium = new GTFusionRecipeObject(11, GTMaterialGen.getFluid(GTMaterial.Sodium, 1)),
			titanium = new GTFusionRecipeObject(22, input("dustTitanium", 1), GTMaterialGen.getDust(GTMaterial.Titanium, 1)),
			tungsten = new GTFusionRecipeObject(74, input("dustTungsten", 1), GTMaterialGen.getDust(GTMaterial.Tungsten, 1)),
			uranium = new GTFusionRecipeObject(92, input("dustUranium", 1), GTMaterialGen.getIc2(Ic2Items.uraniumDrop, 1));
	public static final GTFusionRecipeObject[] fusionObjects = { iron, gold, copper, tin, silver, aluminium, beryllium,
			calcium, carbon, chlorine, chrome, helium, hydrogen, iridium, lithium, mercury, nitrogen, oxygen, potassium,
			silicon, sodium, titanium, tungsten, uranium };

	public GTFusionRecipeObject(int number, IRecipeInput input, ItemStack output) {
		this.number = number;
		this.input = input;
		this.output = output;
	}

	public GTFusionRecipeObject(int number, ItemStack stack) {
		this.number = number;
		this.input = input(stack);
		this.output = stack;
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
