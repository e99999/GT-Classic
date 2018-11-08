package gtclassic.items.eustorage;

import gtclassic.GTClassic;
import ic2.core.item.base.ItemBatteryBase;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemLapotronicEnergyOrb extends ItemBatteryBase {
    public GTItemLapotronicEnergyOrb() {
        super(0);
        this.setRightClick();
        this.setRegistryName("lapotronic_energy_orb");
        this.setUnlocalizedName(GTClassic.MODID + ".lapotronicEnergyOrb");
        this.maxCharge = 10000000;
        this.transferLimit = 1000;
        this.tier = 4;
        this.provider = true;
        this.setCreativeTab(GTClassic.creativeTabGT);
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }

    @Override
    public boolean isDamaged(ItemStack stack) {
        return true;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    @Override
    public boolean wantsToPlay(ItemStack stack) {
        return true;
    }

    @Override
    public ResourceLocation createSound(ItemStack stack) {
        return Ic2Sounds.batteryUse;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture(ItemStack item) {
        return Ic2Icons.getTextures("gtclassic_items")[37];
    }
}
