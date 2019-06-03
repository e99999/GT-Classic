package gtclassic.block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.color.GTColorBlockInterface;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import gtclassic.material.GTMaterialItem;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.block.machine.low.TileEntityMachineBuffer;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTBlockTileBuffer extends GTBlockMultiID implements GTColorBlockInterface {

	GTMaterial material;

	public GTBlockTileBuffer() {
		super(Material.IRON);
		this.material = GTMaterial.RefinedIron;
		setRegistryName("tile_buffer");
		setUnlocalizedName(GTMod.MODID + "." + "tile_buffer");
		setCreativeTab(GTMod.creativeTabGT);
		setBlockUnbreakable();
		setResistance(this.material.getLevel() * 8.0F);
		setSoundType(SoundType.METAL);
		setHarvestLevel("pickaxe", 2);
	}

	@Override
	public List<IBlockState> getValidStates() {
		return getBlockState().getValidStates();
	}

	@Override
	public Color getColor(Block block, int index) {
		return this.material.getColor();
	}

	@Override
	public TileEntityBlock createNewTileEntity(World arg0, int arg1) {
		return new TileEntityMachineBuffer();
	}

	@Override
	public TextureAtlasSprite[] getIconSheet(int arg0) {
		return Ic2Icons.getTextures("tile_buffer");
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
			states.add(def.withProperty(allFacings, side).withProperty(active, false));
			states.add(def.withProperty(allFacings, side).withProperty(active, true));
		}
		return states;
	}

	@Override
	@Deprecated
	public boolean canEntitySpawn(IBlockState state, Entity entityIn) {
		return false;
	}

	public GTMaterial getMaterial() {
		return this.material;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		Item activeItem = playerIn.getHeldItemMainhand().getItem();
		ItemStack activeStack = playerIn.getHeldItemMainhand();
		if (activeItem instanceof GTMaterialItem && activeStack.getCount() >= 6) {
			if (((GTMaterialItem) activeItem).getFlag() == GTMaterialFlag.PLATE) {
				playerIn.getHeldItem(hand).shrink(6);
				this.material = ((GTMaterialItem) activeItem).getMaterial();
				return true;
			}
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
}
