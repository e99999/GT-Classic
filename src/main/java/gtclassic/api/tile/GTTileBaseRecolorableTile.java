package gtclassic.api.tile;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import gtclassic.api.interfaces.IGTItemContainerTile;
import gtclassic.api.interfaces.IGTRecolorableStorageTile;
import gtclassic.api.material.GTMaterialGen;
import ic2.api.classic.network.adv.NetworkField;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public abstract class GTTileBaseRecolorableTile extends TileEntityMachine
		implements IGTRecolorableStorageTile, IGTItemContainerTile {

	@NetworkField(index = 9)
	public int color;
	private static final String NBT_COLOR = "color";

	public GTTileBaseRecolorableTile(int slots) {
		super(slots);
		this.color = 16383998;
		this.addNetworkFields(new String[] { NBT_COLOR });
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		if (nbt.hasKey(NBT_COLOR)) {
			this.color = nbt.getInteger(NBT_COLOR);
		} else {
			this.color = 16383998;
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger(NBT_COLOR, this.color);
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

	@Override
	public void setTileColor(int color) {
		this.color = color;
	}

	@Override
	public Color getTileColor() {
		return new Color(this.color);
	}

	@Override
	public boolean isColored() {
		return this.color != 16383998;
	}

	public abstract Block getBlockDrop();

	@Override
	public List<ItemStack> getDrops() {
		List<ItemStack> drops = new ArrayList<>();
		ItemStack block = GTMaterialGen.get(getBlockDrop());
		if (this.isColored()) {
			NBTTagCompound nbt = StackUtil.getOrCreateNbtData(block);
			nbt.setInteger(NBT_COLOR, this.color);
		}
		drops.addAll(getInventoryDrops());
		drops.add(block);
		return drops;
	}

	@Override
	public List<ItemStack> getInventoryDrops() {
		List<ItemStack> drops = new ArrayList<>();
		for (ItemStack stack : this.inventory) {
			if (!stack.isEmpty()) {
				drops.add(stack);
			}
		}
		return drops;
	}

	@Override
	public void onNetworkUpdate(String field) {
		if (field.equals(NBT_COLOR)) {
			this.world.markBlockRangeForRenderUpdate(this.getPos(), this.getPos());
		}
		super.onNetworkUpdate(field);
	}
}
