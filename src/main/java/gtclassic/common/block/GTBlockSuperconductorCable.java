package gtclassic.common.block;

import java.util.List;
import java.util.Random;

import gtclassic.GTMod;
import gtclassic.api.block.GTBlockBaseConnect;
import gtclassic.api.interfaces.IGTReaderInfoBlock;
import gtclassic.api.model.GTModelWire;
import gtclassic.common.GTLang;
import gtclassic.common.tile.GTTileSuperconductorCable;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.lang.storage.Ic2InfoLang;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.models.BaseModel;
import ic2.core.util.helpers.BlockStateContainerIC2;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockSuperconductorCable extends GTBlockBaseConnect implements IGTReaderInfoBlock {

	public GTBlockSuperconductorCable() {
		super();
		setUnlocalizedName(GTLang.SUPERCONDUCTORCABLE);
		setRegistryName("superconductorcable");
		this.setHardness(0.2F);
		this.setSoundType(SoundType.CLOTH);
		this.setHarvestLevel("axe", 0);
		setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
		return true;
	}

	@Override
	public TileEntityBlock createNewTileEntity(World arg0, int arg1) {
		return new GTTileSuperconductorCable();
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BaseModel getModelFromState(IBlockState state) {
		return new GTModelWire(state, Ic2Icons.getTextures("superconductorcable")[0], new int[] { 3, 13 });
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite[] getIconSheet(int arg0) {
		return Ic2Icons.getTextures("superconductorcable");
	}

	@Override
	public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
		try {
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof GTTileSuperconductorCable) {
				GTTileSuperconductorCable wire = (GTTileSuperconductorCable) tile;
				return new BlockStateContainerIC2.IC2BlockState(state, wire.getConnections());
			}
		} catch (Exception exception) {
		}
		return super.getExtendedState(state, world, pos);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);
		if (!(tile instanceof GTTileSuperconductorCable)) {
			return new AxisAlignedBB(0.25D, 0.25D, 0.25D, 0.75D, 0.75D, 0.75D);
		} else {
			GTTileSuperconductorCable pipe = (GTTileSuperconductorCable) tile;
			double thickness = (13 - 3) / 32.0D;
			double minX = 0.5D - thickness;
			double minY = 0.5D - thickness;
			double minZ = 0.5D - thickness;
			double maxX = 0.5D + thickness;
			double maxY = 0.5D + thickness;
			double maxZ = 0.5D + thickness;
			if (pipe.connection.contains(EnumFacing.WEST)) {
				minX = 0.0D;
			}
			if (pipe.connection.contains(EnumFacing.DOWN)) {
				minY = 0.0D;
			}
			if (pipe.connection.contains(EnumFacing.NORTH)) {
				minZ = 0.0D;
			}
			if (pipe.connection.contains(EnumFacing.EAST)) {
				maxX = 1.0D;
			}
			if (pipe.connection.contains(EnumFacing.UP)) {
				maxY = 1.0D;
			}
			if (pipe.connection.contains(EnumFacing.SOUTH)) {
				maxZ = 1.0D;
			}
			return new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
		}
	}

	@Override
	public void addReaderInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add((Ic2InfoLang.euReaderCableLimit.getLocalizedFormatted(new Object[] { 134217728 })));
		tooltip.add((Ic2InfoLang.euReaderCableLoss.getLocalizedFormatted(new Object[] { 0.001 })));
	}
}
