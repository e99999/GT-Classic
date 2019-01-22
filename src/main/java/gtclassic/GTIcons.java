package gtclassic;

import static ic2.core.platform.textures.Ic2Icons.addCustomTexture;
import static ic2.core.platform.textures.Ic2Icons.addSprite;
import static ic2.core.platform.textures.Ic2Icons.addTextureEntry;

import java.util.EnumSet;

import gtclassic.block.GTBlockTileBasic;
import gtclassic.block.GTBlockTileCustom;
import ic2.core.platform.textures.Sprites;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTIcons {
	@SideOnly(Side.CLIENT)
	public static void loadSprites() {
		addSprite(new Sprites.SpriteData("gtclassic_blocks", "gtclassic:textures/sprites/blocks.png",
				new Sprites.SpriteInfo(16, 16)));
		addSprite(new Sprites.SpriteData("gtclassic_items", "gtclassic:textures/sprites/items.png",
				new Sprites.SpriteInfo(16, 16)));

		addSprite(new Sprites.SpriteData("gtclassic_builder", "gtclassic:textures/sprites/builder.png",
				new Sprites.SpriteInfo(1, 12)));

		iterateBasicTileSpriteEnum();
		iterateCustomTileSpriteEnum();

		addTextureEntry(new Sprites.TextureEntry("gtclassic_builder", 0, 0, 1, 12));
		addTextureEntry(new Sprites.TextureEntry("gtclassic_blocks", 0, 0, 16, 8));
		addTextureEntry(new Sprites.TextureEntry("gtclassic_items", 0, 0, 16, 5));

		addCustomTexture("tile_quantumchest_lv", 0, 1, location("screen0"));
		addCustomTexture("machine_fusioncomputer_iv", 0, 7, location("screen1"));
		addCustomTexture("machine_industrialcentrifuge_lv", 0, 7, location("centrifuge_top"));
		addCustomTexture("machine_industrialcentrifuge_lv", 0, 8, location("centrifuge_back"));
		addCustomTexture("machine_industrialcentrifuge_lv", 0, 9, location("centrifuge_front"));

	}

	private static ResourceLocation location(String name) {
		return new ResourceLocation(GTClassic.MODID, "animations/" + name);
	}

	public static void iterateBasicTileSpriteEnum() {
		EnumSet.allOf(GTBlockTileBasic.GTBlockTileBasicVariants.class)
				.forEach(variant -> addSprite(new Sprites.SpriteData("" + variant.toString().toLowerCase(),
						"gtclassic:textures/sprites/" + variant.toString().toLowerCase() + ".png",
						new Sprites.SpriteInfo(1, 12))));
		EnumSet.allOf(GTBlockTileBasic.GTBlockTileBasicVariants.class).forEach(variant -> addTextureEntry(
				new Sprites.TextureEntry("" + variant.toString().toLowerCase(), 0, 0, 1, 12)));

	}
	
	public static void iterateCustomTileSpriteEnum() {
		EnumSet.allOf(GTBlockTileCustom.GTBlockTileCustomVariants.class)
				.forEach(variant -> addSprite(new Sprites.SpriteData("" + variant.toString().toLowerCase(),
						"gtclassic:textures/sprites/" + variant.toString().toLowerCase() + ".png",
						new Sprites.SpriteInfo(1, 12))));
		EnumSet.allOf(GTBlockTileCustom.GTBlockTileCustomVariants.class).forEach(variant -> addTextureEntry(
				new Sprites.TextureEntry("" + variant.toString().toLowerCase(), 0, 0, 1, 12)));

	}
}
