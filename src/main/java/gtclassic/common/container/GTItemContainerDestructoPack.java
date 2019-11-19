package gtclassic.common.container;

import gtclassic.GTMod;
import gtclassic.common.GTItems;
import ic2.core.inventory.container.ContainerItemComponent;
import ic2.core.inventory.filters.ArrayFilter;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.InvertedFilter;
import ic2.core.inventory.slots.SlotCustom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GTItemContainerDestructoPack extends ContainerItemComponent<GTItemInventoryDestructoPack> {

	public static ResourceLocation TEXTURE = new ResourceLocation(GTMod.MODID, "textures/gui/destructopack.png");

	public GTItemContainerDestructoPack(GTItemInventoryDestructoPack inv, int id, InventoryPlayer player) {
		super(inv, id);
		this.addSlotToContainer(new SlotCustom(inv, 0, 80, 17, new InvertedFilter(new ArrayFilter(new BasicItemFilter(GTItems.destructoPack), new BasicItemFilter(GTItems.debugScanner), new BasicItemFilter(GTItems.portableScanner)))));
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
}
