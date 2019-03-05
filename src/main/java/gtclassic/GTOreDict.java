package gtclassic;

import gtclassic.tool.GTToolFile;
import gtclassic.tool.GTToolHammer;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class GTOreDict {

	public static void init() {

		for (Item item : Item.REGISTRY) {
			if (item instanceof GTToolFile) {
				registerFile(item);
			}
			if (item instanceof GTToolHammer) {
				registerHammer(item);
			}
		}

		// Register ores... with ore dict
		OreDictionary.registerOre("oreGalena", GTBlocks.galenaOre);
		OreDictionary.registerOre("oreIridium", GTBlocks.iridiumOre);
		OreDictionary.registerOre("oreRuby", GTBlocks.rubyOre);
		OreDictionary.registerOre("oreSapphire", GTBlocks.sapphireOre);
		OreDictionary.registerOre("oreBauxite", GTBlocks.bauxiteOre);
		OreDictionary.registerOre("oreCalcite", GTBlocks.calciteOre);
		OreDictionary.registerOre("oreMagnetite", GTBlocks.magnetiteOre);
		OreDictionary.registerOre("orePyrite", GTBlocks.pyriteOre);
		OreDictionary.registerOre("oreCinnabar", GTBlocks.cinnabarOre);
		OreDictionary.registerOre("oreSphalerite", GTBlocks.sphaleriteOre);
		OreDictionary.registerOre("oreTungsten", GTBlocks.tungstateOre);
		OreDictionary.registerOre("oreSheldonite", GTBlocks.sheldoniteOre);
		OreDictionary.registerOre("orePlatinum", GTBlocks.sheldoniteOre);
		OreDictionary.registerOre("oreOlivine", GTBlocks.olivineOre);
		OreDictionary.registerOre("oreSodalite", GTBlocks.sodaliteOre);

		// Just doing these to make iteration & unification easier in some cases
		OreDictionary.registerOre("dustGunpowder", Items.GUNPOWDER);
		OreDictionary.registerOre("circuitElite", GTItems.circuitSapphire);
		OreDictionary.registerOre("bookshelf", Blocks.BOOKSHELF);

		// Register some missing Ic2c stuff
		OreDictionary.registerOre("dustNetherrack", Ic2Items.netherrackDust);
		OreDictionary.registerOre("dustObsidian", Ic2Items.obsidianDust);
		OreDictionary.registerOre("plateIridium", Ic2Items.iridiumPlate);

	}

	public static void registerFile(Item tool) {
		OreDictionary.registerOre("craftingToolFile", new ItemStack(tool, 1, OreDictionary.WILDCARD_VALUE));
	}

	public static void registerHammer(Item tool) {
		OreDictionary.registerOre("craftingToolForgeHammer", new ItemStack(tool, 1, OreDictionary.WILDCARD_VALUE));
	}

}
