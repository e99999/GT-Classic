package gtclassic.common.event;

import java.util.ArrayList;
import java.util.List;

import gtclassic.api.material.GTMaterial;
import gtclassic.api.material.GTMaterialFlag;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.common.GTConfig;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GTEventLootTableLoad {

	private static ResourceLocation[] loottable = { LootTableList.CHESTS_ABANDONED_MINESHAFT,
			LootTableList.CHESTS_DESERT_PYRAMID, LootTableList.CHESTS_END_CITY_TREASURE,
			LootTableList.CHESTS_IGLOO_CHEST, LootTableList.CHESTS_JUNGLE_TEMPLE, LootTableList.CHESTS_NETHER_BRIDGE,
			LootTableList.CHESTS_SIMPLE_DUNGEON, LootTableList.CHESTS_STRONGHOLD_CORRIDOR,
			LootTableList.CHESTS_STRONGHOLD_CROSSING, LootTableList.CHESTS_STRONGHOLD_LIBRARY,
			LootTableList.CHESTS_VILLAGE_BLACKSMITH, LootTableList.CHESTS_WOODLAND_MANSION };
	private static List<ItemStack> lootpool = new ArrayList<>();
	private static final LootCondition[] NO_CONDITIONS = new LootCondition[0];

	public static void init() {
		for (GTMaterial mat : GTMaterial.values()) {
			if (mat.hasFlag(GTMaterialFlag.DUST)) {
				lootpool.add(GTMaterialGen.getDust(mat, 1));
			}
			if (mat.hasFlag(GTMaterialFlag.INGOT)) {
				lootpool.add(GTMaterialGen.getIngot(mat, 1));
			}
			if (GTMaterial.isGem(mat)) {
				lootpool.add(GTMaterialGen.getGem(mat, 1));
			}
		}
	}

	@SubscribeEvent
	public void onLootTableLoad(LootTableLoadEvent event) {
		/*
		 * Iterates both the stack array and resource location array to create a 2d
		 * table
		 */
		if (GTConfig.general.addLootItems) {
			for (ItemStack item : lootpool) {
				for (ResourceLocation table : loottable) {
					if (event.getName().equals(table)) {
						event.getTable().getPool("main").addEntry(new LootEntryItem(item.getItem(), 16, 0, new LootFunction[] {
								createCountFunction(1, 6) }, NO_CONDITIONS, getStackResourceName(item)));
					}
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

	public static SetCount createCountFunction(float min, float max) {
		return new SetCount(NO_CONDITIONS, new RandomValueRange(min, max));
	}
}
