package gtclassic.common.tile.datanet;

import java.util.Collections;
import java.util.List;

import gtclassic.common.container.GTContainerReconstructorFluid;
import gtclassic.common.util.datanet.GTDataNet.DataType;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileReconstructorFluid extends GTTileBaseOutputNode implements IHasGui {

	public FluidStack filter;
	public static final String NBT_FLUIDFILTER = "filter";

	/** Literally just a pointer on the network to where an output pos is **/
	public GTTileReconstructorFluid() {
		super(1);
	}

	@Override
	public DataType dataType() {
		return DataType.FLUID;
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
	public BlockPos inventoryPos() {
		return this.getPos().offset(this.getFacing());
	}

	@Override
	public EnumFacing inventoryFacing() {
		return this.getFacing().getOpposite();
	}

	@Override
	public FluidStack tankFilter() {
		return this.filter;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return !this.isInvalid();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerReconstructorFluid(player.inventory, this);
	}

	@Override
	public boolean hasGui(EntityPlayer var1) {
		return true;
	}

	@Override
	public void onGuiClosed(EntityPlayer var1) {
	}

	@Override
	public List<ItemStack> getDrops() {
		return Collections.emptyList();
	}
}
