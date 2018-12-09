package gtclassic.util;

import static ic2.core.platform.textures.Ic2Icons.addSprite;
import static ic2.core.platform.textures.Ic2Icons.addTextureEntry;

import ic2.core.platform.textures.Sprites;
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
		addSprite(new Sprites.SpriteData("gtclassic_autocrafter", "gtclassic:textures/sprites/autocrafter.png",
				new Sprites.SpriteInfo(1, 12)));
		addSprite(new Sprites.SpriteData("gtclassic_chargeomat", "gtclassic:textures/sprites/chargeomat.png",
				new Sprites.SpriteInfo(1, 12)));
		addSprite(new Sprites.SpriteData("gtclassic_computercube", "gtclassic:textures/sprites/computercube.png",
				new Sprites.SpriteInfo(1, 12)));
		addSprite(new Sprites.SpriteData("gtclassic_industrialcentrifuge",
				"gtclassic:textures/sprites/industrialcentrifuge.png", new Sprites.SpriteInfo(1, 12)));
		addSprite(new Sprites.SpriteData("gtclassic_matterfabricator",
				"gtclassic:textures/sprites/matterfabricator.png", new Sprites.SpriteInfo(1, 12)));
		addSprite(new Sprites.SpriteData("gtclassic_playerdetector", "gtclassic:textures/sprites/playerdetector.png",
				new Sprites.SpriteInfo(1, 12)));
		addSprite(new Sprites.SpriteData("gtclassic_uumatterassembler", "gtclassic:textures/sprites/uumassembler.png",
				new Sprites.SpriteInfo(1, 12)));
		addSprite(new Sprites.SpriteData("gtclassic_sonictron", "gtclassic:textures/sprites/sonictron.png",
				new Sprites.SpriteInfo(1, 12)));

		addSprite(new Sprites.SpriteData("gtclassic_smallchest", "gtclassic:textures/sprites/smallchest.png",
				new Sprites.SpriteInfo(1, 12)));
		addSprite(new Sprites.SpriteData("gtclassic_largechest", "gtclassic:textures/sprites/largechest.png",
				new Sprites.SpriteInfo(1, 12)));
		addSprite(new Sprites.SpriteData("gtclassic_quantumchest", "gtclassic:textures/sprites/quantumchest.png",
				new Sprites.SpriteInfo(1, 12)));

		addSprite(new Sprites.SpriteData("gtclassic_hugeenergysu", "gtclassic:textures/sprites/hugeenergysu.png",
				new Sprites.SpriteInfo(1, 12)));
		addSprite(new Sprites.SpriteData("gtclassic_interdimensionalenergysu",
				"gtclassic:textures/sprites/interdimensionalenergysu.png", new Sprites.SpriteInfo(1, 12)));
		addSprite(new Sprites.SpriteData("gtclassic_lightningrod", "gtclassic:textures/sprites/lightningrod.png",
				new Sprites.SpriteInfo(1, 12)));
		addSprite(new Sprites.SpriteData("gtclassic_fusioncomputer", "gtclassic:textures/sprites/fusioncomputer.png",
				new Sprites.SpriteInfo(1, 12)));
		addSprite(new Sprites.SpriteData("gtclassic_supercondensator",
				"gtclassic:textures/sprites/supercondensator.png", new Sprites.SpriteInfo(1, 12)));
		addSprite(new Sprites.SpriteData("gtclassic_superconductorwire",
				"gtclassic:textures/sprites/superconductorwire.png", new Sprites.SpriteInfo(1, 12)));

		addTextureEntry(new Sprites.TextureEntry("gtclassic_blocks", 0, 0, 16, 8));
		addTextureEntry(new Sprites.TextureEntry("gtclassic_items", 0, 0, 16, 5));

		addTextureEntry(new Sprites.TextureEntry("gtclassic_builder", 0, 0, 1, 12));
		addTextureEntry(new Sprites.TextureEntry("gtclassic_autocrafter", 0, 0, 1, 12));
		addTextureEntry(new Sprites.TextureEntry("gtclassic_chargeomat", 0, 0, 1, 12));
		addTextureEntry(new Sprites.TextureEntry("gtclassic_computercube", 0, 0, 1, 12));
		addTextureEntry(new Sprites.TextureEntry("gtclassic_industrialcentrifuge", 0, 0, 1, 12));
		addTextureEntry(new Sprites.TextureEntry("gtclassic_matterfabricator", 0, 0, 1, 12));
		addTextureEntry(new Sprites.TextureEntry("gtclassic_playerdetector", 0, 0, 1, 12));
		addTextureEntry(new Sprites.TextureEntry("gtclassic_uumatterassembler", 0, 0, 1, 12));
		addTextureEntry(new Sprites.TextureEntry("gtclassic_sonictron", 0, 0, 1, 12));

		addTextureEntry(new Sprites.TextureEntry("gtclassic_smallchest", 0, 0, 1, 12));
		addTextureEntry(new Sprites.TextureEntry("gtclassic_largechest", 0, 0, 1, 12));
		addTextureEntry(new Sprites.TextureEntry("gtclassic_quantumchest", 0, 0, 1, 12));

		addTextureEntry(new Sprites.TextureEntry("gtclassic_hugeenergysu", 0, 0, 1, 12));
		addTextureEntry(new Sprites.TextureEntry("gtclassic_interdimensionalenergysu", 0, 0, 1, 12));
		addTextureEntry(new Sprites.TextureEntry("gtclassic_lightningrod", 0, 0, 1, 12));
		addTextureEntry(new Sprites.TextureEntry("gtclassic_fusioncomputer", 0, 0, 1, 12));
		addTextureEntry(new Sprites.TextureEntry("gtclassic_supercondensator", 0, 0, 1, 12));
		addTextureEntry(new Sprites.TextureEntry("gtclassic_superconductorwire", 0, 0, 1, 12));

	}
}
