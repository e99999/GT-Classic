package gtclassic.api.pipe;

import gtclassic.common.GTLang;
import ic2.core.inventory.transport.IItemTransporter;
import ic2.core.inventory.transport.TransporterManager;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

public abstract class GTTilePipeItemBase extends GTTilePipeBase {

	public GTTilePipeItemBase(int slots) {
		super(slots);
	}

	@Override
	public LocaleComp getBlockName() {
		return GTLang.ITEM_PIPE_LANG;
	}

	@Override
	public boolean canConnect(TileEntity tile, EnumFacing dir) {
		if (tile == null) {
			return false;
		}
		if (tile instanceof GTTilePipeItemBase) {// do color stuff here
			return true;
		}
		return mode != 2 && tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, dir);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (getHandler() == null) {
				return false;
			}
			return this.handler.hasInventory(facing);
		}
		return super.hasCapability(capability, facing);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (getHandler() == null) {
				return null;
			}
			return (T) this.handler.getInventory(facing);
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public void update() {
		if (world.getTotalWorldTime() % 5 == 0) {
			if (this.getStackInSlot(0).isEmpty()) {
				return;
			}
			for (EnumFacing side : this.connection) {
				BlockPos sidePos = this.pos.offset(side);
				if (world.isBlockLoaded(sidePos) && side != lastIn) {
					TileEntity tile = world.getTileEntity(sidePos);
					if (mode != 0 && !(tile instanceof GTTilePipeItemBase)) {
						continue;
					}
					if (side == EnumFacing.UP && tile instanceof TileEntityHopper) {
						continue;
					}
					IItemTransporter slave = TransporterManager.manager.getTransporter(tile, false);
					if (slave != null) {
						for (int i = 0; i < this.inventory.size(); ++i) {
							int added = slave.addItem(this.getStackInSlot(i).copy(), side.getOpposite(), true).getCount();
							if (added > 0) {
								// GTMod.logger.info("Pipe pushed: " + added + " to " + side.toString());
								this.getStackInSlot(i).shrink(added);
								if (tile instanceof GTTilePipeItemBase) {
									((GTTilePipeItemBase) tile).lastIn = side.getOpposite();
								}
								break;
							}
						}
					}
				}
			}
		}
	}

	@Override
	public String[] debugInfo() {
		ItemStack stack = this.getStackInSlot(0).copy();
		String in = this.lastIn != null ? this.lastIn.toString() : "Null";
		String itemName = !stack.isEmpty() ? stack.getDisplayName() + " x " + stack.getCount() : "Empty";
		return new String[] { itemName, "Last In: " + in, this.info[this.mode] };
	}
}
