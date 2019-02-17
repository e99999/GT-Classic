package gtclassic.util;

import gtclassic.GTBlocks;
import gtclassic.GTItems;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class GTOreDict {

	public static void init() {

		// Register tools with ore dict
		registerHammer(GTItems.hammerIron);
		registerHammer(GTItems.hammerTitanium);
		registerHammer(GTItems.hammerTungstenSteel);
		registerFile(GTItems.fileIron);
		registerFile(GTItems.fileTitanium);
		registerFile(GTItems.fileTungstenSteel);
		registerRubber(GTItems.braintechAerospaceARDT);

		// Register ores... with ore dict
		OreDictionary.registerOre("oreGalena", GTBlocks.galenaOre);
		OreDictionary.registerOre("oreIridium", GTBlocks.iridiumOre);
		OreDictionary.registerOre("oreRuby", GTBlocks.rubyOre);
		OreDictionary.registerOre("oreSapphire", GTBlocks.sapphireOre);
		OreDictionary.registerOre("oreBauxite", GTBlocks.bauxiteOre);
		OreDictionary.registerOre("oreMagnetite", GTBlocks.sandIron);
		OreDictionary.registerOre("orePyrite", GTBlocks.pyriteOre);
		OreDictionary.registerOre("oreCinnabar", GTBlocks.cinnabarOre);
		OreDictionary.registerOre("oreSphalerite", GTBlocks.sphaleriteOre);
		OreDictionary.registerOre("oreTungstate", GTBlocks.tungstateOre);
		OreDictionary.registerOre("oreSheldonite", GTBlocks.sheldoniteOre);
		OreDictionary.registerOre("oreOlivine", GTBlocks.olivineOre);
		OreDictionary.registerOre("oreSodalite", GTBlocks.sodaliteOre);

		// Just doing this to make iteration with gun powder easier
		OreDictionary.registerOre("dustGunpowder", Items.GUNPOWDER);

		// Register some missing Ic2c stuff
		OreDictionary.registerOre("dustNetherrack", Ic2Items.netherrackDust);
		OreDictionary.registerOre("dustObsidian", Ic2Items.obsidianDust);
		OreDictionary.registerOre("plateIridium", Ic2Items.iridiumPlate);

	}

	public static void registerHammer(Item tool) {
		OreDictionary.registerOre("craftingToolForgeHammer", new ItemStack(tool, 1, OreDictionary.WILDCARD_VALUE));
	}

	public static void registerFile(Item tool) {
		OreDictionary.registerOre("craftingToolFile", new ItemStack(tool, 1, OreDictionary.WILDCARD_VALUE));
	}

	public static void registerRubber(Item tool) {
		OreDictionary.registerOre("itemRubber", new ItemStack(tool, 1, OreDictionary.WILDCARD_VALUE));
	}

}
