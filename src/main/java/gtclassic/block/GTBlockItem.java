package gtclassic.block;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import gtclassic.GTClassic;
import gtclassic.util.GTValues;
import ic2.core.block.base.BlockCommonContainer;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ITexturedBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockItem extends BlockCommonContainer implements ITexturedBlock {

	/*
	 * GTBlockItemVariants enums
	 * 
	 * @param enum name
	 * 
	 * @param vertical axis sprite index
	 * 
	 * @param horizontal axis sprite index
	 * 
	 * @param bounding box model to use, feel free to add your own
	 */

	public enum GTBlockItemVariants {
		SMALL_COOLANT(32, 32, GTValues.SMALLCOOLANT_AABB), 
		MED_COOLANT(32, 32, GTValues.LARGECOOLANT_AABB),
		LARGE_COOLANT(32, 32, GTValues.LARGECOOLANT_AABB),

		SMALL_THORIUM(32, 32, GTValues.SMALLROD_AABB), 
		MED_THORIUM(32, 32, GTValues.MEDROD_AABB),
		LARGE_THORIUM(32, 32, GTValues.LARGEROD_AABB),

		SMALL_PLUTONIUM(32, 32, GTValues.SMALLROD_AABB), 
		MED_PLUTONIUM(32, 32, GTValues.MEDROD_AABB),
		LARGE_PLUTONIUM(32, 32, GTValues.LARGEROD_AABB),

		SMALL_LITHIUM(16, 16, GTValues.SLIMBATTERY_AABB), 
		MED_LITHIUM(16, 16, GTValues.MEDBATTERY_AABB),
		LARGE_LITHIUM(16, 16, GTValues.LARGEBATTERY_AABB),

		SMALL_LAPOTRON(17, 17, GTValues.SMALLBATTERY_AABB), 
		MED_LAPOTRON(17, 17, GTValues.MEDBATTERY_AABB),
		LARGE_LAPOTRON(17, 17, GTValues.LARGEBATTERY_AABB),

		SMALL_ENERGIUM(18, 18, GTValues.SMALLBATTERY_AABB), 
		MED_ENERGIUM(18, 18, GTValues.MEDBATTERY_AABB),
		LARGE_ENERGIUM(18, 18, GTValues.LARGEBATTERY_AABB),

		ALUMINIUM_DATASTICK(32, 35, GTValues.DATASTICK_AABB), 
		TITANIUM_DATASTICK(32, 36, GTValues.DATASTICK_AABB),
		CHROME_DATASTICK(32, 37, GTValues.DATASTICK_AABB),

		ALUMINIUM_DATADRIVE(32, 38, GTValues.DATADRIVE_AABB), 
		TITANIUM_DATADRIVE(32, 39, GTValues.DATADRIVE_AABB),
		CHROME_DATADRIVE(32, 40, GTValues.DATADRIVE_AABB),

		ENERGY_CIRCUIT(33, 32, GTValues.CIRCUIT_AABB), 
		DATA_CIRCUIT(34, 32, GTValues.CIRCUIT_AABB);

		private int vertical;
		private int horizontal;
		private AxisAlignedBB box;

		GTBlockItemVariants(int vertical, int horizontal, AxisAlignedBB box) {
			this.vertical = vertical;
			this.horizontal = horizontal;
			this.box = box;
		}

		public int getVertical() {
			return vertical;
		}

		public int getHorizontal() {
			return horizontal;
		}

		public AxisAlignedBB getBox() {
			return box;
		}
	}

	GTBlockItemVariants variant;

	public GTBlockItem(GTBlockItemVariants variant) {
		super(Material.CLOTH);
		this.variant = variant;
		setRegistryName(variant.toString().toLowerCase() + "_tileblock");
		setUnlocalizedName(GTClassic.MODID + "." + variant.toString().toLowerCase() + "_tileblock");
		setCreativeTab(GTClassic.creativeTabGT);
		setHardness(0.5F);
		setResistance(30.0F);
		setSoundType(SoundType.CLOTH);
	}

	public AxisAlignedBB getBatteryBoundingBox() {
		return variant.getBox();
	}

	@Override
	@Deprecated
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	@Deprecated
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	@Deprecated
	public boolean isFullBlock(IBlockState state) {
		return false;
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox(IBlockState iBlockState) {
		return getBatteryBoundingBox();
	}

	@Override
	@Nullable
	@Deprecated
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess access, BlockPos pos) {
		return getBatteryBoundingBox();
	}

	@Override
	@Deprecated
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
		return getBatteryBoundingBox().offset(pos);
	}

	@Override
	@Deprecated
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return getBatteryBoundingBox();
	}

	@Override
	@Deprecated
	public boolean canEntitySpawn(IBlockState state, Entity entityIn) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getTextureFromState(IBlockState iBlockState, EnumFacing enumFacing) {
		if (enumFacing == EnumFacing.UP || enumFacing == EnumFacing.DOWN) {
			return Ic2Icons.getTextures("gtclassic_blocks")[variant.getVertical()];
		} else {
			return Ic2Icons.getTextures("gtclassic_blocks")[variant.getHorizontal()];
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getParticleTexture(IBlockState state) {
		return this.getTextureFromState(state, EnumFacing.SOUTH);
	}

	@Override
	public List<IBlockState> getValidStates() {
		return Arrays.asList(getDefaultState());
	}

	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateFromStack(ItemStack stack) {
		return this.getDefaultState();
	}

	@Override
	public TileEntityBlock createNewTileEntity(World arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IBlockState getDefaultBlockState() {
		return this.getDefaultState();
	}
}
