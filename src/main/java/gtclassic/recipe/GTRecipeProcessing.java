package gtclassic.recipe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gtclassic.GTBlocks;
import gtclassic.GTConfig;
import gtclassic.GTItems;
import gtclassic.fluid.GTFluidBlockDryable;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.util.recipe.GTMultiInputRecipeList;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.IC2;
import ic2.core.block.machine.low.TileEntityCompressor;
import ic2.core.block.machine.low.TileEntityExtractor;
import ic2.core.block.machine.low.TileEntityMacerator;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GTRecipeProcessing {

	static IMachineRecipeList smelting = ClassicRecipes.furnace;
	public static final GTMultiInputRecipeList BYPRODUCT_LIST = new GTMultiInputRecipeList("gt.byproducts");
	public static final GTMultiInputRecipeList INTERACTION_LIST = new GTMultiInputRecipeList("gt.interaction");

	public static void recipesProcessing() {
		removeSmelting(GTMaterialGen.getIc2(Ic2Items.rubber, 1));
		removeSmelting(GTMaterialGen.getIc2(Ic2Items.refinedIronIngot, 1));
		/*
		 * Recipes specific to GT Classic blocks
		 */
		maceratorUtil("oreBauxite", 1, GTMaterialGen.getDust(GTMaterial.Bauxite, 4));
		maceratorUtil("oreIridium", 1, GTMaterialGen.getIc2(Ic2Items.iridiumOre, 2));
		maceratorUtil("oreSodalite", 1, GTMaterialGen.getDust(GTMaterial.Sodalite, 12));
		GameRegistry.addSmelting(GTBlocks.sandSlag, GTMaterialGen.get(GTBlocks.glassSlag), 0.1F);
		TileEntityExtractor.addRecipe("oreRuby", 1, GTMaterialGen.getGem(GTMaterial.Ruby, 3), 0.1F);
		TileEntityExtractor.addRecipe("oreSapphire", 1, GTMaterialGen.getGem(GTMaterial.Sapphire, 3), 0.1F);
		TileEntityExtractor.addRecipe("oreOlivine", 1, GTMaterialGen.getGem(GTMaterial.Olivine, 3), 0.1F);
		TileEntityExtractor.addRecipe("oreVibranium", 1, GTMaterialGen.getGem(GTMaterial.Vibranium, 3), 0.1F);
		/*
		 * Maceration recipes not covered by Ic2c automatically or that need to be
		 * different
		 */
		TileEntityMacerator.addRecipe(GTMaterialGen.get(Items.FLINT, 1), GTMaterialGen.getDust(GTMaterial.Flint, 1), 0.1F);
		TileEntityMacerator.addRecipe("stoneGranite", 1, GTMaterialGen.getDust(GTMaterial.Granite, 1));
		TileEntityMacerator.addRecipe("stoneMarble", 1, GTMaterialGen.getDust(GTMaterial.Marble, 1));
		TileEntityMacerator.addRecipe("stoneBasalt", 1, GTMaterialGen.getDust(GTMaterial.Basalt, 1));
		TileEntityMacerator.addRecipe(Ic2Items.uraniumDrop, 1, GTMaterialGen.getDust(GTMaterial.Uranium, 1), 0.1F);
		TileEntityMacerator.addRecipe("enderpearl", 1, GTMaterialGen.getDust(GTMaterial.EnderPearl, 1), 0.2F);
		TileEntityMacerator.addRecipe(GTMaterialGen.get(Items.ENDER_EYE, 1), GTMaterialGen.getDust(GTMaterial.EnderEye, 1), 0.2F);
		TileEntityMacerator.addRecipe("gemDiamond", 1, GTMaterialGen.getDust(GTMaterial.Diamond, 1), 0.1F);
		TileEntityMacerator.addRecipe("gemEmerald", 1, GTMaterialGen.getDust(GTMaterial.Emerald, 1), 0.1F);
		TileEntityMacerator.addRecipe("plateRefinedIron", 1, GTMaterialGen.getIc2(Ic2Items.ironDust, 1));
		/*
		 * Compressor recipes
		 */
		TileEntityCompressor.addRecipe(GTMaterialGen.getDust(GTMaterial.Carbon, 8), GTMaterialGen.getIc2(Ic2Items.carbonFiber, 1), 0.1F);
		TileEntityCompressor.addRecipe("dustUranium", 1, GTMaterialGen.getIc2(Ic2Items.uraniumIngot, 1), 0.1F);
		TileEntityCompressor.addRecipe("dustEmerald", 1, GTMaterialGen.get(Items.EMERALD), 0.1F);
		TileEntityCompressor.addRecipe("dustDiamond", 1, GTMaterialGen.get(Items.DIAMOND), 0.1F);
		/*
		 * Some random fuel things
		 */
		ClassicRecipes.fluidGenerator.addEntry(FluidRegistry.getFluid("sodium"), 3800, 8);
		ClassicRecipes.fluidGenerator.addEntry(FluidRegistry.getFluid("lithium"), 3800, 8);
		ClassicRecipes.fluidGenerator.addEntry(FluidRegistry.getFluid("hydrogen"), 950, 16);
		ClassicRecipes.fluidGenerator.addEntry(FluidRegistry.getFluid("methane"), 3000, 16);
		/*
		 * Dryable fluid JEI stuff
		 */
		GTFluidBlockDryable.dryingUtil(GTMaterialGen.getFluid(GTMaterial.BauxiteTailings, 1), GTMaterialGen.getDust(GTMaterial.BauxiteTailings, 1));
		GTFluidBlockDryable.dryingUtil(GTMaterialGen.getFluid(GTMaterial.Brine, 1), GTMaterialGen.getDust(GTMaterial.Brine, 1));
		GTFluidBlockDryable.dryingUtil(GTMaterialGen.getFluid(GTMaterial.LithiumCarbonate, 1), GTMaterialGen.getDust(GTMaterial.Lithium, 1));
	}

	public static void recipesByproducts() {
		// byproducts for vanilla ore
		addByproduct("oreGold", GTMaterialGen.getIc2(Ic2Items.goldDust, 1), GTMaterialGen.getIc2(Ic2Items.copperDust, 1), GTMaterialGen.getDust(GTMaterial.Nickel, 1));
		addByproduct("oreIron", GTMaterialGen.getIc2(Ic2Items.ironDust, 1), GTMaterialGen.getDust(GTMaterial.Nickel, 1));
		addByproduct("oreCoal", GTMaterialGen.getIc2(Ic2Items.coalDust, 1), GTMaterialGen.getDust(GTMaterial.Thorium, 1), GTMaterialGen.getDust(GTMaterial.Germanium, 1));
		addByproduct("oreLapis", GTMaterialGen.getDust(GTMaterial.Lazurite, 1));
		addByproduct("oreDiamond", GTMaterialGen.get(Items.DIAMOND), GTMaterialGen.getDust(GTMaterial.Diamond, 1), GTMaterialGen.getDust(GTMaterial.Graphite, 1));
		addByproduct("oreRedstone", GTMaterialGen.get(Items.REDSTONE, 1), GTMaterialGen.get(Items.GLOWSTONE_DUST, 1));
		addByproduct("oreEmerald", GTMaterialGen.get(Items.EMERALD), GTMaterialGen.getDust(GTMaterial.Emerald, 1), GTMaterialGen.getDust(GTMaterial.Olivine, 1));
		// byproducts for ic2c ore
		addByproduct("oreCopper", GTMaterialGen.getIc2(Ic2Items.copperDust, 1), GTMaterialGen.getIc2(Ic2Items.goldDust, 1), GTMaterialGen.getDust(GTMaterial.Nickel, 1));
		addByproduct("oreTin", GTMaterialGen.getIc2(Ic2Items.tinDust, 1), GTMaterialGen.getIc2(Ic2Items.ironDust, 1), GTMaterialGen.getDust(GTMaterial.Zinc, 1));
		addByproduct("oreUranium", GTMaterialGen.getDust(GTMaterial.Uranium, 1), GTMaterialGen.getDust(GTMaterial.Plutonium, 1), GTMaterialGen.getDust(GTMaterial.Thorium, 1));
		addByproduct("oreSilver", GTMaterialGen.getIc2(Ic2Items.silverDust, 1), GTMaterialGen.getDust(GTMaterial.Lead, 1));
		// byproducts for gt ores
		addByproduct("oreBauxite", GTMaterialGen.getDust(GTMaterial.Aluminium, 1), GTMaterialGen.getDust(GTMaterial.Titanium, 1), GTMaterialGen.getDust(GTMaterial.Silicon, 1));
		addByproduct("oreBismuthtine", GTMaterialGen.getDust(GTMaterial.Bismuth, 1), GTMaterialGen.getDust(GTMaterial.Antimony, 1));
		addByproduct("oreCalcite", GTMaterialGen.getDust(GTMaterial.Calcite, 1), GTMaterialGen.getDust(GTMaterial.Phosphorus, 1));
		addByproduct("oreCassiterite", GTMaterialGen.getIc2(Ic2Items.tinDust, 1), GTMaterialGen.getDust(GTMaterial.Tantalum, 1));
		addByproduct("oreChromite", GTMaterialGen.getDust(GTMaterial.Chrome, 1), GTMaterialGen.getIc2(Ic2Items.ironDust, 1));
		addByproduct("oreCinnabar", GTMaterialGen.getFluid(GTMaterial.Mercury, 1), GTMaterialGen.get(Items.REDSTONE, 1), GTMaterialGen.getDust(GTMaterial.Sulfur, 1));
		addByproduct("oreCryolite", GTMaterialGen.getDust(GTMaterial.Cryolite, 1));
		addByproduct("oreGalena", GTMaterialGen.getDust(GTMaterial.Lead, 1), GTMaterialGen.getIc2(Ic2Items.silverDust, 1), GTMaterialGen.getDust(GTMaterial.Sulfur, 1));
		addByproduct("oreGarnierite", GTMaterialGen.getDust(GTMaterial.Nickel, 1), GTMaterialGen.getDust(GTMaterial.Cobalt, 1));
		addByproduct("oreGraphite", GTMaterialGen.getDust(GTMaterial.Graphite, 1), GTMaterialGen.getDust(GTMaterial.Diamond, 1));
		addByproduct("oreIridium", GTMaterialGen.getDust(GTMaterial.Iridium, 1), GTMaterialGen.getDust(GTMaterial.Platinum, 1), GTMaterialGen.getDust(GTMaterial.Osmium, 1));
		addByproduct("oreLimonite", GTMaterialGen.getIc2(Ic2Items.ironDust, 1));
		addByproduct("oreMagnetite", GTMaterialGen.getIc2(Ic2Items.ironDust, 1));
		addByproduct("oreMalachite", GTMaterialGen.getIc2(Ic2Items.copperDust, 1), GTMaterialGen.getDust(GTMaterial.Calcite, 1));
		addByproduct("oreOlivine", GTMaterialGen.getGem(GTMaterial.Olivine, 1), GTMaterialGen.getDust(GTMaterial.Olivine, 1), GTMaterialGen.getDust(GTMaterial.Emerald, 1));
		addByproduct("orePyrite", GTMaterialGen.getIc2(Ic2Items.ironDust, 1), GTMaterialGen.getDust(GTMaterial.Sulfur, 1));
		addByproduct("oreSheldonite", GTMaterialGen.getDust(GTMaterial.Platinum, 1), GTMaterialGen.getDust(GTMaterial.Iridium, 1), GTMaterialGen.getDust(GTMaterial.Osmium, 1));
		addByproduct("oreRuby", GTMaterialGen.getGem(GTMaterial.Ruby, 1), GTMaterialGen.getDust(GTMaterial.Ruby, 1), GTMaterialGen.getDust(GTMaterial.GarnetRed, 1));
		addByproduct("oreSaltpeter", GTMaterialGen.getDust(GTMaterial.Saltpeter, 1));
		addByproduct("oreSapphire", GTMaterialGen.getGem(GTMaterial.Sapphire, 1), GTMaterialGen.getDust(GTMaterial.Sapphire, 1), GTMaterialGen.getDust(GTMaterial.SapphireGreen, 1));
		addByproduct("oreSodalite", GTMaterialGen.getDust(GTMaterial.Sodalite, 1), GTMaterialGen.getDust(GTMaterial.Aluminium, 1));
		addByproduct("oreGalena", GTMaterialGen.getDust(GTMaterial.Zinc, 1), GTMaterialGen.getDust(GTMaterial.Germanium, 1), GTMaterialGen.getDust(GTMaterial.Sulfur, 1));
		addByproduct("oreSulfur", GTMaterialGen.getDust(GTMaterial.Sulfur, 1));
		addByproduct("oreTantalite", GTMaterialGen.getDust(GTMaterial.Tantalum, 1), GTMaterialGen.getDust(GTMaterial.Niobium, 1));
		addByproduct("oreTetrahedrite", GTMaterialGen.getIc2(Ic2Items.copperDust, 1), GTMaterialGen.getDust(GTMaterial.Antimony, 1), GTMaterialGen.getIc2(Ic2Items.ironDust, 1), GTMaterialGen.getDust(GTMaterial.Sulfur, 1));
		addByproduct("oreTungstate", GTMaterialGen.getDust(GTMaterial.Tungsten, 1), GTMaterialGen.getIc2(Ic2Items.ironDust, 1));
		addByproduct("oreBasalt", GTMaterialGen.getDust(GTMaterial.Basalt, 1), GTMaterialGen.getDust(GTMaterial.Zirconium, 1), GTMaterialGen.getDust(GTMaterial.GarnetYellow, 1), GTMaterialGen.getDust(GTMaterial.GarnetRed, 1));
		addByproduct("orePyrolusite", GTMaterialGen.getDust(GTMaterial.Manganese, 1));
		addByproduct("oreSalt", GTMaterialGen.getDust(GTMaterial.Salt, 1), GTMaterialGen.getDust(GTMaterial.Lithium, 1));
		addByproduct("oreMolybdenite", GTMaterialGen.getDust(GTMaterial.Molybdenum, 1), GTMaterialGen.getDust(GTMaterial.Sulfur, 1));
		addByproduct("oreVibranium", GTMaterialGen.getGem(GTMaterial.Vibranium, 1), GTMaterialGen.getDust(GTMaterial.Vibranium, 1));
	}

	public static void recipesInteractions() {
		addInteraction(new IRecipeInput[] { input("dustResin", 1),
				input(GTMaterialGen.get(GTBlocks.tileHeating, 1)) }, GTMaterialGen.get(GTBlocks.driedResin, 1));
		addInteraction(new IRecipeInput[] { input("craftingToolKnife", 1),
				input(GTMaterialGen.get(GTBlocks.driedResin, 1)) }, GTMaterialGen.get(GTItems.resinPCB, 1));
		if (!GTConfig.harderPlates && !GTConfig.harderRods && IC2.getRefinedIron().equals("ingotRefinedIron")) {
			addInteraction(new IRecipeInput[] { input("craftingToolFile", 1),
					input(GTMaterialGen.getIc2(Ic2Items.machine, 1)) }, GTMaterialGen.getCasing(GTMaterial.RefinedIron, 1), GTMaterialGen.getSmallDust(GTMaterial.Iron, 2));
		}
	}

	/**
	 * ItemStack input version of byproduct JEI helper
	 * 
	 * @param input   - stack input
	 * @param outputs - array of stack outputs
	 */
	public static void addByproduct(ItemStack input, ItemStack... outputs) {
		addByproduct(new IRecipeInput[] { new RecipeInputItemStack(input) }, outputs);
	}

	/**
	 * ItemStack input version of byproduct JEI helper
	 * 
	 * @param input   - string for input
	 * @param outputs - array of stack outputs
	 */
	public static void addByproduct(String input, ItemStack... outputs) {
		addByproduct(new IRecipeInput[] { new RecipeInputOreDict(input, 1) }, outputs);
	}

	// private method use public versions above
	private static void addByproduct(IRecipeInput[] inputs, ItemStack... outputs) {
		List<IRecipeInput> inlist = new ArrayList<>();
		List<ItemStack> outlist = new ArrayList<>();
		for (IRecipeInput input : inputs) {
			inlist.add(input);
		}
		for (ItemStack output : outputs) {
			outlist.add(output);
		}
		addByproduct(inlist, new MachineOutput(null, outlist));
	}

	// private method use public versions above
	private static void addByproduct(List<IRecipeInput> input, MachineOutput output) {
		BYPRODUCT_LIST.addRecipe(input, output, output.getAllOutputs().get(0).getDisplayName(), 0);
	}

	public static void addInteraction(IRecipeInput[] inputs, ItemStack... outputs) {
		List<IRecipeInput> inlist = new ArrayList<>();
		List<ItemStack> outlist = new ArrayList<>();
		for (IRecipeInput input : inputs) {
			inlist.add(input);
		}
		for (ItemStack output : outputs) {
			outlist.add(output);
		}
		addInteraction(inlist, new MachineOutput(null, outlist));
	}

	// private method use public versions above
	private static void addInteraction(List<IRecipeInput> input, MachineOutput output) {
		INTERACTION_LIST.addRecipe(input, output, output.getAllOutputs().get(0).getDisplayName(), 0);
	}

	/*
	 * Adds a macerator recipe while removing duplicates generated by ic2c
	 */
	public static void maceratorUtil(String input, int amount, ItemStack output) {
		TileEntityMacerator.oreBlacklist.add(input);
		TileEntityMacerator.addRecipe(input, amount, output);
	}

	/*
	 * removing smelting recipes code by Muramasa
	 */
	public static void removeSmelting(ItemStack resultStack) {
		ItemStack recipeResult;
		Map<ItemStack, ItemStack> recipes = FurnaceRecipes.instance().getSmeltingList();
		Iterator<ItemStack> iterator = recipes.keySet().iterator();
		while (iterator.hasNext()) {
			ItemStack tmpRecipe = iterator.next();
			recipeResult = recipes.get(tmpRecipe);
			if (ItemStack.areItemStacksEqual(resultStack, recipeResult)) {
				iterator.remove();
			}
		}
	}

	/*
	 * the 2 methods below are utilities for making recipes in all tiles extended
	 * off this class
	 */
	public static IRecipeInput input(ItemStack stack) {
		return new RecipeInputItemStack(stack);
	}

	public static IRecipeInput input(String name, int amount) {
		return new RecipeInputOreDict(name, amount);
	}
}