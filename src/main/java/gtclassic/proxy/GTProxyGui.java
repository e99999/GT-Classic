package gtclassic.proxy;

import gtclassic.container.GTContainerCabinet;
import gtclassic.gui.GTGuiCabinet;
import gtclassic.tileentity.GTTileEntityCabinet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GTProxyGui implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof GTTileEntityCabinet) {
            return new GTContainerCabinet(player.inventory, (GTTileEntityCabinet) te);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof GTTileEntityCabinet) {
            GTTileEntityCabinet containerTileEntity = (GTTileEntityCabinet) te;
            return new GTGuiCabinet(containerTileEntity, new GTContainerCabinet(player.inventory, containerTileEntity));
        }
        return null;
    }
}
