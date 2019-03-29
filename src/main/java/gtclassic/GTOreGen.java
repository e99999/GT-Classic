package gtclassic;

import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public enum GTOreGen {

	BAUXITE(0, GTMaterial.Bauxite, Blocks.STONE, 1, 3.0F, false, null),
	BISMUTHTINE(1, GTMaterial.Bismuthtine, Blocks.SAND, 0, 0.5F, true, GTMaterialGen.getNugget(GTMaterial.Bismuth, 3)),
	CALCITE(2, GTMaterial.Calcite, Blocks.STONE, 1, 3.0F, false, null),
	CASSITERITE(3, GTMaterial.Cassiterite, Blocks.SAND, 0, 0.5F, true, GTMaterialGen.getNugget(GTMaterial.Tin, 3)),
	CHROMITE(4, GTMaterial.Chromite, Blocks.STONE, 3, 5.0F, false, null),
	CINNABAR(5, GTMaterial.Cinnabar, Blocks.STONE, 2, 3.0F, false, null),
	CRYOLITE(6, GTMaterial.Cryolite, Blocks.STONE, 1, 3.0F, false, null),
	GALENA(7, GTMaterial.Galena, Blocks.STONE, 1, 3.0F, true, GTMaterialGen.getNugget(GTMaterial.Lead, 3)),
	GARNIERITE(8, GTMaterial.Garnierite, Blocks.STONE, 1, 3.0F, true, GTMaterialGen.getNugget(GTMaterial.Nickel, 3)),
	GRAPHITE(9, GTMaterial.Graphite, Blocks.STONE, 2, 3.0F, false, null),
	IRIDIUM(10, GTMaterial.Iridium, Blocks.STONE, 3, 20.0F, true, GTMaterialGen.getIc2(Ic2Items.iridiumOre, 1)),
	LIGNITE(11, GTMaterial.Coal, Blocks.STONE, 1, 3.0F, false, null),
	LIMONITE(12, GTMaterial.Limonite, Blocks.STONE, 1, 3.0F, true, GTMaterialGen.get(Items.IRON_NUGGET, 3)),
	MAGNETITE(13, GTMaterial.Magnetite, Blocks.SAND, 0, 0.5F, true, GTMaterialGen.get(Items.IRON_NUGGET, 3)),
	MALACHITE(14, GTMaterial.Malachite, Blocks.STONE, 1, 3.0F, true, GTMaterialGen.getNugget(GTMaterial.Copper, 1)),
	OLIVINE(15, GTMaterial.Olivine, Blocks.STONE, 3, 3.0F, true, GTMaterialGen.getGem(GTMaterial.Olivine, 1)),
	PYRITE(16, GTMaterial.Pyrite, Blocks.STONE, 1, 2.0F, true, GTMaterialGen.get(Items.IRON_NUGGET, 3)),
	SHELDONITE(17, GTMaterial.Sheldonite, Blocks.STONE, 3, 3.5F, false, null),
	RUBY(18, GTMaterial.Ruby, Blocks.STONE, 2, 4.0F, true, GTMaterialGen.getGem(GTMaterial.Ruby, 1)),
	SALTPETER(19, GTMaterial.Saltpeter, Blocks.STONE, 1, 2.0F, false, null),
	SAPPHIRE(20, GTMaterial.Sapphire, Blocks.STONE, 2, 4.0F, true, GTMaterialGen.getGem(GTMaterial.Sapphire, 1)),
	SODALITE(21, GTMaterial.Sodalite, Blocks.STONE, 2, 3.0F, false, null),
	SPHALERITE(22, GTMaterial.Sphalerite, Blocks.STONE, 1, 2.0F, true, GTMaterialGen.getNugget(GTMaterial.Zinc, 3)),
	SULFUR(23, GTMaterial.Sulfur, Blocks.STONE, 1, 2.0F, false, null),
	TANTALITE(24, GTMaterial.Tantalite, Blocks.STONE, 2, 4.0F, false, null),
	TETRAHEDRITE(25, GTMaterial.Tetrahedrite, Blocks.STONE, 1, 3.0F, true,
			GTMaterialGen.getNugget(GTMaterial.Copper, 3)),
	TUNGSTATE(26, GTMaterial.Tungstate, Blocks.STONE, 1, 3.0F, false, null),
	ZIRCONIUM(27, GTMaterial.Zirconium, Blocks.GRAVEL, 0, 0.5F, false, null);

	private int id;
	private GTMaterial mat;
	private Block type;
	private int level;
	private float hardness;
	private boolean smeltable;
	private ItemStack output;

	GTOreGen(int id, GTMaterial mat, Block type, int level, float hardness, boolean smeltable, ItemStack output) {
		this.mat = mat;
		this.id = id;
		this.type = type;
		this.level = level;
		this.hardness = hardness;
		this.smeltable = smeltable;
		this.output = output;
	}

	public int getId() {
		return this.id;
	}

	public GTMaterial getMaterial() {
		return this.mat;
	}

	public Block getType() {
		return this.type;
	}

	public int getLevel() {
		return this.level;
	}

	public float getHardness() {
		return this.hardness;
	}

	public boolean isSmeltable() {
		return this.smeltable;
	}

	public ItemStack getOutput() {
		return this.output;
	}

}
