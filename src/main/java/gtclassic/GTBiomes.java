package gtclassic;

import gtclassic.toxicdimension.biome.GTToxicBiome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class GTBiomes {

	private GTBiomes() {
		throw new IllegalStateException("Biome Class");
	}

	public static void init() {
		ForgeRegistries.BIOMES.register(GTToxicBiome.biome);
	}

	public static void initBiomeDict() {
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(GTToxicBiome.biome, 0));
		BiomeDictionary.addTypes(GTToxicBiome.biome, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.DEAD);

	}

}
