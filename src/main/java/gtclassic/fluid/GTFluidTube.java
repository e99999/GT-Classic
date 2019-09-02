package gtclassic.fluid;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import gtclassic.GTItems;
import gtclassic.GTMod;
import gtclassic.color.GTColorItemInterface;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import gtclassic.material.GTMaterialGen;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IAdvancedTexturedItem;
import ic2.core.platform.textures.obj.ILayeredItemModel;
import ic2.core.platform.textures.obj.ITexturedItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
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

public class GTFluidTube extends Item
		implements IAdvancedTexturedItem, ITexturedItem, ILayeredItemModel, GTColorItemInterface {

	private final int size = Fluid.BUCKET_VOLUME;
	private final ItemStack empty = new ItemStack(this);
	public ModelResourceLocation[] model = new ModelResourceLocation[2];

	public GTFluidTube() {
		setMaxStackSize(64);
		setRegistryName("test_tube");
		setUnlocalizedName(GTMod.MODID + "." + "test_tube");
		setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(getFluidName(stack));
		if (isFluidGas(stack)) {
			tooltip.add(TextFormatting.GREEN + I18n.format("Gaseous"));
		}
		if (isFluidPlaceable(stack)) {
			tooltip.add(TextFormatting.YELLOW + I18n.format("Can be placed in world"));
		}
		if (isFluidBurnable(stack)) {
			tooltip.add(TextFormatting.RED + I18n.format("Can be burned as liquid fuel"));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		FluidStack fluid = FluidUtil.getFluidContained(stack);
		return (fluid != null) && (fluid.getFluid().getName().contains("plasma"));
	}

	public String getFluidName(ItemStack stack) {
		FluidStack fluid = FluidUtil.getFluidContained(stack);
		if (fluid != null) {
			return fluid.amount + "mB of " + fluid.getLocalizedName();
		}
		return "Empty";
	}

	public Boolean isFluidPlaceable(ItemStack stack) {
		FluidStack fluid = FluidUtil.getFluidContained(stack);
		return fluid != null && fluid.getFluid().canBePlacedInWorld();
	}

	public Boolean isFluidGas(ItemStack stack) {
		FluidStack fluid = FluidUtil.getFluidContained(stack);
		return fluid != null && fluid.getFluid().isGaseous();
	}

	public Boolean isFluidBurnable(ItemStack stack) {
		FluidStack fluid = FluidUtil.getFluidContained(stack);
		if (fluid != null) {
			return fluid.getFluid().getName().equalsIgnoreCase("hydrogen")
					|| fluid.getFluid().getName().equalsIgnoreCase("methane")
					|| fluid.getFluid().getName().equalsIgnoreCase("sodium");
		}
		return false;
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new GTFluidItemStackHandler(stack, stack, size);
	}

	@Override
	public void getSubItems(@Nullable final CreativeTabs tab, final NonNullList<ItemStack> subItems) {
		if (this.isInCreativeTab(tab)) {
			subItems.add(empty);
			subItems.add(GTMaterialGen.getWater(1));
			subItems.add(GTMaterialGen.getLava(1));
			for (GTMaterial mat : GTMaterial.values()) {
				if (mat.hasFlag(GTMaterialFlag.FLUID)) {
					subItems.add(GTMaterialGen.getTube(mat, 1));
				}
				if (mat.hasFlag(GTMaterialFlag.GAS)) {
					subItems.add(GTMaterialGen.getTube(mat, 1));
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public ModelResourceLocation createResourceLocationForStack(ItemStack stack) {
		boolean hasFluid = FluidUtil.getFluidContained(stack) != null;
		ResourceLocation location = this.getRegistryName();
		String name = stack.getUnlocalizedName();
		this.model[hasFluid ? 1 : 0] = new ModelResourceLocation(location.getResourceDomain()
				+ name.substring(name.indexOf(".") + 1) + (hasFluid ? 1 : 0), "inventory");
		return this.model[hasFluid ? 1 : 0];
	}

	@SideOnly(Side.CLIENT)
	public ModelResourceLocation getResourceLocationForStack(ItemStack stack) {
		return this.model[FluidUtil.getFluidContained(stack) != null ? 1 : 0];
	}

	public boolean isLayered(ItemStack stack) {
		return FluidUtil.getFluidContained(stack) != null;
	}

	public int getLayers(ItemStack stack) {
		return FluidUtil.getFluidContained(stack) != null ? 2 : 1;
	}

	@Override
	public TextureAtlasSprite getTexture(int index, ItemStack stack) {
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[13 + index];
	}

	@Override
	public List<ItemStack> getValidItemVariants() {
		return Arrays.asList(GTMaterialGen.get(GTItems.testTube), GTMaterialGen.getTube(GTMaterial.Hydrogen, 1));
	}

	@Override
	public TextureAtlasSprite getTexture(ItemStack var1) {
		// does not do anything since its layered
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[0];
	}

	@Override
	public Color getColor(ItemStack stack, int index) {
		FluidStack fluid = FluidUtil.getFluidContained(stack);
		if (fluid != null && index == 1) {
			if (fluid.getFluid() == FluidRegistry.WATER) {
				return Color.blue;
			}
			if (fluid.getFluid() == FluidRegistry.LAVA) {
				return Color.red;
			}
			if (fluid.getFluid().getName().contains("bio")) {
				return Color.green;
			}
			if (fluid.getFluid().getName().contains("oil")) {
				return Color.black;
			}
			if (fluid.getFluid().getName().contains("fuel")) {
				return Color.yellow;
			}
		}
		if (fluid != null && index == 1 && fluid.getFluid() instanceof GTFluid) {
			GTFluid gtFluid = (GTFluid) fluid.getFluid();
			return gtFluid.getGTMaterial().getColor();
		}
		return Color.white;
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
		// clicked on a block?
		RayTraceResult mop = this.rayTrace(world, player, false);
		ActionResult<ItemStack> ret = ForgeEventFactory.onBucketUse(player, world, itemstack, mop);
		if (ret != null)
			return ret;
		if (mop == null || mop.typeOfHit != RayTraceResult.Type.BLOCK) {
			return ActionResult.newResult(EnumActionResult.PASS, itemstack);
		}
		BlockPos clickPos = mop.getBlockPos();
		// can we place liquid there?
		if (world.isBlockModifiable(player, clickPos)) {
			// the block adjacent to the side we clicked on
			BlockPos targetPos = clickPos.offset(mop.sideHit);
			// can the player place there?
			if (player.canPlayerEdit(targetPos, mop.sideHit, itemstack)) {
				// try placing liquid
				FluidActionResult result = FluidUtil.tryPlaceFluid(player, world, targetPos, itemstack, fluidStack);
				if (result.isSuccess() && !player.capabilities.isCreativeMode) {
					// success!
					player.addStat(StatList.getObjectUseStats(this));
					itemstack.shrink(1);
					ItemStack emptyStack = new ItemStack(GTItems.testTube);
					// check whether we replace the item or add the empty one to the inventory
					if (itemstack.isEmpty()) {
						return ActionResult.newResult(EnumActionResult.SUCCESS, emptyStack);
					} else {
						// add empty bucket to player inventory
						ItemHandlerHelper.giveItemToPlayer(player, emptyStack);
						return ActionResult.newResult(EnumActionResult.SUCCESS, itemstack);
					}
				}
			}
		}
		// couldn't place liquid there2
		return ActionResult.newResult(EnumActionResult.FAIL, itemstack);
	}
}
