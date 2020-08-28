package gtclassic.common.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gtclassic.api.material.GTMaterial;
import gtclassic.api.material.GTMaterialFlag;
import gtclassic.api.material.GTMaterialGen;
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

	private static final HashMap<ResourceLocation, String> LOOT_TABLE = new HashMap<>();
	private static final List<ItemStack> LOOT_POOL = new ArrayList<>();
	private static final List<GTMaterial> LOOT_BLACKLIST = new ArrayList<>();
	private static final LootCondition[] NO_CONDITIONS = new LootCondition[0];
	private static final ResourceLocation[] VANILLA_LOOTTABLES = { LootTableList.CHESTS_ABANDONED_MINESHAFT,
			LootTableList.CHESTS_DESERT_PYRAMID, LootTableList.CHESTS_END_CITY_TREASURE,
			LootTableList.CHESTS_IGLOO_CHEST, LootTableList.CHESTS_JUNGLE_TEMPLE, LootTableList.CHESTS_NETHER_BRIDGE,
			LootTableList.CHESTS_SIMPLE_DUNGEON, LootTableList.CHESTS_STRONGHOLD_CORRIDOR,
			LootTableList.CHESTS_STRONGHOLD_CROSSING, LootTableList.CHESTS_STRONGHOLD_LIBRARY,
			LootTableList.CHESTS_VILLAGE_BLACKSMITH, LootTableList.CHESTS_WOODLAND_MANSION };

	public static void init() {
		/* Iterates the above array of vanilla resource locations */
		for (ResourceLocation loc : VANILLA_LOOTTABLES) {
			addTable(loc, "main");
		}
		/* Blacklisting some materials from loot */
		addMaterialToLootBlacklist(GTMaterial.Technetium);
		addMaterialToLootBlacklist(GTMaterial.Plutonium);
		addMaterialToLootBlacklist(GTMaterial.Uranium);
		/* Iterates the material list and SHOTGUNS it all into the lootpool */
		for (GTMaterial mat : GTMaterial.values()) {
			if (mat.getTier() > 4 || LOOT_BLACKLIST.contains(mat)) {
				continue;
			}
			if (mat.hasFlag(GTMaterialFlag.DUST)) {
				LOOT_POOL.add(GTMaterialGen.getDust(mat, 1));
			}
			if (mat.hasFlag(GTMaterialFlag.INGOT)) {
				LOOT_POOL.add(GTMaterialGen.getIngot(mat, 1));
			}
			if (GTMaterial.isGem(mat)) {
				LOOT_POOL.add(GTMaterialGen.getGem(mat, 1));
			}
		}
	}

	@SubscribeEvent
	public void onLootTableLoad(LootTableLoadEvent event) {
		/*
		 * Iterates both the stack array and resource location array to create a 2d
		 * table, at this point Tforest tables have been loaded
		 */
		for (ItemStack item : LOOT_POOL) {
			for (Map.Entry<ResourceLocation, String> entry : LOOT_TABLE.entrySet()) {
				if (event.getName().equals(entry.getKey())) {
					event.getTable().getPool(entry.getValue()).addEntry(new LootEntryItem(item.getItem(), 16, 0, new LootFunction[] {
							createCountFunction(1, 6) }, NO_CONDITIONS, getStackResourceName(item)));
				}
			}
		}
	}

	/*
	 * Utility method needed for getting the correct resource name
	 */
	private static String getStackResourceName(ItemStack item) {
		return item.getItem().getRegistryName().toString();
	}

	/*
	 * Utility method needed for creating loot count
	 */
	private static SetCount createCountFunction(float min, float max) {
		return new SetCount(NO_CONDITIONS, new RandomValueRange(min, max));
	}

	/**
	 * Add table for GTC loot to generate in. Can be called any time before server
	 * start.
	 * 
	 * @param table - The ResourceLocation of the loot table
	 * @param pool  - the pool to get inside the table
	 */
	public static void addTable(ResourceLocation table, String pool) {
		LOOT_TABLE.put(table, pool);
	}

	/**
	 * Blacklist a material from being added to the loot pool, must be called before
	 * PostInit.
	 * 
	 * @param mat - the GTmaterial to blacklist from loot.
	 */
	public static void addMaterialToLootBlacklist(GTMaterial mat) {
		LOOT_BLACKLIST.add(mat);
	}
}
