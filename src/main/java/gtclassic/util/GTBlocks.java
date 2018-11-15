package gtclassic.util;

import gtclassic.GTClassic;
import gtclassic.blocks.GTBlockCabinet;
import gtclassic.blocks.GTBlockHazard;
import gtclassic.blocks.GTBlockMachine;
import gtclassic.blocks.GTBlockMachine.GTBlockMachineVariants;
import gtclassic.blocks.GTBlockHazard.GTBlockHazardVariants;
import gtclassic.blocks.machines.GTBlockAlloySmelter;
import gtclassic.blocks.resources.GTBlockMetal;
import gtclassic.blocks.resources.GTBlockOre;
import gtclassic.blocks.resources.GTBlockSandIron;
import gtclassic.tileentity.GTTileEntityAlloySmelter;
import gtclassic.tileentity.GTTileEntityCabinet;
import gtclassic.toxicdimension.blocks.GTBlockToxicPortalFrame;
import gtclassic.toxicdimension.blocks.GTBlockToxicGrass;
import gtclassic.toxicdimension.blocks.GTBlockToxicPortal;
import gtclassic.blocks.resources.GTBlockMetal.GTBlockMetalVariants;
import gtclassic.blocks.resources.GTBlockOre.GTBlockOreVariants;


import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class GTBlocks {

    //not required but useful stored references to blocks
    public static final GTBlockCabinet blockCabinet = new GTBlockCabinet();
    public static final GTBlockSandIron sandIron = new GTBlockSandIron();
    public static final GTBlockAlloySmelter alloySmelter = new GTBlockAlloySmelter();

    public static final GTBlockMachine
	fusionMachineBlock = new GTBlockMachine(GTBlockMachineVariants.FUSION),
	lesuMachineBlock = new GTBlockMachine(GTBlockMachineVariants.LESU),
	standardMachineBlock = new GTBlockMachine(GTBlockMachineVariants.STANDARD),
	reinforcedMachineBlock = new GTBlockMachine(GTBlockMachineVariants.REINFORCED),
	advancedMachineBlock = new GTBlockMachine(GTBlockMachineVariants.ADVANCED),
	highlyadvancedMachineBlock = new GTBlockMachine(GTBlockMachineVariants.HIGHLYADVANCED);
    
    public static final GTBlockHazard
    metalHazardBlock = new GTBlockHazard(GTBlockHazardVariants.METAL),
    fireHazardBlock = new GTBlockHazard(GTBlockHazardVariants.FIRE),
    radioactiveHazardBlock = new GTBlockHazard(GTBlockHazardVariants.RADIOACTIVE),
    cautionHazardBlock = new GTBlockHazard(GTBlockHazardVariants.CAUTION);
    
    public static final GTBlockMetal
	rubyBlock = new GTBlockMetal(GTBlockMetalVariants.RUBY),
	sapphireBlock = new GTBlockMetal(GTBlockMetalVariants.SAPPHIRE),
	aluminumBlock = new GTBlockMetal(GTBlockMetalVariants.ALUMINUM),
	titaniumBlock = new GTBlockMetal(GTBlockMetalVariants.TITANIUM),
	chromeBlock = new GTBlockMetal(GTBlockMetalVariants.CHROME),
	steelBlock = new GTBlockMetal(GTBlockMetalVariants.STEEL),
	brassBlock = new GTBlockMetal(GTBlockMetalVariants.BRASS),
	leadBlock = new GTBlockMetal(GTBlockMetalVariants.LEAD),
	electrumBlock = new GTBlockMetal(GTBlockMetalVariants.ELECTRUM),
	zincBlock = new GTBlockMetal(GTBlockMetalVariants.ZINC),
	olivineBlock = new GTBlockMetal(GTBlockMetalVariants.OLIVINE),
	greenSapphireBlock = new GTBlockMetal(GTBlockMetalVariants.GREEN_SAPPHIRE),
	platinumBlock = new GTBlockMetal(GTBlockMetalVariants.PLATINUM),
	tungstenBlock = new GTBlockMetal(GTBlockMetalVariants.TUNGSTEN),
	nickelBlock = new GTBlockMetal(GTBlockMetalVariants.NICKEL),
	tungstensteelBlock = new GTBlockMetal(GTBlockMetalVariants.TUNGSTENSTEEL),
	iridiumReinforcedTungstensteelBlock = new GTBlockMetal(GTBlockMetalVariants.IRIDIUM_REINFORCED_TUNGSTENSTEEL),
	invarBlock = new GTBlockMetal(GTBlockMetalVariants.INVAR),
	osmiumBlock = new GTBlockMetal(GTBlockMetalVariants.OSMIUM),
	iridiumBlock = new GTBlockMetal(GTBlockMetalVariants.IRIDIUM);

	public static final GTBlockOre
    galenaOre = new GTBlockOre(GTBlockOreVariants.GALENA),
    iridiumOre = new GTBlockOre(GTBlockOreVariants.IRIDIUM),
    rubyOre = new GTBlockOre(GTBlockOreVariants.RUBY),
    sapphireOre = new GTBlockOre(GTBlockOreVariants.SAPPHIRE),
    bauxiteOre = new GTBlockOre(GTBlockOreVariants.BAUXITE),
    pyriteOre = new GTBlockOre(GTBlockOreVariants.PYRITE),
    cinnabarOre = new GTBlockOre(GTBlockOreVariants.CINNABAR),
    sphaleriteOre = new GTBlockOre(GTBlockOreVariants.SPHALERITE),
    tungstateOre = new GTBlockOre(GTBlockOreVariants.TUNGSTATE),
    sheldoniteOre = new GTBlockOre(GTBlockOreVariants.SHELDONITE),
    olivineOre = new GTBlockOre(GTBlockOreVariants.OLIVINE),
    sodaliteOre = new GTBlockOre(GTBlockOreVariants.SODALITE);


    public static final GTBlockToxicPortalFrame toxicPortalFrame = new GTBlockToxicPortalFrame();
	public static final GTBlockToxicPortal toxicPortal = new GTBlockToxicPortal();
    public static final GTBlockToxicGrass grassToxic = new GTBlockToxicGrass();
    
    public static final Block[] blocks = {
            blockCabinet,
            alloySmelter,
            sandIron,
            
            fusionMachineBlock,
            lesuMachineBlock,
            standardMachineBlock,
            reinforcedMachineBlock,
            advancedMachineBlock,
            highlyadvancedMachineBlock,
            toxicPortalFrame,
            
            metalHazardBlock,
            fireHazardBlock,
            radioactiveHazardBlock,
            cautionHazardBlock,
            
            rubyBlock,
            sapphireBlock,
            aluminumBlock,
            titaniumBlock,
            chromeBlock,
            steelBlock,
            brassBlock,
            leadBlock,
            electrumBlock,
            zincBlock,
            olivineBlock,
            greenSapphireBlock,
            platinumBlock,
            tungstenBlock,
            nickelBlock,
            tungstensteelBlock,
            iridiumReinforcedTungstensteelBlock,
            invarBlock,
            osmiumBlock,
            iridiumBlock,
            
            galenaOre,
            iridiumOre,
            rubyOre,
            sapphireOre,
            bauxiteOre,
            pyriteOre,
            cinnabarOre,
            sphaleriteOre,
            tungstateOre,
            sheldoniteOre,
            olivineOre,
            sodaliteOre,
            
            toxicPortal,
            grassToxic
    };
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event){
        IForgeRegistry registry = event.getRegistry();
        for (Block block : blocks){
            registry.register(block);
        }
        registerTileEntity(GTTileEntityCabinet.class, "_cabinetblock");
    }
    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event){
        final IForgeRegistry registry = event.getRegistry();
        for (Block block : blocks) {
            registry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()).setUnlocalizedName(block.getUnlocalizedName()).setCreativeTab(GTClassic.creativeTabGT));
        }
    }
    public static void registerTiles(){
        GameRegistry.registerTileEntity(GTTileEntityAlloySmelter.class, new ResourceLocation(GTClassic.MODID, "tileEntityAloySmelter"));
    }
    private static void registerTileEntity(final Class<? extends TileEntity> tileEntityClass, final String name) {
        GameRegistry.registerTileEntity(tileEntityClass, GTClassic.MODID + ":" + name);
    }
	
	//TODO refactor and remove everything below into the sprite system
    @SideOnly(Side.CLIENT)
    public static void initModels() {
    	blockCabinet.initModel();
    	sandIron.initModel();
    	toxicPortal.initModel();
    	grassToxic.initModel();
    	}
    
    //TODO refactor this information into ore blocks enums
    public static void initHarvestLevel() {
    	int stone = 1;
    	int iron = 2;
    	int diamond = 3;
    	
    	cinnabarOre.setHarvestLevel("pickaxe", iron);
    	pyriteOre.setHarvestLevel("pickaxe", stone);
    	sphaleriteOre.setHarvestLevel("pickaxe", stone);
    	
    	tungstateOre.setHarvestLevel("pickaxe", iron);
    	sheldoniteOre.setHarvestLevel("pickaxe", diamond);
    	sodaliteOre.setHarvestLevel("pickaxe", iron);
    	olivineOre.setHarvestLevel("pickaxe", diamond);
    	
    	galenaOre.setHarvestLevel("pickaxe", stone);
    	iridiumOre.setHarvestLevel("pickaxe", diamond);
    	rubyOre.setHarvestLevel("pickaxe", iron);
    	sapphireOre.setHarvestLevel("pickaxe", iron);
    	bauxiteOre.setHarvestLevel("pickaxe", stone);
    }
}
