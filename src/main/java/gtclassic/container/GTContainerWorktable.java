package gtclassic.container;

import gtclassic.GTMod;
import gtclassic.gui.GTGuiCompBasicString;
import gtclassic.tile.GTTileWorktable;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.slots.SlotBase;
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

public class GTContainerWorktable extends ContainerTileComponent<GTTileWorktable> {

	private InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
	private InventoryCraftResult craftResult = new InventoryCraftResult();
	private final World world;
	private final EntityPlayer player;
	public static ResourceLocation TEXTURE = new ResourceLocation(GTMod.MODID, "textures/gui/worktable.png");

	public GTContainerWorktable(InventoryPlayer player, GTTileWorktable tile) {
		super(tile);
		this.world = player.player.getEntityWorld();
		this.player = player.player;
		this.addComponent(new GTGuiCompBasicString("Basic Worktable", 85, 6));
		this.addSlotToContainer(new SlotCrafting(this.player, craftMatrix, craftResult, 0, 148, 35));// slot 0
		for (int k = 0; k < 4; ++k) {
			for (int l = 0; l < 4; ++l) {
				this.addSlotToContainer(new SlotBase(tile, (k + l * 4) + 1, 8 + l * 18, 8 + k * 18));
			}
		}
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				this.addSlotToContainer(new Slot(craftMatrix, j + i * 3, 82 + j * 18, 17 + i * 18));
			}
		}
		this.addPlayerInventory(player, 0, 0);
	}

	@Override
	public void onCraftMatrixChanged(IInventory inventoryIn) {
		this.slotChangedCraftingGrid(this.world, this.player, this.craftMatrix, this.craftResult);
	}

	@Override
	public void onContainerClosed(EntityPlayer playerIn) {
		super.onContainerClosed(playerIn);
		if (!this.world.isRemote) {
			this.clearContainer(playerIn, this.world, this.craftMatrix);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiLoaded(GuiIC2 gui) {
		gui.disableName();
		gui.dissableInvName();
	}

	@Override
	public ResourceLocation getTexture() {
		return TEXTURE;
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
