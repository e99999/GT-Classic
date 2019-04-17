package gtclassic.block;

import gtclassic.GTMod;
import gtclassic.models.GTSluiceBoxModel;
import gtclassic.util.GTValues;
import ic2.core.platform.textures.models.BaseModel;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
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
		return new GTSluiceBoxModel();
	}

	@Override
	@Deprecated
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return SLUICE_FULL;
	}
}
