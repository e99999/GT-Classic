package gtclassic.item.materials;


import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.util.GTItemColorInterface;
import gtclassic.util.GTValues;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GTItemTinyDust extends Item implements IStaticTexturedItem, GTItemColorInterface {
    
    private String material;
	
	public GTItemTinyDust(String material){
        this.material = material;
        setRegistryName(this.material + "_tiny_dust");
        setUnlocalizedName(GTMod.MODID + "." + this.material + "_tiny_dust");
        setCreativeTab(GTMod.creativeTabGT);
    }

    @Override
    public List<Integer> getValidVariants() {
        return Arrays.asList(0);
    }

    @Override
    public TextureAtlasSprite getTexture(int i) {
        return Ic2Icons.getTextures(GTMod.MODID + "_materials")[1];
    }
    
    @Override
	public Color getColor(ItemStack stack, int index) {
		return GTValues.getColor(this.material);
	}
}
