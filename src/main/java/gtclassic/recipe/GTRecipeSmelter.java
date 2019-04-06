package gtclassic.recipe;

import gtclassic.GTBlocks;
import gtclassic.GTItems;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileElectricSmelter;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GTRecipeSmelter {

	static GTMaterialGen GT;
	static GTMaterial M;

	public static void recipesElectricSmelter1() {

		/*
		 * Alloy Smelter Recipes
		 */
		alloyUtil("Tin", 1, "Copper", 3, GT.getIc2(Ic2Items.bronzeIngot, 4));
		alloyUtil("Zinc", 1, "Copper", 3, GT.getIngot(M.Brass, 4));
		alloyUtil("Bismuth", 1, "Brass", 3, GT.getIngot(M.BismuthBronze, 4));
		alloyUtil("Iron", 2, "Nickel", 1, GT.getIngot(M.Invar, 3));
		alloyUtil("Gold", 1, "Silver", 1, GT.getIngot(M.Electrum, 2));
		alloyUtil("Copper", 1, "Nickel", 1, GT.getIngot(M.Constantan, 2));

		GTTileElectricSmelter.addRecipe("sand", 5, GT.get(GTItems.moldTube), GT.get(GTItems.testTube, 32));
		GTTileElectricSmelter.addRecipe("blockGlass", 5, GT.get(GTItems.moldTube), GT.get(GTItems.testTube, 32));
		GTTileElectricSmelter.addRecipe("dustPlastic", 1, GT.get(GTItems.moldPlate), GT.get(GTItems.plasticPCB, 1));
		GTTileElectricSmelter.addRecipe("dustPlastic", 9, GT.get(GTItems.moldBlock),
				GT.get(GTBlocks.plastic1CasingBlock, 1));

		GTTileElectricSmelter.addRecipe("dustSilicon", 1, GT.get(GTItems.testTube), GT.getFluid(M.Silicon, 1));
		GTTileElectricSmelter.addRecipe("dustCryolite", 1, GT.get(GTItems.testTube), GT.getFluid(M.Cryolite, 1));

	}

	public static void recipesElectricSmelter2() {
		/*
		 * Iterator recipes for processing materials into other forms
		 */

		for (GTMaterial mat : GTMaterial.values()) {
			String dust = "dust" + mat.getDisplayName();
			String ingot = "ingot" + mat.getDisplayName();
			String nugget = "nugget" + mat.getDisplayName();

			if (mat.getSmeltable() && !mat.equals(mat.RefinedIron)) {

				if (mat.hasFlag(GTMaterialFlag.PLATE)) {
					GTTileElectricSmelter.addRecipe(ingot, 1, GT.get(GTItems.moldPlate), GT.getPlate(mat, 1));
					GTTileElectricSmelter.addRecipe(nugget, 9, GT.get(GTItems.moldPlate), GT.getPlate(mat, 1));
					GTTileElectricSmelter.addRecipe(dust, 1, GT.get(GTItems.moldPlate), GT.getPlate(mat, 1));
				}
				if (mat.hasFlag(GTMaterialFlag.GEAR)) {
					GTTileElectricSmelter.addRecipe(ingot, 4, GT.get(GTItems.moldGear), GT.getGear(mat, 1));
					GTTileElectricSmelter.addRecipe(nugget, 36, GT.get(GTItems.moldGear), GT.getGear(mat, 1));
					GTTileElectricSmelter.addRecipe(dust, 4, GT.get(GTItems.moldGear), GT.getGear(mat, 1));
				}

				if (mat.hasFlag(GTMaterialFlag.STICK)) {
					GTTileElectricSmelter.addRecipe(ingot, 1, GT.get(GTItems.moldStick), GT.getStick(mat, 2));
					GTTileElectricSmelter.addRecipe(nugget, 9, GT.get(GTItems.moldStick), GT.getStick(mat, 2));
					GTTileElectricSmelter.addRecipe(dust, 1, GT.get(GTItems.moldStick), GT.getStick(mat, 2));
				}

				if (mat.hasFlag(GTMaterialFlag.BLOCK)) {
					GTTileElectricSmelter.addRecipe(ingot, 9, GT.get(GTItems.moldBlock), GT.getMaterialBlock(mat, 1));
					GTTileElectricSmelter.addRecipe(dust, 9, GT.get(GTItems.moldBlock), GT.getMaterialBlock(mat, 1));
				}

				if (mat.hasFlag(GTMaterialFlag.NUGGET)) {
					GTTileElectricSmelter.addRecipe(ingot, 1, GT.get(GTItems.moldNugget), GT.getNugget(mat, 9));
					GTTileElectricSmelter.addRecipe(dust, 1, GT.get(GTItems.moldNugget), GT.getNugget(mat, 9));
					if (mat != M.Bronze && mat != M.Silver && mat != M.Copper && mat != M.Tin) {
						GTTileElectricSmelter.addRecipe(nugget, 9, GT.get(GTItems.moldIngot), GT.getIngot(mat, 1));
						GTTileElectricSmelter.addRecipe(dust, 1, GT.get(GTItems.moldIngot), GT.getIngot(mat, 1));
					}
				}

			}
		}

		/*
		 * recipes that are part of the material/iteration system but exist outside of
		 * this mod
		 * 
		 */

		GTTileElectricSmelter.addRecipe("ingotRefinedIron", 1, GT.get(GTItems.moldPlate),
				GT.getPlate(M.RefinedIron, 1));
		GTTileElectricSmelter.addRecipe("ingotRefinedIron", 1, GT.get(GTItems.moldStick),
				GT.getStick(M.RefinedIron, 2));

		smeltingUtil("Iron", 9, GT.get(GTItems.moldBlock), GT.get(Blocks.IRON_BLOCK, 1));
		smeltingUtil("Gold", 9, GT.get(GTItems.moldBlock), GT.get(Blocks.GOLD_BLOCK, 1));
		smeltingUtil("Bronze", 9, GT.get(GTItems.moldBlock), GT.getIc2(Ic2Items.bronzeBlock, 1));
		smeltingUtil("Silver", 9, GT.get(GTItems.moldBlock), GT.getIc2(Ic2Items.silverBlock, 1));
		smeltingUtil("Copper", 9, GT.get(GTItems.moldBlock), GT.getIc2(Ic2Items.copperBlock, 1));
		smeltingUtil("Tin", 9, GT.get(GTItems.moldBlock), GT.getIc2(Ic2Items.tinBlock, 1));

		smeltingUtil("Iron", 1, GT.get(GTItems.moldNugget), GT.get(Items.IRON_NUGGET, 9));
		smeltingUtil("Gold", 1, GT.get(GTItems.moldNugget), GT.get(Items.GOLD_NUGGET, 9));

		GTTileElectricSmelter.addRecipe("ingotTin", 1, GT.get(GTItems.moldCable), GT.getIc2(Ic2Items.tinCable, 3));
		GTTileElectricSmelter.addRecipe("dustTin", 1, GT.get(GTItems.moldCable), GT.getIc2(Ic2Items.tinCable, 3));
		GTTileElectricSmelter.addRecipe("ingotCopper", 1, GT.get(GTItems.moldCable),
				GT.getIc2(Ic2Items.copperCable, 2));
		GTTileElectricSmelter.addRecipe("dustCopper", 1, GT.get(GTItems.moldCable), GT.getIc2(Ic2Items.copperCable, 2));
		GTTileElectricSmelter.addRecipe("ingotBronze", 1, GT.get(GTItems.moldCable),
				GT.getIc2(Ic2Items.bronzeCable, 2));
		GTTileElectricSmelter.addRecipe("dustBronze", 1, GT.get(GTItems.moldCable), GT.getIc2(Ic2Items.bronzeCable, 2));
		GTTileElectricSmelter.addRecipe("ingotGold", 1, GT.get(GTItems.moldCable), GT.getIc2(Ic2Items.goldCable, 4));
		GTTileElectricSmelter.addRecipe("dustGold", 1, GT.get(GTItems.moldCable), GT.getIc2(Ic2Items.goldCable, 4));
		GTTileElectricSmelter.addRecipe("ingotRefinedIron", 1, GT.get(GTItems.moldCable),
				GT.getIc2(Ic2Items.ironCable, 4));
	}

	/*
	 * Simple utility to generate recipe variants for the Alloy Smelter
	 */
	public static void alloyUtil(String input1, int amount1, String input2, int amount2, ItemStack output) {
		GTTileElectricSmelter.addRecipe("ingot" + input1, amount1, "ingot" + input2, amount2, output);
		GTTileElectricSmelter.addRecipe("dust" + input1, amount1, "dust" + input2, amount2, output);
		GTTileElectricSmelter.addRecipe("dust" + input1, amount1, "ingot" + input2, amount2, output);
		GTTileElectricSmelter.addRecipe("ingot" + input1, amount1, "dust" + input2, amount2, output);

	}

	/*
	 * Simple utility to generate mold recipe variants for the Alloy Smelter
	 */
	public static void smeltingUtil(String input1, int amount1, ItemStack input2, ItemStack output) {
		GTTileElectricSmelter.addRecipe("ingot" + input1, amount1, input2, output);
		GTTileElectricSmelter.addRecipe("dust" + input1, amount1, input2, output);
	}

}
