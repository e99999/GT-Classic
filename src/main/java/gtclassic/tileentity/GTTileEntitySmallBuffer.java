package gtclassic.tileentity;

import gtclassic.GTClassic;
import gtclassic.container.GTContainerIndustrialCentrifuge;
import gtclassic.container.GTContainerSmallBuffer;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyConductor;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;

public class GTTileEntitySmallBuffer extends TileEntityMachine implements IHasGui{

	public GTTileEntitySmallBuffer() {
		super(1);
	}
	
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
        return GuiComponentContainer.class;
    }
	
	public ContainerIC2 getGuiContainer(EntityPlayer player)
    {
        return new GTContainerSmallBuffer(player.inventory, this);
    }

	@Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return !this.isInvalid();
    }

	@Override
    public void onGuiClosed(EntityPlayer entityPlayer)
    {
        //needed for construction
    }

    @Override
    public boolean hasGui(EntityPlayer player)
    {
        return true;
    }

}
