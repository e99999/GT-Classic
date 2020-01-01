package gtclassic.common.container;

import gtclassic.GTMod;
import gtclassic.api.gui.GTGuiCompEnergyStorageBar;
import gtclassic.common.tile.GTTileBedrockMiner;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.base.MachineProgressComp;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotDischarge;
import ic2.core.inventory.slots.SlotDisplay;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTContainerBedrockMiner extends ContainerTileComponent<GTTileBedrockMiner> {

	public static ResourceLocation TEXTURE = new ResourceLocation(GTMod.MODID, "textures/gui/bedrockminer.png");
	public static final Box2D machineProgressBox = new Box2D(78, 23, 20, 18);
	public static final Vec2i machineProgressPos = new Vec2i(176, 0);

	public GTContainerBedrockMiner(InventoryPlayer player, GTTileBedrockMiner tile) {
		super(tile);
		this.addSlotToContainer(new SlotCustom(tile, 0, 35, 25, GTTileBedrockMiner.filter));
		this.addSlotToContainer(new SlotCustom(tile, 1, 53, 25, GTTileBedrockMiner.filter));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 2, 107, 25));
		this.addSlotToContainer(new SlotOutput(player.player, tile, 3, 125, 25));
		this.addSlotToContainer(new SlotDisplay(tile, 4, 35, 62));
		this.addSlotToContainer(new SlotDischarge(tile, Integer.MAX_VALUE, 5, 8, 62));
		this.addComponent(new MachineProgressComp(tile, machineProgressBox, machineProgressPos));
		this.addComponent(new GTGuiCompEnergyStorageBar(tile, new Box2D(8, 49, 160, 5), new Vec2i(0, 251)));
		this.addPlayerInventory(player);
	}

	@Override
	public ResourceLocation getTexture() {
		return TEXTURE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onGuiLoaded(GuiIC2 gui) {
		gui.dissableInvName();
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
