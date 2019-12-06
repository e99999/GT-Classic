package gtclassic.common.item;

import gtclassic.api.helpers.GTHelperStack;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.api.pipe.GTTilePipeFluidBase;
import gtclassic.common.GTItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.ItemHandlerHelper;

public class GTItemPlunger extends GTItemComponent {

	public GTItemPlunger() {
		super("plunger", 4, 2);
		this.setMaxDamage(63);
		this.setNoRepair();
		this.maxStackSize = 1;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		ItemStack copy = itemStack.copy();
		return copy.attemptDamageItem(1, itemRand, null) ? ItemStack.EMPTY : copy;
	}

	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX,
			float hitY, float hitZ, EnumHand hand) {
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity instanceof GTTilePipeFluidBase) {
			GTTilePipeFluidBase pipe = (GTTilePipeFluidBase) tileEntity;
			FluidStack fluid = pipe.getTankInstance().getFluid();
			if (fluid != null) {
				if (fluid.amount >= 1000) {
					for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
						if (GTHelperStack.isEqual(GTMaterialGen.get(GTItems.testTube), player.inventory.getStackInSlot(i))) {
							player.inventory.getStackInSlot(i).shrink(1);
							ItemHandlerHelper.giveItemToPlayer(player, GTMaterialGen.getModdedTube(fluid.getFluid().getName(), 1));
						}
					}
				}
				// Do all this below no matter what
				pipe.getTankInstance().drain(1000, true);
				world.playSound(null, player.getPosition(), SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, 1.0F, 1.0F);
				player.getHeldItem(hand).damageItem(1, player);
				return EnumActionResult.SUCCESS;
			}
		}
		return EnumActionResult.FAIL;
	}
}
