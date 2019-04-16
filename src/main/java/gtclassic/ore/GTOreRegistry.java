package gtclassic.ore;

import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraftforge.fml.common.registry.GameRegistry;

public enum GTOreRegistry {
	BAUXITE(0, GTMaterial.Bauxite, 32, 2, 40, 100), BISMUTHTINE(1, GTMaterial.Bismuthtine, 8, 8, 50, 80),
	CALCITE(2, GTMaterial.Calcite, 8, 12, 20, 80), CASSITERITE(3, GTMaterial.Cassiterite, 32, 2, 60, 120),
	CHROMITE(4, GTMaterial.Chromite, 8, 4, 0, 16), CINNABAR(5, GTMaterial.Cinnabar, 8, 8, 0, 16),
	CRYOLITE(6, GTMaterial.Cryolite, 8, 8, 50, 60), GALENA(7, GTMaterial.Galena, 16, 1, 30, 50),
	GARNIERITE(8, GTMaterial.Garnierite, 16, 1, 20, 40), GRAPHITE(9, GTMaterial.Graphite, 8, 2, 5, 20),
	IRIDIUM(10, GTMaterial.Iridium, 2, 1, 0, 128), ANTHRACITE(11, GTMaterial.Coal, 32, 1, 0, 128),
	LIMONITE(12, GTMaterial.Limonite, 8, 8, 40, 80), MAGNETITE(13, GTMaterial.Magnetite, 24, 1, 60, 120),
	MALACHITE(14, GTMaterial.Malachite, 16, 2, 40, 80), OLIVINE(15, GTMaterial.Olivine, 8, 4, 0, 16),
	PYRITE(16, GTMaterial.Pyrite, 16, 2, 20, 40), SHELDONITE(17, GTMaterial.Sheldonite, 8, 1, 50, 60),
	RUBY(18, GTMaterial.Ruby, 8, 2, 0, 20), SALTPETER(19, GTMaterial.Saltpeter, 16, 1, 40, 80),
	SAPPHIRE(20, GTMaterial.Sapphire, 8, 2, 0, 20), SODALITE(21, GTMaterial.Sodalite, 12, 1, 10, 24),
	SPHALERITE(22, GTMaterial.Sphalerite, 24, 1, 8, 24), SULFUR(23, GTMaterial.Sulfur, 16, 1, 8, 24),
	TANTALITE(24, GTMaterial.Tantalite, 8, 4, 0, 20), TETRAHEDRITE(25, GTMaterial.Tetrahedrite, 32, 2, 60, 120),
	TUNGSTATE(26, GTMaterial.Tungstate, 4, 4, 0, 16), BASALT(27, GTMaterial.Basalt, 18, 2, 16, 50),
	PYROLUSITE(28, GTMaterial.Pyrolusite, 16, 1, 24, 50), SALT(29, GTMaterial.Salt, 32, 2, 40, 120),
	MOLYBDENITE(30, GTMaterial.Molybdenite, 8, 2, 30, 50), VIBRANIUM(31, GTMaterial.Vibranium, 2, 1, 0, 10);

	private int id;
	private GTMaterial mat;
	private int size;
	private int chance;
	private int min;
	private int max;

	GTOreRegistry(int id, GTMaterial mat, int size, int chance, int min, int max) {
		this.mat = mat;
		this.id = id;
		this.size = size;
		this.chance = chance;
		this.min = min;
		this.max = max;

	}

	public int getId() {
		return this.id;
	}

	public GTMaterial getMaterial() {
		return this.mat;
	}

	public int getSize() {
		return this.size;
	}

	public int getChance() {
		return this.chance;
	}

	public int getMinY() {
		return this.min;
	}

	public int getMaxY() {
		return this.max;
	}

	public static void oreDirectSmelting() {
		for (Block block : Block.REGISTRY) {

			if (block instanceof GTOreStone) {
				GTOreStone ore = (GTOreStone) block;

				if (ore.getOreEntry().equals(GALENA)) {
					GameRegistry.addSmelting(block, GTMaterialGen.getNugget(GTMaterial.Lead, 6), 0.1F);
				}

				if (ore.getOreEntry().equals(GARNIERITE)) {
					GameRegistry.addSmelting(block, GTMaterialGen.getNugget(GTMaterial.Nickel, 6), 0.1F);
				}

				if (ore.getOreEntry().equals(IRIDIUM)) {
					GameRegistry.addSmelting(block, GTMaterialGen.getIc2(Ic2Items.iridiumOre, 1), 0.1F);
				}

				if (ore.getOreEntry().equals(LIMONITE) || ore.getOreEntry().equals(PYRITE)) {
					GameRegistry.addSmelting(block, GTMaterialGen.get(Items.IRON_NUGGET, 3), 0.1F);
				}

				if (ore.getOreEntry().equals(MALACHITE)) {
					GameRegistry.addSmelting(block, GTMaterialGen.getNugget(GTMaterial.Copper, 1), 0.1F);
				}

				if (ore.getOreEntry().equals(OLIVINE)) {
					GameRegistry.addSmelting(block, GTMaterialGen.getGem(GTMaterial.Olivine, 1), 0.1F);
				}

				if (ore.getOreEntry().equals(RUBY)) {
					GameRegistry.addSmelting(block, GTMaterialGen.getGem(GTMaterial.Ruby, 1), 0.1F);
				}

				if (ore.getOreEntry().equals(SAPPHIRE)) {
					GameRegistry.addSmelting(block, GTMaterialGen.getGem(GTMaterial.Sapphire, 1), 0.1F);
				}

				if (ore.getOreEntry().equals(SPHALERITE)) {
					GameRegistry.addSmelting(block, GTMaterialGen.getNugget(GTMaterial.Zinc, 3), 0.1F);
				}

				if (ore.getOreEntry().equals(CASSITERITE)) {
					GameRegistry.addSmelting(block, GTMaterialGen.getNugget(GTMaterial.Tin, 6), 0.1F);
				}

				if (ore.getOreEntry().equals(MAGNETITE)) {
					GameRegistry.addSmelting(block, GTMaterialGen.get(Items.IRON_NUGGET, 3), 0.1F);
				}
			}

		}

	}

}
