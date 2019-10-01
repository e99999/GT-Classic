package gtclassic.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gtclassic.helpers.GTHelperData;
import gtclassic.tile.GTTileUUMAssembler;
import gtclassic.util.GTLang;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.IItemContainer;
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
import net.minecraft.world.World;

public class GTBlockUUMAssembler extends GTBlockMachine {

	String count = "digitalCount";

	public GTBlockUUMAssembler() {
		super("uumassembler", GTLang.UUM_ASSEMBLER);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (stack.hasTagCompound()) {
			NBTTagCompound nbt;
			nbt = StackUtil.getNbtData(stack);
			if (nbt.hasKey(count)) {
				tooltip.add(TextFormatting.AQUA + I18n.format(nbt.getInteger(count) + " of UU-Matter stored"));
			}
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public TileEntityBlock createNewTileEntity(World worldIn, int meta) {
		return new GTTileUUMAssembler();
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
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		TileEntity tile = worldIn.getTileEntity(pos);
		NBTTagCompound nbt;
		if (tile instanceof GTTileUUMAssembler) {
			GTTileUUMAssembler uumassembler = (GTTileUUMAssembler) tile;
			if (stack.hasTagCompound()) {
				nbt = StackUtil.getNbtData(stack);
				if (nbt.hasKey(count)) {
					uumassembler.setDigtialCount(nbt.getInteger(count));
				}
				if (nbt.hasKey("energy")) {
		              uumassembler.energy = nbt.getInteger("energy");
		        }
				if (nbt.hasKey("ItemsStored")) {
		               GTHelperData.readFromNBT(nbt.getCompoundTag("ItemsStored"), uumassembler);
		        }
			}
		}
	}
}
