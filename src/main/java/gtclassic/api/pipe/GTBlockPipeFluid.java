package gtclassic.api.pipe;

import gtclassic.GTMod;
import gtclassic.api.material.GTMaterial;
import gtclassic.api.pipe.GTPipeTypes.GTFluidPipeType;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.world.World;

public class GTBlockPipeFluid extends GTBlockPipeBase {

	GTFluidPipeType type;

	public GTBlockPipeFluid(GTMaterial mat, GTFluidPipeType type, LocaleComp comp) {
		super(mat, type.getSizes());
		this.type = type;
		setRegistryName("pipe_fluid_" + mat.getName() + type.getSuffix());
		setUnlocalizedName(comp);
		setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public TileEntityBlock createNewTileEntity(World worldIn, int meta) {
		return new GTTilePipeFluid();
	}

	public GTFluidPipeType getPipeType() {
		return this.type;
	}
}
