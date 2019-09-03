package gtclassic.helpers;

import gtclassic.GTMod;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTHelperWorld {

	public static void notifyPlayersOfGeneration(World worldIn, BlockPos pos, String info, String extrainfo) {
		GTMod.debugLogger("Created " + info + ":" + extrainfo + " @ " + pos.getX() + " " + pos.getY() + " " + pos.getZ()
				+ " Dim:" + worldIn.provider.getDimension());
	}

	public static boolean entityHasFlammible(EntityLivingBase entity) {
		return GTHelperStack.isEqual(new ItemStack(Blocks.TORCH), entity.getHeldItemOffhand())
				|| GTHelperStack.isEqual(new ItemStack(Blocks.TORCH), entity.getHeldItemMainhand());
	}

	public static boolean blockHasFlammible(World worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos).getMaterial() == Material.LAVA
				|| worldIn.getBlockState(pos).getBlock() == Blocks.TORCH
				|| worldIn.getBlockState(pos).getMaterial() == Material.FIRE;
	}

	public static boolean blockHasFlammibleAdjacent(World worldIn, BlockPos pos) {
		for (EnumFacing side : EnumFacing.VALUES) {
			if (GTHelperWorld.blockHasFlammible(worldIn, pos.offset(side))) {
				return true;
			}
		}
		return false;
	}
}
