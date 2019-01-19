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
