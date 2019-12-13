package gtclassic.common.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gtclassic.api.interfaces.IGTItemBlock;
import gtclassic.api.tile.GTTileBatteryBase;
import gtclassic.common.GTBlocks;
import gtclassic.common.itemblock.GTItemBlockBattery;
import gtclassic.common.tile.GTTileBattery;
import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.item.block.ItemBlockRare;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.lang.storage.Ic2InfoLang;
import ic2.core.util.obj.IItemContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTBlockBattery extends GTBlockMachine implements IGTItemBlock {

	public GTBlockBattery(String name, LocaleComp comp) {
		super(name, comp, Material.GROUND, 3);
		this.setHardness(0.2F);
		this.setSoundType(SoundType.CLOTH);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("tooltip.gtclassic.batteryblock0"));
		tooltip.add(I18n.format("tooltip.gtclassic.batteryblock1"));
		tooltip.add(I18n.format("tooltip.gtclassic.batteryblock2"));
	}
	
	@Override
	public void addReaderInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (this == GTBlocks.tileBatteryLV) {
			tooltip.add((Ic2InfoLang.electricMaxIn.getLocalizedFormatted(new Object[] { 32 })));
			tooltip.add((Ic2InfoLang.electricMaxStorage.getLocalizedFormatted(new Object[] { 80000 })));
		}
	}

	@Override
	public Class<? extends ItemBlockRare> getCustomItemBlock() {
		return GTItemBlockBattery.GTItemBlockBatteryLV.class;
	}

	@Override
	public TileEntityBlock createNewTileEntity(World world, int arg1) {
		return new GTTileBattery.GTTileBatteryLV();
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		if (!IC2.platform.isRendering()) {
			TileEntity tile = worldIn.getTileEntity(pos);
			if (tile instanceof GTTileBatteryBase) {
				((GTTileBatteryBase) tile).setItem(stack.copy());
			}
		}
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}

	@Override
	public List<ItemStack> getWrenchDrops(World world, BlockPos pos, IBlockState state, TileEntity te,
			EntityPlayer player, int fortune) {
		List<ItemStack> items = new ArrayList<>();
		if (te instanceof IItemContainer) {
			items.addAll(((IItemContainer) te).getDrops());
		}
		return items;
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}
}
