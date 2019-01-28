package gtclassic.util;

import static ic2.core.platform.textures.Ic2Icons.addTextureEntry;

import java.util.EnumSet;

import gtclassic.GTBlocks;
import gtclassic.GTItems;
import gtclassic.GTMod;
import gtclassic.block.GTBlockTileBasic;
import gtclassic.item.materials.GTItemDust;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.textures.Sprites;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class GTOreDict {

	public static void init() {

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
		OreDictionary.registerOre("dustTungsten", GTItems.dustTungsten);
		OreDictionary.registerOre("dustPlatinum", GTItems.dustPlatinum);
		

		OreDictionary.registerOre("oreRuby", GTBlocks.rubyOre);
		OreDictionary.registerOre("oreSapphire", GTBlocks.sapphireOre);
		OreDictionary.registerOre("oreIridium", GTBlocks.iridiumOre);
		OreDictionary.registerOre("oreBauxite", GTBlocks.bauxiteOre);
		OreDictionary.registerOre("oreMagnetite", GTBlocks.sandIron);

		OreDictionary.registerOre("gemRuby", GTItems.ruby);
		OreDictionary.registerOre("gemSapphire", GTItems.sapphire);
		OreDictionary.registerOre("ingotAluminium", GTItems.ingotAluminium);
		OreDictionary.registerOre("ingotAluminum", GTItems.ingotAluminium);
		OreDictionary.registerOre("ingotTitanium", GTItems.ingotTitanium);
		OreDictionary.registerOre("ingotTungsten", GTItems.ingotTungsten);
		OreDictionary.registerOre("ingotPlatinum", GTItems.ingotPlatinum);
		OreDictionary.registerOre("ingotChrome", GTItems.ingotChrome);
		OreDictionary.registerOre("ingotIridium", GTItems.ingotIridium);
		
		OreDictionary.registerOre("stickIron", GTItems.stickIron);
		OreDictionary.registerOre("stickAluminium", GTItems.stickAluminium);
		OreDictionary.registerOre("stickChrome", GTItems.stickChrome);
		OreDictionary.registerOre("stickTitanium", GTItems.stickTitanium);
		OreDictionary.registerOre("stickTungsten", GTItems.stickTungsten);
		OreDictionary.registerOre("stickPlatinum", GTItems.stickPlatinum);


		OreDictionary.registerOre("blockIridium", GTBlocks.iridiumBlock);
		OreDictionary.registerOre("blockRuby", GTBlocks.rubyBlock);
		OreDictionary.registerOre("blockSapphire", GTBlocks.sapphireBlock);
		OreDictionary.registerOre("blockAluminium", GTBlocks.aluminiumBlock);
		OreDictionary.registerOre("blockAluminum", GTBlocks.aluminiumBlock);
		OreDictionary.registerOre("blockChrome", GTBlocks.chromeBlock);
		OreDictionary.registerOre("blockTitanium", GTBlocks.titaniumBlock);
		OreDictionary.registerOre("blockTungsten", GTBlocks.tungstenBlock);
		OreDictionary.registerOre("blockPlatinum", GTBlocks.platinumBlock);

		OreDictionary.registerOre("itemRubber",
				new ItemStack(GTItems.braintechAerospaceARDT, 1, OreDictionary.WILDCARD_VALUE));

		OreDictionary.registerOre("craftingToolForgeHammer", GTItems.hammerIron);
		OreDictionary.registerOre("craftingToolFile", GTItems.fileIron);

		OreDictionary.registerOre("itemSilicon", GTItems.plateSilicon);
		OreDictionary.registerOre("plateSilicon", GTItems.plateSilicon);

		OreDictionary.registerOre("plateIridium", Ic2Items.iridiumPlate);

		OreDictionary.registerOre("batteryAdvanced", Ic2Items.energyCrystal);
		OreDictionary.registerOre("batteryAdvanced", GTItems.smallLithium);

		// TODO find a better way to register plasma, mainly for recipes

		OreDictionary.registerOre("itemPlasma", GTItems.plasmaHelium);
		// OreDictionary.registerOre("itemPlasma", Ic2Items.plasmaCell);

	}
	
	public static String formatString(String input) {
		String name = input;
		String output = name.substring(0, 1).toUpperCase() + name.substring(1);
		return output;
	}

}
