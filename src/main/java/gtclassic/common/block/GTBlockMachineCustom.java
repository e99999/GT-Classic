package gtclassic.common.block;

import gtclassic.common.GTBlocks;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockMachineCustom extends GTBlockMachineDirectionable {

	public GTBlockMachineCustom(String name, LocaleComp comp) {
		super(name, comp);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getTextureFromState(IBlockState state, int meta, int extraMeta, EnumFacing side) {
		EnumFacing facing = this.hasFacing() ? (EnumFacing) state.getValue(allFacings) : EnumFacing.NORTH;
		return (Boolean) state.getValue(active) ? this.getIconSheet(meta)[extraMeta
				+ (this.customSpriteOffset()[side.getIndex()][facing.getIndex()] + 6) * this.getMaxSheetSize(meta)]
				: this.getIconSheet(meta)[extraMeta
						+ this.customSpriteOffset()[side.getIndex()][facing.getIndex()] * this.getMaxSheetSize(meta)];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getParticleTexture(IBlockState state) {
		int meta = this.getMetaFromState(state);
		return this.getIconSheet(meta)[this.getIconMeta(state)
				+ this.customSpriteOffset()[EnumFacing.random(RANDOM).getIndex()][3] * this.getMaxSheetSize(meta)];
	}

	public int[][] customSpriteOffset() {
		if (this == GTBlocks.tileIDSU) {
			return new int[][] { { 3, 0, 0, 0, 0, 0 }, { 0, 3, 0, 0, 0, 0 }, { 0, 0, 3, 0, 0, 0 }, { 0, 0, 0, 3, 0, 0 },
					{ 0, 0, 0, 0, 3, 0 }, { 0, 0, 0, 0, 0, 3 } };
		}
		return new int[][] { { 3, 2, 0, 0, 0, 0 }, { 2, 3, 1, 1, 1, 1 }, { 1, 1, 3, 2, 5, 4 }, { 0, 0, 2, 3, 4, 5 },
				{ 4, 5, 4, 5, 3, 2 }, { 5, 4, 5, 4, 2, 3 } };
	}
}
