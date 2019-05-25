package gtclassic.tile;

import java.util.ArrayList;
import java.util.List;

import gtclassic.GTBlocks;
import gtclassic.material.GTMaterialGen;
import gtclassic.tool.GTToolHammer;
import gtclassic.util.GTValues;
import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.fluid.IC2Tank;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.IClickable;
import ic2.core.util.obj.IItemContainer;
import ic2.core.util.obj.ITankListener;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStackSimple;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.items.ItemHandlerHelper;

public class GTTileDrum extends TileEntityMachine implements ITankListener, IItemContainer, IClickable, ITickable {

	private IC2Tank tank;
	private Block drop;
	private boolean allowExport;

	public GTTileDrum() {
		super(0);
		this.tank = new IC2Tank(64000);
		this.drop = GTBlocks.drum;
		this.tank.addListener(this);
		this.addGuiFields("tank", "export");
		this.setWorld(world);
	}

	@Override
	public boolean supportsRotation() {
		return false;
	}

	public void onTankChanged(IFluidTank tank) {
		this.getNetwork().updateTileGuiField(this, "tank");
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.tank.readFromNBT(nbt.getCompoundTag("Tank"));
		this.allowExport = nbt.getBoolean("export");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		this.tank.writeToNBT(this.getTag(nbt, "Tank"));
		nbt.setBoolean("export", this.allowExport);
		return nbt;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY ? true
				: super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY
				? CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this.tank)
				: super.getCapability(capability, facing);
	}

	public List<ItemStack> getDrops() {
		List<ItemStack> list = new ArrayList();
		ItemStack stack = GTMaterialGen.get(this.drop);
		if (this.tank.getFluid() != null) {
			StackUtil.getOrCreateNbtData(stack).setTag("Fluid", this.tank.getFluid().writeToNBT(new NBTTagCompound()));
		}
		StackUtil.getOrCreateNbtData(stack).setBoolean("export", this.allowExport);
		list.add(stack);
		return list;
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	public String getFluidName() {
		return this.tank.getFluid().getLocalizedName();
	}

	public int getFluidAmount() {
		return this.tank.getFluid().amount;
	}

	public boolean isEmpty() {
		return this.tank.getFluidAmount() <= 0;
	}

	public FluidStack getFluid() {
		return this.tank.getFluid();
	}

	public IC2Tank getTankInstance() {
		return this.tank;
	}

	public void setExport(boolean input) {
		this.allowExport = input;
	}

	public boolean getExport() {
		return this.allowExport;
	}

	@Override
	public boolean hasLeftClick() {
		return false;
	}

	@Override
	public boolean hasRightClick() {
		return true;
	}

	@Override
	public void onLeftClick(EntityPlayer var1, Side var2) {

	}

	@Override
	public boolean onRightClick(EntityPlayer player, EnumHand hand, EnumFacing enumFacing, Side side) {
		ItemStack playerStack = player.getHeldItem(hand);

		if (playerStack.getItem() instanceof GTToolHammer) {
			this.allowExport = !allowExport;
			this.getNetwork().updateTileGuiField(this, "export");
			if (!IC2.platform.isSimulating()) {
				IC2.platform.messagePlayer(player, "Auto Output: " + allowExport);
				world.playSound(player, pos, SoundEvents.BLOCK_METAL_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
			}
			return true;
		}

		if (isConsumable(playerStack) || GTValues.isBCShard(playerStack)) {
			if (!IC2.platform.isSimulating()) {
				IC2.platform.messagePlayer(player, "Consumable containers are temporarily disabled");
				world.playSound(player, pos, SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
			}
			return false;
		}

		if (!playerStack.isEmpty()) {
			ItemStack stackEmpty = FluidUtil.tryEmptyContainer(playerStack, this.tank,
					this.tank.getCapacity() - this.tank.getFluidAmount(), player, true).getResult();
			ItemStack stackCopy = FluidUtil.tryEmptyContainer(playerStack, this.tank,
					this.tank.getCapacity() - this.tank.getFluidAmount(), player, false).getResult();
			if (!stackCopy.isEmpty()) {
				playerStack.shrink(1);
				ItemHandlerHelper.giveItemToPlayer(player, stackEmpty);
				return true;
			}

			ItemStack stackFilled = FluidUtil.tryFillContainer(playerStack, this.tank, 1000, player, true).getResult();
			if (!stackFilled.isEmpty()) {
				playerStack.shrink(1);
				ItemHandlerHelper.giveItemToPlayer(player, stackFilled);
				return true;
			}
		}
		return false;
	}

	public void tryExportFluid() {
		IFluidHandler tile = FluidUtil.getFluidHandler(world, getPos().down(1), EnumFacing.UP);
		boolean canExport = this.allowExport && tile != null && this.tank.getFluid() != null;
		if (world.getTotalWorldTime() % 20 == 0 && canExport) {
			FluidUtil.tryFluidTransfer(tile, this.tank, 1000, true);
		}
	}

	@Override
	public void update() {
		tryExportFluid();
	}

	public boolean isConsumable(ItemStack stack) {
		return stack.getItem().initCapabilities(stack, null) instanceof FluidHandlerItemStackSimple.Consumable;
	}

}
