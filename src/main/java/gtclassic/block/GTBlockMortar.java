package gtclassic.block;

import java.util.List;

import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import gtclassic.material.GTMaterialGen;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.oredict.OreDictionary;

public class GTBlockMortar extends GTBlockTileCustom {

	public GTBlockMortar() {
		super("block_mortar", 10, 4, false);
		setSoundType(SoundType.STONE);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format(this.getUnlocalizedName().replace("tile", "tooltip")));
	}

	/*
	 * This where the logic for what happens when you click a mortar
	 */
	@Override
	public boolean onBlockActivated(World w, BlockPos p, IBlockState state, EntityPlayer e, EnumHand h,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		// Special cases
		addDrops(w, p, e, h, "ingotRefinedIron", GTMaterialGen.getSmallDust(GTMaterial.Iron, 4));
		addDrops(w, p, e, h, "ingotWroughtIron", GTMaterialGen.getSmallDust(GTMaterial.Iron, 4));
		addDrops(w, p, e, h, "ingotAluminum", GTMaterialGen.getSmallDust(GTMaterial.Aluminium, 4));
		addDrops(w, p, e, h, "bone", new ItemStack(Items.DYE, 4, 15));
		addDrops(w, p, e, h, "gravel", new ItemStack(Items.FLINT));
		addDrops(w, p, e, h, GTMaterialGen.get(Items.COAL), GTMaterialGen.getSmallDust(GTMaterial.Coal, 4));
		addDrops(w, p, e, h, new ItemStack(Items.COAL, 1, 1), GTMaterialGen.getSmallDust(GTMaterial.Charcoal, 4));
		addDrops(w, p, e, h, GTMaterialGen.get(Items.CLAY_BALL), GTMaterialGen.getSmallDust(GTMaterial.Clay, 1));
		addDrops(w, p, e, h, GTMaterialGen.get(Blocks.CLAY), GTMaterialGen.getSmallDust(GTMaterial.Clay, 4));
		addDrops(w, p, e, h, GTMaterialGen.getIc2(Ic2Items.stickyResin, 1), GTMaterialGen.getSmallDust(GTMaterial.DirtyResin, 6));
		for (GTMaterial mat : GTMaterial.values()) {
			if (mat.hasFlag(GTMaterialFlag.SMALLDUST)) {
				// Ores to dust
				addDrops(w, p, e, h, "ore" + mat.getDisplayName(), GTMaterialGen.getSmallDust(mat, 6));
				addDrops(w, p, e, h, "crushedPurified" + mat.getDisplayName(), GTMaterialGen.getSmallDust(mat, 5));
				// Ingots to small dusts
				if (GTMaterial.isLowHeat(mat)) {
					addDrops(w, p, e, h, "ingot" + mat.getDisplayName(), GTMaterialGen.getSmallDust(mat, 4));
				}
			}
		}
		return true;
	}

	public static String getOreName(ItemStack stack) {
		if (!stack.isEmpty() && (OreDictionary.getOreIDs(stack).length > 0)) {
			return OreDictionary.getOreName(OreDictionary.getOreIDs(stack)[0]);
		} else {
			return "null";
		}
	}

	public boolean addDrops(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand, String input,
			ItemStack... outputs) {
		ItemStack handstack = playerIn.getHeldItemMainhand();
		if (getOreName(handstack).equals(input)) {
			playerIn.getHeldItem(hand).shrink(1);
			for (ItemStack stack : outputs) {
				ItemHandlerHelper.giveItemToPlayer(playerIn, stack);
			}
			// FoodStats food = playerIn.getFoodStats();
			// food.setFoodLevel(food.getFoodLevel()-1);
			worldIn.playSound(playerIn, pos, SoundEvents.BLOCK_ANVIL_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
		}
		return true;
	}

	public boolean addDrops(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand, ItemStack input,
			ItemStack... outputs) {
		ItemStack handstack = playerIn.getHeldItemMainhand();
		if (ItemStack.areItemsEqual(handstack, input)) {
			playerIn.getHeldItem(hand).shrink(1);
			for (ItemStack stack : outputs) {
				ItemHandlerHelper.giveItemToPlayer(playerIn, stack);
			}
			worldIn.playSound(playerIn, pos, SoundEvents.BLOCK_ANVIL_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
		}
		return true;
	}
}
