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
			materialBlockUtil(mat, GTMaterialFlag.CASING);
		}
		for (GTMaterial mat : GTMaterial.values()) {
			materialBlockUtil(mat, GTMaterialFlag.WALL);
		}
		for (GTMaterial mat : GTMaterial.values()) {
			materialBlockUtil(mat, GTMaterialFlag.COIL);
		}

		for (GTMaterial mat : GTMaterial.values()) {
			materialItemUtil(mat, GTMaterialFlag.PARTICLE);
		}
		for (GTMaterial mat : GTMaterial.values()) {
			materialItemUtil(mat, GTMaterialFlag.SMALLDUST);
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
		for (GTMaterial mat : GTMaterial.values()) {
			materialItemHotUtil(mat, GTMaterialFlag.HOTINGOT);
		}
		for (GTMaterial mat : GTMaterial.values()) {
			materialItemUtil(mat, GTMaterialFlag.NUGGET);
		}
		for (GTMaterial mat : GTMaterial.values()) {
			materialItemUtil(mat, GTMaterialFlag.PLATE);
		}
		for (GTMaterial mat : GTMaterial.values()) {
			materialItemUtil(mat, GTMaterialFlag.GEAR);
		}
		for (GTMaterial mat : GTMaterial.values()) {
			materialItemUtil(mat, GTMaterialFlag.STICK);
		}
		for (GTMaterial mat : GTMaterial.values()) {
			materialItemUtil(mat, GTMaterialFlag.WIRE);
		}

		// stuff that has a small amount of items
		for (GTMaterial mat : GTMaterial.values()) {
			materialItemUtil(mat, GTMaterialFlag.FOIL);
		}

		for (GTMaterial mat : GTMaterial.values()) {
			materialItemUtil(mat, GTMaterialFlag.SMALLPLATE);
		}

	}

	public static void materialItemUtil(GTMaterial mat, GTMaterialFlag flag) {
		if (mat.hasFlag(flag)) {
			itemMap.put(mat.getName() + "_" + flag.getSuffix(), new GTMaterialItem(mat, flag));
		}
	}

	public static void materialItemHotUtil(GTMaterial mat, GTMaterialFlag flag) {
		if (mat.hasFlag(GTMaterialFlag.INGOT) && mat.getTemp() > 2700) {
			itemMap.put(mat.getName() + "_" + flag.getSuffix(), new GTMaterialItemHot(mat, flag));
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

	public static ItemStack getCasing(GTMaterial mat, int count) {
		return new ItemStack(blockMap.get(mat.getName() + "_" + GTMaterialFlag.CASING.getSuffix()), count, 0);
	}

	public static ItemStack getWall(GTMaterial mat, int count) {
		return new ItemStack(blockMap.get(mat.getName() + "_" + GTMaterialFlag.WALL.getSuffix()), count, 0);
	}

	public static ItemStack getCoil(GTMaterial mat, int count) {
		return new ItemStack(blockMap.get(mat.getName() + "_" + GTMaterialFlag.COIL.getSuffix()), count, 0);
	}

	public static ItemStack getParticle(GTMaterial mat, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.PARTICLE.getSuffix()), count, 0);
	}

	public static ItemStack getSmallDust(GTMaterial mat, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.SMALLDUST.getSuffix()), count, 0);
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

	public static ItemStack getHotIngot(GTMaterial mat, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.HOTINGOT.getSuffix()), count, 0);
	}

	public static ItemStack getNugget(GTMaterial mat, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.NUGGET.getSuffix()), count, 0);
	}

	public static ItemStack getPlate(GTMaterial mat, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.PLATE.getSuffix()), count, 0);
	}

	public static ItemStack getSmallPlate(GTMaterial mat, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.SMALLPLATE.getSuffix()), count, 0);
	}

	public static ItemStack getStick(GTMaterial mat, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.STICK.getSuffix()), count, 0);
	}

	public static ItemStack getWire(GTMaterial mat, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.WIRE.getSuffix()), count, 0);
	}

	public static ItemStack getGear(GTMaterial mat, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.GEAR.getSuffix()), count, 0);
	}

	public static ItemStack getFoil(GTMaterial mat, int count) {
		return new ItemStack(itemMap.get(mat.getName() + "_" + GTMaterialFlag.FOIL.getSuffix()), count, 0);
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

	public static FluidStack getFluidTest(GTMaterial mat) {
		String name = mat.getDisplayName().toLowerCase();
		return FluidRegistry.getFluidStack(name, 1000);
	}

	public static ItemStack getModFluid(String name, int count) {
		FluidStack fluid = FluidRegistry.getFluidStack(name, 1000);
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
