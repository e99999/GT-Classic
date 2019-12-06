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
		tooltip.add(I18n.format("Transfers " + this.size.getSize() + " mB per second"));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public TileEntityBlock createNewTileEntity(World worldIn, int meta) {
		return new GTTilePipeFluid();
	}

	public String getPipeType() {
		return this.type.getName();
	}

	public int getCapacity() {
		return this.size.getSize();
	}
}
