package gtclassic;

import gtclassic.blocks.BlockHazard;
import gtclassic.blocks.cabinet.BlockCabinet;
import gtclassic.blocks.ores.BlockSandIron;
import gtclassic.toxicdimension.blocks.BlockToxicPortalFrame;
import gtclassic.toxicdimension.blocks.BlockToxicGrass;
import gtclassic.toxicdimension.blocks.BlockToxicPortal;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

    //not required but useful stored references to blocks
	@GameRegistry.ObjectHolder("gtclassic:hazard_block")
    public static BlockHazard blockHazard;
	
	@GameRegistry.ObjectHolder("gtclassic:cabinet_block")
    public static BlockCabinet blockCabinet;
	
	
	
	@GameRegistry.ObjectHolder("gtclassic:iron_sand")
    public static BlockSandIron sandIron;
	
	
	
	@GameRegistry.ObjectHolder("gtclassic:testdimension_portal")
	public static BlockToxicPortal portal;
	
	@GameRegistry.ObjectHolder("gtclassic:toxic_portal_frame")
    public static BlockToxicPortalFrame toxicPortalFrame;
	
	@GameRegistry.ObjectHolder("gtclassic:toxic_grass")
    public static BlockToxicGrass grassToxic;
	
	
	//inits block models all blocks should be listed
    @SideOnly(Side.CLIENT)
    public static void initModels() {
    	//blocks
    	blockHazard.initModel();
    	blockCabinet.initModel();
    	
    	sandIron.initModel();
    	
    	portal.initModel();
    	toxicPortalFrame.initModel();
    	grassToxic.initModel();
    	}
}
