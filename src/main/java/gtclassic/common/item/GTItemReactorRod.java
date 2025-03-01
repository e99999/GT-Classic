package gtclassic.common.item;

import java.util.Collections;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.common.util.GTUranPlutonium;
import gtclassic.common.util.GTUranThorium;
import ic2.core.item.reactor.base.ItemUraniumRodBase;
import ic2.core.item.reactor.uranTypes.IUranium;
import ic2.core.item.reactor.uranTypes.IUranium.RodType;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemReactorRod extends ItemUraniumRodBase {

	String title;
	int id;
	int amount;
	protected static IUranium urans[] = new IUranium[0];
	protected IUranium uran;

	/**
	 * Constructor for GTC Reactor Rod.
	 * 
	 * @param title  - String name for the rod item
	 * @param id     - int for texture entry, determines the uran type as well
	 * @param amount - amount for rod type, single 1, dual 2, quad 4
	 */
	public GTItemReactorRod(String title, int id, int amount) {
		this.title = "rod_" + title;
		this.id = id;
		this.amount = amount;
		if (id == 16) {
			init();
		}
		setUranium();
		setRegistryName(this.title);
		setTranslationKey(GTMod.MODID + "." + this.title);
		setCreativeTab(GTMod.creativeTabGT);
	}

	public static void init() {
		urans = new IUranium[2];
		urans[0] = new GTUranThorium();
		urans[1] = new GTUranPlutonium();
	}

	public static IUranium getUran(int index) {
		if (index > 1) {
			return urans[0];
		}
		return urans[index];
	}

	public void setUranium() {
		switch (this.id) {
		case 19:
		case 20:
		case 21:
			this.uran = urans[1];
			break;
		default:
			this.uran = urans[0];
			break;
		}
	}

	@Override
	public List<Integer> getValidVariants() {
		return Collections.singletonList(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int i) {
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[this.id];
	}

	@Override
	public LocaleComp getLangComponent(ItemStack stack) {
		return this.name;
	}

	@Override
	public ItemStack getReactorPart() {
		return new ItemStack(this);
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
	public int getRodAmount(ItemStack paramItemStack) {
		return this.amount;
	}

	@Override
	public RodType getRodType(ItemStack paramItemStack) {
		return this.getRodType();
	}

	@Override
	public RodType getRodType(int paramInt) {
		return this.getRodType();
	}

	// my version to return for both methods
	public RodType getRodType() {
		switch (this.amount) {
		case 2:
			return IUranium.RodType.DualRod;
		case 4:
			return IUranium.RodType.QuadRod;
		default:
			return IUranium.RodType.SingleRod;
		}
	}

	@Override
	public IUranium getUranium(ItemStack paramItemStack) {
		return this.uran;
	}

	@Override
	public IUranium getUranium(int paramInt) {
		return this.uran;
	}

	@Override
	public int getTextureEntry(int arg0) {
		return 0;
	}
}
