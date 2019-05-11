package gtclassic.block;

import gtclassic.GTMod;
import gtclassic.models.GTModelSluiceBox;
import gtclassic.tile.GTTileSluice;
import gtclassic.util.GTValues;
import ic2.core.platform.textures.models.BaseModel;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockSluice extends GTBlockFacing {

	static final AxisAlignedBB SLUICE_FULL = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.8D, 1.0D);

	public GTBlockSluice() {
		super(Material.WOOD);
		setRegistryName(GTValues.sluiceBox.getUnlocalized().replaceAll("tile.gtclassic.", ""));
		setUnlocalizedName(GTValues.sluiceBox);
		setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BaseModel getModelFromState(IBlockState state) {
		return GTModelSluiceBox.getModel();
	}

	@Override
	public EnumFacing[] getAllowedRotations() {
		return EnumFacing.HORIZONTALS;
	}

	@Override
	@Deprecated
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return SLUICE_FULL;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new GTTileSluice(getAllowedRotations());
	}

}
