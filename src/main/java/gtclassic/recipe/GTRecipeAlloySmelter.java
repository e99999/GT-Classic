package gtclassic.recipe;

import gtclassic.GTItems;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileAlloySmelter;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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
		alloyUtil("Gold", 1, "Silver", 1, GT.getIngot(M.Electrum, 2));
		alloyUtil("Copper", 1, "Nickel", 1, GT.getIngot(M.Constantan, 2));

		// These recipes break because of ore dict
		GTTileAlloySmelter.addRecipe("sand", 5, GT.get(GTItems.moldTube), GT.get(GTItems.glassTube, 32));
		GTTileAlloySmelter.addRecipe("blockGlass", 5, GT.get(GTItems.moldTube), GT.get(GTItems.glassTube, 32));

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

			if (mat.getSmeltable() && mat != mat.RefinedIron && mat != mat.Maganlium) {

				if (mat.hasFlag(GTMaterialFlag.PLATE)) {
					GTTileAlloySmelter.addRecipe(ingot, 1, GT.get(GTItems.moldPlate), GT.getPlate(mat, 1));
					GTTileAlloySmelter.addRecipe(nugget, 9, GT.get(GTItems.moldPlate), GT.getPlate(mat, 1));
					GTTileAlloySmelter.addRecipe(dust, 1, GT.get(GTItems.moldPlate), GT.getPlate(mat, 1));
				}

				if (mat.hasFlag(GTMaterialFlag.STICK)) {
					GTTileAlloySmelter.addRecipe(ingot, 1, GT.get(GTItems.moldStick), GT.getStick(mat, 2));
					GTTileAlloySmelter.addRecipe(nugget, 9, GT.get(GTItems.moldStick), GT.getStick(mat, 2));
					GTTileAlloySmelter.addRecipe(dust, 1, GT.get(GTItems.moldStick), GT.getStick(mat, 2));
				}

				if (mat.hasFlag(GTMaterialFlag.BLOCK)) {
					GTTileAlloySmelter.addRecipe(ingot, 9, GT.get(GTItems.moldBlock), GT.getMaterialBlock(mat, 1));
					GTTileAlloySmelter.addRecipe(dust, 9, GT.get(GTItems.moldBlock), GT.getMaterialBlock(mat, 1));
				}

				if (mat.hasFlag(GTMaterialFlag.NUGGET)) {
					GTTileAlloySmelter.addRecipe(ingot, 1, GT.get(GTItems.moldNugget), GT.getNugget(mat, 9));
					GTTileAlloySmelter.addRecipe(dust, 1, GT.get(GTItems.moldNugget), GT.getNugget(mat, 9));
					if (mat != M.Bronze && mat != M.Silver && mat != M.Copper && mat != M.Tin) {
						GTTileAlloySmelter.addRecipe(nugget, 9, GT.get(GTItems.moldIngot), GT.getIngot(mat, 1));
					}
				}

			}
		}

		/*
		 * recipes that are part of the material/iteration system but exist outside of
		 * this mod
		 * 
		 */

		GTTileAlloySmelter.addRecipe("ingotRefinedIron", 1, GT.get(GTItems.moldPlate), GT.getPlate(M.RefinedIron, 1));

		smeltingUtil("Iron", 9, GT.get(GTItems.moldBlock), GT.get(Blocks.IRON_BLOCK, 1));
		smeltingUtil("Gold", 9, GT.get(GTItems.moldBlock), GT.get(Blocks.GOLD_BLOCK, 1));
		smeltingUtil("Bronze", 9, GT.get(GTItems.moldBlock), GT.getIc2(Ic2Items.bronzeBlock, 1));
		smeltingUtil("Silver", 9, GT.get(GTItems.moldBlock), GT.getIc2(Ic2Items.silverBlock, 1));
		smeltingUtil("Copper", 9, GT.get(GTItems.moldBlock), GT.getIc2(Ic2Items.copperBlock, 1));
		smeltingUtil("Tin", 9, GT.get(GTItems.moldBlock), GT.getIc2(Ic2Items.tinBlock, 1));

		smeltingUtil("Iron", 1, GT.get(GTItems.moldNugget), GT.get(Items.IRON_NUGGET, 9));
		smeltingUtil("Gold", 1, GT.get(GTItems.moldNugget), GT.get(Items.GOLD_NUGGET, 9));

		GTTileAlloySmelter.addRecipe("ingotTin", 1, GT.get(GTItems.moldCable), GT.getIc2(Ic2Items.tinCable, 3));
		GTTileAlloySmelter.addRecipe("dustTin", 1, GT.get(GTItems.moldCable), GT.getIc2(Ic2Items.tinCable, 3));
		GTTileAlloySmelter.addRecipe("ingotCopper", 1, GT.get(GTItems.moldCable), GT.getIc2(Ic2Items.copperCable, 2));
		GTTileAlloySmelter.addRecipe("dustCopper", 1, GT.get(GTItems.moldCable), GT.getIc2(Ic2Items.copperCable, 2));
		GTTileAlloySmelter.addRecipe("ingotBronze", 1, GT.get(GTItems.moldCable), GT.getIc2(Ic2Items.bronzeCable, 2));
		GTTileAlloySmelter.addRecipe("dustBronze", 1, GT.get(GTItems.moldCable), GT.getIc2(Ic2Items.bronzeCable, 2));
		GTTileAlloySmelter.addRecipe("ingotGold", 1, GT.get(GTItems.moldCable), GT.getIc2(Ic2Items.goldCable, 4));
		GTTileAlloySmelter.addRecipe("dustGold", 1, GT.get(GTItems.moldCable), GT.getIc2(Ic2Items.goldCable, 4));
		GTTileAlloySmelter.addRecipe("ingotRefinedIron", 1, GT.get(GTItems.moldCable),
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

	/*
	 * Simple utility to generate mold recipe variants for the Alloy Smelter
	 */
	public static void smeltingUtil(String input1, int amount1, ItemStack input2, ItemStack output) {
		GTTileAlloySmelter.addRecipe("ingot" + input1, amount1, input2, output);
		GTTileAlloySmelter.addRecipe("dust" + input1, amount1, input2, output);
	}

}
