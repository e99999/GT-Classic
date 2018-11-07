package gtclassic.tileentity;

import gtclassic.GTClassic;
import gtclassic.container.GTContainerAlloySmelter;
import gtclassic.util.GTItems;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.tile.MachineType;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.base.tile.TileEntityBasicElectricMachine;
import ic2.core.block.machine.recipes.managers.BasicMachineRecipeList;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.lang.components.base.LangComponentHolder;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GTTileEntityAlloySmelter extends TileEntityBasicElectricMachine {
    public GTTileEntityAlloySmelter() {
        super(3, 2, 400, 32);
    }

    public MachineType getType() {
        return MachineType.macerator;
    }

    public ResourceLocation getGuiTexture() {
        return new ResourceLocation(GTClassic.MODID, "textures/guisprites/guialloysmelter.png");
    }

    public LocaleComp getBlockName() {
        return new LangComponentHolder.LocaleBlockComp("tile.alloySmelter");
    }
    public static IMachineRecipeList alloySmelter = new BasicMachineRecipeList("alloySmelter");

    public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
        return GuiComponentContainer.class;
    }

    public IMachineRecipeList.RecipeEntry getOutputFor(ItemStack input) {
        return alloySmelter.getRecipeInAndOutput(input, false);
    }

    public ResourceLocation getStartSoundFile() {
        return Ic2Sounds.maceratorOp;
    }

    public ResourceLocation getInterruptSoundFile() {
        return Ic2Sounds.interruptingSound;
    }

    public double getWrenchDropRate() {
        return 0.8500000238418579D;
    }

    public boolean isValidInput(ItemStack par1) {
        if (par1 == null) {
            return false;
        } else {
            return alloySmelter.getRecipeInAndOutput(par1, true) != null ? super.isValidInput(par1) : false;
        }
    }

    @Override
    public boolean supportsNotify() {
        return true;
    }

    @Override
    public String getName() {
        return "AlloySmelter";
    }

    @Override
    public boolean hasCustomName() {
        return true;
    }

    @Override
    public IMachineRecipeList getRecipeList() {
        return alloySmelter;
    }

    @Override
    public void update() {

    }

    //not sure if we need this
//    public static void postInit() {
//        Set<String> oreBlacklist = new HashSet();
//        oreBlacklist.addAll(Arrays.asList("oreIron", "oreGold", "oreSilver", "oreCopper", "oreTin", "oreRedstone", "oreUranium"));
//        Set<String> ingotBlackList = new HashSet();
//        ingotBlackList.addAll(Arrays.asList("ingotIron", "ingotGold", "ingotSilver", "ingotCopper", "ingotTin", "ingotBronze"));
//        String[] var2 = OreDictionary.getOreNames();
//        int var3 = var2.length;
//
//        for(int var4 = 0; var4 < var3; ++var4) {
//            String id = var2[var4];
//            String dust;
//            NonNullList list;
//            if (id.startsWith("ore")) {
//                if (!oreBlacklist.contains(id)) {
//                    dust = "dust" + id.substring(3);
//                    if (OreDictionary.doesOreNameExist(dust)) {
//                        list = OreDictionary.getOres(dust, false);
//                        if (!list.isEmpty()) {
//                            addRecipe((String)id, 1, StackUtil.copyWithSize((ItemStack)list.get(0), 2));
//                        }
//                    }
//                }
//            } else if (id.startsWith("ingot") && !ingotBlackList.contains(id)) {
//                dust = "dust" + id.substring(3);
//                if (OreDictionary.doesOreNameExist(dust)) {
//                    list = OreDictionary.getOres(dust, false);
//                    if (!list.isEmpty()) {
//                        addRecipe((String)id, 1, ((ItemStack)list.get(0)).func_77946_l());
//                    }
//                }
//            }
//        }
//
//    }

    public static void init(){

        alloySmelter.addRecipe((IRecipeInput) (new RecipeInputOreDict("ingotCopper", 4)),  new ItemStack(GTItems.ingotBrassGT, 4, 0), 0.7f, "brassIngots");
//        alloySmelter.addRecipe((IRecipeInput) (new RecipeInputOreDict("ingotTin", 1)),  new ItemStack(RegistryItem.itemCasings, 2, 1), 0.7f, "tinItemCasingRolling");
//        alloySmelter.addRecipe((IRecipeInput) (new RecipeInputOreDict("ingotSilver", 1)),  new ItemStack(RegistryItem.itemCasings, 2, 2), 0.7f, "silverItemCasingRolling");
//        alloySmelter.addRecipe((IRecipeInput) (new RecipeInputOreDict("ingotLead", 1)),  new ItemStack(RegistryItem.itemCasings, 2, 3), 0.7f, "leadItemCasingRolling");
//        alloySmelter.addRecipe((IRecipeInput) (new RecipeInputOreDict("ingotIron", 1)),  new ItemStack(RegistryItem.itemCasings, 2, 4), 0.7f, "ironItemCasingRolling");
//        alloySmelter.addRecipe((IRecipeInput) (new RecipeInputOreDict("ingotGold", 1)),  new ItemStack(RegistryItem.itemCasings, 2, 5), 0.7f, "goldItemCasingRolling");
//        alloySmelter.addRecipe((IRecipeInput) (new RecipeInputOreDict("ingotBronze", 1)),  new ItemStack(RegistryItem.itemCasings, 2, 8), 0.7f, "bronzeItemCasingRolling");
    }

    public static void addRecipe(ItemStack input, ItemStack output) {
        addRecipe((IRecipeInput)(new RecipeInputItemStack(input)), output);
    }

    public static void addRecipe(ItemStack input, int stacksize, ItemStack output) {
        addRecipe((IRecipeInput)(new RecipeInputItemStack(input, stacksize)), output);
    }

    public static void addRecipe(String input, int stacksize, ItemStack output) {
        addRecipe((IRecipeInput)(new RecipeInputOreDict(input, stacksize)), output);
    }

    public static void addRecipe(ItemStack input, ItemStack output, float exp) {
        addRecipe((IRecipeInput)(new RecipeInputItemStack(input)), output, exp);
    }

    public static void addRecipe(ItemStack input, int stacksize, ItemStack output, float exp) {
        addRecipe((IRecipeInput)(new RecipeInputItemStack(input, stacksize)), output, exp);
    }

    public static void addRecipe(String input, int stacksize, ItemStack output, float exp) {
        addRecipe((IRecipeInput)(new RecipeInputOreDict(input, stacksize)), output, exp);
    }

    public static void addRecipe(IRecipeInput input, ItemStack output) {
        addRecipe(input, output, 0.0F);
    }

    public static void addRecipe(IRecipeInput input, ItemStack output, float exp) {
        alloySmelter.addRecipe(input, output, exp, makeString(output));
    }

    private static String makeString(ItemStack stack) {
        return stack.getDisplayName();
    }
}
