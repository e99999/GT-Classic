package gtclassic.util.jei;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
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
		ResourceLocation texture = new ResourceLocation("ic2", "textures/guiSprites/GUIJEI.png");
		this.draw = helper.createDrawable(texture, 10, 10, 80, 30);
		this.slot = helper.createDrawable(texture, 176, 0, 70, 70); //input
		this.slot = helper.createDrawable(texture, 176, 0, 70, 80); //tube
		this.slot = helper.createDrawable(texture, 176, 0, 24, 60); //outputs
		this.slot = helper.createDrawable(texture, 176, 0, 45, 20);
		this.slot = helper.createDrawable(texture, 176, 0, 112, 20);
		this.slot = helper.createDrawable(texture, 176, 0, 125, 60);
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
	public void setRecipe(IRecipeLayout arg0, GTJeiCentrifugeWrapper arg1, IIngredients arg2) {
		// TODO Auto-generated method stub
		
	}

}
