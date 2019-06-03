package gtclassic.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTBlocks;
import gtclassic.GTMod;
import gtclassic.tile.GTTileSolarPanel;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IBlockTextureModifier;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class GTBlockTileCustom extends GTBlockMultiID implements IBlockTextureModifier {

	String name;
	int height;
	int width;
	boolean light;

	public GTBlockTileCustom(String name, int width, int height, boolean light) {
		super(Material.CLOTH);
		this.name = name;
		this.height = height;
		this.width = width;
		this.light = light;
		setRegistryName(this.name.toLowerCase());
		setUnlocalizedName(GTMod.MODID + "." + this.name.toLowerCase());
		setCreativeTab(GTMod.creativeTabGT);
		setHardness(0.5F);
		setResistance(30.0F);
		setSoundType(SoundType.CLOTH);
		setLightLevel(getVariantLightLevel());
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
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

	public float getVariantLightLevel() {
		if (this.light) {
			return (float) (this.getWidthBB());
		}
		return 0;
	}

	@Override
	@Deprecated
	public boolean canEntitySpawn(IBlockState state, Entity entityIn) {
		return false;
	}

	@Override
	public TileEntityBlock createNewTileEntity(World arg0, int arg1) {
		if (this == GTBlocks.solarPanel) {
			return new GTTileSolarPanel();
		}
		return new TileEntityBlock();
	}

	@Override
	@Deprecated
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return getVariantBoundingBox();
	}

	@Override
	public float[] getCustomTextureUV(IBlockState var1, EnumFacing var2) {
		if (var2 == EnumFacing.UP || var2 == EnumFacing.DOWN) {
			return new float[] { 0.0F, 16 - this.getWidth(), this.getWidth(), 16 };
		}
		return new float[] { 0.0F, 16 - this.getHeight(), this.getWidth(), 16 };
	}

	@Override
	public TextureAtlasSprite[] getIconSheet(int arg0) {
		return Ic2Icons.getTextures(this.name);
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
			states.add(def.withProperty(allFacings, side).withProperty(active, false));
			states.add(def.withProperty(allFacings, side).withProperty(active, true));
		}
		return states;
	}

	@Override
	public List<IBlockState> getValidStates() {
		return getBlockState().getValidStates();
	}

	public AxisAlignedBB getVariantBoundingBox() {
		return new AxisAlignedBB(this.getOffsetBB(), 0.0D, this.getOffsetBB(), this.getOffsetBB()
				+ this.getWidthBB(), this.getHeightBB(), this.getOffsetBB() + this.getWidthBB());
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
