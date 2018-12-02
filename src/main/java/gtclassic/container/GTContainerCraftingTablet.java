package gtclassic.container;

import gtclassic.GTClassic;
import gtclassic.items.inventory.GTInventoryCraftingTablet;
import ic2.core.inventory.container.ContainerItemComponent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTContainerCraftingTablet extends ContainerItemComponent<GTInventoryCraftingTablet> {
	
	public static ResourceLocation TEXTURE = new ResourceLocation(GTClassic.MODID, "textures/gui/craftingtablet.png");
	
	public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
    public InventoryCraftResult craftResult = new InventoryCraftResult();

    private final World world;
    private final BlockPos pos;
    private final EntityPlayer player;
	
	public GTContainerCraftingTablet(GTInventoryCraftingTablet inv, int id, InventoryPlayer player) 
	{
		super(inv, id);
		this.world = player.player.getEntityWorld();
        this.pos = player.player.getPosition();
        this.player = player.player;
        this.addSlotToContainer(new SlotCrafting(this.player, this.craftMatrix, this.craftResult, 0, 124, 35));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {
                this.addSlotToContainer(new Slot(this.craftMatrix, j + i * 3, 30 + j * 18, 17 + i * 18));
            }      
        }

		this.addPlayerInventory(player, 0, 0);
	}
	
	@Override
	public void onCraftMatrixChanged(IInventory inventoryIn)
    {
        this.slotChangedCraftingGrid(this.world, this.player, this.craftMatrix, this.craftResult);
    }
	
	@Override
	public void onContainerClosed(EntityPlayer playerIn)
    {
        super.onContainerClosed(playerIn);

        if (!this.world.isRemote)
        {
            this.clearContainer(playerIn, this.world, this.craftMatrix);
        }
    }
	
	@Override
	public ResourceLocation getTexture() {
		return TEXTURE;
	}

	@Override
    public boolean canInteractWith(EntityPlayer player)
    {
		return getGuiHolder().canInteractWith(player);
    }
}
