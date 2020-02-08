package gtclassic.common.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gtclassic.api.block.GTBlockBaseDataNode;
import gtclassic.common.GTBlocks;
import gtclassic.common.GTLang;
import gtclassic.common.tile.datanet.GTTileDigitizerFluid;
import gtclassic.common.tile.datanet.GTTileDigitizerItem;
import gtclassic.common.tile.datanet.GTTileNetworkEnergizer;
import gtclassic.common.tile.datanet.GTTileNetworkManager;
import gtclassic.common.tile.datanet.GTTileReconstructorFluid;
import gtclassic.common.tile.datanet.GTTileReconstructorItem;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.util.obj.IItemContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTBlockDataNode extends GTBlockBaseDataNode {

	String shortName;

	public GTBlockDataNode(String name, int id) {
		super(id);
		this.shortName = name;
		setUnlocalizedName(GTLang.DATACABLE);
		setRegistryName(name);
		this.setHardness(0.2F);
		this.setSoundType(SoundType.CLOTH);
		this.setHarvestLevel("axe", 0);
	}

	@Override
	public TileEntityBlock createNewTileEntity(World worldIn, int meta) {
		if (this == GTBlocks.tileReconstructorItem) {
			return new GTTileReconstructorItem();
		}
		if (this == GTBlocks.tileReconstructorFluid) {
			return new GTTileReconstructorFluid();
		}
		if (this == GTBlocks.tileNetworkEnergizer) {
			return new GTTileNetworkEnergizer();
		}
		if (this == GTBlocks.tileDigitizerItem) {
			return new GTTileDigitizerItem();
		}
		if (this == GTBlocks.tileDigitizerFluid) {
			return new GTTileDigitizerFluid();
		}
		if (this == GTBlocks.tileNetworkManager) {
			return new GTTileNetworkManager();
		}
		return new GTTileReconstructorItem();
	}

	@Override
	public List<ItemStack> getWrenchDrops(World world, BlockPos pos, IBlockState state, TileEntity tile,
			EntityPlayer player, int fortune) {
		List<ItemStack> items = new ArrayList<>();
		if (tile instanceof IItemContainer) {
			items.addAll(((IItemContainer) tile).getDrops());
		}
		return items;
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	public String getShortName() {
		return this.shortName;
	}
}
