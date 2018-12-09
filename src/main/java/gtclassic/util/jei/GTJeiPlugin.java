package gtclassic.util.jei;

import javax.annotation.Nonnull;

import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;

@JEIPlugin
public class GTJeiPlugin implements IModPlugin {
	
	@Override
	public void onRuntimeAvailable(@Nonnull IJeiRuntime arg0) {
		// empty method for construction
	}
	
	@Override
	public void register(@Nonnull IModRegistry registry) {
		// register handlers first
		// register recipes seconds
	}

}
