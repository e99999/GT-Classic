package gtclassic.api.itemblock;

import java.util.List;

import gtclassic.api.interfaces.IGTReaderInfoBlock;
import ic2.core.item.block.ItemBlockRare;
import ic2.core.platform.player.PlayerHandler;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemBlockRare extends ItemBlockRare {

	public GTItemBlockRare(Block block) {
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
	public int getMetadata(int damage) {
		return 0;
	}

	@Override
	public int getMetadata(ItemStack stack) {
		return 0;
	}
}
