package gtclassic.util;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;

/**
 * Created By Muramasa - https://github.com/Muramasa- Allows easily stepping in
 * directions given a EnumFacing
 */
public class int3 {
	
	MutableBlockPos pos = new MutableBlockPos();
	EnumFacing facing = EnumFacing.NORTH; // Used for moving in a direction

	public int3() {
		set(0, 0, 0);
	}

	public int3(int x, int y, int z) {
		set(x, y, z);
	}

	public int3(int3 pos) {
		set(pos);
	}

	public int3(int3 pos, EnumFacing facing) {
		set(pos);
		this.facing = facing;
	}

	public int3(BlockPos pos) {
		set(pos);
	}

	public int3(BlockPos pos, EnumFacing facing) {
		set(pos);
		this.facing = facing;
	}

	public int3 set(int x, int y, int z) {
		pos.setPos(x, y, z);
		return this;
	}

	public int3 set(int3 pos) {
		set(pos.getX(), pos.getY(), pos.getZ());
		this.facing = pos.facing;
		return this;
	}

	public int3 set(BlockPos pos) {
		return set(pos.getX(), pos.getY(), pos.getZ());
	}

	public void set(EnumFacing facing) {
		this.facing = facing;
	}

	public int3 add(int x, int y, int z) {
		return set(getX() + x, getY() + y, getZ() + z);
	}

	public int3 add(int3 pos) {
		return set(getX() + pos.getX(), getY() + pos.getY(), getZ() + pos.getZ());
	}

	public int3 sub(int x, int y, int z) {
		return set(getX() - x, getY() - y, getZ() - z);
	}

	public int3 sub(int3 pos) {
		return set(getX() - pos.getX(), getY() - pos.getY(), getZ() - pos.getZ());
	}

	public int3 left(int n) {
		return offset(n, facing.rotateY());
	}

	public int3 right(int n) {
		return offset(n, facing.rotateYCCW());
	}

	public int3 forward(int n) {
		return offset(n, facing);
	}

	public int3 back(int n) {
		return offset(n, facing.getOpposite());
	}

	public int3 up(int n) {
		return offset(n, EnumFacing.UP);
	}

	public int3 down(int n) {
		return offset(n, EnumFacing.DOWN);
	}

	public int3 offset(int n, EnumFacing facing) {
		if (n == 0 || facing == null)
			return this;
		return set(getX() + facing.getFrontOffsetX() * n, getY() + facing.getFrontOffsetY() * n,
				getZ() + facing.getFrontOffsetZ() * n);
	}

	public BlockPos asBlockPos() {
		return pos.toImmutable();
	}
	
	public BlockPos asReadPos()
	{
		return pos;
	}

	@Override
	public String toString() {
		return "(" + getX() + ", " + getY() + ", " + getZ() + ")";
	}
	
	public int getX()
	{
		return pos.getX();
	}
	
	public int getY()
	{
		return pos.getY();
	}
	
	public int getZ()
	{
		return pos.getZ();
	}
}
