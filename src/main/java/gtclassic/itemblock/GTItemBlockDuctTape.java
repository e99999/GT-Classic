package gtclassic.itemblock;

import java.util.List;

import gtclassic.GTMod;
import ic2.api.classic.energy.tile.IInsulationModifieableConductor;
import ic2.core.IC2;
import ic2.core.platform.registry.Ic2Sounds;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTItemBlockDuctTape extends GTItemBlockRare {

	public GTItemBlockDuctTape(Block block) {
		super(block);
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
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}

	@Override
	public int getMetadata(int damage) {
		return 0;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format(this.getUnlocalizedName().replace("tile", "tooltip")));
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
