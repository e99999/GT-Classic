package gtclassic.container;

import gtclassic.GTClassic;
import gtclassic.tileentity.GTTileEntityWorkbench;
import gtclassic.util.gui.GTGuiCompBasicString;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTContainerWorkbench extends ContainerTileComponent<GTTileEntityWorkbench> {

	private InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
	private InventoryCraftResult craftResult = new InventoryCraftResult();
	private final World world;
	@SuppressWarnings("unused")
	private final BlockPos pos;
	private final EntityPlayer player;

	public static ResourceLocation TEXTURE = new ResourceLocation(GTClassic.MODID, "textures/gui/workbench.png");

	public GTContainerWorkbench(InventoryPlayer player, GTTileEntityWorkbench tile) {
		super(tile);
		this.world = player.player.getEntityWorld();
		this.pos = player.player.getPosition();
		this.player = player.player;

		this.addComponent(new GTGuiCompBasicString("Basic Workbench", 85, 6));

		this.addSlotToContainer(new SlotCrafting(player.player, craftMatrix, craftResult, 0, 148, 35));

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				this.addSlotToContainer(new Slot(craftMatrix, j + i * 3, 82 + j * 18, 17 + i * 18));
			}
		}

		this.addSlotToContainer(new SlotBase(tile, 0, 8, 8));
		this.addSlotToContainer(new SlotBase(tile, 1, 26, 8));
		this.addSlotToContainer(new SlotBase(tile, 2, 44, 8));
		this.addSlotToContainer(new SlotBase(tile, 3, 62, 8));

		this.addSlotToContainer(new SlotBase(tile, 4, 8, 26));
		this.addSlotToContainer(new SlotBase(tile, 5, 26, 26));
		this.addSlotToContainer(new SlotBase(tile, 6, 44, 26));
		this.addSlotToContainer(new SlotBase(tile, 7, 62, 26));

		this.addSlotToContainer(new SlotBase(tile, 8, 8, 44));
		this.addSlotToContainer(new SlotBase(tile, 9, 26, 44));
		this.addSlotToContainer(new SlotBase(tile, 10, 44, 44));
		this.addSlotToContainer(new SlotBase(tile, 11, 62, 44));

		this.addSlotToContainer(new SlotBase(tile, 12, 8, 62));
		this.addSlotToContainer(new SlotBase(tile, 13, 26, 62));
		this.addSlotToContainer(new SlotBase(tile, 14, 44, 62));
		this.addSlotToContainer(new SlotBase(tile, 15, 62, 62));

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
