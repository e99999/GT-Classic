package gtclassic.common.proxy;

import gtclassic.GTMod;
import gtclassic.api.color.GTColorBlock;
import gtclassic.api.color.GTColorItem;
import gtclassic.api.interfaces.IGTColorBlock;
import gtclassic.api.interfaces.IGTColorItem;
import gtclassic.common.GTIcons;
import ic2.core.platform.textures.Ic2Icons.SpriteReloadEvent;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
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

	@SubscribeEvent
	public static void onRegisterTexture(TextureStitchEvent.Pre event) {
		event.getMap().registerSprite(new ResourceLocation(GTMod.MODID, "fluids/fluid"));
		event.getMap().registerSprite(new ResourceLocation(GTMod.MODID, "fluids/fluidflowing"));
		event.getMap().registerSprite(new ResourceLocation(GTMod.MODID, "fluids/gas"));
		event.getMap().registerSprite(new ResourceLocation(GTMod.MODID, "fluids/gasflowing"));
	}

	public static void registerTintedItems() {
		GTColorItem colors = new GTColorItem();
		ItemColors registry = Minecraft.getMinecraft().getItemColors();
		for (Item item : Item.REGISTRY) {
			if (item instanceof IGTColorItem) {
				registry.registerItemColorHandler(colors, item);
			}
		}
	}

	public static void registerTintedBlocks() {
		GTColorBlock colors = new GTColorBlock();
		BlockColors registry = Minecraft.getMinecraft().getBlockColors();
		for (Block block : Block.REGISTRY) {
			if (block instanceof IGTColorBlock) {
				registry.registerBlockColorHandler(colors, block);
			}
		}
	}
}
