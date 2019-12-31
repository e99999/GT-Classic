package gtclassic.api.world;

import java.util.HashMap;
import java.util.Map;

import gtclassic.api.block.GTBlockBaseOre;
import gtclassic.api.material.GTMaterial;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.common.GTBlocks;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GTBedrockOreHandler {

	private static HashMap<Block, ItemStack> bedrockOreMap = new HashMap<>();

	public static void bedrockOresInit() {
		// vanilla
		addBedrockOre(GTBlocks.oreBedrockGold, Ic2Items.goldDust);
		addBedrockOre(GTBlocks.oreBedrockIron, Ic2Items.ironDust);
		addBedrockOre(GTBlocks.oreBedrockCoal, GTMaterialGen.get(Items.COAL));
		addBedrockOre(GTBlocks.oreBedrockLapis, new ItemStack(Items.DYE, 1, 4));
		addBedrockOre(GTBlocks.oreBedrockDiamond, GTMaterialGen.get(Items.DIAMOND));
		addBedrockOre(GTBlocks.oreBedrockRedstone, GTMaterialGen.get(Items.REDSTONE));
		addBedrockOre(GTBlocks.oreBedrockEmerald, GTMaterialGen.getDust(GTMaterial.Emerald, 1));
		// ic2
		addBedrockOre(GTBlocks.oreBedrockCopper, Ic2Items.copperDust);
		addBedrockOre(GTBlocks.oreBedrockTin, Ic2Items.tinDust);
		addBedrockOre(GTBlocks.oreBedrockUranium, GTMaterialGen.getDust(GTMaterial.Uranium, 1));
		addBedrockOre(GTBlocks.oreBedrockSilver, Ic2Items.silverDust);
		// gtc
		addBedrockOre(GTBlocks.oreBedrockIridium, GTMaterialGen.getDust(GTMaterial.Iridium, 1));
		addBedrockOre(GTBlocks.oreBedrockSheldonite, GTMaterialGen.getDust(GTMaterial.Platinum, 1));
		addBedrockOre(GTBlocks.oreBedrockRuby, GTMaterialGen.getDust(GTMaterial.Ruby, 1));
		addBedrockOre(GTBlocks.oreBedrockSapphire, GTMaterialGen.getDust(GTMaterial.Sapphire, 1));
		addBedrockOre(GTBlocks.oreBedrockBauxite, GTMaterialGen.getDust(GTMaterial.Bauxite, 1));
	}

	public static void addBedrockOre(Block ore, ItemStack output) {
		bedrockOreMap.put(ore, output);
	}

	public static Map<Block, ItemStack> getBedrockOreMap() {
		return bedrockOreMap;
	}

	public static ItemStack getResource(Block ore) {
		return isBedrockOre(ore) ? bedrockOreMap.get(ore).copy() : ItemStack.EMPTY;
	}

	public static boolean isBedrockOre(Block ore) {
		return bedrockOreMap.containsKey(ore);
	}

	public static boolean shouldGTCHandleGeneration(Block block) {
		return block instanceof GTBlockBaseOre;
	}
}
