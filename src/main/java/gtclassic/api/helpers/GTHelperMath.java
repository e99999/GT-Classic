package gtclassic.api.helpers;

import net.minecraft.util.math.AxisAlignedBB;

public class GTHelperMath {

	public static int clip(int value, int min, int max) {
		if (value < min) {
			return min;
		}
		if (value > max) {
			return max;
		}
		return value;
	}

	public static boolean within(int value, int low, int high) {
		return (value >= low) && (value <= high);
	}

	/**
	 * Creates a centered AABB using pixels args
	 * 
	 * @param width  - width of the BB
	 * @param height - height of the BB
	 * @return - the centered sum as an AABB
	 */
	public static AxisAlignedBB createAABBFromPixels(double width, double height) {
		double offset = (1.0D - (width / 16.0D)) * .5;
		double x = width / 16.0D;
		double y = height / 16.0D;
		return new AxisAlignedBB(offset, 0.0D, offset, offset + x, y, offset + x);
	}
}
