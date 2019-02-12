package gtclassic.item;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import ic2.core.item.base.ItemIC2;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemEchophone extends ItemIC2 {

	public GTItemEchophone() {
		this.maxStackSize = 1;
		this.setCreativeTab(GTMod.creativeTabGT);
		this.setRegistryName("echophone_item");
		this.setUnlocalizedName(GTMod.MODID + ".echophoneItem");
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.RED + I18n.format("tooltip." + GTMod.MODID + ".wip"));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int meta) {
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[57];
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	public int getTextureEntry(int var1) {
		return 0;
	}

}
