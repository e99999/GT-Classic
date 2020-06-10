package gtclassic.common.container;

import javax.annotation.Nullable;

import gtclassic.GTMod;
import gtclassic.api.helpers.GTHelperMath;
import gtclassic.common.gui.GTGuiCompBuffer;
import gtclassic.common.tile.GTTileTranslocator;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.slots.SlotDisplay;
import ic2.core.util.misc.StackUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTContainerTranslocator extends ContainerTileComponent<GTTileTranslocator> {

	public static ResourceLocation TEXTURE = new ResourceLocation(GTMod.MODID, "textures/gui/translocator.png");
	GTTileTranslocator block;

	public GTContainerTranslocator(InventoryPlayer player, GTTileTranslocator tile) {
		super(tile);
		block = tile;
		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 3; ++x) {
				this.addSlotToContainer(new SlotDisplay(tile, x + y * 3, 62 + x * 18, 5 + y * 18));
			}
		}
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
		return getGuiHolder().slotCount;
	}

	@Nullable
	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
		if (GTHelperMath.within(slotId, 0, 8)) {
			ItemStack stack = player.inventory.getItemStack();
			this.block.setStackInSlot(slotId, stack.isEmpty() ? ItemStack.EMPTY : StackUtil.copyWithSize(stack, 1));
			return ItemStack.EMPTY;
		}
		return super.slotClick(slotId, dragType, clickTypeIn, player);
	}
}
