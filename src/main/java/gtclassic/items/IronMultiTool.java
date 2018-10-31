package gtclassic.items;

import gtclassic.GTMod;
import gtclassic.Materials;
import gtclassic.ModItems;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemSword;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class IronMultiTool extends ItemSword {
	
	public IronMultiTool() {
		super(Materials.IRON);
		setRegistryName("ironmultitool");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName(GTMod.MODID + ".ironmultitool");     // Used for localization (en_US.lang)
        setCreativeTab(ModItems.tabGTClassic);
    }
	
	//init texture
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

}
