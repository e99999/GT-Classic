package gtclassic.common.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gtclassic.api.material.GTMaterialGen;
import gtclassic.common.GTLang;
import gtclassic.common.container.GTContainerTypeFilter;
import gtclassic.common.util.GTIFilters;
import gtclassic.common.util.GTIFilters.TypeFilter;
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
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Tuple;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileTypeFilter extends GTTileBufferBase implements IHasGui {

	public GTIFilters.TypeFilter filter = new TypeFilter(this);
	String currentFilter = "null";
	int currentIndex = 0;
	static final List<Tuple<String, ItemStack>> TYPE_LIST = new ArrayList<>();
	static final String NBT_FILTER = "currentFilter";
	static final String NBT_INDEX = "currentIndex";

	public GTTileTypeFilter() {
		super(10);
		this.addGuiFields(NBT_FILTER, NBT_INDEX);
		updateEntry();
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		int[] slots = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Both, slots);
		handler.registerDefaultSlotsForSide(RotationList.ALL, slots);
		handler.registerInputFilter(new GTIFilters.TypeFilter(this), slots);
		handler.registerSlotType(SlotType.Storage, slots);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.currentIndex = nbt.getInteger(NBT_INDEX);
		if (this.currentIndex > TYPE_LIST.size()) {
			this.currentIndex = 0;
		}
		this.updateEntry();
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setString(NBT_FILTER, this.currentFilter);
		nbt.setInteger(NBT_INDEX, this.currentIndex);
		return nbt;
	}

	public void nextEntry() {
		int newIndex = this.currentIndex + 1;
		if (newIndex >= TYPE_LIST.size()) {
			newIndex = 0;
		}
		this.currentIndex = newIndex;
		updateEntry();
	}

	public void updateEntry() {
		this.currentFilter = TYPE_LIST.get(this.currentIndex).getFirst();
		ItemStack key = TYPE_LIST.get(this.currentIndex).getSecond();
		this.getNetwork().updateTileGuiField(this, NBT_FILTER);
		if (key == null) {
			key = ItemStack.EMPTY;
		}
		this.setStackInSlot(9, key.copy());
	}

	@Override
	public LocaleComp getBlockName() {
		return GTLang.TYPE_FILTER;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerTypeFilter(player.inventory, this);
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

	public static void addOreDictFilter(String entry, Block display) {
		addOreDictFilter(entry, GTMaterialGen.get(display));
	}

	public static void addOreDictFilter(String entry, Item display) {
		addOreDictFilter(entry, GTMaterialGen.get(display));
	}

	public static void addOreDictFilter(String entry, ItemStack display) {
		if (entry.length() > 0 && display != null) {
			for (Tuple<String, ItemStack> tuple : TYPE_LIST) {
				if (tuple.getFirst().equals(entry)) {
					return;
				}
			}
			TYPE_LIST.add(new Tuple<String, ItemStack>(entry, display));
		}
	}

	@Override
	public void onNetworkEvent(EntityPlayer var1, int event) {
		super.onNetworkEvent(var1, event);
		if (event == 3) {
			this.nextEntry();
		}
	}

	@Override
	public void onBufferTick() {
		tryImport();
		tryExport();
	}

	private void tryImport() {
		IItemTransporter in = TransporterManager.manager.getTransporter(world.getTileEntity(getImportTilePos()), true);
		if (in == null) {
			return;
		}
		IItemTransporter out = TransporterManager.manager.getTransporter(this, true);
		int limit = out.getSizeInventory(getFacing());
		for (int i = 0; i < limit; ++i) {
			ItemStack stack = in.removeItem(this.filter, getFacing(), 64, false);
			if (stack.isEmpty()) {
				break;
			}
			ItemStack added = out.addItem(stack, getFacing().getOpposite(), true);
			if (added.getCount() <= 0) {
				break;
			}
			in.removeItem(new GTIFilters.BetterBasicItemFilter(added), getFacing(), added.getCount(), true);
		}
	}

	private void tryExport() {
		IItemTransporter slave = TransporterManager.manager.getTransporter(world.getTileEntity(getExportTilePos()), false);
		if (slave != null) {
			for (int i = 0; i < 9; ++i) {
				int added = slave.addItem(this.getStackInSlot(i).copy(), getFacing().getOpposite(), true).getCount();
				if (added > 0) {
					this.getStackInSlot(i).shrink(added);
					break;
				}
			}
		}
	}

	public String getCurrentFilter() {
		return this.currentFilter;
	}

	@Override
	public List<ItemStack> getDrops() {
		List<ItemStack> newDrops = new ArrayList<>();
		for (int i = 0; i < 9; ++i) {
			if (this.inventory.get(i).isEmpty()) {
				continue;
			}
			newDrops.add(this.inventory.get(i).copy());
		}
		return newDrops;
	}

	@Override
	public boolean isInventoryFull() {
		for (int i = 0; i < 9; ++i) {
			if (this.inventory.get(i).isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		super.getData(data);
		data.put("Current Filter: " + this.currentFilter, false);
	}
}
