package gtclassic.common.block;

import gtclassic.common.GTLang;
import gtclassic.common.tile.GTTileLamp;
import ic2.core.block.base.tile.TileEntityBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class GTBlockLamp extends GTBlockStorage {

	public GTBlockLamp() {
		super("lamp", GTLang.LAMP, Material.REDSTONE_LIGHT, 1);
		setHardness(0.3F);
		setSoundType(SoundType.GLASS);
	}

	@Override
	public boolean hasFacing() {
		return false;
	}

	@Override
	public IBlockState getStateFromStack(ItemStack stack) {
		return this.getDefaultBlockState().withProperty(active, true);
	}

	@Override
	public TileEntityBlock createNewTileEntity(World worldIn, int meta) {
		return new GTTileLamp();
	}

	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof GTTileLamp) {
			return ((GTTileLamp) tile).redstoneLevel;
		} else {
			return 0;
		}
	}

	@Deprecated
	public int getLightValue(IBlockState state) {
		return 0;
	}
}
