package gtclassic.common.block;

import java.util.List;

import gtclassic.common.GTLang;
import gtclassic.common.tile.GTTileDrum;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class GTBlockDrum extends GTBlockStorage {

	public GTBlockDrum() {
		super("drum", GTLang.DRUM, Material.GROUND, 1);
		setHardness(0.5F);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (stack.hasTagCompound()) {
			NBTTagCompound nbt;
			nbt = StackUtil.getNbtData(stack);
			if (nbt.hasKey("Fluid")) {
				FluidStack fluid = FluidStack.loadFluidStackFromNBT(nbt.getCompoundTag("Fluid"));
				tooltip.add(I18n.format(fluid.amount + "mB of " + fluid.getLocalizedName()));
			}
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		TileEntity tile = worldIn.getTileEntity(pos);
		NBTTagCompound nbt;
		if (tile instanceof GTTileDrum) {
			GTTileDrum tank = (GTTileDrum) tile;
			if (stack.hasTagCompound()) {
				nbt = StackUtil.getNbtData(stack);
				if (nbt.hasKey("Fluid")) {
					tank.getTankInstance().setFluid(FluidStack.loadFluidStackFromNBT(nbt.getCompoundTag("Fluid")));
				}
				if (nbt.hasKey("flow")) {
					tank.setFlow(nbt.getBoolean("flow"));
				}
			}
		}
	}

	@Override
	public TileEntityBlock createNewTileEntity(World arg0, int arg1) {
		return new GTTileDrum();
	}
}