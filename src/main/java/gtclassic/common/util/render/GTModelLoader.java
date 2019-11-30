package gtclassic.common.util.render;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;

import java.util.HashMap;

import gtclassic.GTMod;


public class GTModelLoader implements ICustomModelLoader {

    private static HashMap<String, IModel> modelLookup = new HashMap<>();

    private IResourceManager resourceManager;

    public static void register(String registryPath, IModel model) {
        if (!modelLookup.containsKey(registryPath)) modelLookup.put(registryPath, model);
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    @Override
    public boolean accepts(ResourceLocation loc) {
        return loc.getResourceDomain().equals(GTMod.MODID) && modelLookup.containsKey(loc.getResourcePath());
    }

    @Override
    public IModel loadModel(ResourceLocation modelLoc) {
        IModel model = modelLookup.get(modelLoc.getResourcePath());
        return model != null ? model : ModelLoaderRegistry.getMissingModel();
    }
}
