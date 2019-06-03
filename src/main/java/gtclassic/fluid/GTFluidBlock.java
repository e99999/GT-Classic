package gtclassic.fluid;

import java.util.List;

import gtclassic.GTMod;
import gtclassic.material.GTMaterial;
import ic2.core.platform.lang.ILocaleBlock;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Lang;
import ic2.core.platform.textures.models.BaseModel;
import ic2.core.platform.textures.obj.ICustomModeledBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTFluidBlock extends BlockFluidClassic implements ILocaleBlock, ICustomModeledBlock {

	LocaleComp comp;
	GTMaterial mat;

	public GTFluidBlock(GTMaterial mat) {
		super(FluidRegistry.getFluid(mat.getDisplayName().toLowerCase()), Material.WATER);
		setRegistryName(mat.getDisplayName().toLowerCase() + "_fluidblock");
		setUnlocalizedName(GTMod.MODID + "." + mat.getDisplayName().toLowerCase() + "_fluidblock");
		setCreativeTab(GTMod.creativeTabGT);
		this.mat = mat;
		this.comp = Ic2Lang.nullKey;
	}

	public LocaleComp getName() {
		return this.comp;
	}

	public Block setUnlocalizedName(LocaleComp name) {
		this.comp = name;
		return super.setUnlocalizedName(name.getUnlocalized());
	}

	@Override
	public Block setUnlocalizedName(String name) {
		this.comp = new LocaleBlockComp("tile." + name);
		return super.setUnlocalizedName(name);
	}

	public List<IBlockState> getValidModelStates() {
		return this.getBlockState().getValidStates();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BaseModel getModelFromState(IBlockState state) {
		return new GTFluidModel(FluidRegistry.getFluid(mat.getDisplayName().toLowerCase()));
	}

	@Override
	public IBlockState getStateFromStack(ItemStack stack) {
		return this.getDefaultState();
	}

	public GTMaterial getMaterial() {
		return this.mat;
	}
}
