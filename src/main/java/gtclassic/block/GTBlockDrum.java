package gtclassic.block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gtclassic.GTMod;
import gtclassic.color.GTColorBlockInterface;
import gtclassic.material.GTMaterial;
import gtclassic.tile.GTTileDrum;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.IItemContainer;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class GTBlockDrum extends GTBlockMultiID implements GTColorBlockInterface {

	GTMaterial material;
	String name;

	public GTBlockDrum(GTMaterial mat) {
		super(Material.GROUND);
		this.material = mat;
		this.name = material.getName() + "_drum";
		setRegistryName(this.name.toLowerCase());
		setUnlocalizedName(GTMod.MODID + "." + this.name.toLowerCase());
		setCreativeTab(GTMod.creativeTabGT);
		setResistance(20.0F);
		setSoundType(SoundType.METAL);
		setHardness(0.5F);
	}

	@Override
	public List<IBlockState> getValidStates() {
		return getBlockState().getValidStates();
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		TileEntity tile = worldIn.getTileEntity(pos);
		NBTTagCompound nbt;
		if (tile instanceof GTTileDrum) {
			GTTileDrum tank = (GTTileDrum) tile;
			if (stack.hasTagCompound()) {
				nbt = StackUtil.getNbtData(stack);
				if (nbt.hasKey("Fluid")) {
					tank.getTankInstance().setFluid(FluidStack.loadFluidStackFromNBT(nbt.getCompoundTag("Fluid")));
				}
				tank.setExport(nbt.getBoolean("export"));
			}
		}
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.AQUA + I18n.format("Max: 64000 mB"));
		if (stack.hasTagCompound()) {
			NBTTagCompound nbt;
			nbt = StackUtil.getNbtData(stack);
			if (nbt.hasKey("Fluid")) {
				FluidStack fluid = FluidStack.loadFluidStackFromNBT(nbt.getCompoundTag("Fluid"));
				tooltip.add(I18n.format(fluid.amount + "mB of " + fluid.getLocalizedName()));
			}
		}
		tooltip.add(TextFormatting.YELLOW + I18n.format("Can handle any fluid"));
		tooltip.add(TextFormatting.YELLOW + I18n.format("Toggle bottom output with hammer right click"));
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	public List<ItemStack> getWrenchDrops(World world, BlockPos pos, IBlockState state, TileEntity te,
			EntityPlayer player, int fortune) {
		List<ItemStack> items = new ArrayList<ItemStack>();
		if (te instanceof IItemContainer) {
			items.addAll(((IItemContainer) te).getDrops());
		}
		return items;
	}

	@Override
	public TileEntityBlock createNewTileEntity(World arg0, int arg1) {
		return new GTTileDrum();
	}

	@Override
	public TextureAtlasSprite[] getIconSheet(int arg0) {
		return Ic2Icons.getTextures("tile_drum");
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
	public Color getColor(Block block, int index) {
		return this.material.getColor();
	}

	public GTMaterial getMaterial() {
		return this.material;
	}

}
