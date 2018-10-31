package gtclassic;

import gtclassic.toxicdimension.biome.ToxicBiome;

import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModBiomes {
	
	public static void init() {
		ForgeRegistries.BIOMES.register(ToxicBiome.biome);
	}

}
