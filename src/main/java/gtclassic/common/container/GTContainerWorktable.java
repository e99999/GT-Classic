package gtclassic.common.container;

import gtclassic.api.helpers.GTHelperStack;
import gtclassic.common.container.inventory.GTInventoryCraftResult;
import gtclassic.common.container.inventory.GTInventoryCrafting;
import gtclassic.common.gui.GTGuiCompWorktable;
import gtclassic.common.tile.GTTileWorktable;
import gtclassic.common.util.GTIFilters;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.slots.SlotBase;
import ic2.core.inventory.slots.SlotCustom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTContainerWorktable extends ContainerTileComponent<GTTileWorktable> {

	private GTInventoryCrafting craftMatrix;
	public GTInventoryCraftResult craftResult = new GTInventoryCraftResult();
	private final World world;
	private final EntityPlayer player;
	private GTTileWorktable block;
	public IFilter toolFilter = new GTIFilters.ToolFilter();

	public GTContainerWorktable(InventoryPlayer player, GTTileWorktable tile) {
		super(tile);
		craftMatrix = new GTInventoryCrafting(this, tile);
		this.block = tile;
		this.world = player.player.getEntityWorld();
		this.player = player.player;
		// crafting output slot
		this.addSlotToContainer(new SlotCrafting(this.player, craftMatrix, craftResult, 0, 136, 46));// slot 0
		// main inventory
		int k;
		for (k = 0; k < 4; ++k) {
			for (int l = 0; l < 4; ++l) {
				this.addSlotToContainer(new SlotBase(tile, (k + l * 4) + 1, 8 + l * 18, 8 + k * 18));
			}
		}
		// crafting slots
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				this.addSlotToContainer(new Slot(craftMatrix, (j + i * 3), 82 + j * 18, 28 + i * 18));
			}
		}

		// tool slots
		for (k = 0; k < 5; k++) {
			this.addSlotToContainer(new SlotCustom(tile, k + 17, 82 + (k * 18), 8, toolFilter));
		}
		// park slot
		this.addSlotToContainer(new SlotBase(tile, 22, 154, 46));
		this.addComponent(new GTGuiCompWorktable(tile, this));
		this.addPlayerInventory(player, 0, 0);
		readTileCraftingList();
		this.block.inUse = true;
		this.block.setActive(true);
	}

	@Override
	public void onCraftMatrixChanged(IInventory inventoryIn) {
		this.slotChangedCraftingGrid(this.world, this.player, this.craftMatrix, this.craftResult);
	}

	public void onButtonClick(int event) {
		if (event == 2) {
			if (block.inUse) {
				for (int j = 17; j < 26; j++) {
					Slot slot = getSlot(j);
					ItemStack stack = slot.getStack();
					if (stack.isEmpty()) {
						continue;
					}
					stack = insert(j, stack, 59, 67);
					if (stack.isEmpty()) {
						continue;
					}
					insert(j, stack, 32, 58);
				}
			}
		}
		if (event == 1) {
			if (block.inUse) {
				for (int j = 17; j < 26; j++) {
					Slot slot = getSlot(j);
					ItemStack stack = slot.getStack();
					if (stack.isEmpty()) {
						continue;
					}
					insert(j, stack, 1, 16);
				}
			}
		}
	}

	ItemStack insert(int slot, ItemStack aStack, final int firstSlot, final int lastSlot) {
		ItemStack craftingStack = aStack;
		int curSlot = firstSlot;
		int maxStackSize = craftingStack.getMaxStackSize();
		int count = craftingStack.getCount();
		int room;
		int toDeliver;
		// Try to first insert into same ItemStacks
		while (curSlot <= lastSlot && count > 0) {
			ItemStack slotStack = this.getStackInSlot(curSlot);
			if (craftingStack.isEmpty()) {
				count = 0;
			}
			if (GTHelperStack.isEqual(craftingStack, slotStack) && slotStack.getCount() < maxStackSize) {
				room = maxStackSize - slotStack.getCount();
				toDeliver = Math.min(room, count);
				slotStack.grow(toDeliver);
				this.setStackInSlot(curSlot, slotStack);
				craftingStack.grow(-toDeliver);
				setStackInSlot(slot, craftingStack);
				if (count >= room) {
					count -= room;
				}
			}
			curSlot++;
		}
		curSlot = firstSlot;
		// Try to deliver into empty slot
		while (curSlot <= lastSlot && count > 0) {
			if (this.getStackInSlot(curSlot).isEmpty()) {
				this.setStackInSlot(curSlot, craftingStack.copy());
				craftingStack.grow(-count);
				setStackInSlot(slot, craftingStack);
				count = 0;
			}
			curSlot++;
		}
		return craftingStack;
	}

	public ItemStack getStackInSlot(int slot) {
		return getSlot(slot).getStack();
	}

	public void setStackInSlot(int slot, ItemStack stack) {
		getSlot(slot).putStack(stack);
	}

	@Override
	public void onContainerClosed(EntityPlayer playerIn) {
		super.onContainerClosed(playerIn);
		writeTileCraftingList();
	}

	public void writeTileCraftingList() {
		this.block.craftingInventory.clear();
		for (int i = 0; i < this.craftMatrix.getSizeInventory(); ++i) {
			ItemStack mSlot = this.craftMatrix.getStackInSlot(i);
			this.block.craftingInventory.set(i, mSlot);
		}
	}

	public void readTileCraftingList() {
		for (int i = 0; i < this.craftMatrix.getSizeInventory(); ++i) {
			ItemStack mSlot = this.block.craftingInventory.get(i);
			this.craftMatrix.setInventorySlotContents(i, mSlot);
		}
	}

	// Unused hopefully this can be of use for server support
	public boolean isCurrentListEqual() {
		NonNullList<ItemStack> copyList = NonNullList.<ItemStack>withSize(9, ItemStack.EMPTY);
		for (int i = 0; i < this.craftMatrix.getSizeInventory(); ++i) {
			ItemStack mSlot = this.craftMatrix.getStackInSlot(i);
			copyList.set(i, mSlot);
		}
		return copyList.equals(this.block.craftingInventory);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiLoaded(GuiIC2 gui) {
		gui.disableName();
		gui.dissableInvName();
	}

	@Override
	public ResourceLocation getTexture() {
		return getGuiHolder().getGuiTexture();
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return getGuiHolder().canInteractWith(player);
	}

	@Override
	public int guiInventorySize() {
		return 26;
	}
}
