package gtclassic.item;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import gtclassic.GTMod;
import gtclassic.util.GTValues;
import gtclassic.util.color.GTColorItemInterface;
import ic2.api.classic.item.IMiningDrill;
import ic2.api.item.ElectricItem;
import ic2.core.IC2;
import ic2.core.item.base.ItemElectricTool;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ILayeredItemModel;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemDrill extends ItemElectricTool implements IMiningDrill, IStaticTexturedItem, GTColorItemInterface, ILayeredItemModel { 

	String material;
	float speed;
	
	public GTItemDrill(String material, float speed, int charge, int transfer, int tier) {
		super(0.0F, -3.0F, ToolMaterial.DIAMOND);
		this.material = material;
		this.tier = tier;
		this.speed = speed;
		this.maxCharge = charge;
		this.transferLimit = transfer;
		this.setRegistryName(getDrillName());
		this.setUnlocalizedName(GTMod.MODID + "." + getDrillName());
		this.attackDamage = 8.0F;
		this.setCreativeTab(GTMod.creativeTabGT);
	}
	
	public String getDrillName() {
		return ("drill_" + this.material + "_" + GTValues.getTierString(this.tier)).toLowerCase();
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(GTValues.getTierTextColor(this.tier) + I18n.format("Tier: " + GTValues.getTierString(this.tier)));
		tooltip.add(GTValues.getTierTextColor(this.tier) + I18n.format("Material: " + this.material));
		tooltip.add(GTValues.getTierTextColor(this.tier) + I18n.format("Speed: " + String.valueOf(this.speed)));
		tooltip.add(GTValues.getTierTextColor(this.tier) + I18n.format("Size: " + String.valueOf(this.maxCharge)));
		tooltip.add(GTValues.getTierTextColor(this.tier) + I18n.format("Transfer: " + String.valueOf(this.transferLimit)));
	}

	@Override
	public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
		return Items.DIAMOND_PICKAXE.canHarvestBlock(state) || Items.DIAMOND_SHOVEL.canHarvestBlock(state);
	}

	@Override
	public int getHarvestLevel(ItemStack stack, String toolClass, EntityPlayer player, IBlockState blockState) {
		return 3;
	}

	@Override
	public int getEnergyCost(ItemStack stack) {
		return this.transferLimit;
	}

	@Override
	public float getMiningSpeed(ItemStack stack) {
		return this.speed;
	}

	@Override
	public Set<String> getToolClasses(ItemStack stack) {
		return ImmutableSet.of("pickaxe", "shovel");
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState blockIn, BlockPos pos,
			EntityLivingBase entityLiving) {
		if (entityLiving instanceof EntityPlayer) {
			IC2.achievements.issueStat((EntityPlayer) entityLiving, "blocksDrilled");
		}
		IC2.audioManager.playOnce(entityLiving, Ic2Sounds.drillHard);
		return super.onBlockDestroyed(stack, worldIn, blockIn, pos, entityLiving);
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	public EnumEnchantmentType getType(ItemStack itemStack) {
		return EnumEnchantmentType.DIGGER;
	}

	@Override
	public boolean isBasicDrill(ItemStack d) {
		return !d.isItemEnchantable();
	}

	@Override
	public int getExtraSpeed(ItemStack d) {
		int pointBoost = this.getPointBoost(d);
		return 0 + pointBoost;
	}

	private int getPointBoost(ItemStack drill) {
		int lvl = EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, drill);
		return lvl <= 0 ? 0 : lvl * lvl + 1;
	}

	@Override
	public int getExtraEnergyCost(ItemStack d) {
		int points = this.getEnergyChange(d);
		return points > 0 ? points : 0;
	}

	public int getEnergyChange(ItemStack drill) {
		int eff = EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, drill);
		int unb = EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, drill);
		int points = eff * eff + 1;
		points -= unb * (unb + unb);
		return points;
	}

	@Override
	public void useDrill(ItemStack d) {
		ElectricItem.manager.use(d, this.getEnergyCost(d), (EntityLivingBase) null);
	}

	@Override
	public boolean canMine(ItemStack d) {
		return ElectricItem.manager.canUse(d, this.getEnergyCost(d));
	}

	@Override
	public boolean canMineBlock(ItemStack d, IBlockState state, IBlockAccess access, BlockPos pos) {
		return ForgeHooks.canToolHarvestBlock(access, pos, d);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int i) {
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[32];
	}

	@Override
	public Color getColor(ItemStack stack, int index) {
		if (index == 0) {
			return GTValues.getTierColor(this.tier);
		} else {
			return GTValues.getColor(this.material);
		}
	}

	@Override
	public boolean isLayered(ItemStack var1) {
		return true;
	}

	@Override
	public int getLayers(ItemStack var1) {
		return 2;
	}

	@Override
	public TextureAtlasSprite getTexture(int var1, ItemStack var2) {
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[32 + var1];
	}
}
