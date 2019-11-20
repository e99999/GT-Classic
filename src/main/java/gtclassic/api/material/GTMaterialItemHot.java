package gtclassic.api.material;

import java.util.List;

import gtclassic.api.helpers.GTHelperPlayer;
import ic2.core.item.armor.standart.ItemHazmatArmor;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class GTMaterialItemHot extends GTMaterialItem {

	public GTMaterialItemHot(GTMaterial material) {
		super(material, GTMaterialFlag.INGOTHOT);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.RED + I18n.format("Hot stuff coming through!"));
		tooltip.add(TextFormatting.YELLOW + I18n.format("Can be handled safely wearing a full Hazmat or Quantum Suit"));
	}

	/**
	 * Creates the behavior harming the player if unprotected
	 */
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof EntityLivingBase) {
			EntityLivingBase player = (EntityLivingBase) entityIn;
			if (!ItemHazmatArmor.isFullHazmatSuit(player) && !player.isImmuneToFire()
					&& !GTHelperPlayer.hasFullQuantumSuit(player)) {
				entityIn.attackEntityFrom(DamageSource.IN_FIRE, 4.0F);
			}
		}
	}
}
