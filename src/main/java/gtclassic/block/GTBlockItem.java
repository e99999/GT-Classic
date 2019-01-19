package gtclassic.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTBlocks;
import gtclassic.GTClassic;
import gtclassic.util.GTValues;
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

public class GTBlockItem extends BlockMultiID implements IBlockTextureModifier {

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

		// NAME(TYPE?, AABB, LIGHT LEVEL)
		SMALL_COOLANT(4, 16), MED_COOLANT(16, 4), LARGE_COOLANT(16, 4),

		SMALL_THORIUM(4, 16), MED_THORIUM(6, 16), LARGE_THORIUM(10, 16),

		SMALL_PLUTONIUM(4, 16), MED_PLUTONIUM(6, 16), LARGE_PLUTONIUM(10, 16),

		SMALL_LITHIUM(4, 6), MED_LITHIUM(12, 9), LARGE_LITHIUM(14, 14),

		SMALL_LAPOTRON(6, 6), MED_LAPOTRON(12, 9), LARGE_LAPOTRON(14, 14),

		SMALL_ENERGIUM(6, 6), MED_ENERGIUM(12, 9), LARGE_ENERGIUM(14, 14),

		ALUMINIUM_DATASTICK(3, 6), TITANIUM_DATASTICK(3, 6), CHROME_DATASTICK(3, 6),

		ALUMINIUM_DATADRIVE(14, 16), TITANIUM_DATADRIVE(14, 16), CHROME_DATADRIVE(14, 16),

		ENERGY_CIRCUIT(16, 1), DATA_CIRCUIT(16, 1);

		private int width;
		private int height;

		GTBlockItemVariants(int width, int height) {
			this.width = width;
			this.height = height;
		}

		public double getWidthBB() {
			return this.width / 16.0D;
			// returns width as 0.0D-1.0D
		}

		public double getHeightBB() {
			return this.height / 16.0D;
			// returns height as 0.0D-1.0D
		}

		public double getOffsetBB() {
			return (1.0D - (this.width/ 16.0D)) *.5;
			// returns full block height 1.0 - width (D) as 0.0-1.0D
		}

		public float getWidth() {
			return this.width;
			// returns width as 0-16
		}

		public float getHeight() {
			return this.height;
			// returns width as 0-16
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

	// MY STUFF

	public AxisAlignedBB getVariantBoundingBox() {
		return new AxisAlignedBB(variant.getOffsetBB(), 0.0D, variant.getOffsetBB(), 
				variant.getOffsetBB()+variant.getWidthBB(), variant.getHeightBB(), variant.getOffsetBB()+variant.getWidthBB());
	}
	
	@Override
	@Deprecated
	public boolean canEntitySpawn(IBlockState state, Entity entityIn) {
		return false;
	}
	
	@Override
	public int getMaxSheetSize(int meta) {
		return 1;
	}

	// MUTLIMACHINESTUFF

	@Override
	public TileEntityBlock createNewTileEntity(World arg0, int arg1) {
		return new TileEntityBlock();
	}

	@Override
	public TextureAtlasSprite[] getIconSheet(int arg0) {
		return Ic2Icons.getTextures("gtclassic_builder");
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
			states.add(def.withProperty(getMetadataProperty(), 0).withProperty(allFacings, side).withProperty(active,
					false));
			states.add(def.withProperty(getMetadataProperty(), 0).withProperty(allFacings, side).withProperty(active,
					true));
		}
		return states;
	}

	@Override
	public List<Integer> getValidMetas() {
		return Arrays.asList(0);
	}

	// ADDED OVERRIDES FOR WIERD SHAPE

	@Override
	public boolean hasFacing() {
		// sadly this method screws up weird shaped blocks atm
		return true;
	}

	@Deprecated
	public boolean isNormalCube(IBlockState state) {
		return false;
	}

	@Deprecated
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Deprecated
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Deprecated
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return getVariantBoundingBox();
	}

	public AxisAlignedBB getRenderBoundingBox(IBlockState state) {
		GTClassic.logger.info(variant.toString() + ": " + getVariantBoundingBox());
		return getVariantBoundingBox();
	}

	// TEXTURE INTERFACE

	@Override
	public boolean hasTextureRotation(IBlockState var1, EnumFacing var2) {
		return false;
	}

	@Override
	public int getTextureRotation(IBlockState var1, EnumFacing var2) {
		return 0;
	}

	@Override
	public boolean hasCustomTextureUV(IBlockState var1, EnumFacing var2) {
		return true;
	}

	@Override
	public float[] getCustomTextureUV(IBlockState var1, EnumFacing var2) {
		if (var2 == EnumFacing.UP || var2 == EnumFacing.DOWN){
		return new float[] {0.0F, 0.0F, variant.getWidth(), variant.getWidth() };
		}
		return new float[] {0.0F, 0.0F, variant.getWidth(), variant.getHeight() };
		
	}

}
