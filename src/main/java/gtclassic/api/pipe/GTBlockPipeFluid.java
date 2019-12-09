package gtclassic.api.pipe;

import java.util.List;

import gtclassic.GTMod;
import gtclassic.api.material.GTMaterial;
import gtclassic.api.pipe.GTPipeTypes.GTFluidPipeSize;
import gtclassic.api.pipe.GTPipeTypes.GTFluidPipeType;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class GTBlockPipeFluid extends GTBlockPipeBase {

	GTFluidPipeType type;
	GTFluidPipeSize size;

	public GTBlockPipeFluid(GTMaterial mat, GTFluidPipeType type, GTFluidPipeSize size, LocaleComp comp) {
		super(mat, type.getSizes());
		this.type = type;
		this.size = size;
		setRegistryName("pipe_fluid_" + mat.getName() + type.getSuffix());
		setUnlocalizedName(comp);
		setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.BLUE + I18n.format("Fluid Capacity:  " + this.size.getSize() + " mB/sec"));
	}

	@Override
	public TileEntityBlock createNewTileEntity(World worldIn, int meta) {
		switch (this.size) {
		case S800:
			return new GTTilePipeFluid.GTTileFluidPipe800();
		case S1600:
			return new GTTilePipeFluid.GTTileFluidPipe1600();
		case S3200:
			return new GTTilePipeFluid.GTTileFluidPipe3200();
		case S6400:
			return new GTTilePipeFluid.GTTileFluidPipe6400();
		case S12800:
			return new GTTilePipeFluid.GTTileFluidPipe12800();
		case SMAX1:
			return new GTTilePipeFluid.GTTileFluidPipeMax1();
		case SMAX2:
			return new GTTilePipeFluid.GTTileFluidPipeMax2();
		case SMAX3:
			return new GTTilePipeFluid.GTTileFluidPipeMax3();
		default:
			return new TileEntityBlock();
		}
	}

	public String getPipeType() {
		return this.type.getName();
	}

	public int getCapacity() {
		return this.size.getSize();
	}
}
