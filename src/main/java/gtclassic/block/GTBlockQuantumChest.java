package gtclassic.block;

import java.util.ArrayList;
import java.util.List;

import gtclassic.tile.GTTileQuantumChest;
import gtclassic.util.GTItemContainerInterface;
import gtclassic.util.GTLang;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class GTBlockQuantumChest extends GTBlockMachine {

	int slotInput = 0;
	int slotOutput = 1;
	int slotDisplay = 2;
	String display = "display";
	String count = "digitalCount";

	public GTBlockQuantumChest() {
		super("quantumchest", GTLang.QUANTUM_CHEST);
	}

	@Override
	public TileEntityBlock createNewTileEntity(World worldIn, int meta) {
		return new GTTileQuantumChest();
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (stack.hasTagCompound()) {
			NBTTagCompound nbt;
			nbt = StackUtil.getNbtData(stack);
			if (nbt.hasKey(display) && nbt.hasKey(count)) {
				tooltip.add(TextFormatting.AQUA + I18n.format((nbt.getInteger(count)) + " of "
						+ StackUtil.copyWithSize(new ItemStack(nbt.getCompoundTag(display)), 1).getDisplayName()));
			}
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> items = new ArrayList<>();
		TileEntity te = this.getLocalTile() == null ? world.getTileEntity(pos) : this.getLocalTile();
		if (te instanceof GTItemContainerInterface) {
			items.addAll(((GTItemContainerInterface) te).getInventoryDrops());
			return items;
		}
		return items;
	}

	@Override
	public List<ItemStack> getWrenchDrops(World world, BlockPos pos, IBlockState state, TileEntity te,
			EntityPlayer player, int fortune) {
		List<ItemStack> items = new ArrayList<>();
		if (te instanceof GTItemContainerInterface) {
			items.addAll(((GTItemContainerInterface) te).getDrops());
		}
		return items;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		TileEntity tile = worldIn.getTileEntity(pos);
		NBTTagCompound nbt;
		if (tile instanceof GTTileQuantumChest) {
			GTTileQuantumChest chest = (GTTileQuantumChest) tile;
			if (stack.hasTagCompound()) {
				nbt = StackUtil.getNbtData(stack);
				if (nbt.hasKey(display) && nbt.hasKey(count)) {
					chest.setDigtialCount(nbt.getInteger(count));
					chest.setStackInSlot(slotDisplay, StackUtil.copyWithSize(new ItemStack(nbt.getCompoundTag(display)), 1));
				}
			}
		}
	}
}
