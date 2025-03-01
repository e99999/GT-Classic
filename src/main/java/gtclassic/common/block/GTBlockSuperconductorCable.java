package gtclassic.common.block;

import java.util.List;
import java.util.Random;

import gtclassic.GTMod;
import gtclassic.api.block.GTBlockBaseConnect;
import gtclassic.api.helpers.GTValues;
import gtclassic.api.interfaces.IGTReaderInfoBlock;
import gtclassic.api.model.GTModelWire;
import gtclassic.api.tile.GTTileBaseSuperconductorCable;
import gtclassic.common.GTBlocks;
import gtclassic.common.GTLang;
import gtclassic.common.tile.GTTileSuperconductorCables;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.lang.storage.Ic2InfoLang;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.models.BaseModel;
import ic2.core.util.helpers.BlockStateContainerIC2;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
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

	int size;

	public GTBlockSuperconductorCable(int size, String suffix) {
		super();
		setTranslationKey(GTLang.SUPERCONDUCTORCABLE);
		setRegistryName("superconductorcable" + suffix);
		this.size = size;
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
	public boolean hasFacing() {
		return false;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (this.equals(GTBlocks.tileSuperconductorCableBEACON)) {
			tooltip.add(I18n.format(GTValues.TOOLTIP_BEACON));
		}
	}

	@Override
	public boolean isBeaconBase(IBlockAccess world, BlockPos pos, BlockPos beacon) {
		return this.equals(GTBlocks.tileSuperconductorCableBEACON);
	}

	@Override
	public TileEntityBlock createNewTileEntity(World arg0, int arg1) {
		if (this == GTBlocks.tileSuperconductorCableIV) {
			return new GTTileSuperconductorCables.SuperconductorIV();
		}
		if (this == GTBlocks.tileSuperconductorCableHV) {
			return new GTTileSuperconductorCables.SuperconductorHV();
		}
		return new GTTileSuperconductorCables.SuperconductorMAX();
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	private int[] getSize() {
		int var = (16 - this.size) / 2;
		return new int[] { 0 + var, 16 - var };
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BaseModel getModelFromState(IBlockState state) {
		return new GTModelWire(state, getSize());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite[] getIconSheet(int arg0) {
		return null;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getTextureFromState(IBlockState iBlockState, EnumFacing enumFacing) {
		return Ic2Icons.getTextures("gtclassic_materials")[12];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getParticleTexture(IBlockState state) {
		return this.getTextureFromState(state, EnumFacing.SOUTH);
	}

	@Override
	public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
		try {
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof GTTileBaseSuperconductorCable) {
				GTTileBaseSuperconductorCable wire = (GTTileBaseSuperconductorCable) tile;
				return new BlockStateContainerIC2.IC2BlockState(state, wire.getConnections());
			}
		} catch (Exception exception) {
		}
		return super.getExtendedState(state, world, pos);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);
		if (!(tile instanceof GTTileBaseSuperconductorCable)) {
			return new AxisAlignedBB(0.25D, 0.25D, 0.25D, 0.75D, 0.75D, 0.75D);
		} else {
			GTTileBaseSuperconductorCable cable = (GTTileBaseSuperconductorCable) tile;
			double thickness = this.size / 32.0D;
			double minX = 0.5D - thickness;
			double minY = 0.5D - thickness;
			double minZ = 0.5D - thickness;
			double maxX = 0.5D + thickness;
			double maxY = 0.5D + thickness;
			double maxZ = 0.5D + thickness;
			if (cable.connection.contains(EnumFacing.WEST)) {
				minX = 0.0D;
			}
			if (cable.connection.contains(EnumFacing.DOWN)) {
				minY = 0.0D;
			}
			if (cable.connection.contains(EnumFacing.NORTH)) {
				minZ = 0.0D;
			}
			if (cable.connection.contains(EnumFacing.EAST)) {
				maxX = 1.0D;
			}
			if (cable.connection.contains(EnumFacing.UP)) {
				maxY = 1.0D;
			}
			if (cable.connection.contains(EnumFacing.SOUTH)) {
				maxZ = 1.0D;
			}
			return new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
		}
	}

	@Override
	public void addReaderInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (this == GTBlocks.tileSuperconductorCableMAX || this == GTBlocks.tileSuperconductorCableBEACON) {
			tooltip.add((Ic2InfoLang.euReaderCableLimit.getLocalizedFormatted(new Object[] { 134217728 })));
		}
		if (this == GTBlocks.tileSuperconductorCableIV) {
			tooltip.add((Ic2InfoLang.euReaderCableLimit.getLocalizedFormatted(new Object[] { 32769 })));
		}
		if (this == GTBlocks.tileSuperconductorCableHV) {
			tooltip.add((Ic2InfoLang.euReaderCableLimit.getLocalizedFormatted(new Object[] { 512 })));
		}
		tooltip.add((Ic2InfoLang.euReaderCableLoss.getLocalizedFormatted(new Object[] { 0.001 })));
	}
}
