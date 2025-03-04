package gtclassic.common.block;

import java.util.ArrayList;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.api.block.GTBlockBase;
import gtclassic.api.helpers.GTHelperMath;
import gtclassic.api.model.GTModelMortar;
import gtclassic.api.recipe.GTRecipeMachineHandler;
import gtclassic.api.recipe.GTRecipeMultiInputList;
import gtclassic.api.recipe.GTRecipeMultiInputList.MultiRecipe;
import gtclassic.common.GTBlocks;
import ic2.api.recipe.IRecipeInput;
import ic2.core.IC2;
import ic2.core.platform.textures.models.BaseModel;
import ic2.core.platform.textures.obj.ICustomModeledBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

public class GTBlockMortar extends GTBlockBase implements ICustomModeledBlock {

	static final AxisAlignedBB AABB_MORTAR = GTHelperMath.createAABBFromPixelsCentered(12, 10);
	public static final List<IRecipeInput> INPUT_LIST = new ArrayList<>();
	public static final GTRecipeMultiInputList RECIPE_LIST = new GTRecipeMultiInputList("gt.mortar", 1);

	public GTBlockMortar(String name, String tool) {
		super(Material.GROUND);
		setRegistryName(name);
		setTranslationKey(GTMod.MODID + "." + name);
		setCreativeTab(GTMod.creativeTabGT);
		setSoundType(SoundType.METAL);
		setResistance(10.0F);
		setHardness(1.0F);
		setHarvestLevel(tool, 0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format(this.getTranslationKey().replace("tile", "tooltip") + 0));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTextureFromState(IBlockState var1, EnumFacing var2) {
		// This is useless once the model is working
		return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("minecraft:blocks/stone");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BaseModel getModelFromState(IBlockState state) {
		return new GTModelMortar(state);
	}

	@Override
	public List<IBlockState> getValidModelStates() {
		return this.getBlockState().getValidStates();
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand h,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack playerStack = player.getHeldItemMainhand();
		if (playerStack.isEmpty()) {
			return false;
		}
		int matches = 0;
		for (IRecipeInput inputMatcher : INPUT_LIST) {
			if (inputMatcher.matches(playerStack)) {
				matches++;
			}
		}
		if (matches == 0) {
			return false;
		}
		if (IC2.platform.isSimulating()) {
			int chance = this == GTBlocks.ironMortar ? 2 : 15;
			if (worldIn.rand.nextInt(chance) != 0) {
				return true;
			}
			for (MultiRecipe recipe : RECIPE_LIST.getRecipeList()) {
				IRecipeInput inputStack = recipe.getInputs().get(0);
				ItemStack outputStack = recipe.getOutputs().getAllOutputs().get(0);
				if (inputStack.matches(playerStack)) {
					playerStack.shrink(inputStack.getAmount());
					ItemHandlerHelper.giveItemToPlayer(player, outputStack.copy());
				}
			}
		}
		worldIn.playSound(player, pos, SoundEvents.BLOCK_ANVIL_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
		return true;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isBlockNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, SpawnPlacementType type) {
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		return AABB_MORTAR;
	}

	public static void addRecipe(IRecipeInput[] inputs, ItemStack... outputs) {
		GTRecipeMachineHandler.addRecipe(RECIPE_LIST, inputs, GTRecipeMachineHandler.totalEu(RECIPE_LIST, 250), outputs);
		INPUT_LIST.add(inputs[0]);
	}
}
