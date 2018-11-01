package gtclassic.items;

import gtclassic.ModCore;
import gtclassic.Materials;
import gtclassic.ModItems;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemPickaxe;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHammerIron extends ItemPickaxe {
	
	public ItemHammerIron() {
		super(Materials.IRON);
		this.setMaxDamage(500);
		setRegistryName("iron_hammer");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName(ModCore.MODID + ".hammerIron");     // Used for localization (en_US.lang)
        setCreativeTab(ModItems.tabGTClassic);
    }
	
	//init texture
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

}
