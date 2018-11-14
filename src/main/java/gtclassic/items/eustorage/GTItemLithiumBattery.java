package gtclassic.items.eustorage;

import gtclassic.GTClassic;
import ic2.api.item.ElectricItem;
import ic2.core.item.base.ItemBatteryBase;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IAdvancedTexturedItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemLithiumBattery extends ItemBatteryBase implements IAdvancedTexturedItem {
    private ModelResourceLocation[] locations = new ModelResourceLocation[5];

    public GTItemLithiumBattery() {
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

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return this.shouldBeStackable(stack) ? 16 : 1;
    }

    @Override
    public boolean isDamaged(ItemStack stack) {
        return !this.shouldBeStackable(stack);
    }

    private boolean shouldBeStackable(ItemStack stack) {
        return !stack.hasTagCompound() || ElectricItem.manager.getCharge(stack) == 0.0D;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return this.shouldBeStackable(stack) ? false : super.showDurabilityBar(stack);
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
        if (ElectricItem.manager.getCharge(item) == 0.0D) {
            return Ic2Icons.getTextures("gtclassic_items")[56];
        }else{
            return Ic2Icons.getTextures("gtclassic_items")[57];
        }
    }
//    @Override
//    @SideOnly(Side.CLIENT)
//    public ModelResourceLocation createResourceLocationForStack(ItemStack stack) {
//        int damage = stack.getItemDamage();
//        ResourceLocation location = this.getRegistryName();
//        String name = stack.getTranslationKey();
//        this.locations[damage] = new ModelResourceLocation(location.getNamespace() + name.substring(name.indexOf(".") + 1) + damage, "inventory");
//        return this.locations[damage];
//    }
//
//    @SideOnly(Side.CLIENT)
//    public ModelResourceLocation getResourceLocationFromStack(ItemStack stack){
//        int damage = stack.getItemDamage();
//        if (ElectricItem.manager.getCharge(stack) == 0.0D) {
//            return this.locations[4];
//        } else if (damage <= 1) {
//            return this.locations[0];
//        } else if (damage <= 8) {
//            return this.locations[1];
//        } else if (damage <= 14) {
//            return this.locations[2];
//        } else {
//            return damage <= 20 ? this.locations[3] : this.locations[4];
//        }
//    }
}
