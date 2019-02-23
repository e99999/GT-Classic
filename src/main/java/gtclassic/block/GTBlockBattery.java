package gtclassic.block;

import java.util.ArrayList;

import gtclassic.itemblock.GTItemBlockBattery;
import gtclassic.itemblock.GTItemBlockInterface;
import gtclassic.tile.GTTileBlockCustom;
import ic2.api.classic.item.IDamagelessElectricItem;
import ic2.api.item.ElectricItem;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.item.block.ItemBlockRare;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class GTBlockBattery extends GTBlockTileCustom implements GTItemBlockInterface {
	public int max;
	public int trans;
	public int tier;
	public int charge;

	public GTBlockBattery(String name, int width, int height, boolean light, int max, int trans, int tier) {
		super(name, width, height, light);
		this.max = max;
		this.trans = trans;
		this.tier = tier;
	}

	@Override
	public Class<? extends ItemBlockRare> getCustomItemBlock() {
		return GTItemBlockBattery.class;
	}

	@Override
	public TileEntityBlock createNewTileEntity(World world, int arg1) {
		return new GTTileBlockCustom(this.charge);
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		if (stack.getItem() instanceof IDamagelessElectricItem) {
			this.charge = (int) ElectricItem.manager.getCharge(stack);
		}
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}

	public int getTileCharge() {
		TileEntity tile = this.getLocalTile();
		if (tile instanceof GTTileBlockCustom) {
			GTTileBlockCustom te = (GTTileBlockCustom) tile;
			return te.getData();
		} else {
			return 0;
		}
	}

	@Override
		 public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos,
		 IBlockState state, int fortune) {
		 ArrayList<ItemStack> drops = new ArrayList<>();
		 ItemStack stack =  new ItemStack(this, 1, 0);
		//ElectricItem.manager.charge(stack, var2, var4, var5, var6);
		 drops.add(stack);
		 return drops;
		 }
}
