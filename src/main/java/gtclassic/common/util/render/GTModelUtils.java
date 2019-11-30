package gtclassic.common.util.render;

import java.util.LinkedList;
import java.util.List;

import gtclassic.GTMod;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BakedQuadRetextured;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;


public class GTModelUtils {

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
	
	 public static List<BakedQuad> texAndTint(List<BakedQuad> quads, int rgb, Texture texture) {
	        return texAndTint(quads, rgb, texture.getSprite());
	    }

	    public static List<BakedQuad> texAndTint(List<BakedQuad> quads, int rgb, TextureAtlasSprite sprite) {
	        List<BakedQuad> quadsTemp = new LinkedList<>();
	        int size = quads.size();
	        for (int i = 0; i < size; i++) {
	            BakedQuad quad = new BakedQuadRetextured(quads.get(i), sprite);
	            quadsTemp.add(new BakedQuadTinted(quad, rgb));
	        }
	        return quadsTemp;
	    }
}
