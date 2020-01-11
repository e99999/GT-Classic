package gtclassic.common.util.modeltest;

import java.util.List;
import java.util.Random;

import gtclassic.GTMod;
import gtclassic.api.block.GTBlockBaseConnect;
import gtclassic.api.interfaces.IGTDataNetObject;
import gtclassic.api.interfaces.IGTReaderInfoBlock;
import gtclassic.common.GTLang;
import ic2.core.IC2;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.models.BaseModel;
import ic2.core.util.helpers.BlockStateContainerIC2;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
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

public class GTBlockDataNode extends GTBlockBaseConnect implements IGTReaderInfoBlock, IGTDataNetObject {

	int size;
	String name;
	int id;

	public GTBlockDataNode(String name, int id, LocaleComp comp) {
		super();
		this.name = name;
		this.id = id;
		setUnlocalizedName(GTLang.TEST);
		setRegistryName(this.name);
		this.size = 4;
		this.setHardness(0.2F);
		this.setSoundType(SoundType.CLOTH);
		this.setHarvestLevel("axe", 0);
		setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		TileEntity tile = worldIn.getTileEntity(pos);
		if (!IC2.platform.isRendering()) {
			if (tile instanceof TileEntityBlock) {
				TileEntityBlock block = (TileEntityBlock) tile;
				if (placer == null) {
					block.setFacing(EnumFacing.NORTH);
				} else {
					int pitch = Math.round(placer.rotationPitch);
					if (pitch >= 65) {
						block.setFacing(EnumFacing.UP);
					} else if (pitch <= -65) {
						block.setFacing(EnumFacing.DOWN);
					} else {
						block.setFacing(EnumFacing.fromAngle((double) placer.rotationYaw).getOpposite());
					}
				}
				if (stack.hasDisplayName()) {
					block.setCustomName(stack.getDisplayName());
				}
			}
		}
		if (tile instanceof GTTileBaseDataNode) {
			GTTileBaseDataNode test = (GTTileBaseDataNode) tile;
			test.anchors = RotationList.ofFacings(test.getFacing());
		}
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
		return new GTTileBaseDataNode();
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	private int[] getSize() {
		int var = (16 - this.size) / 2;
		return new int[] { 0 + var, 16 - var };
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BaseModel getModelFromState(IBlockState state) {
		return new GTModelDataNode(state, Ic2Icons.getTextures(GTMod.MODID + "_blocks")[this.id], getSize());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite[] getIconSheet(int arg0) {
		return null;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getTextureFromState(IBlockState iBlockState, EnumFacing enumFacing) {
		return Ic2Icons.getTextures(GTMod.MODID + "_blocks")[5];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getParticleTexture(IBlockState state) {
		return this.getTextureFromState(state, EnumFacing.SOUTH);
	}

	@Override
	public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
		try {
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof GTTileBaseDataNode) {
				GTTileBaseDataNode wire = (GTTileBaseDataNode) tile;
				return new BlockStateContainerIC2.IC2BlockState(state, wire.getConnections());
			}
		} catch (Exception exception) {
		}
		return super.getExtendedState(state, world, pos);
	}

	@Override
	public void addReaderInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
	}
}
