package gtclassic.util;

import gtclassic.GTClassic;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleJEIInfoComp;
import ic2.core.platform.lang.components.base.LocaleComp;

public class GTLang {

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
