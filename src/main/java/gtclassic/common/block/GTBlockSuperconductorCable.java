package gtclassic.common.block;

import gtclassic.GTMod;
import gtclassic.api.block.GTBlockBaseConnect;
import gtclassic.api.interfaces.IGTColorBlock;
import gtclassic.api.interfaces.IGTItemContainerTile;
import gtclassic.api.interfaces.IGTReaderInfoBlock;
import gtclassic.api.interfaces.IGTRecolorableStorageTile;
import gtclassic.api.model.GTModelLayeredAnchoredWire;
import gtclassic.api.tile.GTTileBaseSuperconductorCable;
import gtclassic.common.GTBlocks;
import gtclassic.common.GTLang;
import gtclassic.common.tile.GTTileSuperconductorCables;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.block.wiring.BlockCable;
import ic2.core.platform.lang.storage.Ic2InfoLang;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.models.BaseModel;
import ic2.core.util.helpers.BlockStateContainerIC2;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GTBlockSuperconductorCable extends GTBlockBaseConnect implements IGTReaderInfoBlock,  IGTColorBlock {

	int size;

	public GTBlockSuperconductorCable(int size, String suffix) {
		super();
		setUnlocalizedName(GTLang.SUPERCONDUCTORCABLE);
		setRegistryName("superconductorcable" + suffix);
		this.size = size;
		this.setHardness(0.2F);
		this.setSoundType(SoundType.CLOTH);
		this.setHarvestLevel("axe", 0);
		setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainerIC2(this, active);
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
			}
		}
	}

	@Override
	public Color getColor(IBlockState state, IBlockAccess worldIn, BlockPos pos, Block block, int index) {
		if (index == 0){
			if (worldIn != null && state != null && state.getValue(active)) {
				TileEntity tile = worldIn.getTileEntity(pos);
				if (tile instanceof IGTRecolorableStorageTile) {
					IGTRecolorableStorageTile colorTile = (IGTRecolorableStorageTile) tile;
					return colorTile.getTileColor();
				}
			}
			return Color.WHITE;
		}
		return Color.WHITE;
	}

	@Override
	public boolean recolorBlock(World world, BlockPos pos, EnumFacing side, EnumDyeColor color) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof GTTileBaseSuperconductorCable) {
			GTTileBaseSuperconductorCable colorTile = (GTTileBaseSuperconductorCable) tile;
			colorTile.setTileColor(color.getColorValue());
			IBlockState state = tile.getWorld().getBlockState(tile.getPos());
			world.notifyBlockUpdate(pos, state, state, 2);
			return true;
		}
		return false;
	}

	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
		return true;
	}

	@Override
	public boolean hasFacing() {
		return false;
	}

	@Override
	public TileEntityBlock createNewTileEntity(World arg0, int arg1) {
		if (this == GTBlocks.tileSuperconductorCableIV) {
			return new GTTileSuperconductorCables.SuperconductorIV();
		}
		if (this == GTBlocks.tileSuperconductorCableHV) {
			return new GTTileSuperconductorCables.SuperconductorHV();
		}
		return new GTTileSuperconductorCables.SuperconductorMAX();
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		TileEntity tile = worldIn.getTileEntity(pos);
		if (tile instanceof GTTileBaseSuperconductorCable) {
			GTTileBaseSuperconductorCable cable = (GTTileBaseSuperconductorCable)tile;
			return state.withProperty(active, cable.getActive());
		} else {
			return super.getActualState(state, worldIn, pos);
		}
	}

	@Override
	public IBlockState getDefaultBlockState() {
		IBlockState state = this.getDefaultState().withProperty(active, false);
		if (this.hasFacing()) {
			state = state.withProperty(allFacings, EnumFacing.NORTH);
		}

		return state;
	}

	@Override
	public List<IBlockState> getValidStateList() {
		IBlockState def = this.getDefaultState();
		List<IBlockState> states = new ArrayList<>();
		states.add(def.withProperty(active, true));
		states.add(def.withProperty(active, false));

		return states;
	}

	@Override
	public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
		try {
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof GTTileBaseSuperconductorCable) {
				GTTileBaseSuperconductorCable wire = (GTTileBaseSuperconductorCable) tile;
				return new BlockStateContainerIC2.IC2BlockState(state, wire.getConnections());
			}
		} catch (Exception exception) {
		}
		return super.getExtendedState(state, world, pos);
	}

	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack) {
		state = this.getActualState(state, worldIn, pos);
		super.harvestBlock(worldIn, player, pos, state, te, stack);
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> list = new ArrayList<>();
		TileEntity te = this.getLocalTile() == null ? world.getTileEntity(pos) : this.getLocalTile();
		if (te instanceof IGTItemContainerTile){
			list.addAll(((IGTItemContainerTile) te).getDrops());
			return list;
		}
		return super.getDrops(world, pos, state, fortune);
	}

	private int[] getSize() {
		int var = (16 - this.size) / 2;
		return new int[] {var, 16 - var };
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BaseModel getModelFromState(IBlockState state) {
		return new GTModelLayeredAnchoredWire(state, Ic2Icons.getTextures("bcable")[277], getSize());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite[] getIconSheet(int arg0) {
		return null;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getTextureFromState(IBlockState state, EnumFacing enumFacing) {
		return Ic2Icons.getTextures("gtclassic_terrain")[state.getValue(active) ? 101 : 52];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getParticleTexture(IBlockState state) {
		return this.getTextureFromState(state, EnumFacing.SOUTH);
	}

	@Override
	public IBlockState getStateFromStack(ItemStack stack) {
		NBTTagCompound nbt = StackUtil.getOrCreateNbtData(stack);
		boolean isActive = nbt.hasKey("color");
		return this.getDefaultState().withProperty(active, isActive);
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		state = this.getActualState(state, world, pos);
		ItemStack stack = super.getPickBlock(state, target, world, pos, player);
		NBTTagCompound nbt = StackUtil.getOrCreateNbtData(stack);
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof GTTileBaseSuperconductorCable){
			GTTileBaseSuperconductorCable cable = (GTTileBaseSuperconductorCable) tile;
			if (cable.isColored()){
				nbt.setInteger("color", cable.getTileColor().getRGB());
			}
		}
		return stack;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);
		if (!(tile instanceof GTTileBaseSuperconductorCable)) {
			return new AxisAlignedBB(0.25D, 0.25D, 0.25D, 0.75D, 0.75D, 0.75D);
		} else {
			GTTileBaseSuperconductorCable cable = (GTTileBaseSuperconductorCable) tile;
			double thickness = this.size / 32.0D;
			double minX = 0.5D - thickness;
			double minY = 0.5D - thickness;
			double minZ = 0.5D - thickness;
			double maxX = 0.5D + thickness;
			double maxY = 0.5D + thickness;
			double maxZ = 0.5D + thickness;
			if (cable.connection.contains(EnumFacing.WEST) || cable.anchors.contains(EnumFacing.WEST)) {
				minX = 0.0D;
			}
			if (cable.connection.contains(EnumFacing.DOWN) || cable.anchors.contains(EnumFacing.DOWN)) {
				minY = 0.0D;
			}
			if (cable.connection.contains(EnumFacing.NORTH) || cable.anchors.contains(EnumFacing.NORTH)) {
				minZ = 0.0D;
			}
			if (cable.connection.contains(EnumFacing.EAST) || cable.anchors.contains(EnumFacing.EAST)) {
				maxX = 1.0D;
			}
			if (cable.connection.contains(EnumFacing.UP) || cable.anchors.contains(EnumFacing.UP)) {
				maxY = 1.0D;
			}
			if (cable.connection.contains(EnumFacing.SOUTH) || cable.anchors.contains(EnumFacing.SOUTH)) {
				maxZ = 1.0D;
			}
			return new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
		}
	}

	@Override
	public void addReaderInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (this == GTBlocks.tileSuperconductorCableMAX) {
			tooltip.add((Ic2InfoLang.euReaderCableLimit.getLocalizedFormatted(new Object[] { 134217728 })));
		}
		if (this == GTBlocks.tileSuperconductorCableIV) {
			tooltip.add((Ic2InfoLang.euReaderCableLimit.getLocalizedFormatted(new Object[] { 32769 })));
		}
		if (this == GTBlocks.tileSuperconductorCableHV) {
			tooltip.add((Ic2InfoLang.euReaderCableLimit.getLocalizedFormatted(new Object[] { 512 })));
		}
		tooltip.add((Ic2InfoLang.euReaderCableLoss.getLocalizedFormatted(new Object[] { 0.001 })));
	}



	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntity tile = worldIn.getTileEntity(pos);
		if (tile instanceof GTTileBaseSuperconductorCable) {
			GTTileBaseSuperconductorCable cable = (GTTileBaseSuperconductorCable) tile;
			ItemStack stack = playerIn.getHeldItem(hand);

			if (StackUtil.isStackEqual(stack, Ic2Items.miningPipe)) {
				EnumFacing rotation = (new BlockCable.ClickHelper(hitX, hitY, hitZ, (float) this.size / 16)).getFacing(facing);
				if (rotation != null && cable.addAnchor(rotation)) {
					if (!playerIn.capabilities.isCreativeMode) {
						stack.shrink(1);
					}

					return true;
				}
			}
		}
		return false;
	}
}
