package gtclassic.common.crafttweaker;

import static ic2.core.platform.textures.Ic2Icons.addSprite;
import static ic2.core.platform.textures.Ic2Icons.addTextureEntry;

import java.util.LinkedHashMap;
import java.util.Map;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.runtime.ScriptLoader;
import gtclassic.GTMod;
import ic2.core.platform.textures.Sprites;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTCraftTweakerLoader {

	static Map<String, ResourceLocation> flagTextureMap = new LinkedHashMap<>();

	public static void preInit() {
		ScriptLoader loader = CraftTweakerAPI.tweaker.getOrCreateLoader(GTMod.MODID + "_data");
		CraftTweakerAPI.tweaker.loadScript(false, loader);
	}

	@SideOnly(Side.CLIENT)
	public static void initIcons() {
		for (String string : flagTextureMap.keySet()) {
			ResourceLocation location = flagTextureMap.get(string);
			addTexture(location.getNamespace() + "_" + string, location);
		}
	}

	@SideOnly(Side.CLIENT)
	private static void addTexture(String name, ResourceLocation location) {
		addSprite(new Sprites.SpriteData(name, location.getNamespace() + ":" + "textures/"
				+ location.getPath() + ".png", new Sprites.SpriteInfo(1, 1)));
		addTextureEntry(new Sprites.TextureEntry(name, 0, 0, 1, 1));
	}
}
