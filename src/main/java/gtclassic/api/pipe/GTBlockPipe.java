package gtclassic.api.pipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.common.GTLang;
import ic2.core.block.base.BlockMultiID;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.util.helpers.BlockStateContainerIC2;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class GTBlockPipe extends BlockMultiID {

	public GTBlockPipe() {
		super(Material.IRON);
		setRegistryName("pipe");
		setUnlocalizedName(GTLang.PIPE);
		setCreativeTab(GTMod.creativeTabGT);
		setHardness(5.0F);
		setResistance(10.0F);
		setHarvestLevel("pickaxe", 2);
	}

	@Override
	public List<Integer> getValidMetas() {
		return Arrays.asList(0);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainerIC2(this, allFacings, active);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState();
	}

	@Override
	public IBlockState getDefaultBlockState() {
		IBlockState state = getDefaultState().withProperty(active, false);
		if (hasFacing()) {
			state = state.withProperty(allFacings, EnumFacing.NORTH);
		}
		return state;
	}
	
	@Override
	public List<IBlockState> getValidStates() {
		return getBlockState().getValidStates();
	}
	
	@Override
	public List<IBlockState> getValidStateList() {
		IBlockState def = getDefaultState();
		List<IBlockState> states = new ArrayList<>();
		for (EnumFacing side : EnumFacing.VALUES) {
			states.add(def.withProperty(allFacings, side).withProperty(active, false));
			states.add(def.withProperty(allFacings, side).withProperty(active, true));
		}
		return states;
	}
	
	@Override
	public TileEntityBlock createNewTileEntity(World worldIn, int meta) {
		return new TileEntityBlock();
	}

	@Override
	public TextureAtlasSprite[] getIconSheet(int var1) {
		return Ic2Icons.getTextures("pipe");
	}
	
	@Override
	public int getMaxSheetSize(int meta) {
		return 1;
	}
}
