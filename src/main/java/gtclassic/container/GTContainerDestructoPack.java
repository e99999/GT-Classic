package gtclassic.container;

import gtclassic.GTClassic;
import gtclassic.items.inventory.GTInventoryDestructoPack;
import gtclassic.items.tools.GTItemDestructoPack;
import ic2.core.inventory.container.ContainerComponent;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.lang.storage.Ic2ItemLang;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GTContainerDestructoPack extends ContainerComponent<GTItemDestructoPack> {

	public GTContainerDestructoPack(GTInventoryDestructoPack inv, int id, InventoryPlayer player) {
		super(inv);
		//super(inv, id);
		this.addSlotToContainer(new SlotCustom(inv, 0, 80, 17, null));
		this.addPlayerInventory(player, 0, 0);
	}

	
	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation(GTClassic.MODID, "textures/gui/destructopack.png");
	}
	
	@Override
	public int guiInventorySize() {
		return 1;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

	@Override
	public LocaleComp getGuiName() {
		//yes I know this returns a generic name its temporary
		return Ic2ItemLang.machineTool;
	}

}
