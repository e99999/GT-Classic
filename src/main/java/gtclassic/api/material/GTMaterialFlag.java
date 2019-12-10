package gtclassic.api.material;

import gtclassic.GTMod;
import gtclassic.common.GTLang;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Lang;

public class GTMaterialFlag {

	public static GTMaterialFlag DUST = new GTMaterialFlag("_dust", 0, false, GTLang.DUST_LANG);
	public static GTMaterialFlag INGOT = new GTMaterialFlag("_ingot", 1, false, GTLang.INGOT_LANG);
	public static GTMaterialFlag INGOTHOT = new GTMaterialFlag("_ingothot", 1, true, GTLang.INGOT_HOT_LANG);
	public static GTMaterialFlag RUBY = new GTMaterialFlag("_gem", 3, false, GTLang.GEM_LANG);
	public static GTMaterialFlag SAPPHIRE = new GTMaterialFlag("_gem", 4, false, GTLang.GEM_LANG);
	public static GTMaterialFlag FLUID = new GTMaterialFlag("_fluid", 13, true);
	public static GTMaterialFlag GAS = new GTMaterialFlag("_gas", 13, true);
	public static GTMaterialFlag BLOCKMETAL = new GTMaterialFlag("_block", 16, false, GTLang.BLOCK_LANG);
	public static GTMaterialFlag BLOCKGEM = new GTMaterialFlag("_block", 17, false, GTLang.BLOCK_LANG);
	public static GTMaterialFlag PIPEITEM = new GTMaterialFlag("_itempipe", -1, false);
	public static GTMaterialFlag PIPEFLUID = new GTMaterialFlag("_fluidpipe", -1, false);
	public static GTMaterialFlag NULL = new GTMaterialFlag("", -1, false);
	private static int LAST_INTERNAL_ID;
	private int mask;
	private String suffix;
	private String texture;
	private int id;
	private boolean layered;
	private String modid;
	private LocaleComp comp;

	public GTMaterialFlag(String suffix, int id, boolean layered) {
		this(suffix, GTMod.MODID + "_materials", id, layered, GTMod.MODID, Ic2Lang.nullKey);
	}

	public GTMaterialFlag(String suffix, int id, boolean layered, LocaleComp comp) {
		this(suffix, GTMod.MODID + "_materials", id, layered, GTMod.MODID, comp);
	}

	public GTMaterialFlag(String suffix, String texture, int id, boolean layered, String modid, LocaleComp comp) {
		this.mask = 1 << LAST_INTERNAL_ID++;
		this.suffix = suffix;
		this.texture = texture;
		this.id = id;
		this.layered = layered;
		this.modid = modid;
		this.comp = comp;
	}

	public String getTexture() {
		return texture;
	}

	public int getMask() {
		return mask;
	}

	public String getSuffix() {
		return suffix;
	}

	public String getPrefix() {
		return suffix.replace("_", "");
	}

	public int getTextureID() {
		return id;
	}

	public boolean isLayered() {
		return layered;
	}

	public String getModID() {
		return this.modid;
	}

	public LocaleComp getComp() {
		return comp;
	}
}
