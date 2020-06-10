package gtclassic.common.tile;

import java.util.ArrayList;
import java.util.List;

import gtclassic.api.material.GTMaterialGen;
import gtclassic.api.tile.GTTileBaseRecolorableTile;
import gtclassic.common.GTBlocks;
import ic2.api.classic.network.adv.NetworkField;
import ic2.core.IC2;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.IClickable;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.world.EnumSkyBlock;
import net.minecraftforge.fml.relauncher.Side;

public class GTTileLamp extends GTTileBaseRecolorableTile implements ITickable, IClickable {

	@NetworkField(index = 10)
	public int redstoneLevel = -1;
	private boolean inverted;
	private boolean shouldUpdate;
	private static final String NBT_LEVEL = "redstoneLevel";
	private static final String NBT_INVERT = "inverted";

	public GTTileLamp() {
		super(0);
		this.addNetworkFields(new String[] { NBT_LEVEL });
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return false;
	}

	@Override
	public Block getBlockDrop() {
		return GTBlocks.tileLamp;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.inverted = nbt.getBoolean(NBT_INVERT);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setBoolean(NBT_INVERT, this.inverted);
		return nbt;
	}

	@Override
	public void onBlockUpdate(Block block) {
		this.shouldUpdate = true;
	}

	@Override
	public void update() {
		if (this.shouldUpdate || this.redstoneLevel == -1) {
			int oldlevel = this.redstoneLevel;
			int highLevel = this.getHighestRedstoneLevel();
			this.redstoneLevel = this.inverted ? 15 - highLevel : highLevel;
			this.setActive(this.redstoneLevel > 0);
			if (oldlevel != this.redstoneLevel) {
				this.getNetwork().updateTileEntityField(this, NBT_LEVEL);
			}
		}
		this.shouldUpdate = false;
	}

	@Override
	public void onNetworkUpdate(String field) {
		super.onNetworkUpdate(field);
		if (field.equals(NBT_LEVEL)) {
			this.world.markBlockRangeForRenderUpdate(this.getPos(), this.getPos());
			this.world.checkLightFor(EnumSkyBlock.BLOCK, this.getPos());
		}
	}

	private int getHighestRedstoneLevel() {
		int level = 0;
		for (EnumFacing side : EnumFacing.VALUES) {
			int newLevel = world.getRedstonePower(this.pos.offset(side), side);
			if (newLevel > level) {
				level = newLevel;
			}
		}
		return level;
	}

	@Override
	public List<ItemStack> getDrops() {
		List<ItemStack> drops = new ArrayList<>();
		ItemStack block = GTMaterialGen.get(getBlockDrop());
		if (this.isColored()) {
			NBTTagCompound nbt = StackUtil.getOrCreateNbtData(block);
			nbt.setInteger("color", this.color);
		}
		drops.add(block);
		return drops;
	}

	@Override
	public List<ItemStack> getInventoryDrops() {
		return getDrops();
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
		// Unused but needed for interface
	}

	@Override
	public boolean onRightClick(EntityPlayer player, EnumHand hand, EnumFacing enumFacing, Side side) {
		if (player.isSneaking() && player.getHeldItemMainhand().isEmpty()) {
			this.inverted = !this.inverted;
			this.shouldUpdate = true;
			if (this.isSimulating()) {
				String msg = this.inverted ? "Inverted" : "Not Inverted";
				IC2.platform.messagePlayer(player, msg);
			}
			player.playSound(SoundEvents.UI_BUTTON_CLICK, 1.0F, 1.0F);
			return true;
		}
		return false;
	}
}
