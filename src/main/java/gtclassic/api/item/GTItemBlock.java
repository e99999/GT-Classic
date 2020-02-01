package gtclassic.api.item;

import java.util.List;

import gtclassic.api.interfaces.IGTBurnableBlock;
import gtclassic.api.interfaces.IGTReaderInfoBlock;
import gtclassic.common.GTBlocks;
import ic2.core.item.block.ItemBlockRare;
import ic2.core.platform.player.PlayerHandler;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemBlock extends ItemBlockRare {

	public GTItemBlock(Block block) {
		super(block);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		PlayerHandler handler = PlayerHandler.getClientPlayerHandler();
		if (handler.hasEUReader()) {
			Block thisBlock = this.getBlock();
			if (thisBlock instanceof IGTReaderInfoBlock) {
				((IGTReaderInfoBlock) thisBlock).addReaderInformation(stack, worldIn, tooltip, flagIn);
			}
		}
	}

	@Override
	public int getItemBurnTime(ItemStack itemstack) {
		if (this.block instanceof IGTBurnableBlock) {
			return ((IGTBurnableBlock) this.block).getBlockBurnTime(block);
		}
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return this.block == GTBlocks.superFuelMagic;
	}

	@Override
	public int getMetadata(int damage) {
		return 0;
	}

	@Override
	public int getMetadata(ItemStack stack) {
		return 0;
	}
}
