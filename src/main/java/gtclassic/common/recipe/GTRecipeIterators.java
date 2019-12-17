package gtclassic.common.recipe;

import gtclassic.api.helpers.GTHelperMods;
import gtclassic.api.helpers.GTHelperStack;
import gtclassic.api.material.GTMaterial;
import gtclassic.api.material.GTMaterialFlag;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.common.GTConfig;
import gtclassic.common.GTItems;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.api.classic.recipe.machine.IMachineRecipeList.RecipeEntry;
import ic2.core.block.machine.low.TileEntityCompressor;
import ic2.core.block.machine.low.TileEntityExtractor;
import ic2.core.block.machine.low.TileEntityMacerator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class GTRecipeIterators {

	public static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;

	/** Iterates through the GregTech Classic mat registry **/
	public static void init() {
		/*
		 * The statements below iterate through the material registry to create recipes
		 * for the correct corresponding items and blocks.
		 */
		for (GTMaterial mat : GTMaterial.values()) {
			createIngotRecipe(mat);
			createGemRecipe(mat);
			createBlockRecipe(mat);
			createTubeRecipe(mat);
			if (!Loader.isModLoaded(GTHelperMods.GTCX)) {
				createPipeRecipe(mat);
			}
		}
	}

	/** Iterates through loaded itemstacks for all mods **/
	public static void postInit() {
		createMortarRecipe();
		for (Item item : Item.REGISTRY) {
			NonNullList<ItemStack> items = NonNullList.create();
			item.getSubItems(CreativeTabs.SEARCH, items);
			for (ItemStack stack : items) {
				if (GTConfig.general.oreDictWroughtIron && GTHelperStack.matchOreDict(stack, "ingotWroughtIron")
						&& !GTHelperStack.matchOreDict(stack, "ingotRefinedIron")) {
					OreDictionary.registerOre("ingotRefinedIron", stack);
				}
				if (GTHelperStack.matchOreDict(stack, "ingotAluminum")
						&& !GTHelperStack.matchOreDict(stack, "ingotAluminium")) {
					OreDictionary.registerOre("ingotAluminium", stack);
				}
				if (GTHelperStack.matchOreDict(stack, "dustAluminum")
						&& !GTHelperStack.matchOreDict(stack, "dustAluminium")) {
					OreDictionary.registerOre("dustAluminium", stack);
				}
				if (GTHelperStack.matchOreDict(stack, "ingotChromium")
						&& !GTHelperStack.matchOreDict(stack, "ingotChrome")) {
					OreDictionary.registerOre("ingotChrome", stack);
				}
			}
		}
	}

	public static void createIngotRecipe(GTMaterial mat) {
		if (mat.hasFlag(GTMaterialFlag.INGOT) && mat.hasFlag(GTMaterialFlag.DUST) && mat.getSmeltable()) {
			GameRegistry.addSmelting(GTMaterialGen.getDust(mat, 1), (GTMaterialGen.getIngot(mat, 1)), 0.1F);
		}
	}

	public static void createGemRecipe(GTMaterial mat) {
		String dust = "dust" + mat.getDisplayName();
		String gem = "gem" + mat.getDisplayName();
		String block = "block" + mat.getDisplayName();
		if (GTMaterial.isGem(mat)) {
			// Dust to gem
			TileEntityCompressor.addRecipe(dust, 1, GTMaterialGen.getGem(mat, 1), 0.0F);
			// Inverse
			TileEntityMacerator.addRecipe(gem, 1, GTMaterialGen.getDust(mat, 1), 0.0F);
			if (mat.hasFlag(GTMaterialFlag.BLOCKGEM)) {
				// Block and gem related logic
				recipes.addShapelessRecipe(GTMaterialGen.getGem(mat, 9), new Object[] { block });
				TileEntityCompressor.addRecipe(gem, 9, GTMaterialGen.getMaterialBlock(mat, 1), 0.0F);
				TileEntityMacerator.addRecipe(block, 1, GTMaterialGen.getDust(mat, 9), 0.0F);
				recipes.addRecipe(GTMaterialGen.getMaterialBlock(mat, 1), new Object[] { "XXX", "XXX", "XXX", 'X',
						gem });
			}
		}
	}

	public static void createBlockRecipe(GTMaterial mat) {
		String ingot = "ingot" + mat.getDisplayName();
		String block = "block" + mat.getDisplayName();
		if (mat.hasFlag(GTMaterialFlag.BLOCKMETAL)) {
			if (mat.hasFlag(GTMaterialFlag.INGOT)) {
				// Block crafting recipe
				recipes.addRecipe(GTMaterialGen.getMaterialBlock(mat, 1), new Object[] { "XXX", "XXX", "XXX", 'X',
						ingot });
				TileEntityCompressor.addRecipe(ingot, 9, GTMaterialGen.getMaterialBlock(mat, 1), 0.0F);
				// Inverse
				recipes.addShapelessRecipe(GTMaterialGen.getIngot(mat, 9), new Object[] { block });
				TileEntityMacerator.addRecipe(block, 1, GTMaterialGen.getDust(mat, 9), 0.0F);
			}
		}
	}

	public static void createTubeRecipe(GTMaterial mat) {
		for (GTMaterialFlag fluidFlag : GTMaterialGen.getFluidFlagList()) {
			if (mat.hasFlag(fluidFlag)) {
				TileEntityExtractor.addRecipe(GTMaterialGen.getTube(mat, 1), GTMaterialGen.get(GTItems.testTube));
			}
		}
	}

	public static void createPipeRecipe(GTMaterial mat) {
		String ingot = "ingot" + mat.getDisplayName();
		String tool = "craftingToolMonkeyWrench";
		if (mat.hasFlag(GTMaterialFlag.PIPEITEM)) {
			recipes.addRecipe(GTMaterialGen.getItemPipe(mat, 2), "III", " T ", "III", 'I', ingot, 'T', tool);
			recipes.addRecipe(GTMaterialGen.getItemPipeLarge(mat, 1), "I I", "ITI", "I I", 'I', ingot, 'T', tool);
			TileEntityMacerator.addRecipe("pipeItem" + mat.getDisplayName(), 1, GTMaterialGen.getDust(mat, 3));
			TileEntityMacerator.addRecipe("pipeItemLarge" + mat.getDisplayName(), 1, GTMaterialGen.getDust(mat, 6));
		}
		if (mat.hasFlag(GTMaterialFlag.PIPEFLUID) && mat != GTMaterial.HighPressure) {
			recipes.addRecipe(GTMaterialGen.getFluidPipeSmall(mat, 6), "ITI", "I I", "I I", 'I', ingot, 'T', tool);
			recipes.addRecipe(GTMaterialGen.getFluidPipe(mat, 2), "III", " T ", "III", 'I', ingot, 'T', tool);
			recipes.addRecipe(GTMaterialGen.getFluidPipeLarge(mat, 1), "I I", "I I", "ITI", 'I', ingot, 'T', tool);
			TileEntityMacerator.addRecipe("pipeFluidSmall" + mat.getDisplayName(), 1, GTMaterialGen.getDust(mat, 1));
			TileEntityMacerator.addRecipe("pipeFluid" + mat.getDisplayName(), 1, GTMaterialGen.getDust(mat, 3));
			TileEntityMacerator.addRecipe("pipeFluidLarge" + mat.getDisplayName(), 1, GTMaterialGen.getDust(mat, 6));
		}
	}

	public static void createMortarRecipe() {
		// Grabs everything from the ic2 classic macerator list
		// Separate method so it can be done last in post init
		for (RecipeEntry entry : ClassicRecipes.macerator.getRecipeMap()) {
			if (entry.getInput().getInputs().get(0).getCount() == 1
					&& entry.getOutput().getAllOutputs().get(0).getCount() == 1) {
				recipes.addShapelessRecipe(entry.getOutput().getAllOutputs().get(0), entry.getInput(), "craftingToolMortar");
			}
		}
	}
}
