package gtclassic.common.gui;

import gtclassic.api.gui.GTGuiButton;
import gtclassic.common.container.GTContainerWorktable;
import gtclassic.common.tile.GTTileWorktable;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.registry.Ic2GuiComp;
import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;
import java.util.List;

public class GTGuiCompWorktable extends GuiComponent {

    GTContainerWorktable container;
    GTTileWorktable block;


    public GTGuiCompWorktable(GTTileWorktable tile, GTContainerWorktable container) {
        super(Ic2GuiComp.nullBox);
        this.block = tile;
        this.container = container;
    }

	@Override
	public List<ActionRequest> getNeededRequests() {
		return Arrays.asList(ActionRequest.GuiInit, ActionRequest.ButtonNotify);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiInit(GuiIC2 gui) {
		gui.registerButton((new GTGuiButton(2, bX(gui, 153), bY(gui, 25), 18, 20)));// shift crafting grid items into
																					// player inventory
		gui.registerButton((new GTGuiButton(1, bX(gui, 135), bY(gui, 25), 18, 20)));// shift crafting grid items into
																					// main inventory
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onButtonClick(GuiIC2 gui, GuiButton button) {
		if (button.id == 2) {
			this.block.getNetwork().initiateClientTileEntityEvent(this.block, 2);
		}
		if (button.id == 1) {
			this.block.getNetwork().initiateClientTileEntityEvent(this.block, 1);
		}
	}

	private int bX(GuiIC2 gui, int position) {
		return gui.getXOffset() + position;
	}

	private int bY(GuiIC2 gui, int position) {
		return gui.getYOffset() + position;
	}
}
