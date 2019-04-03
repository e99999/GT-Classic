package gtclassic.tile;

import gtclassic.GTBlocks;
import gtclassic.util.int3;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.core.block.base.tile.TileEntityMachine;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;

public class GTTileMultiCharcoalPit extends TileEntityMachine implements ITickable, IProgressMachine {

	float progress = 0;
	@NetworkField(index = 8)
	float recipeOperation = 24000.0F;
	boolean processing = false;

	public GTTileMultiCharcoalPit() {
		super(0);
		setWorld(world);
		addGuiFields("progress");
	}

	@Override
	public float getProgress() {
		return progress;
	}

	@Override
	public float getMaxProgress() {
		return recipeOperation;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.processing = nbt.getBoolean("processing");
		this.setActive(nbt.getBoolean("active"));
		this.progress = nbt.getFloat("progress");
		this.recipeOperation = nbt.getFloat("RecipeTime");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setBoolean("processing", this.processing);
		nbt.setBoolean("active", this.getActive());
		nbt.setFloat("progress", this.progress);
		nbt.setFloat("RecipeTime", recipeOperation);
		return nbt;
	}

	@Override
	public void update() {
		if (processing) {
			progress = progress + 1.0F;
			getNetwork().updateTileGuiField(this, "progress");
			clockedCheckStructure();
		}
		if (progress >= recipeOperation) {
			recipeLastTick(); // log stuff
			processing = false;
			progress = 0;
			getNetwork().updateTileGuiField(this, "progress");
			this.setActive(false);
		}

	}

	public void clockedCheckStructure() {
		if (world.getTotalWorldTime() % 1000 == 0) {
			if (!checkStructure()) {
				processing = false;
				progress = 0;
				getNetwork().updateTileGuiField(this, "progress");
				this.setActive(false);
				world.playSound((EntityPlayer) null, pos, SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.BLOCKS, 1.0F,
						1.0F);
				int3 dir = new int3(getPos(), getFacing());
				setFire(dir.down(1));
			}
		}
	}

	public void recipeLastTick() {
		int3 dir = new int3(getPos(), getFacing());

		setCharcoal(dir.down(1));
		setCharcoal(dir.back(1));
		setCharcoal(dir.left(1));
		setCharcoal(dir.forward(1));
		setCharcoal(dir.forward(1));
		setCharcoal(dir.right(1));
		setCharcoal(dir.right(1));
		setCharcoal(dir.back(1));
		setCharcoal(dir.back(1));
		setCharcoal(dir.left(1));
		setCharcoal(dir.forward(1));

		setCharcoal(dir.down(1));
		setCharcoal(dir.back(1));
		setCharcoal(dir.left(1));
		setCharcoal(dir.forward(1));
		setCharcoal(dir.forward(1));
		setCharcoal(dir.right(1));
		setCharcoal(dir.right(1));
		setCharcoal(dir.back(1));
		setCharcoal(dir.back(1));
		setCharcoal(dir.left(1));
		setCharcoal(dir.forward(1));

		setCharcoal(dir.down(1));
		setCharcoal(dir.back(1));
		setCharcoal(dir.left(1));
		setCharcoal(dir.forward(1));
		setCharcoal(dir.forward(1));
		setCharcoal(dir.right(1));
		setCharcoal(dir.right(1));
		setCharcoal(dir.back(1));
		setCharcoal(dir.back(1));
		setCharcoal(dir.left(1));
		setCharcoal(dir.forward(1));

		world.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_SAND_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);

	}

	public boolean canWork() {
		if (!processing && checkStructure()) {
			processing = true;
			setActive(true);
			return true;
		}
		return false;
	}

	public boolean checkStructure() {
		if (!world.isAreaLoaded(pos, 3))
			return false;
		int3 dir = new int3(getPos(), getFacing());
		// top layer
		if (!(isDirt(dir.back(1)) && isDirt(dir.left(1)) && isDirt(dir.forward(1)) && isDirt(dir.forward(1))
				&& isDirt(dir.right(1)) && isDirt(dir.right(1)) && isDirt(dir.back(1)) && isDirt(dir.back(1))
				// log cube loops up and down
				&& isLog(dir.down(1)) && isLog(dir.down(1)) && isLog(dir.down(1)) && isLog(dir.left(1))
				&& isLog(dir.up(1)) && isLog(dir.up(1)) && isLog(dir.left(1)) && isLog(dir.down(1))
				&& isLog(dir.down(1)) && isLog(dir.forward(1)) && isLog(dir.up(1)) && isLog(dir.up(1))
				&& isLog(dir.right(1)) && isLog(dir.down(1)) && isLog(dir.down(1)) && isLog(dir.right(1))
				&& isLog(dir.up(1)) && isLog(dir.up(1))

				&& isLog(dir.forward(1)) && isLog(dir.down(1)) && isLog(dir.down(1)) && isLog(dir.left(1))
				&& isLog(dir.up(1)) && isLog(dir.up(1)) && isLog(dir.left(1)) && isLog(dir.down(1))
				&& isLog(dir.down(1))
				// bottom dirt layer
				&& isDirt(dir.down(1)) && isDirt(dir.back(1)) && isDirt(dir.back(1)) && isDirt(dir.right(1))
				&& isDirt(dir.forward(1)) && isDirt(dir.forward(1)) && isDirt(dir.right(1)) && isDirt(dir.back(1))
				&& isDirt(dir.back(1))
				// a little piece of the corner to shift into the dirt walls - south wall
				&& isLog(dir.up(1)) && isDirt(dir.back(1)) && isDirt(dir.left(1)) && isDirt(dir.left(1))
				&& isDirt(dir.up(1)) && isDirt(dir.right(1)) && isDirt(dir.right(1)) && isDirt(dir.up(1))
				&& isDirt(dir.left(1)) && isDirt(dir.left(1))
				// north wall action
				&& isDirt(dir.forward(4)) && isDirt(dir.right(1)) && isDirt(dir.right(1)) && isDirt(dir.down(1))
				&& isDirt(dir.left(1)) && isDirt(dir.left(1)) && isDirt(dir.down(1)) && isDirt(dir.right(1))
				&& isDirt(dir.right(1))
				// once again back thru a log to get to right mutation - east wall
				&& isLog(dir.back(1)) && isDirt(dir.right(1)) && isDirt(dir.back(1)) && isDirt(dir.back(1))
				&& isDirt(dir.up(1)) && isDirt(dir.forward(1)) && isDirt(dir.forward(1)) && isDirt(dir.up(1))
				&& isDirt(dir.back(1)) && isDirt(dir.back(1))
				// finally the beautiful exterior west wall of dirt
				&& isDirt(dir.left(4)) && isDirt(dir.forward(1)) && isDirt(dir.forward(1)) && isDirt(dir.down(1))
				&& isDirt(dir.back(1)) && isDirt(dir.back(1)) && isDirt(dir.down(1)) && isDirt(dir.forward(1))
				&& isDirt(dir.forward(1))

		)) {
			return false;
		}
		return true;
	}

	public boolean isLog(int3 pos) {
		return world.getBlockState(pos.asBlockPos()).getBlock().isWood(world, pos.asBlockPos());

	}

	public boolean isDirt(int3 pos) {
		return world.getBlockState(pos.asBlockPos()).getMaterial() == Material.GROUND
				|| world.getBlockState(pos.asBlockPos()).getMaterial() == Material.GRASS;
	}

	public void setCharcoal(int3 pos) {
		world.setBlockState(pos.asBlockPos(), GTBlocks.charcoalPile.getDefaultState());
	}

	public void setFire(int3 pos) {
		world.setBlockState(pos.asBlockPos(), Blocks.FIRE.getDefaultState());
	}

}
