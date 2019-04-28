package gtclassic.material;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.color.GTColorItemInterface;
import ic2.core.IC2;
import ic2.core.item.armor.standart.ItemHazmatArmor;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ILayeredItemModel;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

public class GTMaterialItemHot extends Item implements IStaticTexturedItem, GTColorItemInterface, ILayeredItemModel {

	private GTMaterial material;
	private GTMaterialFlag flag;

	public GTMaterialItemHot(GTMaterial material, GTMaterialFlag flag) {
		this.material = material;
		this.flag = flag;
		setRegistryName(this.material.getName() + this.flag.getSuffix());
		setUnlocalizedName(GTMod.MODID + "." + this.material.getName() + this.flag.getSuffix());
		setCreativeTab(GTMod.creativeTabGT);
		if (!this.flag.equals(GTMaterialFlag.HOTINGOT)) {
			GTMod.logger.info("Incorrect Hot Item created for: " + this.material.getDisplayName());
		}
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("Contains: " + material.getFormula()));
		tooltip.add(I18n.format("Melting: " + material.getTemp() + "K"));
		tooltip.add(TextFormatting.RED + I18n.format("Hot stuff coming through!"));
		tooltip.add(TextFormatting.YELLOW + I18n.format("Can be handled safely wearing a full Hazmat Suit"));
		tooltip.add(TextFormatting.YELLOW + I18n.format("Can be quenched in a cauldron or bath"));
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		if (material.equals(material.Plutonium) || material.equals(material.Thorium)
				|| material.equals(material.Uranium) || material.equals(material.Vibranium)) {
			return true;
		}
		return super.hasEffect(stack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int i) {
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[flag.getTextureID()];
	}

	@Override
	public Color getColor(ItemStack stack, int index) {
		if (index == 0) {
			return this.material.getColor();
		} else {
			return Color.white;
		}
	}

	@Override
	public boolean isLayered(ItemStack var1) {
		return flag.isLayered();
	}

	@Override
	public int getLayers(ItemStack var1) {
		return 2;
	}

	@Override
	public TextureAtlasSprite getTexture(int index, ItemStack var2) {
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[flag.getTextureID() + index];
	}

	/**
	 * Creates the behavior harming the player if unprotected
	 */
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof EntityLivingBase) {
			EntityLivingBase player = (EntityLivingBase) entityIn;
			if (!ItemHazmatArmor.isFullHazmatSuit(player) || !player.isImmuneToFire()) {
				entityIn.attackEntityFrom(DamageSource.LAVA, 4.0F);
			}
		}
	}

	/**
	 * Creates the behavior of quenching in a cauldron
	 */
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		IBlockState state = world.getBlockState(pos);
		PropertyInteger level = PropertyInteger.create("level", 0, 3);
		if (state.getBlock() == Blocks.CAULDRON && state.getValue(level).intValue() != 3) {
			if (!IC2.platform.isSimulating()) {
				IC2.platform.messagePlayer(player, "Not enough water to quench!");
			}
			return EnumActionResult.FAIL;
		}

		if (state.getBlock() == Blocks.CAULDRON && state.getValue(level).intValue() == 3) {
			player.getHeldItem(hand).shrink(1);
			Blocks.CAULDRON.setWaterLevel(world, pos, state, 0);
			ItemHandlerHelper.giveItemToPlayer(player, GTMaterialGen.getIngot(this.material, 1));
			world.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1.0F,
					1.0F);
			double d0 = (double) pos.getX() + world.rand.nextDouble();
			double d1 = (double) pos.getY() + world.rand.nextDouble() * 0.5D + 0.5D;
			double d2 = (double) pos.getZ() + world.rand.nextDouble();
			world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, d0, d1 + 1.0, d2, 0.0D, 0.0D, 0.0D);
			return EnumActionResult.SUCCESS;

		}
		return EnumActionResult.PASS;
	}

	public GTMaterial getMaterial() {
		return this.material;
	}

	public GTMaterialFlag getFlag() {
		return this.flag;
	}
}
