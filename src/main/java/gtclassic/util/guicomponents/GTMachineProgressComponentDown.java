package gtclassic.util.guicomponents;

import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.lang.storage.Ic2InfoLang;
import ic2.core.platform.player.PlayerHandler;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;
import java.util.List;

public class GTMachineProgressComponentDown extends GuiComponent{
    IProgressMachine progress;
    Vec2i texPos;

    public GTMachineProgressComponentDown(IProgressMachine tile, Box2D box, Vec2i pos) {
        super(box);
        this.progress = tile;
        this.texPos = pos;
    }

    public List<GuiComponent.ActionRequest> getNeededRequests() {
        return Arrays.asList(GuiComponent.ActionRequest.BackgroundDraw, GuiComponent.ActionRequest.ToolTip);
    }

    @SideOnly(Side.CLIENT)
    public void drawBackground(GuiIC2 gui, int mouseX, int mouseY, float particalTicks) {
        float prog = this.progress.getProgress();
        if (prog >= 1.0F) {
            int x = gui.getXOffset();
            int y = gui.getYOffset();
            float per = prog / this.progress.getMaxProgress();
            if (per > 1.0F) {
                per = 1.0F;
            }

            Box2D box = this.getPosition();
            int maxX = box.getLenght();
            int lvl = (int)(per * (float)maxX);
            if (lvl <= 0) {
                return;
            }

            gui.drawTexturedModalRect(x + box.getX(), y + box.getY(), this.texPos.getX(), this.texPos.getY(), lvl, box.getHeight());
        }

    }

    @SideOnly(Side.CLIENT)
    public void onToolTipCollecting(GuiIC2 gui, int mouseX, int mouseY, List<String> tooltips) {
        if (this.isMouseOver(mouseX, mouseY) && PlayerHandler.getClientPlayerHandler().hasEUReader()) {
            tooltips.add(Ic2InfoLang.machineProgress.getLocalizedFormatted(new Object[]{this.progress.getProgress(), this.progress.getMaxProgress()}));
        }

    }
}
