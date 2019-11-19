package gtclassic.common.container;

import static ic2.core.block.wiring.container.ContainerElectricBlock.VALID_EQUIPMENT_SLOTS;

import gtclassic.GTMod;
import gtclassic.api.gui.GTGuiCompEnergyStorageBar;
import gtclassic.common.tile.GTTileChargeOMat;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.slots.SlotArmor;
import ic2.core.inventory.slots.SlotBase;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTContainerChargeOMat extends ContainerTileComponent<GTTileChargeOMat> {

	public static ResourceLocation TEXTURE = new ResourceLocation(GTMod.MODID, "textures/gui/chargeomat.png");
	public static Box2D chargeProgressBox = new Box2D(8, 60, 141, 5);
	public static Vec2i chargeProgressPos = new Vec2i(0, 251); //

	public GTContainerChargeOMat(InventoryPlayer player, GTTileChargeOMat tile) {
		super(tile);
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				this.addSlotToContainer(new SlotBase(tile, j + i * 3, 8 + j * 18, 5 + i * 18));
			}
		}
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				this.addSlotToContainer(new SlotOutput(player.player, tile, (j + i * 3) + 9, 97 + j * 18, 5 + i * 18));
			}
		}
		for (int i = 0; i < 4; ++i) {
			this.addSlotToContainer(new SlotArmor(player, 3 - i, VALID_EQUIPMENT_SLOTS[i], 152, 5 + i * 18));
		}
		this.addComponent(new GTGuiCompEnergyStorageBar(tile, chargeProgressBox, chargeProgressPos));
		this.addPlayerInventory(player);
	}

	@Override
	public ResourceLocation getTexture() {
		return TEXTURE;
	}

	@Override
	public int guiInventorySize() {
		return 18;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiLoaded(GuiIC2 gui) {
		gui.disableName();
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.getGuiHolder().canInteractWith(playerIn);
	}
}
