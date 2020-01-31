package gtclassic.common.block;

import java.util.List;
import java.util.Random;

import gtclassic.GTMod;
import gtclassic.api.block.GTBlockBase;
import gtclassic.common.GTBlocks;
import ic2.core.block.render.model.ModelSapling;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.models.BaseModel;
import ic2.core.platform.textures.obj.ICustomModeledBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockOrechid extends GTBlockBase implements ICustomModeledBlock {

	static final String TOOLTIP = "Indicates the presence of a bedrock ore deposit nearby";
	String name;
	int id;

	public GTBlockOrechid(String name, int id) {
		super(Material.PLANTS);
		this.name = name;
		this.id = id;
		setRegistryName(this.name.toLowerCase());
		setUnlocalizedName(GTMod.MODID + "." + this.name.toLowerCase());
		setCreativeTab(GTMod.creativeTabGT);
		setHardness(0.8F);
		setResistance(0.2F);
		setSoundType(SoundType.PLANT);
		setHarvestLevel("axe", 0);
		if (id == 39) {
			this.setLightLevel(2.5F);
		}
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format(TOOLTIP));
	}

	@Override
	public TextureAtlasSprite getTextureFromState(IBlockState var1, EnumFacing var2) {
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[this.id];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.4D, 0.0D, 0.4D, 0.6D, 0.45D, 0.6D).grow(.125D);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@SideOnly(Side.CLIENT)
	public BaseModel getModelFromState(IBlockState state) {
		return new ModelSapling(Ic2Icons.getTextures(GTMod.MODID + "_items")[this.id]);
	}

	@Override
	public List<IBlockState> getValidModelStates() {
		return this.getBlockState().getValidStates();
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		IBlockState soil = worldIn.getBlockState(pos.down());
		if (this == GTBlocks.oreChid) {
			return super.canPlaceBlockAt(worldIn, pos)
					&& (soil.getMaterial() == Material.GRASS || soil.getMaterial() == Material.GROUND);
		}
		if (this == GTBlocks.phosphorLily) {
			return super.canPlaceBlockAt(worldIn, pos) && (soil.getBlock() == Blocks.NETHERRACK);
		}
		return super.canPlaceBlockAt(worldIn, pos);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
		if (worldIn.isAirBlock(pos.down())) {
			this.dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (this == GTBlocks.phosphorLily) {
			double d0 = (double) pos.getX() + .5 + (rand.nextFloat() * .25F);
			double d1 = (double) pos.getY() + .4 + (rand.nextFloat() * .5F);
			double d2 = (double) pos.getZ() + .5 + (rand.nextFloat() * .25F);
			if (rand.nextFloat() > .25) {
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			} else {
				worldIn.spawnParticle(EnumParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			}
		}
	}
}
