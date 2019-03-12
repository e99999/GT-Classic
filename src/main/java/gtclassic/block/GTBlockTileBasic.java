package gtclassic.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import gtclassic.GTBlocks;
import gtclassic.GTMod;
import gtclassic.tile.GTTileAlloySmelter;
import gtclassic.tile.GTTileArcFurnace;
import gtclassic.tile.GTTileAssemblyLine;
import gtclassic.tile.GTTileBasicEnergyStorage;
import gtclassic.tile.GTTileBlastFurnace;
import gtclassic.tile.GTTileBloomery;
import gtclassic.tile.GTTileComputerCube;
import gtclassic.tile.GTTileDigitalChest;
import gtclassic.tile.GTTileDigitalTransformer;
import gtclassic.tile.GTTileFusionComputer;
import gtclassic.tile.GTTileIndustrialCentrifuge;
import gtclassic.tile.GTTileLightningRod;
import gtclassic.tile.GTTileMultiEnergyStorage;
import gtclassic.tile.GTTileQuantumEnergyStorage;
import gtclassic.tile.GTTileSuperConductorHigh;
import gtclassic.tile.GTTileSuperConductorLow;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockTileBasic extends GTBlockMultiID {

	String name;
	String texture;

	public GTBlockTileBasic(String name) {
		super(Material.IRON);
		this.name = name;
		setRegistryName(this.name.toLowerCase());
		setUnlocalizedName(GTMod.MODID + "." + this.name.toLowerCase());
		setCreativeTab(GTMod.creativeTabGT);
		setBlockUnbreakable();
		setResistance(20.0F);
		setSoundType(SoundType.METAL);
		setHarvestLevel("pickaxe", 2);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		// TODO put tooltip into arguments and add machine info
	}

	@Override
	@Deprecated
	public boolean canEntitySpawn(IBlockState state, Entity entityIn) {
		return false;
	}

	@Override
	@Deprecated
	public boolean canProvidePower(IBlockState state) {
		int meta = this.getMetaFromState(state);
		return meta >= 0 && meta <= 2 ? true : super.canProvidePower(state);
	}

	@Override
	public TileEntityBlock createNewTileEntity(World worldIn, int meta) {
		if (this == GTBlocks.computerCube) {
			return new GTTileComputerCube();
		} else if (this == GTBlocks.bloomery) {
			return new GTTileBloomery();
		} else if (this == GTBlocks.blastFurnace) {
			return new GTTileBlastFurnace();
		} else if (this == GTBlocks.industrialCentrifuge) {
			return new GTTileIndustrialCentrifuge();
		} else if (this == GTBlocks.alloySmelter) {
			return new GTTileAlloySmelter();
		} else if (this == GTBlocks.assLine) {
			return new GTTileAssemblyLine();
		} else if (this == GTBlocks.arcFurnace) {
			return new GTTileArcFurnace();
		} else if (this == GTBlocks.lightningRod) {
			return new GTTileLightningRod();
		} else if (this == GTBlocks.fusionComputer) {
			return new GTTileFusionComputer();
		} else if (this == GTBlocks.basicEnergyStorage) {
			return new GTTileBasicEnergyStorage();
		} else if (this == GTBlocks.quantumEnergyStorage) {
			return new GTTileQuantumEnergyStorage();
		} else if (this == GTBlocks.multiEnergyStorage) {
			return new GTTileMultiEnergyStorage();
		} else if (this == GTBlocks.digitalChest) {
			return new GTTileDigitalChest();
		} else if (this == GTBlocks.digitalTransformerIV) {
			return new GTTileDigitalTransformer();
		} else if (this == GTBlocks.energiumCable) {
			return new GTTileSuperConductorLow();
		} else if (this == GTBlocks.lapotronCable) {
			return new GTTileSuperConductorHigh();
		} else {
			return new TileEntityBlock();
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite[] getIconSheet(int meta) {
		return Ic2Icons.getTextures(this.name);
	}

	@Override
	public int getMaxSheetSize(int meta) {
		return 1;
	}

	@Override
	@Deprecated
	public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		TileEntity tile = blockAccess.getTileEntity(pos);
		if (tile instanceof TileEntityElectricBlock) {
			return ((TileEntityElectricBlock) tile).isEmittingRedstone() ? 15 : 0;
		} else {
			return super.getStrongPower(blockState, blockAccess, pos, side);
		}
	}

	@Override
	public List<Integer> getValidMetas() {
		return Arrays.asList(0);
	}

	@Override
	public List<IBlockState> getValidStateList() {
		IBlockState def = getDefaultState();
		List<IBlockState> states = new ArrayList<>();
		for (EnumFacing side : EnumFacing.VALUES) {
			states.add(def.withProperty(allFacings, side).withProperty(active, false));
			states.add(def.withProperty(allFacings, side).withProperty(active, true));
		}
		return states;
	}

	@Override
	public List<IBlockState> getValidStates() {
		return getBlockState().getValidStates();
	}

	@Override
	@Deprecated
	public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		TileEntity tile = blockAccess.getTileEntity(pos);
		if (tile instanceof TileEntityElectricBlock) {
			return ((TileEntityElectricBlock) tile).isEmittingRedstone() ? 15 : 0;
		} else {
			return super.getWeakPower(blockState, blockAccess, pos, side);
		}
	}

	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		if (this == GTBlocks.bloomery && ItemStack.areItemsEqualIgnoreDurability(playerIn.getHeldItemMainhand(),
				new ItemStack(Items.FLINT_AND_STEEL))) {
			TileEntity te = worldIn.getTileEntity(pos);
			if (te instanceof GTTileBloomery && ((GTTileBloomery) te).isActive) {
				return false;
			}
			if (te instanceof GTTileBloomery && !((GTTileBloomery) te).isActive) {
				return ((GTTileBloomery) te).canWork();
			}
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		particleQuantumEnergy(stateIn, worldIn, pos, rand);
		particleBloomery(stateIn, worldIn, pos, rand);
	}

	public void particleQuantumEnergy(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (this == GTBlocks.quantumEnergyStorage) {
			for (int i = 0; i < 3; ++i) {
				int j = rand.nextInt(2) * 2 - 1;
				int k = rand.nextInt(2) * 2 - 1;
				double d0 = pos.getX() + 0.5D + 0.25D * j;
				double d1 = pos.getY() + rand.nextFloat();
				double d2 = pos.getZ() + 0.5D + 0.25D * k;
				double d3 = rand.nextFloat() * j;
				double d4 = (rand.nextFloat() - 0.5D) * 0.125D;
				double d5 = rand.nextFloat() * k;
				worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
			}
		}
	}

	@SuppressWarnings("incomplete-switch")
	public void particleBloomery(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		TileEntity tile = worldIn.getTileEntity(pos);
		if (tile instanceof GTTileBloomery) {
			if (((GTTileBloomery) tile).isActive) {
				EnumFacing enumfacing = getFacing(worldIn, pos);
				double d0 = (double) pos.getX() + 0.5D;
				double d1 = (double) pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
				double d2 = (double) pos.getZ() + 0.5D;
				double d3 = 0.52D;
				double d4 = rand.nextDouble() * 0.6D - 0.3D;

				if (rand.nextDouble() < 0.1D) {
					worldIn.playSound((double) pos.getX() + 0.5D, (double) pos.getY(), (double) pos.getZ() + 0.5D,
							SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
				}

				switch (enumfacing) {
				case WEST:
					worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
					worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
					break;
				case EAST:
					worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
					worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
					break;
				case NORTH:
					worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
					worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
					break;
				case SOUTH:
					worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
					worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
				}
			}
		}
	}

}