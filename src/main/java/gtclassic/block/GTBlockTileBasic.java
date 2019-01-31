package gtclassic.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import gtclassic.GTBlocks;
import gtclassic.GTMod;
import gtclassic.tileentity.GTTileEntityBasicEnergyStorage;
import gtclassic.tileentity.GTTileEntityBookshelf;
import gtclassic.tileentity.GTTileEntityComputerCube;
import gtclassic.tileentity.GTTileEntityDigitalChest;
import gtclassic.tileentity.GTTileEntityDigitalTransformer;
import gtclassic.tileentity.GTTileEntityFusionComputer;
import gtclassic.tileentity.GTTileEntityIndustrialCentrifuge;
import gtclassic.tileentity.GTTileEntityLargeChest;
import gtclassic.tileentity.GTTileEntityLightningRod;
import gtclassic.tileentity.GTTileEntityMultiEnergyStorage;
import gtclassic.tileentity.GTTileEntityQuantumEnergyStorage;
import gtclassic.tileentity.GTTileEntitySmallChest;
import gtclassic.tileentity.GTTileEntitySuperConductor;
import gtclassic.tileentity.GTTileEntityWorkbench;
import ic2.core.block.base.BlockMultiID;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
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

public class GTBlockTileBasic extends BlockMultiID {

	public enum GTBlockTileBasicVariants {
		MACHINE_AUTOCRAFTER_LV,
		MACHINE_BASICENERGYSTORAGE_EV,
		MACHINE_CHARGEOMAT_EV,
		MACHINE_COMPUTERCUBE_EV,
		MACHINE_FUSIONCOMPUTER_IV,
		MACHINE_INDUSTRIALCENTRIFUGE_LV,
		MACHINE_LIGHTNINGROD_IV,
		MACHINE_MATTERFABRICATOR_EV,
		MACHINE_MULTIENERGYSTORAGE_MV,
		MACHINE_PLAYERDETECTOR_LV,
		MACHINE_QUANTUMENERGYSTORAGE_EV,
		MACHINE_ECHOPHONE_LV,
		MACHINE_DIGITALTRANSFORMER_IV,
		MACHINE_UUMASSEMBLER_EV,
		TILE_BOOKSHELF_LV,
		TILE_BOOKSHELF_MV,
		TILE_LARGECHEST_LV,
		TILE_LARGECHEST_MV,
		TILE_DIGITALCHEST_LV,
		TILE_DIGITALCHEST_MV,
		TILE_SMALLCHEST_LV,
		TILE_SMALLCHEST_MV,
		TILE_WORKBENCH_LV,
		TILE_WORKBENCH_MV,
		// WIRE_TUNGSTEN_IV,
		WIRE_ENERGIUM_LUV,
		WIRE_LAPOTRON_ZPM;
	}

	GTBlockTileBasicVariants variant;

	public GTBlockTileBasic(GTBlockTileBasicVariants variant) {
		super(Material.IRON);
		this.variant = variant;
		setRegistryName(variant.toString().toLowerCase());
		setUnlocalizedName(GTMod.MODID + "." + variant.toString().toLowerCase());
		setCreativeTab(GTMod.creativeTabGT);
		setHardness(4.0F);
		setResistance(20.0F);
		setSoundType(SoundType.METAL);
		setHarvestLevel("pickaxe", 2);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("tooltip." + GTMod.MODID + "." + variant.toString().toLowerCase()));
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
			return new GTTileEntityComputerCube();
		} else if (this == GTBlocks.industrialCentrifuge) {
			return new GTTileEntityIndustrialCentrifuge();
		} else if (this == GTBlocks.lightningRod) {
			return new GTTileEntityLightningRod();
		} else if (this == GTBlocks.fusionComputer) {
			return new GTTileEntityFusionComputer();
		} else if (this == GTBlocks.HESU) {
			return new GTTileEntityBasicEnergyStorage();
		} else if (this == GTBlocks.IDSU) {
			return new GTTileEntityQuantumEnergyStorage();
		} else if (this == GTBlocks.LESU) {
			return new GTTileEntityMultiEnergyStorage();
		} else if (this == GTBlocks.digitalTransformerIV) {
			return new GTTileEntityDigitalTransformer(32768, 65535);
		} else if (this == GTBlocks.digitalChestLV || this == GTBlocks.digitalChestMV) {
			return new GTTileEntityDigitalChest();
		} else if (this == GTBlocks.smallChestLV || this == GTBlocks.smallChestMV) {
			return new GTTileEntitySmallChest();
		} else if (this == GTBlocks.largeChestLV || this == GTBlocks.largeChestMV) {
			return new GTTileEntityLargeChest();
		} else if (this == GTBlocks.bookShelfLV || this == GTBlocks.bookShelfMV) {
			return new GTTileEntityBookshelf();
		} else if (this == GTBlocks.workBenchLV || this == GTBlocks.workBenchMV) {
			return new GTTileEntityWorkbench();
		} else if (this == GTBlocks.energiumWire) {
			return new GTTileEntitySuperConductor(1.5D, 32769.0D);
		} else {
			return new TileEntityBlock();
		}

	}

	@Override
	public float getEnchantPowerBonus(World world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);
		if ((tile instanceof GTTileEntityBookshelf) && (((GTTileEntityBookshelf) tile).isActive)) {
			return 2;
		} else {
			return 0;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite[] getIconSheet(int meta) {
		return Ic2Icons.getTextures(variant.toString().toLowerCase());
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
			states.add(def.withProperty(getMetadataProperty(), 0).withProperty(allFacings, side).withProperty(active,
					false));
			states.add(def.withProperty(getMetadataProperty(), 0).withProperty(allFacings, side).withProperty(active,
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
		if (tile instanceof GTTileEntityFusionComputer) {
			if (((GTTileEntityFusionComputer) tile).isActive) {
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

		else if (this == GTBlocks.IDSU) {
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