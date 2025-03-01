package gtclassic.common.item;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import gtclassic.GTMod;
import ic2.api.reactor.IReactor;
import ic2.core.item.reactor.ItemReactorHeatStorageBase;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemReactorHeat extends ItemReactorHeatStorageBase implements IStaticTexturedItem {

	String title;
	int id;
	int durability;

	/**
	 * 
	 * @param name       - String name for the rod item
	 * @param id         - int for texture entry
	 * @param durability - int, the reactor part
	 */
	public GTItemReactorHeat(String title, int id, int durability) {
		this.title = title;
		this.id = id;
		this.durability = durability;
		setRegistryName("heatstorage_" + this.title);
		setTranslationKey(GTMod.MODID + ".heatstorage_" + this.title);
		setCreativeTab(GTMod.creativeTabGT);
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
	public int getMaxCustomDamage(ItemStack stack) {
		return this.durability;
	}

	@Override
	public List<ReactorComponentStat> getExtraStats(ItemStack stack) {
		return Collections.emptyList();
	}

	@Override
	public short getID(ItemStack var1) {
		return (short) (this.id + 1200);
	}

	@Override
	public ReactorType getReactorInfo(ItemStack stack) {
		return ReactorType.Both;
	}

	@Override
	public ItemStack getReactorPart() {
		return new ItemStack(this);
	}

	@Override
	public NBTPrimitive getReactorStat(ReactorComponentStat stat, ItemStack stack) {
		return stat == ReactorComponentStat.HeatStorage ? new NBTTagInt(this.getMaxCustomDamage(stack)) : nulltag;
	}

	@Override
	public NBTPrimitive getReactorStat(IReactor var1, int var2, int var3, ItemStack var4, ReactorComponentStat var5) {
		return nulltag;
	}

	@Override
	public ItemStack[] getSubParts() {
		return new ItemStack[0];
	}

	@Override
	public ReactorComponentType getType(ItemStack var1) {
		return ReactorComponentType.CoolantCell;
	}

	@Override
	public boolean hasSubParts() {
		return false;
	}

	@Override
	public boolean isAdvancedStat(ReactorComponentStat var1, ItemStack var2) {
		return false;
	}

	@Override
	public int getTextureEntry(int arg0) {
		return 0;
	}
}
