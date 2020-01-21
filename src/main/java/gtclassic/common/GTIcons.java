package gtclassic.common;

import static ic2.core.platform.textures.Ic2Icons.addCustomTexture;
import static ic2.core.platform.textures.Ic2Icons.addSprite;
import static ic2.core.platform.textures.Ic2Icons.addTextureEntry;

import gtclassic.GTMod;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.Sprites;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTIcons {

	public static final String TERRAIN = "gtclassic_terrain";

	@SideOnly(Side.CLIENT)
	public static void loadSprites() {
		addTexture("gtclassic_blocks", 16, 1);
		addTexture("gtclassic_terrain", 16, 16);
		addTexture("gtclassic_items", 16, 3);
		addTexture("gtclassic_materials", 16, 1);
		addTexture("gtclassic_ores", 16, 1);
		addTexture("gtclassic_crops", 4, 8);
		addTexture("batteryblocklv", 5, 6);
		addTexture("autocrafter");
		addTexture("digitalchest");
		addTexture("quantumchest");
		addTexture("quantumtank");
		addTexture("supercondensator");
		addTexture("cabinet");
		addTexture("drum");
		addTexture("worktable");
		addTexture("translocator");
		addTexture("translocatorfluid");
		addTexture("bufferlarge");
		addTexture("buffersmall");
		addTexture("bufferfluid");
		if (GTConfig.general.animatedTextures) {
			
			addCustomTexture("quantumchest", 0, 3, location("qchest_front"));
			addCustomTexture("quantumtank", 0, 1, location("qtank_top"));
			addCustomTexture("digitalchest", 0, 1, location("digichest_top"));
			//fixed
			addCustomTexture("gtclassic_terrain", 11, 0, location("centrifuge_top"));
			addCustomTexture("gtclassic_terrain", 13, 0, location("centrifuge_side"));
			addCustomTexture("gtclassic_terrain", 10, 1, location("miner_top"));
			addCustomTexture("gtclassic_terrain", 14, 2, location("fusion_top"));
			addCustomTexture("gtclassic_terrain", 13, 2, location("idsu"));
			
		}
	}

	public static TextureAtlasSprite[] buildTexture(int bottom, int top, int back, int front, int right, int left) {
		return buildFullTexture(bottom, top, back, front, right, left, bottom, top, back, front, right, left);
	}
	
	public static TextureAtlasSprite[] buildToggleTexture(int off, int on) {
		return buildFullTexture(off, off, off, off, off, off, on, on, on, on, on, on);
	}

	public static TextureAtlasSprite[] buildFullTexture(int bottom, int top, int back, int front, int right, int left,
			int bottomActive, int topActive, int backActive, int frontActive, int rightActive, int leftActive) {
		return new TextureAtlasSprite[] { Ic2Icons.getTextures(TERRAIN)[bottom], Ic2Icons.getTextures(TERRAIN)[top],
				Ic2Icons.getTextures(TERRAIN)[back], Ic2Icons.getTextures(TERRAIN)[front],
				Ic2Icons.getTextures(TERRAIN)[right], Ic2Icons.getTextures(TERRAIN)[left],
				// active state
				Ic2Icons.getTextures(TERRAIN)[bottomActive], Ic2Icons.getTextures(TERRAIN)[topActive],
				Ic2Icons.getTextures(TERRAIN)[backActive], Ic2Icons.getTextures(TERRAIN)[frontActive],
				Ic2Icons.getTextures(TERRAIN)[rightActive], Ic2Icons.getTextures(TERRAIN)[leftActive] };
	}

	// private texture loader helper
	private static void addTexture(String name) {
		addTexture(name, 1, 12);
	}

	// private texture loader helper
	private static void addTexture(String name, int x, int y) {
		addSprite(new Sprites.SpriteData(name, GTMod.MODID + ":textures/sprites/" + name
				+ ".png", new Sprites.SpriteInfo(x, y)));
		addTextureEntry(new Sprites.TextureEntry(name, 0, 0, x, y));
	}

	// private texture loader helper
	private static ResourceLocation location(String name) {
		return new ResourceLocation(GTMod.MODID, "animations/" + name);
	}
}
