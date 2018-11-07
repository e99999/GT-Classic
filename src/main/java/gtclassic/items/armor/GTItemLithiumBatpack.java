package gtclassic.items.armor;

import gtclassic.GTClassic;
import ic2.api.classic.item.IDamagelessElectricItem;
import ic2.api.item.ElectricItem;
import ic2.core.item.armor.base.ItemIC2AdvArmorBase;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemLithiumBatpack extends ItemIC2AdvArmorBase implements IDamagelessElectricItem {
    String texture;
    int maxEnergy;
    int tier;
    int transferlimit;
    EnumRarity rare;

    public GTItemLithiumBatpack() {
        super(0, EntityEquipmentSlot.CHEST);
        this.setMaxDamage(0);
        this.texture = "gtclassic:textures/models/armor/lithiumbatpack";
        this.maxEnergy = 600000;
        this.setRegistryName("lithium_batpack");
        this.setUnlocalizedName(GTClassic.MODID + ".lithiumBatpack");
        this.setCreativeTab(GTClassic.creativeTabGT);
        this.tier = 1;
        this.transferlimit = 200;
        this.rare = EnumRarity.COMMON;
    }

    //might use this above later to make registering packs easier.
    //int index, int maxDamage, String tex, int max, String reg, String unl, int lvl, int limit


    public GTItemLithiumBatpack setRarity(EnumRarity newValue) {
        this.rare = newValue;
        return this;
    }

    public String getTexture() {
        return this.texture;
    }

    public ItemStack getRepairItem() {
        return ItemStack.EMPTY;
    }

    public boolean canProvideEnergy(ItemStack stack) {
        return true;
    }

    public double getMaxCharge(ItemStack stack) {
        return (double)this.maxEnergy;
    }

    public int getTier(ItemStack stack) {
        return this.tier;
    }

    public double getTransferLimit(ItemStack stack) {
        return (double)this.transferlimit;
    }

    public EnumRarity func_77613_e(ItemStack stack) {
        return this.rare;
    }

    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (this.isInCreativeTab(tab)) {
            ItemStack empty = new ItemStack(this);
            ItemStack full = new ItemStack(this);
            ElectricItem.manager.discharge(empty, 2.147483647E9D, 2147483647, true, false, false);
            ElectricItem.manager.charge(full, 2.147483647E9D, 2147483647, true, false);
            subItems.add(empty);
            subItems.add(full);
        }
    }

    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    public double getDurabilityForDisplay(ItemStack stack) {
        return 1.0D - ElectricItem.manager.getCharge(stack) / this.getMaxCharge(stack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture(int meta){
        return Ic2Icons.getTextures("gtclassic_items")[58];
    }
}
