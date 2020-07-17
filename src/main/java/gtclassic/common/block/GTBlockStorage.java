package gtclassic.common.block;

import gtclassic.api.interfaces.IGTColorBlock;
import gtclassic.api.interfaces.IGTItemContainerTile;
import gtclassic.api.interfaces.IGTRecolorableStorageTile;
import gtclassic.common.GTBlocks;
import gtclassic.common.tile.GTTileCabinet;
import gtclassic.common.tile.GTTileWorktable;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class GTBlockStorage extends GTBlockMachine implements IGTColorBlock {

	private static final String PAINTED_TRUE = "tooltip.gtclassic.paintedtrue";
	private static final String PAINTED_FALSE = "tooltip.gtclassic.paintedfalse";

	public GTBlockStorage(String name, LocaleComp comp) {
		super(name, comp);
	}

	public GTBlockStorage(String name, LocaleComp comp, int tooltipSize) {
		super(name, comp, tooltipSize);
	}

	public GTBlockStorage(String name, LocaleComp comp, Material blockMat, int tooltipSize) {
		super(name, comp, blockMat, tooltipSize);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		addColorTooltipInformation(stack, tooltip);
	}

	public static void addColorTooltipInformation(ItemStack stack, List<String> tooltip) {
		NBTTagCompound nbt = StackUtil.getNbtData(stack);
		if (nbt.hasKey("color")) {
			tooltip.add(I18n.format(PAINTED_TRUE));
		} else {
			tooltip.add(I18n.format(PAINTED_FALSE));
		}
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		TileEntity tile = worldIn.getTileEntity(pos);
		NBTTagCompound nbt = StackUtil.getNbtData(stack);
		if (tile instanceof IGTRecolorableStorageTile) {
			IGTRecolorableStorageTile colorTile = (IGTRecolorableStorageTile) tile;
			if (nbt.hasKey("color")) {
				colorTile.setTileColor(nbt.getInteger("color"));
			} else {
				colorTile.setTileColor(16383998);
			}
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntity tile = worldIn.getTileEntity(pos);
		if (tile instanceof GTTileWorktable && ((GTTileWorktable) tile).inUse) {
			return false;
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public boolean recolorBlock(World world, BlockPos pos, EnumFacing side, net.minecraft.item.EnumDyeColor color) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof IGTRecolorableStorageTile) {
			IGTRecolorableStorageTile colorTile = (IGTRecolorableStorageTile) tile;
			colorTile.setTileColor(getColorFromEnumDyeColor(color));
			IBlockState state = tile.getWorld().getBlockState(tile.getPos());
			world.notifyBlockUpdate(pos, state, state, 2);
			return true;
		}
		return false;
	}

	public int getColorFromEnumDyeColor(EnumDyeColor color){
		switch (color.getDyeDamage()){
			case 0: return 1908001;
			case 1: return 11546150;
			case 2: return 6192150;
			case 3: return 8606770;
			case 4: return 3949738;
			case 5: return 8991416;
			case 6: return 1481884;
			case 7: return 10329495;
			case 8: return 4673362;
			case 9: return 15961002;
			case 10: return 8439583;
			case 11: return 16701501;
			case 12: return 3847130;
			case 13: return 13061821;
			case 14: return 16351261;
			case 15: return 16383998;
			default: throw new IllegalArgumentException("Unknown Color Dye Damage"); // should never be reached
		}
	}

	@Override
	public Color getColor(IBlockState state, IBlockAccess worldIn, BlockPos pos, Block block, int index) {
		if (worldIn != null) {
			TileEntity tile = worldIn.getTileEntity(pos);
			if (tile instanceof IGTRecolorableStorageTile) {
				IGTRecolorableStorageTile colorTile = (IGTRecolorableStorageTile) tile;
				return colorTile.getTileColor();
			}
		}
		return Color.white;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> items = new ArrayList<>();
		TileEntity te = this.getLocalTile() == null ? world.getTileEntity(pos) : this.getLocalTile();
		if (te instanceof IGTItemContainerTile) {
			items.addAll(((IGTItemContainerTile) te).getInventoryDrops());
			return items;
		}
		return items;
	}

	@Override
	public List<ItemStack> getWrenchDrops(World world, BlockPos pos, IBlockState state, TileEntity te,
			EntityPlayer player, int fortune) {
		List<ItemStack> items = new ArrayList<>();
		if (te instanceof IGTItemContainerTile) {
			items.addAll(((IGTItemContainerTile) te).getDrops());
			return items;
		}
		return items;
	}

	@Override
	public IBlockState getStateFromStack(ItemStack stack) {
		return this.getStateFromMeta(stack.getMetadata());
	}

	@Override
	public boolean hasFacing() {
		return this != GTBlocks.tileWorktable;
	}

	@Override
	public TileEntityBlock createNewTileEntity(World worldIn, int meta) {
		if (this == GTBlocks.tileWorktable) {
			return new GTTileWorktable();
		}
		if (this == GTBlocks.tileCabinet) {
			return new GTTileCabinet();
		} else {
			return new TileEntityBlock();
		}
	}
}
