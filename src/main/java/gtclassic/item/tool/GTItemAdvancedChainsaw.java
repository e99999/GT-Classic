package gtclassic.item.tool;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import gtclassic.GTClassic;
import ic2.api.item.ElectricItem;
import ic2.core.IC2;
import ic2.core.item.base.ItemElectricTool;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemAdvancedChainsaw extends ItemElectricTool implements IStaticTexturedItem {
	public static final ItemStack ironAxe;

	public GTItemAdvancedChainsaw() {
		super(0.0F, 0.0F, ToolMaterial.IRON);
		this.attackDamage = 8.0F;
		this.maxCharge = 100000;
		this.transferLimit = 128;
		this.operationEnergyCost = 50;
		this.tier = 1;
		this.efficiency = 12.0F;
		this.setHarvestLevel("axe", 2);
		this.setRegistryName("advanced_chainsaw");
		this.setUnlocalizedName(GTClassic.MODID + ".advancedChainsaw");
		this.setCreativeTab(GTClassic.creativeTabGT);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("tooltip." + GTClassic.MODID + ".saw"));
	}

	@Override
	public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
		return ironAxe.canHarvestBlock(state) || state.getBlock() == Blocks.WEB;
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		Material material = state.getMaterial();
		if (!ElectricItem.manager.canUse(stack, this.getEnergyCost(stack))) {
			return 1.0F;
		} else {
			return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE
					&& material != Material.LEAVES ? super.getDestroySpeed(stack, state) : this.efficiency;
		}
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase entity,
			EnumHand hand) {
		if (entity.world.isRemote) {
			return false;
		} else if (!(entity instanceof IShearable)) {
			return false;
		} else {
			IShearable target = (IShearable) entity;
			BlockPos pos = new BlockPos(entity.posX, entity.posY, entity.posZ);
			if (target.isShearable(stack, entity.world, pos)
					&& ElectricItem.manager.canUse(stack, this.getEnergyCost(stack) * 2)) {
				List<ItemStack> drops = target.onSheared(stack, entity.world, pos,
						EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack));

				EntityItem ent;
				for (Iterator var8 = drops.iterator(); var8
						.hasNext(); ent.motionZ += (entity.world.rand.nextFloat() - entity.world.rand.nextFloat())
								* 0.1F) {
					ItemStack item = (ItemStack) var8.next();
					ent = entity.entityDropItem(item, 1.0F);
					ent.motionY += entity.world.rand.nextFloat() * 0.05F;
					ent.motionX += (entity.world.rand.nextFloat() - entity.world.rand.nextFloat()) * 0.1F;
				}

				ElectricItem.manager.use(stack, this.getEnergyCost(stack) * 2, playerIn);
			}

			return true;
		}
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
		World worldIn = player.world;
		if (!player.isSneaking()) {
			for (int i = 1; i < 60; i++) {
				BlockPos nextPos = pos.up(i);
				IBlockState nextState = worldIn.getBlockState(nextPos);
				if (nextState.getBlock().isWood(worldIn, nextPos)) {
					breakBlock(nextPos, itemstack, worldIn, pos, player);
				}
			}
		}
		if (!player.world.isRemote && !player.capabilities.isCreativeMode) {
			Block block = player.world.getBlockState(pos).getBlock();
			if (block instanceof IShearable) {
				IShearable target = (IShearable) block;
				if (target.isShearable(itemstack, player.world, pos)
						&& ElectricItem.manager.canUse(itemstack, this.getEnergyCost(itemstack))) {
					List<ItemStack> drops = target.onSheared(itemstack, player.world, pos,
							EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, itemstack));
					Iterator var7 = drops.iterator();

					while (var7.hasNext()) {
						ItemStack stack = (ItemStack) var7.next();
						float f = 0.7F;
						double d = player.world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
						double d1 = player.world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
						double d2 = player.world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
						EntityItem entityitem = new EntityItem(player.world, pos.getX() + d, pos.getY() + d1,
								pos.getZ() + d2, stack);
						entityitem.setDefaultPickupDelay();
						player.world.spawnEntity(entityitem);
					}

					ElectricItem.manager.use(itemstack, this.getEnergyCost(itemstack), player);
					player.addStat(StatList.getBlockStats(block));
					if (block == Blocks.WEB) {
						player.world.setBlockToAir(pos);
						IC2.achievements.issueStat(player, "blocksSawed");
						return true;
					}
				}
			}

			return false;
		} else {
			return false;
		}
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState blockIn, BlockPos pos,
			EntityLivingBase entityLiving) {
		if (entityLiving instanceof EntityPlayer) {
			IC2.achievements.issueStat((EntityPlayer) entityLiving, "blocksSawed");
		}
		return super.onBlockDestroyed(stack, worldIn, blockIn, pos, entityLiving);
	}

	public void breakBlock(BlockPos pos, ItemStack saw, World world, BlockPos oldPos, EntityPlayer player) {
		if (oldPos == pos) {
			return;
		}
		if (!ElectricItem.manager.canUse(saw, this.getEnergyCost(saw))) {
			return;
		}
		IBlockState blockState = world.getBlockState(pos);
		if (blockState.getBlockHardness(world, pos) == -1.0F) {
			return;
		}
		ElectricItem.manager.use(saw, this.getEnergyCost(saw), player);
		blockState.getBlock().harvestBlock(world, player, pos, blockState, world.getTileEntity(pos), saw);
		world.setBlockToAir(pos);
		world.removeTileEntity(pos);
		IC2.audioManager.playOnce(player, Ic2Sounds.chainsawUseOne);
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int meta) {
		return Ic2Icons.getTextures("gtclassic_items")[61];
	}

	@Override
	public EnumEnchantmentType getType(ItemStack item) {
		return EnumEnchantmentType.DIGGER;
	}

	@Override
	public boolean isSpecialSupported(ItemStack item, Enchantment ench) {
		return ench instanceof EnchantmentDamage;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (!(attacker instanceof EntityPlayer)) {
			return true;
		} else {
			if (ElectricItem.manager.use(stack, this.operationEnergyCost, attacker)) {
				target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 10.0F);
			} else {
				target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 1.0F);
			}

			if (target.getHealth() <= 0.0F) {
				if (target instanceof EntityCreeper) {
					IC2.achievements.issueStat((EntityPlayer) attacker, "killCreeperChainsaw");
				}

				if (attacker instanceof EntityPlayer) {
					IC2.achievements.issueStat((EntityPlayer) attacker, "chainsawKills");
				}
			}

			return false;
		}
	}

	static {
		ironAxe = new ItemStack(Items.IRON_AXE);
	}
}
