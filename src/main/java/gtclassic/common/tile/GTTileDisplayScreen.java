package gtclassic.common.tile;

import java.util.Map;

import org.lwjgl.opengl.GL11;

import gtclassic.api.interfaces.IGTCoordinateTile;
import gtclassic.api.interfaces.IGTDebuggableTile;
import ic2.api.classic.network.adv.NetworkField;
import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.obj.IClickable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

public class GTTileDisplayScreen extends TileEntityMachine
		implements IGTDebuggableTile, IGTCoordinateTile, IClickable, ITickable {

	private BlockPos targetPos;
	private static final String NBT_TARGETPOS = "targetPos";
	@NetworkField(index = 3)
	public String testData = "No Data"; // TODO make this a list brother

	public GTTileDisplayScreen() {
		super(1);
		this.addNetworkFields("testData");
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		if (nbt.hasKey(NBT_TARGETPOS)) {
			this.targetPos = NBTUtil.getPosFromTag(nbt.getCompoundTag(NBT_TARGETPOS));
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		if (this.targetPos != null) {
			nbt.setTag(NBT_TARGETPOS, NBTUtil.createPosTag(targetPos));
		}
		return nbt;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing() && facing.getAxis().isHorizontal();
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	public double getWrenchDropRate() {
		return 1.0D;
	}

	@SideOnly(Side.CLIENT)
	public void applySize(float size, double posX, double posY, double posz) {
		GL11.glTranslated(0.0D, 0.0D, posz);
		GL11.glScalef(0.0039063F, 0.0039063F, -1.0E-004F);
		GL11.glTranslated(posX, posY, 0.0D);
		GL11.glScalef(size, size, 1.0F);
	}

	@SideOnly(Side.CLIENT)
	public void applyRotation(float x, float y, float z) {
		GL11.glTranslatef((float) x + 0.5f, (float) y + 0.5f, (float) z + 0.5f);
		switch (this.facing) {
		case 2:
			GL11.glRotatef(0, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(180F, 0F, 0F, 1F);
			break;
		case 3:
			GL11.glRotatef(180, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(180F, 0F, 0F, 1F);
			break;
		case 4:
			GL11.glRotatef(90, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(180, 0.0F, 0F, 1.0F);
			break;
		case 5:
			GL11.glRotatef(270, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(180, 0.0F, 0F, 1.0F);
			break;
		}
		GL11.glTranslated(-0.5D, -0.5D, -0.5D);
	}

	@Override
	public void update() {
		if (world.getTotalWorldTime() % 10 == 0) {
			if (this.targetPos != null) {
				String name = world.getBlockState(this.targetPos).getBlock().getLocalizedName();
				this.testData = formatTextForScreen(name);
			} else {
				this.testData = "No Data";
			}
			this.getNetwork().updateTileEntityField(this, "testData");
		}
	}

	public static String formatTextForScreen(String text) {
		return text.length() > 15 ? text.substring(0, 15) + "..." : text;
	}

	@Override
	public boolean applyCoordinates(BlockPos pos, int dimensionId) {
		if (!pos.toString().equals(this.pos.toString())) {
			this.targetPos = pos;
			return true;
		}
		return false;
	}

	@Override
	public boolean isInterdimensional() {
		return false;
	}

	@Override
	public BlockPos getAppliedPos() {
		return this.targetPos;
	}

	@Override
	public int getAppliedDimId() {
		return this.world.provider.getDimension();
	}

	@Override
	public boolean hasLeftClick() {
		return false;
	}

	@Override
	public boolean hasRightClick() {
		return true;
	}

	@Override
	public void onLeftClick(EntityPlayer arg0, Side arg1) {
		// neeeded by interface, unused by tile
	}

	@Override
	public boolean onRightClick(EntityPlayer player, EnumHand arg1, EnumFacing arg2, Side arg3) {
		ItemStack slotStack = this.getStackInSlot(0);
		if (slotStack.isEmpty() || !player.isSneaking()) {
			return false;
		}
		ItemHandlerHelper.giveItemToPlayer(player, slotStack.copy());
		slotStack.shrink(1);
		this.resetScreen();
		IC2.audioManager.playOnce(player, Ic2Sounds.wrenchUse);
		return true;
	}

	public void resetScreen() {
		this.targetPos = null;
	}

	@Override
	public boolean insertSensorStick(ItemStack card) {
		if (!this.getStackInSlot(0).isEmpty()) {
			return false;
		}
		this.setStackInSlot(0, card.copy());
		return true;
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		// 3 and 4 are correct, 5 and 2 are fucked
		data.put("Facing: " + this.getFacing().toString().toUpperCase(), true);
		data.put("Facing Int : " + this.facing, true);
		data.put(this.testData, true);
	}
}
