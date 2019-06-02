package gtclassic.fluid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gtclassic.GTMod;
import gtclassic.material.GTMaterial;
import gtclassic.util.recipe.GTMultiInputRecipeList;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.platform.lang.ILocaleBlock;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Lang;
import ic2.core.platform.textures.models.BaseModel;
import ic2.core.platform.textures.obj.ICustomModeledBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockHardenedClay;
import net.minecraft.block.BlockStainedHardenedClay;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTFluidBlockDryable extends BlockFluidClassic implements ILocaleBlock, ICustomModeledBlock {

	LocaleComp comp;
	Block result;
	GTMaterial mat;

	public static final GTMultiInputRecipeList DRYING_RECIPE_LIST = new GTMultiInputRecipeList("gt.drying");

	public GTFluidBlockDryable(GTMaterial mat, Block result) {
		super(FluidRegistry.getFluid(mat.getDisplayName().toLowerCase()), Material.WATER);
		setRegistryName(mat.getDisplayName().toLowerCase() + "_fluidblock");
		setUnlocalizedName(GTMod.MODID + "." + mat.getDisplayName().toLowerCase() + "_fluidblock");
		setCreativeTab(GTMod.creativeTabGT);
		this.mat = mat;
		this.comp = Ic2Lang.nullKey;
		this.result = result;
		this.setTickRandomly(true);
		this.setTickRate(10);
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

		if (state.getValue(LEVEL) == 0) {
			IBlockState down = worldIn.getBlockState(pos.down(1));
			if (isDryingBlock(down) && isCorrectEnviornment(worldIn, pos)) {
				if (rand.nextInt(7) == 0) {
					worldIn.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_STONE_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
					worldIn.setBlockState(pos, result.getDefaultState());
				}
			}
		}
		super.updateTick(worldIn, pos, state, rand);
	}

	public List<IBlockState> getValidModelStates() {
		return this.getBlockState().getValidStates();
	}

	public boolean isDryingBlock(IBlockState state) {
		return isHardClay(state) || isConcrete(state);
	}

	public boolean isHardClay(IBlockState state) {
		Block block = state.getBlock();
		return block instanceof BlockHardenedClay || block instanceof BlockStainedHardenedClay;
	}

	@SuppressWarnings("deprecation")
	public boolean isConcrete(IBlockState state) {
		Block block = state.getBlock();
		return block instanceof BlockColored && block.getMaterial(state).equals(Material.ROCK);
	}

	public boolean isCorrectEnviornment(World worldIn, BlockPos pos) {
		if (worldIn.provider.hasSkyLight()) {
			if (!worldIn.canBlockSeeSky(pos)) {
				return false;
			} else {
				Biome biome = worldIn.getBiome(pos);
				if (BiomeDictionary.hasType(biome, Type.COLD)) {
					return false;
				}
				if (BiomeDictionary.hasType(biome, Type.HOT) && !biome.canRain()) {
					return true;
				} else {
					return !worldIn.isRaining() && !worldIn.isThundering();
				}
			}
		} else {
			return false;
		}
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

	public Block getResult() {
		return this.result;
	}

	/**
	 * creates JEI integration of drying recipes
	 */
	public static void dryingUtil(ItemStack input, ItemStack output) {
		List<IRecipeInput> inputs = new ArrayList<>();
		List<ItemStack> outputs = new ArrayList<>();
		inputs.add((IRecipeInput) (new RecipeInputItemStack(input)));
		outputs.add(output);
		dryingWrapper(inputs, new MachineOutput(null, outputs));
	}

	private static void dryingWrapper(List<IRecipeInput> input, MachineOutput output) {
		DRYING_RECIPE_LIST.addRecipe(input, output, output.getAllOutputs().get(0).getDisplayName(), 0);
	}

}
