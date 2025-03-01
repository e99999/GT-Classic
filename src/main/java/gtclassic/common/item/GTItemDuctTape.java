package gtclassic.common.item;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import ic2.api.classic.energy.tile.IInsulationModifieableConductor;
import ic2.core.IC2;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemDuctTape extends Item implements IStaticTexturedItem {

	public GTItemDuctTape() {
		this.setRegistryName("duct_tape");
		this.setTranslationKey(GTMod.MODID + ".ductTape");
		this.setCreativeTab(GTMod.creativeTabGT);
		this.setMaxStackSize(1);
		this.setMaxDamage(255);
		this.setHasSubtypes(false);
		this.setNoRepair();
	}

	@Override
	public EnumRarity getRarity(ItemStack thisItem) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	public boolean hasContainerItem(ItemStack itemStack) {
		return true;
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		ItemStack copy = itemStack.copy();
		return copy.attemptDamageItem(1, itemRand, null) ? ItemStack.EMPTY : copy;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int meta) {
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[22];
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (player.isSneaking()) {
			return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
		} else if (tileEntity instanceof IInsulationModifieableConductor) {
			IInsulationModifieableConductor wire = (IInsulationModifieableConductor) tileEntity;
			if (wire.tryAddInsulation()) {
				player.getHeldItem(hand).damageItem(1, player);
				IC2.audioManager.playOnce(player, Ic2Sounds.painterUse);
				return EnumActionResult.SUCCESS;
			} else {
				return EnumActionResult.FAIL;
			}
		} else {
			return EnumActionResult.FAIL;
		}
	}
}
