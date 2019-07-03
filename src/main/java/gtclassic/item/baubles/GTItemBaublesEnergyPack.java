package gtclassic.item.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import gtclassic.item.GTItemEnergyPack;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional;

@Optional.Interface(iface = "baubles.api.IBauble", modid = "baubles", striprefs = true)
public class GTItemBaublesEnergyPack extends GTItemEnergyPack implements IBauble {

	public GTItemBaublesEnergyPack(int index, String tex, int max, String reg, String unl, int lvl, int limit) {
		super(index, tex, max, reg, unl, lvl, limit);
	}

	@Override
	@Optional.Method(modid = "baubles")
	public BaubleType getBaubleType(ItemStack itemStack) {
		return BaubleType.BODY;
	}

	@Override
	@Optional.Method(modid = "baubles")
	public boolean willAutoSync(ItemStack itemstack, EntityLivingBase player) {
		return true;
	}
}
