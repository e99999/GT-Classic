package gtclassic.common.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import gtclassic.api.material.GTMaterial;
import gtclassic.api.material.GTMaterialFlag;
import gtclassic.api.material.GTMaterialGen;
import net.minecraft.util.ResourceLocation;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenConstructor;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.ArrayList;
import java.util.List;

@ZenRegister
@ZenClass("mods.gtclassic.GTMaterial")
public class GTCraftTweakerMaterial {

    private GTMaterial material;

    @ZenConstructor
    public GTCraftTweakerMaterial(String displayName, int r, int g, int b, String[] flags){
        if (GTMaterial.hasMaterial(displayName)){
            CraftTweakerAPI.logError(CraftTweakerAPI.getScriptFileAndLine() + " > Material " + displayName + " already Exists!");
            return;
        }
        if (flags.length == 0){
            CraftTweakerAPI.logError(CraftTweakerAPI.getScriptFileAndLine() + " > Material " + displayName + " must have at least one flag!");
            return;
        }
        List<GTMaterialFlag> flagList = new ArrayList<>();
        for (String string : flags){
            if (GTMaterialFlag.hasFlag(string)){
                flagList.add(GTMaterialFlag.getFlag(string));
            }
        }
        GTMaterialFlag[] flags1 = flagList.toArray(new GTMaterialFlag[0]);
        this.material = new GTMaterial(displayName, r, g, b, flags1);
    }

    @ZenConstructor
    public GTCraftTweakerMaterial(GTMaterial material){
        this.material = material;
    }

    @ZenMethod
    public GTCraftTweakerMaterial setElement(int element){
        this.material.setElement(element);
        return this;
    }

    @ZenMethod
    public GTCraftTweakerMaterial setTier(int tier){
        this.material.setTier(tier);
        return this;
    }

    @ZenMethod
    public GTCraftTweakerMaterial setSmeltable(boolean smeltable) {
        this.material.setSmeltable(smeltable);
        return this;
    }

    @ZenMethod
    public GTCraftTweakerMaterial addFlags(String[] flags){
        List<GTMaterialFlag> flagList = new ArrayList<>();
        for (String string : flags){
            if (GTMaterialFlag.hasFlag(string)){
                flagList.add(GTMaterialFlag.getFlag(string));
            }
        }
        GTMaterialFlag[] flags1 = flagList.toArray(new GTMaterialFlag[0]);
        this.material.addFlags(flags1);
        return this;
    }

    @ZenMethod
    public static GTCraftTweakerMaterial getMaterial(String name){
        if (GTMaterial.hasMaterial(name)){
            return new GTCraftTweakerMaterial((GTMaterial.get(name)));
        } else {
            CraftTweakerAPI.logError(CraftTweakerAPI.getScriptFileAndLine() + " > Material " + name + " does not Exist!");
            return null;
        }
    }

    @ZenMethod
    public static void createFlag(String suffix, String domain, String path, boolean layered, boolean block){
        String prefix = suffix.replace("_", "");
        if (GTMaterialFlag.hasFlag(prefix)){
            CraftTweakerAPI.logError(CraftTweakerAPI.getScriptFileAndLine() + " > Material Flag " + suffix + " already Exists!");
            return;
        }
        GTCraftTweakerLoader.flagTextureMap.put(prefix, new ResourceLocation(domain, path));
        if (layered){
            GTCraftTweakerLoader.flagTextureMap.put(prefix + 1, new ResourceLocation(domain, path + 1));
        }
        GTMaterialFlag flag = new GTMaterialFlag(suffix, domain + "_" + prefix, 0, layered, domain).setCraftweaker(true);
        if (block) {
            GTMaterialGen.addBlockFlag(flag);
        } else {
            GTMaterialGen.addItemFlag(flag);
        }
    }
}
