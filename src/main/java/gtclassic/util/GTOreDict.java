package gtclassic.util;

import gtclassic.GTBlocks;
import gtclassic.GTItems;
import gtclassic.GTMod;
import gtclassic.item.materials.GTItemDust;
import gtclassic.item.materials.GTItemDustSmall;
import gtclassic.item.materials.GTItemIngot;
import gtclassic.item.materials.GTItemNugget;
import gtclassic.item.materials.GTItemPlate;
import gtclassic.item.materials.GTItemStick;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.item.Item;
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

		registerMaterials();

		// TODO bug speiger about this ore dict

		OreDictionary.registerOre("dustNetherrack", Ic2Items.netherrackDust);
		OreDictionary.registerOre("dustObsidian", Ic2Items.obsidianDust);

		OreDictionary.registerOre("oreRuby", GTBlocks.rubyOre);
		OreDictionary.registerOre("oreSapphire", GTBlocks.sapphireOre);
		OreDictionary.registerOre("oreIridium", GTBlocks.iridiumOre);
		OreDictionary.registerOre("oreBauxite", GTBlocks.bauxiteOre);
		OreDictionary.registerOre("oreMagnetite", GTBlocks.sandIron);

		OreDictionary.registerOre("gemRuby", GTItems.gemRuby);
		OreDictionary.registerOre("gemSapphire", GTItems.gemSapphire);

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

		OreDictionary.registerOre("itemSilicon", GTItems.plateSilicon);

		OreDictionary.registerOre("plateIridium", Ic2Items.iridiumPlate);

		OreDictionary.registerOre("batteryAdvanced", Ic2Items.energyCrystal);
		OreDictionary.registerOre("batteryAdvanced", GTItems.smallLithium);

		// TODO find a better way to register plasma, mainly for recipes

		OreDictionary.registerOre("itemPlasma", GTItems.plasmaHelium);
		// OreDictionary.registerOre("itemPlasma", Ic2Items.plasmaCell);

	}

	public static String convertUnlocal(String replace, Item item) {
		return replace + item.getUnlocalizedName().replace("_" + replace, "").replace("item.gtclassic.", "");
	}

	public static void registerMaterials() {
		for (Item item : GTItems.items) {
			if (item instanceof GTItemDust) {
				if (GTValues.debugMode) {
					GTMod.logger.info("Registering with ore dict: " + convertUnlocal("dust", item));
				}
				OreDictionary.registerOre(convertUnlocal("dust", item), item);
			}
			if (item instanceof GTItemDustSmall) {
				if (GTValues.debugMode) {
					GTMod.logger.info("Registering with ore dict: " + convertUnlocal("dustSmall", item));
				}
				OreDictionary.registerOre(convertUnlocal("dustSmall", item), item);
			}
			if (item instanceof GTItemIngot) {
				if (GTValues.debugMode) {
					GTMod.logger.info("Registering with ore dict: " + convertUnlocal("ingot", item));
				}
				OreDictionary.registerOre(convertUnlocal("ingot", item), item);
			}
			if (item instanceof GTItemNugget) {
				if (GTValues.debugMode) {
					GTMod.logger.info("Registering with ore dict: " + convertUnlocal("nugget", item));
				}
				OreDictionary.registerOre(convertUnlocal("nugget", item), item);
			}
			if (item instanceof GTItemPlate) {
				if (GTValues.debugMode) {
					GTMod.logger.info("Registering with ore dict: " + convertUnlocal("plate", item));
				}
				OreDictionary.registerOre(convertUnlocal("plate", item), item);
			}
			if (item instanceof GTItemStick) {
				if (GTValues.debugMode) {
					GTMod.logger.info("Registering with ore dict: " + convertUnlocal("stick", item));
				}
				OreDictionary.registerOre(convertUnlocal("stick", item), item);
			}
		}
	}

}
