package gtclassic.util.guicomponents;

import java.util.Arrays;
import java.util.List;

import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.buttons.IconButton;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.lang.storage.Ic2GuiLang;
import ic2.core.platform.registry.Ic2GuiComp;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompEnergyStorage extends GuiComponent {

	public static final ItemStack red;
	byte lastMode;
	TileEntityElectricBlock block;
	int white = 16777215;

	public GTGuiCompEnergyStorage(TileEntityElectricBlock tile) {
		super(Ic2GuiComp.nullBox);
		this.block = tile;
	}

	@Override
	public List<ActionRequest> getNeededRequests() {
		return Arrays.asList(ActionRequest.GuiInit, ActionRequest.ButtonNotify, ActionRequest.GuiTick,
				ActionRequest.FrontgroundDraw, ActionRequest.BackgroundDraw);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawFrontground(GuiIC2 gui, int mouseX, int mouseY) {
		gui.drawString(block.getBlockName(), 12, 8, white);
		gui.drawString(Ic2GuiLang.energyStorageCharge, 12, 18, white);
		int eu = this.block.getStoredEU();
		int max = this.block.getMaxEU();
		if (eu > max) {
			eu = max;
		}

		gui.drawString("" + eu, 12, 28, white);
		gui.drawString("/" + max, 12, 38, white);
		gui.drawString(Ic2GuiLang.energyStorageOutput.getLocalizedFormatted(new Object[] { this.block.output }), 12, 48,
				white);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiInit(GuiIC2 gui) {
		this.lastMode = this.block.redstoneMode;
		gui.registerButton((new IconButton(0, gui.getXOffset() + 103, gui.getYOffset() + 6, 20, 20)).setItemStack(red)
				.addText(this.block.getRedstoneMode()));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiTick(GuiIC2 gui) {
		if (this.lastMode != this.block.redstoneMode) {
			this.lastMode = this.block.redstoneMode;
			gui.getCastedButton(0, IconButton.class).clearText().addText(this.block.getRedstoneMode());
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onButtonClick(GuiIC2 gui, GuiButton button) {
		this.block.getNetwork().initiateClientTileEntityEvent(this.block, 0);
	}

	static {
		red = new ItemStack(Items.REDSTONE);
	}

}
