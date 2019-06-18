package gtclassic.container;

import gtclassic.gui.GTGuiCompBasicString;
import gtclassic.gui.GTGuiCompEnergyStorageBar;
import gtclassic.gui.GTGuiCompProgressText;
import gtclassic.tile.GTTileMatterFabricator;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTContainerMatterFabricator extends ContainerTileComponent<GTTileMatterFabricator> {

	public static Box2D chargeProgressBox = new Box2D(8, 73, 116, 5); // where the background to cover is, and the size
	public static Vec2i chargeProgressPos = new Vec2i(0, 251); // where the overlay is located

	public GTContainerMatterFabricator(InventoryPlayer player, GTTileMatterFabricator tile) {
		super(tile);
		for (int y = 0; y < 2; ++y) {
			for (int x = 0; x < 4; ++x) {
				this.addSlotToContainer(new SlotCustom(tile, x + y * 4, 8 + x * 18, 14
						+ y * 18, CommonFilters.Anything));
			}
		}
		this.addComponent(new GTGuiCompBasicString("Matter Fabricator", 48, 4));
		this.addComponent(new GTGuiCompProgressText(tile, 122, 41));
		this.addComponent(new GTGuiCompEnergyStorageBar(tile, new Box2D(8, 60, 160, 5), new Vec2i(0, 251)));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 8, 128, 14));
		this.addPlayerInventory(player);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiLoaded(GuiIC2 gui) {
		gui.disableName();
	}

	@Override
	public ResourceLocation getTexture() {
		return this.getGuiHolder().getGuiTexture();
	}

	@Override
	public int guiInventorySize() {
		return this.getGuiHolder().slotCount;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.getGuiHolder().canInteractWith(playerIn);
	}
}