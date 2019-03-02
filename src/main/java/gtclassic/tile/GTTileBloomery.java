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
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileBloomery extends TileEntityMachine implements ITickable, IHasGui {

	String status = "null";
	String recipe = "null";

	IBlockState brick = Blocks.BRICK_BLOCK.getDefaultState();
	IBlockState door = Blocks.IRON_TRAPDOOR.getDefaultState();
	IBlockState lava = Blocks.FLOWING_LAVA.getDefaultState();
	IBlockState steel = GTMaterialGen.getBlock(GTMaterial.Steel, GTMaterialFlag.BLOCK).getDefaultState();

	AxisAlignedBB recipeBB = null;

	ItemStack iron = new ItemStack(Blocks.IRON_BLOCK);
	ItemStack coal = new ItemStack(Blocks.COAL_BLOCK);

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
		 * I use the getActive method to ping update() here, so I can also get the world
		 * time. If the tile isActive then structure begins to make Steel.
		 */
		if (getActive()) {
			int3 dir = new int3(pos, getFacing());
			setLava(dir.back(1));
			setLava(dir.up(1));
			setLava(dir.up(1));
			setLava(dir.up(1));
			if (world.getTotalWorldTime() % 256 == 0) { // TODO extend the time after testing
				setAir(dir.down(0));
				setAir(dir.down(1));
				setAir(dir.down(1));
				setSteel(dir.down(1));
				this.setActive(false);
			}
		}
	}

	public boolean canWork() {
		/*
		 * Checks the strucure then the recipe area to see if it can execute the recipe
		 * logic
		 */
		if (!getActive() && checkRecipeBoundingBox() && checkStructure()) {
			this.setActive(true);
			return true;
		}
		this.setActive(false);
		return false;
	}

	public boolean checkRecipeBoundingBox() {
		/*
		 * checks blocks space directly behind the tile for correct recipe stacks
		 */
		recipeBB = new AxisAlignedBB(new int3(pos, getFacing()).back(1).asBlockPos());
		List<EntityItem> items = world.getEntitiesWithinAABB(EntityItem.class, recipeBB);
		for (EntityItem item : items) {
			// TODO make this check or coal AND iron, for now this is fine to test logic
			if (StackUtil.isStackEqual(item.getItem(), iron)) {
				recipe = "Recipe Valid!";
				return true;
			}
		}

		if (items.isEmpty()) {
			recipe = "Recipe is finding nothing at all";
			return false;

		} else {
			recipe = items.toString();
			return false;
		}
	}

	public boolean checkStructure() {
		/*
		 * Important to remember this structure check steps to the last position, so the
		 * next step is based on the previous pos position.
		 */
		status = "Checking...";
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

			status = "false";
			return false;
		}
		status = "true";
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

	public String getStatus() {
		return this.status;
	}

	public String getRecipe() {
		return this.recipe;
	}

}
