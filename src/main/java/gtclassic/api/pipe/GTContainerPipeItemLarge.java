package gtclassic.api.pipe;

import gtclassic.GTMod;
import gtclassic.common.item.GTItemMonkeyWrench;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.slots.SlotBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;

public class GTContainerPipeItemLarge extends ContainerTileComponent<GTTilePipeItemBase> {

	public static ResourceLocation TEXTURE = new ResourceLocation(GTMod.MODID, "textures/gui/quad.png");

	public GTContainerPipeItemLarge(InventoryPlayer player, GTTilePipeItemBase tile) {
		super(tile);
		player.player.playSound(SoundEvents.BLOCK_IRON_DOOR_OPEN, 1.0F, 1.0F
				+ (player.player.getEntityWorld().rand.nextFloat() * .1F));
		GTItemMonkeyWrench.useMonkeyWrench(player.player);
		this.addSlotToContainer(new SlotBase(tile, 0, 71, 23));
		this.addSlotToContainer(new SlotBase(tile, 1, 89, 23));
		this.addSlotToContainer(new SlotBase(tile, 2, 71, 41));
		this.addSlotToContainer(new SlotBase(tile, 3, 89, 41));
		this.addPlayerInventory(player);
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
		return 4;
	}
}
