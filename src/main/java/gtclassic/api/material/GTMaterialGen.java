package gtclassic.api.material;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import gtclassic.api.fluid.GTFluid;
import gtclassic.api.fluid.GTFluidHandler;
import gtclassic.api.pipe.GTBlockPipeFluid;
import gtclassic.api.pipe.GTBlockPipeItem;
import gtclassic.api.pipe.GTHelperPipes;
import gtclassic.api.pipe.GTHelperPipes.GTPipeFluidCapacity;
import gtclassic.api.pipe.GTHelperPipes.GTPipeModel;
import gtclassic.common.GTItems;
import ic2.api.classic.recipe.ClassicRecipes;
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
	private static List<GTMaterialFlag> blockFlags = new ArrayList<>();
	private static List<GTMaterialFlag> pipeFlags = new ArrayList<>();
	private static List<GTMaterialFlag> itemFlags = new ArrayList<>();
	private static List<GTMaterialFlag> fluidFlags = new ArrayList<>();

	/**
	 * Initializing flags to generate here before the material map is iterated
	 **/
	public static void initFlags() {
		blockFlags.add(GTMaterialFlag.BLOCKGEM);
		blockFlags.add(GTMaterialFlag.BLOCKMETAL);
		pipeFlags.add(GTMaterialFlag.PIPEITEM);
		pipeFlags.add(GTMaterialFlag.PIPEFLUID);
		itemFlags.add(GTMaterialFlag.DUST);
		itemFlags.add(GTMaterialFlag.RUBY);
		itemFlags.add(GTMaterialFlag.SAPPHIRE);
		itemFlags.add(GTMaterialFlag.INGOT);
		itemFlags.add(GTMaterialFlag.INGOTHOT);
		fluidFlags.add(GTMaterialFlag.GAS);
		fluidFlags.add(GTMaterialFlag.FLUID);
		fluidFlags.add(GTMaterialFlag.MAGICDYE);
	}

	/**
	 * Where GTClassic iterates the material list and put items on the matieral map.
	 **/
	public static void init() {
		for (GTMaterialFlag blockFlag : blockFlags) {
			for (GTMaterial mat : GTMaterial.values()) {
				materialBlockUtil(mat, blockFlag);
			}
		}
		for (GTMaterialFlag pipeFlag : pipeFlags) {
			for (GTMaterial mat : GTMaterial.values()) {
				materialPipeUtil(mat, pipeFlag);
			}
		}
		for (GTMaterialFlag itemFlag : itemFlags) {
			for (GTMaterial mat : GTMaterial.values()) {
				materialItemUtil(mat, itemFlag);
			}
		}
		for (GTMaterialFlag fluidFlag : fluidFlags) {
			for (GTMaterial mat : GTMaterial.values()) {
				materialFluidUtil(mat, fluidFlag);
			}
		}
	}

	public static void postInitProperities() {
		for (Fluid entry : ClassicRecipes.fluidGenerator.getBurnMap().keySet()) {
			GTFluidHandler.addBurnableToolTip(entry);
		}
	}

	/**
	 * Add your own flag to the block flag list
	 * 
	 * @param flag
	 */
	public static void addBlockFlag(GTMaterialFlag flag) {
		blockFlags.add(flag);
	}

	/**
	 * Add your own flag to the item flag list
	 * 
	 * @param flag
	 */
	public static void addItemFlag(GTMaterialFlag flag) {
		itemFlags.add(flag);
	}

	/**
	 * Add your own flag to the fluid flag list
	 * 
	 * @param flag
	 */
	public static void addFluidFlag(GTMaterialFlag flag) {
		fluidFlags.add(flag);
	}

	/**
	 * returns an unmodifiable version of the item flags list
	 * 
	 * @return
	 */
	public static List<GTMaterialFlag> getItemFlagList() {
		return Collections.unmodifiableList(itemFlags);
	}

	/**
	 * returns an unmodifiable version of the blocks flags list
	 * 
	 * @return
	 */
	public static List<GTMaterialFlag> getBlockFlagList() {
		return Collections.unmodifiableList(blockFlags);
	}

	/**
	 * returns an unmodifiable version of the fluid flags list
	 * 
	 * @return
	 */
	public static List<GTMaterialFlag> getFluidFlagList() {
		return Collections.unmodifiableList(fluidFlags);
	}

	/**
	 * For putting an material and flag onto the item map.
	 * 
	 * @param mat  - GTMaterial to use
	 * @param flag - GTMaterialFlag to combine with the material
	 */
	public static void materialItemUtil(GTMaterial mat, GTMaterialFlag flag) {
		if (flag == GTMaterialFlag.INGOTHOT && mat.hasFlag(GTMaterialFlag.INGOTHOT)) {
			itemMap.put(mat.getName() + "_" + flag.getSuffix(), new GTMaterialItemHot(mat));
		} else if (mat.hasFlag(flag)) {
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
			blockMap.put(mat.getName() + "_" + flag.getSuffix(), new GTMaterialBlock(mat, flag));
		}
	}

	public static void materialPipeUtil(GTMaterial mat, GTMaterialFlag flag) {
		if (mat.hasFlag(GTMaterialFlag.PIPEITEM) && flag.equals(GTMaterialFlag.PIPEITEM)) {
			blockMap.put(mat.getName() + "_"
					+ GTMaterialFlag.PIPEITEM.getSuffix(), new GTBlockPipeItem(mat, GTPipeModel.MED));
			blockMap.put(mat.getName() + "_" + GTMaterialFlag.PIPEITEM.getSuffix()
					+ "_large", new GTBlockPipeItem(mat, GTPipeModel.LARGE));
		}
		if (mat.hasFlag(GTMaterialFlag.PIPEFLUID) && flag.equals(GTMaterialFlag.PIPEFLUID)) {
			GTPipeFluidCapacity[] capacity = GTHelperPipes.getPipeCapacityFromTier(mat.getTier());
			blockMap.put(mat.getName() + "_" + GTMaterialFlag.PIPEFLUID.getSuffix()
					+ "_small", new GTBlockPipeFluid(mat, GTPipeModel.SMALL, capacity[0]));
			blockMap.put(mat.getName() + "_"
					+ GTMaterialFlag.PIPEFLUID.getSuffix(), new GTBlockPipeFluid(mat, GTPipeModel.MED, capacity[1]));
			blockMap.put(mat.getName() + "_" + GTMaterialFlag.PIPEFLUID.getSuffix()
					+ "_large", new GTBlockPipeFluid(mat, GTPipeModel.LARGE, capacity[2]));
		}
	}

	/**
	 * For creating a fluid from a material directly.
	 * 
	 * @param mat  - GTMaterial to use
	 * @param flag - GTMaterialFlag to combine with the material
	 */
	public static void materialFluidUtil(GTMaterial mat, GTMaterialFlag flag) {
		if (mat.hasFlag(flag)) {
			FluidRegistry.registerFluid(new GTFluid(mat, flag));
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

	/** How to get a hot ingot of any GTMaterial **/
	public static ItemStack getHotIngot(GTMaterial mat, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.INGOTHOT.getSuffix()), count, 0);
	}

	public static ItemStack getItemPipe(GTMaterial mat, int count) {
		return new ItemStack(blockMap.get(mat.getName() + "_" + GTMaterialFlag.PIPEITEM.getSuffix()), count, 0);
	}

	public static ItemStack getItemPipeLarge(GTMaterial mat, int count) {
		return new ItemStack(blockMap.get(mat.getName() + "_" + GTMaterialFlag.PIPEITEM.getSuffix()
				+ "_large"), count, 0);
	}

	public static ItemStack getFluidPipeSmall(GTMaterial mat, int count) {
		return new ItemStack(blockMap.get(mat.getName() + "_" + GTMaterialFlag.PIPEFLUID.getSuffix()
				+ "_small"), count, 0);
	}

	public static ItemStack getFluidPipe(GTMaterial mat, int count) {
		return new ItemStack(blockMap.get(mat.getName() + "_" + GTMaterialFlag.PIPEFLUID.getSuffix()), count, 0);
	}

	public static ItemStack getFluidPipeLarge(GTMaterial mat, int count) {
		return new ItemStack(blockMap.get(mat.getName() + "_" + GTMaterialFlag.PIPEFLUID.getSuffix()
				+ "_large"), count, 0);
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
	 * @param count   - int, the count
	 * @return ItemStack - the ItemStack requested
	 */
	public static ItemStack getModMetaItem(String modname, String itemid, int meta, int count) {
		String pair = modname + ":" + itemid;
		return GameRegistry.makeItemStack(pair, meta, count, null);
	}

	/**
	 * A getter for retrieving modded blocks.
	 * 
	 * @param modname - String, the mod name
	 * @param blockid - String, the block by name
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

	/** Generic getter for items and blocks with meta **/
	public static ItemStack get(Item item, int count, int meta) {
		return new ItemStack(item, count, meta);
	}

	/** Generic getter for items and blocks **/
	public static ItemStack get(Block block, int count) {
		return new ItemStack(block, count);
	}

	/** Generic getter for items and blocks with meta **/
	public static ItemStack get(Block block, int count, int meta) {
		return new ItemStack(block, count, meta);
	}

	/**
	 * A helper method to check if one material equals another by using the material
	 * names, thereby avoiding problems casued by material overrides.
	 *
	 * @param mat1 - GTMaterial, the material being checked.
	 * @param mat2 - GTMaterial, the material being checked against.
	 * @return boolean - whether the 2 materials are equal.
	 */
	public static boolean isMaterialEqual(GTMaterial mat1, GTMaterial mat2) {
		return mat1.getDisplayName().equals(mat2.getDisplayName());
	}
}
