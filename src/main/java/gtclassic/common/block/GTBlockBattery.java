package gtclassic.common.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gtclassic.api.interfaces.IGTItemBlock;
import gtclassic.common.GTLang;
import gtclassic.common.tile.GTTileBattery;
import gtclassic.common.util.GTItemBlockBattery;
import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.item.block.ItemBlockRare;
import ic2.core.util.obj.IItemContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTBlockBattery extends GTBlockMachine implements IGTItemBlock {

	public GTBlockBattery() {
		super("battery", GTLang.BATTERY, Material.GROUND, 0);
		this.setHardness(0.2F);
		this.setSoundType(SoundType.CLOTH);
	}

	@Override
	public Class<? extends ItemBlockRare> getCustomItemBlock() {
		return GTItemBlockBattery.class;
	}

	@Override
	public TileEntityBlock createNewTileEntity(World world, int arg1) {
		return new GTTileBattery();
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		if (!IC2.platform.isRendering()) {
			TileEntity tile = worldIn.getTileEntity(pos);
			if (tile instanceof GTTileBattery) {
				((GTTileBattery) tile).setItem(stack.copy());
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
