package gtclassic.api.pipe;

import ic2.core.inventory.container.ContainerIC2;
import net.minecraft.entity.player.EntityPlayer;

public class GTTilePipeItem {

	public static class GTTilePipeItemSmall extends GTTilePipeItemBase {

		public GTTilePipeItemSmall() {
			super(1);
		}
		
		@Override
		public ContainerIC2 getGuiContainer(EntityPlayer player) {
			return new GTContainerPipeItem(player.inventory, this);
		}
	}
	
	public static class GTTilePipeItemLarge extends GTTilePipeItemBase {

		public GTTilePipeItemLarge() {
			super(4);
		}
		
		@Override
		public ContainerIC2 getGuiContainer(EntityPlayer player) {
			return new GTContainerPipeItemLarge(player.inventory, this);
		}
	}
}
