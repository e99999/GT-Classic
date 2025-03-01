package gtclassic.common.item;

import gtclassic.GTMod;
import gtclassic.api.item.GTItemBaseComponent;
import gtclassic.common.GTItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
		setTranslationKey(GTMod.MODID + "." + this.name.toLowerCase());
		setCreativeTab(GTMod.creativeTabGT);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean hasEffect(ItemStack stack) {
		return this == GTItems.fuelBinderMagic ? true : stack.isItemEnchanted();
	}
}
