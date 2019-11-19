package gtclassic.common.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import gtclassic.GTMod;
import gtclassic.api.block.GTBlockBaseMultiID;
import gtclassic.api.interfaces.IGTDisplayTickTile;
import gtclassic.api.interfaces.IGTReaderInfoBlock;
import gtclassic.common.GTBlocks;
import gtclassic.common.tile.GTTileAutocrafter;
import gtclassic.common.tile.GTTileCabinet;
import gtclassic.common.tile.GTTileCentrifuge;
import gtclassic.common.tile.GTTileChargeOMat;
import gtclassic.common.tile.GTTileComputerCube;
import gtclassic.common.tile.GTTileDigitalChest;
import gtclassic.common.tile.GTTileDragonEggEnergySiphon;
import gtclassic.common.tile.GTTileEchotron;
import gtclassic.common.tile.GTTileMagicEnergyAbsorber;
import gtclassic.common.tile.GTTileMagicEnergyConverter;
import gtclassic.common.tile.GTTileMatterFabricator;
import gtclassic.common.tile.GTTileMobRepeller;
import gtclassic.common.tile.GTTilePlayerDetector;
import gtclassic.common.tile.GTTileUUMAssembler;
import gtclassic.common.tile.GTTileWorktable;
import gtclassic.common.tile.multi.GTTileMultiFusionReactor;
import gtclassic.common.tile.multi.GTTileMultiLightningRod;
import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.block.personal.base.misc.IOwnerBlock;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.lang.storage.Ic2InfoLang;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockMachine extends GTBlockBaseMultiID implements IGTReaderInfoBlock {

	String name;
	String texture;
	int size = 0;

	public GTBlockMachine(String name, LocaleComp comp) {
		this(name, comp, 0);
	}

	public GTBlockMachine(String name, LocaleComp comp, int additionalInfo) {
		super(Material.IRON);
		this.name = name;
		this.size = additionalInfo + 1;
		setRegistryName(this.name.toLowerCase());
		setUnlocalizedName(comp);
		setCreativeTab(GTMod.creativeTabGT);
		setHardness(100.0F);
		setResistance(20.0F);
		setSoundType(SoundType.METAL);
	}

	public GTBlockMachine(String name, LocaleComp comp, Material blockMat) {
		super(blockMat);
		this.name = name;
		this.size = 1;
		setRegistryName(this.name.toLowerCase());
		setUnlocalizedName(comp);
		setCreativeTab(GTMod.creativeTabGT);
		setResistance(20.0F);
		setSoundType(SoundType.METAL);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		for (int i = 0; i < this.size; i++) {
			tooltip.add(I18n.format(this.getUnlocalizedName().replace("tile", "tooltip") + i));
		}
	}

	@Override
	public void addReaderInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (this == GTBlocks.tileCentrifuge || this == GTBlocks.tilePlayerDetector || this == GTBlocks.tileEchotron
				|| this == GTBlocks.tileMobRepeller) {
			tooltip.add((Ic2InfoLang.euReaderSinkInfo.getLocalizedFormatted(new Object[] { 32 })));
		}
		if (this == GTBlocks.tileChargeOmat) {
			tooltip.add((Ic2InfoLang.euReaderSinkInfo.getLocalizedFormatted(new Object[] { 2048 })));
		}
		if (this == GTBlocks.tileUUMAssembler) {
			tooltip.add((Ic2InfoLang.euReaderSinkInfo.getLocalizedFormatted(new Object[] { 512 })));
		}
		if (this == GTBlocks.tileFusionReactor) {
			tooltip.add((Ic2InfoLang.euReaderSinkInfo.getLocalizedFormatted(new Object[] { 8192 })));
		}
		if (this == GTBlocks.tileLightningRod) {
			tooltip.add((Ic2InfoLang.electricProduction.getLocalizedFormatted(new Object[] { 25000000 })
					+ " each strike"));
			tooltip.add((Ic2InfoLang.euOutput.getLocalizedFormatted(new Object[] { 8192 })));
		}
		if (this == GTBlocks.tileDragonEggEnergySiphon) {
			tooltip.add((Ic2InfoLang.electricProduction.getLocalizedFormatted(new Object[] { 128.0 })));
		}
		if (this == GTBlocks.tileMagicEnergyConverter) {
			tooltip.add((Ic2InfoLang.electricProduction.getLocalizedFormatted(new Object[] { 24.0 })));
		}
		if (this == GTBlocks.tileMagicEnergyAbsorber) {
			tooltip.add((Ic2InfoLang.electricProduction.getLocalizedFormatted(new Object[] {
					Ic2InfoLang.electricTransferRateVariable.getLocalized() })));
			tooltip.add((Ic2InfoLang.euOutput.getLocalizedFormatted(new Object[] { 128 })));
		}
		if (this == GTBlocks.tileAESU) {
			tooltip.add((Ic2InfoLang.electricMaxIn.getLocalizedFormatted(new Object[] { 2048 })));
			tooltip.add((Ic2InfoLang.electricMaxStorage.getLocalizedFormatted(new Object[] { 100000000 })));
		}
		if (this == GTBlocks.tileIDSU) {
			tooltip.add((Ic2InfoLang.electricMaxIn.getLocalizedFormatted(new Object[] { 8192 })));
			tooltip.add((Ic2InfoLang.electricMaxStorage.getLocalizedFormatted(new Object[] { 400000000 })));
		}
		if (this == GTBlocks.tileLESU) {
			tooltip.add((Ic2InfoLang.electricMaxIn.getLocalizedFormatted(new Object[] { 32 })));
			tooltip.add((Ic2InfoLang.electricMaxStorage.getLocalizedFormatted(new Object[] { 202000000 })));
			tooltip.add((Ic2InfoLang.euOutput.getLocalizedFormatted(new Object[] { 512 })));
		}
		if (this == GTBlocks.tileFabricator) {
			tooltip.add((Ic2InfoLang.euReaderSinkInfo.getLocalizedFormatted(new Object[] { 134217728 })));
		}
		if (this == GTBlocks.tileTranslocator || this == GTBlocks.tileBufferSmall || this == GTBlocks.tileBufferLarge
				|| this == GTBlocks.tileBufferFluid) {
			tooltip.add((Ic2InfoLang.euReaderCableLimit.getLocalizedFormatted(new Object[] { 32 })));
		}
		if (this == GTBlocks.tileSupercondensator) {
			tooltip.add(Ic2InfoLang.electricTransformer.getLocalizedFormatted(new Object[] { 134217728, 8192 }));
		}
		if (this == GTBlocks.tileSuperconductorCable) {
			tooltip.add((Ic2InfoLang.euReaderCableLimit.getLocalizedFormatted(new Object[] { 134217728 })));
			tooltip.add((Ic2InfoLang.euReaderCableLoss.getLocalizedFormatted(new Object[] { 0.001 })));
		}
	}

	@Override
	public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, SpawnPlacementType type) {
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
		if (this == GTBlocks.tileComputer) {
			return new GTTileComputerCube();
		}
		if (this == GTBlocks.tileCentrifuge) {
			return new GTTileCentrifuge();
		}
		if (this == GTBlocks.tilePlayerDetector) {
			return new GTTilePlayerDetector();
		}
		if (this == GTBlocks.tileMobRepeller) {
			return new GTTileMobRepeller();
		}
		if (this == GTBlocks.tileEchotron) {
			return new GTTileEchotron();
		}
		if (this == GTBlocks.tileChargeOmat) {
			return new GTTileChargeOMat();
		}
		if (this == GTBlocks.tileAutocrafter) {
			return new GTTileAutocrafter();
		}
		if (this == GTBlocks.tileLightningRod) {
			return new GTTileMultiLightningRod();
		}
		if (this == GTBlocks.tileDragonEggEnergySiphon) {
			return new GTTileDragonEggEnergySiphon();
		}
		if (this == GTBlocks.tileMagicEnergyConverter) {
			return new GTTileMagicEnergyConverter();
		}
		if (this == GTBlocks.tileMagicEnergyAbsorber) {
			return new GTTileMagicEnergyAbsorber();
		}
		if (this == GTBlocks.tileFabricator) {
			return new GTTileMatterFabricator();
		}
		if (this == GTBlocks.tileUUMAssembler) {
			return new GTTileUUMAssembler();
		}
		if (this == GTBlocks.tileFusionReactor) {
			return new GTTileMultiFusionReactor();
		}
		if (this == GTBlocks.tileWorktable) {
			return new GTTileWorktable();
		}
		if (this == GTBlocks.tileDigitalChest) {
			return new GTTileDigitalChest();
		}
		if (this == GTBlocks.tileCabinet) {
			return new GTTileCabinet();
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
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		TileEntity tile = worldIn.getTileEntity(pos);
		if (tile instanceof IOwnerBlock && placer != null) {
			UUID owner = placer.getUniqueID();
			((IOwnerBlock) tile).setOwner(owner);
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItemMainhand();
		TileEntity tile = worldIn.getTileEntity(pos);
		if (hStack.isEmpty() && tile instanceof GTTilePlayerDetector
				&& ((GTTilePlayerDetector) tile).canAccess(playerIn.getUniqueID())) {
			if (playerIn.isSneaking()) {
				((GTTilePlayerDetector) tile).advanceMode();
			}
			if (((TileEntityBlock) tile).isSimulating()) {
				IC2.platform.messagePlayer(playerIn, "Mode: " + ((GTTilePlayerDetector) tile).getMode());
			}
			return true;
		}
		if (tile instanceof GTTileWorktable) {
			if (((GTTileWorktable) tile).inUse) {
				return false;
			}
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
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
	public List<Integer> getValidMetas() {
		return Arrays.asList(0);
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
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
	public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity) {
		if (entity instanceof net.minecraft.entity.boss.EntityDragon
				&& (this.equals(GTBlocks.tileMagicEnergyAbsorber) || this.equals(GTBlocks.tileMagicEnergyConverter))) {
			return false;
		}
		return super.canEntityDestroy(state, world, pos, entity);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		TileEntity tile = worldIn.getTileEntity(pos);
		if (tile instanceof IGTDisplayTickTile) {
			((IGTDisplayTickTile) tile).randomTickDisplay(stateIn, worldIn, pos, rand);
		}
	}
}