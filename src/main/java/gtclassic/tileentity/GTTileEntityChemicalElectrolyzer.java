package gtclassic.tileentity;

import gtclassic.container.GTContainerChemicalElectrolyzer;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;

public class GTTileEntityChemicalElectrolyzer extends TileEntityElecMachine implements IHasGui {

	public GTTileEntityChemicalElectrolyzer() 
	{
		super(7, 32);
	}

	@Override
	public boolean supportsNotify() 
	{
		return true;
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) 
	{
		return !this.isInvalid();
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer var1) 
	{
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerChemicalElectrolyzer(player.inventory, this);
	}

	@Override
	public boolean hasGui(EntityPlayer var1) {
		return true;
	}

	@Override
    public void onGuiClosed(EntityPlayer entityPlayer)
    {
        //needed for construction
    }

}
