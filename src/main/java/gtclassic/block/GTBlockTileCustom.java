package gtclassic.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTClassic;
import ic2.core.block.base.BlockMultiID;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IBlockTextureModifier;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class GTBlockTileCustom extends BlockMultiID implements IBlockTextureModifier {

	/*
	 * GTBlockItemVariants enums
	 * 
	 * @param enum name & sprite sheet
	 * 
	 * @param custom block width
	 * 
	 * @param custom block height
	 * 
	 */

	public enum GTBlockTileCustomVariants {

		BATTERY_ENERGIUM_LARGE(14, 14), 
		BATTERY_ENERGIUM_MED(11, 11), 
		BATTERY_ENERGIUM_SMALL(6, 6),
		BATTERY_LAPOTRON_LARGE(14, 14), 
		BATTERY_LAPOTRON_MED(11, 11), 
		BATTERY_LAPOTRON_SMALL(6, 6),
		BATTERY_LITHIUM_LARGE(10, 11), 
		BATTERY_LITHIUM_MED(8, 11), 
		BATTERY_LITHIUM_SMALL(6, 11), 
		CIRCUIT_DATA(16, 1),
		CIRCUIT_ENERGY(16, 1), 
		COOLANT_HELIUM_LARGE(13, 2), 
		COOLANT_HELIUM_MED(13, 2), 
		COOLANT_HELIUM_SMALL(5, 13),
		DATA_LARGE(12, 4),
		DATA_SMALL(3, 6), 
		ROD_PLUTONIUM_LARGE(7, 16),
		ROD_PLUTONIUM_MED(6, 16), 
		ROD_PLUTONIUM_SMALL(4, 16), 
		ROD_THORIUM_LARGE(7, 16), 
		ROD_THORIUM_MED(6, 16),
		ROD_THORIUM_SMALL(4, 16);

		private int height;
		private int width;

		GTBlockTileCustomVariants(int width, int height) {
			this.width = width;
			this.height = height;
		}

		public float getHeight() {
			return this.height;
			// returns width as 0-16
		}

		public double getHeightBB() {
			return this.height / 16.0D;
			// returns height as 0.0D-1.0D
		}

		public double getOffsetBB() {
			return (1.0D - (this.width / 16.0D)) * .5;
			// returns full block height 1.0 - width (D) as 0.0-1.0D divided to center block
		}

		public float getWidth() {
			return this.width;
			// returns width as 0-16
		}

		public double getWidthBB() {
			return this.width / 16.0D;
			// returns width as 0.0D-1.0D
		}

	}

	GTBlockTileCustomVariants variant;

	public GTBlockTileCustom(GTBlockTileCustomVariants variant) {
		super(Material.CLOTH);
		this.variant = variant;
		setRegistryName(variant.toString().toLowerCase());
		setUnlocalizedName(GTClassic.MODID + "." + variant.toString().toLowerCase());
		setCreativeTab(GTClassic.creativeTabGT);
		setHardness(0.5F);
		setResistance(30.0F);
		setSoundType(SoundType.CLOTH);
	}

	@Override
	@Deprecated
	public boolean canEntitySpawn(IBlockState state, Entity entityIn) {
		return false;
	}

	@Override
	public TileEntityBlock createNewTileEntity(World arg0, int arg1) {
		return new TileEntityBlock();
	}

	@Deprecated
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return getVariantBoundingBox();
	}

	@Override
	public float[] getCustomTextureUV(IBlockState var1, EnumFacing var2) {
		if (var2 == EnumFacing.UP || var2 == EnumFacing.DOWN) {
			return new float[] { 0.0F, 16 - variant.getWidth(), variant.getWidth(), 16 };
		}
		return new float[] { 0.0F, 16 - variant.getHeight(), variant.getWidth(), 16 };

	}

	@Override
	public TextureAtlasSprite[] getIconSheet(int arg0) {
		return Ic2Icons.getTextures(variant.toString().toLowerCase());
	}

	@Override
	public int getMaxSheetSize(int meta) {
		return 1;
	}

	public AxisAlignedBB getRenderBoundingBox(IBlockState state) {
		return getVariantBoundingBox();
	}

	@Override
	public int getTextureRotation(IBlockState var1, EnumFacing var2) {
		return 0;
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
			states.add(def.withProperty(getMetadataProperty(), 0).withProperty(allFacings, side).withProperty(active,
					false));
			states.add(def.withProperty(getMetadataProperty(), 0).withProperty(allFacings, side).withProperty(active,
					true));
		}
		return states;
	}

	@Override
	public List<IBlockState> getValidStates() {
		return getBlockState().getValidStates();
	}

	public AxisAlignedBB getVariantBoundingBox() {
		return new AxisAlignedBB(variant.getOffsetBB(), 0.0D, variant.getOffsetBB(),
				variant.getOffsetBB() + variant.getWidthBB(), variant.getHeightBB(),
				variant.getOffsetBB() + variant.getWidthBB());
	}

	@Override
	public boolean hasCustomTextureUV(IBlockState var1, EnumFacing var2) {
		return true;
	}

	@Override
	public boolean hasFacing() {
		return true;
	}

	@Override
	public boolean hasTextureRotation(IBlockState var1, EnumFacing var2) {
		return false;
	}

	@Deprecated
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Deprecated
	public boolean isNormalCube(IBlockState state) {
		return false;
	}

	@Deprecated
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

}
