package gtclassic.tile;

import java.util.ArrayList;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.container.GTContainerUUMAssembler;
import gtclassic.gui.GTGuiMachine.GTUUMAssemblerGui;
import gtclassic.helpers.GTHelperStack;
import gtclassic.material.GTMaterialGen;
import gtclassic.util.GTFilterUUMAssembler;
import gtclassic.util.recipe.GTRecipeMultiInputList;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;

public class GTTileUUMAssembler extends TileEntityElecMachine implements ITickable, IHasGui {

	int digitalCount;
	int currentCost;
	int amountPer;
	public static final GTRecipeMultiInputList RECIPE_LIST = new GTRecipeMultiInputList("gt.uumassembler");
	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID, "textures/gui/uumassembler.png");

	public GTTileUUMAssembler() {
		super(14, 512);
		maxEnergy = 100000;
		this.addGuiFields(new String[] { "digitalCount", "currentCost", "amountPer" });
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.digitalCount = nbt.getInteger("digitalCount");
		this.currentCost = nbt.getInteger("currentCost");
		this.amountPer = nbt.getInteger("amountPer");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("digitalCount", this.digitalCount);
		nbt.setInteger("currentCost", this.currentCost);
		nbt.setInteger("amountPer", this.amountPer);
		return nbt;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerUUMAssembler(player.inventory, this);
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer var1) {
		return GTUUMAssemblerGui.class;
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return !this.isInvalid();
	}

	@Override
	public boolean hasGui(EntityPlayer var1) {
		return true;
	}

	@Override
	public void onGuiClosed(EntityPlayer var1) {
	}

	@Override
	public void update() {
		tryInputUU();
		tryAssemble();
	}

	public void tryInputUU() {
		if (this.digitalCount < 3456 && GTHelperStack.isEqual(this.getStackInSlot(12), Ic2Items.uuMatter.copy())) {
			this.getStackInSlot(12).shrink(1);
			this.digitalCount = this.digitalCount + 1;
			this.updateGui();
		}
	}

	public void tryAssemble() {
		if (this.currentCost == 0 || this.getStackInSlot(11).isEmpty()) {
			return;
		}
		if (this.digitalCount < this.currentCost) {
			return;
		}
		// if slot 11 GTFilterUUMAssembler.getCost != 0 && hasPower{}
	}

	@Override
	public boolean supportsNotify() {
		return true;
	}

	public void updateGui() {
		this.getNetwork().updateTileGuiField(this, "digitalCount");
		this.getNetwork().updateTileGuiField(this, "currentCost");
		this.getNetwork().updateTileGuiField(this, "amountPer");
	}

	public ResourceLocation getGuiTexture() {
		return GUI_LOCATION;
	}

	public void updateCost() {
		this.currentCost = GTFilterUUMAssembler.getCost(this.getStackInSlot(11));
		this.amountPer = GTFilterUUMAssembler.getAmountPer(this.getStackInSlot(11));
		updateGui();
	}

	public int getStoredUU() {
		return this.digitalCount;
	}

	public int getCurrentCost() {
		return this.currentCost;
	}

	public int getAmountPer() {
		return this.amountPer;
	}

	public static void init() {
		addUUMAssemblerValue(9, GTMaterialGen.get(Items.DIAMOND));
		addUUMAssemblerValue(1, GTMaterialGen.get(Blocks.COBBLESTONE, 16));
		addUUMAssemblerValue(7, GTMaterialGen.getIc2(Ic2Items.iridiumOre));
	}

	public static void addUUMAssemblerValue(int uuRequired, ItemStack output) {
		addUUMAssemblerValue(new IRecipeInput[] {
				new RecipeInputItemStack(GTMaterialGen.getIc2(Ic2Items.uuMatter, uuRequired)) }, output);
	}

	private static void addUUMAssemblerValue(IRecipeInput[] inputs, ItemStack... outputs) {
		List<IRecipeInput> inlist = new ArrayList<>();
		List<ItemStack> outlist = new ArrayList<>();
		for (IRecipeInput input : inputs) {
			inlist.add(input);
		}
		for (ItemStack output : outputs) {
			outlist.add(output);
		}
		addUUMAssemblerValue(inlist, new MachineOutput(null, outlist));
	}

	static void addUUMAssemblerValue(List<IRecipeInput> input, MachineOutput output) {
		RECIPE_LIST.addRecipe(input, output, output.getAllOutputs().get(0).getUnlocalizedName(), 1);
	}

	public static void removeRecipe(String id) {
		RECIPE_LIST.removeRecipe(id);
	}

	@Override
	public List<ItemStack> getDrops() {
		List<ItemStack> list = new ArrayList<>();
		list.add(this.getStackInSlot(12));
		list.add(this.getStackInSlot(13));
		return list;
	}
}
