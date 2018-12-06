package gtclassic.util;

import gtclassic.GTClassic;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleItemInfoComp;
import ic2.core.platform.lang.components.base.LocaleComp;

public class GTLang {

	public static LocaleComp hfsu = new LocaleBlockComp("tile." + GTClassic.MODID + ".hesu");
	public static LocaleComp fusion = new LocaleBlockComp("tile." + GTClassic.MODID + ".fusionreactor");

	public static LocaleComp smallchest = new LocaleBlockComp("tile." + GTClassic.MODID + ".smallchest");
	public static LocaleComp largechest = new LocaleBlockComp("tile." + GTClassic.MODID + ".largechest");
	public static LocaleComp quantumchest = new LocaleBlockComp("tile." + GTClassic.MODID + ".quantumchest");

	public static LocaleComp zpm1 = new LocaleItemInfoComp("tooltip." + GTClassic.MODID + ".zpm1");
	public static LocaleComp zpm2 = new LocaleItemInfoComp("tooltip." + GTClassic.MODID + ".zpm2");
	public static LocaleComp zpm3 = new LocaleItemInfoComp("tooltip." + GTClassic.MODID + ".zpm3");
	public static LocaleComp zpm4 = new LocaleItemInfoComp("tooltip." + GTClassic.MODID + ".zpm4");

}
