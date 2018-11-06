package gtclassic.items.tools;

import gtclassic.GTClassic;
import gtclassic.GTMaterials;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemPickaxe;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemHammerIron extends ItemPickaxe {
	
	public GTItemHammerIron() {
		super(GTMaterials.IRON);
		this.setMaxDamage(500);
		setRegistryName("iron_hammer");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName(GTClassic.MODID + ".hammerIron");     // Used for localization (en_US.lang)
        setCreativeTab(GTClassic.creativeTabGT);
    }
	
	//init texture
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

}
