package gtclassic.common.tile;

import java.util.Collections;
import java.util.List;

import gtclassic.common.GTLang;
import gtclassic.common.container.GTContainerTranslocatorFluid;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileTranslocatorFluid extends GTTileBufferBase implements IHasGui {

	public FluidStack filter;
	public static final String NBT_FLUIDFILTER = "filter";

	public GTTileTranslocatorFluid() {
		super(1);
		this.hasRedstone = false;
		this.isFluid = true;
	}

	@Override
	public LocaleComp getBlockName() {
		return GTLang.TRANSLOCATOR_FLUID;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		if (nbt.hasKey(NBT_FLUIDFILTER)) {
			Fluid storedFluid = FluidRegistry.getFluid(nbt.getString(NBT_FLUIDFILTER));
			if (storedFluid != null) {
				this.filter = new FluidStack(FluidRegistry.getFluid(nbt.getString(NBT_FLUIDFILTER)), 1000);
			}
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		if (this.filter != null) {
			nbt.setString(NBT_FLUIDFILTER, this.filter.getFluid().getName());
		}
		return nbt;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerTranslocatorFluid(player.inventory, this);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return !this.isInvalid();
	}

	@Override
	public void onGuiClosed(EntityPlayer entityPlayer) {
		// needed for construction
	}

	@Override
	public boolean hasGui(EntityPlayer player) {
		return true;
	}

	@Override
	public void onBufferTick() {
		BlockPos importPos = this.pos.offset(this.getFacing().getOpposite());
		BlockPos exportPos = this.pos.offset(this.getFacing());
		if (!world.isBlockLoaded(importPos) || !world.isBlockLoaded(exportPos)) {
			return;
		}
		IFluidHandler start = FluidUtil.getFluidHandler(world, importPos, getFacing());
		IFluidHandler end = FluidUtil.getFluidHandler(world, exportPos, getFacing().getOpposite());
		boolean canExport = start != null && end != null;
		if (canExport && filter == null) {
			FluidUtil.tryFluidTransfer(end, start, 10000, true);
		}
		if (canExport && filter != null) {
			FluidUtil.tryFluidTransfer(end, start, new FluidStack(filter, 10000), true);
		}
	}

	@Override
	public List<ItemStack> getDrops() {
		return Collections.emptyList();
	}

	@Override
	public boolean isInventoryFull() {
		return false;
	}
}
