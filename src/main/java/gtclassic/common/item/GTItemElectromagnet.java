package gtclassic.common.item;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import ic2.api.classic.item.IElectricTool;
import ic2.api.item.ElectricItem;
import ic2.core.IC2;
import ic2.core.item.base.BasicElectricItem;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IAdvancedTexturedItem;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemElectromagnet extends BasicElectricItem implements IAdvancedTexturedItem, IElectricTool {

	public ModelResourceLocation[] model = new ModelResourceLocation[2];
	public static final String ACTIVE = "active";
	int range = 7;
	double speed = 0.04D;
	double energyCost = 1;

	public GTItemElectromagnet() {
		this.maxStackSize = 1;
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setRegistryName("electromagnet");
		this.setTranslationKey(GTMod.MODID + ".electromagnet");
		this.maxCharge = 10000;
		this.transferLimit = 100;
		this.tier = 1;
		this.setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format(this.getTranslationKey().replace("item", "tooltip")));
	}

	@Override
	public int getRGBDurabilityForDisplay(ItemStack stack) {
		return Color.CYAN.hashCode();
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		IC2.audioManager.playOnce(playerIn, Ic2Sounds.forceFieldOp);
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
		if (worldIn.getTotalWorldTime() % 2 == 0) {
			if (!(entityIn instanceof EntityPlayer) || !nbt.getBoolean(ACTIVE)) {
				this.setDamage(stack, 0);
				return;
			}
			double x = entityIn.posX;
			double y = entityIn.posY + 1.5;
			double z = entityIn.posZ;
			int pulled = 0;
			for (EntityItem item : worldIn.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(x, y, z, x + 1, y
					+ 1, z + 1).grow(range))) {
				if (item.getEntityData().getBoolean("PreventRemoteMovement")) {
					continue;
				}
				if (!canPull(stack) || pulled > 200) {
					break;
				}
				item.addVelocity((x - item.posX) * speed, (y - item.posY) * speed, (z - item.posZ) * speed);
				ElectricItem.manager.use(stack, energyCost, (EntityLivingBase) entityIn);
				pulled++;
			}
			this.setDamage(stack, pulled > 0 ? 1 : 0);
		}
	}

	public boolean canPull(ItemStack stack) {
		return ElectricItem.manager.canUse(stack, energyCost);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		NBTTagCompound nbt = StackUtil.getOrCreateNbtData((stack));
		return nbt.getBoolean(ACTIVE);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int meta) {
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[27 + meta];
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0, 1);
	}

	@SideOnly(Side.CLIENT)
	public ModelResourceLocation createResourceLocationForStack(ItemStack stack) {
		int damage = stack.getItemDamage();
		ResourceLocation location = this.getRegistryName();
		String name = stack.getTranslationKey();
		this.model[damage] = new ModelResourceLocation(location.getNamespace()
				+ name.substring(name.indexOf(".") + 1) + damage, "inventory");
		return this.model[damage];
	}

	@SideOnly(Side.CLIENT)
	public ModelResourceLocation getResourceLocationForStack(ItemStack stack) {
		int damage = stack.getItemDamage();
		return this.model[damage];
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
