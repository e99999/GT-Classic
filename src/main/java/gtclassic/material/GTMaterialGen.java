package gtclassic.material;

import java.util.LinkedHashMap;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GTMaterialGen {

	public static LinkedHashMap<String, Item> itemMap = new LinkedHashMap<>();
	public static LinkedHashMap<String, Block> blockMap = new LinkedHashMap<>();

	public static void init() {

		// Add material entries and flags to correct maps
		for (GTMaterial mat : GTMaterial.values()) {
			materialBlockUtil(mat, GTMaterialFlag.CASING);
		}

		for (GTMaterial mat : GTMaterial.values()) {
			materialBlockUtil(mat, GTMaterialFlag.BLOCK);
		}

		for (GTMaterial mat : GTMaterial.values()) {
			materialItemUtil(mat, GTMaterialFlag.PARTICLE);
		}
		for (GTMaterial mat : GTMaterial.values()) {
			materialItemUtil(mat, GTMaterialFlag.CHEMICAL);
		}
		for (GTMaterial mat : GTMaterial.values()) {
			materialItemUtil(mat, GTMaterialFlag.PLASMA);
		}
		for (GTMaterial mat : GTMaterial.values()) {
			materialItemUtil(mat, GTMaterialFlag.SMALLDUST);
		}
		for (GTMaterial mat : GTMaterial.values()) {
			materialItemUtil(mat, GTMaterialFlag.DUST);
		}
		for (GTMaterial mat : GTMaterial.values()) {
			materialItemUtil(mat, GTMaterialFlag.GEM);
		}
		for (GTMaterial mat : GTMaterial.values()) {
			materialItemUtil(mat, GTMaterialFlag.INGOT);
		}
		for (GTMaterial mat : GTMaterial.values()) {
			materialItemUtil(mat, GTMaterialFlag.NUGGET);
		}
		for (GTMaterial mat : GTMaterial.values()) {
			materialItemUtil(mat, GTMaterialFlag.PLATE);
		}
		for (GTMaterial mat : GTMaterial.values()) {
			materialItemUtil(mat, GTMaterialFlag.STICK);
		}

	}

	public static void materialItemUtil(GTMaterial mat, GTMaterialFlag flag) {
		if (mat.hasFlag(flag)) {
			itemMap.put(mat.getName() + "_" + flag.getSuffix(), new GTMaterialItem(mat, flag));
		}
	}

	public static void materialBlockUtil(GTMaterial mat, GTMaterialFlag flag) {
		if (mat.hasFlag(flag)) {
			blockMap.put(mat.getName() + "_" + flag.getSuffix(), new GTMaterialBlock(mat, flag));
		}
	}

	// How to get an itemstack of any material
	public static ItemStack getStack(GTMaterial mat, GTMaterialFlag flag, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + flag.getSuffix()), count, 0);
	}

	// How to get an itemstack of any block material
	public static ItemStack getBlockStack(GTMaterial mat, GTMaterialFlag flag, int count) {
		return new ItemStack(blockMap.get(mat.getName() + "_" + flag.getSuffix()), count, 0);
	}

	// How to get an item for instances that require an item
	public static Item getItem(GTMaterial mat, GTMaterialFlag flag) {
		return itemMap.get(mat.getName() + "_" + flag.getSuffix());
	}

	// How to get block for instances that require a block
	public static Block getBlock(GTMaterial mat, GTMaterialFlag flag) {
		return blockMap.get(mat.getName() + "_" + flag.getSuffix());
	}

	// Instances per flag, most of the mod will references these

	public static ItemStack getCasing(GTMaterial mat, int count) {
		return new ItemStack(blockMap.get(mat.getName() + "_" + GTMaterialFlag.CASING.getSuffix()), count, 0);
	}

	public static ItemStack getMaterialBlock(GTMaterial mat, int count) {
		return new ItemStack(blockMap.get(mat.getName() + "_" + GTMaterialFlag.BLOCK.getSuffix()), count, 0);
	}

	public static ItemStack getParticle(GTMaterial mat, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.PARTICLE.getSuffix()), count, 0);
	}

	public static ItemStack getChemical(GTMaterial mat, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.CHEMICAL.getSuffix()), count, 0);
	}

	public static ItemStack getPlasma(GTMaterial mat, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.PLASMA.getSuffix()), count, 0);
	}

	public static ItemStack getSmallDust(GTMaterial mat, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.SMALLDUST.getSuffix()), count, 0);
	}

	public static ItemStack getDust(GTMaterial mat, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.DUST.getSuffix()), count, 0);
	}

	public static ItemStack getGem(GTMaterial mat, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.GEM.getSuffix()), count, 0);
	}

	public static ItemStack getIngot(GTMaterial mat, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.INGOT.getSuffix()), count, 0);
	}

	public static ItemStack getNugget(GTMaterial mat, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.NUGGET.getSuffix()), count, 0);
	}

	public static ItemStack getPlate(GTMaterial mat, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.PLATE.getSuffix()), count, 0);
	}

	public static ItemStack getStick(GTMaterial mat, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.STICK.getSuffix()), count, 0);
	}

}
