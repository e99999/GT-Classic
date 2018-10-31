package gtclassic.proxy;

import gtclassic.blocks.cabinet.CabinetContainer;
import gtclassic.blocks.cabinet.CabinetGui;
import gtclassic.blocks.cabinet.CabinetTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiProxy implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof CabinetTileEntity) {
            return new CabinetContainer(player.inventory, (CabinetTileEntity) te);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof CabinetTileEntity) {
            CabinetTileEntity containerTileEntity = (CabinetTileEntity) te;
            return new CabinetGui(containerTileEntity, new CabinetContainer(player.inventory, containerTileEntity));
        }
        return null;
    }
}
