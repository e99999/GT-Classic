package gtclassic.container;

import gtclassic.tile.GTTileAutocrafter;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.slots.SlotBase;
import ic2.core.inventory.slots.SlotGhoest;
import ic2.core.inventory.slots.SlotOutput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
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
		// holo slots - unused atm 10-19
		for (int l = 0; l < 9; ++l) {
			this.addSlotToContainer(new SlotGhoest(tile, l + 10, 8 + l * 18, 60));
		}
		// output slot
		this.addSlotToContainer(new SlotOutput(player.player, tile, 20, 143, 41));
		// crafting slots
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				this.addSlotToContainer(new Slot(craftMatrix, 0 + (j + i * 3), 64 + j * 17, 6 + i * 17));
			}
		}
		this.addPlayerInventory(player, 0, 0);
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
