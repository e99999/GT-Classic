package gtclassic;

import java.util.ArrayList;
import java.util.List;

import gtclassic.item.GTBatteryBuilder;
import gtclassic.item.GTItemComponents;
import gtclassic.item.GTItemCraftingTablet;
import gtclassic.item.GTItemCreativeScanner;
import gtclassic.item.GTItemDestructoPack;
import gtclassic.item.GTItemDuctTape;
import gtclassic.item.GTItemEchophone;
import gtclassic.item.GTItemElectromagnet;
import gtclassic.item.GTItemSurvivalScanner;
import gtclassic.item.GTItemTeslaStaff;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.tool.GTToolChainsaw;
import gtclassic.tool.GTToolFile;
import gtclassic.tool.GTToolHammer;
import gtclassic.tool.GTToolMiningDrill;
import gtclassic.tool.GTToolRockCutter;
import ic2.core.IC2;
import net.minecraft.item.Item;

public class GTItems {
	
	static List<Item> toRegister = new ArrayList<Item>();
	public static final GTItemComponents glassTube = createItem(new GTItemComponents("glassTube", 0, false));
	public static final GTItemComponents bouleSilicon = createItem(new GTItemComponents("bouleSilicon", 32, false));
	public static final GTItemComponents platePlastic = createItem(new GTItemComponents("platePlastic", 34, false));
	public static final GTItemComponents lensDiamond = createItem(new GTItemComponents("lensDiamond", 35, true));
	public static final GTItemComponents lensRuby = createItem(new GTItemComponents("lensRuby", 36, true));
	public static final GTItemComponents lensEmerald = createItem(new GTItemComponents("lensEmerald", 37, true));
	public static final GTItemComponents lensSapphire = createItem(new GTItemComponents("lensSapphire", 38, true));
	public static final GTItemComponents chipDiamond = createItem(new GTItemComponents("chipDiamond", 39, false));
	public static final GTItemComponents chipRuby = createItem(new GTItemComponents("chipRuby", 40, false));
	public static final GTItemComponents chipEmerald = createItem(new GTItemComponents("chipEmerald", 41, false));
	public static final GTItemComponents chipSapphire = createItem(new GTItemComponents("chipSapphire", 42, false));
	public static final GTItemComponents circuitEmpty = createItem(new GTItemComponents("circuitEmpty", 43, false));
	public static final GTItemComponents circuitDiamond = createItem(new GTItemComponents("circuitDiamond", 44, false));
	public static final GTItemComponents circuitRuby = createItem(new GTItemComponents("circuitRuby", 45, false));
	public static final GTItemComponents circuitEmerald = createItem(new GTItemComponents("circuitEmerald", 46, false));
	public static final GTItemComponents circuitSapphire = createItem(new GTItemComponents("circutSapphire", 47, false));

	public static final GTItemDuctTape braintechAerospaceARDT = createItem(new GTItemDuctTape());
	public static final GTItemEchophone sonictronItem = createItem(new GTItemEchophone());
	public static final GTItemDestructoPack destructoPack = createItem(new GTItemDestructoPack());
	public static final GTItemCraftingTablet craftingTablet = createItem(new GTItemCraftingTablet());

	public static final GTToolFile fileIron = createItem(new GTToolFile(GTMaterial.Iron));
	public static final GTToolFile fileTitanium = createItem(new GTToolFile(GTMaterial.Titanium));
	public static final GTToolFile fileTungstenSteel = createItem(new GTToolFile(GTMaterial.TungstenSteel));

	public static final GTToolHammer hammerIron = createItem(new GTToolHammer(GTMaterial.Iron));
	public static final GTToolHammer hammerTitanium = createItem(new GTToolHammer(GTMaterial.Titanium));
	public static final GTToolHammer hammerTungstenSteel = createItem(new GTToolHammer(GTMaterial.TungstenSteel));

	public static final GTToolMiningDrill advancedDrill = createItem(new GTToolMiningDrill(GTMaterial.Diamond, 100000, 128, 1));
	public static final GTToolMiningDrill advancedDrill2 = createItem(new GTToolMiningDrill(GTMaterial.Diamond, 200000, 256, 2));
	public static final GTToolMiningDrill advancedDrill3 = createItem(new GTToolMiningDrill(GTMaterial.Diamond, 400000, 512, 3));

	public static final GTToolChainsaw advancedChainsaw = createItem(new GTToolChainsaw(GTMaterial.Steel, 100000, 128, 1));
	public static final GTToolChainsaw advancedChainsaw2 = createItem(new GTToolChainsaw(GTMaterial.Titanium, 200000, 256, 2));
	public static final GTToolChainsaw advancedChainsaw3 = createItem(new GTToolChainsaw(GTMaterial.TungstenSteel, 400000, 512, 3));

	public static final GTToolRockCutter rockCutter = createItem(new GTToolRockCutter(GTMaterial.Diamond, 10000, 100, 1));
	public static final GTToolRockCutter rockCutter2 = createItem(new GTToolRockCutter(GTMaterial.Diamond, 100000, 256, 2));
	public static final GTToolRockCutter rockCutter3 = createItem(new GTToolRockCutter(GTMaterial.Diamond, 1000000, 1024, 3));

	public static final GTItemElectromagnet electroMagnet = createItem(new GTItemElectromagnet());
	public static final GTItemTeslaStaff teslaStaff = createItem(new GTItemTeslaStaff());

	public static final GTItemCreativeScanner debugScanner = createItem(new GTItemCreativeScanner());
	public static final GTItemSurvivalScanner portableScanner = createItem(new GTItemSurvivalScanner());
	
	public static <T extends Item> T createItem(T item)
	{
		toRegister.add(item);
		return item;
	}
	
	public static void registerItems()
	{
		for(Item item : toRegister)
		{
			IC2.getInstance().createItem(item);
		}
		for (Item item : GTMaterialGen.itemMap.values()) 
		{
			IC2.getInstance().createItem(item);
		}
	}

}
