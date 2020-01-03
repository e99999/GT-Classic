package gtclassic.common.block;

import java.util.List;

import gtclassic.common.GTBlocks;
import gtclassic.common.tile.GTTileAESU;
import gtclassic.common.tile.GTTileBufferFluid;
import gtclassic.common.tile.GTTileBufferLarge;
import gtclassic.common.tile.GTTileBufferSmall;
import gtclassic.common.tile.GTTileIDSU;
import gtclassic.common.tile.GTTilePipelineItemEnd;
import gtclassic.common.tile.GTTileSupercondensator;
import gtclassic.common.tile.GTTileTranslocator;
import gtclassic.common.tile.GTTileTranslocatorFluid;
import gtclassic.common.tile.multi.GTTileMultiLESU;
import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.lang.storage.Ic2InfoLang;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
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

	public GTBlockMachineDirectionable(String name, LocaleComp comp, Material material, int additionalInfo) {
		super(name, comp, material, additionalInfo);
	}

	@Override
	public TileEntityBlock createNewTileEntity(World worldIn, int meta) {
		if (this == GTBlocks.tileLESU) {
			return new GTTileMultiLESU();
		}
		if (this == GTBlocks.tileAESU) {
			return new GTTileAESU();
		}
		if (this == GTBlocks.tileIDSU) {
			return new GTTileIDSU();
		}
		if (this == GTBlocks.tileSupercondensator) {
			return new GTTileSupercondensator();
		}
		if (this == GTBlocks.tileTranslocator) {
			return new GTTileTranslocator();
		}
		if (this == GTBlocks.tileTranslocatorFluid) {
			return new GTTileTranslocatorFluid();
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
		if (this == GTBlocks.tilePipelineItemEnd) {
			return new GTTilePipelineItemEnd();
		}
		return new TileEntityBlock();
	}

	@Override
	public void addReaderInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (this == GTBlocks.tileAESU) {
			tooltip.add((Ic2InfoLang.electricMaxIn.getLocalizedFormatted(new Object[] { 2048 })));
			tooltip.add((Ic2InfoLang.electricMaxStorage.getLocalizedFormatted(new Object[] { 100000000 })));
		}
		if (this == GTBlocks.tileIDSU) {
			tooltip.add((Ic2InfoLang.electricMaxIn.getLocalizedFormatted(new Object[] { 8192 })));
			tooltip.add((Ic2InfoLang.electricMaxStorage.getLocalizedFormatted(new Object[] { 400000000 })));
		}
		if (this == GTBlocks.tileLESU) {
			tooltip.add((Ic2InfoLang.electricMaxIn.getLocalizedFormatted(new Object[] { 32 })));
			tooltip.add((Ic2InfoLang.electricMaxStorage.getLocalizedFormatted(new Object[] { 202000000 })));
			tooltip.add((Ic2InfoLang.euOutput.getLocalizedFormatted(new Object[] { 512 })));
		}
		if (this == GTBlocks.tileTranslocator || this == GTBlocks.tileBufferSmall || this == GTBlocks.tileBufferLarge
				|| this == GTBlocks.tileBufferFluid || this == GTBlocks.tileTranslocatorFluid) {
			tooltip.add((Ic2InfoLang.euReaderCableLimit.getLocalizedFormatted(new Object[] { 32 })));
		}
		if (this == GTBlocks.tileSupercondensator) {
			tooltip.add(Ic2InfoLang.electricTransformer.getLocalizedFormatted(new Object[] { 134217728, 8192 }));
		}
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
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