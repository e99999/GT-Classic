package gtclassic.util;

import net.minecraftforge.oredict.OreDictionary;

public class GTOreDict {
	
	public static void init () {
		OreDictionary.registerOre("dustEnderPearl", GTItems.dustEnderpearl);
		OreDictionary.registerOre("dustEnderEye", GTItems.dustEnderEye);
		OreDictionary.registerOre("gemLapis", GTItems.dustLazurite);
	    OreDictionary.registerOre("dustLazurite", GTItems.dustLazurite);
	    OreDictionary.registerOre("dustPyrite", GTItems.dustPyrite);
	    OreDictionary.registerOre("dustCalcite", GTItems.dustCalcite);
	    OreDictionary.registerOre("dustFlint", GTItems.dustFlint);
	    OreDictionary.registerOre("dustUranium", GTItems.dustUranium);
	    OreDictionary.registerOre("dustBauxite", GTItems.dustBauxite);
	    OreDictionary.registerOre("dustAluminum", GTItems.dustAluminum);
	    OreDictionary.registerOre("dustTitanium", GTItems.dustTitanium);
	    OreDictionary.registerOre("dustChrome", GTItems.dustChrome);
		OreDictionary.registerOre("dustRuby", GTItems.dustRuby);
		OreDictionary.registerOre("dustSapphire", GTItems.dustSapphire);
		OreDictionary.registerOre("dustGreenSapphire", GTItems.dustGreenSapphire);
		OreDictionary.registerOre("dustEmerald", GTItems.dustEmerald);
		
        OreDictionary.registerOre("oreRuby", GTBlocks.rubyOre);
        OreDictionary.registerOre("oreSapphire", GTBlocks.sapphireOre);
        OreDictionary.registerOre("oreIridium", GTBlocks.iridiumOre);
        OreDictionary.registerOre("oreBauxite", GTBlocks.bauxiteOre);
        OreDictionary.registerOre("oreMagnetite", GTBlocks.sandIron);
        
        OreDictionary.registerOre("gemRuby", GTItems.ruby);
        OreDictionary.registerOre("gemSapphire", GTItems.sapphire);
        OreDictionary.registerOre("ingotAluminum", GTItems.ingotAluminum);
        OreDictionary.registerOre("ingotChrome", GTItems.ingotChrome);
        OreDictionary.registerOre("ingotIridium", GTItems.ingotIridium);
        OreDictionary.registerOre("ingotTitanium", GTItems.ingotTitanium);
        
        OreDictionary.registerOre("blockRuby", GTBlocks.rubyBlock);
        OreDictionary.registerOre("blockSapphire", GTBlocks.sapphireBlock);
        OreDictionary.registerOre("blockAluminum", GTBlocks.aluminumBlock);
        OreDictionary.registerOre("blockChrome", GTBlocks.chromeBlock);
        OreDictionary.registerOre("blockTitanium", GTBlocks.titaniumBlock);
        
	}

}
