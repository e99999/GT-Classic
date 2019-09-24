package gtclassic.util;

import gtclassic.material.GTMaterial;

public class GTCropType {

	private int id;
	private GTMaterial mat;
	private String name;
	private int tier;
	private String[] attributes;

	public GTCropType(int id, GTMaterial mat, String name, int tier, String... attributes) {
		this.id = id;
		this.mat = mat;
		this.name = name;
		this.tier = tier;
		this.attributes = attributes;
	}

	public int getId() {
		return this.id;
	}

	public GTMaterial getMaterial() {
		return this.mat;
	}

	public String getName() {
		return this.name;
	}

	public int getTier() {
		return this.tier;
	}

	public String[] getAttributes() {
		return this.attributes;
	}
}
