package gtclassic.blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTClassic;
import gtclassic.tileentity.GTTileEntityComputerCube;
import gtclassic.tileentity.GTTileEntityIndustrialCentrifuge;
import gtclassic.util.GTBlocks;
import ic2.core.block.base.BlockMultiID;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockMachine extends BlockMultiID {
	public enum GTBlockMachineVariants {
		AUTOCRAFTER, COMPUTERCUBE, CHARGEOMAT, INDUSTRIALCENTRIFUGE, MATTERFABRICATOR, UUMASSEMBLER, PLAYERDETECTOR,
		SONICTRON;
	}

	GTBlockMachineVariants variant;

	public GTBlockMachine(GTBlockMachineVariants variant) {
		super(Material.IRON);
		this.variant = variant;
		setRegistryName(variant.toString().toLowerCase());
		setUnlocalizedName(GTClassic.MODID + "." + variant.toString().toLowerCase());
		setCreativeTab(GTClassic.creativeTabGT);
		setHardness(4.0F);
		setResistance(20.0F);
		setSoundType(SoundType.METAL);
		setHarvestLevel("pickaxe", 2);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (this == GTBlocks.computerCube) {
			tooltip.add(I18n.format("tooltip." + GTClassic.MODID + ".computer"));
		}

		else if (this == GTBlocks.industrialCentrifuge) {
			tooltip.add(I18n.format("tooltip." + GTClassic.MODID + ".centrifuge"));
		}

	}

	@Override
	public List<Integer> getValidMetas() {
		return Arrays.asList(0);
	}

	@Override
	public TileEntityBlock createNewTileEntity(World worldIn, int meta) {
		if (this == GTBlocks.computerCube) {
			return new GTTileEntityComputerCube();
		}

		else if (this == GTBlocks.industrialCentrifuge) {
			return new GTTileEntityIndustrialCentrifuge();
		}

		else {
			return new TileEntityBlock();
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite[] getIconSheet(int meta) {
		if (this == GTBlocks.autoCrafter) {
			return Ic2Icons.getTextures("gtclassic_autocrafter");
		}

		else if (this == GTBlocks.chargeOMat) {
			return Ic2Icons.getTextures("gtclassic_chargeomat");
		}

		else if (this == GTBlocks.computerCube) {
			return Ic2Icons.getTextures("gtclassic_computercube");
		}

		else if (this == GTBlocks.industrialCentrifuge) {
			return Ic2Icons.getTextures("gtclassic_industrialcentrifuge");
		}

		else if (this == GTBlocks.matterFabricator) {
			return Ic2Icons.getTextures("gtclassic_matterfabricator");
		}

		else if (this == GTBlocks.uuMatterAssembler) {
			return Ic2Icons.getTextures("gtclassic_uumatterassembler");
		}

		else if (this == GTBlocks.playerDetector) {
			return Ic2Icons.getTextures("gtclassic_playerdetector");
		}

		else if (this == GTBlocks.sonictronBlock) {
			return Ic2Icons.getTextures("gtclassic_sonictron");
		}

		else {
			return Ic2Icons.getTextures("gtclassic_builder");
		}

	}

	@Override
	public int getMaxSheetSize(int meta) {
		return 1;
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
	public boolean canEntitySpawn(IBlockState state, Entity entityIn) {
		return false;
	}

}