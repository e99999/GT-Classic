package gtclassic.common.gui;

import java.util.Arrays;
import java.util.List;

import gtclassic.api.gui.GTGuiButton;
import gtclassic.api.helpers.GTHelperMath;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.common.tile.GTTileBufferBase;
import ic2.core.IC2;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.math.Box2D;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompBuffer extends GuiComponent {

	public static final ItemStack cable = GTMaterialGen.getIc2(Ic2Items.insulatedCopperCable, 1);
	GTTileBufferBase tile;
	InventoryPlayer player;

	public GTGuiCompBuffer(GTTileBufferBase tile, InventoryPlayer player) {
		super(new Box2D(7, 62, 96, 18));
		this.tile = tile;
		this.player = player;
	}

	@Override
	public List<ActionRequest> getNeededRequests() {
		return Arrays.asList(ActionRequest.GuiInit, ActionRequest.ButtonNotify, ActionRequest.GuiTick, ActionRequest.ToolTip);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiInit(GuiIC2 gui) {
		gui.registerButton(new GTGuiButton(0, bX(gui, 7), bY(gui, 60), 18, 20));
		if (this.tile.hasRedstone) {
			gui.registerButton(new GTGuiButton(1, bX(gui, 25), bY(gui, 60), 18, 20));
			gui.registerButton(new GTGuiButton(2, bX(gui, 43), bY(gui, 60), 18, 20));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onButtonClick(GuiIC2 gui, GuiButton button) {
		if (button.id == 0) {
			this.tile.getNetwork().initiateClientTileEntityEvent(this.tile, 0);
			String conduct = !this.tile.conduct ? "Emit Energy to Outputside" : "Dont emit Energy";
			IC2.platform.messagePlayer(this.player.player, conduct);
		}
		if (this.tile.hasRedstone) {
			if (button.id == 1) {
				this.tile.getNetwork().initiateClientTileEntityEvent(this.tile, 1);
				String redstone = !this.tile.outputRedstone ? "Emit Redstone if slots contain something"
						: "Dont emit Redstone";
				IC2.platform.messagePlayer(this.player.player, redstone);
			}
			if (button.id == 2) {
				this.tile.getNetwork().initiateClientTileEntityEvent(this.tile, 2);
				String invert = !this.tile.invertRedstone ? "Invert Redstone" : "Dont invert Redstone";
				IC2.platform.messagePlayer(this.player.player, invert);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onToolTipCollecting(GuiIC2 gui, int mouseX, int mouseY, List<String> tooltips) {
		if (this.isMouseOver(mouseX, mouseY)) {
			if (mouseX < 25) {
				tooltips.add(I18n.format("Emit Energy"));
			}
			if (this.tile.hasRedstone) {
				if (GTHelperMath.within(mouseX, 25, 42)) {
					tooltips.add(I18n.format("Emit Redstone"));
				}
				if (GTHelperMath.within(mouseX, 43, 60)) {
					tooltips.add(I18n.format("Invert Redstone"));
				}
			}
		}
	}

	private int bX(GuiIC2 gui, int position) {
		return gui.getXOffset() + position;
	}

	private int bY(GuiIC2 gui, int position) {
		return gui.getYOffset() + position;
	}
}
