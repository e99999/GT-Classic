package gtclassic.api.pipe;

import gtclassic.GTMod;
import gtclassic.api.material.GTMaterial;
import gtclassic.api.pipe.GTPipeTypes.GTItemPipeType;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.world.World;

public class GTBlockPipeItem extends GTBlockPipeBase {

	GTItemPipeType type;

	public GTBlockPipeItem(GTMaterial mat, GTItemPipeType type, LocaleComp comp) {
		super(mat, type.getSizes());
		this.type = type;
		setRegistryName("pipe_item_" + mat.getName() + type.getSuffix());
		setUnlocalizedName(comp);
		setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public TileEntityBlock createNewTileEntity(World worldIn, int meta) {
		return new GTTilePipeItem();
	}

	public GTItemPipeType getPipeType() {
		return this.type;
	}
}