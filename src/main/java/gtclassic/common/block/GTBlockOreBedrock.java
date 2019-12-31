package gtclassic.common.block;

import java.awt.Color;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.api.block.GTBlockBaseOre;
import gtclassic.api.interfaces.IGTBedrockMineableBlock;
import gtclassic.api.material.GTMaterial;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.common.GTBlocks;
import gtclassic.common.GTLang;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GTBlockOreBedrock extends GTBlockBaseOre implements IGTBedrockMineableBlock {

	String name;
	GTMaterial mat;

	public GTBlockOreBedrock(String name, Color color, TextureSet set) {
		super(color, set, GTBlockBaseOre.BackgroundSet.BEDROCK);
		this.name = name;
		setRegistryName(this.name.toLowerCase() + "_bedrockore");
		setUnlocalizedName(GTLang.ORE_BEDROCK);
		setCreativeTab(GTMod.creativeTabGT);
	}

	public GTBlockOreBedrock(GTMaterial mat, TextureSet set) {
		this(mat.getDisplayName(), mat.getColor(), set);
		this.mat = mat;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("Contains: " + this.getMineableResource().getDisplayName()));
	}

	@Override
	public ItemStack getMineableResource() {
		if (this == GTBlocks.oreBedrockGold) {
			return Ic2Items.goldDust.copy();
		}
		if (this == GTBlocks.oreBedrockIron) {
			return Ic2Items.ironDust.copy();
		}
		if (this == GTBlocks.oreBedrockCoal) {
			return Ic2Items.coalDust.copy();
		}
		if (this == GTBlocks.oreBedrockLapis) {
			return new ItemStack(Items.DYE, 1, 4);
		}
		if (this == GTBlocks.oreBedrockDiamond) {
			return GTMaterialGen.get(Items.DIAMOND);
		}
		if (this == GTBlocks.oreBedrockRedstone) {
			return GTMaterialGen.get(Items.REDSTONE);
		}
		if (this == GTBlocks.oreBedrockCopper) {
			return Ic2Items.copperDust;
		}
		if (this == GTBlocks.oreBedrockTin) {
			return Ic2Items.tinDust;
		}
		if (this == GTBlocks.oreBedrockSilver) {
			return Ic2Items.silverDust;
		}
		if (this.mat != null) {
			if (this.mat == GTMaterial.Sheldonite) {
				return GTMaterialGen.getDust(GTMaterial.Platinum, 1);
			}
			return GTMaterialGen.getDust(this.mat, 1);
		}
		return ItemStack.EMPTY;
	}
}
