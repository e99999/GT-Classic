package gtclassic.common.event;

import gtclassic.api.helpers.GTHelperStack;
import gtclassic.api.material.GTMaterialGen;
import ic2.core.platform.registry.Ic2Items;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTEventItemTooltip {

	// TODO move to lang ref
	private static final String TOOLTIP = "Made in a Fusion Reactor with UU-Matter";

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onTooltipEvent(ItemTooltipEvent event) {
		if (GTHelperStack.isEqual(event.getItemStack(), GTMaterialGen.getIc2(Ic2Items.plasmaCell))) {
			String name = event.getItemStack().getDisplayName();
			event.getToolTip().clear();
			event.getToolTip().add(name);
			event.getToolTip().add(TOOLTIP);
		}
	}
}
