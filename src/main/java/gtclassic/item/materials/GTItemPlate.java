package gtclassic.item.materials;

import gtclassic.GTMod;
import gtclassic.util.GTItemColorInterface;
import gtclassic.util.GTValues;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

public class GTItemPlate extends Item implements IStaticTexturedItem, GTItemColorInterface {
    
	private String material;
	
	public GTItemPlate(String material){
		this.material = material;
        setRegistryName(this.material + "_plate");
        setUnlocalizedName(GTMod.MODID + "." + this.material + "_plate");
        setCreativeTab(GTMod.creativeTabGT);
    }

    @Override
    public List<Integer> getValidVariants() {
        return Arrays.asList(0);
    }

    @Override
    public TextureAtlasSprite getTexture(int i) {
        return Ic2Icons.getTextures(GTMod.MODID + "_materials")[5];
    }
    
    @Override
	public Color getColor(ItemStack stack, int index) {
		return GTValues.getColor(this.material);
	}
}

