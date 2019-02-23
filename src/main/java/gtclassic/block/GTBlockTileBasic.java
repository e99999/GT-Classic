package gtclassic.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import gtclassic.GTBlocks;
import gtclassic.GTMod;
import gtclassic.tile.GTTileAlloySmelter;
import gtclassic.tile.GTTileBasicEnergyStorage;
import gtclassic.tile.GTTileBookshelf;
import gtclassic.tile.GTTileComputerCube;
import gtclassic.tile.GTTileDigitalChest;
import gtclassic.tile.GTTileDigitalTransformer;
import gtclassic.tile.GTTileFusionComputer;
import gtclassic.tile.GTTileIndustrialCentrifuge;
import gtclassic.tile.GTTileLargeChest;
import gtclassic.tile.GTTileLightningRod;
import gtclassic.tile.GTTileMultiEnergyStorage;
import gtclassic.tile.GTTileQuantumEnergyStorage;
import gtclassic.tile.GTTileSmallChest;
import gtclassic.tile.GTTileSuperConductor;
import gtclassic.tile.GTTileWorkbench;
import ic2.core.block.base.BlockMultiID;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
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
		setHardness(4.0F);
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
		} else if (this == GTBlocks.industrialCentrifuge) {
			return new GTTileIndustrialCentrifuge();
		} else if (this == GTBlocks.alloySmelter) {
			return new GTTileAlloySmelter();
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
		} else if (this == GTBlocks.digitalTransformerIV) {
			return new GTTileDigitalTransformer(32768, 65535);
		} else if (this == GTBlocks.digitalChestLV || this == GTBlocks.digitalChestMV) {
			return new GTTileDigitalChest();
		} else if (this == GTBlocks.smallChestLV || this == GTBlocks.smallChestMV) {
			return new GTTileSmallChest();
		} else if (this == GTBlocks.largeChestLV || this == GTBlocks.largeChestMV) {
			return new GTTileLargeChest();
		} else if (this == GTBlocks.bookShelfLV || this == GTBlocks.bookShelfMV) {
			return new GTTileBookshelf();
		} else if (this == GTBlocks.workBenchLV || this == GTBlocks.workBenchMV) {
			return new GTTileWorkbench();
		} else if (this == GTBlocks.energiumWire) {
			return new GTTileSuperConductor(1.5D, 32769.0D);
		} else {
			return new TileEntityBlock();
		}

	}

	@Override
	public float getEnchantPowerBonus(World world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);
		if ((tile instanceof GTTileBookshelf) && (((GTTileBookshelf) tile).isActive)) {
			return 2;
		} else {
			return 0;
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
			states.add(def.withProperty(allFacings, side).withProperty(active,
					false));
			states.add(def.withProperty(allFacings, side).withProperty(active,
					true));
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

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		TileEntity tile = worldIn.getTileEntity(pos);
		if (tile instanceof GTTileFusionComputer) {
			if (((GTTileFusionComputer) tile).isActive) {
				for (int i = -2; i <= 2; ++i) {
					for (int j = -2; j <= 2; ++j) {
						if (i > -2 && i < 2 && j == -1) {
							j = 2;
						}

						if (rand.nextInt(4) == 0) {
							for (int k = 0; k <= 1; ++k) {

								if (!worldIn.isAirBlock(pos.add(i / 2, 0, j / 2))) {
									break;
								}

								worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, (double) pos.getX() + 0.5D,
										(double) pos.getY() + 1.0D, (double) pos.getZ() + 0.5D,
										(double) ((float) i + rand.nextFloat()) - 0.5D,
										(double) ((float) k - rand.nextFloat() - 1.0F),
										(double) ((float) j + rand.nextFloat()) - 0.5D);
							}
						}
					}
				}
			}
		}

		else if (this == GTBlocks.quantumEnergyStorage) {
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

}