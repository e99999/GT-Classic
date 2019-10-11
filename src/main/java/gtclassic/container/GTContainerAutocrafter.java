package gtclassic.container;

import javax.annotation.Nullable;

import gtclassic.GTMod;
import gtclassic.helpers.GTHelperMath;
import gtclassic.helpers.GTHelperStack;
import gtclassic.tile.GTTileAutocrafter;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.slots.SlotBase;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.util.misc.StackUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTContainerAutocrafter extends ContainerTileComponent<GTTileAutocrafter> {

	private InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
	private InventoryCraftResult craftResult = new InventoryCraftResult();
	private final World world;
	private final EntityPlayer player;
	GTTileAutocrafter block;

	public GTContainerAutocrafter(InventoryPlayer player, GTTileAutocrafter tile) {
		super(tile);
		this.block = tile;
		this.world = player.player.getEntityWorld();
		this.player = player.player;
		// crafting result slot - 0
		this.addSlotToContainer(new SlotCrafting(this.player, craftMatrix, craftResult, 0, 143, 5));// slot 0
		// inventory - 1-9
		for (int k = 0; k < 3; ++k) {
			for (int l = 0; l < 3; ++l) {
				this.addSlotToContainer(new SlotBase(tile, (k + l * 3) + 1, 8 + l * 18, 5 + k * 18));
			}
		}
		// container output slots
		for (int l = 0; l < 9; ++l) {
			this.addSlotToContainer(new SlotOutput(player.player, tile, l + 10, 8 + l * 18, 60));
		}
		// output slot
		this.addSlotToContainer(new SlotOutput(player.player, tile, 19, 143, 41));
		// crafting slots
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				this.addSlotToContainer(new Slot(craftMatrix, 0 + (j + i * 3), 64 + j * 17, 6 + i * 17));
			}
		}
		this.addPlayerInventory(player, 0, 0);
		readTileCraftingList();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiLoaded(GuiIC2 gui) {
		gui.disableName();
		gui.dissableInvName();
	}

	@Override
	public void onCraftMatrixChanged(IInventory inventoryIn) {
		this.slotChangedCraftingGrid(this.world, this.player, this.craftMatrix, this.craftResult);
	}

	@Nullable
	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
		// GTMod.logger.info("Slot: " + slotId);
		if (clickTypeIn == ClickType.QUICK_MOVE && !(slotId > 9 && slotId < 20)) {
			return ItemStack.EMPTY;
		}
		if (slotId == 0) {
			return ItemStack.EMPTY;
		}
		if (GTHelperMath.within(slotId, 20, 28)) {
			ItemStack stack = player.inventory.getItemStack();
			// I need to offset the slot by -20 here because normal stack methods freak out
			this.craftMatrix.setInventorySlotContents(slotId - 20, doWeirdStackCraftingStuff(stack, slotId));
			writeTileCraftingList();
			return ItemStack.EMPTY;
		}
		return super.slotClick(slotId, dragType, clickTypeIn, player);
	}

	// this increases a stack size if its valid to handle stack crafting
	public ItemStack doWeirdStackCraftingStuff(ItemStack stack, int slotId) {
		if (stack.isEmpty()) {
			return ItemStack.EMPTY;
		}
		ItemStack slotStack = this.craftMatrix.getStackInSlot(slotId - 20);
		if (GTHelperStack.isEqual(stack, slotStack) && slotStack.getCount() < slotStack.getMaxStackSize()) {
			return StackUtil.copyWithSize(slotStack, slotStack.getCount() + 1);
		}
		return StackUtil.copyWithSize(stack, 1);
	}

	@Override
	public void onContainerClosed(EntityPlayer playerIn) {
		super.onContainerClosed(playerIn);
		writeTileCraftingList();
	}

	public void writeTileCraftingList() {
		this.block.craftingList.clear();
		// this is where the crafting slots are saved in the tile
		for (int i = 0; i < this.craftMatrix.getSizeInventory(); ++i) {
			ItemStack mSlot = this.craftMatrix.getStackInSlot(i);
			this.block.craftingList.set(i, mSlot);
		}
		// this is weird the key is set for the itemstack output in the tile
		this.block.target = this.craftResult.getStackInSlot(0);
		//GTMod.logger.info("Target Set To: " + this.block.target.getDisplayName() + " x "
		//		+ this.block.target.getCount());
	}

	public void readTileCraftingList() {
		for (int i = 0; i < this.craftMatrix.getSizeInventory(); ++i) {
			ItemStack mSlot = this.block.craftingList.get(i);
			this.craftMatrix.setInventorySlotContents(i, mSlot);
		}
	}

	@Override
	public ResourceLocation getTexture() {
		return this.getGuiHolder().getGuiTexture();
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return getGuiHolder().canInteractWith(player);
	}

	@Override
	public int guiInventorySize() {
		return this.getGuiHolder().slotCount;
	}
}
