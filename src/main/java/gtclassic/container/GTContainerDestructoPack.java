package gtclassic.container;

import gtclassic.GTClassic;
import gtclassic.items.inventory.GTInventoryDestructoPack;
import gtclassic.items.tools.GTItemDestructoPack;
import ic2.core.inventory.container.ContainerComponent;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.platform.lang.components.base.LangComponentHolder;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.lang.storage.Ic2ItemLang;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import gtclassic.util.GTItems;

public class GTContainerDestructoPack extends ContainerItemComponent<GTInventoryDestructoPack> {
	public static LocaleComp NAME_LOCATION = new LangComponentHolder.LocaleBlockComp("item.gtclassic.destructoPack");
	public static ResourceLocation TEXTURE = new ResourceLocation(GTClassic.MODID, "textures/gui/destructopack.png");
	
	public GTContainerDestructoPack(GTInventoryDestructoPack inv, int id, InventoryPlayer player) {
		super(inv, id);
		this.addSlotToContainer(new SlotCustom(inv, 0, 80, 17, new InvertedFilter(new BasicItemFilter(GTItems.destructoPack)));
		this.addPlayerInventory(player, 0, 0);
	}
	
	@Override
	public ResourceLocation getTexture() {
		return TEXTURE;
	}
	
	@Override
	public LocaleComp getGuiName() {
		 return NAME_LOCATION;
	}
}
