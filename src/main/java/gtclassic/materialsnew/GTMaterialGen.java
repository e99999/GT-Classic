package gtclassic.materialsnew;

import java.util.HashMap;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GTMaterialGen {
	
	public static HashMap<String, Item> itemMap = new HashMap<>();

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

    //How to get an item
    public static ItemStack getStack(GTMaterial mat, GTMaterialFlag flag, int count) {
        return new ItemStack(itemMap.get(mat.getName() + "_" + flag.getSuffix()), 1, count);
    }
    
    //Utility method for generateItems()
    public static void materialItemUtil(GTMaterial mat, GTMaterialFlag flag) {
    	if (mat.hasFlag(flag)) {
            itemMap.put(mat.getName() + "_" + flag.getSuffix(), new GTMaterialItem(mat, flag));
        }
    }

}
