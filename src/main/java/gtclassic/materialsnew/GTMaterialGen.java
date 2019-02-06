package gtclassic.materialsnew;

import java.util.HashMap;
import java.util.LinkedHashMap;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GTMaterialGen {
	
	public static LinkedHashMap<String, Item> itemMap = new LinkedHashMap<>();

    public static void generateMaterials() {
        for (GTMaterial mat : GTMaterial.values()) {
        	materialItemUtil(mat, GTMaterialFlag.SMALLDUST);
        	materialItemUtil(mat, GTMaterialFlag.DUST);
        	materialItemUtil(mat, GTMaterialFlag.GEM);
        	materialItemUtil(mat, GTMaterialFlag.INGOT);
        	materialItemUtil(mat, GTMaterialFlag.NUGGET);
        	materialItemUtil(mat, GTMaterialFlag.PLATE);
        	materialItemUtil(mat, GTMaterialFlag.STICK);
            //Expand for all flags etc
        }
    }

    //How to get an itemstack of any material
    public static ItemStack getStack(GTMaterial mat, GTMaterialFlag flag, int count) {
        return new ItemStack(itemMap.get(mat.getName() + "_" + flag.getSuffix()), 1, count);
    }
    
    public static ItemStack getSmallDust(GTMaterial mat, int count) {
        return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.SMALLDUST.getSuffix()), 1, count);
    }
    
    public static ItemStack getDust(GTMaterial mat, int count) {
        return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.DUST.getSuffix()), 1, count);
    }
    
    public static ItemStack getGem(GTMaterial mat, int count) {
        return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.GEM.getSuffix()), 1, count);
    }
    
    //How to get an item (mostly for weird cases that require an item instance specifically)
    public static Item getItem(GTMaterial mat, GTMaterialFlag flag) {
        return itemMap.get(mat.getName() + "_" + flag.getSuffix());
    }
    
    
    
    //Utility method for generateItems()
    public static void materialItemUtil(GTMaterial mat, GTMaterialFlag flag) {
    	if (mat.hasFlag(flag)) {
            itemMap.put(mat.getName() + "_" + flag.getSuffix(), new GTMaterialItem(mat, flag));
        }
    }

}
