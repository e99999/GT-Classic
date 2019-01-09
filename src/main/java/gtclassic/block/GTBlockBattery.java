package gtclassic.block;

import java.util.List;

import javax.annotation.Nullable;

import gtclassic.GTClassic;
import gtclassic.util.GTValues;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ITexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockBattery extends Block implements ITexturedBlock {
	public enum GTBlockBatteryVariants {
		SMALL_LITHIUM(16, 0), MED_LITHIUM(16, 2), LARGE_LITHIUM(16, 3),

		SMALL_LAPOTRON(17, 1), MED_LAPOTRON(17, 2), LARGE_LAPOTRON(17, 3),

		SMALL_ENERGIUM(18, 1), MED_ENERGIUM(18, 2), LARGE_ENERGIUM(18, 3);

		private int id;
		private int size;

		GTBlockBatteryVariants(int id, int size) {
			this.id = id;
			this.size = size;
		}

		public int getID() {
			return id;
		}

		public int getSize() {
			return size;
		}
	}

	GTBlockBatteryVariants variant;

	public GTBlockBattery(GTBlockBatteryVariants variant) {
		super(Material.CLOTH);
		this.variant = variant;
		setRegistryName(variant.toString().toLowerCase() + "_batteryblock");
		setUnlocalizedName(GTClassic.MODID + "." + variant.toString().toLowerCase() + "_batteryblock");
		setCreativeTab(GTClassic.creativeTabGT);
		setHardness(0.5F);
		setResistance(30.0F);
		setSoundType(SoundType.CLOTH);
	}

	public AxisAlignedBB getBatteryBoundingBox() {
		if (variant.getSize() == 0) {
			return GTValues.BATTERY_BLOCK_AABB;
		} else if (variant.getSize() == 1) {
			return GTValues.SMALL_BLOCK_AABB;
		} else if (variant.getSize() == 2) {
			return GTValues.MED_BLOCK_AABB;
		} else if (variant.getSize() == 3) {
			return GTValues.LARGE_BLOCK_AABB;
		} else {
			return FULL_BLOCK_AABB;
		}
	}

	@Override
	@Deprecated
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	@Deprecated
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	@Deprecated
	public boolean isFullBlock(IBlockState state) {
		return false;
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox(IBlockState iBlockState) {
		return getBatteryBoundingBox();
	}

	@Override
	@Nullable
	@Deprecated
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess access, BlockPos pos) {
		return getBatteryBoundingBox();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
		return getBatteryBoundingBox().offset(pos);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return getBatteryBoundingBox();
	}

	@Override
	@Deprecated
	public boolean canEntitySpawn(IBlockState state, Entity entityIn) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getTextureFromState(IBlockState iBlockState, EnumFacing enumFacing) {
		return Ic2Icons.getTextures("gtclassic_blocks")[variant.getID()];
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
}
