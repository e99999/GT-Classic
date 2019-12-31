package gtclassic.api.block;

import java.awt.Color;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.api.interfaces.IGTColorBlock;
import gtclassic.api.model.GTModelOre;
import gtclassic.common.GTWorldGen;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.models.BaseModel;
import ic2.core.platform.textures.obj.ICustomModeledBlock;
import ic2.core.platform.textures.obj.ILayeredBlockModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockBaseOre extends GTBlockBase implements ILayeredBlockModel, ICustomModeledBlock, IGTColorBlock {

	public Color color;
	public TextureSet set;
	public BackgroundSet background;

	public GTBlockBaseOre(Color color, TextureSet set, BackgroundSet background) {
		super(Material.ROCK);
		this.color = color;
		this.set = set;
		this.background = background;
		setSoundType(SoundType.STONE);
		if (this.background == BackgroundSet.BEDROCK) {
			setBlockUnbreakable();
			setResistance(6000000.0F);
			GTWorldGen.bedrockOres.add(this);
		}
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
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[this.set.getId()];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BaseModel getModelFromState(IBlockState state) {
		return new GTModelOre(this, state);
	}

	@Override
	public List<IBlockState> getValidModelStates() {
		return this.getBlockState().getValidStates();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public Color getColor(IBlockState state, IBlockAccess worldIn, BlockPos pos, Block block, int index) {
		return index == 0 ? Color.white : this.color;
	}

	public enum TextureSet {
		METAL(28),
		GEM(29),
		LAPIS(30),
		QUARTZ(31);

		int id;

		TextureSet(int id) {
			this.id = id;
		}

		public int getId() {
			return this.id;
		}
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
