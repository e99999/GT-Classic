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
	private static final int[] SET_NULL = { 111, 111 };
	public static final TextureAtlasSprite[] SET_EMPTY = {};

	private GTIcons() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Null texture key: 110 (Black) = The array passed to the texture builder was
	 * an incorrect size. 111 (Red) = The block was not added to the texture map.
	 */
	@SideOnly(Side.CLIENT)
	public static void loadSprites() {
		addTexture("gtclassic_terrain", 16, 7);
		addTexture("gtclassic_items", 16, 3);
		addTexture("gtclassic_materials", 16, 1);
		addTexture("gtclassic_ores", 16, 1);
		addTexture("gtclassic_crops", 4, 8);
		addAnimatedTexture("screen", 0, 4);
		addAnimatedTexture("screenadv", 15, 2);
		addAnimatedTexture("displayscreen", 13, 6);
		addAnimatedTexture("centrifuge_top", 11, 0);
		addAnimatedTexture("centrifuge_side", 13, 0);
		addAnimatedTexture("miner_top", 10, 1);
		addAnimatedTexture("miner_front", 9, 0);
		addAnimatedTexture("idsu", 13, 2);
		addAnimatedTexture("idsu2", 14, 2);
		setTexture(GTBlocks.tileCharcoalPit, 0, 25, 2, 2, 2, 2, 0, 26, 2, 2, 2, 2);
		setTexture(GTBlocks.tileComputer, 7, 7, 7, 7, 7, 7);
		setTexture(GTBlocks.tileCentrifuge, 0, 10, 12, 12, 12, 12, 0, 11, 13, 13, 13, 13);
		setTexture(GTBlocks.tilePlayerDetector, 14, 14, 14, 14, 14, 14, 15, 15, 15, 15, 15, 15);
		setTexture(GTBlocks.tileMobRepeller, 3, 16, 3, 3, 3, 3, 3, 17, 3, 3, 3, 3);
		setTexture(GTBlocks.tileEnergyTransmitter, 16, 17);
		setTexture(GTBlocks.tileEchotron, 18, 18, 18, 18, 18, 18);
		setTexture(GTBlocks.tileChargeOmat, 3, 19, 3, 3, 3, 3, 3, 20, 3, 3, 3, 3);
		setTexture(GTBlocks.tileDisassembler, 0, 21, 2, 22, 2, 2, 0, 21, 2, 23, 2, 2);
		setTexture(GTBlocks.tileBedrockMiner, 96, 25, 98, 8, 2, 2, 96, 26, 98, 9, 2, 2);
		setTexture(GTBlocks.tileDragonEggEnergySiphon, 24, 29, 24, 24, 24, 24, 28, 29, 28, 28, 28, 28);
		setTexture(GTBlocks.tileMagicEnergyConverter, 30, 30, 30, 24, 30, 30, 30, 30, 30, 31, 30, 30);
		setTexture(GTBlocks.tileMagicEnergyAbsorber, 32, 33);
		setTexture(GTBlocks.tileFabricator, 34, 35);
		setTexture(GTBlocks.tileUUMAssembler, 36, 37, 38, 38, 38, 38);
		setTexture(GTBlocks.tileLightningRod, 41, 44, 41, 41, 41, 41);
		setTexture(GTBlocks.tileFusionReactor, 39, 47, 41, 41, 42, 42, 39, 47, 41, 41, 43, 43);
		setTexture(GTBlocks.tileLESU, 48, 48, 48, 49, 48, 48);
		setTexture(GTBlocks.tileAESU, 39, 39, 39, 40, 39, 39);
		setTexture(GTBlocks.tileIDSU, 45, 45, 45, 41, 45, 45);
		setTexture(GTBlocks.tileTesseractMaster, 46, 46, 55, 47, 46, 46);
		setTexture(GTBlocks.tileTesseractSlave, 46, 46, 46, 47, 46, 46);
		setTexture(GTBlocks.tileSupercondensator, 50, 50, 50, 51, 50, 50, 51, 51, 51, 50, 51, 51);
		setTexture(GTBlocks.tileWorktable, 0, 65, 67, 67, 67, 67, 0, 66, 67, 67, 67, 67);
		setTexture(GTBlocks.tileCabinet, 0, 1, 2, 68, 2, 2);
		setTexture(GTBlocks.tileDrum, 0, 73, 72, 72, 72, 72);
		setTexture(GTBlocks.tileDigitalChest, 0, 64, 70, 71, 70, 70);
		setTexture(GTBlocks.tileQuantumChest, 0, 1, 2, 64, 2, 2);
		setTexture(GTBlocks.tileQuantumTank, 0, 64, 2, 2, 2, 2);
		setTexture(GTBlocks.tileAutocrafter, 59, 59, 60, 61, 59, 59);
		setTexture(GTBlocks.tileTranslocator, 80, 81, 60, 61, 82, 83);
		setTexture(GTBlocks.tileTypeFilter, 54, 54, 54, 61, 54, 54);
		setTexture(GTBlocks.tileItemFilter, 54, 54, 54, 61, 54, 54);
		setTexture(GTBlocks.tileBufferLarge, 84, 85, 57, 61, 86, 87);
		setTexture(GTBlocks.tileBufferSmall, 88, 89, 56, 61, 90, 91);
		setTexture(GTBlocks.tileTranslocatorFluid, 92, 93, 62, 63, 94, 95);
		setTexture(GTBlocks.tileBufferFluid, 92, 93, 58, 63, 94, 95);
		setTexture(GTBlocks.tileRedstoneTransmitter, 0, 64, 2, 24, 2, 2, 0, 64, 2, 27, 2, 2);
		setTexture(GTBlocks.tileRedstoneReceiver, 24, 27);
		setTexture(GTBlocks.tileBlockExtender, 96, 97, 55, 98, 98, 98);
		setTexture(GTBlocks.tileRockBreaker, 53, 53, 53, 61, 53, 53);
		setTexture(GTBlocks.tileDisplayScreen, 0, 1, 2, 108, 2, 2, 0, 1, 2, 109, 2, 2);
		setTexture(GTBlocks.tileLamp, 99, 100);
	}

	/**
	 * Getting a dynamically generated sprite array for a block
	 * 
	 * @param block - the block to get sprite data for
	 * @return - will return the sprite sheet if present or missing gtc texture (set
	 *         null)
	 */
	public static TextureAtlasSprite[] getTextureData(Block block) {
		return TEXTURE_MAP.containsKey(block) ? textureHelper(TEXTURE_MAP.get(block)) : textureHelper(SET_NULL);
	}

	/**
	 * 
	 * @param block  to make textures for
	 * @param values the spirte locations for the block texture
	 */
	private static void setTexture(Block block, int... values) {
		TEXTURE_MAP.put(block, values);
	}

	/**
	 * 
	 * @param arr size determines style of texture, 2 = off/on all sides, 6 = all
	 *            side but single state, 12 = all sides full state
	 * @return the constructed sprite
	 */
	private static TextureAtlasSprite[] textureHelper(int[] arr) {
		if (arr.length == 2) {
			return buildTexture(arr[0], arr[0], arr[0], arr[0], arr[0], arr[0], arr[1], arr[1], arr[1], arr[1], arr[1], arr[1]);
		}
		if (arr.length == 6) {
			return buildTexture(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]);
		}
		if (arr.length == 12) {
			return buildTexture(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7], arr[8], arr[9], arr[10], arr[11]);
		}
		return buildTexture(arr[110], arr[110], arr[110], arr[110], arr[110], arr[110], arr[110], arr[110], arr[110], arr[110], arr[110], arr[110]);
	}

	/** How to make a custom texture **/
	public static TextureAtlasSprite[] buildTexture(int... arr) {
		TextureAtlasSprite[] texture = new TextureAtlasSprite[arr.length];
		for (int i = 0; i < arr.length; ++i) {
			texture[i] = Ic2Icons.getTextures("gtclassic_terrain")[arr[i]];
		}
		return texture;
	}

	/**
	 * Loads a sprite sheet
	 * 
	 * @param the name of the file
	 * @param x   size
	 * @param y   size
	 */
	private static void addTexture(String name, int x, int y) {
		addSprite(new Sprites.SpriteData(name, GTMod.MODID + ":textures/sprites/" + name
				+ ".png", new Sprites.SpriteInfo(x, y)));
		addTextureEntry(new Sprites.TextureEntry(name, 0, 0, x, y));
	}

	/**
	 * Overwrites a sprite entry with an animated texture
	 * 
	 * @param filename
	 * @param x        position to overwrite
	 * @param y        positon to overwrite
	 */
	private static void addAnimatedTexture(String filename, int x, int y) {
		if (GTConfig.general.animatedTextures) {
			addCustomTexture("gtclassic_terrain", x, y, new ResourceLocation(GTMod.MODID, "animations/" + filename));
		}
	}
}
