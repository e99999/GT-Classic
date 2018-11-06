package gtclassic.util;

import gtclassic.GTClassic;
import gtclassic.items.*;
import gtclassic.items.GTItemDusts.GTItemDustTypes;
import gtclassic.items.GTItemTinyDusts.GTItemTinyDustTypes;
import gtclassic.items.GTItemNuggets.GTItemNuggetTypes;
import gtclassic.items.GTItemMaterials.GTItemMaterialTypes;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

@GameRegistry.ObjectHolder(GTClassic.MODID)
public class GTItems {

    public static final GTItemCreditDoge creditDoge = new GTItemCreditDoge();
	public static final GTItemCreditAlk creditAlk = new GTItemCreditAlk();
	public static final GTItemHammerIron hammerIron = new GTItemHammerIron();
	public static final GTItemDusts
	dustEnderpearl = new GTItemDusts(GTItemDustTypes.ENDERPEARL),
    dustEnderEye = new GTItemDusts(GTItemDustTypes.ENDER_EYE),
	dustLazurite = new GTItemDusts(GTItemDustTypes.LAZURITE),
	dustPyrite = new GTItemDusts(GTItemDustTypes.PYRITE),
	dustCalcite = new GTItemDusts(GTItemDustTypes.CALCITE),
	dustSodalite = new GTItemDusts(GTItemDustTypes.SODALITE),
	dustNetherrack = new GTItemDusts(GTItemDustTypes.NETHERRACK),
	dustFlintGT = new GTItemDusts(GTItemDustTypes.FLINT),
	dustSulfurGT = new GTItemDusts(GTItemDustTypes.SULFUR),
	dustSaltpeterGT = new GTItemDusts(GTItemDustTypes.SALTPETER),
	dustEndstone = new GTItemDusts(GTItemDustTypes.ENDSTONE),
	dustCinnabar = new GTItemDusts(GTItemDustTypes.CINNABAR),
	dustMaganese = new GTItemDusts(GTItemDustTypes.MAGANESE),
	dustMagnesiumGT = new GTItemDusts(GTItemDustTypes.MAGNESIUM),
	dustSphalerite = new GTItemDusts(GTItemDustTypes.SPHALERITE),
	dustWoodPulp = new GTItemDusts(GTItemDustTypes.WOOD_PULP),
	dustUraniumGT = new GTItemDusts(GTItemDustTypes.URANIUM),
	dustBauxiteGT = new GTItemDusts(GTItemDustTypes.BAUXITE),
	dustAluminum = new GTItemDusts(GTItemDustTypes.ALUMINUM),
	dustTitaniumGT = new GTItemDusts(GTItemDustTypes.TITANIUM),
	dustChrome = new GTItemDusts(GTItemDustTypes.CHROME),
	dustElectrumGT = new GTItemDusts(GTItemDustTypes.ELECTRUM),
	dustTungstenGT = new GTItemDusts(GTItemDustTypes.TUNGSTEN),
	dustLeadGT = new GTItemDusts(GTItemDustTypes.LEAD),
	dustZincGT = new GTItemDusts(GTItemDustTypes.ZINC),
	dustBrassGT = new GTItemDusts(GTItemDustTypes.BRASS),
	dustSteelGT = new GTItemDusts(GTItemDustTypes.STEEL),
	dustPlatinumGT = new GTItemDusts(GTItemDustTypes.PLATINUM),
	dustNickelGT = new GTItemDusts(GTItemDustTypes.NICKEL),
	dustInvarGT = new GTItemDusts(GTItemDustTypes.INVAR),
	dustOsmiumGT = new GTItemDusts(GTItemDustTypes.OSMIUM),
	dustRubyGT = new GTItemDusts(GTItemDustTypes.RUBY),
	dustSapphireGT = new GTItemDusts(GTItemDustTypes.SAPPHIRE),
	dustGreenSapphireGT = new GTItemDusts(GTItemDustTypes.GREEN_SAPPHIRE),
	dustEmerald = new GTItemDusts(GTItemDustTypes.EMERALD),
	dustDiamondGT = new GTItemDusts(GTItemDustTypes.DIAMOND),
	dustOlivine = new GTItemDusts(GTItemDustTypes.OLIVINE),
	dustGalena = new GTItemDusts(GTItemDustTypes.GALENA),
	dustPhosphor = new GTItemDusts(GTItemDustTypes.PHOSPHOR),
	dustRedGarnet = new GTItemDusts(GTItemDustTypes.RED_GARNET),
	dustYellowGarnet = new GTItemDusts(GTItemDustTypes.YELLOW_GARNET),
	dustPyrope = new GTItemDusts(GTItemDustTypes.PYROPE),
	dustAlmandine = new GTItemDusts(GTItemDustTypes.ALMANDINE),
	dustSpessartine = new GTItemDusts(GTItemDustTypes.SPESSARTINE),
	dustAndradite = new GTItemDusts(GTItemDustTypes.ANDRADITE),
	dustGrossular = new GTItemDusts(GTItemDustTypes.GROSSULAR),
	dustUvarovite = new GTItemDusts(GTItemDustTypes.UVAROVITE),
	dustAshes = new GTItemDusts(GTItemDustTypes.ASHES),
	dustDarkAshes = new GTItemDusts(GTItemDustTypes.DARK_ASHES),
	dustRedRock = new GTItemDusts(GTItemDustTypes.REDROCK),
	dustMarble = new GTItemDusts(GTItemDustTypes.MARBLE),
	dustBasalt = new GTItemDusts(GTItemDustTypes.BASALT),
	dustThoriumGT = new GTItemDusts(GTItemDustTypes.THORIUM),
	dustPlutoniumGT = new GTItemDusts(GTItemDustTypes.PLUTONIUM);

	public static final GTItemTinyDusts
	tinyDustEnderpearl = new GTItemTinyDusts(GTItemTinyDustTypes.ENDERPEARL),
    tinyDustEnderEye = new GTItemTinyDusts(GTItemTinyDustTypes.ENDER_EYE),
	tinyDustLazurite = new GTItemTinyDusts(GTItemTinyDustTypes.LAZURITE),
	tinyDustPyrite = new GTItemTinyDusts(GTItemTinyDustTypes.PYRITE),
	tinyDustCalcite = new GTItemTinyDusts(GTItemTinyDustTypes.CALCITE),
	tinyDustSodalite = new GTItemTinyDusts(GTItemTinyDustTypes.SODALITE),
	tinyDustNetherrack = new GTItemTinyDusts(GTItemTinyDustTypes.NETHERRACK),
	tinyDustFlintGT = new GTItemTinyDusts(GTItemTinyDustTypes.FLINT),
	tinyDustSulfurGT = new GTItemTinyDusts(GTItemTinyDustTypes.SULFUR),
	tinyDustSaltpeterGT = new GTItemTinyDusts(GTItemTinyDustTypes.SALTPETER),
	tinyDustEndstone = new GTItemTinyDusts(GTItemTinyDustTypes.ENDSTONE),
	tinyDustCinnabar = new GTItemTinyDusts(GTItemTinyDustTypes.CINNABAR),
	tinyDustMaganese = new GTItemTinyDusts(GTItemTinyDustTypes.MAGANESE),
	tinyDustMagnesiumGT = new GTItemTinyDusts(GTItemTinyDustTypes.MAGNESIUM),
	tinyDustSphalerite = new GTItemTinyDusts(GTItemTinyDustTypes.SPHALERITE),
	tinyDustWoodPulp = new GTItemTinyDusts(GTItemTinyDustTypes.WOOD_PULP),
	tinyDustUraniumGT = new GTItemTinyDusts(GTItemTinyDustTypes.URANIUM),
	tinyDustBauxiteGT = new GTItemTinyDusts(GTItemTinyDustTypes.BAUXITE),
	tinyDustAluminum = new GTItemTinyDusts(GTItemTinyDustTypes.ALUMINUM),
	tinyDustTitaniumGT = new GTItemTinyDusts(GTItemTinyDustTypes.TITANIUM),
	tinyDustChrome = new GTItemTinyDusts(GTItemTinyDustTypes.CHROME),
	tinyDustElectrumGT = new GTItemTinyDusts(GTItemTinyDustTypes.ELECTRUM),
	tinyDustTungstenGT = new GTItemTinyDusts(GTItemTinyDustTypes.TUNGSTEN),
	tinyDustLeadGT = new GTItemTinyDusts(GTItemTinyDustTypes.LEAD),
	tinyDustZincGT = new GTItemTinyDusts(GTItemTinyDustTypes.ZINC),
	tinyDustBrassGT = new GTItemTinyDusts(GTItemTinyDustTypes.BRASS),
	tinyDustSteelGT = new GTItemTinyDusts(GTItemTinyDustTypes.STEEL),
	tinyDustPlatinumGT = new GTItemTinyDusts(GTItemTinyDustTypes.PLATINUM),
	tinyDustNickelGT = new GTItemTinyDusts(GTItemTinyDustTypes.NICKEL),
	tinyDustInvarGT = new GTItemTinyDusts(GTItemTinyDustTypes.INVAR),
	tinyDustOsmiumGT = new GTItemTinyDusts(GTItemTinyDustTypes.OSMIUM),
	tinyDustRubyGT = new GTItemTinyDusts(GTItemTinyDustTypes.RUBY),
	tinyDustSapphireGT = new GTItemTinyDusts(GTItemTinyDustTypes.SAPPHIRE),
	tinyDustGreenSapphireGT = new GTItemTinyDusts(GTItemTinyDustTypes.GREEN_SAPPHIRE),
	tinyDustEmerald = new GTItemTinyDusts(GTItemTinyDustTypes.EMERALD),
	tinyDustDiamondGT = new GTItemTinyDusts(GTItemTinyDustTypes.DIAMOND),
	tinyDustOlivine = new GTItemTinyDusts(GTItemTinyDustTypes.OLIVINE),
	tinyDustGalena = new GTItemTinyDusts(GTItemTinyDustTypes.GALENA),
	tinyDustPhosphor = new GTItemTinyDusts(GTItemTinyDustTypes.PHOSPHOR),
	tinyDustObsidian = new GTItemTinyDusts(GTItemTinyDustTypes.OBSIDIAN),
	tinyDustCharcoal = new GTItemTinyDusts(GTItemTinyDustTypes.CHARCOAL),
	tinyDustRedGarnet = new GTItemTinyDusts(GTItemTinyDustTypes.RED_GARNET),
	tinyDustYellowGarnet = new GTItemTinyDusts(GTItemTinyDustTypes.YELLOW_GARNET),
	tinyDustPyrope = new GTItemTinyDusts(GTItemTinyDustTypes.PYROPE),
	tinyDustAlmandine = new GTItemTinyDusts(GTItemTinyDustTypes.ALMANDINE),
	tinyDustSpessartine = new GTItemTinyDusts(GTItemTinyDustTypes.SPESSARTINE),
	tinyDustAndradite = new GTItemTinyDusts(GTItemTinyDustTypes.ANDRADITE),
	tinyDustGrossular = new GTItemTinyDusts(GTItemTinyDustTypes.GROSSULAR),
	tinyDustUvarovite = new GTItemTinyDusts(GTItemTinyDustTypes.UVAROVITE),
	tinyDustAshes = new GTItemTinyDusts(GTItemTinyDustTypes.ASHES),
	tinyDustDarkAshes = new GTItemTinyDusts(GTItemTinyDustTypes.DARK_ASHES),
	tinyDustRedRock = new GTItemTinyDusts(GTItemTinyDustTypes.REDROCK),
	tinyDustMarble = new GTItemTinyDusts(GTItemTinyDustTypes.MARBLE),
	tinyDustBasalt = new GTItemTinyDusts(GTItemTinyDustTypes.BASALT),
	tinyDustThoriumGT = new GTItemTinyDusts(GTItemTinyDustTypes.THORIUM),
	tinyDustPlutoniumGT = new GTItemTinyDusts(GTItemTinyDustTypes.PLUTONIUM),
	tinyDustCoal = new GTItemTinyDusts(GTItemTinyDustTypes.COAL),
	tinyDustIron = new GTItemTinyDusts(GTItemTinyDustTypes.IRON),
	tinyDustGold = new GTItemTinyDusts(GTItemTinyDustTypes.GOLD),
	tinyDustCopper = new GTItemTinyDusts(GTItemTinyDustTypes.COPPER),
	tinyDustTin = new GTItemTinyDusts(GTItemTinyDustTypes.TIN),
	tinyDustBronze = new GTItemTinyDusts(GTItemTinyDustTypes.BRONZE),
	tinyDustSilver = new GTItemTinyDusts(GTItemTinyDustTypes.SILVER),
	tinyDustClay = new GTItemTinyDusts(GTItemTinyDustTypes.CLAY),
	tinyDustGunpowder = new GTItemTinyDusts(GTItemTinyDustTypes.GUNPOWDER),
	tinyDustRedstone = new GTItemTinyDusts(GTItemTinyDustTypes.REDSTONE),
	tinyDustGlowstone = new GTItemTinyDusts(GTItemTinyDustTypes.GLOWSTONE);

	public static final GTItemNuggets
	nuggetIridium = new GTItemNuggets(GTItemNuggetTypes.IRIDIUM),
	nuggetSilver = new GTItemNuggets(GTItemNuggetTypes.SILVER),
	nuggetAluminum = new GTItemNuggets(GTItemNuggetTypes.ALUMINUM),
	nuggetTitanium = new GTItemNuggets(GTItemNuggetTypes.TITANIUM),
	nuggetChrome = new GTItemNuggets(GTItemNuggetTypes.CHROME),
	nuggetElectrum = new GTItemNuggets(GTItemNuggetTypes.ELECTRUM),
	nuggetTungsten = new GTItemNuggets(GTItemNuggetTypes.TUNGSTEN),
	nuggetLead = new GTItemNuggets(GTItemNuggetTypes.LEAD),
	nuggetZinc = new GTItemNuggets(GTItemNuggetTypes.ZINC),
	nuggetBrass = new GTItemNuggets(GTItemNuggetTypes.BRASS),
	nuggetSteel = new GTItemNuggets(GTItemNuggetTypes.STEEL),
	nuggetPlatinum = new GTItemNuggets(GTItemNuggetTypes.PLATINUM),
	nuggetNickel = new GTItemNuggets(GTItemNuggetTypes.NICKEL),
	nuggetInvar = new GTItemNuggets(GTItemNuggetTypes.INVAR),
	nuggetOsmium = new GTItemNuggets(GTItemNuggetTypes.OSMIUM),
	nuggetCopperGT = new GTItemNuggets(GTItemNuggetTypes.COPPER),
	nuggetTin = new GTItemNuggets(GTItemNuggetTypes.TIN),
	nuggetBronze = new GTItemNuggets(GTItemNuggetTypes.BRONZE);

	public static final GTItemMaterials
	ingotIridiumAlloy = new GTItemMaterials(GTItemMaterialTypes.IRIDIUM_ALLOY_INGOT),
	ingotHotTungstensteel = new GTItemMaterials(GTItemMaterialTypes.HOT_TUNGSTENSTEEL_INGOT),
	ingotTungstensteel = new GTItemMaterials(GTItemMaterialTypes.TUNGSTENSTEEL_INGOT),
	ingotIridiumGT = new GTItemMaterials(GTItemMaterialTypes.IRIDIUM_INGOT),
	ingotAluminum = new GTItemMaterials(GTItemMaterialTypes.ALUMINUM_INGOT),
	ingotTitaniumGT = new GTItemMaterials(GTItemMaterialTypes.TITANIUM_INGOT),
	ingotChromeGT = new GTItemMaterials(GTItemMaterialTypes.CHROME_INGOT),
	ingotElectrumGT = new GTItemMaterials(GTItemMaterialTypes.ELECTRUM_INGOT),
	ingotTungstenGT = new GTItemMaterials(GTItemMaterialTypes.TUNGSTEN_INGOT),
	ingotLeadGT = new GTItemMaterials(GTItemMaterialTypes.LEAD_INGOT),
	ingotZincGT = new GTItemMaterials(GTItemMaterialTypes.ZINC_INGOT),
	ingotBrassGT = new GTItemMaterials(GTItemMaterialTypes.BRASS_INGOT),
	ingotSteelGT = new GTItemMaterials(GTItemMaterialTypes.STEEL_INGOT),
	ingotPlatinumGT = new GTItemMaterials(GTItemMaterialTypes.PLATINUM_INGOT),
	ingotNickelGT = new GTItemMaterials(GTItemMaterialTypes.NICKEL_INGOT),
	ingotInvarGT = new GTItemMaterials(GTItemMaterialTypes.INVAR_INGOT),
	ingotOsmiumGT = new GTItemMaterials(GTItemMaterialTypes.OSMIUM_INGOT),
	rubyGT = new GTItemMaterials(GTItemMaterialTypes.RUBY),
	sapphireGT = new GTItemMaterials(GTItemMaterialTypes.SAPPHIRE),
	greenSapphire = new GTItemMaterials(GTItemMaterialTypes.GREEN_SAPPHIRE),
	lazuriteChunk = new GTItemMaterials(GTItemMaterialTypes.LAZURITE_CHUNK),
	plateSiliconGT = new GTItemMaterials(GTItemMaterialTypes.SILICON_PLATE),
	olivine = new GTItemMaterials(GTItemMaterialTypes.OLIVINE),
	ingotThoriumGT = new GTItemMaterials(GTItemMaterialTypes.THORIUM_INGOT),
	ingotPlutoniumGT = new GTItemMaterials(GTItemMaterialTypes.PLUTONIUM_INGOT),
	redGarnet = new GTItemMaterials(GTItemMaterialTypes.RED_GARNET),
	yellowGarnet = new GTItemMaterials(GTItemMaterialTypes.YELLOW_GARNET),
	plateIronGT = new GTItemMaterials(GTItemMaterialTypes.IRON_PLATE),
	plateGold = new GTItemMaterials(GTItemMaterialTypes.GOLD_PLATE),
	plateRefinedIronGT = new GTItemMaterials(GTItemMaterialTypes.REFINED_IRON_PLATE),
	plateTin = new GTItemMaterials(GTItemMaterialTypes.TIN_PLATE),
	plateCopperGT = new GTItemMaterials(GTItemMaterialTypes.COPPER_PLATE),
	plateSilver = new GTItemMaterials(GTItemMaterialTypes.SILVER_PLATE),
	plateBronzeGT = new GTItemMaterials(GTItemMaterialTypes.BRONZE_PLATE),
	plateElectrumGT = new GTItemMaterials(GTItemMaterialTypes.ELECTRUM_PLATE),
	plateNickel = new GTItemMaterials(GTItemMaterialTypes.NICKEL_PLATE),
	plateInvar = new GTItemMaterials(GTItemMaterialTypes.INVAR_PLATE),
	plateLead = new GTItemMaterials(GTItemMaterialTypes.LEAD_PLATE),
	plateAluminum = new GTItemMaterials(GTItemMaterialTypes.ALUMINUM_PLATE),
	plateChrome = new GTItemMaterials(GTItemMaterialTypes.CHROME_PLATE),
	plateTitaniumGT = new GTItemMaterials(GTItemMaterialTypes.TITANIUM_PLATE),
	plateSteelGT = new GTItemMaterials(GTItemMaterialTypes.STEEL_PLATE),
	platePlatinumGT = new GTItemMaterials(GTItemMaterialTypes.PLATINUM_PLATE),
	plateTungstenGT = new GTItemMaterials(GTItemMaterialTypes.TUNGSTEN_PLATE),
	plateBrassGT = new GTItemMaterials(GTItemMaterialTypes.BRASS_PLATE),
	plateZinc = new GTItemMaterials(GTItemMaterialTypes.ZINC_PLATE),
	plateTungstensteel = new GTItemMaterials(GTItemMaterialTypes.TUNGSTENSTEEL_PLATE),
	plateOsmium = new GTItemMaterials(GTItemMaterialTypes.OSMIUM_PLATE),
	plateMagnaliumGT = new GTItemMaterials(GTItemMaterialTypes.MAGNALIUM_PLATE),
	flour = new GTItemMaterials(GTItemMaterialTypes.FLOUR),
	plateWood = new GTItemMaterials(GTItemMaterialTypes.WOOD_PLATE);

	@Mod.EventBusSubscriber(modid = GTClassic.MODID)
	public static class RegistrationHandler {
		public static final Item[] items = {
				creditDoge,
				creditAlk,
				hammerIron,
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
	}
	//inits textures for items
	@SideOnly(Side.CLIENT)
    public static void initModels() {
        creditDoge.initModel();
        creditAlk.initModel();
        hammerIron.initModel();
    }
}
