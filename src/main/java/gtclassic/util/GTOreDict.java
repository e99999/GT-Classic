package gtclassic.util;

import gtclassic.GTBlocks;
import gtclassic.GTItems;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class GTOreDict {

	public static void init() {

		// registering gregtech tools with ore dict
		OreDictionary.registerOre("craftingToolForgeHammer",
				new ItemStack(GTItems.hammerIron, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("craftingToolForgeHammer",
				new ItemStack(GTItems.hammerAluminium, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("craftingToolForgeHammer",
				new ItemStack(GTItems.hammerTitanium, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("craftingToolForgeHammer",
				new ItemStack(GTItems.hammerTungstenSteel, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("craftingToolFile", new ItemStack(GTItems.fileIron, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("craftingToolFile",
				new ItemStack(GTItems.fileAluminium, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("craftingToolFile",
				new ItemStack(GTItems.fileTitanium, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("craftingToolFile",
				new ItemStack(GTItems.fileTungstenSteel, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("itemRubber",
				new ItemStack(GTItems.braintechAerospaceARDT, 1, OreDictionary.WILDCARD_VALUE));

		// TODO bug speiger about this ore dict

		OreDictionary.registerOre("dustNetherrack", Ic2Items.netherrackDust);
		OreDictionary.registerOre("dustObsidian", Ic2Items.obsidianDust);

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

		OreDictionary.registerOre("blockIridium", GTBlocks.iridiumBlock);
		OreDictionary.registerOre("blockRuby", GTBlocks.rubyBlock);
		OreDictionary.registerOre("blockSapphire", GTBlocks.sapphireBlock);
		OreDictionary.registerOre("blockAluminium", GTBlocks.aluminiumBlock);
		OreDictionary.registerOre("blockAluminum", GTBlocks.aluminiumBlock);
		OreDictionary.registerOre("blockChrome", GTBlocks.chromeBlock);
		OreDictionary.registerOre("blockTitanium", GTBlocks.titaniumBlock);
		OreDictionary.registerOre("blockTungsten", GTBlocks.tungstenBlock);
		OreDictionary.registerOre("blockPlatinum", GTBlocks.platinumBlock);

		OreDictionary.registerOre("plateIridium", Ic2Items.iridiumPlate);

		OreDictionary.registerOre("batteryAdvanced", Ic2Items.energyCrystal);
		OreDictionary.registerOre("batteryAdvanced", GTItems.smallLithium);

		// TODO find a better way to register plasma, mainly for recipes
		// OreDictionary.registerOre("itemPlasma", Ic2Items.plasmaCell);

	}

}
