package gtclassic;

import gtclassic.block.GTBlockTileStorage;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.tool.GTToolFile;
import gtclassic.tool.GTToolHammer;
import gtclassic.tool.GTToolWrench;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.block.Block;
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
			if (item instanceof GTToolWrench) {
				registerWrench(item);
			}
		}

		for (Block block : Block.REGISTRY) {
			if (block instanceof GTBlockTileStorage) {
				GTBlockTileStorage tile = (GTBlockTileStorage) block;
				if (tile.getType() == 0) {
					String name = "chest" + tile.getMaterial().getDisplayName();
					OreDictionary.registerOre(name, new ItemStack(block));
				}
			}
		}

		// Register ores... with ore dict
		OreDictionary.registerOre("oreGalena", GTBlocks.galenaOre);
		OreDictionary.registerOre("oreIridium", GTBlocks.iridiumOre);
		OreDictionary.registerOre("oreRuby", GTBlocks.rubyOre);
		OreDictionary.registerOre("oreSapphire", GTBlocks.sapphireOre);
		OreDictionary.registerOre("oreBauxite", GTBlocks.bauxiteOre);
		OreDictionary.registerOre("oreCalcite", GTBlocks.calciteOre);
		OreDictionary.registerOre("dustMagnetite", GTBlocks.magnetiteSand);
		OreDictionary.registerOre("oreTantalite", GTBlocks.tantaliteOre);
		OreDictionary.registerOre("orePyrite", GTBlocks.pyriteOre);
		OreDictionary.registerOre("oreCinnabar", GTBlocks.cinnabarOre);
		OreDictionary.registerOre("oreSphalerite", GTBlocks.sphaleriteOre);
		OreDictionary.registerOre("oreTungstate", GTBlocks.tungstateOre);
		OreDictionary.registerOre("oreSheldonite", GTBlocks.sheldoniteOre);
		OreDictionary.registerOre("oreOlivine", GTBlocks.olivineOre);
		OreDictionary.registerOre("oreSodalite", GTBlocks.sodaliteOre);

		// Just doing these to make iteration & unification easier in some cases
		OreDictionary.registerOre("dustGunpowder", Items.GUNPOWDER);
		OreDictionary.registerOre("bookshelf", Blocks.BOOKSHELF);

		// Register some missing Ic2c stuff
		OreDictionary.registerOre("dustNetherrack", Ic2Items.netherrackDust);
		OreDictionary.registerOre("dustObsidian", Ic2Items.obsidianDust);
		OreDictionary.registerOre("plateIridium", Ic2Items.iridiumPlate);
		OreDictionary.registerOre("blockCharcoal", Ic2Items.charcoalBlock);

		// Registering Aluminium for idiots
		GTMaterial aluminium = GTMaterial.Aluminium;
		OreDictionary.registerOre("casingMachineAluminum", (GTMaterialGen.getCasing(aluminium, 1)));
		OreDictionary.registerOre("blockAluminum", (GTMaterialGen.getMaterialBlock(aluminium, 1)));
		OreDictionary.registerOre("dustSmallAluminum", (GTMaterialGen.getSmallDust(aluminium, 1)));
		OreDictionary.registerOre("dustAluminum", (GTMaterialGen.getDust(aluminium, 1)));
		OreDictionary.registerOre("ingotAluminum", (GTMaterialGen.getIngot(aluminium, 1)));
		OreDictionary.registerOre("nuggetAluminum", (GTMaterialGen.getNugget(aluminium, 1)));
		OreDictionary.registerOre("plateAluminum", (GTMaterialGen.getPlate(aluminium, 1)));
		OreDictionary.registerOre("stickAluminum", (GTMaterialGen.getStick(aluminium, 1)));
		OreDictionary.registerOre("rodAluminum", (GTMaterialGen.getStick(aluminium, 1)));

		// Stuff to fit with how other mods have done it
		OreDictionary.registerOre("dustAsh", (GTMaterialGen.getDust(GTMaterial.Ashes, 1)));

	}

	public static void registerFile(Item tool) {
		OreDictionary.registerOre("craftingToolFile", new ItemStack(tool, 1, OreDictionary.WILDCARD_VALUE));
	}

	public static void registerHammer(Item tool) {
		OreDictionary.registerOre("craftingToolForgeHammer", new ItemStack(tool, 1, OreDictionary.WILDCARD_VALUE));
	}

	public static void registerWrench(Item tool) {
		OreDictionary.registerOre("craftingToolWrench", new ItemStack(tool, 1, OreDictionary.WILDCARD_VALUE));
	}

}
