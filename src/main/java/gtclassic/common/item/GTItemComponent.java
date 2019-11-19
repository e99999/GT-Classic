package gtclassic.common.item;

import gtclassic.GTMod;
import gtclassic.api.item.GTItemBaseComponent;

public class GTItemComponent extends GTItemBaseComponent {

	/**
	 * Constructor for making a simple item with no action.
	 * 
	 * @param name - String name for the item
	 * @param x    - int column
	 * @param y    - int row
	 */
	public GTItemComponent(String name, int x, int y) {
		super(name, x, y, GTMod.MODID + "_items");
		setRegistryName(this.name.toLowerCase());
		setUnlocalizedName(GTMod.MODID + "." + this.name.toLowerCase());
		setCreativeTab(GTMod.creativeTabGT);
	}
}
