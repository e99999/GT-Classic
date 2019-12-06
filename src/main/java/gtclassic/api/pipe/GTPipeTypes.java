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

	public enum GTFluidPipeSize {
		S800(800),
		S1600(1600),
		S3200(3200),
		S6400(6400),
		S12800(12800),
		SMAX1(96000),
		SMAX2(144000),
		SMAX3(192000);

		int size;

		GTFluidPipeSize(int size) {
			this.size = size;
		}

		public int getSize() {
			return this.size;
		}
	}
}
