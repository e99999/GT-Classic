package gtclassic.common.util.render;

import gtclassic.GTMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BakedQuadRetextured;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.model.TRSRTransformation;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;


public class GTModelUtils {

	private static Function<ResourceLocation, TextureAtlasSprite> TEXTURE_GETTER;

	private static EnumMap<ItemCameraTransforms.TransformType, Matrix4f> TRANSFORM_MAP_ITEM = new EnumMap<>(ItemCameraTransforms.TransformType.class);

	static {
		TRANSFORM_MAP_ITEM.put(ItemCameraTransforms.TransformType.GUI, getTransform(0, 0, 0, 0, 0, 0, 1f).getMatrix());
		TRANSFORM_MAP_ITEM.put(ItemCameraTransforms.TransformType.GROUND, getTransform(0, 2, 0, 0, 0, 0, 0.5f).getMatrix());
		TRANSFORM_MAP_ITEM.put(ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND, getTransform(1.13f, 3.2f, 1.13f, 0, -90, 25, 0.68f).getMatrix());
		TRANSFORM_MAP_ITEM.put(ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, getTransform(0, 3, 1, 0, 0, 0, 0.55f).getMatrix());
		TRANSFORM_MAP_ITEM.put(ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND, getTransform(1.13f, 3.2f, 1.13f, 0, 90, -25, 0.68f).getMatrix());
		TRANSFORM_MAP_ITEM.put(ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, getTransform(0f, 4.0f, 0.5f, 0, 90, -55, 0.85f).getMatrix());
	}

	public static Function<ResourceLocation, TextureAtlasSprite> getTextureGetter() {
		if (TEXTURE_GETTER == null) TEXTURE_GETTER = location -> Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString());
		return TEXTURE_GETTER;
	}

	public static Matrix4f getItemTransform(ItemCameraTransforms.TransformType type) {
		Matrix4f mat = TRANSFORM_MAP_ITEM.get(type);
		return mat != null ? mat : TRSRTransformation.identity().getMatrix();
	}

	public static TRSRTransformation getTransform(float tx, float ty, float tz, float ax, float ay, float az, float s) {
		return new TRSRTransformation(new Vector3f(tx / 16, ty / 16, tz / 16), TRSRTransformation.quatFromXYZDegrees(new Vector3f(ax, ay, az)), new Vector3f(s, s, s), null);
	}

	public static IModel load(String path) {
		return load(new ModelResourceLocation(GTMod.MODID + ":" + path));
	}

	public static IModel load(String domain, String path) {
		return load(new ModelResourceLocation(domain + ":" + path));
	}

	public static IModel load(ModelResourceLocation loc) {
		try {
			return ModelLoaderRegistry.getModel(loc);
		} catch (Exception e) {
			System.err.println("I Fucked up : " + e + ":");
			e.printStackTrace();
			return ModelLoaderRegistry.getMissingModel();
		}
	}

	public static List<BakedQuad> texAndTint(List<BakedQuad> quads, int rgb, TextureAtlasSprite sprite) {
		List<BakedQuad> quadsTemp = new LinkedList<>();
		int size = quads.size();
		for (int i = 0; i < size; i++) {
			BakedQuad quad = new BakedQuadRetextured(quads.get(i), sprite);
			quadsTemp.add(new GTBakedQuadTinted(quad, rgb));
		}
		return quadsTemp;
	}

	public static int rgbToABGR(int rgb) {
		rgb |= 0xFF000000;
		int r = (rgb >> 16) & 0xFF;
		int b = rgb & 0xFF;
		return (rgb & 0xFF00FF00) | (b << 16) | r;
	}
}
