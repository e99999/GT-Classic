package gtclassic.proxy;

import gtclassic.GTIcons;
import ic2.core.platform.textures.Ic2Icons.SpriteReloadEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GTProxyClient extends GTProxyCommon {

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onIconLoad(SpriteReloadEvent event) {
		GTIcons.loadSprites();
	}
}
