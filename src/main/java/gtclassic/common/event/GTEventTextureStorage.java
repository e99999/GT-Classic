package gtclassic.common.event;

import gtclassic.api.interfaces.IGTTextureStorageTile;
import gtclassic.api.tile.GTTileBaseCable;
import ic2.api.classic.event.FoamEvent;
import ic2.api.classic.event.RetextureEventClassic;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GTEventTextureStorage {

    @SubscribeEvent
    public void onRetextureEvent(RetextureEventClassic event){
        if (!event.isApplied() && !event.isCanceled()){
            TileEntity tile = event.getTargetTile();
            if (tile instanceof IGTTextureStorageTile){
                IGTTextureStorageTile storage = (IGTTextureStorageTile) tile;
                if (storage.setStorage(event.getTargetSide(), event.getModelState(), event.getRenderState(), event.getColorMultipliers(), event.getRotations(), event.getRefSide())){
                    event.setApplied(true);
                }
            }
        }
    }

    @SubscribeEvent
    public void onFoamCheckEvent(FoamEvent.Check event) {
        if (!event.hasCustomTarget()){
            TileEntity tile = event.getTileEntity();
            if (tile instanceof GTTileBaseCable) {
                GTTileBaseCable cable = (GTTileBaseCable)tile;
                if (cable.foamed == 0) {
                    event.setResult(FoamEvent.FoamResult.Cable);
                }
            }
        }
    }

    @SubscribeEvent
    public void onFoamPlaceEvent(FoamEvent.Place event){

        TileEntity tile = event.getTileEntity();
        if (tile instanceof GTTileBaseCable) {
            GTTileBaseCable cable = (GTTileBaseCable)tile;
            if (cable.changeFoam((byte)1)) {
                event.setCanceled(true);
            }
        }
    }
}
