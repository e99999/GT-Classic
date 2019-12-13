package gtclassic.common.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import gtclassic.GTMod;
import gtclassic.api.interfaces.IGTItemBlock;
import gtclassic.api.tile.GTTileBatteryBase;
import gtclassic.common.GTBlocks;
import gtclassic.common.itemblock.GTItemBlockBattery;
import gtclassic.common.tile.GTTileBattery;
import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.item.block.ItemBlockRare;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.lang.storage.Ic2InfoLang;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.util.helpers.BlockStateContainerIC2;
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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockBattery extends GTBlockMachine implements IGTItemBlock {

	public static PropertyInteger chargelevel = PropertyInteger.create("chargelevel", 0, 3);

	public GTBlockBattery(String name, LocaleComp comp) {
		super(name, comp, Material.GROUND, 3);
		this.setHardness(0.2F);
		this.setSoundType(SoundType.CLOTH);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite[] getIconSheet(int meta) {
		return Ic2Icons.getTextures(GTMod.MODID + "_blocks");
	}
	
	@Override
	public int getMaxSheetSize(int meta) {
		return 16;
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
		return new BlockStateContainerIC2(this, new IProperty[] { this.getMetadataProperty(), allFacings, active,
				chargelevel });
	}

	@Override
	public IBlockState getDefaultBlockState() {
		return super.getDefaultBlockState().withProperty(chargelevel, 0);
	}

	@Override
	public List<IBlockState> getValidStateList() {
		IBlockState def = this.getDefaultState();
		List<IBlockState> states = new ArrayList<>();
		int[] array = new int[] { 0, 1, 2, 3, 5 };
		int[] var4 = array;
		int var5 = array.length;
		int var6;
		int i;
		EnumFacing[] var8;
		int var9;
		int var10;
		EnumFacing side;
		for (var6 = 0; var6 < var5; ++var6) {
			i = var4[var6];
			var8 = EnumFacing.VALUES;
			var9 = var8.length;
			for (var10 = 0; var10 < var9; ++var10) {
				side = var8[var10];
				for (int x = 0; x < 4; ++x) {
					states.add(def.withProperty(this.getMetadataProperty(), i).withProperty(allFacings, side).withProperty(chargelevel, x));
				}
			}
		}
		states.add(def.withProperty(this.getMetadataProperty(), 4));
		array = new int[] { 8, 9, 10, 11, 13 };
		var4 = array;
		var5 = array.length;
		for (var6 = 0; var6 < var5; ++var6) {
			i = var4[var6];
			var8 = EnumFacing.VALUES;
			var9 = var8.length;
			for (var10 = 0; var10 < var9; ++var10) {
				side = var8[var10];
				states.add(def.withProperty(this.getMetadataProperty(), i).withProperty(allFacings, side).withProperty(active, false));
				states.add(def.withProperty(this.getMetadataProperty(), i).withProperty(allFacings, side).withProperty(active, true));
			}
		}
		EnumFacing[] var13 = EnumFacing.VALUES;
		var5 = var13.length;
		for (var6 = 0; var6 < var5; ++var6) {
			EnumFacing s = var13[var6];
			states.add(def.withProperty(this.getMetadataProperty(), 12).withProperty(allFacings, s));
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
			if (tile instanceof GTTileBatteryBase) {
				GTTileBatteryBase block = (GTTileBatteryBase) tile;
				result = result.withProperty(chargelevel, block.state);
			}
		
		return result;
	}

	//public int getMaxSheetSize(int meta) {
	//	return meta >= 4 && meta != 5 ? 16 : 4;
	//}

	@Override
	public Class<? extends ItemBlockRare> getCustomItemBlock() {
		return GTItemBlockBattery.GTItemBlockBatteryLV.class;
	}

	@Override
	public TileEntityBlock createNewTileEntity(World world, int arg1) {
		return new GTTileBattery.GTTileBatteryLV();
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		if (!IC2.platform.isRendering()) {
			TileEntity tile = worldIn.getTileEntity(pos);
			if (tile instanceof GTTileBatteryBase) {
				((GTTileBatteryBase) tile).setItem(stack.copy());
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
