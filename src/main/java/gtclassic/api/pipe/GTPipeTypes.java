package gtclassic.api.pipe;

public class GTPipeTypes {

	public enum GTPipeModel {
		SMALL("_small", new int[] { 6, 10 }),
		MED("", new int[] { 4, 12 }),
		LARGE("_large", new int[] { 1, 15 });

		String suffix;
		int[] sizes;

		GTPipeModel(String suffix, int[] sizes) {
			this.suffix = suffix;
			this.sizes = sizes;
		}

		public String getPrefix() {
			return suffix.replace("_", "");
		}

		public String getSuffix() {
			return this.suffix.toLowerCase();
		}

		public int[] getSizes() {
			return this.sizes;
		}
	}

	public enum GTPipeFluidCapacity {
		S800(800),
		S1600(1600),
		S3200(3200),
		S6400(6400),
		S12800(12800),
		SMAX1(96000),
		SMAX2(144000),
		SMAX3(192000);

		int size;

		GTPipeFluidCapacity(int size) {
			this.size = size;
		}

		public int getSize() {
			return this.size;
		}
	}
}
