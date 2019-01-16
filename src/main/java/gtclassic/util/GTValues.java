package gtclassic.util;

import gtclassic.GTClassic;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleJEIInfoComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.util.math.AxisAlignedBB;

public class GTValues {

	// index for storing global variables

	// boolean that renders anything labeled as WIP uncraftable
	public static boolean debugMode = true;

	//custom bounding boxes
	public static final AxisAlignedBB FULLBLOCK_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	public static final AxisAlignedBB SLIMBATTERY_AABB = new AxisAlignedBB(0.35D, 0.0D, 0.35D, 0.65D, 0.6D, 0.65D);
	public static final AxisAlignedBB SMALLBATTERY_AABB = new AxisAlignedBB(0.3D, 0.0D, 0.3D, 0.7D, 0.4D, 0.7D);
	public static final AxisAlignedBB MEDBATTERY_AABB = new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 0.6D, 0.8D);
	public static final AxisAlignedBB LARGEBATTERY_AABB = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.8D, 0.9D);
	
	public static final AxisAlignedBB CIRCUIT_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1D, 1.0D);
	public static final AxisAlignedBB DATASTICK_AABB = new AxisAlignedBB(0.6D, 0.0D, 0.55D, 0.4D, 0.6D, 0.45D);
	public static final AxisAlignedBB DATADRIVE_AABB = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 1.0D, 0.9D);
	
	public static final AxisAlignedBB SMALLROD_AABB = new AxisAlignedBB(0.6D, 0.0D, 0.6D, 0.4D, 1.0D, 0.4D);
	public static final AxisAlignedBB MEDROD_AABB = new AxisAlignedBB(0.2D, 0.0D, 0.6D, 0.8D, 1.0D, 0.4D);
	public static final AxisAlignedBB LARGEROD_AABB = new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 1.0D, 0.8D);
	
	public static final AxisAlignedBB SMALLCOOLANT_AABB = new AxisAlignedBB(0.7D, 0.0D, 0.35D, 0.3D, 1.0D, 0.65D);
	public static final AxisAlignedBB LARGECOOLANT_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.35D, 1.0D, 1.0D, 0.65D);

	// colors
	public static int white = 16777215;
	public static int grey = 4210752;
	public static int red = 15599112;
	public static int green = 9567352;

	// lang
	public static LocaleComp hesu = new LocaleBlockComp("tile." + GTClassic.MODID + ".hesu");
	public static LocaleComp idsu = new LocaleBlockComp("tile." + GTClassic.MODID + ".idsu");
	public static LocaleComp lesu = new LocaleBlockComp("tile." + GTClassic.MODID + ".lesu");
	public static LocaleComp centrifuge = new LocaleBlockComp("tile." + GTClassic.MODID + ".industrialcentrifuge");
	public static LocaleComp fusion = new LocaleBlockComp("tile." + GTClassic.MODID + ".fusioncomputer");

	public static LocaleComp smallchest = new LocaleBlockComp("tile." + GTClassic.MODID + ".smallchest");
	public static LocaleComp largechest = new LocaleBlockComp("tile." + GTClassic.MODID + ".largechest");
	public static LocaleComp quantumchest = new LocaleBlockComp("tile." + GTClassic.MODID + ".quantumchest");
	public static LocaleComp bookshelf = new LocaleBlockComp("tile." + GTClassic.MODID + ".bookshelf");

	public static LocaleComp centrifugeEU = new LocaleJEIInfoComp("jei.centrifugeu.name");
}
