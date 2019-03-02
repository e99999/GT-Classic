package gtclassic.tile;

import java.util.List;

import gtclassic.container.GTContainerBloomery;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import gtclassic.material.GTMaterialGen;
import gtclassic.util.int3;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileBloomery extends TileEntityMachine implements ITickable, IHasGui {

	boolean ready = false;

	IBlockState brick = Blocks.BRICK_BLOCK.getDefaultState();
	IBlockState door = Blocks.IRON_TRAPDOOR.getDefaultState();
	IBlockState lava = Blocks.FLOWING_LAVA.getDefaultState();
	IBlockState steel = GTMaterialGen.getBlock(GTMaterial.Steel, GTMaterialFlag.BLOCK).getDefaultState();

	AxisAlignedBB recipeBB = null;

	ItemStack ironblock = new ItemStack(Blocks.IRON_BLOCK);
	ItemStack coalblock = new ItemStack(Blocks.COAL_BLOCK);

	ItemStack iron = new ItemStack(Items.IRON_INGOT, 9);
	ItemStack coal = new ItemStack(Items.COAL, 9);
	ItemStack charcoal = new ItemStack(Items.COAL, 9, 1);

	EntityItem entityIron = null;
	EntityItem entityCoal = null;

	int currentProgress = 0;
	int maxProgress = 600;

	public GTTileBloomery() {
		super(1);
		setWorld(world);
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return !this.isInvalid();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerBloomery(player.inventory, this);
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSlotAccess(AccessRule.Export, 0);
		handler.registerDefaultSlotsForSide(RotationList.ALL, 0);
		handler.registerSlotType(SlotType.Output, 0);
	}

	@Override
	public boolean hasGui(EntityPlayer arg0) {
		return true;
	}

	@Override
	public void onGuiClosed(EntityPlayer arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update() {
		/*
		 * if the bloomery is ready to work the following logic executes
		 */
		if (ready) {
			currentProgress = currentProgress + 1;
			setActive(true);
			if (entityIron != null) {
				world.removeEntity(entityIron);
			}
			if (entityCoal != null) {
				world.removeEntity(entityCoal);
			}
			int3 dir = new int3(pos, getFacing());
			setLava(dir.back(1));
			setLava(dir.up(1));
			setLava(dir.up(1));
			setLava(dir.up(1));
			// MABYEDO add a check for the lava and structure check during progress to keep
			// people from fucking with it
			if (currentProgress == maxProgress) { // TODO extend the time after testing - bear recommends 4.5 minutes
				setAir(dir.down(0));
				setAir(dir.down(1));
				setAir(dir.down(1));
				setSteel(dir.down(1));
				ready = false;
				currentProgress = 0;
				this.setActive(false);
			}
		}
	}

	public boolean canWork() {
		/*
		 * Checks the strucure then the recipe area to see if it can execute the recipe
		 * logic
		 */
		if (!getActive() && isRecipeValid() && checkStructure()) {
			ready = true;
			return true;
		}
		return false;
	}

	public boolean isEntityValid(EntityItem entity, ItemStack stack) {
		return StackUtil.isStackEqual(entity.getItem(), stack) && entity.getItem().getCount() >= stack.getCount();
	}

	public boolean isRecipeValid() {
		/*
		 * checks blocks space directly behind the tile for correct recipe stacks
		 */

		boolean ironFound = false;
		boolean coalFound = false;
		entityIron = null;
		entityCoal = null;

		recipeBB = new AxisAlignedBB(new int3(pos, getFacing()).back(1).asBlockPos());
		List<EntityItem> items = world.getEntitiesWithinAABB(EntityItem.class, recipeBB);
		for (EntityItem item : items) {

			if (isEntityValid(item, iron)) {
				entityIron = item;
				ironFound = true;
			}
			if (isEntityValid(item, ironblock)) {
				entityIron = item;
				ironFound = true;
			}
			if (isEntityValid(item, coal)) {
				entityCoal = item;
				coalFound = true;
			}
			if (isEntityValid(item, charcoal)) {
				entityCoal = item;
				coalFound = true;
			}
			if (isEntityValid(item, coalblock)) {
				entityCoal = item;
				coalFound = true;
			}
		}

		if (ironFound && coalFound) {
			return true;
		}

		if (items.isEmpty()) {
			return false;

		} else {
			return false;
		}
	}

	public boolean checkStructure() {
		/*
		 * Important to remember this structure check steps to the last position, so the
		 * next step is based on the previous pos position.
		 */

		int3 dir = new int3(getPos(), getFacing());
		if (!(isBrick(dir.left(1)) &&
		// layer -1
				isBrick(dir.down(1)) && isBrick(dir.back(1)) && isBrick(dir.back(1)) && isBrick(dir.right(1))
				&& isBrick(dir.forward(1)) && isBrick(dir.back(1)) && isBrick(dir.right(1))
				// layer 0
				&& isBrick(dir.up(1)) && isDoor(dir.forward(1)) && isBrick(dir.forward(1)) && isBrick(dir.down(1))
				&& isBrick(dir.up(1)) && isBrick(dir.left(2)) && isBrick(dir.back(1)) && isBrick(dir.back(1))
				&& isBrick(dir.right(1)) && isBrick(dir.up(1))
				// layer 1
				&& isBrick(dir.forward(2)) && isBrick(dir.left(1)) && isBrick(dir.back(1)) && isBrick(dir.back(1))
				&& isBrick(dir.right(1)) && isBrick(dir.right(1)) && isBrick(dir.forward(2)) && isBrick(dir.back(1))
				// chimney
				&& isBrick(dir.up(1)) && isBrick(dir.up(1)) && isBrick(dir.left(2)) && isBrick(dir.down(1))
				&& isBrick(dir.down(1)) && isBrick(dir.forward(1)) && isBrick(dir.right(1)) && isBrick(dir.up(1))
				&& isBrick(dir.up(1)) && isBrick(dir.back(2)) && isBrick(dir.down(1)))) {

			return false;
		}
		return true;
	}

	public boolean isBrick(int3 pos) {
		return world.getBlockState(pos.asBlockPos()) == brick;
	}

	public boolean isDoor(int3 pos) {
		return world.getBlockState(pos.asBlockPos()) == door;
	}

	public void setLava(int3 pos) {
		world.setBlockState(pos.asBlockPos(), lava);
	}

	public void setSteel(int3 pos) {
		world.setBlockState(pos.asBlockPos(), steel);
	}

	public void setAir(int3 pos) {
		world.setBlockState(pos.asBlockPos(), Blocks.AIR.getDefaultState());
	}

	public int getProgress() {
		return currentProgress;
	}

	public int getMaxProgress() {
		return maxProgress;
	}

}
