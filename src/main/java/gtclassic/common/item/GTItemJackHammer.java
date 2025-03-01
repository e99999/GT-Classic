package gtclassic.common.item;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;

import gtclassic.GTMod;
import ic2.api.classic.item.IMiningDrill;
import ic2.api.item.ElectricItem;
import ic2.core.IC2;
import ic2.core.item.base.ItemElectricTool;
import ic2.core.item.tool.electric.ItemElectricToolDrill;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemJackHammer extends ItemElectricTool implements IMiningDrill, IStaticTexturedItem {

	public static final Set<Block> rocks = new HashSet<>();

	public GTItemJackHammer() {
		super(0.0F, -3.0F, ToolMaterial.DIAMOND);
		this.tier = 1;
		this.attackDamage = 1.0F;
		this.maxCharge = 10000;
		this.transferLimit = 100;
		this.setRegistryName("jackhammer");
		this.setTranslationKey(GTMod.MODID + "." + "jackhammer");
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
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return false;
	}

	@Override
	public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
		return Items.DIAMOND_PICKAXE.canHarvestBlock(state);
	}

	@Override
	public int getHarvestLevel(ItemStack stack, String toolClass, EntityPlayer player, IBlockState blockState) {
		return 3;
	}

	@Override
	public int getEnergyCost(ItemStack stack) {
		return 50;
	}

	@Override
	public float getMiningSpeed(ItemStack stack) {
		return 8.0F;
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		if (canMine(stack) && isValidState(state)) {
			return getMiningSpeed(stack);
		} else {
			return 0.0F;
		}
	}

	@Override
	public Set<String> getToolClasses(ItemStack stack) {
		return ImmutableSet.of("pickaxe");
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	public EnumEnchantmentType getType(ItemStack itemStack) {
		return null;
	}

	@Override
	public boolean isBasicDrill(ItemStack d) {
		return false;
	}

	@Override
	public int getExtraSpeed(ItemStack d) {
		return 0;
	}

	@Override
	public int getExtraEnergyCost(ItemStack d) {
		return 0;
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
	public boolean canMineBlock(ItemStack stack, IBlockState state, IBlockAccess access, BlockPos pos) {
		return ForgeHooks.canToolHarvestBlock(access, pos, stack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int i) {
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[30];
	}

	@Override
	public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player) {
		World worldIn = player.world;
		if (!player.isSneaking()) {
			for (BlockPos additionalPos : getTargetBlocks(worldIn, pos, player)) {
				breakBlock(additionalPos, worldIn, player, stack);
			}
			if (IC2.platform.isRendering()) {
				IC2.audioManager.playOnce(player, Ic2Sounds.drillHard);
			}
		}
		return false;
	}

	public Set<BlockPos> getTargetBlocks(World worldIn, BlockPos pos, @Nullable EntityPlayer playerIn) {
		Set<BlockPos> targetBlocks = new HashSet<BlockPos>();
		if (playerIn == null) {
			return new HashSet<BlockPos>();
		}
		RayTraceResult raytrace = rayTrace(worldIn, playerIn, false);
		if (raytrace == null || raytrace.sideHit == null) {
			return Collections.emptySet();
		}
		EnumFacing enumfacing = raytrace.sideHit;
		if (enumfacing == EnumFacing.SOUTH || enumfacing == EnumFacing.NORTH) {
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					BlockPos newPos = pos.add(i, j, 0);
					if (shouldBreak(playerIn, worldIn, pos, newPos)) {
						targetBlocks.add(newPos);
					}
				}
			}
		} else if (enumfacing == EnumFacing.EAST || enumfacing == EnumFacing.WEST) {
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					BlockPos newPos = pos.add(0, j, i);
					if (shouldBreak(playerIn, worldIn, pos, newPos)) {
						targetBlocks.add(newPos);
					}
				}
			}
		} else if (enumfacing == EnumFacing.DOWN || enumfacing == EnumFacing.UP) {
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					BlockPos newPos = pos.add(j, 0, i);
					if (shouldBreak(playerIn, worldIn, pos, newPos)) {
						targetBlocks.add(newPos);
					}
				}
			}
		}
		return targetBlocks;
	}

	public void breakBlock(BlockPos pos, World world, EntityPlayer player, ItemStack drill) {
		IBlockState blockState = world.getBlockState(pos);
		if (!isValidState(blockState) || !ElectricItem.manager.canUse(drill, this.getEnergyCost(drill))) {
			return;
		}
		ElectricItem.manager.use(drill, this.getEnergyCost(drill), player);
		blockState.getBlock().harvestBlock(world, player, pos, blockState, world.getTileEntity(pos), drill);
		world.setBlockToAir(pos);
		world.removeTileEntity(pos);
	}

	public boolean shouldBreak(EntityPlayer playerIn, World worldIn, BlockPos originalPos, BlockPos pos) {
		if (originalPos.equals(pos)) {
			return false;
		}
		IBlockState blockState = worldIn.getBlockState(pos);
		if (blockState.getMaterial() == Material.AIR) {
			return false;
		}
		if (blockState.getMaterial().isLiquid()) {
			return false;
		}
		float blockHardness = blockState.getPlayerRelativeBlockHardness(playerIn, worldIn, pos);
		if (blockHardness == -1.0F) {
			return false;
		}
		float originalHardness = worldIn.getBlockState(originalPos).getPlayerRelativeBlockHardness(playerIn, worldIn, originalPos);
		if ((originalHardness / blockHardness) > 10.0F) {
			return false;
		}
		return true;
	}

	public boolean isValidState(IBlockState blockstate) {
		return ItemElectricToolDrill.rocks.contains(blockstate)
				|| GTItemJackHammer.rocks.contains(blockstate.getBlock());
	}
}
