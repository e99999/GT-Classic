package gtclassic.toxicdimension.item;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTClassic;
import gtclassic.util.GTBlocks;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemCreditAlk extends Item implements IStaticTexturedItem {

	public GTItemCreditAlk() {
		this.maxStackSize = 1;
		this.setMaxDamage(64);
		setRegistryName("alk_credit");
		setUnlocalizedName(GTClassic.MODID + ".creditAlk");
		setCreativeTab(GTClassic.creativeTabGT);
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int i) {
		return Ic2Icons.getTextures("gtclassic_items")[79];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public EnumRarity getRarity(ItemStack thisItem) {
		return EnumRarity.EPIC;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.GREEN + I18n.format("tooltip." + GTClassic.MODID + ".alkcredit"));
		tooltip.add(stack.getMaxDamage() - stack.getItemDamage() + "/64");

	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer entity, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		pos = pos.offset(facing);
		ItemStack itemstack = entity.getHeldItem(hand);
		if (!entity.canPlayerEdit(pos, facing, itemstack)) {
			return EnumActionResult.FAIL;
		} else {
			if (world.isAirBlock(pos)) {
				world.playSound(entity, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F,
						itemRand.nextFloat() * 0.4F + 0.8F);
				GTBlocks.toxicPortal.trySpawnPortal(world, pos);
			}
			itemstack.damageItem(1, entity);
			return EnumActionResult.SUCCESS;
		}

	}
}
