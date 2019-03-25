package gtclassic.recipe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gtclassic.GTBlocks;
import gtclassic.GTConfig;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileMultiBlastFurnace;
import gtclassic.tile.GTTileMultiBloomery;
import gtclassic.tile.GTTileMultiFusionComputer;
import gtclassic.util.recipe.GTMultiInputRecipeList;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.machine.low.TileEntityCompressor;
import ic2.core.block.machine.low.TileEntityExtractor;
import ic2.core.block.machine.low.TileEntityMacerator;
import ic2.core.item.recipe.entry.RecipeInputCombined;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GTRecipeProcessing {

	static GTMaterialGen GT;
	static GTMaterial M;

	static IMachineRecipeList smelting = ClassicRecipes.furnace;

	public static final GTMultiInputRecipeList BLOOM_RECIPE_LIST = new GTMultiInputRecipeList("bloomery");

	public static final IRecipeInput fuel = new RecipeInputCombined(1,
			new IRecipeInput[] { new RecipeInputOreDict("blockCoal"), new RecipeInputOreDict("blockCharcoal"),
					new RecipeInputItemStack(new ItemStack(Items.COAL, 9)),
					new RecipeInputItemStack(new ItemStack(Items.COAL, 9, 1)) });

	public static void recipesProcessing() {

		removeSmelting(GT.getIc2(Ic2Items.rubber, 1));
		removeSmelting(GT.getIc2(Ic2Items.refinedIronIngot, 1));

		/*
		 * Recipes specific to GT Classic blocks
		 */
		maceratorUtil("oreBauxite", 1, GT.getDust(M.Bauxite, 4));
		maceratorUtil("oreIridium", 1, GT.getIc2(Ic2Items.iridiumOre, 2));
		maceratorUtil("orePyrite", 1, GT.getDust(M.Pyrite, 5));
		maceratorUtil("oreCinnabar", 1, GT.getDust(M.Cinnabar, 5));
		maceratorUtil("oreSphalerite", 1, GT.getDust(M.Sphalerite, 5));
		maceratorUtil("oreSodalite", 1, GT.getDust(M.Sodalite, 12));

		GameRegistry.addSmelting(GT.getDust(M.Galena, 1), (GT.getNugget(M.Lead, 3)), 0.1F);
		GameRegistry.addSmelting(GTBlocks.iridiumOre, (GT.getIc2(Ic2Items.iridiumOre, 1)), 0.1F);
		GameRegistry.addSmelting(GT.getDust(M.Magnetite, 1), GT.get(Items.IRON_NUGGET, 3), 0.1F);
		GameRegistry.addSmelting(GT.get(GTBlocks.magnetiteSand), GT.get(Items.IRON_NUGGET, 3), 0.1F);
		GameRegistry.addSmelting(GTBlocks.pyriteOre, GT.get(Items.IRON_INGOT), 0.1F);
		GameRegistry.addSmelting(GTBlocks.slagSand, GT.get(GTBlocks.slagGlass), 0.1F);

		TileEntityExtractor.addRecipe("oreRuby", 1, GT.getGem(M.Ruby, 3), 0.1F);
		TileEntityExtractor.addRecipe("oreSapphire", 1, GT.getGem(M.Sapphire, 3), 0.1F);
		TileEntityExtractor.addRecipe("oreOlivine", 1, GT.getGem(M.Olivine, 3), 0.1F);

		/*
		 * Maceration recipes not covered by Ic2c automatically or that need to be
		 * different
		 */
		TileEntityMacerator.addRecipe(GT.get(Items.FLINT, 1), GT.getDust(M.Flint, 1), 0.1F);
		// TODO add granite recipe to granite dust
		TileEntityMacerator.addRecipe(Ic2Items.uraniumDrop, 1, GT.getDust(M.Uranium, 1), 0.1F);
		TileEntityMacerator.addRecipe("enderpearl", 1, GT.getDust(M.EnderPearl, 1), 0.2F);
		TileEntityMacerator.addRecipe(GT.get(Items.ENDER_EYE, 1), GT.getDust(M.EnderEye, 1), 0.2F);
		TileEntityMacerator.addRecipe("gemDiamond", 1, GT.getDust(M.Diamond, 1), 0.1F);
		TileEntityMacerator.addRecipe("gemEmerald", 1, GT.getDust(M.Emerald, 1), 0.1F);
		TileEntityMacerator.addRecipe("plateRefinedIron", 1, GT.getIc2(Ic2Items.ironDust, 1));

		/*
		 * Compressor recipes
		 */
		TileEntityCompressor.addRecipe(GT.getDust(M.Carbon, 8), GT.getIc2(Ic2Items.carbonFiber, 1), 0.1F);
		TileEntityCompressor.addRecipe("dustUranium", 1, GT.getIc2(Ic2Items.uraniumIngot, 1), 0.1F);
		TileEntityCompressor.addRecipe("dustEmerald", 1, GT.get(Items.EMERALD), 0.1F);
		TileEntityCompressor.addRecipe("dustDiamond", 1, GT.get(Items.DIAMOND), 0.1F);
		TileEntityCompressor.addRecipe("dustGraphite", 1, GT.getIngot(M.Graphite, 1), 0.1F);
		TileEntityCompressor.addRecipe("dustSmallGraphite", 4, GT.getIngot(M.Graphite, 1), 0.1F);

		/*
		 * Bloomery Recipes
		 * 
		 */

		IBlockState bloom = GTBlocks.bloomBlock.getDefaultState();

		GTTileMultiBloomery.RECIPE_LIST.addRecipe("ingotIron", bloom, 4, 400, new RecipeInputOreDict("ingotIron", 3),
				fuel);
		GTTileMultiBloomery.RECIPE_LIST.addRecipe("dustIron", bloom, 4, 400, new RecipeInputOreDict("dustIron", 3),
				fuel);
		GTTileMultiBloomery.RECIPE_LIST.addRecipe("oreIron", bloom, 4, 400, new RecipeInputOreDict("oreIron", 1),
				new RecipeInputOreDict("dustCalcite", 1), fuel);
		GTTileMultiBloomery.RECIPE_LIST.addRecipe("dustPyrite", bloom, 4, 400, new RecipeInputOreDict("dustPyrite", 2),
				new RecipeInputOreDict("dustCalcite", 2), fuel);
		GTTileMultiBloomery.RECIPE_LIST.addRecipe("dustMagnetite", bloom, 4, 400,
				new RecipeInputOreDict("dustMagnetite", 2), new RecipeInputOreDict("dustCalcite", 2), fuel);

		addFakeBloomRecipe("ingotIron", 3, GT.get(GTBlocks.bloomBlock));
		addFakeBloomRecipe("dustIron", 3, GT.get(GTBlocks.bloomBlock));
		addFakeBloomRecipe("oreIron", 1, "dustCalcite", 1, GT.get(GTBlocks.bloomBlock));
		addFakeBloomRecipe("dustPyrite", 2, "dustCalcite", 2, GT.get(GTBlocks.bloomBlock));
		addFakeBloomRecipe("dustMagnetite", 2, "dustCalcite", 2, GT.get(GTBlocks.bloomBlock));

		/*
		 * GT Blast Furnace recipes
		 */

		GTTileMultiBlastFurnace.addRecipe("ingotIron", 1, "dustCoal", 2, GT.getIngot(M.Steel, 1),
				GT.getDust(M.DarkAshes, 2));
		GTTileMultiBlastFurnace.addRecipe("ingotRefinedIron", 1, "dustCoal", 2, GT.getIngot(M.Steel, 1),
				GT.getDust(M.DarkAshes, 2));
		GTTileMultiBlastFurnace.addRecipe("oreIron", 1, "dustCalcite", 1, GT.getIc2(Ic2Items.refinedIronIngot, 3),
				GT.getSmallDust(M.Slag, 2));
		GTTileMultiBlastFurnace.addRecipe("dustPyrite", 1, "dustCalcite", 1, GT.getIc2(Ic2Items.refinedIronIngot, 2),
				GT.getSmallDust(M.Slag, 1));
		GTTileMultiBlastFurnace.addRecipe("dustMagnetite", 1, "dustCalcite", 1, GT.getIc2(Ic2Items.refinedIronIngot, 2),
				GT.getSmallDust(M.Slag, 1));
		GTTileMultiBlastFurnace.addRecipe("dustTantalum", 1, GT.getIngot(M.Tantalum, 1));
		GTTileMultiBlastFurnace.addRecipe("dustSmallTantalum", 4, GT.getIngot(M.Tantalum, 1));
		GTTileMultiBlastFurnace.addRecipe("dustTungsten", 1, GT.getIngot(M.Tungsten, 1));
		GTTileMultiBlastFurnace.addRecipe("dustSmallTungsten", 4, GT.getIngot(M.Tungsten, 1));

		if (GTConfig.harderAluminium) {
			GTTileMultiBlastFurnace.addRecipe("dustAluminium", 1, GT.getIngot(M.Aluminium, 1));
			GTTileMultiBlastFurnace.addRecipe("dustSmallAluminium", 4, GT.getIngot(M.Aluminium, 1));
		}

		/*
		 * Just a few test fusion recipes
		 */
		GTTileMultiFusionComputer.addRecipe("dustTungsten", 1, GT.getFluid(M.Lithium, 1),
				GT.getIc2(Ic2Items.iridiumOre, 1));
		GTTileMultiFusionComputer.addRecipe("dustTungsten", 1, GT.getFluid(M.Beryllium, 1), GT.getDust(M.Platinum, 1));

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
	 * fake recipes for the bloomery to show in JEI
	 */
	public static void addFakeBloomRecipe(String input1, int amount1, String input2, int amount2, ItemStack output) {
		List<IRecipeInput> inputs = new ArrayList<>();
		List<ItemStack> outputs = new ArrayList<>();
		inputs.add((IRecipeInput) (new RecipeInputOreDict(input1, amount1)));
		inputs.add((IRecipeInput) (new RecipeInputOreDict(input2, amount2)));
		inputs.add(fuel);
		outputs.add(output);
		outputs.add(GT.getDust(GTMaterial.DarkAshes, 4));
		addFakeBloomRecipe(inputs, new MachineOutput(null, outputs));
	}

	public static void addFakeBloomRecipe(String input1, int amount1, ItemStack output) {
		List<IRecipeInput> inputs = new ArrayList<>();
		List<ItemStack> outputs = new ArrayList<>();
		inputs.add((IRecipeInput) (new RecipeInputOreDict(input1, amount1)));
		inputs.add(fuel);
		outputs.add(output);
		outputs.add(GT.getDust(GTMaterial.DarkAshes, 4));
		addFakeBloomRecipe(inputs, new MachineOutput(null, outputs));
	}

	/*
	 * fake recipes for the bloomery to show in JEI
	 */
	static void addFakeBloomRecipe(List<IRecipeInput> input, MachineOutput output) {
		BLOOM_RECIPE_LIST.addRecipe(input, output, output.getAllOutputs().get(0).getDisplayName());
	}

}