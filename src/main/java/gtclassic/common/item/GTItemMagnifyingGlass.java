package gtclassic.common.item;

import java.util.LinkedHashMap;
import java.util.Map;

import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.api.interfaces.IGTMultiTileStatus;
import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.core.IC2;
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
		if (player.isSneaking() || !IC2.platform.isSimulating() || hand.equals(EnumHand.OFF_HAND)) {
			return EnumActionResult.PASS;
		}
		boolean f = false;
		if (tileEntity instanceof IProgressMachine) {
			f = true;
			IProgressMachine progress = (IProgressMachine) tileEntity;
			IC2.platform.messagePlayer(player, "Progress: "
					+ +(Math.round((progress.getProgress() / progress.getMaxProgress()) * 100)) + "%");
		}
		if (tileEntity instanceof IGTMultiTileStatus) {
			f = true;
			IGTMultiTileStatus multi = (IGTMultiTileStatus) tileEntity;
			IC2.platform.messagePlayer(player, "Correct Strucuture: " + multi.getStructureValid());
		}
		if (tileEntity instanceof IGTDebuggableTile) {
			f = true;
			LinkedHashMap<String, Boolean> data = new LinkedHashMap<>();
			IGTDebuggableTile debug = (IGTDebuggableTile) tileEntity;
			debug.getData(data);
			for (Map.Entry<String, Boolean> entry : data.entrySet()) {
				if (!entry.getValue()) {
					IC2.platform.messagePlayer(player, entry.getKey());
				}
			}
		}
		if (f) {
			world.playSound(null, player.getPosition(), SoundEvents.ENTITY_VILLAGER_AMBIENT, SoundCategory.PLAYERS, 1.0F, 1.0F);
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}
}
