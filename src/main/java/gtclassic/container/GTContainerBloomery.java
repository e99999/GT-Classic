package gtclassic.container;

import gtclassic.GTMod;
import gtclassic.gui.GTGuiCompVerticalProgress;
import gtclassic.tile.multi.GTTileMultiBloomery;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GTContainerBloomery extends ContainerTileComponent<GTTileMultiBloomery> {
	public static ResourceLocation TEXTURE = new ResourceLocation(GTMod.MODID, "textures/gui/bloomery.png");

	public static Box2D machineProgressBoxRight = new Box2D(79, 15, 18, 18);
	public static Vec2i machineProgressPosRight = new Vec2i(176, 0);

	public GTContainerBloomery(InventoryPlayer player, GTTileMultiBloomery tile) {
		super(tile);
		this.addSlotToContainer(new SlotOutput(player.player, tile, 0, 80, 37));
		this.addPlayerInventory(player, 0, 0);
		this.addComponent(new GTGuiCompVerticalProgress(tile, GTContainerBloomery.machineProgressBoxRight,
				GTContainerBloomery.machineProgressPosRight));
	}

	@Override
	public ResourceLocation getTexture() {
		return TEXTURE;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return getGuiHolder().canInteractWith(player);
	}

	@Override
	public int guiInventorySize() {
		return 1;
	}

}
