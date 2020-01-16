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
	public static AxisAlignedBB createAABBFromPixelsCentered(int width, int height) {
		double offset = (double)(1.0D - (width / 16.0D)) * .5;
		double x = (double)width / 16.0D;
		double y = (double)height / 16.0D;
		return new AxisAlignedBB(offset, 0.0D, offset, offset + x, y, offset + x);
	}

	public static AxisAlignedBB createAABBFromPixels(int x, int y, int z, int x2, int y2, int z2) {
		return new AxisAlignedBB(pixelToDouble(x), pixelToDouble(y), pixelToDouble(z), pixelToDouble(x2), pixelToDouble(y2), pixelToDouble(z2));
	}

	public static double pixelToDouble(int pixels) {
		return (double) pixels / 16.0D;
	}
}
