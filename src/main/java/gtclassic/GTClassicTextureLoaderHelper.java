package gtclassic;

import gtclassic.util.GTIcons;
import ic2.api.classic.addon.IC2Plugin;
import ic2.api.classic.addon.PluginBase;
import net.minecraftforge.fml.relauncher.Side;

@IC2Plugin(name = "GTClassic Texture Loader Helper", id = "gtclassictextureloaderhelper",version = GTClassic.MODVERSION, hasResourcePack = true)
public class GTClassicTextureLoaderHelper extends PluginBase{


    @Override
    public boolean canLoad(Side side) {
        return true;
    }

    @Override
    public void onTextureLoad() {
        GTIcons.loadSprites();
    }

}
