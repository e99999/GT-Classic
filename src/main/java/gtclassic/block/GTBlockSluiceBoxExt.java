package gtclassic.block;

import gtclassic.GTMod;
import gtclassic.models.GTSluiceBoxExtensionModel;
import gtclassic.util.GTValues;
import ic2.core.platform.textures.models.BaseModel;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockSluiceBoxExt extends GTBlockFacing {

	static final AxisAlignedBB SLUICE_EXT = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.4D, 1.0D);

	public GTBlockSluiceBoxExt() {
		super(Material.WOOD);
		setRegistryName(GTValues.sluiceBoxExtension.getUnlocalized().replaceAll("tile.gtclassic.", ""));
		setUnlocalizedName(GTValues.sluiceBoxExtension);
		setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BaseModel getModelFromState(IBlockState state) {
		return new GTSluiceBoxExtensionModel(getRotation(state));
	}

	@Override
	@Deprecated
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return SLUICE_EXT;
	}

}
