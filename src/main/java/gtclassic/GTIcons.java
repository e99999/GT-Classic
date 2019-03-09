package gtclassic;

import static ic2.core.platform.textures.Ic2Icons.addCustomTexture;
import static ic2.core.platform.textures.Ic2Icons.addSprite;
import static ic2.core.platform.textures.Ic2Icons.addTextureEntry;

import ic2.core.platform.textures.Sprites;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTIcons {
	@SideOnly(Side.CLIENT)
	public static void loadSprites() {
		addSprite(new Sprites.SpriteData(GTMod.MODID + "_blocks", GTMod.MODID + ":textures/sprites/sprites_blocks.png",
				new Sprites.SpriteInfo(16, 16)));

		addSprite(new Sprites.SpriteData(GTMod.MODID + "_items", GTMod.MODID + ":textures/sprites/sprites_items.png",
				new Sprites.SpriteInfo(16, 16)));

		addSprite(new Sprites.SpriteData(GTMod.MODID + "_materials",
				GTMod.MODID + ":textures/sprites/sprites_materials.png", new Sprites.SpriteInfo(16, 16)));

		addSprite(new Sprites.SpriteData(GTMod.MODID + "_casings",
				GTMod.MODID + ":textures/sprites/sprites_casings.png", new Sprites.SpriteInfo(16, 16)));

		addSprite(new Sprites.SpriteData(GTMod.MODID + "_builder", GTMod.MODID + ":textures/sprites/builder.png",
				new Sprites.SpriteInfo(1, 12)));

		collectBasicTileSprites();
		collectCustomTileSprites();

		addTextureEntry(new Sprites.TextureEntry(GTMod.MODID + "_builder", 0, 0, 1, 12));
		addTextureEntry(new Sprites.TextureEntry(GTMod.MODID + "_blocks", 0, 0, 16, 12));
		addTextureEntry(new Sprites.TextureEntry(GTMod.MODID + "_items", 0, 0, 16, 5));

		addTextureEntry(new Sprites.TextureEntry(GTMod.MODID + "_materials", 0, 0, 16, 4));
		addTextureEntry(new Sprites.TextureEntry(GTMod.MODID + "_casings", 0, 0, 16, 4));

		addCustomTexture("machine_bloomery", 0, 9, location("bloomery_front"));
		addCustomTexture("tile_digitalchest", 0, 1, location("digitalchest_top"));
		addCustomTexture("machine_fusioncomputer_iv", 0, 7, location("fusion_top"));
		addCustomTexture("machine_industrialcentrifuge_lv", 0, 7, location("centrifuge_top"));
		addCustomTexture("machine_industrialcentrifuge_lv", 0, 8, location("centrifuge_back"));
		addCustomTexture("machine_industrialcentrifuge_lv", 0, 9, location("centrifuge_front"));
		addCustomTexture(GTMod.MODID + "_materials", 15, 0, location("particle"));

	}

	private static ResourceLocation location(String name) {
		return new ResourceLocation(GTMod.MODID, "animations/" + name);
	}

	public static void collectBasicTileSprites() {
		for (String string : GTBlocks.textureTileBasic) {
			addSprite(new Sprites.SpriteData(string, GTMod.MODID + ":textures/sprites/" + string + ".png",
					new Sprites.SpriteInfo(1, 12)));
			addTextureEntry(new Sprites.TextureEntry(string, 0, 0, 1, 12));
		}
	}

	public static void collectCustomTileSprites() {
		for (String string : GTBlocks.textureTileCustom) {
			addSprite(new Sprites.SpriteData(string, GTMod.MODID + ":textures/sprites/" + string + ".png",
					new Sprites.SpriteInfo(1, 12)));
			addTextureEntry(new Sprites.TextureEntry(string, 0, 0, 1, 12));
		}
	}
}
