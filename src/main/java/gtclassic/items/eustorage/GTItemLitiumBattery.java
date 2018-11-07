package gtclassic.items.eustorage;

import gtclassic.GTClassic;
import ic2.api.item.ElectricItem;
import ic2.core.item.base.ItemBatteryBase;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemLitiumBattery extends ItemBatteryBase {
    public GTItemLitiumBattery() {
        super(0);
        this.setRightClick();
        this.setRegistryName("lithium_battery");
        this.setUnlocalizedName(GTClassic.MODID + ".lithiumBattery");
        this.maxCharge = 100000;
        this.transferLimit = 150;
        this.tier = 1;
        this.provider = true;
        this.setCreativeTab(GTClassic.creativeTabGT);
    }

    public int getItemStackLimit(ItemStack stack) {
        return this.shouldBeStackable(stack) ? 16 : 1;
    }

    public boolean isDamaged(ItemStack stack) {
        return !this.shouldBeStackable(stack);
    }

    private boolean shouldBeStackable(ItemStack stack) {
        return !stack.hasTagCompound() || ElectricItem.manager.getCharge(stack) == 0.0D;
    }

    public boolean showDurabilityBar(ItemStack stack) {
        return this.shouldBeStackable(stack) ? false : super.showDurabilityBar(stack);
    }

    public boolean wantsToPlay(ItemStack stack) {
        return true;
    }

    public ResourceLocation createSound(ItemStack stack) {
        return Ic2Sounds.batteryUse;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture(ItemStack item) {
        if (ElectricItem.manager.getCharge(item) == 0.0D) {
            return Ic2Icons.getTextures("gtclassic_items")[56];
        }else{
            return Ic2Icons.getTextures("gtclassic_items")[57];
        }
    }
}
