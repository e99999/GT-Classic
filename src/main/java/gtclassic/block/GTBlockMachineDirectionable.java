package gtclassic.block;

import gtclassic.GTBlocks;
import gtclassic.tile.GTTileAESU;
import gtclassic.tile.GTTileBufferFluid;
import gtclassic.tile.GTTileBufferLarge;
import gtclassic.tile.GTTileBufferSmall;
import gtclassic.tile.GTTileIDSU;
import gtclassic.tile.GTTileLESU;
import gtclassic.tile.GTTileTranslocator;
import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTBlockMachineDirectionable extends GTBlockMachine {

	public GTBlockMachineDirectionable(String name, LocaleComp comp) {
		super(name, comp);
	}

	public GTBlockMachineDirectionable(String name, LocaleComp comp, int additionalInfo) {
		super(name, comp, additionalInfo);
	}

	@Override
	public TileEntityBlock createNewTileEntity(World worldIn, int meta) {
		if (this == GTBlocks.tileLESU) {
			return new GTTileLESU();
		}
		if (this == GTBlocks.tileAESU) {
			return new GTTileAESU();
		}
		if (this == GTBlocks.tileIDSU) {
			return new GTTileIDSU();
		}
		if (this == GTBlocks.tileTranslocator) {
			return new GTTileTranslocator();
		}
		if (this == GTBlocks.tileBufferLarge) {
			return new GTTileBufferLarge();
		}
		if (this == GTBlocks.tileBufferSmall) {
			return new GTTileBufferSmall();
		}
		if (this == GTBlocks.tileBufferFluid) {
			return new GTTileBufferFluid();
		}
		return new TileEntityBlock();
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		if (!IC2.platform.isRendering()) {
			TileEntity tile = worldIn.getTileEntity(pos);
			if (tile instanceof TileEntityBlock) {
				TileEntityBlock block = (TileEntityBlock) tile;
				if (placer == null) {
					block.setFacing(EnumFacing.NORTH);
				} else {
					int pitch = Math.round(placer.rotationPitch);
					if (pitch >= 65) {
						block.setFacing(EnumFacing.UP);
					} else if (pitch <= -65) {
						block.setFacing(EnumFacing.DOWN);
					} else {
						block.setFacing(EnumFacing.fromAngle((double) placer.rotationYaw).getOpposite());
					}
				}
				if (stack.hasDisplayName()) {
					block.setCustomName(stack.getDisplayName());
				}
			}
		}
		TileEntity tile = worldIn.getTileEntity(pos);
		if (tile instanceof GTTileIDSU && placer != null) {
			((GTTileIDSU) tile).setOwner(placer);
		}
	}
}