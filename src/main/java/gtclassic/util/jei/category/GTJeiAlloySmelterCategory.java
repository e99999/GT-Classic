package gtclassic.util.jei.category;

import gtclassic.container.GTContainerAlloySmelter;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;

public class GTJeiAlloySmelterCategory extends GTJeiMultiRecipeCategory {

    private IDrawable background, progress;

    public GTJeiAlloySmelterCategory(IGuiHelper helper, Block block, String name) {
        super(name, block, GTContainerAlloySmelter.slots);
        background = helper.createDrawable(backgroundTexture, 25, 25, 120, 36);
        IDrawableStatic progressPic = helper.createDrawable(backgroundTexture, 176, 0, 10, 10);
        progress = helper.createAnimatedDrawable(progressPic, 150, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        progress.draw(minecraft, 37, 4);
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }
}
