package gtclassic.item.reactor;

import java.util.List;

import ic2.api.classic.reactor.IReactorPlannerComponent;
import ic2.api.reactor.IReactor;
import ic2.core.item.base.ItemGrandualInt;
import ic2.core.util.obj.IBootable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTPrimitive;

public class GTItemRodDepleated extends ItemGrandualInt implements IReactorPlannerComponent, IBootable {

	@Override
	public boolean acceptUraniumPulse(ItemStack var1, IReactor var2, ItemStack var3, int var4, int var5, int var6,
			int var7, boolean var8) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int alterHeat(ItemStack var1, IReactor var2, int var3, int var4, int var5) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canStoreHeat(ItemStack var1, IReactor var2, int var3, int var4) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCurrentHeat(ItemStack var1, IReactor var2, int var3, int var4) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxHeat(ItemStack var1, IReactor var2, int var3, int var4) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float influenceExplosion(ItemStack var1, IReactor var2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void processChamber(ItemStack var1, IReactor var2, int var3, int var4, boolean var5) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canBePlacedIn(ItemStack var1, IReactor var2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Integer> getValidVariants() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onLoad() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ReactorComponentStat> getExtraStats(ItemStack var1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public short getID(ItemStack var1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ReactorType getReactorInfo(ItemStack var1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack getReactorPart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NBTPrimitive getReactorStat(ReactorComponentStat var1, ItemStack var2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NBTPrimitive getReactorStat(IReactor var1, int var2, int var3, ItemStack var4, ReactorComponentStat var5) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack[] getSubParts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReactorComponentType getType(ItemStack var1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasSubParts() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAdvancedStat(ReactorComponentStat var1, ItemStack var2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getTextureEntry(int var1) {
		// TODO Auto-generated method stub
		return 0;
	}

}
