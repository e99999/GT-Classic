package gtclassic.tile;

import java.util.ArrayList;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.container.GTContainerUUMAssembler;
import gtclassic.gui.GTGuiMachine.GTUUMAssemblerGui;
import gtclassic.util.recipe.GTRecipeMultiInputList;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;

public class GTTileUUMAssembler extends TileEntityElecMachine implements ITickable, IHasGui {

	public static final GTRecipeMultiInputList RECIPE_LIST = new GTRecipeMultiInputList("gt.uumassembler");
	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID, "textures/gui/uumassembler.png");

	public GTTileUUMAssembler() {
		super(14, 512);
		maxEnergy = 100000;
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
		// TODO Everything
	}

	@Override
	public boolean supportsNotify() {
		return true;
	}

	public ResourceLocation getGuiTexture() {
		return GUI_LOCATION;
	}

	public void updateCost() {
	}

	@Override
	public List<ItemStack> getDrops() {
		List<ItemStack> list = new ArrayList<>();
		list.add(this.getStackInSlot(12));
		list.add(this.getStackInSlot(13));
		return list;
	}
}
