package gtclassic.container;

import static ic2.core.block.wiring.container.ContainerElectricBlock.VALID_EQUIPMENT_SLOTS;

import gtclassic.GTMod;
import gtclassic.gui.GTGuiCompEnergyBar;
import gtclassic.gui.GTGuiCompEnergyStorage;
import gtclassic.tile.GTTileQuantumEnergyStorage;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.slots.SlotArmor;
import ic2.core.inventory.slots.SlotCharge;
import ic2.core.inventory.slots.SlotDischarge;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTContainerQuantumEnergyStorage extends ContainerTileComponent<GTTileQuantumEnergyStorage> {

	public static ResourceLocation TEXTURE = new ResourceLocation(GTMod.MODID, "textures/gui/energystorage.png");

	public static Box2D chargeProgressBox = new Box2D(8, 73, 116, 5); // where the background to cover is, and the size
																		// xy
	public static Vec2i chargeProgressPos = new Vec2i(0, 251); // where the overlay is located

	public GTContainerQuantumEnergyStorage(InventoryPlayer player, GTTileQuantumEnergyStorage tile) {

		super(tile);
		this.addSlotToContainer(new SlotDischarge(tile, tile.tier, 1, 128, 50));
		this.addSlotToContainer(new SlotCharge(tile, tile.tier, 0, 128, 14));

		for (int i = 0; i < 4; ++i) {
			this.addSlotToContainer(new SlotArmor(player, 3 - i, VALID_EQUIPMENT_SLOTS[i], 152, 5 + i * 18));
		}

		this.addComponent(new GTGuiCompEnergyStorage(tile));
		this.addComponent(new GTGuiCompEnergyBar(tile, GTContainerQuantumEnergyStorage.chargeProgressBox, GTContainerQuantumEnergyStorage.chargeProgressPos));
		this.addPlayerInventory(player);
	}

	@Override
	public ResourceLocation getTexture() {
		return TEXTURE;
	}

	@Override
	public int guiInventorySize() {
		return 2;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiLoaded(GuiIC2 gui) {
		gui.disableName();
		gui.dissableInvName();
	}

	@Override
	protected boolean moveIntoInventoryInverted() {
		return false;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.getGuiHolder().canInteractWith(playerIn);
	}
}
