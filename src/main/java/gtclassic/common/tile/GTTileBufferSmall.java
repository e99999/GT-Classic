package gtclassic.common.tile;

import gtclassic.common.GTLang;
import gtclassic.common.container.GTContainerBufferSmall;
import ic2.core.RotationList;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.inventory.transport.IItemTransporter;
import ic2.core.inventory.transport.TransporterManager;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileBufferSmall extends GTTileBufferBase implements IHasGui {

	public GTTileBufferSmall() {
		super(1);
	}

	@Override
	public LocaleComp getBlockName() {
		return GTLang.BUFFER_SMALL;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerBufferSmall(player.inventory, this);
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Both, 0);
		handler.registerDefaultSlotsForSide(RotationList.ALL, 0);
		handler.registerSlotType(SlotType.Storage, 0);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return !this.isInvalid();
	}

	@Override
	public void onGuiClosed(EntityPlayer entityPlayer) {
		// needed for construction
	}

	@Override
	public boolean hasGui(EntityPlayer player) {
		return true;
	}

	@Override
	public void onBufferTick() {
		IItemTransporter slave = TransporterManager.manager.getTransporter(world.getTileEntity(getExportTilePos()), false);
		if (slave != null) {
			for (int i = 0; i < this.inventory.size(); ++i) {
				int added = slave.addItem(this.getStackInSlot(i).copy(), getFacing().getOpposite(), true).getCount();
				if (added > 0) {
					this.getStackInSlot(i).shrink(added);
				}
			}
		}
	}

	@Override
	public boolean isInventoryFull() {
		return !this.inventory.get(0).isEmpty();
	}
}
