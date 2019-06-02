package gtclassic.util;

import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GTLootHandler {

	@SubscribeEvent
	public void onLootTableLoad(LootTableLoadEvent event) {

		// ItemStack array of items to add to the loot table
		ItemStack[] lootitems = { GTMaterialGen.getGem(GTMaterial.Ruby, 1),
				GTMaterialGen.getGem(GTMaterial.Sapphire, 1), GTMaterialGen.getGem(GTMaterial.Olivine, 1),
				GTMaterialGen.getIngot(GTMaterial.Germanium, 1), GTMaterialGen.getIngot(GTMaterial.Invar, 1),
				GTMaterialGen.getIngot(GTMaterial.Cobalt, 1), GTMaterialGen.getIngot(GTMaterial.Zinc, 1),
				GTMaterialGen.getIngot(GTMaterial.Steel, 1), GTMaterialGen.getIngot(GTMaterial.BismuthBronze, 1),
				GTMaterialGen.getIngot(GTMaterial.Electrum, 1), GTMaterialGen.getIngot(GTMaterial.Constantan, 1),
				GTMaterialGen.getIngot(GTMaterial.Lead, 1), GTMaterialGen.getIngot(GTMaterial.Brass, 1),
				GTMaterialGen.getIngot(GTMaterial.Nickel, 1), GTMaterialGen.getIngot(GTMaterial.Bismuth, 1),
				GTMaterialGen.getIngot(GTMaterial.StainlessSteel, 1), GTMaterialGen.getIngot(GTMaterial.RedAlloy, 1) };

		// ResourceLocation array of valid loot tables to iterate
		ResourceLocation[] loottable = { LootTableList.CHESTS_ABANDONED_MINESHAFT, LootTableList.CHESTS_DESERT_PYRAMID,
				LootTableList.CHESTS_END_CITY_TREASURE, LootTableList.CHESTS_IGLOO_CHEST,
				LootTableList.CHESTS_JUNGLE_TEMPLE, LootTableList.CHESTS_NETHER_BRIDGE,
				LootTableList.CHESTS_SIMPLE_DUNGEON, LootTableList.CHESTS_STRONGHOLD_CORRIDOR,
				LootTableList.CHESTS_STRONGHOLD_CROSSING, LootTableList.CHESTS_STRONGHOLD_LIBRARY,
				LootTableList.CHESTS_VILLAGE_BLACKSMITH, LootTableList.CHESTS_WOODLAND_MANSION };

		/*
		 * Iterates both the stack array and resource location array to create a 2d
		 * table
		 */
		for (ItemStack item : lootitems) {
			for (ResourceLocation table : loottable) {
				if (event.getName().equals(table)) {
					event.getTable().getPool("main").addEntry(new LootEntryItem(item.getItem(), 16, 0, new LootFunction[] {}, new LootCondition[0], getStackResourceName(item)));
				}
			}
		}
	}

	/*
	 * Utility method needed for getting the correct resource location
	 */
	public static String getStackResourceName(ItemStack item) {
		return item.getItem().getRegistryName().toString();
	}

}
