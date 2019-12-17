package gtclassic.common;

import gtclassic.api.helpers.GTHelperMods;
import gtclassic.api.material.GTMaterial;
import gtclassic.api.material.GTMaterialFlag;
import gtclassic.api.material.GTMaterialGen;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

public class GTOreDict {

	public static void init() {
		// GT Materials
		for (GTMaterial mat : GTMaterial.values()) {
			// Iterating through resource blocks
			if (mat.hasFlag(GTMaterialFlag.BLOCKMETAL) || mat.hasFlag(GTMaterialFlag.BLOCKGEM)) {
				OreDictionary.registerOre("block" + mat.getDisplayName(), GTMaterialGen.getMaterialBlock(mat, 1));
			}
			// Regular dusts
			if (mat.hasFlag(GTMaterialFlag.DUST)) {
				OreDictionary.registerOre("dust" + mat.getDisplayName(), GTMaterialGen.getDust(mat, 1));
			}
			// Gems
			if (GTMaterial.isGem(mat)) {
				OreDictionary.registerOre("gem" + mat.getDisplayName(), GTMaterialGen.getGem(mat, 1));
			}
			// Ingots
			if (mat.hasFlag(GTMaterialFlag.INGOT)) {
				OreDictionary.registerOre("ingot" + mat.getDisplayName(), GTMaterialGen.getIngot(mat, 1));
			}
			if (mat.hasFlag(GTMaterialFlag.PIPEITEM)){
				OreDictionary.registerOre("pipeItem" + mat.getDisplayName(), GTMaterialGen.getItemPipe(mat, 1));	
				OreDictionary.registerOre("pipeItemLarge" + mat.getDisplayName(), GTMaterialGen.getItemPipeLarge(mat, 1));
			}
			if (mat.hasFlag(GTMaterialFlag.PIPEFLUID)){
				OreDictionary.registerOre("pipeFluidSmall" + mat.getDisplayName(), GTMaterialGen.getFluidPipeSmall(mat, 1));	
				OreDictionary.registerOre("pipeFluid" + mat.getDisplayName(), GTMaterialGen.getFluidPipe(mat, 1));	
				OreDictionary.registerOre("pipeFluidLarge" + mat.getDisplayName(), GTMaterialGen.getFluidPipeLarge(mat, 1));
			}
		}
		// Just doing these to make iteration & unification easier in some cases
		OreDictionary.registerOre("dustGunpowder", Items.GUNPOWDER);
		OreDictionary.registerOre("bookshelf", Blocks.BOOKSHELF);
		// Register some missing Ic2c stuff cause Speiger is a bad doge
		OreDictionary.registerOre("dustNetherrack", Ic2Items.netherrackDust);
		OreDictionary.registerOre("dustObsidian", Ic2Items.obsidianDust);
		OreDictionary.registerOre("plateIridiumAlloy", Ic2Items.iridiumPlate);
		OreDictionary.registerOre("blockCharcoal", Ic2Items.charcoalBlock);
		OreDictionary.registerOre("logRubber", Ic2Items.rubberWood);
		OreDictionary.registerOre("dustRareEarth", Ic2Items.rareEarthDust);
		OreDictionary.registerOre("dustWheat", Ic2Items.flour);
		if (GTConfig.general.oreDictWroughtIron) {
			OreDictionary.registerOre("ingotWroughtIron", Ic2Items.refinedIronIngot);
		}
		if (!Loader.isModLoaded(GTHelperMods.GTCX)) {
			OreDictionary.registerOre("machineBlockBasic", Ic2Items.machine);
		}
		OreDictionary.registerOre("machineBlockAdvanced", Ic2Items.advMachine);
		// Registering Aluminium for idiots
		GTMaterial aluminium = GTMaterial.Aluminium;
		OreDictionary.registerOre("blockAluminum", (GTMaterialGen.getMaterialBlock(aluminium, 1)));
		OreDictionary.registerOre("dustAluminum", (GTMaterialGen.getDust(aluminium, 1)));
		OreDictionary.registerOre("ingotAluminum", (GTMaterialGen.getIngot(aluminium, 1)));
		// Chrome and chromium
		GTMaterial chrome = GTMaterial.Chrome;
		OreDictionary.registerOre("blockChromium", (GTMaterialGen.getMaterialBlock(chrome, 1)));
		OreDictionary.registerOre("dustChromium", (GTMaterialGen.getDust(chrome, 1)));
		OreDictionary.registerOre("ingotChromium", (GTMaterialGen.getIngot(chrome, 1)));
		// Stuff to fit with how other mods have done it
		OreDictionary.registerOre("dustEnderEye", (GTMaterialGen.getDust(GTMaterial.EnderEye, 1)));
		OreDictionary.registerOre("itemSilicon", (GTMaterialGen.getIngot(GTMaterial.Silicon, 1)));
		OreDictionary.registerOre("dyeCyan", (GTMaterialGen.getDust(GTMaterial.Lazurite, 1)));
		OreDictionary.registerOre("dyeBlue", (GTMaterialGen.getDust(GTMaterial.Sodalite, 1)));
		OreDictionary.registerOre("chestIron", (GTMaterialGen.get(GTBlocks.tileCabinet, 1)));
		// My Ores
		OreDictionary.registerOre("orePlatinum", (GTMaterialGen.get(GTBlocks.oreSheldonite, 1)));
		OreDictionary.registerOre("oreSheldonite", (GTMaterialGen.get(GTBlocks.oreSheldonite, 1)));
		OreDictionary.registerOre("oreIridium", (GTMaterialGen.get(GTBlocks.oreIridium, 1)));
		OreDictionary.registerOre("oreRuby", (GTMaterialGen.get(GTBlocks.oreRuby, 1)));
		OreDictionary.registerOre("oreSapphire", (GTMaterialGen.get(GTBlocks.oreSapphire, 1)));
		OreDictionary.registerOre("oreBauxite", (GTMaterialGen.get(GTBlocks.oreBauxite, 1)));
		ClassicRecipes.oreRegistry.registerValueableOre(GTBlocks.oreSheldonite, 6);
		ClassicRecipes.oreRegistry.registerValueableOre(GTBlocks.oreIridium, 7);
		ClassicRecipes.oreRegistry.registerValueableOre(GTBlocks.oreRuby, 3);
		ClassicRecipes.oreRegistry.registerValueableOre(GTBlocks.oreSapphire, 3);
		// My Stuff
		OreDictionary.registerOre("craftingToolMortar", new ItemStack(GTItems.flintMortar, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("craftingToolMortar", new ItemStack(GTItems.ironMortar, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("craftingToolDuctTape", new ItemStack(GTItems.ductTape, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("craftingToolMonkeyWrench", new ItemStack(GTItems.monkeyWrench, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("circuitMaster", (GTMaterialGen.get(GTItems.circuitEnergy, 1)));
		OreDictionary.registerOre("circuitElite", (GTMaterialGen.get(GTItems.circuitData, 1)));
		OreDictionary.registerOre("circuitStorage", (GTMaterialGen.get(GTItems.chipData, 1)));
		OreDictionary.registerOre("circuitData", (GTMaterialGen.get(GTItems.chipData, 1)));
		OreDictionary.registerOre("circuitUltimate", (GTMaterialGen.get(GTItems.orbData, 1)));
		OreDictionary.registerOre("batteryUltimate", new ItemStack(GTItems.orbEnergy, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("craftingSuperconductor", (GTMaterialGen.get(GTItems.superConductor, 1)));
		OreDictionary.registerOre("machineBlockElite", (GTMaterialGen.get(GTBlocks.casingHighlyAdvanced, 1)));
		OreDictionary.registerOre("machineBlockHighlyAdvanced", (GTMaterialGen.get(GTBlocks.casingHighlyAdvanced, 1)));
		/** Pams Harvestcraft **/
		if (!Loader.isModLoaded(GTHelperMods.HARVESTCRAFT)) {
			OreDictionary.registerOre("listAllmeatraw", GTMaterialGen.get(Items.PORKCHOP));
			OreDictionary.registerOre("listAllmeatcooked", GTMaterialGen.get(Items.COOKED_PORKCHOP));
			OreDictionary.registerOre("listAllmeatraw", GTMaterialGen.get(Items.BEEF));
			OreDictionary.registerOre("listAllmeatcooked", GTMaterialGen.get(Items.COOKED_BEEF));
			OreDictionary.registerOre("listAllfishraw", GTMaterialGen.get(Items.FISH));
			OreDictionary.registerOre("listAllfishcooked", GTMaterialGen.get(Items.COOKED_FISH));
			OreDictionary.registerOre("listAllmeatraw", GTMaterialGen.get(Items.CHICKEN));
			OreDictionary.registerOre("listAllmeatcooked", GTMaterialGen.get(Items.COOKED_CHICKEN));
			OreDictionary.registerOre("listAllmeatraw", GTMaterialGen.get(Items.MUTTON));
			OreDictionary.registerOre("listAllmeatcooked", GTMaterialGen.get(Items.COOKED_MUTTON));
			OreDictionary.registerOre("listAllfruit", GTMaterialGen.get(Items.APPLE));
			OreDictionary.registerOre("listAllveggie", GTMaterialGen.get(Blocks.PUMPKIN));
			OreDictionary.registerOre("listAllveggie", GTMaterialGen.get(Items.CARROT));
			OreDictionary.registerOre("listAllveggie", GTMaterialGen.get(Items.POTATO));
			OreDictionary.registerOre("listAllcookie", GTMaterialGen.get(Items.COOKIE));
		}
	}
}
