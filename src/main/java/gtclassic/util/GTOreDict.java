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

		OreDictionary.registerOre("dustLithium", GTItems.lithium);
		OreDictionary.registerOre("dustCarbon", GTItems.carbon);

		// TODO bug speiger about this ore dict

		OreDictionary.registerOre("dustNetherrack", Ic2Items.netherrackDust);
		OreDictionary.registerOre("dustObsidian", Ic2Items.obsidianDust);
		OreDictionary.registerOre("dustEnderpearl", GTItems.dustEnderpearl);
		OreDictionary.registerOre("dustEnderEye", GTItems.dustEnderEye);
		OreDictionary.registerOre("dustLazurite", GTItems.dustLazurite);
		OreDictionary.registerOre("dyeBlue", GTItems.dustLazurite);
		OreDictionary.registerOre("dustPyrite", GTItems.dustPyrite);
		OreDictionary.registerOre("dustCalcite", GTItems.dustCalcite);
		OreDictionary.registerOre("dustFlint", GTItems.dustFlint);
		OreDictionary.registerOre("dustUranium", GTItems.dustUranium);
		OreDictionary.registerOre("dustBauxite", GTItems.dustBauxite);
		OreDictionary.registerOre("dustAluminium", GTItems.dustAluminium);
		OreDictionary.registerOre("dustAluminum", GTItems.dustAluminium);
		OreDictionary.registerOre("dustTitanium", GTItems.dustTitanium);
		OreDictionary.registerOre("dustChrome", GTItems.dustChrome);
		OreDictionary.registerOre("dustRuby", GTItems.dustRuby);
		OreDictionary.registerOre("dustSapphire", GTItems.dustSapphire);
		OreDictionary.registerOre("dustGreenSapphire", GTItems.dustGreenSapphire);
		OreDictionary.registerOre("dustEmerald", GTItems.dustEmerald);
		OreDictionary.registerOre("dustSodalite", GTItems.dustSodalite);
		OreDictionary.registerOre("dustTungsten", GTItems.dustTungsten);

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

		OreDictionary.registerOre("gemRuby", GTItems.gemRuby);
		OreDictionary.registerOre("gemSapphire", GTItems.gemSapphire);
		OreDictionary.registerOre("ingotAluminium", GTItems.ingotAluminium);
		OreDictionary.registerOre("ingotAluminum", GTItems.ingotAluminium);
		OreDictionary.registerOre("ingotChrome", GTItems.ingotChrome);
		OreDictionary.registerOre("ingotIridium", GTItems.ingotIridium);
		OreDictionary.registerOre("ingotTitanium", GTItems.ingotTitanium);

		OreDictionary.registerOre("blockIridium", GTBlocks.iridiumBlock);
		OreDictionary.registerOre("blockRuby", GTBlocks.rubyBlock);
		OreDictionary.registerOre("blockSapphire", GTBlocks.sapphireBlock);
		OreDictionary.registerOre("blockAluminium", GTBlocks.aluminiumBlock);
		OreDictionary.registerOre("blockAluminum", GTBlocks.aluminiumBlock);
		OreDictionary.registerOre("blockChrome", GTBlocks.chromeBlock);
		OreDictionary.registerOre("blockTitanium", GTBlocks.titaniumBlock);
		OreDictionary.registerOre("blockTungsten", GTBlocks.tungstenBlock);
		OreDictionary.registerOre("blockPlatinum", GTBlocks.platinumBlock);

		OreDictionary.registerOre("itemSilicon", GTItems.plateSilicon);

		OreDictionary.registerOre("plateIridium", Ic2Items.iridiumPlate);

		OreDictionary.registerOre("batteryAdvanced", Ic2Items.energyCrystal);
		OreDictionary.registerOre("batteryAdvanced", GTItems.smallLithium);

		// TODO find a better way to register plasma, mainly for recipes

		OreDictionary.registerOre("itemPlasma", GTItems.plasmaHelium);
		// OreDictionary.registerOre("itemPlasma", Ic2Items.plasmaCell);

	}

}
