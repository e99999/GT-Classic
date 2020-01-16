package gtclassic.api.model;

import java.util.List;

import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import gtclassic.api.helpers.GTHelperMath;
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
	TextureAtlasSprite bowlSprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("minecraft:blocks/stone");
	TextureAtlasSprite postSprite = Ic2Models.getIconSafe(Ic2Icons.getTextures("b0")[1]);
	// just for testing
	static final AxisAlignedBB AABB_BOTTOMSLAB= GTHelperMath.createAABBFromPixels(16.0D, 4.0D);
	static final AxisAlignedBB AABB_CENTERPOST = GTHelperMath.createAABBFromPixels(6.0D, 12.0D);

	public GTModelMortar(IBlockState state) {
		super(Ic2Models.getBlockTransforms());
		this.state = state;
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
			face = this.createBlockFace(side);
			//Bottom/main peice
			this.quads[side.getIndex()].add(this.getBakery().makeBakedQuad(this.getMinBox(side, AABB_BOTTOMSLAB), this.getMaxBox(side, AABB_BOTTOMSLAB), face, bowlSprite, side, sideRotation, (BlockPartRotation) null, false, true));
			//The shaft... Kreygasm
			this.quads[side.getIndex()].add(this.getBakery().makeBakedQuad(this.getMinBox(side, AABB_CENTERPOST), this.getMaxBox(side, AABB_CENTERPOST), face, postSprite, side, sideRotation, (BlockPartRotation) null, false, true));
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
		return new BlockPartFace((EnumFacing) null, -1, "", new BlockFaceUV(new float[] { 0.0F, 0.0F, 16.0F, 16.0F }, 0));
	}
}
