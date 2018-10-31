package gtclassic.blocks.ores;

import gtclassic.GTMod;
import gtclassic.ModItems;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class IronSand extends BlockFalling {
	
	public IronSand() {
		super(Material.SAND);
		setUnlocalizedName(GTMod.MODID + ".ironsand");
        setRegistryName("ironsand");
        setCreativeTab(ModItems.tabGTClassic);
        setHardness(0.5F);
        setSoundType(SoundType.SAND);
        setHarvestLevel("shovel", 0);
    }
	
	//initializes the block texture as an item texture
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
        
    }

}
