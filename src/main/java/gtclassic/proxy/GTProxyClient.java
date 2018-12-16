package gtclassic.proxy;

import gtclassic.util.GTIcons;
import ic2.core.platform.textures.Ic2Icons.SpriteReloadEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class GTProxyClient extends GTProxyCommon {

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
	}

	@SubscribeEvent
	public static void onIconLoad(SpriteReloadEvent event) {
		GTIcons.loadSprites();
	}
}
