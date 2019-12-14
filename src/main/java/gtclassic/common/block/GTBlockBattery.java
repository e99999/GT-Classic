package gtclassic.common.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gtclassic.GTMod;
import gtclassic.api.block.GTBlockBaseMachine;
import gtclassic.api.interfaces.IGTItemBlock;
import gtclassic.api.interfaces.IGTReaderInfoBlock;
import gtclassic.common.GTBlocks;
import gtclassic.common.itemblock.GTItemBlockBattery;
import gtclassic.common.tile.GTTileBattery;
import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.item.block.ItemBlockRare;
import ic2.core.platform.lang.storage.Ic2InfoLang;
import ic2.core.platform.registry.Ic2Lang;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.util.helpers.BlockStateContainerIC2;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.IItemContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockBattery extends GTBlockBaseMachine implements IGTItemBlock, IGTReaderInfoBlock {

	public int maxCharge;
	public int transferLimit;
	public int tier;
	public String name;
	public static PropertyInteger chargelevel = PropertyInteger.create("chargelevel", 0, 4);

	public GTBlockBattery(String name) {
		super(Material.GROUND, Ic2Lang.nullKey, 3);
		this.maxCharge = 80000;
		this.transferLimit = 32;
		this.tier = 1;
		this.name = name;
		this.setUnlocalizedName(GTMod.MODID + "." + name);
		this.setRegistryName(name.toLowerCase());
		this.setResistance(20.0F);
		this.setSoundType(SoundType.METAL);
		this.setCreativeTab(GTMod.creativeTabGT);
		this.setHardness(0.2F);
		this.setSoundType(SoundType.CLOTH);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite[] getIconSheet(int meta) {
		return Ic2Icons.getTextures(GTMod.MODID + "_" + this.name);
	}

	@Override
	public int getMaxSheetSize(int meta) {
		return 5;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("tooltip.gtclassic.batteryblock0"));
		tooltip.add(I18n.format("tooltip.gtclassic.batteryblock1"));
		tooltip.add(I18n.format("tooltip.gtclassic.batteryblock2"));
	}

	@Override
	public void addReaderInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (this == GTBlocks.tileBatteryLV) {
			tooltip.add((Ic2InfoLang.electricMaxIn.getLocalizedFormatted(new Object[] { 32 })));
			tooltip.add((Ic2InfoLang.electricMaxStorage.getLocalizedFormatted(new Object[] { 80000 })));
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainerIC2(this, new IProperty[] { allFacings, active, chargelevel });
	}

	@Override
	public IBlockState getDefaultBlockState() {
		return super.getDefaultBlockState().withProperty(chargelevel, 0);
	}

	@Override
	public Class<? extends ItemBlockRare> getCustomItemBlock() {
		return GTItemBlockBattery.class;
	}

	@Override
	public TileEntityBlock createNewTileEntity(World world, int arg1) {
		return new GTTileBattery();
	}

	@Override
	public IBlockState getStateFromStack(ItemStack stack) {
		NBTTagCompound nbt = StackUtil.getOrCreateNbtData(stack);
		if (nbt.hasKey("charge")) {
			return this.getDefaultBlockState().withProperty(chargelevel, getStateFromCharge(nbt.getInteger("charge"), 80000));
		}
		return this.getStateFromMeta(stack.getMetadata());
	}

	public int getStateFromCharge(int energy, int maxEnergy) {
		double half = maxEnergy * .5;
		double partial = maxEnergy * .75;
		if (energy == 0) {
			return 0;
		}
		if (energy > 0 && energy < half) {
			return 1;
		}
		if (energy >= half && energy < partial) {
			return 2;
		}
		if (energy >= partial && energy < maxEnergy) {
			return 3;
		}
		return 4;
	}

	@Override
	public List<IBlockState> getValidStateList() {
		List<IBlockState> states = new ArrayList<>();
		IBlockState def = this.getDefaultState();
		int[] arr = new int[] { 0, 1, 2, 3, 4 };
		int arrLength = arr.length;
		int i;
		EnumFacing[] facings;
		int facingsLength;
		int j;
		EnumFacing side;
		for (i = 0; i < arrLength; ++i) {
			facings = EnumFacing.VALUES;
			facingsLength = facings.length;
			for (j = 0; j < facingsLength; ++j) {
				side = facings[j];
				for (int x = 0; x < 5; ++x) {
					states.add(def.withProperty(allFacings, side).withProperty(chargelevel, x));
				}
			}
		}
		return states;
	}

	@Override
	public int getIconMeta(IBlockState state) {
		return (Integer) state.getValue(chargelevel);
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		IBlockState result = super.getActualState(state, worldIn, pos);
		TileEntityBlock tile = (TileEntityBlock) worldIn.getTileEntity(pos);
		if (tile instanceof GTTileBattery) {
			GTTileBattery block = (GTTileBattery) tile;
			result = result.withProperty(chargelevel, block.state);
		}
		return result;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		if (!IC2.platform.isRendering()) {
			TileEntity tile = worldIn.getTileEntity(pos);
			if (tile instanceof GTTileBattery) {
				((GTTileBattery) tile).setItem(stack.copy());
				((GTTileBattery) tile).setElectricTileInfo(this.tier, this.maxCharge, this.transferLimit);
			}
		}
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}

	@Override
	public List<ItemStack> getWrenchDrops(World world, BlockPos pos, IBlockState state, TileEntity te,
			EntityPlayer player, int fortune) {
		List<ItemStack> items = new ArrayList<>();
		if (te instanceof IItemContainer) {
			items.addAll(((IItemContainer) te).getDrops());
		}
		return items;
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}
}
