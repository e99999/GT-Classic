package gtclassic.common.item;

import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.api.interfaces.IGTMultiTileStatus;
import gtclassic.api.pipe.GTTilePipeBase;
import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.core.IC2;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTItemMagnifyingGlass extends GTItemComponent {

	public GTItemMagnifyingGlass() {
		super("magnifying_glass", 3, 2);
	}

	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX,
			float hitY, float hitZ, EnumHand hand) {
		TileEntity tileEntity = world.getTileEntity(pos);
		IBlockState state = world.getBlockState(pos);
		if (player.isSneaking() || !IC2.platform.isSimulating()) {
			return EnumActionResult.PASS;
		}
		world.playSound(null, player.getPosition(), SoundEvents.ENTITY_VILLAGER_AMBIENT, SoundCategory.PLAYERS, 1.0F, 1.0F);
		IC2.platform.messagePlayer(player, "---" + state.getBlock().getLocalizedName() + "---");
		if (tileEntity instanceof IProgressMachine) {
			IProgressMachine progress = (IProgressMachine) tileEntity;
			IC2.platform.messagePlayer(player, "Progress: "
					+ +(Math.round((progress.getProgress() / progress.getMaxProgress()) * 100)) + "%");
		}
		if (tileEntity instanceof IGTMultiTileStatus) {
			IGTMultiTileStatus multi = (IGTMultiTileStatus) tileEntity;
			IC2.platform.messagePlayer(player, "Correct Strucuture: " + multi.getStructureValid());
		}
		if (tileEntity instanceof IGTDebuggableTile) {
			IGTDebuggableTile debug = (IGTDebuggableTile) tileEntity;
			if (debug.canDebugWithMagnifyingGlass()) {
				for (String data : debug.debugInfo()) {
					IC2.platform.messagePlayer(player, data);
				}
			}
		}
		if (tileEntity instanceof GTTilePipeBase) {
			GTTilePipeBase pipe = (GTTilePipeBase) tileEntity;
			if (pipe.blacklist.isEmpty()) {
				IC2.platform.messagePlayer(player, "Export Blacklist is empty");
			}
			for (EnumFacing facing : pipe.blacklist) {
				IC2.platform.messagePlayer(player, "Will not export " + facing.toString().toUpperCase());
			}
		}
		IC2.platform.messagePlayer(player, "You are facing: " + GTItemCreativeScanner.getPlayerDirection(player, side).toString().toUpperCase());
		return EnumActionResult.SUCCESS;
	}
}
