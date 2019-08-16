package gtclassic.util;

public class GTMathUtil {

	public static int clip(int value, int min, int max) {
		if (value < min) {
			return min;
		}
		if (value > max) {
			return max;
		}
		return value;
	}
}
