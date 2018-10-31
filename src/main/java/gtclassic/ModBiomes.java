package gtclassic;

import gtclassic.toxicdimension.biome.ToxicBiome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModBiomes {
	
	public static void init() {
		ForgeRegistries.BIOMES.register(ToxicBiome.biome);
	}
	
	public static void initBiomeDict() {
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(ToxicBiome.biome, 10));
		BiomeDictionary.addTypes(ToxicBiome.biome,
				BiomeDictionary.Type.WASTELAND
                );
		
	}

}
