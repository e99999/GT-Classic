package gtclassic.common.item;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.api.interfaces.IGTColorItem;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.common.GTItems;
import ic2.core.IC2;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ILayeredItemModel;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemSprayCan extends Item implements IStaticTexturedItem, IGTColorItem, ILayeredItemModel {

	public static final String COLOR = "color";

	public GTItemSprayCan() {
		this.setRegistryName("spray_can");
		this.setTranslationKey(GTMod.MODID + "." + "spray_can");
		this.setCreativeTab(GTMod.creativeTabGT);
		this.setMaxDamage(127);
		this.setMaxStackSize(1);
	}

	@Override
	public int getRGBDurabilityForDisplay(ItemStack stack) {
		NBTTagCompound nbt = StackUtil.getNbtData(stack);
		if (nbt.hasKey(COLOR)) {
			return EnumDyeColor.byDyeDamage(nbt.getInteger(COLOR)).getColorValue();
		}
		return super.getRGBDurabilityForDisplay(stack);
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return false;
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		NBTTagCompound nbt = StackUtil.getNbtData(stack);
		if (nbt.hasKey(COLOR)) {
			String name = EnumDyeColor.byDyeDamage(nbt.getInteger(COLOR)).getDyeColorName();
			tooltip.add(I18n.format("Current: " + name.toUpperCase()));
		} else {
			tooltip.add(I18n.format("Current: NONE"));
		}
		tooltip.add(I18n.format("Sneak + click to change colors"));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (playerIn.isSneaking() && handIn == EnumHand.MAIN_HAND) {
			ItemStack playerStack = playerIn.getHeldItemMainhand();
			NBTTagCompound nbt = StackUtil.getOrCreateNbtData(playerStack);
			if (nbt.hasKey(COLOR)) {
				int i = nbt.getInteger(COLOR);
				if (i + 1 > 15) {
					nbt.setInteger(COLOR, 0);
				} else {
					nbt.setInteger(COLOR, i + 1);
				}
			} else {
				nbt.setInteger(COLOR, 0);
			}
			if (!IC2.platform.isSimulating()) {
				IC2.audioManager.playOnce(playerIn, Ic2Sounds.cutterUse);
			}
			return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
		}
		return ActionResult.newResult(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}

	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer playerIn, World world, BlockPos pos, EnumFacing side,
			float hitX, float hitY, float hitZ, EnumHand handIn) {
		if (!playerIn.isSneaking() && handIn == EnumHand.MAIN_HAND) {
			ItemStack playerStack = playerIn.getHeldItemMainhand();
			NBTTagCompound nbt = StackUtil.getNbtData(playerStack);
			if (nbt.hasKey(COLOR)) {
				EnumDyeColor dye = EnumDyeColor.byDyeDamage(nbt.getInteger(COLOR));
				if (colorBlock(world.getBlockState(pos), world, pos, null, dye)) {
					if (playerStack.getItemDamage() < playerStack.getMaxDamage()) {
						playerStack.damageItem(1, playerIn);
						if (!IC2.platform.isSimulating()) {
							IC2.audioManager.playOnce(playerIn, Ic2Sounds.painterUse);
						}
					} else {
						playerIn.setHeldItem(handIn, GTMaterialGen.get(GTItems.sprayCanEmpty));
						if (!IC2.platform.isSimulating()) {
							playerIn.playSound(SoundEvents.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
						}
					}
					return EnumActionResult.SUCCESS;
				}
			}
		}
		return EnumActionResult.PASS;
	}

	public boolean colorBlock(IBlockState state, World world, BlockPos pos, EnumFacing side, EnumDyeColor color) {
		try {
			return state.getBlock().recolorBlock(world, pos, side, color);
		} catch (Exception var7) {
			return false;
		}
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (!isInCreativeTab(tab)) {
			return;
		}
		ItemStack stack = GTMaterialGen.get(GTItems.sprayCan);
		StackUtil.getOrCreateNbtData(stack).setInteger(COLOR, 15);
		items.add(stack);
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int i) {
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[36];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int i, ItemStack var2) {
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[36 + i];
	}

	@Override
	public Color getColor(ItemStack stack, int index) {
		NBTTagCompound nbt = StackUtil.getNbtData(stack);
		if (index == 1 && nbt.hasKey(COLOR)) {
			return new Color(EnumDyeColor.byDyeDamage(nbt.getInteger(COLOR)).getColorValue());
		}
		return Color.white;
	}

	@Override
	public boolean isLayered(ItemStack stack) {
		return true;
	}

	@Override
	public int getLayers(ItemStack var1) {
		return 2;
	}
}
