package gtclassic.container;

import gtclassic.GTMod;
import gtclassic.gui.GTGuiCompVerticalProgress;
import gtclassic.tile.GTTileSluice;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.components.base.MachineProgressComp;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GTContainerSluice extends ContainerTileComponent<GTTileSluice> {
	public static ResourceLocation TEXTURE = new ResourceLocation(GTMod.MODID, "textures/gui/sluice.png");

	public static Box2D machineProgressBoxRight = new Box2D(79, 15, 18, 18);
	public static Vec2i machineProgressPosRight = new Vec2i(176, 0);

	public GTContainerSluice(InventoryPlayer player, GTTileSluice tile) {
		super(tile);
		this.addSlotToContainer(new SlotCustom(tile, 0, 36, 35, null));
		
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				this.addSlotToContainer(new SlotOutput(player.player, tile, (j + i * 3)+1, 94+ j * 18, 17 + i * 18));
			}
		}
		
		this.addPlayerInventory(player, 0, 0);
		this.addComponent(new MachineProgressComp(tile, GTContainerSluice.machineProgressBoxRight,
				GTContainerSluice.machineProgressPosRight));
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
