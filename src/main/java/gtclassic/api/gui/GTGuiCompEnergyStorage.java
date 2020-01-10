package gtclassic.api.gui;

import java.awt.Color;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import gtclassic.common.tile.GTTileIDSU;
import gtclassic.common.tile.multi.GTTileMultiLESU;
import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.lang.storage.Ic2GuiLang;
import ic2.core.platform.registry.Ic2GuiComp;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompEnergyStorage extends GuiComponent {

	TileEntityElectricBlock block;

	public GTGuiCompEnergyStorage(TileEntityElectricBlock tile) {
		super(Ic2GuiComp.nullBox);
		this.block = tile;
	}

	@Override
	public List<ActionRequest> getNeededRequests() {
		return Arrays.asList(ActionRequest.FrontgroundDraw);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawFrontground(GuiIC2 gui, int mouseX, int mouseY) {
		gui.drawString(block.getBlockName(), 12, 8, Color.cyan.hashCode());
		gui.drawString(Ic2GuiLang.energyStorageCharge, 12, 18, Color.cyan.hashCode());
		int eu = this.block.getStoredEU();
		int max = this.block.getMaxEU();
		if (eu > max) {
			eu = max;
		}
		gui.drawString("" + NumberFormat.getNumberInstance(Locale.US).format(eu), 12, 28, Color.cyan.hashCode());
		gui.drawString("/" + NumberFormat.getNumberInstance(Locale.US).format(max), 12, 38, Color.cyan.hashCode());
		gui.drawString(Ic2GuiLang.energyStorageOutput.getLocalizedFormatted(new Object[] {
				this.block.output }), 12, 48, Color.cyan.hashCode());
		// This is just for the UUID in the IDSU, didnt warrant a whole new class
		if (this.block instanceof GTTileIDSU) {
			String owner = ((GTTileIDSU) this.block).getOwnerName();
			if (owner != null) {
				gui.drawString("" + owner, 12, 58, Color.cyan.hashCode());
			} else {
				gui.drawString("No Owner", 12, 58, Color.cyan.hashCode());
			}
		}
		if (this.block instanceof GTTileMultiLESU) {
			gui.drawString("Blocks: " + ((GTTileMultiLESU) this.block).getCount(), 12, 58, Color.cyan.hashCode());
		}
	}
}
