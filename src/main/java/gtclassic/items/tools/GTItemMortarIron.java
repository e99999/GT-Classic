package gtclassic.items.tools;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTClassic;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemMortarIron extends Item implements IStaticTexturedItem {
	
	public GTItemMortarIron() {
		this.maxStackSize = 1;
		this.setMaxDamage(96);
		setRegistryName("iron_mortar");      
        setUnlocalizedName(GTClassic.MODID + ".mortarIron");
        setCreativeTab(GTClassic.creativeTabGT);
    }

	@Override
    public List<Integer> getValidVariants() {
        return Arrays.asList(0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture(int meta) {
        return Ic2Icons.getTextures("gtclassic_items")[30];
    }

}
