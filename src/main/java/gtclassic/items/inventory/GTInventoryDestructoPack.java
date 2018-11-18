package gtclassic.items.inventory;

import gtclassic.container.GTContainerDestructoPack;
import ic2.core.block.personal.base.misc.PersonalInventory;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.base.IHasInventory;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.InventoryHandler;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;


public class GTInventoryDestructoPack implements IHasGui {
		public IHasInventory inv;
		public InventoryHandler handler;
		boolean allowSlot;

		public GTInventoryDestructoPack() {
			this.inv = new PersonalInventory(1);
			this.allowSlot = false;
			this.handler = null;
		}
		
		@Override
        public void setStackInSlot(int slot, ItemStack stack){
			 //Broken code <---------
		 }
		
		
		public ContainerIC2 getGuiContainer(EntityPlayer player) {
			return new GTContainerDestructoPack(player.inventory, this.inv, this);
		}

		public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
			return GuiComponentContainer.class;
		}

		public void onGuiClosed(EntityPlayer player) {
		}

		public boolean canInteractWith(EntityPlayer player) {
			return this.handler != null;
		}
		
		public boolean hasGui(EntityPlayer player) {
			return true;
		}

	}
