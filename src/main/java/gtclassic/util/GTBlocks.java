package gtclassic.util;

import com.google.common.base.Preconditions;
import gtclassic.GTClassic;
import gtclassic.blocks.GTBlockHazard;
import gtclassic.blocks.cabinet.GTBlockCabinet;
import gtclassic.blocks.cabinet.TileEntityCabinet;
import gtclassic.blocks.resources.GTBlockMetals;
import gtclassic.blocks.resources.GTBlockSandIron;
import gtclassic.toxicdimension.blocks.BlockToxicPortalFrame;
import gtclassic.toxicdimension.blocks.BlockToxicGrass;
import gtclassic.toxicdimension.blocks.BlockToxicPortal;
import gtclassic.blocks.resources.GTBlockMetals.GTBlockMetalsVariants;


import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

@ObjectHolder(GTClassic.MODID)
public class GTBlocks {

    //not required but useful stored references to blocks
    public static final GTBlockHazard blockHazard = new GTBlockHazard();
    public static final GTBlockCabinet blockCabinet = new GTBlockCabinet();
    public static final GTBlockSandIron sandIron = new GTBlockSandIron();

	public static final GTBlockMetals
	rubyBlock = new GTBlockMetals(GTBlockMetalsVariants.RUBY),
	sapphireBlock = new GTBlockMetals(GTBlockMetalsVariants.SAPPHIRE),
	aluminumBlock = new GTBlockMetals(GTBlockMetalsVariants.ALUMINUM),
	titaniumBlock = new GTBlockMetals(GTBlockMetalsVariants.TITANIUM),
	chromeBlock = new GTBlockMetals(GTBlockMetalsVariants.CHROME),
	steelBlock = new GTBlockMetals(GTBlockMetalsVariants.STEEL),
	brassBlock = new GTBlockMetals(GTBlockMetalsVariants.BRASS),
	leadBlock = new GTBlockMetals(GTBlockMetalsVariants.LEAD),
	electrumBlock = new GTBlockMetals(GTBlockMetalsVariants.ELECTRUM),
	zincBlock = new GTBlockMetals(GTBlockMetalsVariants.ZINC),
	olivineBlock = new GTBlockMetals(GTBlockMetalsVariants.OLIVINE),
	greenSapphireBlock = new GTBlockMetals(GTBlockMetalsVariants.GREEN_SAPPHIRE),
	platinumBlock = new GTBlockMetals(GTBlockMetalsVariants.PLATINUM),
	tungstenBlock = new GTBlockMetals(GTBlockMetalsVariants.TUNGSTEN),
	nickelBlock = new GTBlockMetals(GTBlockMetalsVariants.NICKEL),
	tungstensteelBlock = new GTBlockMetals(GTBlockMetalsVariants.TUNGSTENSTEEL),
	iridiumReinforcedTungstensteelBlock = new GTBlockMetals(GTBlockMetalsVariants.IRIDIUM_REINFORCED_TUNGSTENSTEEL),
	invarBlock = new GTBlockMetals(GTBlockMetalsVariants.INVAR),
	osmiumBlock = new GTBlockMetals(GTBlockMetalsVariants.OSMIUM),
	iridiumBlock = new GTBlockMetals(GTBlockMetalsVariants.IRIDIUM);

    public static final BlockToxicPortalFrame toxicPortalFrame = new BlockToxicPortalFrame();
	public static final BlockToxicPortal toxicPortal = new BlockToxicPortal();
    public static final BlockToxicGrass grassToxic = new BlockToxicGrass();
    @Mod.EventBusSubscriber(modid = GTClassic.MODID)
    public static class RegistrationHandler {
        public static final Block[] blocks = {
                blockHazard,
                blockCabinet,
                sandIron,
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
                toxicPortalFrame,
                toxicPortal,
                grassToxic
        };
        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event){
            IForgeRegistry registry = event.getRegistry();
            for (Block block : blocks){
                registry.register(block);
            }
            registerTileEntity(TileEntityCabinet.class, "_cabinetblock");
        }
        @SubscribeEvent
        public static void registerItemBlocks(RegistryEvent.Register<Item> event){
            final IForgeRegistry registry = event.getRegistry();
            for (Block block : blocks) {
                registry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()).setUnlocalizedName(block.getUnlocalizedName()).setCreativeTab(GTClassic.creativeTabGT));
            }
        }
        private static void registerTileEntity(final Class<? extends TileEntity> tileEntityClass, final String name) {
            GameRegistry.registerTileEntity(tileEntityClass, GTClassic.MODID + ":" + name);
        }
    }
	
	//inits block models all blocks should be listed
    @SideOnly(Side.CLIENT)
    public static void initModels() {
    	//blocks
    	blockHazard.initModel();
    	blockCabinet.initModel();
    	
    	sandIron.initModel();
    	
    	toxicPortalFrame.initModel();
    	toxicPortal.initModel();
    	grassToxic.initModel();
    	}
}
