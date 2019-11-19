package gtclassic.common.block;

import java.util.List;
import java.util.Random;

import gtclassic.common.GTLang;
import gtclassic.common.tile.GTTileSuperconductorCable;
import ic2.core.block.base.tile.TileEntityBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class GTBlockSuperconductorCable extends GTBlockMachine {

	public GTBlockSuperconductorCable() {
		super("superconductorcable", GTLang.SUPERCONDUCTORCABLE, Material.IRON);
		this.setHardness(0.2F);
		this.setSoundType(SoundType.CLOTH);
		this.setHarvestLevel("axe", 0);
	}

	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
		return true;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
	}

	@Override
	public TileEntityBlock createNewTileEntity(World arg0, int arg1) {
		return new GTTileSuperconductorCable();
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}
}
