package gtclassic.common.event;

import gtclassic.common.tile.GTTileTypeFilter;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary.OreRegisterEvent;

public class GTEventOreDictRegister {

	private static String[] toSearchFor = { "dust", "dustTiny", "dustSmall", "ingot", "ingotHot", "nugget", "plate",
			"stick", "rod", "gear", "gem", "block", "ore", "crushed", "crushedPurified", "stone", "log", "plank",
			"treeSapling", "treeLeaves", "dye", "record", "crop", "beeComb", "machine", "circuit", "item" };

	@SubscribeEvent
	public void onOreRegistering(OreRegisterEvent event) {
		String entry = event.getName();
		ItemStack stack = event.getOre();
		for (int i = 0; i < toSearchFor.length; ++i) {
			String match = toSearchFor[i];
			if (entry.startsWith(match)) {
				GTTileTypeFilter.addOreDictFilter(match, stack.copy());
			}
		}
	}
}
