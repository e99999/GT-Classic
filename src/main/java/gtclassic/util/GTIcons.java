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
     
        addSprite(new Sprites.SpriteData("gtclassic_builder", "gtclassic:textures/sprites/builder.png", new Sprites.SpriteInfo(1, 12)));
        addSprite(new Sprites.SpriteData("gtclassic_smallbuffer", "gtclassic:textures/sprites/smallbuffer.png", new Sprites.SpriteInfo(1, 12)));
        addSprite(new Sprites.SpriteData("gtclassic_largebuffer", "gtclassic:textures/sprites/largebuffer.png", new Sprites.SpriteInfo(1, 12)));
        addSprite(new Sprites.SpriteData("gtclassic_computercube", "gtclassic:textures/sprites/computercube.png", new Sprites.SpriteInfo(1, 12)));
        addSprite(new Sprites.SpriteData("gtclassic_industrialcentrifuge", "gtclassic:textures/sprites/industrialcentrifuge.png", new Sprites.SpriteInfo(1, 12)));
        addSprite(new Sprites.SpriteData("gtclassic_lapotronicenergysu", "gtclassic:textures/sprites/lapotronicenergysu.png", new Sprites.SpriteInfo(1, 12)));
        addSprite(new Sprites.SpriteData("gtclassic_adjustableenergysu", "gtclassic:textures/sprites/adjustableenergysu.png", new Sprites.SpriteInfo(1, 12)));
        addSprite(new Sprites.SpriteData("gtclassic_interdimensionalenergysu", "gtclassic:textures/sprites/interdimensionalenergysu.png", new Sprites.SpriteInfo(1, 12)));
        addSprite(new Sprites.SpriteData("gtclassic_lightningrod", "gtclassic:textures/sprites/lightningrod.png", new Sprites.SpriteInfo(1, 12)));
        addSprite(new Sprites.SpriteData("gtclassic_fusionreactor", "gtclassic:textures/sprites/fusionreactor.png", new Sprites.SpriteInfo(1, 12)));
        
        
        addTextureEntry(new Sprites.TextureEntry("gtclassic_blocks", 0, 0, 16, 8));
        addTextureEntry(new Sprites.TextureEntry("gtclassic_items", 0, 0, 16, 5));
        
        addTextureEntry(new Sprites.TextureEntry("gtclassic_builder", 0, 0, 1, 12));
        addTextureEntry(new Sprites.TextureEntry("gtclassic_smallbuffer", 0, 0, 1, 12));
        addTextureEntry(new Sprites.TextureEntry("gtclassic_largebuffer", 0, 0, 1, 12));
        addTextureEntry(new Sprites.TextureEntry("gtclassic_computercube", 0, 0, 1, 12));
        addTextureEntry(new Sprites.TextureEntry("gtclassic_industrialcentrifuge", 0, 0, 1, 12));
        addTextureEntry(new Sprites.TextureEntry("gtclassic_lapotronicenergysu", 0, 0, 1, 12));
        addTextureEntry(new Sprites.TextureEntry("gtclassic_adjustableenergysu", 0, 0, 1, 12));
        addTextureEntry(new Sprites.TextureEntry("gtclassic_interdimensionalenergysu", 0, 0, 1, 12));
        addTextureEntry(new Sprites.TextureEntry("gtclassic_lightningrod", 0, 0, 1, 12));
        addTextureEntry(new Sprites.TextureEntry("gtclassic_fusionreactor", 0, 0, 1, 12));
        
    }
}
