package gtclassic.gui;

import gtclassic.GTClassic;
import gtclassic.container.GTContainerCabinet;
import gtclassic.tileentity.GTTileEntityCabinet;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

//TODO refactor and delete this package using ic2c methods for gui handling

public class GTGuiCabinet extends GuiContainer {
    public static final int WIDTH = 180;
    public static final int HEIGHT = 152;

    private static final ResourceLocation background = new ResourceLocation(GTClassic.MODID, "textures/gui/cabinetgui.png");

    public GTGuiCabinet(GTTileEntityCabinet tileEntity, GTContainerCabinet container) {
        super(container);

        xSize = WIDTH;
        ySize = HEIGHT;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        super.renderHoveredToolTip(mouseX, mouseY);
    }
}
