package gtclassic.common.container;

import gtclassic.GTMod;
import gtclassic.common.gui.GTGuiCompBuffer;
import gtclassic.common.gui.GTGuiCompRockBreaker;
import gtclassic.common.tile.GTTileRockBreaker;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotOutput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTContainerRockBreaker extends ContainerTileComponent<GTTileRockBreaker> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(GTMod.MODID, "textures/gui/rockbreaker.png");

	public GTContainerRockBreaker(InventoryPlayer player, GTTileRockBreaker tile) {
		super(tile);
		this.addSlotToContainer(new SlotOutput(player.player, tile, 0, 80, 23));
		this.addSlotToContainer(new SlotCustom(tile, 1, 80, 41, GTTileRockBreaker.reallyCoolHackyItemStackArrayFilter));
		this.addComponent(new GTGuiCompBuffer(tile, player));
		this.addComponent(new GTGuiCompRockBreaker(tile));
		this.addPlayerInventory(player);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiLoaded(GuiIC2 gui) {
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
		return this.getGuiHolder().getSlotCount();
	}
}
