package gtclassic.common.item;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import gtclassic.GTMod;
import gtclassic.api.fluid.GTFluidItemStackHandler;
import gtclassic.api.helpers.GTHelperFluid;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.common.GTItems;
import gtclassic.common.util.render.GTModelLoader;
import gtclassic.common.util.render.GTModelTestTube;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

public class GTItemFluidTube extends Item {

	private final int size = Fluid.BUCKET_VOLUME;
	private final ItemStack empty = new ItemStack(this);
	public ModelResourceLocation[] model = new ModelResourceLocation[2];

	public GTItemFluidTube() {
		setMaxStackSize(64);
		setRegistryName("test_tube");
		setTranslationKey(GTMod.MODID + "." + "test_tube");
		setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(GTHelperFluid.getFluidName(stack));
		if (GTHelperFluid.isFluidGas(stack)) {
			tooltip.add(TextFormatting.GREEN + I18n.format("Gaseous"));
		}
		if (GTHelperFluid.isFluidPlaceable(stack)) {
			tooltip.add(TextFormatting.YELLOW + I18n.format("Can be placed in world"));
		}
		if (GTHelperFluid.isFluidBurnable(stack)) {
			tooltip.add(TextFormatting.RED + I18n.format("Can be burned as liquid fuel"));
		}
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new GTFluidItemStackHandler(stack, stack, size);
	}

	@Override
	public void getSubItems(@Nullable final CreativeTabs tab, final NonNullList<ItemStack> subItems) {
		if (this.isInCreativeTab(tab)) {
			subItems.add(empty);
			for (Fluid fluid : FluidRegistry.getRegisteredFluids().values()) {
				subItems.add(GTMaterialGen.getModdedTube(fluid.getName(), 1));
			}
		}
	}

	@Override
	@Nonnull
	public ActionResult<ItemStack> onItemRightClick(@Nonnull World world, @Nonnull EntityPlayer player,
			@Nonnull EnumHand hand) {
		ItemStack itemstack = player.getHeldItem(hand);
		FluidStack fluidStack = FluidUtil.getFluidContained(itemstack);
		if (fluidStack == null) {
			return tryPickUpFluid(world, player, hand, itemstack, fluidStack);
		}
		return tryPlaceFluid(world, player, hand, itemstack, fluidStack);
	}

	public ActionResult<ItemStack> tryPickUpFluid(@Nonnull World world, @Nonnull EntityPlayer player,
			@Nonnull EnumHand hand, ItemStack itemstack, FluidStack fluidStack) {
		RayTraceResult mop = this.rayTrace(world, player, true);
		ActionResult<ItemStack> ret = ForgeEventFactory.onBucketUse(player, world, itemstack, mop);
		if (ret != null)
			return ret;
		if (mop == null) {
			return ActionResult.newResult(EnumActionResult.PASS, itemstack);
		}
		BlockPos clickPos = mop.getBlockPos();
		if (world.isBlockModifiable(player, clickPos)) {
			FluidActionResult result = FluidUtil.tryPickUpFluid(itemstack, player, world, clickPos, mop.sideHit);
			if (result.isSuccess()) {
				ItemHandlerHelper.giveItemToPlayer(player, result.getResult());
				itemstack.shrink(1);
				return ActionResult.newResult(EnumActionResult.SUCCESS, itemstack);
			}
		}
		return ActionResult.newResult(EnumActionResult.FAIL, itemstack);
	}

	public ActionResult<ItemStack> tryPlaceFluid(@Nonnull World world, @Nonnull EntityPlayer player,
			@Nonnull EnumHand hand, ItemStack itemstack, FluidStack fluidStack) {
		RayTraceResult mop = this.rayTrace(world, player, false);
		ActionResult<ItemStack> ret = ForgeEventFactory.onBucketUse(player, world, itemstack, mop);
		if (ret != null)
			return ret;
		if (mop == null || mop.typeOfHit != RayTraceResult.Type.BLOCK) {
			return ActionResult.newResult(EnumActionResult.PASS, itemstack);
		}
		BlockPos clickPos = mop.getBlockPos();
		if (world.isBlockModifiable(player, clickPos)) {
			BlockPos targetPos = clickPos.offset(mop.sideHit);
			if (player.canPlayerEdit(targetPos, mop.sideHit, itemstack)) {
				FluidActionResult result = FluidUtil.tryPlaceFluid(player, world, targetPos, itemstack, fluidStack);
				if (result.isSuccess() && !player.capabilities.isCreativeMode) {
					player.addStat(StatList.getObjectUseStats(this));
					itemstack.shrink(1);
					ItemStack emptyStack = new ItemStack(GTItems.testTube);
					if (itemstack.isEmpty()) {
						return ActionResult.newResult(EnumActionResult.SUCCESS, emptyStack);
					} else {
						ItemHandlerHelper.giveItemToPlayer(player, emptyStack);
						return ActionResult.newResult(EnumActionResult.SUCCESS, itemstack);
					}
				}
			}
		}
		return ActionResult.newResult(EnumActionResult.FAIL, itemstack);
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
		GTModelLoader.register("test_tube", new GTModelTestTube());
	}
}
