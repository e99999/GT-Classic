package gtclassic.block;

import gtclassic.itemblock.GTItemBlockDuctTape;
import gtclassic.itemblock.GTItemBlockInterface;
import gtclassic.tile.GTTileBlockCustom;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.item.block.ItemBlockRare;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
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
		return new GTTileBlockCustom(this.damage);
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		this.damage = (stack.getMaxDamage() - stack.getItemDamage());
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}

	// @Override
	// public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos,
	// IBlockState state, int fortune) {
	// ArrayList<ItemStack> drops = new ArrayList<>();
	// ItemStack stack = new ItemStack(this);
	// stack.setItemDamage(100);
	// drops.add(stack);
	// return drops;
	// }

}
