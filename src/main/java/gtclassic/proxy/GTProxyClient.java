package gtclassic.proxy;

import gtclassic.GTIcons;
import gtclassic.color.GTColorBlock;
import gtclassic.color.GTColorBlockInterface;
import gtclassic.color.GTColorItem;
import gtclassic.color.GTColorItemInterface;
import ic2.core.platform.textures.Ic2Icons.SpriteReloadEvent;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GTProxyClient extends GTProxyCommon {

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
		registerTintedBlocks();
		registerTintedItems();
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onIconLoad(SpriteReloadEvent event) {
		GTIcons.loadSprites();
	}
	
	public static void registerTintedItems() {
		GTColorItem colors = new GTColorItem();
		ItemColors registry = Minecraft.getMinecraft().getItemColors();
		for (Item item : Item.REGISTRY) {
			if (item instanceof GTColorItemInterface) {
				registry.registerItemColorHandler(colors, item);
			}
		}
	}

	public static void registerTintedBlocks() {
		GTColorBlock colors = new GTColorBlock();
		BlockColors registry = Minecraft.getMinecraft().getBlockColors();
		for (Block block : Block.REGISTRY) {
			if (block instanceof GTColorBlockInterface) {
				registry.registerBlockColorHandler(colors, block);
			}
		}
	}
}
