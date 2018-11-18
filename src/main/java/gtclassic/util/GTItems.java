package gtclassic.util;

import gtclassic.GTClassic;
import gtclassic.items.*;
import gtclassic.items.GTItemComponents.GTItemComponentTypes;
import gtclassic.items.armor.GTItemEnergyPack;
import gtclassic.items.eustorage.GTItemLapotronicEnergyOrb;
import gtclassic.items.eustorage.GTItemLithiumBattery;
import gtclassic.items.resources.GTItemCell;
import gtclassic.items.resources.GTItemCell.GTItemCellTypes;
import gtclassic.items.resources.GTItemDust;
import gtclassic.items.resources.GTItemDust.GTItemDustTypes;
import gtclassic.items.resources.GTItemGem;
import gtclassic.items.resources.GTItemGem.GTItemGemTypes;
import gtclassic.items.resources.GTItemIngot;
import gtclassic.items.resources.GTItemIngot.GTItemIngotTypes;
import gtclassic.items.tools.GTItemAdvancedChainsaw;
import gtclassic.items.tools.GTItemAdvancedDrill;
import gtclassic.items.tools.GTItemHammerIron;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class GTItems {


	public static final GTItemCreditAlk creditAlk = new GTItemCreditAlk();
	public static final GTItemHammerIron hammerIron = new GTItemHammerIron();
	public static final GTItemAdvancedDrill advancedDrill = new GTItemAdvancedDrill();
	public static final GTItemAdvancedChainsaw advancedChainsaw = new GTItemAdvancedChainsaw();
	public static final GTItemLithiumBattery lithiumBattery = new GTItemLithiumBattery();
	public static final GTItemLapotronicEnergyOrb lapotronicEnergyOrb = new GTItemLapotronicEnergyOrb();
	public static final GTItemEnergyPack lithiumBatpack = new GTItemEnergyPack(58, "gtclassic:textures/models/armor/lithiumbatpack", 600000, "lithium_batpack", ".lithiumBatpack", 1, 200);
	public static final GTItemEnergyPack lapotronPack = new GTItemEnergyPack(45, "gtclassic:textures/models/armor/lapotronpack", 100000000, "lapotron_pack", ".lapotronPack", 3, 1500);
	
	public static final GTItemCell
	cellH = new GTItemCell(GTItemCellTypes.H),
	cellD = new GTItemCell(GTItemCellTypes.D),
	cellT = new GTItemCell(GTItemCellTypes.T),
	cellHE = new GTItemCell(GTItemCellTypes.HE),
	cellW = new GTItemCell(GTItemCellTypes.W),
	cellLI = new GTItemCell(GTItemCellTypes.LI),
	cellHE3 = new GTItemCell(GTItemCellTypes.HE3),
	cellSI = new GTItemCell(GTItemCellTypes.SI),
	cellC = new GTItemCell(GTItemCellTypes.C),
	cellME = new GTItemCell(GTItemCellTypes.ME),
	cellBE = new GTItemCell(GTItemCellTypes.BE),
	cellCA = new GTItemCell(GTItemCellTypes.CA),
	cellNA = new GTItemCell(GTItemCellTypes.NA),
	cellCL = new GTItemCell(GTItemCellTypes.CL),
	cellK = new GTItemCell(GTItemCellTypes.K),
	cellN = new GTItemCell(GTItemCellTypes.N);
	
	public static final GTItemDust
	dustEnderpearl = new GTItemDust(GTItemDustTypes.ENDERPEARL),
    dustEnderEye = new GTItemDust(GTItemDustTypes.ENDER_EYE),
	dustLazurite = new GTItemDust(GTItemDustTypes.LAZURITE),
	dustPyrite = new GTItemDust(GTItemDustTypes.PYRITE),
	dustCalcite = new GTItemDust(GTItemDustTypes.CALCITE),
	dustNetherrack = new GTItemDust(GTItemDustTypes.NETHERRACK),
	dustFlint = new GTItemDust(GTItemDustTypes.FLINT),
	dustUranium = new GTItemDust(GTItemDustTypes.URANIUM),
	dustBauxite = new GTItemDust(GTItemDustTypes.BAUXITE),
	dustAluminum = new GTItemDust(GTItemDustTypes.ALUMINUM),
	dustTitanium = new GTItemDust(GTItemDustTypes.TITANIUM),
	dustChrome = new GTItemDust(GTItemDustTypes.CHROME),
	dustRuby = new GTItemDust(GTItemDustTypes.RUBY),
	dustSapphire = new GTItemDust(GTItemDustTypes.SAPPHIRE),
	dustGreenSapphire = new GTItemDust(GTItemDustTypes.GREEN_SAPPHIRE),
	dustEmerald = new GTItemDust(GTItemDustTypes.EMERALD);

	public static final GTItemIngot
	ingotIridium = new GTItemIngot(GTItemIngotTypes.IRIDIUM),
	ingotAluminum = new GTItemIngot(GTItemIngotTypes.ALUMINUM),
	ingotTitanium = new GTItemIngot(GTItemIngotTypes.TITANIUM),
	ingotChrome = new GTItemIngot(GTItemIngotTypes.CHROME);
	
	public static final GTItemGem
	ruby = new GTItemGem(GTItemGemTypes.RUBY),
	sapphire = new GTItemGem(GTItemGemTypes.SAPPHIRE);
	
	public static final GTItemComponents
	energyFlowCircuit = new GTItemComponents(GTItemComponentTypes.ENERGY_FLOW_CIRCUIT),
	dataControlCircuit = new GTItemComponents(GTItemComponentTypes.DATA_CONTROL_CIRCUIT),
	superConductor = new GTItemComponents(GTItemComponentTypes.SUPERCONDUCTOR),
	dataStorageCircuit = new GTItemComponents(GTItemComponentTypes.DATA_STORAGE_CIRCUIT),
	braintechAerospaceARDT = new GTItemComponents(GTItemComponentTypes.BRAINTECH_AEROSPACE_ARDT);

	public static final Item[] items = {
			
			creditAlk,
			
			hammerIron,
			advancedDrill,
			advancedChainsaw,
			lithiumBattery,
			lapotronicEnergyOrb,
			lithiumBatpack,
			lapotronPack,
			
			cellH,
			cellD,
			cellT,
			cellHE,
			cellW,
			cellLI,
			cellHE3,
			cellSI,
			cellC,
			cellME,
			cellBE,
			cellCA,
			cellNA,
			cellCL,
			cellK,
			cellN,

			dustEnderpearl,
			dustEnderEye,
			dustLazurite,
			dustPyrite,
			dustCalcite,
			dustNetherrack,
			dustFlint,
			dustUranium,
			dustBauxite,
			dustAluminum,
			dustTitanium,
			dustChrome,
			dustRuby,
			dustSapphire,
			dustGreenSapphire,
			dustEmerald,

			ruby,
			sapphire,
			ingotIridium,
			ingotAluminum,
			ingotTitanium,
			ingotChrome,
			
			energyFlowCircuit,
			dataControlCircuit,
			superConductor,
			dataStorageCircuit,
			braintechAerospaceARDT,
	};
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry registry = event.getRegistry();
		//items
		GTClassic.logger.info("Registering Items");
		for (Item item : items)
		{
			registry.register(item);
		}
	}
	//inits textures for items
	@SideOnly(Side.CLIENT)
    public static void initModels() {
       
        creditAlk.initModel();
    }
}
