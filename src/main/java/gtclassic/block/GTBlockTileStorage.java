package gtclassic.block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.color.GTColorBlockInterface;
import gtclassic.material.GTMaterial;
import gtclassic.tile.GTTileBookshelf;
import gtclassic.tile.GTTileLargeChest;
import gtclassic.tile.GTTileSmallChest;
import gtclassic.tile.GTTileWorkbench;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.block.Block;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class GTBlockTileStorage extends GTBlockMultiID implements GTColorBlockInterface {

	String name;
	String texture;
	GTMaterial material;
	int type;

	public GTBlockTileStorage(GTMaterial mat, int type) {
		super(Material.IRON);
		this.material = mat;
		this.type = type;
		this.name = material.getName() + "_storage_" + type;
		setRegistryName(this.name.toLowerCase());
		setUnlocalizedName(GTMod.MODID + "." + this.name.toLowerCase());
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
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.ITALIC + I18n.format("tooltip." + GTMod.MODID + ".nomobs"));
		tooltip.add(TextFormatting.ITALIC + I18n.format("Blast Resistance (" + this.blockResistance + ")"));
	}

	@Override
	public TileEntityBlock createNewTileEntity(World arg0, int arg1) {
		if (this.type == 0) {
			return new GTTileSmallChest();
		}
		if (this.type == 1) {
			return new GTTileLargeChest();
		}
		if (this.type == 2) {
			return new GTTileBookshelf();
		}
		if (this.type == 3) {
			return new GTTileWorkbench();
		} else {
			return new TileEntityBlock();
		}
	}

	@Override
	public TextureAtlasSprite[] getIconSheet(int arg0) {
		if (this.type == 0) {
			return Ic2Icons.getTextures("tile_smallchest");
		}
		if (this.type == 1) {
			return Ic2Icons.getTextures("tile_largechest");
		}
		if (this.type == 2) {
			return Ic2Icons.getTextures("tile_bookshelf");
		}
		if (this.type == 3) {
			return Ic2Icons.getTextures("tile_workbench");
		} else {
			return Ic2Icons.getTextures("builder");
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

	@Override
	public float getEnchantPowerBonus(World world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);
		if ((tile instanceof GTTileBookshelf) && (((GTTileBookshelf) tile).isActive)) {
			return 2;
		} else {
			return 0;
		}
	}

	@Override
	public Color getColor(Block block, int index) {
		return this.material.getColor();
	}

	public int getType() {
		return this.type;
	}

	public GTMaterial getMaterial() {
		return this.material;
	}
}
