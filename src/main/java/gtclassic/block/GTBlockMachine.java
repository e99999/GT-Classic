package gtclassic.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTBlocks;
import gtclassic.GTMod;
import gtclassic.tile.GTTileCabinet;
import gtclassic.tile.GTTileCentrifuge;
import gtclassic.tile.GTTileComputerCube;
import gtclassic.tile.GTTileMatterFabricator;
import gtclassic.tile.GTTilePlayerDetector;
import gtclassic.tile.GTTileWorktable;
import gtclassic.tile.multi.GTTileMultiBlastFurnace;
import gtclassic.tile.multi.GTTileMultiFusion;
import gtclassic.tile.multi.GTTileMultiLightningRod;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockMachine extends GTBlockMultiID {

	String name;
	String texture;
	int size = 0;

	public GTBlockMachine(String name) {
		super(Material.IRON);
		this.name = name;
		this.size = 1;
		setRegistryName(this.name.toLowerCase());
		setUnlocalizedName(GTMod.MODID + "." + this.name.toLowerCase());
		setCreativeTab(GTMod.creativeTabGT);
		setBlockUnbreakable();
		setResistance(20.0F);
		setSoundType(SoundType.METAL);
	}

	public GTBlockMachine(String name, int additionalInfo) {
		super(Material.IRON);
		this.name = name;
		this.size = additionalInfo + 1;
		setRegistryName(this.name.toLowerCase());
		setUnlocalizedName(GTMod.MODID + "." + this.name.toLowerCase());
		setCreativeTab(GTMod.creativeTabGT);
		setBlockUnbreakable();
		setResistance(20.0F);
		setSoundType(SoundType.METAL);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		for (int i = 0; i < this.size; i++) {
			tooltip.add(I18n.format(this.getUnlocalizedName().replace("tile", "tooltip") + i));
		}
	}

	@Override
	public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, SpawnPlacementType type) {
		return false;
	}

	@Override
	@Deprecated
	public boolean canProvidePower(IBlockState state) {
		int meta = this.getMetaFromState(state);
		return meta >= 0 && meta <= 2 ? true : super.canProvidePower(state);
	}

	@Override
	public TileEntityBlock createNewTileEntity(World worldIn, int meta) {
		if (this == GTBlocks.tileComputer) {
			return new GTTileComputerCube();
		}
		if (this == GTBlocks.tileCentrifuge) {
			return new GTTileCentrifuge();
		}
		if (this == GTBlocks.tileBlastFurnace) {
			return new GTTileMultiBlastFurnace();
		}
		if (this == GTBlocks.tilePlayerDetector) {
			return new GTTilePlayerDetector();
		}
		if (this == GTBlocks.tileLightningRod) {
			return new GTTileMultiLightningRod();
		}
		if (this == GTBlocks.tileFabricator) {
			return new GTTileMatterFabricator();
		}
		if (this == GTBlocks.tileFusionComputer) {
			return new GTTileMultiFusion();
		}
		if (this == GTBlocks.tileWorktable) {
			return new GTTileWorktable();
		}
		if (this == GTBlocks.tileCabinet) {
			return new GTTileCabinet();
		} else {
			return new TileEntityBlock();
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite[] getIconSheet(int meta) {
		return Ic2Icons.getTextures(this.name);
	}

	@Override
	public int getMaxSheetSize(int meta) {
		return 1;
	}

	@Override
	@Deprecated
	public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		TileEntity tile = blockAccess.getTileEntity(pos);
		if (tile instanceof TileEntityElectricBlock) {
			return ((TileEntityElectricBlock) tile).isEmittingRedstone() ? 15 : 0;
		} else {
			return super.getStrongPower(blockState, blockAccess, pos, side);
		}
	}

	@Override
	@Deprecated
	public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		TileEntity tile = blockAccess.getTileEntity(pos);
		if (tile instanceof TileEntityElectricBlock) {
			return ((TileEntityElectricBlock) tile).isEmittingRedstone() ? 15 : 0;
		} else {
			return super.getWeakPower(blockState, blockAccess, pos, side);
		}
	}

	@Override
	public List<Integer> getValidMetas() {
		return Arrays.asList(0);
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
	public List<IBlockState> getValidStates() {
		return getBlockState().getValidStates();
	}
}