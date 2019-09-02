package gtclassic.material;

import java.util.LinkedHashMap;

import gtclassic.GTItems;
import gtclassic.GTMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GTMaterialGen {

	public static LinkedHashMap<String, Item> itemMap = new LinkedHashMap<>();
	public static LinkedHashMap<String, Block> blockMap = new LinkedHashMap<>();

	/**
	 * Where GTClassic iterates the material list and put items on the matieral map.
	 **/
	public static void init() {
		// Add material entries and flags to correct maps
		for (GTMaterial mat : GTMaterial.values()) {
			materialBlockUtil(mat, GTMaterialFlag.BLOCKGEM);
		}
		for (GTMaterial mat : GTMaterial.values()) {
			materialBlockUtil(mat, GTMaterialFlag.BLOCKMETAL);
		}
		for (GTMaterial mat : GTMaterial.values()) {
			materialItemUtil(mat, GTMaterialFlag.DUST);
		}
		for (GTMaterial mat : GTMaterial.values()) {
			materialItemUtil(mat, GTMaterialFlag.RUBY);
		}
		for (GTMaterial mat : GTMaterial.values()) {
			// to make sure no same gem and override eachother
			if (!mat.hasFlag(GTMaterialFlag.RUBY)) {
				materialItemUtil(mat, GTMaterialFlag.SAPPHIRE);
			}
		}
		for (GTMaterial mat : GTMaterial.values()) {
			materialItemUtil(mat, GTMaterialFlag.INGOT);
		}
	}

	/**
	 * For putting an material and flag onto the item map.
	 * 
	 * @param mat  - GTMaterial to use
	 * @param flag - GTMaterialFlag to combine with the material
	 */
	public static void materialItemUtil(GTMaterial mat, GTMaterialFlag flag) {
		if (mat.hasFlag(flag)) {
			GTMod.debugLogger("Generating GregTech " + flag.getPrefix() + " :" + mat.getDisplayName());
			itemMap.put(mat.getName() + "_" + flag.getSuffix(), new GTMaterialItem(mat, flag));
		}
	}

	/**
	 * For putting an material and flag onto the block map.
	 * 
	 * @param mat  - GTMaterial to use
	 * @param flag - GTMaterialFlag to combine with the material
	 */
	public static void materialBlockUtil(GTMaterial mat, GTMaterialFlag flag) {
		if (mat.hasFlag(flag)) {
			GTMod.debugLogger("Generating GregTech " + flag.getPrefix() + " :" + mat.getDisplayName());
			blockMap.put(mat.getName() + "_" + flag.getSuffix(), new GTMaterialBlock(mat, flag));
		}
	}

	/** How to get an itemstack of any material item **/
	public static ItemStack getStack(GTMaterial mat, GTMaterialFlag flag, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + flag.getSuffix()), count, 0);
	}

	/** How to get an itemstack of any material block **/
	public static ItemStack getBlockStack(GTMaterial mat, GTMaterialFlag flag, int count) {
		return new ItemStack(blockMap.get(mat.getName() + "_" + flag.getSuffix()), count, 0);
	}

	/** How to get an item from the item map **/
	public static Item getItem(GTMaterial mat, GTMaterialFlag flag) {
		return itemMap.get(mat.getName() + "_" + flag.getSuffix());
	}

	/** How to get a block from the block map **/
	public static Block getBlock(GTMaterial mat, GTMaterialFlag flag) {
		return blockMap.get(mat.getName() + "_" + flag.getSuffix());
	}

	/** How to get a material block of any GTMaterial **/
	public static ItemStack getMaterialBlock(GTMaterial mat, int count) {
		return new ItemStack(blockMap.get(mat.getName() + "_" + GTMaterialFlag.BLOCKMETAL.getSuffix()), count, 0);
	}

	/** How to get a dust of any GTMaterial **/
	public static ItemStack getDust(GTMaterial mat, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.DUST.getSuffix()), count, 0);
	}

	/** How to get a gem of any GTMaterial **/
	public static ItemStack getGem(GTMaterial mat, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.RUBY.getSuffix()), count, 0);
	}

	/** How to get an ingot of any GTMaterial **/
	public static ItemStack getIngot(GTMaterial mat, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.INGOT.getSuffix()), count, 0);
	}

	/** How to a GTFluidTube with any GTMaterial **/
	public static ItemStack getTube(GTMaterial mat, int count) {
		String name = mat.getDisplayName().toLowerCase();
		return getModdedTube(name, count);
	}

	/** How to get a GTFluidTube of any fluid by name **/
	public static ItemStack getModdedTube(String name, int count) {
		FluidStack fluid = FluidRegistry.getFluidStack(name, 1000);
		ItemStack stack = new ItemStack(GTItems.testTube);
		IFluidHandlerItem handler = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
		handler.fill(fluid, true);
		stack.setCount(count);
		return stack;
	}

	/** How to get a GT Gas/Fluid Fluid Instance **/
	public static Fluid getFluid(GTMaterial mat) {
		return getFluidStack(mat).getFluid();
	}

	/** How to get a GT Gas/Fluid FluidStack directly of 1000mb **/
	public static FluidStack getFluidStack(GTMaterial mat) {
		return getFluidStack(mat, 1000);
	}

	/** How to get a GT Gas/Fluid FluidStack directly of a custom amount **/
	public static FluidStack getFluidStack(GTMaterial mat, int amount) {
		String name = mat.getDisplayName().toLowerCase();
		return FluidRegistry.getFluidStack(name, amount);
	}

	/** How to get a Block of GT Fluid **/
	public static Block getFluidBlock(GTMaterial mat) {
		return getFluid(mat).getBlock();
	}

	/** How to get a GTFluidTube of water easily **/
	public static ItemStack getWater(int count) {
		return getModdedTube("water", count);
	}

	/** How to get a GTFluidTube of lava easily **/
	public static ItemStack getLava(int count) {
		return getModdedTube("lava", count);
	}

	/** How to properly call an IC2 Classic ItemStack **/
	public static ItemStack getIc2(ItemStack itemStack, int count) {
		ItemStack ret = itemStack.copy();
		ret.setCount(count);
		return ret;
	}

	/** How to properly call an IC2 Classic ItemStack **/
	public static ItemStack getIc2(ItemStack itemStack) {
		return getIc2(itemStack, 1);
	}

	/**
	 * A getter for retrieving modded items.
	 * 
	 * @param modname - String, the mod name
	 * @param itemid  - String, the item by name
	 * @return ItemStack - the ItemStack requested
	 */
	public static ItemStack getModItem(String modname, String itemid) {
		String pair = modname + ":" + itemid;
		return new ItemStack(Item.getByNameOrId(pair));
	}

	/**
	 * A getter for retrieving modded items.
	 * 
	 * @param modname - String, the mod name
	 * @param itemid  - String, the item by name
	 * @param amount  - int, the count
	 * @return ItemStack - the ItemStack requested
	 */
	public static ItemStack getModItem(String modname, String itemid, int amount) {
		String pair = modname + ":" + itemid;
		return new ItemStack(Item.getByNameOrId(pair), amount);
	}

	/**
	 * A getter for retrieving modded items.
	 * 
	 * @param modname - String, the mod name
	 * @param itemid  - String, the item by name
	 * @param meta    - int, the meta value of the item
	 * @param amount  - int, the count
	 * @return ItemStack - the ItemStack requested
	 */
	public static ItemStack getModMetaItem(String modname, String itemid, int meta, int size) {
		String pair = modname + ":" + itemid;
		return GameRegistry.makeItemStack(pair, meta, size, null);
	}

	/**
	 * A getter for retrieving modded blocks.
	 * 
	 * @param modname - String, the mod name
	 * @param itemid  - String, the block by name
	 * @return ItemStack - the ItemStack requested
	 */
	public static ItemStack getModBlock(String modname, String blockid) {
		String pair = modname + ":" + blockid;
		return new ItemStack(Block.getBlockFromName(pair));
	}

	/** Generic getter for items and blocks **/
	public static ItemStack get(Item item) {
		return new ItemStack(item, 1);
	}

	/** Generic getter for items and blocks **/
	public static ItemStack get(Block block) {
		return new ItemStack(block, 1);
	}

	/** Generic getter for items and blocks **/
	public static ItemStack get(Item item, int count) {
		return new ItemStack(item, count);
	}

	/** Generic getter for items and blocks **/
	public static ItemStack get(Block block, int count) {
		return new ItemStack(block, count);
	}
}
