package gtclassic.util;

import ic2.core.platform.registry.Ic2Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class GTOreDict {

	public static void init() {

		OreDictionary.registerOre("dustTungsten", GTItems.tungsten);
		OreDictionary.registerOre("dustLithium", GTItems.lithium);
		OreDictionary.registerOre("dustCarbon", GTItems.carbon);

		OreDictionary.registerOre("dustEnderPearl", GTItems.dustEnderpearl);
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

		OreDictionary.registerOre("oreRuby", GTBlocks.rubyOre);
		OreDictionary.registerOre("oreSapphire", GTBlocks.sapphireOre);
		OreDictionary.registerOre("oreIridium", GTBlocks.iridiumOre);
		OreDictionary.registerOre("oreIridium", GTBlocks.iridiumEnd);
		OreDictionary.registerOre("oreBauxite", GTBlocks.bauxiteOre);
		OreDictionary.registerOre("oreMagnetite", GTBlocks.sandIron);

		OreDictionary.registerOre("gemRuby", GTItems.ruby);
		OreDictionary.registerOre("gemSapphire", GTItems.sapphire);
		OreDictionary.registerOre("ingotAluminium", GTItems.ingotAluminium);
		OreDictionary.registerOre("ingotAluminum", GTItems.ingotAluminium);
		OreDictionary.registerOre("ingotChrome", GTItems.ingotChrome);
		OreDictionary.registerOre("ingotIridium", GTItems.ingotIridium);
		OreDictionary.registerOre("ingotTitanium", GTItems.ingotTitanium);

		OreDictionary.registerOre("blockRuby", GTBlocks.rubyBlock);
		OreDictionary.registerOre("blockSapphire", GTBlocks.sapphireBlock);
		OreDictionary.registerOre("blockAluminium", GTBlocks.aluminiumBlock);
		OreDictionary.registerOre("blockAluminum", GTBlocks.aluminiumBlock);
		OreDictionary.registerOre("blockChrome", GTBlocks.chromeBlock);
		OreDictionary.registerOre("blockTitanium", GTBlocks.titaniumBlock);

		OreDictionary.registerOre("itemRubber",
				new ItemStack(GTItems.braintechAerospaceARDT, 1, OreDictionary.WILDCARD_VALUE));

		OreDictionary.registerOre("toolHammer", GTItems.hammerIron);

		OreDictionary.registerOre("itemSilicon", GTItems.plateSilicon);
		OreDictionary.registerOre("plateSilicon", GTItems.plateSilicon);

		OreDictionary.registerOre("plateIridium", Ic2Items.iridiumPlate);

		OreDictionary.registerOre("batteryAdvanced", Ic2Items.energyCrystal);
		OreDictionary.registerOre("batteryAdvanced", GTItems.lithiumBattery);
	}

}
