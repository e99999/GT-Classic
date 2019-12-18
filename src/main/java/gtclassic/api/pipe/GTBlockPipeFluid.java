package gtclassic.api.pipe;

import java.util.List;

import gtclassic.GTMod;
import gtclassic.api.material.GTMaterial;
import gtclassic.api.pipe.GTHelperPipes.GTPipeFluidCapacity;
import gtclassic.api.pipe.GTHelperPipes.GTPipeModel;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class GTBlockPipeFluid extends GTBlockPipeBase {

	GTPipeModel type;
	GTPipeFluidCapacity size;

	public GTBlockPipeFluid(GTMaterial mat, GTPipeModel type, GTPipeFluidCapacity size) {
		super(mat, type.getSizes());
		this.type = type;
		this.size = size;
		setRegistryName(mat.getName() + "_fluidpipe" + type.getSuffix());
		setUnlocalizedName(GTMod.MODID + "." + mat.getName() + "fluidpipe" + type.getPrefix());
		setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.BLUE + I18n.format("Fluid Capacity:  " + this.size.getSize() + " mB/sec"));
		NBTTagCompound nbt = StackUtil.getNbtData(stack);
		if (nbt.hasKey("color")) {
			tooltip.add(I18n.format("Can be painted white to remove color"));
		} else {
			tooltip.add(I18n.format("Can be painted different colors"));
		}
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
		case S25600:
			return new GTTilePipeFluid.GTTileFluidPipe25600();
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

	public GTPipeModel getPipeType() {
		return this.type;
	}

	public GTPipeFluidCapacity getCapacity() {
		return this.size;
	}
}
