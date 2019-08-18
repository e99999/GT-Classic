package gtclassic.item;

import ic2.api.classic.item.IElectricTool;
import ic2.api.item.ElectricItem;
import ic2.core.IC2;
import ic2.core.item.base.BasicElectricItem;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.misc.StackUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class GTItemBaseToggleItem extends BasicElectricItem implements IElectricTool {

	public static final String ACTIVE = "active";
	double energyCost;
	ResourceLocation toggleSound = Ic2Sounds.forceFieldOp;

	public GTItemBaseToggleItem(int tier, int maxCharge, int cost) {
		this.maxStackSize = 1;
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.tier = tier;
		this.maxCharge = maxCharge;
		this.transferLimit = cost;
		this.energyCost = cost;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		IC2.audioManager.playOnce(playerIn, toggleSound);
		if (IC2.platform.isSimulating()) {
			NBTTagCompound nbt = StackUtil.getOrCreateNbtData(playerIn.getHeldItem(handIn));
			boolean result = !nbt.getBoolean(ACTIVE);
			nbt.setBoolean(ACTIVE, result);
		}
		return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int slot, boolean selected) {
		NBTTagCompound nbt = StackUtil.getNbtData((stack));
		if (!(entityIn instanceof EntityPlayer) || !nbt.getBoolean(ACTIVE)) {
			return;
		}
		if (!canUse(stack)) {
			return;
		}
		if (onItemActive(stack, worldIn, entityIn, slot, selected)) {
			ElectricItem.manager.use(stack, this.energyCost, (EntityLivingBase) entityIn);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		NBTTagCompound nbt = StackUtil.getOrCreateNbtData((stack));
		return nbt.getBoolean(ACTIVE);
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged)
    {
        return false;
    }

	public abstract boolean onItemActive(ItemStack stack, World worldIn, Entity entityIn, int slot, boolean selected);

	public boolean canUse(ItemStack stack) {
		return ElectricItem.manager.canUse(stack, energyCost);
	}

	@Override
	public int getTextureEntry(int var1) {
		return 0;
	}

	@Override
	public EnumEnchantmentType getType(ItemStack paramItemStack) {
		return null;
	}

	@Override
	public boolean isSpecialSupported(ItemStack paramItemStack, Enchantment paramEnchantment) {
		return false;
	}

	@Override
	public boolean isExcluded(ItemStack paramItemStack, Enchantment paramEnchantment) {
		return false;
	}
}
