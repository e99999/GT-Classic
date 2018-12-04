package gtclassic.proxy;

import gtclassic.util.GTBlocks;
import gtclassic.util.GTIcons;
import ic2.core.platform.textures.Ic2Icons.SpriteReloadEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class GTProxyClient extends GTProxyCommon {
    
	@Override
    public void preInit(FMLPreInitializationEvent e) 
	{
    	super.preInit(e);
    }

	public void onIconLoad(SpriteReloadEvent event)
	{
	    GTIcons.loadSprites();
	}
    
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) 
    {
    	GTBlocks.initModels();
    }
}

