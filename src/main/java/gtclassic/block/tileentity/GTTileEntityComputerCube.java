package gtclassic.block.tileentity;

import gtclassic.block.container.GTContainerComputerCube0;
import gtclassic.block.container.GTContainerComputerCube1;
import gtclassic.block.container.GTContainerComputerCube2;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.InventoryHandler;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileEntityComputerCube extends TileEntityMachine implements IHasGui {

	public int index;

	public GTTileEntityComputerCube() {
		super(1);
		this.index = 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		if (this.index == 0) {
			return new GTContainerComputerCube0(player.inventory, this);
		}
		if (this.index == 1) {
			return new GTContainerComputerCube1(player.inventory, this);
		}
		if (this.index == 2) {
			return new GTContainerComputerCube2(player.inventory, this);
		} else {
			return new GTContainerComputerCube0(player.inventory, this);
		}
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		// nothing for now
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return !this.isInvalid();
	}

	@Override
	public void onGuiClosed(EntityPlayer entityPlayer) {
	}

	@Override
	public boolean hasGui(EntityPlayer player) {
		return true;
	}
	
	public void advanceIndex () {
		this.index = this.index + 1;
		if (this.index > 2) {
			this.index = 0;
		}
	}

}
