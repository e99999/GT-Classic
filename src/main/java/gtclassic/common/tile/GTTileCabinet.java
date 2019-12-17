package gtclassic.common.tile;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import gtclassic.api.helpers.GTHelperStack;
import gtclassic.api.interfaces.IGTItemContainerTile;
import gtclassic.api.interfaces.IGTRecolorableStorageTile;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.common.GTBlocks;
import gtclassic.common.GTLang;
import gtclassic.common.container.GTContainerCabinet;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.network.INetworkClientTileEntityEventListener;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.util.math.MathUtil;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileCabinet extends TileEntityMachine
		implements IHasGui, INetworkClientTileEntityEventListener, IGTRecolorableStorageTile, IGTItemContainerTile {

	@NetworkField(index = 9)
	public int color;

	public GTTileCabinet() {
		super(54);
		this.color = 16383998;
		this.addNetworkFields(new String[] { "color" });
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.color = nbt.getInteger("color");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("color", this.color);
		return nbt;
	}

	@Override
	public LocaleComp getBlockName() {
		return GTLang.CABINET;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerCabinet(player.inventory, this);
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		int[] array = MathUtil.fromTo(0, 54);
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Both, array);
		handler.registerDefaultSlotsForSide(RotationList.ALL, array);
		handler.registerSlotType(SlotType.Storage, array);
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
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing() && facing.getAxis().isHorizontal();
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	public void onNetworkEvent(EntityPlayer arg0, int event) {
		if (event == 1) {
			GTHelperStack.tryCondenseInventory(this, 0, this.inventory.size());
		}
	}

	@Override
	public void onNetworkUpdate(String field) {
		if (field.equals("color")) {
			this.world.markBlockRangeForRenderUpdate(this.getPos(), this.getPos());
		}
		super.onNetworkUpdate(field);
	}

	@Override
	public void setTileColor(int color) {
		this.color = color;
	}

	@Override
	public Color getTileColor() {
		return new Color(this.color);
	}

	@Override
	public boolean isColored() {
		return this.color != 16383998;
	}

	@Override
	public List<ItemStack> getDrops() {
		List<ItemStack> drops = new ArrayList<>();
		ItemStack block = GTMaterialGen.get(GTBlocks.tileCabinet);
		if (this.isColored()) {
			NBTTagCompound nbt = StackUtil.getOrCreateNbtData(block);
			nbt.setInteger("color", this.color);
		}
		drops.addAll(getInventoryDrops());
		drops.add(block);
		return drops;
	}

	@Override
	public List<ItemStack> getInventoryDrops() {
		List<ItemStack> drops = new ArrayList<>();
		for (ItemStack stack : this.inventory) {
			if (!stack.isEmpty()) {
				drops.add(stack);
			}
		}
		return drops;
	}
}
