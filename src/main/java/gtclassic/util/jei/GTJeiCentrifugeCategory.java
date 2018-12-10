package gtclassic.util.jei;
import gtclassic.GTClassic;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GTJeiCentrifugeCategory implements IRecipeCategory<GTJeiCentrifugeWrapper> {
	
	ItemStack displayName;
	IDrawable draw;
	IDrawable slot;
	IDrawable arrow;
	
	public GTJeiCentrifugeCategory(IGuiHelper helper) {
		ResourceLocation texture = new ResourceLocation(GTClassic.MODID, "textures/gui/jeicentrifuge.png");
		this.draw = helper.createDrawable(texture, 10, 10, 78, 78);
	}

	@Override
	public IDrawable getBackground() {
		return this.draw;
	}

	@Override
	public String getModName() {
		return "gtclassic";
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUid() {
		return "centrifuge";
	}

	@Override
	public void setRecipe(IRecipeLayout layout, GTJeiCentrifugeWrapper arg1, IIngredients ingridient) {
		IGuiItemStackGroup guiItemStacks = layout.getItemStacks();
		 guiItemStacks.init(0, true, 31, 31); //input
	     guiItemStacks.init(1, true, 1, 1); //cell slot
	     guiItemStacks.init(2, false, 1, 31); //outputs
	     guiItemStacks.init(3, false, 31, 1);
	     guiItemStacks.init(4, false, 61, 31);
	     guiItemStacks.init(5, false, 31, 61);
	     guiItemStacks.set(ingridient);
		
	}

}
