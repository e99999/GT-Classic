package gtclassic.common.item;

import java.util.Collections;
import java.util.List;

import gtclassic.GTMod;
import ic2.core.item.reactor.base.ItemDepletedUraniumRodBase;
import ic2.core.item.reactor.uranTypes.IUranium;
import ic2.core.platform.lang.components.base.LangComponentHolder;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemIsotopicRod extends ItemDepletedUraniumRodBase {

	String title;
	int id;
	protected IUranium uran;

	/**
	 * Constructor for GTC Reactor Rod.
	 *
	 * @param title - String name for the rod item
	 * @param id    - int for texture entry, determines the uran type as well
	 */
	public GTItemIsotopicRod(String title, int id) {
		this.title = "isotopic_rod_" + title;
		this.id = id;
		GTMod.logger.info("texture index: " + this.id);
		setUranium();
		setRegistryName(this.title);
		setCreativeTab(GTMod.creativeTabGT);
		setTranslationKey(new LangComponentHolder.LocaleItemComp("item." + GTMod.MODID + "." + this.title));
	}

	public void setUranium() {
		if (this.title.contains("plutonium")) {
			this.uran = GTItemReactorRod.getUran(1);
		} else {
			this.uran = GTItemReactorRod.getUran(0);
		}
	}

	@Override
	public IUranium getUranium(ItemStack itemStack) {
		return uran;
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(uran.getNewIsotopicRod());
		}
	}

	@Override
	public ItemStack[] getSubParts() {
		return new ItemStack[0];
	}

	@Override
	public boolean hasSubParts() {
		return false;
	}

	@Override
	public ItemStack getReactorPart() {
		return uran.getNewIsotopicRod();
	}

	@Override
	public int getTextureEntry(int i) {
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int i) {
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[this.id];
	}

	@Override
	public List<Integer> getValidVariants() {
		return Collections.singletonList(0);
	}

	@Override
	public LocaleComp getLangComponent(ItemStack stack) {
		return this.name;
	}
}
