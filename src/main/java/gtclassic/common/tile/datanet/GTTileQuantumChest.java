package gtclassic.common.tile.datanet;

import java.util.ArrayList;
import java.util.List;

import gtclassic.api.helpers.GTHelperStack;
import gtclassic.api.interfaces.IGTItemContainerTile;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.common.GTBlocks;
import gtclassic.common.GTLang;
import gtclassic.common.container.GTContainerQuantumChest;
import gtclassic.common.util.datanet.GTDataNet.DataType;
import gtclassic.common.util.datanet.GTFilterQuantumChest;
import ic2.core.RotationList;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileQuantumChest extends GTTileOutputNodeBase implements IHasGui, ITickable, IGTItemContainerTile {

	public GTFilterQuantumChest filter = new GTFilterQuantumChest(this);
	int slotInput = 0;
	int slotOutput = 1;
	int slotDisplay = 2;
	int maxSize = Integer.MAX_VALUE;
	int digitalCount;
	ItemStack display;
	public static final String NBT_DIGITALCOUNT = "digitalCount";
	public static final String NBT_DISPLAYITEM = "display";

	public GTTileQuantumChest() {
		super(3);
		this.digitalCount = 0;
		this.addGuiFields(new String[] { NBT_DIGITALCOUNT, NBT_DISPLAYITEM });
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.UP.invert());
		handler.registerDefaultSlotAccess(AccessRule.Import, 0);
		handler.registerDefaultSlotAccess(AccessRule.Export, 1);
		handler.registerDefaultSlotsForSide(RotationList.DOWN.invert(), 0);
		handler.registerDefaultSlotsForSide(RotationList.UP.invert(), 1);
		handler.registerInputFilter(new GTFilterQuantumChest(this), slotInput);
		handler.registerSlotType(SlotType.Input, 0);
		handler.registerSlotType(SlotType.Output, 1);
	}

	@Override
	public int getMaxStackSize(int slot) {
		return (slot == 2) ? 1 : 64;
	}

	public void updateGui() {
		this.getNetwork().updateTileGuiField(this, NBT_DIGITALCOUNT);
		this.display = StackUtil.copyWithSize(this.getStackInSlot(slotDisplay), 1);
		this.getNetwork().updateTileGuiField(this, NBT_DISPLAYITEM);
	}

	@Override
	public LocaleComp getBlockName() {
		return GTLang.QUANTUM_CHEST;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.digitalCount = nbt.getInteger(NBT_DIGITALCOUNT);
		this.display = new ItemStack(nbt.getCompoundTag(NBT_DISPLAYITEM));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger(NBT_DIGITALCOUNT, this.digitalCount);
		if (this.display != null) {
			nbt.setTag(NBT_DISPLAYITEM, display.writeToNBT(new NBTTagCompound()));
		}
		return nbt;
	}

	@Override
	public List<ItemStack> getDrops() {
		List<ItemStack> list = new ArrayList<>();
		ItemStack stack = GTMaterialGen.get(GTBlocks.tileQuantumChest);
		if (this.display != null && this.digitalCount > 0) {
			StackUtil.getOrCreateNbtData(stack).setTag(NBT_DISPLAYITEM, display.writeToNBT(new NBTTagCompound()));
			StackUtil.getOrCreateNbtData(stack).setInteger(NBT_DIGITALCOUNT, this.digitalCount);
		}
		list.add(stack);
		list.addAll(getInventoryDrops());
		return list;
	}

	@Override
	public List<ItemStack> getInventoryDrops() {
		List<ItemStack> list = new ArrayList<>();
		list.add(this.getStackInSlot(slotInput));
		list.add(this.getStackInSlot(slotOutput));
		return list;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerQuantumChest(player.inventory, this);
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
	public boolean canInteractWith(EntityPlayer player) {
		return !this.isInvalid();
	}

	@Override
	public void onGuiClosed(EntityPlayer entityPlayer) {
		// needed for construction
	}

	@Override
	public boolean hasGui(EntityPlayer player) {
		return true;
	}

	public int getQuantumCount() {
		return this.digitalCount;
	}

	@Override
	public void update() {
		inputLogic();
		outputLogic();
	}

	/** Logic for stack input **/
	public void inputLogic() {
		if (input().isEmpty()) {
			return;
		}
		if (canDigitizeStack(input())) {
			display(input());
			this.digitalCount = this.digitalCount + input().getCount();
			input(ItemStack.EMPTY);
			updateGui();
		}
	}

	/** Logic for stack output **/
	public void outputLogic() {
		if (!display().isEmpty() && GTHelperStack.canOutputStack(this, display(), slotOutput)) {
			int max = display().getMaxStackSize();
			int amount = this.digitalCount >= max ? max : this.digitalCount;
			// if output is empty set the stack
			if (output().isEmpty()) {
				output(StackUtil.copyWithSize(display(), amount));
				this.digitalCount = this.digitalCount - amount;
				// if can merge but not empty grow the stack
			} else {
				int difference = output().getMaxStackSize() - output().getCount();
				int count = this.digitalCount >= difference ? difference : this.digitalCount;
				output().grow(count);
				this.digitalCount = this.digitalCount - count;
			}
			emptyCheck();
			updateGui();
		}
	}

	public ItemStack input() {
		return this.getStackInSlot(slotInput);
	}

	public void input(ItemStack stack) {
		this.setStackInSlot(slotInput, stack);
	}

	public ItemStack display() {
		return this.getStackInSlot(slotDisplay);
	}

	public void display(ItemStack stack) {
		this.setStackInSlot(slotDisplay, StackUtil.copyWithSize(stack, 1));
	}

	public ItemStack output() {
		return this.getStackInSlot(slotOutput);
	}

	public void output(ItemStack stack) {
		this.setStackInSlot(slotOutput, stack);
	}

	public void emptyCheck() {
		if (this.digitalCount == 0) {
			display(ItemStack.EMPTY);
		}
	}

	/** Checks to see if the given stack can go into the digital storage **/
	public boolean canDigitizeStack(ItemStack stack) {
		if (display().isEmpty() && this.digitalCount == 0) {
			return true;
		}
		return (this.digitalCount + stack.getCount() <= maxSize) && GTHelperStack.isEqual(display(), stack);
	}

	/** Sets the digital count, called when the block is placed for NBT stuff **/
	public void setDigtialCount(int count) {
		this.digitalCount = count;
	}

	public boolean canFit(int count) {
		return count + this.digitalCount <= Integer.MAX_VALUE;
	}

	@Override
	public DataType dataType() {
		return DataType.ITEM;
	}

	@Override
	public BlockPos inventoryPos() {
		return this.getPos();
	}

	@Override
	public EnumFacing inventoryFacing() {
		return EnumFacing.UP;
	}
}
