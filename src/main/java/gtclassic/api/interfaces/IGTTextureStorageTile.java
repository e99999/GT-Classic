package gtclassic.api.interfaces;

import ic2.api.classic.event.RetextureEventClassic;
import ic2.core.block.base.util.texture.TextureCopyStorage;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;

public interface IGTTextureStorageTile {
    TextureCopyStorage getStorage();

    boolean setStorage(EnumFacing targetSide, IBlockState model, IBlockState render, int[] color, RetextureEventClassic.Rotation[] rot, EnumFacing facing);
}
