package gtclassic.api.block;

import java.util.List;

import gtclassic.api.model.GTModelOre;
import ic2.core.platform.textures.models.BaseModel;
import ic2.core.platform.textures.obj.ICustomModeledBlock;
import ic2.core.platform.textures.obj.ILayeredBlockModel;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class GTBlockBaseOre extends GTBlockBase implements ILayeredBlockModel, ICustomModeledBlock {

	public BackgroundSet background;

	public GTBlockBaseOre(BackgroundSet background) {
		super(Material.ROCK);
		this.background = background;
		setSoundType(SoundType.STONE);
	}

	@Override
	public boolean isLayered(IBlockState var1) {
		return true;
	}

	@Override
	public int getLayers(IBlockState var1) {
		return 2;
	}

	@Override
	public AxisAlignedBB getRenderBox(IBlockState var1, int layer) {
		return FULL_BLOCK_AABB;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getTextureFromState(IBlockState state, EnumFacing par1) {
		return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(this.background.getBackground());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getParticleTexture(IBlockState state) {
		return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(this.background.getBackground());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getLayerTexture(IBlockState state, EnumFacing facing, int layer) {
		if (layer == 0) {
			return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(this.background.getBackground());
		}
		return this.getTopLayer();
	}

	public abstract TextureAtlasSprite getTopLayer();

	@SideOnly(Side.CLIENT)
	@Override
	public BaseModel getModelFromState(IBlockState state) {
		return new GTModelOre(this, state);
	}

	@Override
	public List<IBlockState> getValidModelStates() {
		return this.getBlockState().getValidStates();
	}

	@Override
	public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, SpawnPlacementType type) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	public enum BackgroundSet {

		STONE("minecraft:blocks/stone"),
		NETHERRACK("minecraft:blocks/netherrack"),
		ENDSTONE("minecraft:blocks/end_stone"),
		BEDROCK("minecraft:blocks/bedrock");

		String sprite;

		BackgroundSet(String sprite) {
			this.sprite = sprite;
		}

		public String getBackground() {
			return this.sprite;
		}
	}
}
