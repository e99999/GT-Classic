package gtclassic.common.tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import gtclassic.GTMod;
import gtclassic.api.helpers.GTHelperStack;
import gtclassic.api.recipe.GTRecipeMachineHandler;
import gtclassic.api.recipe.GTRecipeMultiInputList;
import gtclassic.api.recipe.GTRecipeMultiInputList.MultiRecipe;
import gtclassic.api.tile.GTTileBaseMachine;
import gtclassic.common.GTConfig;
import gtclassic.common.container.GTContainerDisassembler;
import gtclassic.common.gui.GTGuiMachine.GTDisassemblerGui;
import ic2.api.classic.item.IMachineUpgradeItem;
import ic2.api.classic.item.IMachineUpgradeItem.UpgradeType;
import ic2.api.classic.recipe.RecipeModifierHelpers.IRecipeModifier;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.RotationList;
import ic2.core.block.base.util.output.MultiSlotOutput;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.ArrayFilter;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.filters.MachineFilter;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2Sounds;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class GTTileDisassembler extends GTTileBaseMachine {

	protected static final int[] slotInputs = { 0 };
	protected static final int[] slotOutputs = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	int slotFuel = 10;
	public IFilter filter = new MachineFilter(this);
	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID, "textures/gui/disassembler.png");
	public static final GTRecipeMultiInputList RECIPE_LIST = new GTRecipeMultiInputList("gt.disassembler", 16);

	public GTTileDisassembler() {
		super(11, 4, 16, 100, 32);
		this.maxEnergy = 10000;
		setFuelSlot(slotFuel);
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Both, slotFuel);
		handler.registerDefaultSlotAccess(AccessRule.Import, slotInputs);
		handler.registerDefaultSlotAccess(AccessRule.Export, slotOutputs);
		handler.registerDefaultSlotsForSide(RotationList.DOWN.invert(), slotInputs);
		handler.registerDefaultSlotsForSide(RotationList.UP.invert(), slotOutputs);
		handler.registerInputFilter(filter, slotInputs);
		handler.registerInputFilter(new ArrayFilter(CommonFilters.DischargeEU, new BasicItemFilter(Items.REDSTONE), new BasicItemFilter(Ic2Items.suBattery)), slotFuel);
		handler.registerOutputFilter(CommonFilters.NotDischargeEU, slotFuel);
		handler.registerSlotType(SlotType.Fuel, slotFuel);
		handler.registerSlotType(SlotType.Input, slotInputs);
		handler.registerSlotType(SlotType.Output, slotOutputs);
	}

	@Override
	public Set<UpgradeType> getSupportedTypes() {
		return new LinkedHashSet<>(Arrays.asList(UpgradeType.values()));
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
	public int[] getInputSlots() {
		return slotInputs;
	}

	public ResourceLocation getGuiTexture() {
		return GUI_LOCATION;
	}

	@Override
	public IFilter[] getInputFilters(int[] slots) {
		return new IFilter[] { filter };
	}

	@Override
	public boolean isRecipeSlot(int slot) {
		return slot == 0;
	}

	@Override
	public int[] getOutputSlots() {
		return slotOutputs;
	}

	@Override
	public GTRecipeMultiInputList getRecipeList() {
		return RECIPE_LIST;
	}

	@Override
	public ResourceLocation getStartSoundFile() {
		return Ic2Sounds.maceratorOp;
	}

	@Override
	public void process(MultiRecipe recipe) {
		MachineOutput output = recipe.getOutputs().copy();
		for (ItemStack stack : output.getRecipeOutput(getWorld().rand, getTileData())) {
			if (world.rand.nextFloat() > .2) {
				outputs.add(new MultiSlotOutput(stack, getOutputSlots()));
				onRecipeComplete();
			}
		}
		NBTTagCompound nbt = recipe.getOutputs().getMetadata();
		boolean shiftContainers = nbt == null ? false : nbt.getBoolean(MOVE_CONTAINER_TAG);
		List<ItemStack> inputs = getInputs();
		List<IRecipeInput> recipeKeys = new LinkedList<IRecipeInput>(recipe.getInputs());
		for (Iterator<IRecipeInput> keyIter = recipeKeys.iterator(); keyIter.hasNext();) {
			IRecipeInput key = keyIter.next();
			int count = key.getAmount();
			for (Iterator<ItemStack> inputIter = inputs.iterator(); inputIter.hasNext();) {
				ItemStack input = inputIter.next();
				if (key.matches(input)) {
					if (input.getCount() >= count) {
						if (input.getItem().hasContainerItem(input)) {
							if (!shiftContainers) {
								continue;
							}
							ItemStack container = input.getItem().getContainerItem(input);
							if (!container.isEmpty()) {
								container.setCount(count);
								outputs.add(new MultiSlotOutput(container, getOutputSlots()));
							}
						}
						input.shrink(count);
						count = 0;
						if (input.isEmpty()) {
							inputIter.remove();
						}
						keyIter.remove();
						break;
					}
					if (input.getItem().hasContainerItem(input)) {
						if (!shiftContainers) {
							continue;
						}
						ItemStack container = input.getItem().getContainerItem(input);
						if (!container.isEmpty()) {
							container.setCount(input.getCount());
							outputs.add(new MultiSlotOutput(container, getOutputSlots()));
						}
					}
					count -= input.getCount();
					input.setCount(0);
					inputIter.remove();
				}
			}
		}
		addToInventory();
		if (supportsUpgrades) {
			for (int i = 0; i < upgradeSlots; i++) {
				ItemStack item = inventory.get(i + inventory.size() - upgradeSlots);
				if (item.getItem() instanceof IMachineUpgradeItem) {
					((IMachineUpgradeItem) item.getItem()).onProcessFinished(item, this);
				}
			}
		}
		shouldCheckRecipe = true;
	}

	@Override
	public void onRecipeComplete() {
		world.playSound((EntityPlayer) null, pos, SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.BLOCKS, 0.05F, 1.0F);
	}

	public static void init() {
		if (GTConfig.general.enableDisassembler) {
			for (IRecipe recipe : ForgeRegistries.RECIPES) {
				ItemStack input = recipe.getRecipeOutput().copy();
				List<ItemStack> outputList = new ArrayList<>();
				for (int i = 0; i < recipe.getIngredients().size(); ++i) {
					List<ItemStack> tempList = new ArrayList<>();
					Collections.addAll(tempList, recipe.getIngredients().get(i).getMatchingStacks());
					if (!tempList.isEmpty()) {
						ItemStack tempStack = isHighValueMaterial(tempList.get(0).copy()) ? Ic2Items.scrapMetal.copy()
								: tempList.get(0).copy();
						if (canItemBeReturned(tempStack)) {
							outputList.add(tempStack);
						}
					}
				}
				if (canInputBeUsed(input) && !outputList.isEmpty()) {
					ItemStack[] arr = outputList.toArray(new ItemStack[0]);
					addRecipe(new IRecipeInput[] { new RecipeInputItemStack(input) }, totalEu(5000), arr);
				}
			}
		}
	}

	public static IRecipeModifier[] totalEu(int total) {
		return GTRecipeMachineHandler.totalEu(RECIPE_LIST, total);
	}

	public static void addRecipe(IRecipeInput[] inputs, IRecipeModifier[] modifiers, ItemStack... outputs) {
		GTRecipeMachineHandler.addRecipe(RECIPE_LIST, inputs, modifiers, outputs);
	}

	public static boolean isHighValueMaterial(ItemStack input) {
		return GTHelperStack.matchOreDict(input, "ingotChrome") || GTHelperStack.matchOreDict(input, "ingotTitanium")
				|| GTHelperStack.matchOreDict(input, "ingotPlatinum");
	}

	public static boolean canInputBeUsed(ItemStack input) {
		return !input.isEmpty() && input.getCount() > 0 && !input.isItemDamaged()
				&& !input.getItem().hasContainerItem(input);
	}

	public static boolean canItemBeReturned(ItemStack stack) {
		return !GTHelperStack.isEqual(stack, Ic2Items.uuMatter.copy()) && !stack.isItemStackDamageable()
				&& !stack.getTranslationKey().contains("bucket");
	}
}
