package gtclassic.common.container;

import javax.annotation.Nullable;

import gtclassic.GTMod;
import gtclassic.common.gui.GTGuiCompBuffer;
import gtclassic.common.tile.GTTileTranslocatorFluid;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.slots.SlotDisplay;
import ic2.core.item.misc.ItemDisplayIcon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTContainerTranslocatorFluid extends ContainerTileComponent<GTTileTranslocatorFluid> {

	public static ResourceLocation TEXTURE = new ResourceLocation(GTMod.MODID, "textures/gui/translocatorfluid.png");
	GTTileTranslocatorFluid block;

	public GTContainerTranslocatorFluid(InventoryPlayer player, GTTileTranslocatorFluid tile) {
		super(tile);
		block = tile;
		this.addSlotToContainer(new SlotDisplay(tile, 0, 80, 23));
		this.addComponent(new GTGuiCompBuffer(tile, player));
		this.addPlayerInventory(player);
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
		return 1;
	}

	@Nullable
	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
		if (slotId == 0) {
			ItemStack stack = player.inventory.getItemStack();
			if (stack.isEmpty()) {
				this.block.setStackInSlot(0, ItemStack.EMPTY);
				this.block.filter = null;
				return ItemStack.EMPTY;
			}
			if (FluidUtil.getFluidContained(stack) != null) {
				FluidStack fluidStack = FluidUtil.getFluidContained(stack);
				FluidStack newStack = new FluidStack(fluidStack.getFluid(), 1000);
				this.block.setStackInSlot(0, ItemDisplayIcon.createWithFluidStack(newStack));
				this.block.filter = newStack;
			}
			return ItemStack.EMPTY;
		}
		return super.slotClick(slotId, dragType, clickTypeIn, player);
	}
}
