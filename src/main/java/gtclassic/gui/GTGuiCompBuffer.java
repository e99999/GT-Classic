package gtclassic.gui;

import java.util.Arrays;
import java.util.List;

import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileBaseBuffer;
import gtclassic.tile.GTTileBufferLarge;
import gtclassic.tile.GTTileBufferSmall;
import ic2.core.IC2;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.registry.Ic2GuiComp;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompBuffer extends GuiComponent {

	public static final ItemStack cable = GTMaterialGen.getIc2(Ic2Items.insulatedCopperCable, 1);
	GTTileBaseBuffer tile;
	InventoryPlayer player;

	public GTGuiCompBuffer(GTTileBaseBuffer tile, InventoryPlayer player) {
		super(Ic2GuiComp.nullBox);
		this.tile = tile;
		this.player = player;
	}

	@Override
	public List<ActionRequest> getNeededRequests() {
		return Arrays.asList(ActionRequest.GuiInit, ActionRequest.ButtonNotify, ActionRequest.GuiTick);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiInit(GuiIC2 gui) {
		gui.registerButton(new GTGuiButton(0, bX(gui, 7), bY(gui, 60), 18, 18));
		if (this.tile instanceof GTTileBufferSmall || this.tile instanceof GTTileBufferLarge) {
			gui.registerButton(new GTGuiButton(1, bX(gui, 25), bY(gui, 60), 18, 18));
			gui.registerButton(new GTGuiButton(2, bX(gui, 43), bY(gui, 60), 18, 18));	
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onButtonClick(GuiIC2 gui, GuiButton button) {
		if (button.id == 0) {
			this.tile.getNetwork().initiateClientTileEntityEvent(this.tile, 0);
			IC2.platform.messagePlayer(this.player.player, "Outputs Power: " + !this.tile.conduct);
		}
		if (button.id == 1) {
			this.tile.getNetwork().initiateClientTileEntityEvent(this.tile, 1);
			IC2.platform.messagePlayer(this.player.player, "Outputs Redstone If Full: " + !this.tile.outputRedstone);
		}
		if (button.id == 2) {
			this.tile.getNetwork().initiateClientTileEntityEvent(this.tile, 2);
			IC2.platform.messagePlayer(this.player.player, "Invert Redstone: " + !this.tile.invertRedstone);
		}
	}

	private int bX(GuiIC2 gui, int position) {
		return gui.getXOffset() + position;
	}

	private int bY(GuiIC2 gui, int position) {
		return gui.getYOffset() + position;
	}
}
