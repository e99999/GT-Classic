package gtclassic.fluid;

import java.util.List;
import java.util.Random;

import gtclassic.GTBlocks;
import gtclassic.GTMod;
import gtclassic.util.GTValues;
import ic2.core.platform.lang.ILocaleBlock;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Lang;
import ic2.core.platform.textures.models.BaseModel;
import ic2.core.platform.textures.obj.ICustomModeledBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTFluidBlockSlurry extends BlockFluidClassic implements ILocaleBlock, ICustomModeledBlock {

	LocaleComp comp;

	public GTFluidBlockSlurry() {
		super(FluidRegistry.getFluid("slurry"), Material.WATER);
		this.comp = Ic2Lang.nullKey;
		setRegistryName("fluid_slurry");
		setUnlocalizedName(GTMod.MODID + "." + "fluid_slurry");
		setCreativeTab(GTMod.creativeTabGT);
		this.setTickRandomly(true);
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

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		/*
		 * in the future this will be more complex based on enviorment and sky access
		 */
		if (worldIn.getBlockState(pos.down(1)) == Blocks.HARDENED_CLAY.getDefaultState()) {
			if (rand.nextInt(32) == 0) {
				worldIn.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_STONE_BREAK, SoundCategory.BLOCKS, 1.0F,
						1.0F);
				if (GTValues.debugMode) {
					GTMod.logger.info("Slurry block dried at: " + pos.toString());
				}

				worldIn.setBlockState(pos, GTBlocks.mudBlock.getDefaultState());
			}
		}
		super.updateTick(worldIn, pos, state, rand);
	}

	public List<IBlockState> getValidModelStates() {
		return this.getBlockState().getValidStates();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BaseModel getModelFromState(IBlockState state) {
		return new GTFluidModel(FluidRegistry.getFluid("slurry"));
	}

	@Override
	public IBlockState getStateFromStack(ItemStack stack) {
		return this.getDefaultState();
	}

}
