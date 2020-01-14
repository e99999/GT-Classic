package gtclassic.common.tile.datanet;

import java.util.Collections;
import java.util.List;

import gtclassic.common.container.GTContainerReconstructorItem;
import gtclassic.common.util.datanet.GTDataNet.DataType;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.gui.GuiComponentContainer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileReconstructorItem extends GTTileBaseOutputNode implements IHasGui {

	/** Literally just a pointer on the network to where an output pos is **/
	public GTTileReconstructorItem() {
		super(1);
	}

	@Override
	public DataType dataType() {
		return DataType.ITEM;
	}

	@Override
	public BlockPos inventoryPos() {
		return this.getPos().offset(this.getFacing());
	}

	@Override
	public EnumFacing inventoryFacing() {
		return this.getFacing().getOpposite();
	}

	@Override
	public IFilter inventoryFilter() {
		return this.inventory.get(0).isEmpty() ? null : new BasicItemFilter(this.inventory.get(0).copy());
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return !this.isInvalid();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerReconstructorItem(player.inventory, this);
	}

	@Override
	public boolean hasGui(EntityPlayer var1) {
		return true;
	}

	@Override
	public void onGuiClosed(EntityPlayer var1) {
	}

	@Override
	public List<ItemStack> getDrops() {
		return Collections.emptyList();
	}
}
