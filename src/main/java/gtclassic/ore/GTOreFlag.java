package gtclassic.ore;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public enum GTOreFlag {

	STONE(0, Blocks.STONE, ""), NETHER(32, Blocks.NETHERRACK, "_nether"), END(64, Blocks.END_STONE, "_end"),
	SAND(96, Blocks.SAND, "_sand"), GRAVEL(128, Blocks.GRAVEL, "_gravel"), BEDROCK(160, Blocks.BEDROCK, "_bedrock");

	private int textureoffset;
	private String suffix;
	private Block targetblock;

	GTOreFlag(int textureoffset, Block targetblock, String suffix) {
		this.textureoffset = textureoffset;
		this.targetblock = targetblock;
		this.suffix = suffix;
	}

	public int getTextureOffset() {
		return this.textureoffset;
	}

	public Block getTargetBlock() {
		return this.targetblock;
	}

	public String getSuffix() {
		return this.suffix;
	}

}
