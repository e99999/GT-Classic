package gtclassic.api.pipe;

public class GTPipeTypes {

	public enum GTItemPipeType {
		NORMAL("", new int[] { 4, 12 }),
		LARGE("large", new int[] { 1, 15 });

		String name;
		int[] sizes;

		GTItemPipeType(String name, int[] sizes) {
			this.name = name;
			this.sizes = sizes;
		}

		public String getName() {
			return this.name;
		}

		public String getSuffix() {
			return "_" + this.name.toLowerCase();
		}

		public int[] getSizes() {
			return this.sizes;
		}
	}

	public enum GTFluidPipeType {
		SMALL("small", new int[] { 6, 10 }),
		MED("med", new int[] { 4, 12 }),
		LARGE("large", new int[] { 1, 15 });

		String name;
		int[] sizes;

		GTFluidPipeType(String name, int[] sizes) {
			this.name = name;
			this.sizes = sizes;
		}

		public String getName() {
			return this.name;
		}

		public String getSuffix() {
			return "_" + this.name.toLowerCase();
		}

		public int[] getSizes() {
			return this.sizes;
		}
	}
}
