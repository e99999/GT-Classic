package gtclassic.common.item;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.api.interfaces.IGTColorItem;
import gtclassic.api.item.GTItemBaseToggleItem;
import gtclassic.common.GTSounds;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class GTItemCloakingDevice extends GTItemBaseToggleItem implements IStaticTexturedItem, IGTColorItem {

	public GTItemCloakingDevice() {
		super(4, 10000000, 1000);
		this.setRegistryName("cloaking_device");
		this.setTranslationKey(GTMod.MODID + "." + "cloaking_device");
		this.setCreativeTab(GTMod.creativeTabGT);
		this.toggleSound = GTSounds.CLOAK;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format(this.getTranslationKey().replace("item", "tooltip")));
	}

	@Override
	public boolean onItemActive(ItemStack stack, World worldIn, Entity entityIn, int slot, boolean selected) {
		if (entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityIn;
			player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 5, 0, false, false));
			player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 18, 0, false, false));
		}
		return true;
	}

	@Override
	public TextureAtlasSprite getTexture(int meta) {
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[12];
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	public Color getColor(ItemStack stack, int index) {
		NBTTagCompound nbt = StackUtil.getOrCreateNbtData(stack);
		if (nbt.getBoolean(ACTIVE)) {
			return Color.gray;
		}
		return Color.white;
	}
}
