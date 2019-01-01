package gtclassic.util;

import gtclassic.GTItems;
import net.minecraft.item.Item;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetMetadata;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GTLootHandler {

	@SubscribeEvent
	public void onLootTableLoad(LootTableLoadEvent event) {

		LootFunction[] funcs = new LootFunction[] { new SetMetadata(new LootCondition[0], new RandomValueRange(0, 3)) };

		// TODO make a config option to disable this stuff

		String stringRuby = "gtclassic:ruby_gem";
		String stringSapphire = "gtclassic:sapphire_gem";

		Item itemRuby = GTItems.ruby;
		Item itemSapphire = GTItems.sapphire;

		if (event.getName().equals(LootTableList.CHESTS_SIMPLE_DUNGEON)) {
			event.getTable().getPool("main")
					.addEntry(new LootEntryItem(itemRuby, 20, 0, funcs, new LootCondition[0], stringRuby));
			event.getTable().getPool("main")
					.addEntry(new LootEntryItem(itemSapphire, 20, 0, funcs, new LootCondition[0], stringSapphire));
		}

		else if (event.getName().equals(LootTableList.CHESTS_NETHER_BRIDGE)) {
			event.getTable().getPool("main")
					.addEntry(new LootEntryItem(itemRuby, 20, 0, funcs, new LootCondition[0], stringRuby));
			event.getTable().getPool("main")
					.addEntry(new LootEntryItem(itemSapphire, 20, 0, funcs, new LootCondition[0], stringSapphire));
		}

		else if (event.getName().equals(LootTableList.CHESTS_STRONGHOLD_CORRIDOR)) {
			event.getTable().getPool("main")
					.addEntry(new LootEntryItem(itemRuby, 20, 0, funcs, new LootCondition[0], stringRuby));
			event.getTable().getPool("main")
					.addEntry(new LootEntryItem(itemSapphire, 20, 0, funcs, new LootCondition[0], stringSapphire));
		}

		else if (event.getName().equals(LootTableList.CHESTS_STRONGHOLD_CROSSING)) {
			event.getTable().getPool("main")
					.addEntry(new LootEntryItem(itemRuby, 20, 0, funcs, new LootCondition[0], stringRuby));
			event.getTable().getPool("main")
					.addEntry(new LootEntryItem(itemSapphire, 20, 0, funcs, new LootCondition[0], stringSapphire));
		}

		else if (event.getName().equals(LootTableList.CHESTS_JUNGLE_TEMPLE)) {
			event.getTable().getPool("main")
					.addEntry(new LootEntryItem(itemRuby, 20, 0, funcs, new LootCondition[0], stringRuby));
			event.getTable().getPool("main")
					.addEntry(new LootEntryItem(itemSapphire, 20, 0, funcs, new LootCondition[0], stringSapphire));
		}

		else if (event.getName().equals(LootTableList.CHESTS_DESERT_PYRAMID)) {
			event.getTable().getPool("main")
					.addEntry(new LootEntryItem(itemRuby, 20, 0, funcs, new LootCondition[0], stringRuby));
			event.getTable().getPool("main")
					.addEntry(new LootEntryItem(itemSapphire, 20, 0, funcs, new LootCondition[0], stringSapphire));
		}

		else if (event.getName().equals(LootTableList.CHESTS_VILLAGE_BLACKSMITH)) {
			event.getTable().getPool("main")
					.addEntry(new LootEntryItem(itemRuby, 20, 0, funcs, new LootCondition[0], stringRuby));
			event.getTable().getPool("main")
					.addEntry(new LootEntryItem(itemSapphire, 20, 0, funcs, new LootCondition[0], stringSapphire));
		}

	}

}
