package gtclassic.common;

import static ic2.core.platform.textures.Ic2Icons.addCustomTexture;
import static ic2.core.platform.textures.Ic2Icons.addSprite;
import static ic2.core.platform.textures.Ic2Icons.addTextureEntry;

import gtclassic.GTMod;
import ic2.core.platform.textures.Sprites;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTIcons {

	@SideOnly(Side.CLIENT)
	public static void loadSprites() {
		addSprite(new Sprites.SpriteData(GTMod.MODID + "_blocks", GTMod.MODID
				+ ":textures/sprites/sprites_blocks.png", new Sprites.SpriteInfo(16, 16)));
		addSprite(new Sprites.SpriteData(GTMod.MODID + "_items", GTMod.MODID
				+ ":textures/sprites/sprites_items.png", new Sprites.SpriteInfo(16, 16)));
		addSprite(new Sprites.SpriteData(GTMod.MODID + "_materials", GTMod.MODID
				+ ":textures/sprites/sprites_materials.png", new Sprites.SpriteInfo(16, 16)));
		addSprite(new Sprites.SpriteData(GTMod.MODID + "_ores", GTMod.MODID
				+ ":textures/sprites/sprites_ores.png", new Sprites.SpriteInfo(16, 1)));
		addSprite(new Sprites.SpriteData(GTMod.MODID + "_builder", GTMod.MODID
				+ ":textures/sprites/builder.png", new Sprites.SpriteInfo(1, 12)));
		addSprite(new Sprites.SpriteData(GTMod.MODID + "_batteryblocklv", GTMod.MODID
				+ ":textures/sprites/batteryblocklv.png", new Sprites.SpriteInfo(5, 6)));
		collectBasicTileSprites();
		addTextureEntry(new Sprites.TextureEntry(GTMod.MODID + "_builder", 0, 0, 1, 12));
		addTextureEntry(new Sprites.TextureEntry(GTMod.MODID + "_blocks", 0, 0, 16, 12));
		addTextureEntry(new Sprites.TextureEntry(GTMod.MODID + "_items", 0, 0, 16, 16));
		addTextureEntry(new Sprites.TextureEntry(GTMod.MODID + "_materials", 0, 0, 16, 4));
		addTextureEntry(new Sprites.TextureEntry(GTMod.MODID + "_ores", 0, 0, 16, 1));
		addTextureEntry(new Sprites.TextureEntry(GTMod.MODID + "_batteryblocklv", 0, 0, 5, 6));
		if (GTConfig.general.animatedTextures) {
			addCustomTexture("fusionreactor", 0, 7, location("fusion_top"));
			addCustomTexture("quantumchest", 0, 3, location("qchest_front"));
			addCustomTexture("quantumtank", 0, 1, location("qtank_top"));
			addCustomTexture("digitalchest", 0, 1, location("digichest_top"));
			addCustomTexture("bedrockminer", 0, 7, location("miner_top"));
			addCustomTexture("industrialcentrifuge", 0, 7, location("centrifuge_top"));
			addCustomTexture("industrialcentrifuge", 0, 8, location("centrifuge_back"));
			addCustomTexture("industrialcentrifuge", 0, 9, location("centrifuge_front"));
			addCustomTexture("industrialcentrifuge", 0, 10, location("centrifuge_left"));
			addCustomTexture("industrialcentrifuge", 0, 11, location("centrifuge_right"));
		}
	}

	private static ResourceLocation location(String name) {
		return new ResourceLocation(GTMod.MODID, "animations/" + name);
	}

	public static void collectBasicTileSprites() {
		for (String string : GTBlocks.textureTileBasic) {
			addSprite(new Sprites.SpriteData(string, GTMod.MODID + ":textures/sprites/" + string
					+ ".png", new Sprites.SpriteInfo(1, 12)));
			addTextureEntry(new Sprites.TextureEntry(string, 0, 0, 1, 12));
		}
	}
}
