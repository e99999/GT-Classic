package gtclassic.api.crops;

import gtclassic.api.material.GTMaterial;

public class GTCropType {

	private int id;
	private GTMaterial mat;
	private String name;
	private String discoverer;
	private String sprite;
	private int tier;
	private String[] attributes;

	public GTCropType(int id, String sprite, GTMaterial mat, String name, String discoverer, int tier,
			String... attributes) {
		this.id = id;
		this.mat = mat;
		this.name = name;
		this.discoverer = discoverer;
		this.sprite = sprite;
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

	public String getDiscoverer() {
		return this.discoverer;
	}

	public String getSpriteSheet() {
		return this.sprite;
	}

	public int getTier() {
		return this.tier;
	}

	public String[] getAttributes() {
		return this.attributes;
	}
}
