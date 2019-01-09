package gtclassic.block;

import gtclassic.GTClassic;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockBatteryTest extends GTBlockBatteryBase{

	public GTBlockBatteryTest(Block block) {
		super(block);
		this.setRegistryName("lapotronic_energy_block");
		this.setUnlocalizedName(GTClassic.MODID + ".lapotronicEnergyBlock");
		this.maxCharge = 10000000;
		this.transferLimit = 8192;
		this.tier = 4;
		this.provider = true;
		this.setCreativeTab(GTClassic.creativeTabGT);
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack) {
		return 1;
	}

	@Override
	public boolean isDamaged(ItemStack stack) {
		return true;
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}

}
