package gtclassic.common.util.modeltest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.util.vector.Vector3f;

import gtclassic.api.block.GTBlockBaseConnect;
import ic2.core.RotationList;
import ic2.core.platform.textures.Ic2Models;
import ic2.core.platform.textures.models.BaseModel;
import ic2.core.util.helpers.BlockStateContainerIC2.IC2BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockFaceUV;
import net.minecraft.client.renderer.block.model.BlockPartFace;
import net.minecraft.client.renderer.block.model.BlockPartRotation;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ModelRotation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.client.model.PerspectiveMapWrapper;

public class GTModelTest extends BaseModel {

	List<BakedQuad>[] quads = this.createList(64);
	List<BakedQuad>[] anchorQuads = this.createList(64);
	@SuppressWarnings({ "unchecked", "rawtypes" })
	Map<Integer, List<BakedQuad>> comboQuads = new HashMap();
	IBlockState state;
	TextureAtlasSprite sprite;
	int[] sizes;

	public GTModelTest(IBlockState block, TextureAtlasSprite texture, int[] sizes) {
		super(Ic2Models.getBlockTransforms());
		this.state = block;
		this.sprite = texture;
		this.sizes = sizes;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void init() {
		GTBlockBaseConnect wire = (GTBlockBaseConnect) this.state.getBlock();
		this.setParticalTexture(wire.getParticleTexture(this.state));
		int min = this.sizes[0];
		int max = this.sizes[1];
		Map<EnumFacing, BakedQuad> coreQuads = this.generateCoreQuads(wire, min, max);
		Map<EnumFacing, List<BakedQuad>> sideQuads = new EnumMap(EnumFacing.class);
		Map<EnumFacing, List<BakedQuad>> anchorQuadList = new EnumMap(EnumFacing.class);
		EnumFacing[] var7 = EnumFacing.VALUES;
		int var8 = var7.length;
		for (int var9 = 0; var9 < var8; ++var9) {
			EnumFacing side = var7[var9];
			sideQuads.put(side, this.generateQuadsForSide(wire, side, min, max));
			anchorQuadList.put(side, this.generateQuadsForAnchor(this.sprite, side, min, max));
		}
		for (int i = 0; i < 64; ++i) {
			RotationList rotation = RotationList.ofNumber(i);
			List<BakedQuad> quadList = this.quads[i];
			Iterator var15 = rotation.iterator();
			EnumFacing side;
			while (var15.hasNext()) {
				side = (EnumFacing) var15.next();
				quadList.addAll((Collection) sideQuads.get(side));
				this.anchorQuads[i].addAll((Collection) anchorQuadList.get(side));
			}
			var15 = rotation.invert().iterator();
			while (var15.hasNext()) {
				side = (EnumFacing) var15.next();
				quadList.add(coreQuads.get(side));
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
		if (side == null) {
			if (!(state instanceof IC2BlockState)) {
				// if its in jei/creative tab
				return this.quads[12];
			} else {
				Vec3i vec = (Vec3i) ((IC2BlockState) state).getData();
				if (vec.getY() > 0) {
					List<BakedQuad> list = (List) this.comboQuads.get(vec.getZ());
					if (list == null) {
						list = new ArrayList(this.quads[vec.getX()]);
						((List) list).addAll(this.anchorQuads[vec.getY()]);
						this.comboQuads.put(vec.getZ(), list);
					}
					return (List) list;
				} else {
					return this.quads[vec.getX()];
				}
			}
		} else {
			return this.getEmptyList();
		}
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

	public Pair<? extends IBakedModel, Matrix4f> handlePerspective(TransformType cameraTransformType) {
		return PerspectiveMapWrapper.handlePerspective(this, this.getCamera(), cameraTransformType);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map<EnumFacing, BakedQuad> generateCoreQuads(GTBlockBaseConnect wire, int min, int max) {
		Vector3f minF = new Vector3f((float) min, (float) min, (float) min);
		Vector3f maxF = new Vector3f((float) max, (float) max, (float) max);
		BlockPartFace face = new BlockPartFace((EnumFacing) null, 0, "", new BlockFaceUV(new float[] { (float) min,
				(float) min, (float) max, (float) max }, 0));
		Map<EnumFacing, BakedQuad> quads = new EnumMap(EnumFacing.class);
		EnumFacing[] var8 = EnumFacing.VALUES;
		int var9 = var8.length;
		for (int var10 = 0; var10 < var9; ++var10) {
			EnumFacing side = var8[var10];
			quads.put(side, this.getBakery().makeBakedQuad(minF, maxF, face, this.getParticleTexture(), side, ModelRotation.X0_Y0, (BlockPartRotation) null, true, true));
		}
		return quads;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<BakedQuad> generateQuadsForAnchor(TextureAtlasSprite sprite, EnumFacing facing, int min, int max) {
		List<BakedQuad> quads = new ArrayList();
		Pair<Vector3f, Vector3f> position = this.getPosForSide(facing, min, max);
		EnumFacing[] var7 = EnumFacing.VALUES;
		int var8 = var7.length;
		for (int var9 = 0; var9 < var8; ++var9) {
			EnumFacing side = var7[var9];
			if (side.getOpposite() != facing) {
				BlockPartFace face = null;
				if (side == facing) {
					face = new BlockPartFace((EnumFacing) null, 0, "", new BlockFaceUV(new float[] { (float) min,
							(float) min, (float) max, (float) max }, 0));
				} else if (facing.getAxis() == Axis.Z && side.getAxis() == Axis.X) {
					face = new BlockPartFace((EnumFacing) null, 0, "", new BlockFaceUV(new float[] { (float) max,
							(float) min, 16.0F, (float) max }, 0));
				} else {
					face = this.getFace(facing, min, max);
				}
				quads.add(this.getBakery().makeBakedQuad((Vector3f) position.getKey(), (Vector3f) position.getValue(), face, sprite, side, ModelRotation.X0_Y0, (BlockPartRotation) null, true, true));
			}
		}
		return quads;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<BakedQuad> generateQuadsForSide(GTBlockBaseConnect wire, EnumFacing facing, int min, int max) {
		List<BakedQuad> quads = new ArrayList();
		Pair<Vector3f, Vector3f> position = this.getPosForSide(facing, min, max);
		EnumFacing[] var7 = EnumFacing.VALUES;
		int var8 = var7.length;
		for (int var9 = 0; var9 < var8; ++var9) {
			EnumFacing side = var7[var9];
			if (side.getOpposite() != facing) {
				BlockPartFace face = null;
				if (side == facing) {
					face = new BlockPartFace((EnumFacing) null, 0, "", new BlockFaceUV(new float[] { (float) min,
							(float) min, (float) max, (float) max }, 0));
				} else if (facing.getAxis() == Axis.Z && side.getAxis() == Axis.X) {
					face = new BlockPartFace((EnumFacing) null, 0, "", new BlockFaceUV(new float[] { (float) max,
							(float) min, 16.0F, (float) max }, 0));
				} else {
					face = this.getFace(facing, min, max);
				}
				quads.add(this.getBakery().makeBakedQuad((Vector3f) position.getKey(), (Vector3f) position.getValue(), face, wire.getTextureFromState(this.state, side), side, ModelRotation.X0_Y0, (BlockPartRotation) null, true, true));
			}
		}
		return quads;
	}

	private Pair<Vector3f, Vector3f> getPosForSide(EnumFacing facing, int min, int max) {
		switch (facing) {
		case DOWN:
			return Pair.of(new Vector3f(min, 0.0F, min), new Vector3f(max, min, max));
		case UP:
			return Pair.of(new Vector3f(min, max, min), new Vector3f(max, 16.0F, max));
		case NORTH:
			return Pair.of(new Vector3f(min, min, 0.0F), new Vector3f(max, max, min));
		case SOUTH:
			return Pair.of(new Vector3f(min, min, max), new Vector3f(max, max, 16.0F));
		case WEST:
			return Pair.of(new Vector3f(0.0F, min, min), new Vector3f(min, max, max));
		case EAST:
			return Pair.of(new Vector3f(max, min, min), new Vector3f(16.0F, max, max));
		}
		return Pair.of(new Vector3f(min, min, min), new Vector3f(max, max, max));
	}

	private BlockPartFace getFace(EnumFacing facing, int min, int max) {
		switch (facing) {
		case DOWN:
			return new BlockPartFace(null, 0, "", new BlockFaceUV(new float[] { min, max, max, 16.0F }, 0));
		case UP:
			return new BlockPartFace(null, 0, "", new BlockFaceUV(new float[] { min, 0.0F, max, min }, 0));
		case NORTH:
			return new BlockPartFace(null, 0, "", new BlockFaceUV(new float[] { min, 0.0F, max, min }, 0));
		case SOUTH:
			return new BlockPartFace(null, 0, "", new BlockFaceUV(new float[] { min, max, max, 16.0F }, 0));
		case WEST:
			return new BlockPartFace(null, 0, "", new BlockFaceUV(new float[] { 0.0F, min, min, max }, 0));
		case EAST:
			return new BlockPartFace(null, 0, "", new BlockFaceUV(new float[] { max, min, 16.0F, max }, 0));
		}
		return new BlockPartFace(null, 0, "", new BlockFaceUV(new float[] { 0.0F, 0.0F, 16.0F, 16.0F }, 0));
	}
}
