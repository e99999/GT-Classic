package gtclassic.common.event;

import gtclassic.api.helpers.GTHelperStack;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.common.GTConfig;
import ic2.core.platform.registry.Ic2Items;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GTEventItemTooltip {

	@SubscribeEvent
	public void onTooltipEvent(ItemTooltipEvent event) {
		if (GTConfig.general.removeIC2Plasmafier
				&& GTHelperStack.isEqual(event.getItemStack(), GTMaterialGen.getIc2(Ic2Items.plasmaCell))) {
			String name = event.getItemStack().getDisplayName();
			event.getToolTip().clear();
			event.getToolTip().add(name);
			event.getToolTip().add("Made in a Fusion Reactor with UU-Matter");
		}
	}
}
