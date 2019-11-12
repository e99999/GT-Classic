package gtclassic.util.jei;

import gtclassic.util.recipe.GTRecipeMultiInputList;
import net.minecraft.block.Block;

public class GTJeiEntry {
	
	private GTRecipeMultiInputList list;
	private Block catalyst;
	@SuppressWarnings("rawtypes")
	private Class gui;
	private int clickX;
	private int clickY;
	private int sizeX;
	private int sizeY;

	@SuppressWarnings("rawtypes")
	public GTJeiEntry(GTRecipeMultiInputList list, Block catalyst, Class gui, int clickX, int clickY, int sizeX,
			int sizeY) {
		this.list = list;
		this.catalyst = catalyst;
		this.gui = gui;
		this.clickX = clickX;
		this.clickY = clickY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}

	public GTRecipeMultiInputList getRecipeList() {
		return this.list;
	}

	public Block getCatalyst() {
		return this.catalyst;
	}

	@SuppressWarnings("rawtypes")
	public Class getGuiClass() {
		return this.gui;
	}

	public int getClickX() {
		return this.clickX;
	}

	public int getClickY() {
		return this.clickY;
	}

	public int getSizeX() {
		return this.sizeX;
	}

	public int getSizeY() {
		return this.sizeY;
	}
}
