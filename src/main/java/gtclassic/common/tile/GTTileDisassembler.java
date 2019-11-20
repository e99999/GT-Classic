package gtclassic.common.tile;

import java.util.ArrayList;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.api.helpers.GTHelperStack;
import gtclassic.common.container.GTContainerDisassembler;
import gtclassic.common.gui.GTGuiMachine.GTDisassemblerGui;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.IAdvRecipe;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.api.classic.recipe.crafting.IRecipeObject;
import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.block.base.util.info.ProgressInfo;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;

public class GTTileDisassembler extends TileEntityElecMachine implements ITickable, IProgressMachine, IHasGui {

	protected static final int[] slotInputs = { 0 };
	protected static final int[] slotOutputs = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID, "textures/gui/disassembler.png");
	@NetworkField(index = 7)
	float progress = 0;

	public GTTileDisassembler() {
		super(11, 32);
		maxEnergy = 2000;
		addGuiFields("progress");
		addInfos(new ProgressInfo(this));
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return !this.isInvalid();
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer var1) {
		return GTDisassemblerGui.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer var1) {
		return new GTContainerDisassembler(var1.inventory, this);
	}

	@Override
	public boolean hasGui(EntityPlayer var1) {
		return true;
	}

	@Override
	public void onGuiClosed(EntityPlayer var1) {
	}

	@Override
	public float getMaxProgress() {
		return 1000;
	}

	@Override
	public float getProgress() {
		return this.progress;
	}

	@Override
	public void update() {
		if (world.getTotalWorldTime() % 20 == 0) {
			ICraftingRecipeList recipes = ClassicRecipes.advCrafting;
			for (IAdvRecipe currentRecipe : recipes.getRecipes()) {
				List<IRecipeObject> ingredients = currentRecipe.getRecipeInput();
				IRecipe recipe = (IRecipe) currentRecipe;
				ItemStack craftingOutput = recipe.getRecipeOutput().copy();
				if (!currentRecipe.isInvisible()) {
					//TODO check meta but not nbt
					if (GTHelperStack.isEqual(craftingOutput, this.getStackInSlot(0)) && this.getStackInSlot(0).getCount() >= craftingOutput.getCount()) {
						List<ItemStack> outputList = new ArrayList<>();
						for (int i = 0; i < ingredients.size(); ++i) {
							outputList.add(ingredients.get(i).getItems().get(0).copy());
						}
						for (int j = 0; j < outputList.size(); ++j) {
							this.setStackInSlot(j + 1, outputList.get(j).copy());
						}
						this.getStackInSlot(0).shrink(craftingOutput.getCount());
						return;
					}
				}
			}
		}
	}

	@Override
	public boolean supportsNotify() {
		return true;
	}

	public ResourceLocation getGuiTexture() {
		return GUI_LOCATION;
	}
}
