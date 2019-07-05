package gtclassic.itemblock;

import java.util.List;

import gtclassic.GTBlocks;
import ic2.core.item.block.ItemBlockRare;
import ic2.core.platform.lang.storage.Ic2InfoLang;
import ic2.core.platform.player.PlayerHandler;
import ic2.core.util.misc.StackUtil;
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
			if (compare(stack, GTBlocks.tileCentrifuge) || compare(stack, GTBlocks.tilePlayerDetector)) {
				tooltip.add((Ic2InfoLang.euReaderSinkInfo.getLocalizedFormatted(new Object[] { 32 })));
			}
			if (compare(stack, GTBlocks.tileBlastFurnace)) {
				tooltip.add((Ic2InfoLang.euReaderSinkInfo.getLocalizedFormatted(new Object[] { 128 })));
			}
			if (compare(stack, GTBlocks.tileChargeOmat) || compare(stack, GTBlocks.tileUUAssembler)) {
				tooltip.add((Ic2InfoLang.euReaderSinkInfo.getLocalizedFormatted(new Object[] { 2048 })));
			}
			if (compare(stack, GTBlocks.tileFusionComputer)) {
				tooltip.add((Ic2InfoLang.euReaderSinkInfo.getLocalizedFormatted(new Object[] { 32784 })));
			}
			if (compare(stack, GTBlocks.tileLightningRod)) {
				tooltip.add((Ic2InfoLang.electricProduction.getLocalizedFormatted(new Object[] { 25000000 })
						+ " each strike"));
				tooltip.add((Ic2InfoLang.euOutput.getLocalizedFormatted(new Object[] { 8196 })));
			}
			if (compare(stack, GTBlocks.tileAESU)) {
				tooltip.add((Ic2InfoLang.electricMaxIn.getLocalizedFormatted(new Object[] { 2048 })));
				tooltip.add((Ic2InfoLang.electricMaxStorage.getLocalizedFormatted(new Object[] { 100000000 })));
			}
			if (compare(stack, GTBlocks.tileIDSU)) {
				tooltip.add((Ic2InfoLang.electricMaxIn.getLocalizedFormatted(new Object[] { 2048 })));
				tooltip.add((Ic2InfoLang.electricMaxStorage.getLocalizedFormatted(new Object[] { 400000000 })));
			}
			if (compare(stack, GTBlocks.tileFabricator)) {
				tooltip.add((Ic2InfoLang.euReaderSinkInfo.getLocalizedFormatted(new Object[] { 131072 })));
			}
			if (compare(stack, GTBlocks.tileTranslocator) || compare(stack, GTBlocks.tileBufferSmall)
					|| compare(stack, GTBlocks.tileBufferLarge)) {
				tooltip.add((Ic2InfoLang.euReaderCableLimit.getLocalizedFormatted(new Object[] { 32 })));
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

	public boolean compare(ItemStack stack, Block block) {
		return StackUtil.isStackEqual(stack, new ItemStack(block));
	}
}
