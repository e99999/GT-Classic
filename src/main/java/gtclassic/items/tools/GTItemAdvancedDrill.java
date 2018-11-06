package gtclassic.items.tools;

import gtclassic.GTClassic;
import ic2.core.item.tool.electric.ItemElectricToolDrill;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;
import java.util.List;

public class GTItemAdvancedDrill extends ItemElectricToolDrill {
    public GTItemAdvancedDrill(){
        super();
        this.setRegistryName(GTClassic.MODID + "advanced_drill");
        this.setUnlocalizedName(GTClassic.MODID + ".advancedDrill");
        this.attackDamage = 1.0F;
        this.maxCharge = 10000;
        this.transferLimit = 100;
        this.tier = 1;
    }

    public List<Integer> getValidVariants() {
        return Arrays.asList(0);
    }

    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture(int meta) {
        return Ic2Icons.getTextures("i1")[32 + meta];
    }
}
