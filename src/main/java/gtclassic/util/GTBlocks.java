package gtclassic.util;

import gtclassic.GTClassic;

import gtclassic.blocks.GTBlockMachineCasing;
import gtclassic.blocks.GTBlockMachineCasing.GTBlockMachineVariants;
import gtclassic.blocks.resources.GTBlockMetal;
import gtclassic.blocks.resources.GTBlockOre;
import gtclassic.blocks.resources.GTBlockSandIron;
import gtclassic.toxicdimension.blocks.GTBlockToxicPortalFrame;
import gtclassic.toxicdimension.blocks.GTBlockToxicGrass;
import gtclassic.toxicdimension.blocks.GTBlockToxicPortal;
import gtclassic.blocks.resources.GTBlockMetal.GTBlockMetalVariants;
import gtclassic.blocks.resources.GTBlockOre.GTBlockOreVariants;


import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class GTBlocks {

    //not required but useful stored references to blocks
    
    public static final GTBlockSandIron sandIron = new GTBlockSandIron();

    public static final GTBlockMachineCasing
	fusionMachineBlock = new GTBlockMachineCasing(GTBlockMachineVariants.FUSION),
	lesuMachineBlock = new GTBlockMachineCasing(GTBlockMachineVariants.LESU),
	highlyadvancedMachineBlock = new GTBlockMachineCasing(GTBlockMachineVariants.HIGHLYADVANCED);
    
    
    public static final GTBlockMetal
	iridiumReinforcedStoneBlock = new GTBlockMetal(GTBlockMetalVariants.IRIDIUM_REINFORCED_STONE),
    rubyBlock = new GTBlockMetal(GTBlockMetalVariants.RUBY),
	sapphireBlock = new GTBlockMetal(GTBlockMetalVariants.SAPPHIRE),
	aluminumBlock = new GTBlockMetal(GTBlockMetalVariants.ALUMINUM),
    chromeBlock = new GTBlockMetal(GTBlockMetalVariants.CHROME),
    titaniumBlock = new GTBlockMetal(GTBlockMetalVariants.TITANIUM);

	public static final GTBlockOre
    iridiumOre = new GTBlockOre(GTBlockOreVariants.IRIDIUM),
    rubyOre = new GTBlockOre(GTBlockOreVariants.RUBY),
    sapphireOre = new GTBlockOre(GTBlockOreVariants.SAPPHIRE),
    bauxiteOre = new GTBlockOre(GTBlockOreVariants.BAUXITE);
  

    public static final GTBlockToxicPortalFrame toxicPortalFrame = new GTBlockToxicPortalFrame();
	public static final GTBlockToxicPortal toxicPortal = new GTBlockToxicPortal();
    public static final GTBlockToxicGrass grassToxic = new GTBlockToxicGrass();
    
    public static final Block[] blocks = {
    		
            fusionMachineBlock,
            lesuMachineBlock,
            highlyadvancedMachineBlock,
            toxicPortalFrame,
            
            iridiumReinforcedStoneBlock,
            rubyBlock,
            sapphireBlock,
            aluminumBlock,
            titaniumBlock,
            chromeBlock,
            
            iridiumOre,
            rubyOre,
            sapphireOre,
            bauxiteOre,
            sandIron,
            
            toxicPortal,
            grassToxic
    };
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event){
        IForgeRegistry registry = event.getRegistry();
        for (Block block : blocks){
            registry.register(block);
        }
    }
    
    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event){
        final IForgeRegistry registry = event.getRegistry();
        for (Block block : blocks) {
            registry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()).setUnlocalizedName(block.getUnlocalizedName()).setCreativeTab(GTClassic.creativeTabGT));
        }
    }
    
    public static void registerTiles(){
        //GameRegistry.registerTileEntity(GTTileEntityAlloySmelter.class, new ResourceLocation(GTClassic.MODID, "tileEntityAloySmelter"));
    }
    
    private static void registerTileEntity(final Class<? extends TileEntity> tileEntityClass, final String name) {
        GameRegistry.registerTileEntity(tileEntityClass, GTClassic.MODID + ":" + name);
    }
	
	//TODO refactor and remove everything below into the sprite system
    @SideOnly(Side.CLIENT)
    public static void initModels() {
    	sandIron.initModel();
    	toxicPortal.initModel();
    	grassToxic.initModel();
    	}
}