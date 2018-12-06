package gtclassic.toxicdimension.blocks;

import java.util.Random;

import com.google.common.cache.LoadingCache;

import gtclassic.GTClassic;
import gtclassic.GTConfig;
import gtclassic.toxicdimension.world.GTToxicTeleporter;
import gtclassic.util.GTBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockToxicPortal extends BlockPortal {

	public GTBlockToxicPortal() {
		super();
		setRegistryName("toxic_portal"); // texture
		setUnlocalizedName(GTClassic.MODID + ".toxicPortal"); // lang
		setHardness(-1.0F);
		setLightLevel(0.75F);
		setSoundType(SoundType.GLASS);
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0,
				new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
	}

	@Override
	public boolean trySpawnPortal(World worldIn, BlockPos pos) {
		GTBlockToxicPortal.Size blockportalsize = new GTBlockToxicPortal.Size(worldIn, pos, EnumFacing.Axis.X);
		if (blockportalsize.isValid() && blockportalsize.portalBlockCount == 0) {
			blockportalsize.placePortalBlocks();
			return true;
		} else {
			GTBlockToxicPortal.Size blockportalsize1 = new GTBlockToxicPortal.Size(worldIn, pos, EnumFacing.Axis.Z);
			if (blockportalsize1.isValid() && blockportalsize1.portalBlockCount == 0) {
				blockportalsize1.placePortalBlocks();
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	@Deprecated
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		EnumFacing.Axis enumfacingaxis = state.getValue(AXIS);
		if (enumfacingaxis == EnumFacing.Axis.X) {
			GTBlockToxicPortal.Size blockportalsize = new GTBlockToxicPortal.Size(worldIn, pos, EnumFacing.Axis.X);
			if (!blockportalsize.isValid()
					|| blockportalsize.portalBlockCount < blockportalsize.width * blockportalsize.height) {
				worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
				portalDestroyed(worldIn, pos);
			}
		} else if (enumfacingaxis == EnumFacing.Axis.Z) {
			GTBlockToxicPortal.Size blockportalsize1 = new GTBlockToxicPortal.Size(worldIn, pos, EnumFacing.Axis.Z);
			if (!blockportalsize1.isValid()
					|| blockportalsize1.portalBlockCount < blockportalsize1.width * blockportalsize1.height) {
				worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
				portalDestroyed(worldIn, pos);
			}
		}
	}

	private void portalDestroyed(World world, BlockPos pos) {
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(IBlockState stateIn, World world, BlockPos pos, Random rand) {
		if (rand.nextInt(100) == 0)
			world.playSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D,
					net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation(("block.portal.ambient"))),
					SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
		for (int i = 0; i < 4; ++i) {
			double d0 = pos.getX() + rand.nextFloat();
			double d1 = pos.getY() + rand.nextFloat();
			double d2 = pos.getZ() + rand.nextFloat();
			int j = rand.nextInt(2) * 2 - 1;
			if (world.getBlockState(pos.west()).getBlock() != this
					&& world.getBlockState(pos.east()).getBlock() != this) {
				d0 = pos.getX() + 0.5D + 0.25D * j;
			} else {
				d2 = pos.getZ() + 0.5D + 0.25D * j;
			}
			world.spawnParticle(EnumParticleTypes.FLAME, d0, d1, d2, 0, 0, 0);

		}
	}

	@Override
	public BlockPattern.PatternHelper createPatternHelper(World worldIn, BlockPos pos) {
		EnumFacing.Axis enumfacingaxis = EnumFacing.Axis.Z;
		GTBlockToxicPortal.Size blockportalsize = new GTBlockToxicPortal.Size(worldIn, pos, EnumFacing.Axis.X);
		LoadingCache<BlockPos, BlockWorldState> loadingcache = BlockPattern.createLoadingCache(worldIn, true);
		if (!blockportalsize.isValid()) {
			enumfacingaxis = EnumFacing.Axis.X;
			blockportalsize = new GTBlockToxicPortal.Size(worldIn, pos, EnumFacing.Axis.Z);
		}
		if (!blockportalsize.isValid()) {
			return new BlockPattern.PatternHelper(pos, EnumFacing.NORTH, EnumFacing.UP, loadingcache, 1, 1, 1);
		} else {
			int[] aint = new int[EnumFacing.AxisDirection.values().length];
			EnumFacing enumfacing = blockportalsize.rightDir.rotateYCCW();
			BlockPos blockpos = blockportalsize.bottomLeft.up(blockportalsize.getHeight() - 1);
			for (EnumFacing.AxisDirection enumfacingaxisdirection : EnumFacing.AxisDirection.values()) {
				BlockPattern.PatternHelper blockpatternpatternhelper = new BlockPattern.PatternHelper(
						enumfacing.getAxisDirection() == enumfacingaxisdirection ? blockpos
								: blockpos.offset(blockportalsize.rightDir, blockportalsize.getWidth() - 1),
						EnumFacing.getFacingFromAxis(enumfacingaxisdirection, enumfacingaxis), EnumFacing.UP,
						loadingcache, blockportalsize.getWidth(), blockportalsize.getHeight(), 1);
				for (int i = 0; i < blockportalsize.getWidth(); ++i) {
					for (int j = 0; j < blockportalsize.getHeight(); ++j) {
						BlockWorldState blockworldstate = blockpatternpatternhelper.translateOffset(i, j, 1);
						if (blockworldstate.getBlockState() != null
								&& blockworldstate.getBlockState().getMaterial() != Material.AIR) {
							++aint[enumfacingaxisdirection.ordinal()];
						}
					}
				}
			}
			EnumFacing.AxisDirection enumfacingaxisdirection1 = EnumFacing.AxisDirection.POSITIVE;
			for (EnumFacing.AxisDirection enumfacingaxisdirection2 : EnumFacing.AxisDirection.values()) {
				if (aint[enumfacingaxisdirection2.ordinal()] < aint[enumfacingaxisdirection1.ordinal()]) {
					enumfacingaxisdirection1 = enumfacingaxisdirection2;
				}
			}
			return new BlockPattern.PatternHelper(
					enumfacing.getAxisDirection() == enumfacingaxisdirection1 ? blockpos
							: blockpos.offset(blockportalsize.rightDir, blockportalsize.getWidth() - 1),
					EnumFacing.getFacingFromAxis(enumfacingaxisdirection1, enumfacingaxis), EnumFacing.UP, loadingcache,
					blockportalsize.getWidth(), blockportalsize.getHeight(), 1);
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (!worldIn.isRemote && !entityIn.isRiding() && !entityIn.isBeingRidden() && entityIn.isNonBoss()) {
			if (entityIn instanceof EntityPlayerMP) {
				EntityPlayerMP thePlayer = (EntityPlayerMP) entityIn;
				if (entityIn.timeUntilPortal > 0) {
					entityIn.timeUntilPortal = 10;
				} else if (entityIn.dimension != GTConfig.dimensionId) {
					entityIn.timeUntilPortal = 10;
					thePlayer.mcServer.getPlayerList().transferPlayerToDimension(thePlayer, GTConfig.dimensionId,
							getTeleporterForDimension(thePlayer, pos, GTConfig.dimensionId));
				} else {
					entityIn.timeUntilPortal = 10;
					thePlayer.mcServer.getPlayerList().transferPlayerToDimension(thePlayer, 0,
							getTeleporterForDimension(thePlayer, pos, 0));
				}
			} else if (!(entityIn instanceof EntityPlayer) && !(entityIn instanceof EntityPlayerSP)) {
				if (entityIn.timeUntilPortal > 0) {
					entityIn.timeUntilPortal = 10;
				} else if (entityIn.dimension != GTConfig.dimensionId) {
					entityIn.timeUntilPortal = 10;
					entityIn.changeDimension(GTConfig.dimensionId,
							getTeleporterForDimension(entityIn, pos, GTConfig.dimensionId));
				} else {
					entityIn.timeUntilPortal = 10;
					entityIn.changeDimension(GTConfig.dimensionId, getTeleporterForDimension(entityIn, pos, 0));
				}
			}
		}
	}

	private GTToxicTeleporter getTeleporterForDimension(Entity entity, BlockPos pos, int dimid) {
		BlockPattern.PatternHelper blockpatternpatternhelper = GTBlocks.toxicPortal.createPatternHelper(entity.world,
				new BlockPos(pos));
		double d0 = blockpatternpatternhelper.getForwards().getAxis() == EnumFacing.Axis.X
				? (double) blockpatternpatternhelper.getFrontTopLeft().getZ()
				: (double) blockpatternpatternhelper.getFrontTopLeft().getX();
		double d1 = blockpatternpatternhelper.getForwards().getAxis() == EnumFacing.Axis.X ? entity.posZ : entity.posX;
		d1 = Math.abs(MathHelper.pct(
				d1 - (blockpatternpatternhelper.getForwards().rotateY()
						.getAxisDirection() == EnumFacing.AxisDirection.NEGATIVE ? 1 : 0),
				d0, d0 - blockpatternpatternhelper.getWidth()));
		double d2 = MathHelper.pct(entity.posY - 1.0D, blockpatternpatternhelper.getFrontTopLeft().getY(),
				blockpatternpatternhelper.getFrontTopLeft().getY() - blockpatternpatternhelper.getHeight());
		return new GTToxicTeleporter(entity.getServer().getWorld(dimid), new Vec3d(d1, d2, 0.0D),
				blockpatternpatternhelper.getForwards());
	}

	public static class Size {

		private final World world;
		private final EnumFacing.Axis axis;
		private final EnumFacing rightDir;
		private final EnumFacing leftDir;
		private int portalBlockCount;
		private BlockPos bottomLeft;
		private int height;
		private int width;

		public Size(World worldIn, BlockPos sizeblockpos, EnumFacing.Axis sizefacingaxis) {
			this.world = worldIn;
			this.axis = sizefacingaxis;
			if (sizefacingaxis == EnumFacing.Axis.X) {
				this.leftDir = EnumFacing.EAST;
				this.rightDir = EnumFacing.WEST;
			} else {
				this.leftDir = EnumFacing.NORTH;
				this.rightDir = EnumFacing.SOUTH;
			}
			for (BlockPos blockpos = sizeblockpos; sizeblockpos.getY() > blockpos.getY() - 21 && sizeblockpos.getY() > 0
					&& this.isEmptyBlock(worldIn.getBlockState(sizeblockpos.down())
							.getBlock()); sizeblockpos = sizeblockpos.down()) {
				;
			}
			int i = this.getDistanceUntilEdge(sizeblockpos, this.leftDir) - 1;
			if (i >= 0) {
				this.bottomLeft = sizeblockpos.offset(this.leftDir, i);
				this.width = this.getDistanceUntilEdge(this.bottomLeft, this.rightDir);
				if (this.width < 2 || this.width > 21) {
					this.bottomLeft = null;
					this.width = 0;
				}
			}
			if (this.bottomLeft != null) {
				this.height = this.calculatePortalHeight();
			}
		}

		protected int getDistanceUntilEdge(BlockPos distblockpos, EnumFacing distfacing) {
			int i;
			for (i = 0; i < 22; ++i) {
				BlockPos blockpos = distblockpos.offset(distfacing, i);
				if (!this.isEmptyBlock(this.world.getBlockState(blockpos).getBlock())
						|| this.world.getBlockState(blockpos.down()).getBlock() != GTBlocks.toxicPortalFrame
								.getDefaultState().getBlock()) {
					break;
				}
			}
			Block block = this.world.getBlockState(distblockpos.offset(distfacing, i)).getBlock();
			return block == GTBlocks.toxicPortalFrame.getDefaultState().getBlock() ? i : 0;
		}

		public int getHeight() {
			return this.height;
		}

		public int getWidth() {
			return this.width;
		}

		protected int calculatePortalHeight() {
			label56: for (this.height = 0; this.height < 21; ++this.height) {
				for (int i = 0; i < this.width; ++i) {
					BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i).up(this.height);
					Block block = this.world.getBlockState(blockpos).getBlock();
					if (!this.isEmptyBlock(block)) {
						break label56;
					}
					if (block == GTBlocks.toxicPortal) {
						++this.portalBlockCount;
					}
					if (i == 0) {
						block = this.world.getBlockState(blockpos.offset(this.leftDir)).getBlock();
						if (block != GTBlocks.toxicPortalFrame.getDefaultState().getBlock()) {
							break label56;
						}
					} else if (i == this.width - 1) {
						block = this.world.getBlockState(blockpos.offset(this.rightDir)).getBlock();
						if (block != GTBlocks.toxicPortalFrame.getDefaultState().getBlock()) {
							break label56;
						}
					}
				}
			}
			for (int j = 0; j < this.width; ++j) {
				if (this.world.getBlockState(this.bottomLeft.offset(this.rightDir, j).up(this.height))
						.getBlock() != GTBlocks.toxicPortalFrame.getDefaultState().getBlock()) {
					this.height = 0;
					break;
				}
			}
			if (this.height <= 21 && this.height >= 3) {
				return this.height;
			} else {
				this.bottomLeft = null;
				this.width = 0;
				this.height = 0;
				return 0;
			}
		}

		protected boolean isEmptyBlock(Block blockIn) {
			return blockIn.getDefaultState().getMaterial() == Material.AIR || blockIn == Blocks.FIRE
					|| blockIn == GTBlocks.toxicPortal;
		}

		public boolean isValid() {
			return this.bottomLeft != null && this.width >= 2 && this.width <= 21 && this.height >= 3
					&& this.height <= 21;
		}

		public void placePortalBlocks() {
			for (int i = 0; i < this.width; ++i) {
				BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i);
				for (int j = 0; j < this.height; ++j) {
					this.world.setBlockState(blockpos.up(j),
							GTBlocks.toxicPortal.getDefaultState().withProperty(BlockPortal.AXIS, this.axis), 2);
				}
			}
		}
	}
}
