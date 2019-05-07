package gtclassic.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gtclassic.GTBlocks;
import gtclassic.GTMod;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
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
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockFalling extends BlockFalling implements ITexturedBlock, ILocaleBlock {

	String name;
	int id;
	LocaleComp comp;

	public GTBlockFalling(String name, int id) {
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
	@Deprecated
	public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState blockstate, int fortune) {
		ArrayList<ItemStack> drops = new ArrayList<>();
		if (this == GTBlocks.sandCharcoalAsh) {
			drops.add(new ItemStack(Items.COAL, 2, 1));
			drops.add(GTMaterialGen.getDust(GTMaterial.Ashes, 1));
		} else {
			drops.add(GTMaterialGen.get(this));
		}

		return drops;
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

	@Override
	@Deprecated
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

		if (this != GTBlocks.sandSlagrete) {
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
	@Deprecated
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

	@Override
	public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
		Random rand = world instanceof World ? ((World) world).rand : new Random();
		int xp = 0;
		if (this == GTBlocks.sandCharcoalAsh) {
			xp = MathHelper.getInt(rand, 1, 3);
		}
		return xp;
	}

}
