package gtclassic.block;

import java.util.List;

import ic2.api.classic.tile.ISpecialWrenchable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class GTBlockCasingAdvanced extends GTBlockCasing implements ISpecialWrenchable {

	public GTBlockCasingAdvanced(String name, int id, float resistance) {
		super(name, id, resistance);
		this.setBlockUnbreakable();
		this.setHarvestLevel(null, 0);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format(this.getUnlocalizedName().replace("tile", "tooltip") + 0));
	}

	@Override
	public EnumFacing getFacing(World paramWorld, BlockPos paramBlockPos) {
		return EnumFacing.NORTH;
	}

	@Override
	public boolean setFacing(World paramWorld, BlockPos paramBlockPos, EnumFacing paramEnumFacing,
			EntityPlayer paramEntityPlayer) {
		return false;
	}

	@Override
	public boolean wrenchCanRemove(World paramWorld, BlockPos paramBlockPos, EntityPlayer paramEntityPlayer) {
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<ItemStack> getWrenchDrops(World world, BlockPos blockPos, IBlockState state, TileEntity paramTileEntity,
			EntityPlayer paramEntityPlayer, int fortune) {
		return this.getDrops(world, blockPos, state, fortune);
	}

	@Override
	public double getWrenchSuccessRate(World paramWorld, BlockPos paramBlockPos) {
		return 1.0D;
	}

	@Override
	public boolean hasSpecialAction(World paramWorld, BlockPos paramBlockPos, EnumFacing paramEnumFacing,
			EntityPlayer paramEntityPlayer, Vec3d paramVec3d) {
		return false;
	}

	@Override
	public EnumActionResult onSpecialAction(World paramWorld, BlockPos paramBlockPos, EnumFacing paramEnumFacing,
			EntityPlayer paramEntityPlayer, Vec3d paramVec3d) {
		return null;
	}
}
