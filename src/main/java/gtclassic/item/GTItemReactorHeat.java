package gtclassic.item;

import java.util.Arrays;
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
	String name;
	int id;
	int durability;

	public GTItemReactorHeat(String name, int id, int durability) {
		this.name = name;
		this.id = id;
		this.durability = durability;
		setRegistryName("heatstorage_" + this.name);
		setUnlocalizedName(GTMod.MODID + ".heatstorage_" + this.name);
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
		return null;
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
		return (NBTPrimitive) (stat == ReactorComponentStat.HeatStorage ? new NBTTagInt(this.getMaxCustomDamage(stack))
				: nulltag);
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
