package gtclassic.common.tile;

import java.util.ArrayList;
import java.util.List;

import gtclassic.api.material.GTMaterialGen;
import gtclassic.api.tile.GTTileBaseRecolorableTile;
import gtclassic.common.GTBlocks;
import ic2.api.classic.network.adv.NetworkField;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.world.EnumSkyBlock;

public class GTTileLamp extends GTTileBaseRecolorableTile implements ITickable {

	@NetworkField(index = 10)
	public int redstoneLevel = -1;
	private boolean shouldUpdate;
	private static final String NBT_LEVEL = "redstoneLevel";

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
	public void onBlockUpdate(Block block) {
		this.shouldUpdate = true;
	}

	@Override
	public void update() {
		if (this.shouldUpdate || this.redstoneLevel == -1) {
			int oldlevel = this.redstoneLevel;
			this.redstoneLevel = this.getHighestRedstoneLevel();
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
}
