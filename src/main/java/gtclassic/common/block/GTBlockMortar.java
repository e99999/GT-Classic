package gtclassic.common.block;

import java.util.List;

import gtclassic.GTMod;
import gtclassic.api.block.GTBlockBase;
import gtclassic.api.helpers.GTHelperMath;
import gtclassic.api.model.GTModelMortar;
import ic2.core.platform.textures.models.BaseModel;
import ic2.core.platform.textures.obj.ICustomModeledBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class GTBlockMortar extends GTBlockBase implements ICustomModeledBlock{
	
	static final AxisAlignedBB AABB_MORTAR = GTHelperMath.createAABBFromPixelsCentered(12, 10);

	public GTBlockMortar() {
		super(Material.ROCK);
		setRegistryName("mortar");
		setUnlocalizedName(GTMod.MODID + ".mortar");
		setCreativeTab(GTMod.creativeTabGT);
		setSoundType(SoundType.METAL);
		setResistance(10.0F);
		setHardness(3.0F);
		setHarvestLevel("pickaxe", 1);
	}

	@Override
	public TextureAtlasSprite getTextureFromState(IBlockState var1, EnumFacing var2) {
		//This is useless once the model is working
		return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("minecraft:blocks/stone");
	}

	@Override
	public BaseModel getModelFromState(IBlockState state) {
		return new GTModelMortar(state);
	}

	@Override
	public List<IBlockState> getValidModelStates() {
		return this.getBlockState().getValidStates();
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isBlockNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, SpawnPlacementType type) {
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		return AABB_MORTAR;
	}
}
