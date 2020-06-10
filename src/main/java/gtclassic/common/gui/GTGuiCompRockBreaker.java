package gtclassic.common.gui;

import java.util.Arrays;
import java.util.List;

import gtclassic.common.GTLang;
import gtclassic.common.tile.GTTileRockBreaker;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.util.math.Box2D;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompRockBreaker extends GuiComponent {

	GTTileRockBreaker block;
	private static final Box2D BOX = new Box2D(79, 40, 18, 18);

	public GTGuiCompRockBreaker(GTTileRockBreaker tile) {
		super(BOX);
		this.block = tile;
	}

	@Override
	public List<ActionRequest> getNeededRequests() {
		return Arrays.asList(ActionRequest.ToolTip);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onToolTipCollecting(GuiIC2 gui, int mouseX, int mouseY, List<String> tooltips) {
		if (this.isMouseOver(mouseX, mouseY) && this.block.getStackInSlot(1).isEmpty()) {
			tooltips.add(I18n.format(GTLang.BUTTON_ROCKBREAKER));
		}
	}
}
