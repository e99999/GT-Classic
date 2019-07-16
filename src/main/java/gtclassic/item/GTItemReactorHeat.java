package gtclassic.item;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTItems;
import gtclassic.GTMod;
import ic2.api.reactor.IReactor;
import ic2.core.item.reactor.ItemReactorHeatStorageBase;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemReactorHeat extends ItemReactorHeatStorageBase implements IStaticTexturedItem {

	public enum GTItemHeatStorageTypes {
		SINGLE(2),
		TRIPLE(3),
		SIX(4);

		private int id;

		GTItemHeatStorageTypes(int id) {
			this.id = id;
		}

		public int getID() {
			return id;
		}
	}

	GTItemHeatStorageTypes variant;

	public GTItemReactorHeat(GTItemHeatStorageTypes variant) {
		this.variant = variant;
		setRegistryName("heatstorage_" + variant.toString().toLowerCase());
		setUnlocalizedName(GTMod.MODID + ".heatstorage_" + variant.toString().toLowerCase());
		setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.RED + I18n.format("Work in progress, no refunds!"));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int i) {
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[variant.getID()];
	}

	@Override
	public int getMaxCustomDamage(ItemStack stack) {
		if (this == GTItems.heatStorageSingle) {
			return 60000;
		}
		if (this == GTItems.heatStorageTriple) {
			return 180000;
		}
		if (this == GTItems.heatStorageSix) {
			return 360000;
		} else {
			return 0;
		}
	}

	@Override
	public List<ReactorComponentStat> getExtraStats(ItemStack stack) {
		return null;
	}

	@Override
	public short getID(ItemStack var1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ReactorType getReactorInfo(ItemStack stack) {
		return ReactorType.Both;
	}

	@Override
	public ItemStack getReactorPart() {
		return ItemStack.EMPTY;
	}

	@Override
	public NBTPrimitive getReactorStat(ReactorComponentStat stat, ItemStack stack) {
		return stat == ReactorComponentStat.HeatStorage ? new NBTTagInt(this.getMaxCustomDamage(stack))
				: nulltag;
	}

	@Override
	public NBTPrimitive getReactorStat(IReactor var1, int var2, int var3, ItemStack var4, ReactorComponentStat var5) {
		return nulltag;
	}

	@Override
	public ItemStack[] getSubParts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReactorComponentType getType(ItemStack var1) {
		return ReactorComponentType.CoolantCell;
	}

	@Override
	public boolean hasSubParts() {
		// TODO Auto-generated method stub
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
