package gtclassic.tileentity;

import gtclassic.GTClassic;
import gtclassic.container.GTContainerHESU;
import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.block.wiring.container.ContainerElectricBlock;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.platform.registry.Ic2Resources;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GTTileEntityHESU extends TileEntityElectricBlock {
    public GTTileEntityHESU(int tier, int output, int maxEnergy) {
        super(tier, output, maxEnergy);
    }

    @Override
    public ResourceLocation getTexture() {
        return new ResourceLocation(GTClassic.MODID, "textures/gui/aesu.png");
    }

    @Override
    public ContainerIC2 getGuiContainer(EntityPlayer player) {
        return new GTContainerHESU(player.inventory, this);
    }


    @Override
    public int getProcessRate() {
        return 0;
    }

    @Override
    public void update() {

    }

    @Override
    public String getName() {
        return "HESU";
    }

    @Override
    public boolean hasCustomName() {
        return true;
    }
}
