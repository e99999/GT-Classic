package gtclassic.api.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IGTMonkeyWrenchTile {
	
	public boolean onMonkeyWrench(EntityPlayer player, World world, BlockPos pos, EnumFacing side, EnumHand hand);
}
