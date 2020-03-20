package gtclassic.api.gui;

import java.util.Arrays;
import java.util.List;

import gtclassic.api.helpers.GTHelperMath;
import gtclassic.api.tile.GTTileBaseMachine;
import gtclassic.common.GTLang;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.registry.Ic2GuiComp;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompMachinePower extends GuiComponent {

	GTTileBaseMachine block;
	int x;
	int y;

	public GTGuiCompMachinePower(GTTileBaseMachine tile) {
		super(Ic2GuiComp.nullBox);
		this.block = tile;
		this.x = 80;
		this.y = 40;
	}

	public GTGuiCompMachinePower(GTTileBaseMachine tile, int x, int y) {
		super(Ic2GuiComp.nullBox);
		this.block = tile;
		this.x = x;
		this.y = y;
	}

	@Override
	public List<ActionRequest> getNeededRequests() {
		return Arrays.asList(ActionRequest.BackgroundDraw, ActionRequest.ToolTip);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawBackground(GuiIC2 gui, int mouseX, int mouseY, float particalTicks) {
		if (this.block.energy < this.block.energyConsume) {
			gui.drawTexturedModalRect(gui.getXOffset() + this.x, gui.getYOffset() + this.y, 176, 54, 18, 18);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onToolTipCollecting(GuiIC2 gui, int mouseX, int mouseY, List<String> tooltips) {
		if (GTHelperMath.within(mouseX, this.x, this.x + 18) && GTHelperMath.within(mouseY, this.y, this.y + 18)
				&& this.block.energy < this.block.energyConsume) {
			tooltips.add(I18n.format(GTLang.BUTTON_NOPOWER));
		}
	}
}
