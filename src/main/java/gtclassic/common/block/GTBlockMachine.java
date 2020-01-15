package gtclassic.common.block;

import java.util.List;

import gtclassic.GTMod;
import gtclassic.api.block.GTBlockBaseMachine;
import gtclassic.api.helpers.GTHelperStack;
import gtclassic.api.interfaces.IGTReaderInfoBlock;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.common.GTBlocks;
import gtclassic.common.tile.GTTileAutocrafter;
import gtclassic.common.tile.GTTileBedrockMiner;
import gtclassic.common.tile.GTTileCentrifuge;
import gtclassic.common.tile.GTTileChargeOMat;
import gtclassic.common.tile.GTTileDigitalChest;
import gtclassic.common.tile.GTTileDisassembler;
import gtclassic.common.tile.GTTileDragonEggEnergySiphon;
import gtclassic.common.tile.GTTileEchotron;
import gtclassic.common.tile.GTTileEnergyTransmitter;
import gtclassic.common.tile.GTTileMagicEnergyAbsorber;
import gtclassic.common.tile.GTTileMagicEnergyConverter;
import gtclassic.common.tile.GTTileMatterFabricator;
import gtclassic.common.tile.GTTileMobRepeller;
import gtclassic.common.tile.GTTilePlayerDetector;
import gtclassic.common.tile.GTTileUUMAssembler;
import gtclassic.common.tile.datanet.GTTileComputerCube;
import gtclassic.common.tile.multi.GTTileMultiFusionReactor;
import gtclassic.common.tile.multi.GTTileMultiLightningRod;
import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.lang.storage.Ic2InfoLang;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
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

public class GTBlockMachine extends GTBlockBaseMachine implements IGTReaderInfoBlock {

	String name;

	public GTBlockMachine(String name, LocaleComp comp) {
		this(name, comp, 1);
	}

	public GTBlockMachine(String name, LocaleComp comp, int tooltipSize) {
		super(Material.IRON, comp, tooltipSize);
		this.name = name;
		setRegistryName(this.name.toLowerCase());
		setCreativeTab(GTMod.creativeTabGT);
		setHardness(100.0F);
		setResistance(20.0F);
		setSoundType(SoundType.METAL);
	}

	public GTBlockMachine(String name, LocaleComp comp, Material blockMat, int tooltipSize) {
		super(blockMat, comp, tooltipSize);
		this.name = name;
		setRegistryName(this.name.toLowerCase());
		setCreativeTab(GTMod.creativeTabGT);
		setResistance(20.0F);
		setSoundType(SoundType.METAL);
	}

	@Override
	public boolean hasFacing() {
		return !(this == GTBlocks.tilePlayerDetector || this == GTBlocks.tileEchotron
				|| this == GTBlocks.tileMobRepeller || this == GTBlocks.tileFabricator
				|| this == GTBlocks.tileEnergyTransmitter || this == GTBlocks.tileDragonEggEnergySiphon
				|| this == GTBlocks.tileMagicEnergyAbsorber);
	}

	@Override
	public void addReaderInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (this == GTBlocks.tileCentrifuge || this == GTBlocks.tilePlayerDetector || this == GTBlocks.tileEchotron
				|| this == GTBlocks.tileMobRepeller || this == GTBlocks.tileDisassembler) {
			tooltip.add((Ic2InfoLang.euReaderSinkInfo.getLocalizedFormatted(new Object[] { 32 })));
		}
		if (this == GTBlocks.tileAutocrafter) {
			tooltip.add((Ic2InfoLang.euReaderSinkInfo.getLocalizedFormatted(new Object[] { 32 })));
			tooltip.add("Energy Usage: 50 EU Per Craft");
		}
		if (this == GTBlocks.tileChargeOmat) {
			tooltip.add((Ic2InfoLang.euReaderSinkInfo.getLocalizedFormatted(new Object[] { 2048 })));
		}
		if (this == GTBlocks.tileFusionReactor) {
			tooltip.add((Ic2InfoLang.euReaderSinkInfo.getLocalizedFormatted(new Object[] { 8192 })));
			tooltip.add((Ic2InfoLang.euOutput.getLocalizedFormatted(new Object[] { 134217728 })));
		}
		if (this == GTBlocks.tileLightningRod) {
			tooltip.add((Ic2InfoLang.electricProduction.getLocalizedFormatted(new Object[] { 25000000 })
					+ " each strike"));
			tooltip.add((Ic2InfoLang.euOutput.getLocalizedFormatted(new Object[] { 8192 })));
		}
		if (this == GTBlocks.tileDragonEggEnergySiphon) {
			tooltip.add((Ic2InfoLang.electricProduction.getLocalizedFormatted(new Object[] { 128.0 })));
		}
		if (this == GTBlocks.tileBedrockMiner) {
			tooltip.add((Ic2InfoLang.euReaderSinkInfo.getLocalizedFormatted(new Object[] { 512 })));
			tooltip.add("Energy Usage: 4096 EU Per Operation");
		}
		if (this == GTBlocks.tileComputer) {
			tooltip.add((Ic2InfoLang.euReaderSinkInfo.getLocalizedFormatted(new Object[] { 512 })));
			tooltip.add("Energy Usage: 1 EU Per Network Node");
		}
		if (this == GTBlocks.tileEnergyTransmitter) {
			tooltip.add((Ic2InfoLang.electricMaxIn.getLocalizedFormatted(new Object[] { 512 })));
			tooltip.add((Ic2InfoLang.electricMaxStorage.getLocalizedFormatted(new Object[] { 1000000 })));
			tooltip.add((Ic2InfoLang.euOutput.getLocalizedFormatted(new Object[] {
					Ic2InfoLang.electricTransferRateVariable.getLocalized() })));
			tooltip.add((Ic2InfoLang.euReaderCableLoss.getLocalizedFormatted(new Object[] { 0.010 })));
		}
		if (this == GTBlocks.tileMagicEnergyConverter) {
			tooltip.add((Ic2InfoLang.electricProduction.getLocalizedFormatted(new Object[] { 24.0 })));
		}
		if (this == GTBlocks.tileMagicEnergyAbsorber) {
			tooltip.add((Ic2InfoLang.electricProduction.getLocalizedFormatted(new Object[] {
					Ic2InfoLang.electricTransferRateVariable.getLocalized() })));
			tooltip.add((Ic2InfoLang.euOutput.getLocalizedFormatted(new Object[] { 128 })));
		}
		if (this == GTBlocks.tileFabricator) {
			tooltip.add((Ic2InfoLang.euReaderSinkInfo.getLocalizedFormatted(new Object[] { 134217728 })));
		}
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
		if (this == GTBlocks.tileEnergyTransmitter) {
			return new GTTileEnergyTransmitter();
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
		if (this == GTBlocks.tileDisassembler) {
			return new GTTileDisassembler();
		}
		if (this == GTBlocks.tileBedrockMiner) {
			return new GTTileBedrockMiner();
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
		if (this == GTBlocks.tileDigitalChest) {
			return new GTTileDigitalChest();
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
	public IBlockState getStateFromStack(ItemStack stack) {
		if (thisIs(stack, GTBlocks.tileCentrifuge) || thisIs(stack, GTBlocks.tileFabricator)
				|| thisIs(stack, GTBlocks.tileComputer) || thisIs(stack, GTBlocks.tileDisassembler)
				|| thisIs(stack, GTBlocks.tileDragonEggEnergySiphon) || thisIs(stack, GTBlocks.tileMagicEnergyAbsorber)
				|| thisIs(stack, GTBlocks.tileMagicEnergyConverter) || thisIs(stack, GTBlocks.tileFusionReactor)
				|| thisIs(stack, GTBlocks.tileBedrockMiner)) {
			return this.getDefaultBlockState().withProperty(active, true);
		}
		return this.getStateFromMeta(stack.getMetadata());
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
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity) {
		if (entity instanceof net.minecraft.entity.boss.EntityDragon
				&& (this.equals(GTBlocks.tileMagicEnergyAbsorber) || this.equals(GTBlocks.tileMagicEnergyConverter))) {
			return false;
		}
		return super.canEntityDestroy(state, world, pos, entity);
	}

	public boolean thisIs(ItemStack stack, GTBlockMachine block) {
		return (GTHelperStack.isEqual(stack, GTMaterialGen.get(block)));
	}
}