package gtclassic.util;

import net.minecraft.util.math.AxisAlignedBB;

public class GTValues {

	// index for storing global variables
	 public static final AxisAlignedBB BATTERY_BLOCK_AABB = new AxisAlignedBB(0.35D, 0.0D, 0.35D, 0.65D, 0.6D, 0.65D);
	 public static final AxisAlignedBB SMALL_BLOCK_AABB = new AxisAlignedBB  (0.3D, 0.0D, 0.3D, 0.7D, 0.4D, 0.7D);
	 public static final AxisAlignedBB MED_BLOCK_AABB = new AxisAlignedBB    (0.2D, 0.0D, 0.2D, 0.8D, 0.6D, 0.8D);
	 public static final AxisAlignedBB LARGE_BLOCK_AABB = new AxisAlignedBB  (0.1D, 0.0D, 0.1D, 0.9D, 0.8D, 0.9D);
	//public static final AxisAlignedBB LARGE_BLOCK_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.8D, 0.8D, 0.8D);

	 
	 public static final AxisAlignedBB FULL_BLOCK_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

	// boolean that renders anything labeled as WIP uncraftable
	public static boolean debugMode = true;

	// colors
	public static int white = 16777215;
	public static int grey = 4210752;
	public static int red = 15599112;
	public static int green = 9567352;
}
