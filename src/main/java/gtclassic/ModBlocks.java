package gtclassic;

import gtclassic.blocks.HazardBlock;
import gtclassic.blocks.ores.IronSand;
import gtclassic.blocks.testcontainer.TestContainerBlock;
import gtclassic.toxicdimension.blocks.ToxicPortalFrameBlock;
import gtclassic.toxicdimension.blocks.ToxicGrassBlock;
import gtclassic.toxicdimension.blocks.ToxicPortalBlock;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

    //not required but useful stored references to blocks
	@GameRegistry.ObjectHolder("gtclassic:hazardblock")
    public static HazardBlock hazardBlock;
	
	@GameRegistry.ObjectHolder("gtclassic:testcontainerblock")
    public static TestContainerBlock testContainerBlock;
	
	@GameRegistry.ObjectHolder("gtclassic:ironsand")
    public static IronSand ironSand;
	
	@GameRegistry.ObjectHolder("gtclassic:testdimension_portal")
	public static ToxicPortalBlock portal;
	
	@GameRegistry.ObjectHolder("gtclassic:portalframe")
    public static ToxicPortalFrameBlock portalFrame;
	
	@GameRegistry.ObjectHolder("gtclassic:toxicgrassblock")
    public static ToxicGrassBlock toxicgrassBlock;
	
	
	//inits block models all blocks should be listed
    @SideOnly(Side.CLIENT)
    public static void initModels() {
    	//blocks
    	hazardBlock.initModel();
    	testContainerBlock.initModel();
    	ironSand.initModel();
    	
    	portal.initModel();
    	portalFrame.initModel();
    	toxicgrassBlock.initModel();
    	}
}
