package gtclassic.item.energy;

import java.util.List;
import java.util.Map;

import gtclassic.GTClassic;
import gtclassic.util.GTLang;
import gtclassic.util.GTValues;
import ic2.api.item.ElectricItem;
import ic2.core.item.base.ItemBatteryBase;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.util.obj.ToolTipType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemZeroPointModule extends ItemBatteryBase {

	public GTItemZeroPointModule() {
		super(0);
		this.setRightClick();
		this.setRegistryName("zero_point_module");
		this.setUnlocalizedName(GTClassic.MODID + ".zeroPointModule");
		this.setCreativeTab(GTClassic.creativeTabGT);
		this.maxCharge = Integer.MAX_VALUE;
		this.transferLimit = 13107;
		this.tier = 6;
		this.provider = true;
		this.setCreativeTab(GTClassic.creativeTabGT);
	}

	@Override
	public int getTier(ItemStack stack) {
		if (GTValues.debugMode) {
			return 1;
		} else {
			return 6;
		}
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
		return false;
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
		return Ic2Icons.getTextures("gtclassic_items")[78];
	}

	@Override
	public EnumRarity getRarity(ItemStack thisItem) {
		return EnumRarity.EPIC;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onSortedItemToolTip(ItemStack stack, EntityPlayer player, boolean debugTooltip, List<String> tooltip,
			Map<ToolTipType, List<String>> sortedTooltip) {
		List<String> ctrlList = sortedTooltip.get(ToolTipType.Ctrl);
		tooltip.add(TextFormatting.RED + I18n.format("tooltip." + GTClassic.MODID + ".wip"));
		// TODO make zpm not recharge ctrlList.add(TextFormatting.RED +
		// GTLang.zpm4.getLocalized());
		ctrlList.add(TextFormatting.GOLD + GTLang.zpm1.getLocalized());
		ctrlList.add(TextFormatting.GOLD + GTLang.zpm2.getLocalized());
		ctrlList.add(TextFormatting.ITALIC + GTLang.zpm3.getLocalized());
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			ItemStack full = new ItemStack(this, 1, 0);
			ElectricItem.manager.charge(full, 2.147483647E9D, Integer.MAX_VALUE, true, false);
			items.add(full);
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		return ActionResult.newResult(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
	}

}
