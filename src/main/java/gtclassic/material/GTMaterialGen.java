package gtclassic.material;

import java.util.LinkedHashMap;

import gtclassic.GTItems;
import gtclassic.GTMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

public class GTMaterialGen {

	public static LinkedHashMap<String, Item> itemMap = new LinkedHashMap<>();
	public static LinkedHashMap<String, Block> blockMap = new LinkedHashMap<>();

	public static void init() {
		// Add material entries and flags to correct maps
		for (GTMaterial mat : GTMaterial.values()) {
			materialBlockUtil(mat, GTMaterialFlag.BLOCK);
		}
		for (GTMaterial mat : GTMaterial.values()) {
			materialItemUtil(mat, GTMaterialFlag.DUST);
		}
		for (GTMaterial mat : GTMaterial.values()) {
			materialItemUtil(mat, GTMaterialFlag.GEM);
		}
		for (GTMaterial mat : GTMaterial.values()) {
			materialItemUtil(mat, GTMaterialFlag.INGOT);
		}
	}

	public static void materialItemUtil(GTMaterial mat, GTMaterialFlag flag) {
		if (mat.hasFlag(flag)) {
			itemMap.put(mat.getName() + "_" + flag.getSuffix(), new GTMaterialItem(mat, flag));
		}
	}

	public static void materialBlockUtil(GTMaterial mat, GTMaterialFlag flag) {
		if (mat.hasFlag(flag)) {
			blockMap.put(mat.getName() + "_" + flag.getSuffix(), new GTMaterialBlock(mat, flag));
		}
	}

	public static void localizationUtil(GTMaterial mat, GTMaterialFlag flag) {
		GTMod.logger.info("item.gtclassic." + mat.getName() + flag.getSuffix() + ".name=" + "_REP0_"
				+ mat.getDisplayName() + "_REP1_");
	}

	// How to get an itemstack of any material
	public static ItemStack getStack(GTMaterial mat, GTMaterialFlag flag, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + flag.getSuffix()), count, 0);
	}

	// How to get an itemstack of any block material
	public static ItemStack getBlockStack(GTMaterial mat, GTMaterialFlag flag, int count) {
		return new ItemStack(blockMap.get(mat.getName() + "_" + flag.getSuffix()), count, 0);
	}

	// How to get an item for instances that require an item
	public static Item getItem(GTMaterial mat, GTMaterialFlag flag) {
		return itemMap.get(mat.getName() + "_" + flag.getSuffix());
	}

	// How to get block for instances that require a block
	public static Block getBlock(GTMaterial mat, GTMaterialFlag flag) {
		return blockMap.get(mat.getName() + "_" + flag.getSuffix());
	}

	// Instances per flag, most of the mod will references these
	public static ItemStack getMaterialBlock(GTMaterial mat, int count) {
		return new ItemStack(blockMap.get(mat.getName() + "_" + GTMaterialFlag.BLOCK.getSuffix()), count, 0);
	}

	public static ItemStack getDust(GTMaterial mat, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.DUST.getSuffix()), count, 0);
	}

	public static ItemStack getGem(GTMaterial mat, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.GEM.getSuffix()), count, 0);
	}

	public static ItemStack getIngot(GTMaterial mat, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.INGOT.getSuffix()), count, 0);
	}

	public static ItemStack getFluid(GTMaterial mat, int count) {
		String name = mat.getDisplayName().toLowerCase();
		FluidStack fluid = FluidRegistry.getFluidStack(name, 1000);
		ItemStack stack = new ItemStack(GTItems.testTube);
		IFluidHandlerItem handler = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
		handler.fill(fluid, true);
		stack.setCount(count);
		return stack;
	}

	public static ItemStack getModFluid(String name, int count) {
		FluidStack fluid = FluidRegistry.getFluidStack(name, 1000);
		ItemStack stack = new ItemStack(GTItems.testTube);
		IFluidHandlerItem handler = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
		handler.fill(fluid, true);
		stack.setCount(count);
		return stack;
	}

	public static ItemStack getWater(int count) {
		FluidStack fluid = FluidRegistry.getFluidStack("water", 1000);
		ItemStack stack = new ItemStack(GTItems.testTube);
		IFluidHandlerItem handler = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
		handler.fill(fluid, true);
		stack.setCount(count);
		return stack;
	}

	public static ItemStack getLava(int count) {
		FluidStack fluid = FluidRegistry.getFluidStack("lava", 1000);
		ItemStack stack = new ItemStack(GTItems.testTube);
		IFluidHandlerItem handler = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
		handler.fill(fluid, true);
		stack.setCount(count);
		return stack;
	}

	public static ItemStack getIc2(ItemStack itemStack, int count) {
		ItemStack ret = itemStack.copy();
		ret.setCount(count);
		return ret;
	}

	public static ItemStack get(Item item) {
		return new ItemStack(item, 1);
	}

	public static ItemStack get(Block block) {
		return new ItemStack(block, 1);
	}

	public static ItemStack get(Item item, int count) {
		return new ItemStack(item, count);
	}

	public static ItemStack get(Block block, int count) {
		return new ItemStack(block, count);
	}
}
