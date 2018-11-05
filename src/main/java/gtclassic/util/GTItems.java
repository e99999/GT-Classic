package gtclassic.util;

import gtclassic.GTClassic;
import gtclassic.items.GTItemCreditAlk;
import gtclassic.items.GTItemCreditDoge;
import gtclassic.items.GTItemDusts;
import gtclassic.items.GTItemDusts.GTItemDustTypes;
import gtclassic.items.GTItemHammerIron;
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
