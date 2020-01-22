package gtclassic.common;

import static ic2.core.platform.textures.Ic2Icons.addCustomTexture;
import static ic2.core.platform.textures.Ic2Icons.addSprite;
import static ic2.core.platform.textures.Ic2Icons.addTextureEntry;

import java.util.HashMap;
import java.util.Map;

import gtclassic.GTMod;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.Sprites;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTIcons {

	private static final Map<Block, int[]> TEXTURE_MAP = new HashMap<>();
	private static final int[] SET_NULL = { 110, 111, 110, 110, 110, 110 };

	@SideOnly(Side.CLIENT)
	public static void loadSprites() {
		addTexture("gtclassic_terrain", 16, 16);
		addTexture("gtclassic_items", 16, 3);
		addTexture("gtclassic_materials", 16, 1);
		addTexture("gtclassic_ores", 16, 1);
		addTexture("gtclassic_crops", 4, 8);
		addTexture("batteryblocklv", 5, 6);
		if (GTConfig.general.animatedTextures) {
			addCustomTexture("gtclassic_terrain", 0, 4, location("digichest_top"));
			addCustomTexture("gtclassic_terrain", 11, 0, location("centrifuge_top"));
			addCustomTexture("gtclassic_terrain", 13, 0, location("centrifuge_side"));
			addCustomTexture("gtclassic_terrain", 10, 1, location("miner_top"));
			addCustomTexture("gtclassic_terrain", 15, 2, location("fusion_top"));
			addCustomTexture("gtclassic_terrain", 13, 2, location("idsu"));
		}
		setTexture(GTBlocks.tileComputer, 8, 9);
		setTexture(GTBlocks.tileCentrifuge, 0, 10, 12, 12, 12, 12, 0, 11, 13, 13, 13, 13);
		setTexture(GTBlocks.tilePlayerDetector, 14, 14, 14, 14, 14, 14, 15, 15, 15, 15, 15, 15);
		setTexture(GTBlocks.tileMobRepeller, 3, 16, 3, 3, 3, 3, 3, 17, 3, 3, 3, 3);
		setTexture(GTBlocks.tileEnergyTransmitter, 16, 17);
		setTexture(GTBlocks.tileEchotron, 18, 18, 18, 18, 18, 18);
		setTexture(GTBlocks.tileChargeOmat, 3, 19, 3, 3, 3, 3, 3, 20, 3, 3, 3, 3);
		setTexture(GTBlocks.tileDisassembler, 0, 21, 2, 22, 2, 2, 0, 21, 2, 23, 2, 2);
		setTexture(GTBlocks.tileBedrockMiner, 24, 25, 2, 22, 2, 2, 24, 26, 2, 23, 2, 2);
		setTexture(GTBlocks.tileDragonEggEnergySiphon, 27, 29, 27, 27, 27, 27, 28, 29, 28, 28, 28, 28);
		setTexture(GTBlocks.tileMagicEnergyConverter, 30, 30, 30, 27, 30, 30, 30, 30, 30, 31, 30, 30);
		setTexture(GTBlocks.tileMagicEnergyAbsorber, 32, 33);
		setTexture(GTBlocks.tileFabricator, 34, 35);
		setTexture(GTBlocks.tileUUMAssembler, 36, 37, 38, 38, 38, 38);
		setTexture(GTBlocks.tileLightningRod, 41, 44, 41, 41, 41, 41);
		setTexture(GTBlocks.tileFusionReactor, 39, 47, 41, 41, 42, 42, 39, 47, 41, 41, 43, 43);
		setTexture(GTBlocks.tileLESU, 48, 48, 48, 49, 48, 48);
		setTexture(GTBlocks.tileAESU, 39, 39, 39, 40, 39, 39);
		setTexture(GTBlocks.tileIDSU, 45, 45, 45, 41, 45, 45);
		setTexture(GTBlocks.tileSupercondensator, 50, 50, 50, 51, 50, 50, 51, 51, 51, 50, 51, 51);
		setTexture(GTBlocks.tileWorktable, 0, 65, 67, 67, 67, 67, 0, 66, 67, 67, 67, 67);
		setTexture(GTBlocks.tileCabinet, 0, 1, 2, 68, 2, 2);
		setTexture(GTBlocks.tileDrum, 0, 73, 72, 72, 72, 72);
		setTexture(GTBlocks.tileDigitalChest, 0, 64, 70, 71, 70, 70);
		setTexture(GTBlocks.tileQuantumChest, 0, 1, 2, 64, 2, 2);
		setTexture(GTBlocks.tileQuantumTank, 0, 64, 2, 2, 2, 2);
		setTexture(GTBlocks.tileAutocrafter, 59, 59, 60, 61, 59, 59);
		setTexture(GTBlocks.tileTranslocator, 80, 81, 60, 61, 82, 83);
		setTexture(GTBlocks.tileBufferLarge, 84, 85, 57, 61, 86, 87);
		setTexture(GTBlocks.tileBufferSmall, 88, 89, 56, 61, 90, 91);
		setTexture(GTBlocks.tileTranslocatorFluid, 92, 93, 62, 63, 94, 95);
		setTexture(GTBlocks.tileBufferFluid, 92, 93, 58, 63, 94, 95);
	}

	public static TextureAtlasSprite getTexture(int id) {
		return Ic2Icons.getTextures("gtclassic_terrain")[id];
	}

	public static TextureAtlasSprite[] getTextureData(Block block) {
		return TEXTURE_MAP.containsKey(block) ? buildDyanmicTexture(TEXTURE_MAP.get(block))
				: buildDyanmicTexture(SET_NULL);
	}

	private static void setTexture(Block block, int... values) {
		TEXTURE_MAP.put(block, values);
	}

	public static TextureAtlasSprite[] buildDyanmicTexture(int[] arr) {
		if (arr.length == 2) {
			return buildToggleTexture(arr[0], arr[1]);
		}
		if (arr.length == 6) {
			return buildTexture(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]);
		}
		if (arr.length == 12) {
			return buildFullTexture(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7], arr[8], arr[9], arr[10], arr[11]);
		}
		return buildToggleTexture(arr[0], arr[0]);
	}

	public static TextureAtlasSprite[] buildTexture(int bottom, int top, int back, int front, int right, int left) {
		return buildFullTexture(bottom, top, back, front, right, left, bottom, top, back, front, right, left);
	}

	public static TextureAtlasSprite[] buildToggleTexture(int off, int on) {
		return buildFullTexture(off, off, off, off, off, off, on, on, on, on, on, on);
	}

	public static TextureAtlasSprite[] buildFullTexture(int bottom, int top, int back, int front, int right, int left,
			int bottomActive, int topActive, int backActive, int frontActive, int rightActive, int leftActive) {
		return new TextureAtlasSprite[] { Ic2Icons.getTextures("gtclassic_terrain")[bottom],
				Ic2Icons.getTextures("gtclassic_terrain")[top], Ic2Icons.getTextures("gtclassic_terrain")[back],
				Ic2Icons.getTextures("gtclassic_terrain")[front], Ic2Icons.getTextures("gtclassic_terrain")[right],
				Ic2Icons.getTextures("gtclassic_terrain")[left],
				// active state
				Ic2Icons.getTextures("gtclassic_terrain")[bottomActive],
				Ic2Icons.getTextures("gtclassic_terrain")[topActive],
				Ic2Icons.getTextures("gtclassic_terrain")[backActive],
				Ic2Icons.getTextures("gtclassic_terrain")[frontActive],
				Ic2Icons.getTextures("gtclassic_terrain")[rightActive],
				Ic2Icons.getTextures("gtclassic_terrain")[leftActive] };
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
