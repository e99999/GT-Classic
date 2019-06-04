package gtclassic;

import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraftforge.oredict.OreDictionary;

public class GTOreDict {

	public static void init() {
		// Just doing these to make iteration & unification easier in some cases
		OreDictionary.registerOre("dustGunpowder", Items.GUNPOWDER);
		OreDictionary.registerOre("bookshelf", Blocks.BOOKSHELF);
		// Register some missing Ic2c stuff cause Speiger is a bad doge
		OreDictionary.registerOre("dustNetherrack", Ic2Items.netherrackDust);
		OreDictionary.registerOre("dustObsidian", Ic2Items.obsidianDust);
		OreDictionary.registerOre("plateIridium", Ic2Items.iridiumPlate);
		OreDictionary.registerOre("blockCharcoal", Ic2Items.charcoalBlock);
		OreDictionary.registerOre("logRubber", Ic2Items.rubberWood);
		OreDictionary.registerOre("ingotWroughtIron", Ic2Items.refinedIronIngot);
		OreDictionary.registerOre("dustRareEarth", Ic2Items.rareEarthDust);
		// Registering Aluminium for idiots
		GTMaterial aluminium = GTMaterial.Aluminium;
		OreDictionary.registerOre("blockAluminum", (GTMaterialGen.getMaterialBlock(aluminium, 1)));
		OreDictionary.registerOre("dustAluminum", (GTMaterialGen.getDust(aluminium, 1)));
		OreDictionary.registerOre("ingotAluminum", (GTMaterialGen.getIngot(aluminium, 1)));
		// Stuff to fit with how other mods have done it
		OreDictionary.registerOre("dustEnderEye", (GTMaterialGen.getDust(GTMaterial.EnderEye, 1)));
		OreDictionary.registerOre("itemSilicon", (GTMaterialGen.getIngot(GTMaterial.Silicon, 1)));
		OreDictionary.registerOre("dyeBlue", (GTMaterialGen.getDust(GTMaterial.Lazurite, 1)));
	}
}
