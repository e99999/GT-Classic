package gtclassic.api.pipe;

import java.util.ArrayList;
import java.util.List;

import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.api.interfaces.IGTMonkeyWrenchTile;
import ic2.api.classic.network.adv.NetworkField;
import ic2.core.IC2;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.inventory.base.IHasInventory;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.IHasHandler;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.item.misc.ItemDisplayIcon;
import ic2.core.util.math.MathUtil;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.IItemContainer;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public abstract class GTTilePipeBase extends TileEntityBlock
		implements IHasInventory, IHasHandler, IItemContainer, ITickable, IGTDebuggableTile, IGTMonkeyWrenchTile {

	@NetworkField(index = 8)
	public RotationList connection;
	public EnumFacing blacklistSide;
	public boolean onlyPipes;
	public ItemStack drop;
	protected InventoryHandler handler = new InventoryHandler(this);
	public NonNullList<ItemStack> inventory;
	public int slotCount;
	@NetworkField(index = 9)
	public int color;
	public static final int TICK_RATE = 10;
	public static final String NBT_SIDE_BLACKLIST = "blacklistside";
	public static final String NBT_DROP = "drop";
	public static final String NBT_COLOR = "color";
	public static final String NBT_PIPEMODE = "onlyPipes";
	public static final String NBT_CONNECTION = "connection";

	public GTTilePipeBase(int slots) {
		this.onlyPipes = false;
		this.color = 16383998;
		this.connection = RotationList.EMPTY;
		this.addNetworkFields(new String[] { NBT_CONNECTION, NBT_COLOR });
		this.slotCount = slots;
		this.inventory = NonNullList.withSize(this.slotCount, ItemStack.EMPTY);
		this.addSlots(this.handler);
		this.handler.validateSlots();
	}

	protected void addSlots(InventoryHandler handler) {
		int[] array = MathUtil.fromTo(0, this.inventory.size());
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Both, array);
		handler.registerDefaultSlotsForSide(RotationList.ALL, array);
		handler.registerSlotType(SlotType.Storage, array);
	}

	public abstract boolean canConnect(TileEntity tile, EnumFacing dir);

	@Override
	public InventoryHandler getHandler() {
		return this.handler;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.drop = new ItemStack(nbt.getCompoundTag(NBT_DROP));
		this.color = nbt.getInteger(NBT_COLOR);
		this.onlyPipes = nbt.getBoolean(NBT_PIPEMODE);
		this.handler.readFromNBT(nbt.getCompoundTag("HandlerNBT"));
		this.inventory = NonNullList.withSize(this.slotCount, ItemStack.EMPTY);
		if (nbt.getInteger(NBT_SIDE_BLACKLIST) != -1) {
			this.blacklistSide = EnumFacing.getFront(nbt.getInteger(NBT_SIDE_BLACKLIST));
		}
		NBTTagList list = nbt.getTagList("Items", 10);
		for (int i = 0; i < list.tagCount(); ++i) {
			NBTTagCompound data = list.getCompoundTagAt(i);
			byte byte0 = data.getByte("Slot");
			if (byte0 >= 0 && byte0 < this.inventory.size()) {
				this.inventory.set(byte0, new ItemStack(data));
			}
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger(NBT_COLOR, this.color);
		if (this.drop != null) {
			nbt.setTag(NBT_DROP, drop.writeToNBT(new NBTTagCompound()));
		}
		nbt.setBoolean(NBT_PIPEMODE, this.onlyPipes);
		nbt.setInteger(NBT_SIDE_BLACKLIST, blacklistSide != null ? this.blacklistSide.getIndex() : -1);
		this.handler.writeToNBT(this.getTag(nbt, "HandlerNBT"));
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < this.inventory.size(); ++i) {
			ItemStack stack = this.inventory.get(i);
			if (!stack.isEmpty()) {
				NBTTagCompound data = new NBTTagCompound();
				data.setByte("Slot", (byte) i);
				stack.writeToNBT(data);
				list.appendTag(data);
			}
		}
		nbt.setTag("Items", list);
		return nbt;
	}

	@Override
	public List<ItemStack> getDrops() {
		List<ItemStack> list = new ArrayList<>(this.inventory.size());
		for (int i = 0; i < this.inventory.size(); ++i) {
			ItemStack stack = this.inventory.get(i);
			if (!stack.isEmpty() && !(stack.getItem() instanceof ItemDisplayIcon)) {
				list.add(stack);
			}
		}
		ItemStack newDrop = drop.copy();
		if (this.isColored()) {
			NBTTagCompound nbt = StackUtil.getOrCreateNbtData(newDrop);
			nbt.setInteger(NBT_COLOR, this.color);
		}
		list.add(newDrop.copy());
		return list;
	}

	@Override
	public boolean onMonkeyWrench(EntityPlayer player, World world, BlockPos pos, EnumFacing side, EnumHand hand) {
		if (player.isSneaking()) {
			EnumFacing newSide = side.getOpposite();
			if (this.blacklistSide == newSide) {
				this.blacklistSide = null;
				if (this.isSimulating()) {
					String msg = "Pipe will flow into any direction";
					IC2.platform.messagePlayer(player, msg);
					return true;
				}
			}
			this.blacklistSide = newSide;
			if (this.isSimulating()) {
				String msg = "Pipe will not flow: " + newSide.toString().toUpperCase();
				IC2.platform.messagePlayer(player, msg);
				IC2.platform.messagePlayer(player, "You are Facing: " + newSide.toString().toUpperCase());
			}
			return true;
		}
		this.togglePipeOnlyMode();
		if (this.isSimulating()) {
			String msg = this.onlyPipes ? "Will only flow into pipes" : "Will flow into any connection";
			IC2.platform.messagePlayer(player, msg);
		}
		return true;
	}

	public Vec3i getConnections() {
		return new Vec3i(this.connection.getCode(), 0, 0);
	}

	@Override
	public void onLoaded() {
		super.onLoaded();
		updateConnections();
	}

	public void updateConnections() {
		for (EnumFacing facing : EnumFacing.VALUES) {
			BlockPos sidedPos = pos.offset(facing);
			if (world.isBlockLoaded(sidedPos)) {
				world.neighborChanged(sidedPos, Blocks.AIR, pos);
			}
		}
		if (world.isBlockLoaded(pos)) {
			world.neighborChanged(pos, Blocks.AIR, pos);
		}
	}

	@Override
	public boolean canUpdate() {
		return this.isSimulating();
	}

	@Override
	public void onBlockUpdate(Block block) {
		super.onBlockUpdate(block);
		if (!this.isRendering()) {
			RotationList newList = RotationList.EMPTY;
			EnumFacing[] var3 = EnumFacing.VALUES;
			int var4 = var3.length;
			for (int var5 = 0; var5 < var4; ++var5) {
				EnumFacing dir = var3[var5];
				TileEntity tile = this.getWorld().getTileEntity(this.getPos().offset(dir));
				if (canConnect(tile, dir)) {
					newList = newList.add(dir);
				}
			}
			if (this.connection.getCode() != newList.getCode()) {
				this.connection = newList;
				this.getNetwork().updateTileEntityField(this, NBT_CONNECTION);
			}
		}
	}

	@Override
	public void onNetworkUpdate(String field) {
		if (field.equals(NBT_CONNECTION) || field.equals(NBT_COLOR)) {
			this.world.markBlockRangeForRenderUpdate(this.getPos(), this.getPos());
		}
		super.onNetworkUpdate(field);
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	public abstract boolean isEmpty();

	public void update() {
		if (world.getTotalWorldTime() % TICK_RATE == 0) {
			if (isEmpty()) {
				return;
			}
			onPipeTick();
		}
	}

	public abstract void onPipeTick();

	public void togglePipeOnlyMode() {
		this.onlyPipes = !this.onlyPipes;
	}

	public boolean isBlacklistSide(EnumFacing side) {
		if (blacklistSide == null || blacklistSide.getIndex() == -1) {
			return false;
		}
		return side == blacklistSide;
	}

	@Override
	public void setStackInSlot(int slot, ItemStack stack) {
		this.inventory.set(slot, stack);
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.inventory.get(slot);
	}

	@Override
	public int getSlotCount() {
		return this.slotCount;
	}

	@Override
	public int getMaxStackSize(int slot) {
		return 64;
	}

	@Override
	public boolean canInsert(int slot, ItemStack stack) {
		return true;
	}

	public boolean isColored() {
		return this.color != 16383998;
	}
}
