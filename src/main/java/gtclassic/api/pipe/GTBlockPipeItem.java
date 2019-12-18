package gtclassic.api.pipe;

import java.util.List;

import gtclassic.GTMod;
import gtclassic.api.material.GTMaterial;
import gtclassic.api.pipe.GTHelperPipes.GTPipeModel;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class GTBlockPipeItem extends GTBlockPipeBase {

	GTPipeModel type;

	public GTBlockPipeItem(GTMaterial mat, GTPipeModel type) {
		super(mat, type.getSizes());
		this.type = type;
		setRegistryName(mat.getName() + "_itempipe" + type.getSuffix());
		setUnlocalizedName(GTMod.MODID + "." + mat.getName() + "itempipe" + type.getPrefix());
		setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("Item Capacity:  " + (this.type == GTPipeModel.LARGE ? 8 : 2) + " Stacks/sec"));
		NBTTagCompound nbt = StackUtil.getNbtData(stack);
		if (nbt.hasKey("color")) {
			tooltip.add(I18n.format("Can be painted white to remove color"));
		} else {
			tooltip.add(I18n.format("Can be painted different colors"));
		}
	}

	@Override
	public TileEntityBlock createNewTileEntity(World worldIn, int meta) {
		if (this.type == GTPipeModel.LARGE) {
			return new GTTilePipeItem.GTTilePipeItemLarge();
		}
		return new GTTilePipeItem.GTTilePipeItemSmall();
	}

	public GTPipeModel getPipeType() {
		return this.type;
	}
}