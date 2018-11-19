package gtclassic.items.inventory;

import gtclassic.container.GTContainerDestructoPack;
import ic2.core.inventory.base.IC2ItemInventory;
import ic2.core.inventory.base.IHandHeldInventory;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;


public class GTInventoryDestructoPack extends IC2ItemInventory {
		public GTInventoryDestructoPack(EntityPlayer player, IHandHeldInventory inv, ItemStack item) {
			super(player, inv, item);
		}

		public int getType() {
			return 0;
		}
		
		@Override
        public void setStackInSlot(int slot, ItemStack stack){
			if (this.getStackInSlot(0) != ItemStack.EMPTY){
			this.setStackInSlot(0, ItemStack.EMPTY);
			}
		 } 
		
		public ContainerIC2 getGuiContainer(EntityPlayer player) {
			return new GTContainerDestructoPack(this, this.getID(), player.inventory);
		}

		public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
			return GuiComponentContainer.class;
		}

		@Override
		public void onGuiClosed(EntityPlayer player) {
			//TODO add code to delete any lingering nbt data
		}

		public boolean canInteractWith(EntityPlayer player) {
			return !player.isDead;
		}
		
		public boolean hasGui(EntityPlayer player) {
			return true;
		}

		@Override
		public int getInventorySize() {
			return 1;
		}

	}