package gtclassic.api.gui;

import java.awt.Color;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import ic2.core.fluid.IC2Tank;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.registry.Ic2GuiComp;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompFluidTank extends GuiComponent {

	IC2Tank tank;

	public GTGuiCompFluidTank(IC2Tank tank) {
		super(Ic2GuiComp.nullBox);
		this.tank = tank;
	}

	@Override
	public List<ActionRequest> getNeededRequests() {
		return Arrays.asList(ActionRequest.FrontgroundDraw);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawFrontground(GuiIC2 gui, int mouseX, int mouseY) {
		gui.drawString("Fluid Amount:", 11, 20, Color.cyan.hashCode());
		int stored = this.tank.getFluidAmount();
		gui.drawString("" + NumberFormat.getNumberInstance(Locale.US).format(stored), 11, 30, Color.cyan.hashCode());
	}
}
