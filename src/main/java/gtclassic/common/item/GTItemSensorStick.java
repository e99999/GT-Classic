package gtclassic.common.item;

import java.util.List;

import gtclassic.api.interfaces.IGTCoordinateTile;
import ic2.core.IC2;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class GTItemSensorStick extends GTItemComponent {

	public static final String POS = "pos";
	public static final String BLOCK = "block";

	public GTItemSensorStick() {
		super("sensor_stick", 7, 2);
		this.setMaxStackSize(1);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		NBTTagCompound nbt = StackUtil.getNbtData(stack);
		if (nbt.getIntArray(POS).length == 4) {
			tooltip.add("Click a valid block to install Sensor Stick into machine");
			int[] pos = nbt.getIntArray(POS);
			tooltip.add(TextFormatting.YELLOW + I18n.format("Last Scanned: "));
			if (nbt.getString(BLOCK) != null) {
				tooltip.add(TextFormatting.BLUE + I18n.format(nbt.getString(BLOCK)));
			}
			tooltip.add(TextFormatting.BLUE + I18n.format("X: " + pos[0] + " Y: " + pos[1] + " Z: " + pos[2]));
			tooltip.add(TextFormatting.BLUE + I18n.format("Dimension: " + pos[3]));
		} else {
			tooltip.add("Click a block to set coordinates to internal memory");
		}
	}

	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX,
			float hitY, float hitZ, EnumHand hand) {
		if (IC2.platform.isRendering()) {
			IC2.audioManager.playOnce(player, Ic2Sounds.wrenchUse);
			return EnumActionResult.SUCCESS;
		}
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity instanceof IGTCoordinateTile) {
			return tryParseCoords((IGTCoordinateTile) tileEntity, world, player, hand);
		} else {
			NBTTagCompound nbt = StackUtil.getOrCreateNbtData(player.getHeldItem(hand));
			nbt.setIntArray(POS, new int[] { pos.getX(), pos.getY(), pos.getZ(), world.provider.getDimension() });
			String blockName = world.getBlockState(pos).getBlock().getLocalizedName();
			nbt.setString(BLOCK, blockName);
			IC2.platform.messagePlayer(player, "Coordinates set to " + blockName);
		}
		return EnumActionResult.SUCCESS;
	}

	public static EnumActionResult tryParseCoords(IGTCoordinateTile coordTile, World world, EntityPlayer player, EnumHand hand) {
		NBTTagCompound nbt = StackUtil.getNbtData(player.getHeldItem(hand));
		if (nbt.getIntArray(POS).length == 4) {
			int[] posArr = nbt.getIntArray(POS);
			if (!coordTile.isInterdimensional() && posArr[3] != world.provider.getDimension()) {
				IC2.platform.messagePlayer(player, "This machine does not support interdimensional communication");
				return EnumActionResult.SUCCESS;
			}
			ItemStack playerStack = player.getHeldItem(hand);
			if (coordTile.insertSensorStick(playerStack)) {
				coordTile.applyCoordinates(new BlockPos(posArr[0], posArr[1], posArr[2]), posArr[3]);
				player.getHeldItem(hand).shrink(1);
				IC2.platform.messagePlayer(player, "Sensor Stick successfully installed into machine!");
				return EnumActionResult.SUCCESS;
			} else {
				IC2.platform.messagePlayer(player, "Sensor Stick already found in machine");
				return EnumActionResult.SUCCESS;
			}
		}
		IC2.platform.messagePlayer(player, "Sensor Card cannot be installed in this!");
		return EnumActionResult.SUCCESS;
	}
}
