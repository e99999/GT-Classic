package gtclassic.api.model;

import java.util.List;

import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import gtclassic.api.helpers.GTHelperMath;
import gtclassic.common.GTBlocks;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.Ic2Models;
import ic2.core.platform.textures.models.BaseModel;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockFaceUV;
import net.minecraft.client.renderer.block.model.BlockPartFace;
import net.minecraft.client.renderer.block.model.BlockPartRotation;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ModelRotation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.model.PerspectiveMapWrapper;

public class GTModelMortar extends BaseModel {

	List<BakedQuad>[] quads;
	IBlockState state;
	TextureAtlasSprite bowlSprite;
	TextureAtlasSprite postSprite;
	// just for testing
	static final AxisAlignedBB AABB_BOTTOMLAYER = GTHelperMath.createAABBFromPixelsCentered(12, 1);
	static final AxisAlignedBB AABB_CENTERPOST = GTHelperMath.createAABBFromPixelsCentered(4, 9, 1);
	static final AxisAlignedBB AABB_WALL_EAST = GTHelperMath.createAABBFromPixels(12, 1, 4, 14, 6, 14);
	static final AxisAlignedBB AABB_WALL_SOUTH = GTHelperMath.createAABBFromPixels(2, 1, 12, 12, 6, 14);
	static final AxisAlignedBB AABB_WALL_NORTH = GTHelperMath.createAABBFromPixels(4, 1, 2, 14, 6, 4);
	static final AxisAlignedBB AABB_WALL_WEST = GTHelperMath.createAABBFromPixels(2, 1, 2, 4, 6, 12);

	public GTModelMortar(IBlockState state) {
		super(Ic2Models.getBlockTransforms());
		this.state = state;
		this.bowlSprite = state.getBlock() == GTBlocks.ironMortar
				? Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("minecraft:blocks/stone_slab_top")
				: Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("minecraft:blocks/planks_oak");
		this.postSprite = state.getBlock() == GTBlocks.ironMortar
				? Ic2Models.getIconSafe(Ic2Icons.getTextures("b0")[17])
				: Ic2Icons.getTextures("b0")[35];
	}

	@Override
	public void init() {
		this.quads = this.createList(7);// wut?
		this.setParticalTexture(bowlSprite);
		EnumFacing side;
		ModelRotation sideRotation;
		EnumFacing[] facings;
		int facingLength;
		int j;
		BlockPartFace face;
		facings = EnumFacing.VALUES;
		facingLength = facings.length;
		for (j = 0; j < facingLength; ++j) {
			side = facings[j];
			sideRotation = ModelRotation.X0_Y0;
			face = this.createBottomLayerFace(side);
			this.quads[side.getIndex()].add(this.getBakery().makeBakedQuad(this.getMinBox(side, AABB_BOTTOMLAYER), this.getMaxBox(side, AABB_BOTTOMLAYER), face, bowlSprite, side, sideRotation, (BlockPartRotation) null, false, true));
			face = this.createPostFace(side);
			this.quads[side.getIndex()].add(this.getBakery().makeBakedQuad(this.getMinBox(side, AABB_CENTERPOST), this.getMaxBox(side, AABB_CENTERPOST), face, postSprite, side, sideRotation, (BlockPartRotation) null, false, true));
			face = this.createWallFaceEW(side);
			this.quads[side.getIndex()].add(this.getBakery().makeBakedQuad(this.getMinBox(side, AABB_WALL_EAST), this.getMaxBox(side, AABB_WALL_EAST), face, bowlSprite, side, sideRotation, (BlockPartRotation) null, false, true));
			this.quads[side.getIndex()].add(this.getBakery().makeBakedQuad(this.getMinBox(side, AABB_WALL_WEST), this.getMaxBox(side, AABB_WALL_WEST), face, bowlSprite, side, sideRotation, (BlockPartRotation) null, false, true));
			face = this.createWallFaceNS(side);
			this.quads[side.getIndex()].add(this.getBakery().makeBakedQuad(this.getMinBox(side, AABB_WALL_SOUTH), this.getMaxBox(side, AABB_WALL_SOUTH), face, bowlSprite, side, sideRotation, (BlockPartRotation) null, false, true));
			this.quads[side.getIndex()].add(this.getBakery().makeBakedQuad(this.getMinBox(side, AABB_WALL_NORTH), this.getMaxBox(side, AABB_WALL_NORTH), face, bowlSprite, side, sideRotation, (BlockPartRotation) null, false, true));
		}
	}

	@Override
	public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
		return this.quads[side == null ? 6 : side.getIndex()];
	}

	@Override
	public boolean isAmbientOcclusion() {
		return true;
	}

	@Override
	public boolean isGui3d() {
		return true;
	}

	@Override
	public boolean isBuiltInRenderer() {
		return false;
	}

	@Override
	public Pair<? extends IBakedModel, Matrix4f> handlePerspective(TransformType cameraTransformType) {
		return PerspectiveMapWrapper.handlePerspective(this, this.getCamera(), cameraTransformType);
	}

	protected BlockPartFace createBlockFace(EnumFacing side) {
		return new BlockPartFace((EnumFacing) null, -1, "", new BlockFaceUV(new float[] { 0.0F, 0.0F, 16.0F,
				16.0F }, 0));
	}

	protected BlockPartFace createBottomLayerFace(EnumFacing side) {
		if (side == EnumFacing.UP || side == EnumFacing.DOWN) {
			return new BlockPartFace((EnumFacing) null, -1, "", new BlockFaceUV(new float[] { 1.0F, 1.0F, 13.0F,
					13.0F }, 0));
		}
		return new BlockPartFace((EnumFacing) null, -1, "", new BlockFaceUV(new float[] { 0.0F, 0.0F, 12.0F,
				1.0F }, 0));
	}

	protected BlockPartFace createPostFace(EnumFacing side) {
		if (side == EnumFacing.UP || side == EnumFacing.DOWN) {
			return new BlockPartFace((EnumFacing) null, -1, "", new BlockFaceUV(new float[] { 1.0F, 1.0F, 5.0F,
					5.0F }, 0));
		}
		return new BlockPartFace((EnumFacing) null, -1, "", new BlockFaceUV(new float[] { 1.0F, 0.0F, 5.0F,
				11.0F }, 0));
	}

	protected BlockPartFace createWallFaceEW(EnumFacing side) {
		if (side == EnumFacing.UP || side == EnumFacing.DOWN) {
			return new BlockPartFace((EnumFacing) null, -1, "", new BlockFaceUV(new float[] { 0.0F, 0.0F, 2.0F,
					12.0F }, 0));
		}
		if (side == EnumFacing.NORTH || side == EnumFacing.SOUTH) {
			return new BlockPartFace((EnumFacing) null, -1, "", new BlockFaceUV(new float[] { 0.0F, 0.0F, 2.0F,
					5.0F }, 0));
		}
		return new BlockPartFace((EnumFacing) null, -1, "", new BlockFaceUV(new float[] { 0.0F, 0.0F, 10.0F,
				5.0F }, 0));
	}

	protected BlockPartFace createWallFaceNS(EnumFacing side) {
		if (side == EnumFacing.UP || side == EnumFacing.DOWN) {
			return new BlockPartFace((EnumFacing) null, -1, "", new BlockFaceUV(new float[] { 0.0F, 0.0F, 12.0F,
					2.0F }, 0));
		}
		if (side == EnumFacing.NORTH || side == EnumFacing.SOUTH) {
			return new BlockPartFace((EnumFacing) null, -1, "", new BlockFaceUV(new float[] { 0.0F, 0.0F, 10.0F,
					5.0F }, 0));
		}
		return new BlockPartFace((EnumFacing) null, -1, "", new BlockFaceUV(new float[] { 0.0F, 0.0F, 2.0F, 5.0F }, 0));
	}
}
