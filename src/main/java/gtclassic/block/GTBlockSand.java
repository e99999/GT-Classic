package gtclassic.block;

import java.util.List;

import gtclassic.GTBlocks;
import gtclassic.GTMod;
import ic2.core.platform.lang.ILocaleBlock;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Lang;
import ic2.core.platform.registry.Ic2States;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ITexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockSand extends BlockFalling implements ITexturedBlock, ILocaleBlock {

	String name;
	int id;
	LocaleComp comp;

	public GTBlockSand(String name, int id) {
		super(Material.SAND);
		this.name = name;
		this.id = id;
		this.comp = Ic2Lang.nullKey;
		setRegistryName(this.name.toLowerCase() + "_sand");
		setUnlocalizedName(GTMod.MODID + "." + this.name.toLowerCase() + "_sand");
		setCreativeTab(GTMod.creativeTabGT);
		setHardness(0.5F);
		setSoundType(SoundType.SAND);
		setHarvestLevel("shovel", 0);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		// TODO add tooltips
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox(IBlockState iBlockState) {
		return FULL_BLOCK_AABB;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getTextureFromState(IBlockState iBlockState, EnumFacing enumFacing) {
		return Ic2Icons.getTextures(GTMod.MODID + "_blocks")[this.id];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getParticleTexture(IBlockState state) {
		return this.getTextureFromState(state, EnumFacing.SOUTH);
	}

	@Override
	public List<IBlockState> getValidStates() {
		return this.blockState.getValidStates();
	}

	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateFromStack(ItemStack stack) {
		return this.getStateFromMeta(stack.getMetadata());
	}

	public LocaleComp getName() {
		return this.comp;
	}

	public Block setUnlocalizedName(LocaleComp name) {
		this.comp = name;
		return super.setUnlocalizedName(name.getUnlocalized());
	}

	@Override
	public Block setUnlocalizedName(String name) {
		this.comp = new LocaleBlockComp("tile." + name);
		return super.setUnlocalizedName(name);
	}

	protected boolean tryTouchWater(World worldIn, BlockPos pos, IBlockState state) {
		boolean flag = false;

		if (this != GTBlocks.slagcreteSand) {
			return false;
		}
		for (EnumFacing enumfacing : EnumFacing.values()) {
			if (enumfacing != EnumFacing.DOWN) {
				BlockPos blockpos = pos.offset(enumfacing);

				if (worldIn.getBlockState(blockpos).getMaterial() == Material.WATER) {
					flag = true;
					break;
				}
			}
		}

		if (flag) {
			worldIn.setBlockState(pos, Ic2States.reinforcedStone);
		}

		return flag;
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (!this.tryTouchWater(worldIn, pos, state)) {
			super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
		}
	}

	/**
	 * Called after the block is set in the Chunk data, but before the Tile Entity
	 * is set
	 */
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		if (!this.tryTouchWater(worldIn, pos, state)) {
			super.onBlockAdded(worldIn, pos, state);
		}
	}

}
