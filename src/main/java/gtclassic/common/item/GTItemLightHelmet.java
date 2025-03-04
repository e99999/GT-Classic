package gtclassic.common.item;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.common.GTBlocks;
import ic2.api.classic.item.IDamagelessElectricItem;
import ic2.api.item.ElectricItem;
import ic2.core.IC2;
import ic2.core.block.generator.tile.TileEntitySolarPanel;
import ic2.core.item.armor.base.ItemIC2AdvArmorBase;
import ic2.core.item.manager.ElectricItemManager;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemLightHelmet extends ItemIC2AdvArmorBase implements IDamagelessElectricItem {

	public GTItemLightHelmet() {
		super(-1, EntityEquipmentSlot.HEAD);
		this.setRegistryName("light_helmet");
		this.setTranslationKey(GTMod.MODID + "." + "light_helmet");
		this.setCreativeTab(GTMod.creativeTabGT);
		setMaxDamage(0);
	}

	@Override
	public int getRGBDurabilityForDisplay(ItemStack stack) {
		return Color.CYAN.hashCode();
	}

	@Override
	public String getTexture() {
		return "ic2:textures/models/armor/solar";
	}

	@Override
	public ItemStack getRepairItem() {
		return ItemStack.EMPTY;
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getTexture(int meta) {
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[23];
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (!isInCreativeTab(tab)) {
			return;
		}
		ItemStack empty = new ItemStack(this, 1, 0);
		ItemStack full = new ItemStack(this, 1, 0);
		ElectricItem.manager.discharge(empty, 2.147483647E9D, Integer.MAX_VALUE, true, false, false);
		ElectricItem.manager.charge(full, 2.147483647E9D, Integer.MAX_VALUE, true, false);
		items.add(empty);
		items.add(full);
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return 1.0D - ElectricItem.manager.getCharge(stack) / getMaxCharge(stack);
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		if (IC2.platform.isRendering()) {
			return;
		}
		if (TileEntitySolarPanel.isSunVisible(world, player.getPosition())) {
			if (ElectricItem.manager.getCharge(stack) == ElectricItem.manager.getMaxCharge(stack)) {
				ElectricItemManager.chargeArmor(player, 1);
			} else {
				ElectricItem.manager.charge(stack, 1.0D, 1, false, false);
			}
		}
		doLightHelmetThings(world, player, stack);
	}

	/** Logic for light helmet block placing with an electric item **/
	public void doLightHelmetThings(World world, EntityPlayer player, ItemStack stack) {
		if (world.getTotalWorldTime() % 5 == 0) {
			BlockPos playerPos = player.getPosition();
			boolean canPlaceLightBlock = world.isAirBlock(playerPos) && world.getLightBrightness(playerPos) < 7.0F;
			boolean isLightPresent = world.getBlockState(playerPos) == GTBlocks.lightSource.getDefaultState();
			if (canPlaceLightBlock && !isLightPresent && ElectricItem.manager.getCharge(stack) >= 1.0D) {
				world.setBlockState(player.getPosition(), GTBlocks.lightSource.getDefaultState());
				ElectricItem.manager.use(stack, 10.0D, player);
			}
		}
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	public boolean canProvideEnergy(ItemStack paramItemStack) {
		return false;
	}

	@Override
	public double getMaxCharge(ItemStack paramItemStack) {
		return 10000;
	}

	@Override
	public int getTier(ItemStack paramItemStack) {
		return 1;
	}

	@Override
	public double getTransferLimit(ItemStack paramItemStack) {
		return 100;
	}
}
