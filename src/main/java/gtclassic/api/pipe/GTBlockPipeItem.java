package gtclassic.api.pipe;

import java.util.List;

import gtclassic.GTMod;
import gtclassic.api.material.GTMaterial;
import gtclassic.api.pipe.GTPipeTypes.GTItemPipeType;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
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
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("Item Capacity:  " + (this.type == GTItemPipeType.LARGE ? 8 : 2) + " Stacks/sec"));
	}

	@Override
	public TileEntityBlock createNewTileEntity(World worldIn, int meta) {
		if (this.type == GTItemPipeType.LARGE) {
			return new GTTilePipeItem.GTTilePipeItemLarge();
		}
		return new GTTilePipeItem.GTTilePipeItemSmall();
	}

	public GTItemPipeType getPipeType() {
		return this.type;
	}
}