package gtclassic.item;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.util.GTUranPlutonium;
import gtclassic.util.GTUranThorium;
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
	protected static IUranium[] types = new IUranium[0];

	/**
	 * 
	 * @param name   - String name for the rod item
	 * @param id     - int for texture entry
	 * @param amount - amount for rod type, single 1, dual 2, quad 4
	 */
	public GTItemReactorRod(String title, int id, int amount) {
		this.title = "rod_" + title;
		this.id = id;
		this.amount = amount;
		setRegistryName(this.title);
		setUnlocalizedName(GTMod.MODID + "." + this.title);
		setCreativeTab(GTMod.creativeTabGT);
	}

	public static void init() {
		types = new IUranium[2];
		types[0] = new GTUranThorium();
		types[1] = new GTUranPlutonium();
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
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
		case 1:
			return IUranium.RodType.SingleRod;
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
		return getUranium();
	}

	@Override
	public IUranium getUranium(int paramInt) {
		return getUranium();
	}

	// my version to return for both methods
	public IUranium getUranium() {
		return this.id < 19 ? types[0] : types[1];
	}

	@Override
	public int getTextureEntry(int arg0) {
		return 0;
	}
}
