package gtclassic.container;

import gtclassic.GTClassic;
import gtclassic.items.inventory.GTInventoryCraftingTablet;
import gtclassic.util.GTItems;
import ic2.core.inventory.container.ContainerItemComponent;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.InvertedFilter;
import ic2.core.inventory.slots.SlotBase;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotOutput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GTContainerCraftingTablet extends ContainerItemComponent<GTInventoryCraftingTablet> {
	
	public static ResourceLocation TEXTURE = new ResourceLocation(GTClassic.MODID, "textures/gui/craftingtablet.png");
	
	public GTContainerCraftingTablet(GTInventoryCraftingTablet inv, int id, InventoryPlayer player) 
	{
		super(inv, id);
		this.addSlotToContainer(new SlotCustom(inv, 0, 30, 17, new InvertedFilter(new BasicItemFilter(GTItems.craftingTablet))));
		this.addSlotToContainer(new SlotCustom(inv, 1, 48, 17, new InvertedFilter(new BasicItemFilter(GTItems.craftingTablet))));
		this.addSlotToContainer(new SlotCustom(inv, 2, 66, 17, new InvertedFilter(new BasicItemFilter(GTItems.craftingTablet))));
		this.addSlotToContainer(new SlotCustom(inv, 3, 30, 35, new InvertedFilter(new BasicItemFilter(GTItems.craftingTablet))));
		this.addSlotToContainer(new SlotCustom(inv, 4, 48, 35, new InvertedFilter(new BasicItemFilter(GTItems.craftingTablet))));
		this.addSlotToContainer(new SlotCustom(inv, 5, 66, 35, new InvertedFilter(new BasicItemFilter(GTItems.craftingTablet))));
		this.addSlotToContainer(new SlotCustom(inv, 6, 30, 53, new InvertedFilter(new BasicItemFilter(GTItems.craftingTablet))));
		this.addSlotToContainer(new SlotCustom(inv, 7, 48, 53, new InvertedFilter(new BasicItemFilter(GTItems.craftingTablet))));
		this.addSlotToContainer(new SlotCustom(inv, 8, 66, 53, new InvertedFilter(new BasicItemFilter(GTItems.craftingTablet))));
		this.addSlotToContainer(new SlotOutput(player.player,inv, 9, 124, 35));
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
