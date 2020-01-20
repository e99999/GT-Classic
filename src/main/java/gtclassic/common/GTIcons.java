package gtclassic.common;

import static ic2.core.platform.textures.Ic2Icons.addCustomTexture;
import static ic2.core.platform.textures.Ic2Icons.addSprite;
import static ic2.core.platform.textures.Ic2Icons.addTextureEntry;

import gtclassic.GTMod;
import ic2.core.platform.textures.Sprites;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTIcons {

	@SideOnly(Side.CLIENT)
	public static void loadSprites() {
		addTexture("gtclassic_blocks", 16, 1);
		addTexture("gtclassic_items", 16, 3);
		addTexture("gtclassic_materials", 16, 1);
		addTexture("gtclassic_ores", 16, 1);
		addTexture("gtclassic_crops", 4, 8);
		addTexture("batteryblocklv", 5, 6);
		addTexture("autocrafter");
		addTexture("chargeomat");
		addTexture("computercube");
		addTexture("industrialcentrifuge");
		addTexture("matterfabricator");
		addTexture("uumassembler");
		addTexture("disassembler");
		addTexture("echotronblock");
		addTexture("digitalchest");
		addTexture("quantumchest");
		addTexture("quantumtank");
		addTexture("playerdetector");
		addTexture("mobrepeller");
		addTexture("energytransmitter");
		addTexture("bedrockminer");
		addTexture("fusionreactor");
		addTexture("lightningrod");
		addTexture("dragoneggenergysiphon");
		addTexture("magicenergyconverter");
		addTexture("magicenergyabsorber");
		addTexture("idsu");
		addTexture("aesu");
		addTexture("lesu");
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
			addCustomTexture("fusionreactor", 0, 7, location("fusion_top"));
			addCustomTexture("quantumchest", 0, 3, location("qchest_front"));
			addCustomTexture("quantumtank", 0, 1, location("qtank_top"));
			addCustomTexture("digitalchest", 0, 1, location("digichest_top"));
			addCustomTexture("bedrockminer", 0, 7, location("miner_top"));
			addCustomTexture("idsu", 0, 0, location("idsu"));
			addCustomTexture("industrialcentrifuge", 0, 7, location("centrifuge_top"));
			addCustomTexture("industrialcentrifuge", 0, 8, location("centrifuge_back"));
			addCustomTexture("industrialcentrifuge", 0, 9, location("centrifuge_front"));
			addCustomTexture("industrialcentrifuge", 0, 10, location("centrifuge_left"));
			addCustomTexture("industrialcentrifuge", 0, 11, location("centrifuge_right"));
		}
	}

	private static void addTexture(String name) {
		addTexture(name, 1, 12);
	}

	private static void addTexture(String name, int x, int y) {
		addSprite(new Sprites.SpriteData(name, GTMod.MODID + ":textures/sprites/" + name
				+ ".png", new Sprites.SpriteInfo(x, y)));
		addTextureEntry(new Sprites.TextureEntry(name, 0, 0, x, y));
	}

	private static ResourceLocation location(String name) {
		return new ResourceLocation(GTMod.MODID, "animations/" + name);
	}
}
