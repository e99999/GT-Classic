package gtclassic.recipe;

import gtclassic.GTItems;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileAlloySmelter;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.item.ItemStack;

public class GTRecipeAlloySmelter {

	static GTMaterialGen GT;
	static GTMaterial M;

	public static void recipesAlloySmelter1() {

		/*
		 * Alloy Smelter Recipes
		 */
		alloyUtil("Tin", 1, "Copper", 3, GT.getIc2(Ic2Items.bronzeIngot, 4));
		alloyUtil("Zinc", 1, "Copper", 3, GT.getIngot(M.Brass, 4));
		alloyUtil("Iron", 2, "Nickel", 1, GT.getIngot(M.Invar, 3));
		alloyUtil("Gold", 1, "Lead", 1, GT.getIngot(M.Electrum, 2));

		// These recipes break because of ore dict
		GTTileAlloySmelter.addRecipe("sand", 5, new ItemStack(GTItems.moldTube, 32),
				new ItemStack(GTItems.glassTube, 32));
		GTTileAlloySmelter.addRecipe("blockGlass", 5, new ItemStack(GTItems.moldTube, 32),
				new ItemStack(GTItems.glassTube, 32));

	}

	public static void recipesAlloySmelter2() {
		/*
		 * Iterator recipes for processing materials into other forms
		 */

		for (GTMaterial mat : GTMaterial.values()) {

			String smalldust = "dustSmall" + mat.getDisplayName();
			String dust = "dust" + mat.getDisplayName();
			String gem = "gem" + mat.getDisplayName();
			String ingot = "ingot" + mat.getDisplayName();
			String nugget = "nugget" + mat.getDisplayName();
			String stick = "stick" + mat.getDisplayName();
			String plate = "plate" + mat.getDisplayName();
			String block = "block" + mat.getDisplayName();

			if (mat.getSmeltable()) {

				if (mat != mat.RefinedIron && mat != mat.Maganlium) {

					if (mat.hasFlag(GTMaterialFlag.PLATE)) {
						GTTileAlloySmelter.addRecipe(ingot, 1, new ItemStack(GTItems.moldPlate), GT.getPlate(mat, 1));
						GTTileAlloySmelter.addRecipe(nugget, 9, new ItemStack(GTItems.moldPlate), GT.getPlate(mat, 1));
						GTTileAlloySmelter.addRecipe(dust, 1, new ItemStack(GTItems.moldPlate), GT.getPlate(mat, 1));
					}

					if (mat.hasFlag(GTMaterialFlag.STICK)) {
						GTTileAlloySmelter.addRecipe(ingot, 1, new ItemStack(GTItems.moldStick), GT.getStick(mat, 2));
						GTTileAlloySmelter.addRecipe(nugget, 9, new ItemStack(GTItems.moldStick), GT.getStick(mat, 2));
						GTTileAlloySmelter.addRecipe(dust, 1, new ItemStack(GTItems.moldStick), GT.getStick(mat, 2));
					}

					if (mat.hasFlag(GTMaterialFlag.BLOCK)) {
						GTTileAlloySmelter.addRecipe(ingot, 9, new ItemStack(GTItems.moldBlock),
								GT.getMaterialBlock(mat, 1));
						GTTileAlloySmelter.addRecipe(dust, 9, new ItemStack(GTItems.moldBlock),
								GT.getMaterialBlock(mat, 1));
					}

					if (mat.hasFlag(GTMaterialFlag.NUGGET)) {
						GTTileAlloySmelter.addRecipe(ingot, 1, new ItemStack(GTItems.moldNugget), GT.getNugget(mat, 9));
						GTTileAlloySmelter.addRecipe(dust, 1, new ItemStack(GTItems.moldNugget), GT.getNugget(mat, 9));
						GTTileAlloySmelter.addRecipe(nugget, 9, new ItemStack(GTItems.moldIngot), GT.getIngot(mat, 1));
					}
				}
			}
		}

		/*
		 * recipes that are part of the material/iteration system but exist outside of
		 * this mod
		 * 
		 */

		GTTileAlloySmelter.addRecipe("ingotTin", 1, new ItemStack(GTItems.moldCable), GT.getIc2(Ic2Items.tinCable, 3));
		GTTileAlloySmelter.addRecipe("dustTin", 1, new ItemStack(GTItems.moldCable), GT.getIc2(Ic2Items.tinCable, 3));
		GTTileAlloySmelter.addRecipe("ingotCopper", 1, new ItemStack(GTItems.moldCable),
				GT.getIc2(Ic2Items.copperCable, 2));
		GTTileAlloySmelter.addRecipe("dustCopper", 1, new ItemStack(GTItems.moldCable),
				GT.getIc2(Ic2Items.copperCable, 2));
		GTTileAlloySmelter.addRecipe("ingotBronze", 1, new ItemStack(GTItems.moldCable),
				GT.getIc2(Ic2Items.bronzeCable, 2));
		GTTileAlloySmelter.addRecipe("dustBronze", 1, new ItemStack(GTItems.moldCable),
				GT.getIc2(Ic2Items.bronzeCable, 2));
		GTTileAlloySmelter.addRecipe("ingotGold", 1, new ItemStack(GTItems.moldCable),
				GT.getIc2(Ic2Items.goldCable, 4));
		GTTileAlloySmelter.addRecipe("dustGold", 1, new ItemStack(GTItems.moldCable), GT.getIc2(Ic2Items.goldCable, 4));
		GTTileAlloySmelter.addRecipe("ingotRefinedIron", 1, new ItemStack(GTItems.moldCable),
				GT.getIc2(Ic2Items.ironCable, 4));
	}

	/*
	 * Simple utility to generate recipe variants for the Alloy Smelter
	 */
	public static void alloyUtil(String input1, int amount1, String input2, int amount2, ItemStack output) {
		GTTileAlloySmelter.addRecipe("ingot" + input1, amount1, "ingot" + input2, amount2, output);
		GTTileAlloySmelter.addRecipe("ingot" + input2, amount2, "ingot" + input1, amount1, output);
		GTTileAlloySmelter.addRecipe("dust" + input1, amount1, "dust" + input2, amount2, output);
		GTTileAlloySmelter.addRecipe("dust" + input2, amount2, "dust" + input1, amount1, output);
	}

}
