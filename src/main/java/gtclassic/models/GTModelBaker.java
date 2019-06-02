package gtclassic.models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.vecmath.Vector4f;

import org.lwjgl.util.vector.Vector3f;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockFaceUV;
import net.minecraft.client.renderer.block.model.BlockPartFace;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.block.model.ModelRotation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.pipeline.IVertexConsumer;
import net.minecraftforge.client.model.pipeline.UnpackedBakedQuad;
import net.minecraftforge.client.model.pipeline.VertexTransformer;
import net.minecraftforge.common.model.TRSRTransformation;

public class GTModelBaker {
	private static final FaceBakery bakery = new FaceBakery();
	private Map<EnumFacing, List<BakedQuad>> quadCache;
	private TextureAtlasSprite sprite;
	private List<Model> models;

	public static GTModelBaker getBaker(TextureAtlasSprite sprite) {
		return new GTModelBaker(sprite);
	}

	private GTModelBaker(TextureAtlasSprite sprite) {
		quadCache = new LinkedHashMap<>();
		quadCache.put(EnumFacing.NORTH, new ArrayList<>());

		this.sprite = sprite;
		models = new ArrayList<>();
	}

	public void addModel(Model model) {
		models.add(model);
	}

	public void addModel(int textureOffsetX, int textureOffsetY, float shapeX, float shapeY, float shapeZ, int sizeX,
			int sizeY, int sizeZ) {
		addModel(new Model(textureOffsetX, textureOffsetY, shapeX, shapeY, shapeZ, sizeX, sizeY, sizeZ));
	}

	public void addModel(int textureOffsetX, int textureOffsetY, float shapeX, float shapeY, float shapeZ, int sizeX,
			int sizeY, int sizeZ, boolean rotateTopAndBottomTexture) {
		addModel(new Model(textureOffsetX, textureOffsetY, shapeX, shapeY, shapeZ, sizeX, sizeY, sizeZ, rotateTopAndBottomTexture));
	}

	public void bake() {
		for (Model model : models) {
			BlockPartFace[] faces = model.buildFaces();
			for (EnumFacing facing : EnumFacing.VALUES) {
				quadCache.get(EnumFacing.NORTH).add(bakery.makeBakedQuad(model.shapeStart, model.shapeEnd, faces[facing.getIndex()], sprite, facing, ModelRotation.X0_Y0, null, true, true));
			}
		}
	}

	public List<BakedQuad> getQuads(EnumFacing rotation) {
		if (!quadCache.containsKey(rotation)) {
			quadCache.put(rotation, GTModelBaker.transform(quadCache.get(EnumFacing.NORTH), rotation));
		}
		return quadCache.get(rotation);
	}

	public static List<BakedQuad> transform(List<BakedQuad> quads, final EnumFacing rotation) {
		List<BakedQuad> result = new ArrayList<>();

		for (BakedQuad quad : quads) {
			UnpackedBakedQuad.Builder builder = new UnpackedBakedQuad.Builder(DefaultVertexFormats.ITEM);
			final IVertexConsumer consumer = new VertexTransformer(builder) {
				@Override
				public void put(int element, float... data) {
					VertexFormatElement formatElement = DefaultVertexFormats.ITEM.getElement(element);
					switch (formatElement.getUsage()) {
					case POSITION: {
						float[] newData = new float[4];
						Vector4f vec = new Vector4f(data);
						TRSRTransformation.from(rotation).getMatrix().transform(vec);
						switch (rotation) {
						case UP:
							vec.add(new Vector4f(0, 1, 0, 0));
							break;
						case DOWN:
							vec.add(new Vector4f(0, 0, 1, 0));
							break;
						case NORTH:
							vec.add(new Vector4f(0, 0, 0, 0));
							break;
						case EAST:
							vec.add(new Vector4f(1, 0, 0, 0));
							break;
						case SOUTH:
							vec.add(new Vector4f(1, 0, 1, 0));
							break;
						case WEST:
							vec.add(new Vector4f(0, 0, 1, 0));
							break;
						}
						vec.get(newData);
						parent.put(element, newData);
						break;
					}
					default: {
						parent.put(element, data);
						break;
					}
					}
				}
			};

			quad.pipe(consumer);
			result.add(builder.build());
		}
		return result;
	}

	public static class Model {
		final float textureOffsetX;
		final float textureOffsetY;
		final Vector3f shapeStart = new Vector3f();
		final Vector3f shapeEnd = new Vector3f();
		final Vector3f shapeSize = new Vector3f();
		final boolean rotateTopAndBottomTexture;

		public Model(int textureOffsetX, int textureOffsetY, float shapeX, float shapeY, float shapeZ, int sizeX,
				int sizeY, int sizeZ) {
			this(textureOffsetX, textureOffsetY, shapeX, shapeY, shapeZ, sizeX, sizeY, sizeZ, false);
		}

		public Model(int textureOffsetX, int textureOffsetY, float shapeX, float shapeY, float shapeZ, int sizeX,
				int sizeY, int sizeZ, boolean rotateTopAndBottomTexture) {
			this.textureOffsetX = textureOffsetX / 4.0f;
			this.textureOffsetY = textureOffsetY / 4.0f;
			this.rotateTopAndBottomTexture = rotateTopAndBottomTexture;

			shapeStart.set(shapeX, shapeY, shapeZ);
			shapeSize.set(sizeX / 4.0f, sizeY / 4.0f, sizeZ / 4.0f);
			shapeEnd.set(shapeX + sizeX, shapeY + sizeY, shapeZ + sizeZ);
		}

		private BlockFaceUV[] getTextureAreas() {
			BlockFaceUV[] areas = new BlockFaceUV[6];
			float[] left = new float[] { textureOffsetX, textureOffsetY + shapeSize.getZ(),
					textureOffsetX + shapeSize.getZ(), textureOffsetY + shapeSize.getZ() + shapeSize.getY() };
			float[] front = new float[] { left[2], left[1], left[2] + shapeSize.getX(), left[3] };
			float[] right = new float[] { front[2], front[1], front[2] + shapeSize.getZ(), front[3] };
			float[] back = new float[] { right[2], right[1], right[2] + shapeSize.getX(), right[3] };
			float[] top = new float[] { textureOffsetX + shapeSize.getZ(), textureOffsetY,
					textureOffsetX + shapeSize.getZ() + shapeSize.getX(), textureOffsetY + shapeSize.getZ() };
			float[] bottom = new float[] { top[2], top[1], top[2] + shapeSize.getX(), top[3] };

			areas[EnumFacing.EAST.getIndex()] = new BlockFaceUV(left, 0);
			areas[EnumFacing.NORTH.getIndex()] = new BlockFaceUV(front, 0);
			areas[EnumFacing.WEST.getIndex()] = new BlockFaceUV(right, 0);
			areas[EnumFacing.SOUTH.getIndex()] = new BlockFaceUV(back, 0);
			areas[EnumFacing.UP.getIndex()] = new BlockFaceUV(top, rotateTopAndBottomTexture ? 180 : 0);
			areas[EnumFacing.DOWN.getIndex()] = new BlockFaceUV(bottom, rotateTopAndBottomTexture ? 180 : 0);
			return areas;
		}

		BlockPartFace[] buildFaces() {
			BlockPartFace[] faces = new BlockPartFace[6];
			BlockFaceUV[] areas = getTextureAreas();

			for (EnumFacing facing : EnumFacing.VALUES) {
				faces[facing.getIndex()] = new BlockPartFace(null, -1, "", areas[facing.getIndex()]);
			}
			return faces;
		}
	}
}
