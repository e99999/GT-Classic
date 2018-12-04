package gtclassic.container;

import gtclassic.GTClassic;
import gtclassic.items.inventory.GTInventoryDestructoPack;
import gtclassic.util.GTItems;
import ic2.core.inventory.container.ContainerItemComponent;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.InvertedFilter;
import ic2.core.inventory.slots.SlotCustom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GTContainerDestructoPack extends ContainerItemComponent<GTInventoryDestructoPack> {
	public static ResourceLocation TEXTURE = new ResourceLocation(GTClassic.MODID, "textures/gui/destructopack.png");
	
	public GTContainerDestructoPack(GTInventoryDestructoPack inv, int id, InventoryPlayer player) {
		super(inv, id);
		this.addSlotToContainer(new SlotCustom(inv, 0, 80, 17, new InvertedFilter(new BasicItemFilter(GTItems.destructoPack))));
		this.addPlayerInventory(player, 0, 0);
	}
	
	@Override
	public ResourceLocation getTexture() {
		return TEXTURE;
	}

	@Override
    public boolean canInteractWith(EntityPlayer player)
    {
		return getGuiHolder().canInteractWith(player);
    }
}
