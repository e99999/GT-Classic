package gtclassic.itemblock;

import java.util.Random;

import gtclassic.block.GTBlockTileCustom;
import gtclassic.tile.GTTileBlockCustom;
import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.item.block.ItemBlockRare;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTBlockDuctTape extends GTBlockTileCustom implements GTItemBlockInterface {

	public int damage = 255;

	public GTBlockDuctTape(String name, int width, int height, boolean light) {
		super(name, width, height, light);
	}

	@Override
	public Class<? extends ItemBlockRare> getCustomItemBlock() {
		return GTItemBlockDuctTape.class;
	}

	@Override
	public TileEntityBlock createNewTileEntity(World world, int arg1) {
		return new GTTileBlockCustom();
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		if (!IC2.platform.isRendering()) {
			TileEntity tile = worldIn.getTileEntity(pos);
			if (tile instanceof GTTileBlockCustom) {
				this.damage = (stack.getMaxDamage() - stack.getItemDamage());
				((GTTileBlockCustom) tile).setItem(stack);
			}
		}
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		return 0;
	}

}
