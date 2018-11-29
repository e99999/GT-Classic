package gtclassic.tileentity;

import gtclassic.container.GTContainerHESU;
import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.inventory.container.ContainerIC2;
import net.minecraft.entity.player.EntityPlayer;


public class GTTileEntityHESU extends TileEntityElectricBlock {
    public GTTileEntityHESU() 
    {
        super(4, 2048, 100000000);
    }

    @Override
    public ContainerIC2 getGuiContainer(EntityPlayer player) 
    {
        return new GTContainerHESU(player.inventory, this);
    }


    @Override
    public int getProcessRate()
    {
        return 128;
    }
    
    @Override
    public double getWrenchDropRate() 
    {
		return 0.70D;
	}

    @Override
    public void update() 
    {
    	//needed for construction
    }

    @Override
    public String getName() 
    {
        return "H.E.S.U.";
    }

    @Override
    public boolean hasCustomName() 
    {
        return false;
    }
}
