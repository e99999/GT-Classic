package gtclassic.helpers;

import gtclassic.GTConfig;
import ic2.core.IC2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTHelperWorld {

	public static BlockPos lastGenPos = null;
	public static int dim = 0;

	public static void notifyPlayersOfGeneration(World worldIn, BlockPos pos, String info, String extrainfo) {
		if (GTConfig.worldGenDebug && !worldIn.playerEntities.isEmpty()) {
			for (EntityPlayer player : worldIn.playerEntities) {
				if (IC2.platform.isOp(player.getUniqueID())) {
					IC2.platform.messagePlayer(player, "Created " + info + ":" + extrainfo + " At: " + pos.getX() + " "
							+ pos.getY() + " " + pos.getZ() + " ");
				}
			}
			lastGenPos = pos;
			dim = worldIn.provider.getDimension();
		}
	}
}
