package gtclassic.api.model;

import java.util.List;

import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.Ic2Models;
import ic2.core.platform.textures.models.BaseModel;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockPartRotation;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.model.PerspectiveMapWrapper;

public class GTModelMortar extends BaseModel {
	
	List<BakedQuad>[] quads;
	IBlockState state;
	
	TextureAtlasSprite columnSprite = Ic2Models.getIconSafe(Ic2Icons.getTextures("b0")[1]);
	TextureAtlasSprite bowlSprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("minecraft:blocks/stone");
	
	//just for testing
	AxisAlignedBB AABB_BOTTOM_HALF = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);

	public GTModelMortar(IBlockState state) {
		super(Ic2Models.getBlockTransforms());
		this.state = state;
	}

	@Override
	public void init() {
		this.quads = this.createList(7);//wut?
		this.setParticalTexture(bowlSprite);
		AxisAlignedBB box = AABB_BOTTOM_HALF;
		this.quads[0].add(this.getBakery().makeBakedQuad(this.getMinBox(side, box), this.getMaxBox(side, box), face, bowlSprite, side, sideRotation, (BlockPartRotation) null, false, true));
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
}
