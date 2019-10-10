package gtclassic.gui;

import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.base.MachineProgressComp;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTGuiCompDirectionalProgress extends MachineProgressComp {
    Direction direction;
    IProgressMachine progress;
    Vec2i texPos;
    public GTGuiCompDirectionalProgress(IProgressMachine tile, Box2D box, Vec2i pos) {
        this(tile, box, pos, Direction.FORWARD);

    }
    public GTGuiCompDirectionalProgress(IProgressMachine tile, Box2D box, Vec2i pos, Direction direction) {
        super(tile, box, pos);
        this.direction = direction;
        this.progress = tile;
        this.texPos = pos;
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void drawBackground(GuiIC2 gui, int mouseX, int mouseY, float particalTicks) {
        if (direction == Direction.FORWARD){
            super.drawBackground(gui, mouseX, mouseY, particalTicks);
        }else {
            float prog = this.progress.getProgress();
            if (prog >= 1.0F) {
                int x = gui.getXOffset();
                int y = gui.getYOffset();
                float per = prog / this.progress.getMaxProgress();
                if (per > 1.0F) {
                    per = 1.0F;
                }

                Box2D box = this.getPosition();
                int maxY = box.getHeight();
                int maxX = box.getLenght();
                int lvl = (int)(per * (float)maxY);
                int lvlX = (int)(per * (float)maxX);
                if (lvl < 0) {
                    return;
                }

                if (direction == Direction.UP){
                    int y2 = (y + box.getY() + box.getHeight()) - lvl;
                    int texY = (this.texPos.getY() + box.getHeight()) - lvl;
                    gui.drawTexturedModalRect(x + box.getX(), y2, this.texPos.getX(), texY, box.getLenght(), lvl);
                } else if (direction == Direction.DOWN){
                    gui.drawTexturedModalRect(x + box.getX(), y + box.getY(), this.texPos.getX(), this.texPos.getY(), box.getLenght(), lvl);
                } else {
                    int x2 = (x + box.getX() + box.getLenght()) - lvl;
                    int texX = (this.texPos.getX() + box.getLenght()) - lvl;
                    gui.drawTexturedModalRect(x2, y + box.getY(), texX, this.texPos.getY(), lvlX, box.getHeight());
                }

            }
        }
    }

    public enum Direction {
        UP,
        DOWN,
        FORWARD,
        BACKWARD;

        Direction(){

        }
    }
}
