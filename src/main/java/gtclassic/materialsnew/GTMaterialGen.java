package gtclassic.materialsnew;

import java.util.LinkedHashMap;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GTMaterialGen {

	public static LinkedHashMap<String, Item> itemMap = new LinkedHashMap<>();

	public static void generateMaterials() {

		// Expand for all flags etc
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

	// How to get an itemstack of any material
	public static ItemStack getStack(GTMaterial mat, GTMaterialFlag flag, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + flag.getSuffix()), count, 0);
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

	// How to get an item (mostly for weird cases that require an item instance
	// specifically)
	public static Item getItem(GTMaterial mat, GTMaterialFlag flag) {
		return itemMap.get(mat.getName() + "_" + flag.getSuffix());
	}

	// Utility method for generateItems()
	public static void materialItemUtil(GTMaterial mat, GTMaterialFlag flag) {
		if (mat.hasFlag(flag)) {
			itemMap.put(mat.getName() + "_" + flag.getSuffix(), new GTMaterialItem(mat, flag));
		}
	}

}
