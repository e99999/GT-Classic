package gtclassic.common;

import java.util.HashMap;
import java.util.Map;

import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.LazyLoadBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GTTextures extends LazyLoadBase<Ic2Icons> {
	
	private static final Map<Block, TextureAtlasSprite[]> TEXTURE_MAP = new HashMap<>();
	
	public static void buildSprites() {
		setTexture(GTBlocks.tileComputer, GTIcons.buildToggleTexture(8, 9));
	}
	
	public static final TextureAtlasSprite[] SET_NULL = GTIcons.buildTexture(0,0,0,0,0,0);
	public static final TextureAtlasSprite[] SET_COMPUTER = GTIcons.buildToggleTexture(8, 9);
	public static final TextureAtlasSprite[] SET_CENTRIFUGE = GTIcons.buildFullTexture(0, 10, 12, 12, 12, 12, 0, 11, 13, 13, 13, 13);
	public static final TextureAtlasSprite[] SET_PLAYERDETECTOR = GTIcons.buildFullTexture(14, 14, 14, 14, 14, 14, 15, 15, 15, 15, 15, 15);
	public static final TextureAtlasSprite[] SET_MOBREPELLER = GTIcons.buildFullTexture(3, 16, 3, 3, 3, 3, 3, 17, 3, 3, 3, 3);
	public static final TextureAtlasSprite[] SET_ENERGYTRANSMITTER = GTIcons.buildToggleTexture(16, 17);
	public static final TextureAtlasSprite[] SET_ECHOTRON = GTIcons.buildTexture(18, 18, 18, 18, 18, 18);
	
	private static void setTexture(Block block, TextureAtlasSprite[] set) {
		TEXTURE_MAP.put(block, set);
	}
	
	public static TextureAtlasSprite[] getSprite(Block block) {
		return TEXTURE_MAP.get(block) != null ? TEXTURE_MAP.get(block);
	}

	@Override
	protected Object load() {
		// TODO Auto-generated method stub
		return null;
	}
}
