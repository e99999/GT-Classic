package gtclassic.common.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gtclassic.api.helpers.GTUtility;
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
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileTypeFilter extends GTTileBufferBase implements IHasGui {

	public GTIFilters.TypeFilter filter = new TypeFilter(this);
	String currentFilter;
	int currentIndex = 0;
	static final List<String> FILTER_LIST = new ArrayList<>();
	static final String NBT_FILTER = "currentFilter";
	static final String NBT_INDEX = "currentIndex";

	public GTTileTypeFilter() {
		super(9);
		this.addGuiFields(NBT_FILTER, NBT_INDEX);
		this.hasInvertFilter = true;
		if (currentFilter == null) {
			this.updateEntry();
		}
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
		this.currentFilter = nbt.getString(NBT_FILTER);
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
		if (newIndex >= FILTER_LIST.size()) {
			newIndex = 0;
		}
		this.currentIndex = newIndex;
		updateEntry();
	}

	public void updateEntry() {
		this.currentFilter = FILTER_LIST.get(this.currentIndex);
		this.getNetwork().updateTileGuiField(this, NBT_FILTER);
		this.getNetwork().updateTileGuiField(this, NBT_INDEX);
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

	public static void addOreDictFilter(String... entries) {
		for (String entry : entries) {
			addOreDictFilter(entry);
		}
	}

	public static void addOreDictFilter(String entry) {
		if (entry.length() > 0 && !entry.equals("empty") && !FILTER_LIST.contains(entry)) {
			FILTER_LIST.add(entry);
		}
	}

	@Override
	public void onNetworkEvent(EntityPlayer var1, int event) {
		super.onNetworkEvent(var1, event);
		if (event == 5) {
			this.nextEntry();
		}
	}

	@Override
	public void onBufferTick() {
		GTUtility.exportFromMachineToSide(this, this.getFacing());
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

	public String getCurrentFilter() {
		return this.currentFilter;
	}

	public int getCurrentIndex() {
		return this.currentIndex;
	}

	public static int getFilterListSize() {
		return FILTER_LIST.size();
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		super.getData(data);
		data.put("Current Filter: " + this.currentFilter, false);
	}
}
