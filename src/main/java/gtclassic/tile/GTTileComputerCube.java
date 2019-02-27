package gtclassic.tile;

import gtclassic.container.GTContainerComputerCube;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.InventoryHandler;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileComputerCube extends TileEntityMachine implements IHasGui {

	public int index;

	public GTTileComputerCube() {
		super(1);
		this.index = 0;
	}
	
	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerComputerCube(player.inventory, this);

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

}
