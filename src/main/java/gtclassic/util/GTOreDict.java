package gtclassic.util;

import net.minecraftforge.oredict.OreDictionary;

public class GTOreDict {
	
	public static void init () {
		OreDictionary.registerOre("dustRuby", GTItems.dustRuby);
        OreDictionary.registerOre("gemRuby", GTItems.ruby);
        OreDictionary.registerOre("blockRuby", GTBlocks.rubyBlock);
        OreDictionary.registerOre("oreRuby", GTBlocks.rubyOre);
        
        OreDictionary.registerOre("dustSapphire", GTItems.dustSapphire);
        OreDictionary.registerOre("gemSapphire", GTItems.sapphire);
        OreDictionary.registerOre("blockSapphire", GTBlocks.sapphireBlock);
        OreDictionary.registerOre("oreSapphire", GTBlocks.sapphireOre);
	}

}
