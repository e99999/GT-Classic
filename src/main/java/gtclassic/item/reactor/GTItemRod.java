package gtclassic.item.reactor;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTClassic;
import gtclassic.item.reactor.GTItemRod.GTItemRodTypes;
import ic2.api.classic.reactor.IReactorPlanner;
import ic2.api.classic.reactor.IReactorPlannerComponent;
import ic2.api.classic.reactor.ISteamReactor;
import ic2.api.classic.reactor.ISteamReactorComponent;
import ic2.api.reactor.IReactor;
import ic2.core.item.base.ItemGrandualInt;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.util.obj.IBootable;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemRod extends ItemGrandualInt implements IBootable, ISteamReactorComponent, IReactorPlannerComponent {

	public enum GTItemRodTypes {
		SINGLETHORIUM(48), DOUBLETHORIUM(49), QUADTHORIUM(50), SINGLEPLUTONIUM(51), DOUBLEPLUTONIUM(52),
		QUADPLUTONIUM(53);

		private int id;

		GTItemRodTypes(int id) {
			this.id = id;
		}

		public int getID() {
			return id;
		}
	}

	GTItemRodTypes variant;

	public GTItemRod(GTItemRodTypes variant) {
		this.variant = variant;
		setRegistryName(variant.toString().toLowerCase() + "_rod");
		setUnlocalizedName(GTClassic.MODID + "." + variant.toString().toLowerCase() + "_rod");
		setCreativeTab(GTClassic.creativeTabGT);
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int i) {
		return Ic2Icons.getTextures("gtclassic_items")[variant.getID()];
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.RED + I18n.format("tooltip." + GTClassic.MODID + ".wip"));
	}

	@Override
	public void processChamber(ItemStack var1, IReactor var2, int var3, int var4, boolean var5) {
		// TODO Auto-generated method stub

	}

	private Object getUran(ItemStack stack) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canStoreHeat(ItemStack stack, IReactor reactor, int x, int y) {
		return false;
	}

	@Override
	public boolean acceptUraniumPulse(ItemStack var1, IReactor var2, ItemStack var3, int var4, int var5, int var6,
			int var7, boolean var8) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getMaxHeat(ItemStack stack, IReactor reactor, int x, int y) {
		return 0;
	}

	@Override
	public int getCurrentHeat(ItemStack stack, IReactor reactor, int x, int y) {
		return 0;
	}

	@Override
	public int alterHeat(ItemStack stack, IReactor reactor, int x, int y, int heat) {
		return heat;
	}

	@Override
	public float influenceExplosion(ItemStack var1, IReactor var2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ItemStack[] getSubParts() {
		return null;
	}

	@Override
	public boolean hasSubParts() {
		return false;
	}

	public ItemStack getReactorPart() {
		return ItemStack.EMPTY;
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
	public boolean canBePlacedIn(ItemStack stack, IReactor reactor) {
		return true;
	}

	@Override
	public List<ReactorComponentStat> getExtraStats(ItemStack stack) {
		return null;
	}

	@Override
	public NBTPrimitive getReactorStat(ReactorComponentStat var1, ItemStack var2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAdvancedStat(ReactorComponentStat var1, ItemStack var2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public NBTPrimitive getReactorStat(IReactor var1, int var2, int var3, ItemStack var4, ReactorComponentStat var5) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processTick(ISteamReactor var1, ItemStack var2, int var3, int var4, boolean var5, boolean var6) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLoad() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getTextureEntry(int var1) {
		return 0;
	}

	@Override
	public ReactorComponentType getType(ItemStack stack) {
		return ReactorComponentType.FuelRod;
	}

}
