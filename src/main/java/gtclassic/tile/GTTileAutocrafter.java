package gtclassic.tile;

import java.util.ArrayList;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.container.GTContainerAutocrafter;
import gtclassic.helpers.GTHelperMath;
import gtclassic.helpers.GTHelperStack;
import gtclassic.util.int3;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.inventory.transport.IItemTransporter;
import ic2.core.inventory.transport.TransporterManager;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileAutocrafter extends TileEntityElecMachine implements ITickable, IHasGui {

	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID, "textures/gui/autocrafter.png");
	public ItemStack target;
	public NonNullList<ItemStack> craftingList = NonNullList.<ItemStack>withSize(9, ItemStack.EMPTY);
	protected static final int[] slotInputs = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	protected static final int[] slotContainer = { 10, 11, 12, 13, 14, 15, 16, 17, 18 };
	protected static final int[] slotOutputs = { 19 };

	public GTTileAutocrafter() {
		super(29, 32);
		this.maxEnergy = 10000;
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Import, slotInputs);
		handler.registerDefaultSlotAccess(AccessRule.Export, slotOutputs);
		handler.registerDefaultSlotAccess(AccessRule.Export, slotContainer);
		handler.registerDefaultSlotsForSide(RotationList.DOWN.invert(), slotInputs);
		handler.registerDefaultSlotsForSide(RotationList.UP.invert(), slotOutputs);
		handler.registerDefaultSlotsForSide(RotationList.DOWN, slotContainer);
		handler.registerInputFilter(CommonFilters.Anything, slotInputs);
		handler.registerSlotType(SlotType.Input, slotInputs);
		handler.registerSlotType(SlotType.Output, slotOutputs);
		handler.registerSlotType(SlotType.Output, slotContainer);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList list = nbt.getTagList("Crafting", 10);
		for (int i = 0; i < list.tagCount(); ++i) {
			NBTTagCompound data = list.getCompoundTagAt(i);
			int slot = data.getInteger("Slot");
			if (slot >= 0 && slot < 9) {
				craftingList.set(slot, new ItemStack(data));
			}
		}
		this.target = new ItemStack(nbt.getCompoundTag("target"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < this.craftingList.size(); ++i) {
			if (i <= 9) {
				NBTTagCompound data = new NBTTagCompound();
				((ItemStack) craftingList.get(i)).writeToNBT(data);
				data.setInteger("Slot", i);
				list.appendTag(data);
			}
		}
		if (this.target != null) {
			nbt.setTag("target", target.writeToNBT(new NBTTagCompound()));
		}
		nbt.setTag("Crafting", list);
		return nbt;
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return !this.isInvalid();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerAutocrafter(player.inventory, this);
	}

	@Override
	public boolean hasGui(EntityPlayer player) {
		return true;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing() && facing.getAxis().isHorizontal();
	}

	@Override
	public void onGuiClosed(EntityPlayer arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update() {
		if (world.getTotalWorldTime() % 20 == 0) {
			tryImportItems();
			tryCondenseInventory();
			if (canAttemptToCraft() && this.inventory.get(19).isEmpty()) {
				/** this part copies the crafting inventory and removes empty stacks **/
				ArrayList<ItemStack> resourceList = new ArrayList<>();
				for (ItemStack stack : this.craftingList) {
					if (!stack.isEmpty()) {
						resourceList.add(stack.copy());
					}
				}
				/** this part condenses and compacts similar ItemStacks **/
				ArrayList<ItemStack> finalList = new ArrayList<>();
				GTHelperStack.mergeItems(finalList, resourceList);
				/**
				 * this part iterates the stored inventory and final list too see if the recipe
				 * has what it needs
				 **/
				int matched = 0;
				int needed = finalList.size();
				ArrayList<ItemStack> alreadyCounted = new ArrayList<>();
				for (ItemStack neededStack : finalList) {
					for (ItemStack storedStack : storedInventory()) {
						if (GTHelperStack.isEqual(storedStack, neededStack)
								&& GTHelperStack.contains(alreadyCounted, storedStack) == -1 
								&& (storedStack.getCount() >= neededStack.getCount())) {
							alreadyCounted.add(storedStack);
							matched = matched + 1;
						}
					}
				}
				if (matched >= needed) {
					/**
					 * this is where i reduce the stack size of the stored stuff, at this point it
					 * is certain the inventory has whats need to craft
					 **/
					ArrayList<ItemStack> alreadySubtracted = new ArrayList<>();
					boolean canContinue = true; // disables moving forward for container item stuff
					for (ItemStack neededStack : finalList) {
						for (int i = 1; i < 10; ++i) {
							ItemStack input = this.inventory.get(i);
							if (GTHelperStack.isEqual(input, neededStack)
									&& GTHelperStack.contains(alreadySubtracted, neededStack) == -1) {
								/** Handling container items **/
								if (input.getItem().hasContainerItem(input) || neededStack.getItem().hasContainerItem(neededStack)) {
									if (roomForContainerItems() == 0) {
										canContinue = false;
										return;
									}
									ItemStack container = input.getItem().getContainerItem(input);
									for (int k = 0; k < 9; ++k) {
										int m = k + 10;
										if (this.inventory.get(m).isEmpty() && canContinue) {
											this.inventory.set(m, container.copy());
											break;
										}
									}
								}
								/** Now begin shrinking stacks **/
								if (canContinue) {
									alreadySubtracted.add(neededStack);
									input.shrink(neededStack.getCount());
								}
							}
						}
					}
					if (canContinue) {
						this.setStackInSlot(19, target.copy());
						this.useEnergy(50);
					}
				}
			}
			if (!this.inventory.get(19).isEmpty()) {
				tryExportItems();
			}
		}
	}

	public List<ItemStack> storedInventory() {
		ArrayList<ItemStack> storedList = new ArrayList<>();
		for (int i = 1; i < 10; ++i) {
			if (!this.inventory.get(i).isEmpty()) {
				storedList.add(this.inventory.get(i));
			}
		}
		return storedList;
	}

	public int roomForContainerItems() {
		int emptySlots = 0;
		for (int i = 10; i < 19; ++i) {
			if (this.inventory.get(i).isEmpty()) {
				emptySlots = emptySlots + 1;
			}
		}
		return emptySlots;
	}

	public void tryCondenseInventory() {
		for (int i = 1; i < 10; ++i) {
			for (int j = 1; j < 10; ++j) {
				ItemStack stack1 = this.inventory.get(i);
				ItemStack stack2 = this.inventory.get(j);
				if (!stack1.isEmpty() && !stack2.isEmpty()) {
					if (GTHelperStack.isEqual(stack1, stack2) && stack1.getCount() < stack1.getMaxStackSize()) {
						int max = stack1.getMaxStackSize() - stack1.getCount();
						int available = stack2.getCount();
						int size = GTHelperMath.clip(available, 1, max);
						stack1.grow(size);
						stack2.shrink(size);
					}
				}
			}
		}
	}

	public boolean canAttemptToCraft() {
		// if there is valid target to craft return
		if (this.target == null || this.target.isEmpty()) {
			return false;
		}
		// if the list of ingredients is empty return
		if (craftingList.stream().allMatch(e -> e.equals(ItemStack.EMPTY))) {
			return false;
		}
		// if there is not enough power return
		if (this.energy < 50) {
			return false;
		}
		return true;
	}

	public TileEntity getImportTile() {
		int3 dir = new int3(getPos(), getFacing());
		return world.getTileEntity(dir.left(1).asBlockPos());
	}

	public TileEntity getExportTile() {
		int3 dir = new int3(getPos(), getFacing());
		return world.getTileEntity(dir.right(1).asBlockPos());
	}

	@SuppressWarnings("static-access")
	public void tryImportItems() {
		IItemTransporter slave = TransporterManager.manager.getTransporter(getImportTile(), true);
		if (slave == null) {
			return;
		}
		IItemTransporter controller = TransporterManager.manager.getTransporter(this, true);
		int limit = 64;
		for (int i = 0; i < limit; ++i) {
			ItemStack stack = slave.removeItem(CommonFilters.Anything, getFacing().getOpposite(), 1, false);
			if (stack.isEmpty()) {
				break;
			}
			ItemStack added = controller.addItem(stack, getFacing().UP, true);
			if (added.getCount() <= 0) {
				break;
			}
			slave.removeItem(CommonFilters.Anything, getFacing().getOpposite(), 1, true);
		}
	}

	@SuppressWarnings("static-access")
	public void tryExportItems() {
		IItemTransporter slave = TransporterManager.manager.getTransporter(getExportTile(), true);
		if (slave == null) {
			return;
		}
		IItemTransporter controller = TransporterManager.manager.getTransporter(this, true);
		int limit = 64;
		for (int i = 0; i < limit; ++i) {
			ItemStack stack = controller.removeItem(CommonFilters.Anything, getFacing().EAST, 1, false);
			if (stack.isEmpty()) {
				break;
			}
			ItemStack added = slave.addItem(stack, getFacing().UP, true);
			if (added.getCount() <= 0) {
				break;
			}
			controller.removeItem(CommonFilters.Anything, getFacing().getOpposite(), 1, true);
		}
	}

	@Override
	public boolean supportsNotify() {
		return true;
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	public ResourceLocation getGuiTexture() {
		return GUI_LOCATION;
	}
}
