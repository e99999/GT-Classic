package gtclassic.common.block;

import java.util.Random;

import gtclassic.GTMod;
import gtclassic.api.block.GTBlockBaseCable;
import gtclassic.api.block.GTBlockBaseConnect;
import gtclassic.api.helpers.GTValues;
import gtclassic.api.interfaces.IGTReaderInfoBlock;
import gtclassic.api.interfaces.IGTRecolorableStorageTile;
import gtclassic.api.interfaces.IGTTextureStorageTile;
import gtclassic.api.tile.GTTileBaseSuperconductorCable;
import gtclassic.common.GTBlocks;
import gtclassic.common.GTLang;
import gtclassic.common.tile.GTTileSuperconductorCables;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.lang.storage.Ic2InfoLang;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.Color;
import java.util.List;

public class GTBlockSuperconductorCable extends GTBlockBaseCable implements IGTReaderInfoBlock {

	int size;

	public GTBlockSuperconductorCable(int size, String suffix) {
		super();
		setUnlocalizedName(GTLang.SUPERCONDUCTORCABLE);
		setRegistryName("superconductorcable" + suffix);
		this.size = size;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
								ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		TileEntity tile = worldIn.getTileEntity(pos);
		NBTTagCompound nbt = StackUtil.getNbtData(stack);
		if (tile instanceof IGTRecolorableStorageTile) {
			IGTRecolorableStorageTile colorTile = (IGTRecolorableStorageTile) tile;
			if (nbt.hasKey("color")) {
				colorTile.setTileColor(nbt.getInteger("color"));
			}
		}
	}

	@Override
	public boolean recolorBlock(World world, BlockPos pos, EnumFacing side, EnumDyeColor color) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof GTTileBaseSuperconductorCable) {
			GTTileBaseSuperconductorCable colorTile = (GTTileBaseSuperconductorCable) tile;
			colorTile.setTileColor(GTValues.getColorFromEnumDyeColor(color), side);
			IBlockState state = tile.getWorld().getBlockState(tile.getPos());
			world.notifyBlockUpdate(pos, state, state, 2);
			return true;
		}
		return false;
	}

	@Override
	public Color getColor(IBlockState state, IBlockAccess worldIn, BlockPos pos, Block block, int index) {
		if (state != null && state.getValue(FOAMED) > 0){
			if (worldIn != null) {
				TileEntity tile = worldIn.getTileEntity(pos);
				if (tile instanceof IGTTextureStorageTile && state.getValue(FOAMED) > 1) {
					IGTTextureStorageTile colorTile = (IGTTextureStorageTile) tile;
					int dir = index / 50;
					int tintIndex = index % 50;
					int[] colors = colorTile.getStorage().getEntry(dir).getColorMultiplier();
					if (tintIndex >= colors.length){
						return Color.WHITE;
					}
					return new Color(colors[tintIndex]);
				}
			}
		} else{
			if (index == 0){
				if (worldIn != null && state != null && state.getValue(active) && state.getValue(FOAMED) == 0) {
					TileEntity tile = worldIn.getTileEntity(pos);
					if (tile instanceof IGTRecolorableStorageTile) {
						IGTRecolorableStorageTile colorTile = (IGTRecolorableStorageTile) tile;
						return colorTile.getTileColor();
					}
				}
				return Color.WHITE;
			}
		}
		return Color.WHITE;
   }
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
	public int getThickness(IBlockState state) {
		return size;
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
	public IBlockState getStateFromStack(ItemStack stack) {
		NBTTagCompound nbt = StackUtil.getOrCreateNbtData(stack);
		boolean isActive = nbt.hasKey("color");
		return this.getDefaultState().withProperty(active, isActive);
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		state = this.getActualState(state, world, pos);
		ItemStack stack = super.getPickBlock(state, target, world, pos, player);
		if (state.getValue(FOAMED) == 1){
			return Ic2Items.constructionFoam;
		}
		NBTTagCompound nbt = StackUtil.getOrCreateNbtData(stack);
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof GTTileBaseSuperconductorCable){
			GTTileBaseSuperconductorCable cable = (GTTileBaseSuperconductorCable) tile;
			if (cable.foamed > 0) {
				try {
					IBlockState realState = cable.storage.getEntry(target.sideHit.getIndex()).getModelState();
					return realState.getBlock().getPickBlock(realState, target, world, pos, player);
				} catch (Exception ignored) {
				}
			}
			if (cable.isColored()){
				nbt.setInteger("color", cable.getTileColor().getRGB());
			}
		}
		return stack;
	}

	@Override
	public void addReaderInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (this == GTBlocks.tileSuperconductorCableMAX || this == GTBlocks.tileSuperconductorCableBEACON) {
			tooltip.add((Ic2InfoLang.euReaderCableLimit.getLocalizedFormatted(new Object[] { 134217728 })));
		}
		if (this == GTBlocks.tileSuperconductorCableIV) {
			tooltip.add((Ic2InfoLang.euReaderCableLimit.getLocalizedFormatted(32769)));
		}
		if (this == GTBlocks.tileSuperconductorCableHV) {
			tooltip.add((Ic2InfoLang.euReaderCableLimit.getLocalizedFormatted(512)));
		}
		tooltip.add((Ic2InfoLang.euReaderCableLoss.getLocalizedFormatted(0.001)));
	}
}
