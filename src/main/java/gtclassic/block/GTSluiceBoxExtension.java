package gtclassic.block;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTBlocks;
import gtclassic.GTMod;
import gtclassic.models.GTSluiceBoxExtensionModel;
import gtclassic.util.GTValues;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.textures.models.BaseModel;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTSluiceBoxExtension extends GTFacingBlock {

	public GTSluiceBoxExtension() {
		super(Material.IRON);
		setRegistryName(GTValues.sluiceBoxExtension.getUnlocalized().replaceAll("tile.gtclassic.", ""));
		setUnlocalizedName(GTValues.sluiceBoxExtension.getUnlocalized());
		setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public BaseModel getModelFromState(IBlockState state) {
		return new GTSluiceBoxExtensionModel();
	}

	@Override
	public LocaleComp getName() {
		return GTValues.sluiceBoxExtension;
	}

	@Override
	public List<ItemStack> getWrenchDrops(World world, BlockPos pos, IBlockState state, TileEntity te,
			EntityPlayer player, int fortune) {
		return Arrays.asList(new ItemStack(GTBlocks.sluiceBoxExtension));
	}

}
