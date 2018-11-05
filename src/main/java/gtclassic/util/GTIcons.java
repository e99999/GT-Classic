package gtclassic.util;

import ic2.core.platform.textures.Sprites;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static ic2.core.platform.textures.Ic2Icons.addSprite;
import static ic2.core.platform.textures.Ic2Icons.addTextureEntry;

public class GTIcons {
    @SideOnly(Side.CLIENT)
    public static void loadSprites(){
        addSprite(new Sprites.SpriteData("gtclassic_blocks", "gtclassic:textures/sprites/blocks.png", new Sprites.SpriteInfo(16, 16)));
        addSprite(new Sprites.SpriteData("gtclassic_items", "gtclassic:textures/sprites/items.png", new Sprites.SpriteInfo(16, 16)));
        addSprite(new Sprites.SpriteData("gtclassic_dusts", "gtclassic:textures/sprites/dusts.png", new Sprites.SpriteInfo(16, 16)));
        addSprite(new Sprites.SpriteData("gtclassic_duststiny", "gtclassic:textures/sprites/duststiny.png", new Sprites.SpriteInfo(16, 16)));
        addSprite(new Sprites.SpriteData("gtclassic_nuggets", "gtclassic:textures/sprites/nuggets.png", new Sprites.SpriteInfo(16, 16)));
        addTextureEntry(new Sprites.TextureEntry("gtclassic_blocks", 0, 0, 16, 2));
        addTextureEntry(new Sprites.TextureEntry("gtclassic_blocks", 0, 6, 12, 7));
        addTextureEntry(new Sprites.TextureEntry("gtclassic_dusts", 0, 0, 16, 4));
        addTextureEntry(new Sprites.TextureEntry("gtclassic_duststiny", 0, 0, 16, 6));
        addTextureEntry(new Sprites.TextureEntry("gtclassic_nuggets", 0, 0, 16, 1));
    }
}
