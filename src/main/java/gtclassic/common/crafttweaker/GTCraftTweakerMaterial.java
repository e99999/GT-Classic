package gtclassic.common.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import gtclassic.api.material.GTMaterial;
import gtclassic.api.material.GTMaterialFlag;
import gtclassic.api.material.GTMaterialGen;
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
    public GTCraftTweakerMaterial(String displayName, int r, int g, int b, String... flags){
        if (GTMaterial.hasMaterial(displayName)){
            CraftTweakerAPI.logError(CraftTweakerAPI.getScriptFileAndLine() + " > Material " + displayName + " already Exists!");
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
    public static void addFlagsToMaterial(String materialName, String... flags){
        List<GTMaterialFlag> flagList = new ArrayList<>();
        for (String string : flags){
            if (GTMaterialFlag.hasFlag(string)){
                flagList.add(GTMaterialFlag.getFlag(string));
            }
        }
        GTMaterialFlag[] flags1 = flagList.toArray(new GTMaterialFlag[0]);
        if (GTMaterial.hasMaterial(materialName)){
            GTMaterial addTo = GTMaterial.get(materialName);
            addTo.addFlags(flags1);
        } else {
            CraftTweakerAPI.logError(CraftTweakerAPI.getScriptFileAndLine() + " > Material " + materialName + " does not Exist!");
        }
    }

    @ZenMethod
    public static void createFlag(String suffix, String domain, String path, boolean layered, boolean block){
        if (GTMaterialFlag.hasFlag(suffix.replace("_", ""))){
            CraftTweakerAPI.logError(CraftTweakerAPI.getScriptFileAndLine() + " > Material Flag " + suffix + " already Exists!");
            return;
        }
        GTMaterialFlag flag = new GTMaterialFlag(suffix,  domain + ":" + path, 0, layered, domain).setCraftweaker(true);
        if (block) {
            GTMaterialGen.addBlockFlag(flag);
        } else {
            GTMaterialGen.addItemFlag(flag);
        }
    }
}
