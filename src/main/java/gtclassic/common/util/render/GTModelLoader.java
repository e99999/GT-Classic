package gtclassic.common.util.render;

import java.util.HashMap;

import gtclassic.GTMod;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;

public class GTModelLoader implements ICustomModelLoader {

	private static HashMap<String, IModel> modelLookup = new HashMap<>();
	@SuppressWarnings("unused")
	private IResourceManager resourceManager;

	public static void register(String registryPath, IModel model) {
		if (!modelLookup.containsKey(registryPath))
			modelLookup.put(registryPath, model);
	}

	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}

	@Override
	public boolean accepts(ResourceLocation loc) {
		return loc.getNamespace().equals(GTMod.MODID) && modelLookup.containsKey(loc.getPath());
	}

	@Override
	public IModel loadModel(ResourceLocation modelLoc) {
		IModel model = modelLookup.get(modelLoc.getPath());
		return model != null ? model : ModelLoaderRegistry.getMissingModel();
	}
}
