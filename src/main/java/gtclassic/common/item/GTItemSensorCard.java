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

public class GTItemSensorCard extends GTItemComponent {

	public static final String POS = "pos";
	public static final String BLOCK = "block";

	public GTItemSensorCard() {
		super("sensor_card", 9, 2);
		this.setMaxStackSize(1);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		NBTTagCompound nbt = StackUtil.getNbtData(stack);
		if (nbt.getIntArray(POS).length == 4) {
			tooltip.add("Sneak to apply coords to a machine");
			int[] pos = nbt.getIntArray(POS);
			tooltip.add(TextFormatting.YELLOW + I18n.format("Last Scanned: "));
			if (nbt.getString(BLOCK) != null) {
				tooltip.add(TextFormatting.BLUE + I18n.format(nbt.getString(BLOCK)));
			}
			tooltip.add(TextFormatting.BLUE + I18n.format("X: " + pos[0] + " Y: " + pos[1] + " Z: " + pos[2]));
			tooltip.add(TextFormatting.BLUE + I18n.format("Dimension: " + pos[3]));
		} else {
			tooltip.add("Click a block to scan it's coordinates");
		}
	}

	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX,
			float hitY, float hitZ, EnumHand hand) {
		if (IC2.platform.isRendering()) {
			IC2.audioManager.playOnce(player, Ic2Sounds.scannerUse);
			return EnumActionResult.SUCCESS;
		}
		if (player.isSneaking()) {
			return tryParseCoords(world, pos, player, hand);
		} else {
			NBTTagCompound nbt = StackUtil.getOrCreateNbtData(player.getHeldItem(hand));
			nbt.setIntArray(POS, new int[] { pos.getX(), pos.getY(), pos.getZ(), world.provider.getDimension() });
			nbt.setString(BLOCK, world.getBlockState(pos).getBlock().getLocalizedName());
		}
		return EnumActionResult.SUCCESS;
	}

	public static EnumActionResult tryParseCoords(World world, BlockPos pos, EntityPlayer player, EnumHand hand) {
		TileEntity tileEntity = world.getTileEntity(pos);
		NBTTagCompound nbt = StackUtil.getNbtData(player.getHeldItem(hand));
		if (tileEntity instanceof IGTCoordinateTile && nbt.getIntArray(POS).length == 4) {
			int[] posArr = nbt.getIntArray(POS);
			IGTCoordinateTile coordTile = (IGTCoordinateTile) tileEntity;
			if (!coordTile.isInterdimensional() && posArr[3] != world.provider.getDimension()) {
				IC2.platform.messagePlayer(player, "This machine does not support interdimensional communication");
				return EnumActionResult.SUCCESS;
			}
			if (coordTile.applyCoordinates(new BlockPos(posArr[0], posArr[1], posArr[2]), posArr[3])) {
				IC2.platform.messagePlayer(player, "Coordinates successfully parsed to machine!");
				return EnumActionResult.SUCCESS;
			}
		}
		IC2.platform.messagePlayer(player, "Parsing coordinates to this block failed!");
		return EnumActionResult.SUCCESS;
	}
}
