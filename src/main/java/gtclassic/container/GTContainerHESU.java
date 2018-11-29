package gtclassic.container;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import baubles.common.container.SlotBauble;
import gtclassic.tileentity.GTTileEntityHESU;
import ic2.api.classic.tile.IMachine;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.slots.SlotArmor;
import ic2.core.inventory.slots.SlotCharge;
import ic2.core.inventory.slots.SlotDischarge;
import ic2.core.inventory.slots.SlotUpgrade;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;

import static ic2.core.block.wiring.container.ContainerElectricBlock.VALID_EQUIPMENT_SLOTS;

public class GTContainerHESU extends ContainerTileComponent<GTTileEntityHESU> {
    public GTContainerHESU(InventoryPlayer player, GTTileEntityHESU tile){
        super(tile);
        this.addSlotToContainer(new SlotDischarge(tile, 2147483647, 0, 128, 14)); //battery
        this.addSlotToContainer(new SlotCharge(tile, 2147483647, 1, 128, 50));

        if (Loader.isModLoaded("baubles")) {
            this.loadBaubles(player.player);
        }

        for(int i = 0; i < 4; ++i) {
            this.addSlotToContainer(new SlotArmor(player, 3 - i, VALID_EQUIPMENT_SLOTS[i], 8, 8 + i * 18));
        }

        this.addPlayerInventory(player);
    }

    @Optional.Method(
            modid = "baubles"
    )
    public void loadBaubles(EntityPlayer player) {
        IBaublesItemHandler handler = BaublesApi.getBaublesHandler(player);
        if (handler != null) {
            int i = 0;

            for(int x = 0; x < 2; ++x) {
                for(int y = 0; y < 4 && i < 7; ++y) {
                    this.addSlotToContainer(new SlotBauble(player, handler, i, -35 + x * 19, 8 + y * 18));
                    ++i;
                }
            }

        }
    }

    @Override
    public ResourceLocation getTexture() {
        return this.getGuiHolder().getTexture();
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
