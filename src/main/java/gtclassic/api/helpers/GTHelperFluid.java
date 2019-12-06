package gtclassic.api.helpers;

import javax.annotation.Nullable;

import gtclassic.api.fluid.GTFluidHandler;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.fluid.IC2Tank;
import ic2.core.util.misc.FluidHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStackSimple;
import net.minecraftforge.fluids.capability.wrappers.BlockLiquidWrapper;
import net.minecraftforge.fluids.capability.wrappers.FluidBlockWrapper;

public class GTHelperFluid {

	/** Created by e99999, does fluid container handling for GTC tiles **/
	public static void doFluidContainerThings(TileEntityMachine tile, IC2Tank tank, int slotInput, int slotOutput) {
		if (!GTHelperStack.isSlotEmpty(tile, slotInput)) {
			// emptying items
			ItemStack emptyStack = FluidUtil.tryEmptyContainer(tile.getStackInSlot(slotInput), tank, tank.getCapacity()
					- tank.getFluidAmount(), null, false).getResult();
			boolean didEmpty = FluidUtil.tryEmptyContainer(tile.getStackInSlot(slotInput), tank, tank.getCapacity()
					- tank.getFluidAmount(), null, false) != FluidActionResult.FAILURE;
			if (!isTankFull(tank) && !GTHelperStack.isSlotFull(tile, slotOutput)
					&& GTHelperStack.canOutputStack(tile, emptyStack, slotOutput) && didEmpty) {
				FluidUtil.tryEmptyContainer(tile.getStackInSlot(slotInput), tank, tank.getCapacity()
						- tank.getFluidAmount(), null, true);
				if (GTHelperStack.isSlotEmpty(tile, slotOutput)) {
					tile.setStackInSlot(slotOutput, emptyStack);
				} else {
					tile.getStackInSlot(slotOutput).grow(1);
				}
				tile.getStackInSlot(slotInput).shrink(1);
			}
			// filling items
			Tuple<ItemStack, FluidStack> filled = FluidHelper.fillContainer(tile.getStackInSlot(slotInput), tank.getFluid(), true, true);
			if (filled != null && GTHelperStack.canOutputStack(tile, filled.getFirst(), slotOutput)) {
				if (GTHelperStack.isSlotEmpty(tile, slotOutput)) {
					tile.setStackInSlot(slotOutput, filled.getFirst());
				} else {
					tile.getStackInSlot(slotOutput).grow(1);
				}
				tile.getStackInSlot(slotInput).shrink(1);
				tank.drainInternal((FluidStack) filled.getSecond(), true);
			}
		}
	}

	/**
	 * Created by e99999, does clicking fluid filling/emptying logic for GTC Tiles
	 **/
	public static boolean doClickableFluidContainerThings(EntityPlayer player, EnumHand hand, World world, BlockPos pos,
			IC2Tank tank) {
		ItemStack playerStack = player.getHeldItem(hand);
		if (!playerStack.isEmpty()) {
			FluidActionResult result = FluidUtil.tryEmptyContainer(playerStack, tank, tank.getCapacity()
					- tank.getFluidAmount(), player, true);
			if (result.isSuccess()) {
				playerStack.shrink(1);
				ItemStack resultStack = result.getResult();
				if (!resultStack.isEmpty()) {
					if (!player.inventory.addItemStackToInventory(resultStack)) {
						player.dropItem(resultStack, false);
					}
				}
				return true;
			}
			FluidActionResult result2 = FluidUtil.tryFillContainer(playerStack, tank, tank.getCapacity(), player, true);
			if (result2.isSuccess()) {
				playerStack.shrink(1);
				ItemStack resultStack = result2.getResult();
				if (!resultStack.isEmpty()) {
					if (!player.inventory.addItemStackToInventory(resultStack)) {
						player.dropItem(resultStack, false);
					}
				}
				return true;
			}
		}
		return false;
	}

	public static boolean doClickableFluidContainerFillThings(EntityPlayer player, EnumHand hand, World world,
			BlockPos pos, IC2Tank tank) {
		ItemStack playerStack = player.getHeldItem(hand);
		if (!playerStack.isEmpty()) {
			FluidActionResult result = FluidUtil.tryFillContainer(playerStack, tank, tank.getCapacity(), player, true);
			if (result.isSuccess()) {
				playerStack.shrink(1);
				ItemStack resultStack = result.getResult();
				if (!resultStack.isEmpty()) {
					if (!player.inventory.addItemStackToInventory(resultStack)) {
						player.dropItem(resultStack, false);
					}
				}
				return true;
			}
		}
		return false;
	}

	public static boolean doClickableFluidContainerEmptyThings(EntityPlayer player, EnumHand hand, World world,
			BlockPos pos, IC2Tank tank) {
		ItemStack playerStack = player.getHeldItem(hand);
		if (!playerStack.isEmpty()) {
			FluidActionResult result = FluidUtil.tryEmptyContainer(playerStack, tank, tank.getCapacity()
					- tank.getFluidAmount(), player, true);
			if (result.isSuccess()) {
				playerStack.shrink(1);
				ItemStack resultStack = result.getResult();
				if (!resultStack.isEmpty()) {
					if (!player.inventory.addItemStackToInventory(resultStack)) {
						player.dropItem(resultStack, false);
					}
				}
				return true;
			}
		}
		return false;
	}
	
	 /**
     * Helper method to get an IFluidHandler for at tile position.
     *
     * Returns null if there is no valid fluid handler tile.
     */
    @Nullable
    public static IFluidHandler getFluidHandler(World world, BlockPos blockPos, @Nullable EnumFacing side)
    {
        IBlockState state = world.getBlockState(blockPos);
        Block block = state.getBlock();

        if (block.hasTileEntity(state))
        {
            TileEntity tileEntity = world.getTileEntity(blockPos);
            if (tileEntity != null && tileEntity.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side))
            {
                return tileEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side);
            }
        }
        return null;
    }

	// helper for fluid handling
	public static boolean isTankFull(IC2Tank tank) {
		return tank.getFluidAmount() == tank.getCapacity();
	}

	// helper for fluid handling
	public static boolean isConsumable(ItemStack stack) {
		return stack.getItem().initCapabilities(stack, null) instanceof FluidHandlerItemStackSimple.Consumable;
	}

	// helper for fluid handling
	public static String getFluidName(IC2Tank tank) {
		return tank.getFluid().getLocalizedName();
	}

	// helper for fluid handling
	public static int getFluidAmount(IC2Tank tank) {
		return isTankEmpty(tank) ? tank.getFluid().amount : 0;
	}

	// helper for fluid handling
	public static boolean isTankEmpty(IC2Tank tank) {
		return tank.getFluid() == null || tank.getFluidAmount() == 0;
	}

	// helper for fluid containing itemstacks
	public static String getFluidName(ItemStack stack) {
		FluidStack fluid = FluidUtil.getFluidContained(stack);
		if (fluid != null) {
			return fluid.amount + "mB of " + fluid.getLocalizedName();
		}
		return "Empty";
	}

	// helper for fluid containing itemstacks
	public static Boolean isFluidPlaceable(ItemStack stack) {
		FluidStack fluid = FluidUtil.getFluidContained(stack);
		return fluid != null && fluid.getFluid().canBePlacedInWorld();
	}

	// helper for fluid containing itemstacks
	public static Boolean isFluidGas(ItemStack stack) {
		FluidStack fluid = FluidUtil.getFluidContained(stack);
		return fluid != null && fluid.getFluid().isGaseous();
	}

	// helper for fluid containing itemstacks
	public static Boolean isFluidBurnable(ItemStack stack) {
		FluidStack fluid = FluidUtil.getFluidContained(stack);
		if (fluid != null) {
			return (GTFluidHandler.getBurnableToolTipList().contains(fluid.getFluid().getName()));
		}
		return false;
	}
}
