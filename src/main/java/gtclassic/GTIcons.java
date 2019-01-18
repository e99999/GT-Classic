package gtclassic;

import static ic2.core.platform.textures.Ic2Icons.addCustomTexture;
import static ic2.core.platform.textures.Ic2Icons.addSprite;
import static ic2.core.platform.textures.Ic2Icons.addTextureEntry;

import java.util.EnumSet;

import gtclassic.block.GTBlockTile;
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

		iterateTileSpriteEnum(); // this is fucking cool as shit

		addTextureEntry(new Sprites.TextureEntry("gtclassic_blocks", 0, 0, 16, 8));
		addTextureEntry(new Sprites.TextureEntry("gtclassic_items", 0, 0, 16, 5));

		addCustomTexture("quantumchest", 0, 1, location("screen0"));
		addCustomTexture("fusioncomputer", 0, 7, location("screen1"));
		addCustomTexture("industrialcentrifuge", 0, 7, location("centrifuge_top"));
		addCustomTexture("industrialcentrifuge", 0, 8, location("centrifuge_back"));
		addCustomTexture("industrialcentrifuge", 0, 9, location("centrifuge_front"));

	}

	private static ResourceLocation location(String name) {
		return new ResourceLocation(GTClassic.MODID, "animations/" + name);
	}

	public static void iterateEnumPrint() {
		EnumSet.allOf(GTBlockTile.GTBlockTileVariants.class)
				.forEach(variant -> GTClassic.logger.info("gtclassic_" + variant.toString().toLowerCase()));
	}

	public static void iterateTileSpriteEnum() {
		EnumSet.allOf(GTBlockTile.GTBlockTileVariants.class)
				.forEach(variant -> addSprite(new Sprites.SpriteData("" + variant.toString().toLowerCase(),
						"gtclassic:textures/sprites/" + variant.toString().toLowerCase() + ".png",
						new Sprites.SpriteInfo(1, 12))));
		EnumSet.allOf(GTBlockTile.GTBlockTileVariants.class).forEach(variant -> addTextureEntry(
				new Sprites.TextureEntry("" + variant.toString().toLowerCase(), 0, 0, 1, 12)));

	}
}
