package gtclassic.common.block;

import java.awt.Color;

import gtclassic.GTMod;
import gtclassic.api.interfaces.IGTColorBlock;
import gtclassic.api.material.GTMaterial;
import ic2.core.block.misc.BlockMiningPipe;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Lang;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class GTBlockMiningPipe extends BlockMiningPipe implements IGTColorBlock {

	private LocaleComp comp;

	public GTBlockMiningPipe() {
		this.comp = Ic2Lang.nullKey;
		setRegistryName("tungstenminingpipe");
		setTranslationKey(GTMod.MODID + ".tungstenminingpipe");
		setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public LocaleComp getName() {
		return this.comp;
	}

	public Block setTranslationKey(LocaleComp name) {
		this.comp = name;
		return super.setTranslationKey(name.getUnlocalized());
	}

	@Override
	public Block setTranslationKey(String name) {
		this.comp = new LocaleBlockComp("tile." + name);
		return super.setTranslationKey(name);
	}

	@Override
	public void onLoad() {
	}

	@Override
	public Color getColor(IBlockState state, IBlockAccess worldIn, BlockPos pos, Block block, int index) {
		return GTMaterial.Tungsten.getColor();
	}
}
