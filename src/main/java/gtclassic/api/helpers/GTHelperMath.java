package gtclassic.api.helpers;

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
}
