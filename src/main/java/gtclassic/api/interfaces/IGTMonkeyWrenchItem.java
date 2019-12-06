package gtclassic.api.interfaces;

import net.minecraft.entity.player.EntityPlayer;

public interface IGTMonkeyWrenchItem {

	public boolean isMonkeyWrench();

	public void onUse(EntityPlayer player);
}
