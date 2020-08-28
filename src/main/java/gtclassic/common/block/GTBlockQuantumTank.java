package gtclassic.common.block;

import java.util.ArrayList;
import java.util.List;

import gtclassic.api.helpers.GTHelperMath;
import gtclassic.api.interfaces.IGTItemContainerTile;
import gtclassic.common.GTLang;
import gtclassic.common.tile.GTTileQuantumTank;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.item.misc.ItemDisplayIcon;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class GTBlockQuantumTank extends GTBlockMachine {

	int slotInput = 0;
	int slotOutput = 1;
	int slotDisplay = 2;

	public GTBlockQuantumTank() {
		super("quantumtank", GTLang.QUANTUM_TANK);
	}

	@Override
	public TileEntityBlock createNewTileEntity(World worldIn, int meta) {
		return new GTTileQuantumTank();
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (stack.hasTagCompound()) {
			NBTTagCompound nbt;
			nbt = StackUtil.getNbtData(stack);
			if (nbt.hasKey("Fluid")) {
				FluidStack fluid = FluidStack.loadFluidStackFromNBT(nbt.getCompoundTag("Fluid"));
				tooltip.add(TextFormatting.AQUA + I18n.format(fluid.amount + "mB of " + fluid.getLocalizedName()));
			}
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> items = new ArrayList<>();
		TileEntity te = this.getLocalTile() == null ? world.getTileEntity(pos) : this.getLocalTile();
		if (te instanceof IGTItemContainerTile) {
			items.addAll(((IGTItemContainerTile) te).getInventoryDrops());
			return items;
		}
		return items;
	}

	@Override
	public List<ItemStack> getWrenchDrops(World world, BlockPos pos, IBlockState state, TileEntity te,
			EntityPlayer player, int fortune) {
		List<ItemStack> items = new ArrayList<>();
		if (te instanceof IGTItemContainerTile) {
			items.addAll(((IGTItemContainerTile) te).getDrops());
		}
		return items;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		TileEntity tile = worldIn.getTileEntity(pos);
		NBTTagCompound nbt;
		if (tile instanceof GTTileQuantumTank) {
			GTTileQuantumTank tank = (GTTileQuantumTank) tile;
			if (stack.hasTagCompound()) {
				nbt = StackUtil.getNbtData(stack);
				if (nbt.hasKey("Fluid")) {
					tank.getTankInstance().setFluid(FluidStack.loadFluidStackFromNBT(nbt.getCompoundTag("Fluid")));
					tank.inventory.set(2, ItemDisplayIcon.createWithFluidStack(((GTTileQuantumTank) tile).getTankInstance().getFluid()));
				}
			}
		}
	}

	@Override
	@Deprecated
	public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof GTTileQuantumTank) {
			GTTileQuantumTank tank = (GTTileQuantumTank) tile;
			if (tank.getTankInstance().getFluid() != null) {
				return GTHelperMath.clip(15 * tank.getTankInstance().getFluidAmount() / Integer.MAX_VALUE, 1, 15);
			}
		}
		return 0;
	}
}
