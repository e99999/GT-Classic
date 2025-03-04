package gtclassic.common.block;

import java.util.List;

import gtclassic.GTMod;
import gtclassic.api.block.GTBlockBaseOre;
import gtclassic.api.world.GTBedrockOreHandler;
import gtclassic.common.GTLang;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GTBlockOreBedrock extends GTBlockBaseOre {

	String name;
	int id;

	public GTBlockOreBedrock(String name, int id) {
		super(GTBlockBaseOre.BackgroundSet.BEDROCK);
		this.name = name;
		this.id = id;
		setRegistryName(this.name.toLowerCase() + "_bedrockore");
		setTranslationKey(GTLang.ORE_BEDROCK);
		setCreativeTab(GTMod.creativeTabGT);
		setBlockUnbreakable();
		setResistance(6000000.0F);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("Contains: " + GTBedrockOreHandler.getResource(this).getDisplayName()));
	}

	@Override
	public TextureAtlasSprite getTopLayer() {
		return Ic2Icons.getTextures(GTMod.MODID + "_ores")[this.id];
	}
}
