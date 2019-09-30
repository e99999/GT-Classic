package gtclassic.container;

import gtclassic.GTMod;
import gtclassic.gui.GTGuiCompMagicEnergyAbsorber;
import gtclassic.tile.GTTileMagicEnergyAbsorber;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.slots.SlotBase;
import ic2.core.inventory.slots.SlotOutput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GTContainerMagicEnergyAbsorber extends ContainerTileComponent<GTTileMagicEnergyAbsorber> {

	public static ResourceLocation TEXTURE = new ResourceLocation(GTMod.MODID, "textures/gui/magicabsorber.png");

	public GTContainerMagicEnergyAbsorber(InventoryPlayer player, GTTileMagicEnergyAbsorber tile) {
		super(tile);
		this.addSlotToContainer(new SlotBase(tile, 0, 80, 17));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 1, 80, 53));
		this.addComponent(new GTGuiCompMagicEnergyAbsorber(tile));
		this.addPlayerInventory(player, 0, 0);
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
		return 2;
	}
}
