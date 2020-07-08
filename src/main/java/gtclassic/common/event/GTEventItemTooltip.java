package gtclassic.common.event;

import gtclassic.api.helpers.GTHelperStack;
import gtclassic.api.helpers.GTValues;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.common.GTConfig;
import gtclassic.common.GTLang;
import ic2.api.item.IElectricItem;
import ic2.core.item.block.ItemBlockChargePad;
import ic2.core.item.block.ItemBlockCompactedGenerator;
import ic2.core.item.block.ItemBlockElectric;
import ic2.core.item.block.ItemBlockGenerator;
import ic2.core.item.block.ItemBlockMachineHV;
import ic2.core.item.block.ItemBlockMachineLV;
import ic2.core.item.block.ItemBlockMachineLV2;
import ic2.core.item.block.ItemBlockMachineMV;
import ic2.core.item.block.ItemBlockPersonal;
import ic2.core.item.block.ItemBlockRare;
import ic2.core.item.block.ItemCable;
import ic2.core.platform.player.PlayerHandler;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTEventItemTooltip {

	// TODO move to lang ref
	private static final String TOOLTIP = "Made in a Fusion Reactor with UU-Matter";

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onTooltipEvent(ItemTooltipEvent event) {
		if (GTConfig.general.removeIC2Plasmafier
				&& GTHelperStack.isEqual(event.getItemStack(), GTMaterialGen.getIc2(Ic2Items.plasmaCell))) {
			String name = event.getItemStack().getDisplayName();
			event.getToolTip().clear();
			event.getToolTip().add(name);
			event.getToolTip().add(TOOLTIP);
		}
		PlayerHandler handler = PlayerHandler.getClientPlayerHandler();
		ItemStack stack = event.getItemStack();
		String modid = GTValues.MOD_ID_IC2_EXTRAS;
		if (!handler.hasEUReader() && (
				(stack.getItem() instanceof ItemBlockPersonal && stack.getMetadata() == 5)
				|| stack.getItem() instanceof ItemBlockGenerator
				|| (stack.getItem() instanceof ItemBlockCompactedGenerator && stack.getMetadata() < 6)
				|| (stack.getItem() instanceof ItemBlockElectric && stack.getMetadata() != 4)
				|| stack.getItem() instanceof ItemBlockChargePad
				|| (stack.getItem() instanceof ItemBlockMachineLV && stack.getMetadata() > 1 && stack.getMetadata() != 8 && stack.getMetadata() != 15)
				|| (stack.getItem() instanceof ItemBlockMachineLV2 && stack.getMetadata() != 0 && stack.getMetadata() != 6)
				|| (stack.getItem() instanceof ItemBlockMachineMV && stack.getMetadata() > 0 && stack.getMetadata() != 7)
				|| (stack.getItem() instanceof ItemBlockMachineHV && stack.getMetadata() != 2)
				|| stack.getItem() instanceof ItemCable
				|| GTHelperStack.isEqual(stack, Ic2Items.luminator)
				|| GTHelperStack.isEqual(stack, Ic2Items.luminatorMultipart)
				|| (stack.getItem() instanceof ItemBlockRare && Loader.isModLoaded(modid)
						&& stack.getItem().getRegistryName().getResourceDomain().equals(modid)
						&& !GTHelperStack.isEqual(stack, GTMaterialGen.getModBlock(modid, "steelblock"))
						&& !GTHelperStack.isEqual(stack, GTMaterialGen.getModBlock(modid, "refinedironblock"))
						&& !GTHelperStack.isEqual(stack, GTMaterialGen.getModBlock(modid, "leadblock"))
						&& !GTHelperStack.isEqual(stack, GTMaterialGen.getModBlock(modid, "stonedustblock")))
						|| stack.getItem() instanceof IElectricItem)) {
			event.getToolTip().add(GTLang.EU_READER_NEEDED.getLocalized());
		}
	}
}
