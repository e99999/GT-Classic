package gtclassic.material;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.color.GTColorItemInterface;
import gtclassic.recipe.GTRecipeCauldron;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ILayeredItemModel;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

public class GTMaterialItem extends Item implements IStaticTexturedItem, GTColorItemInterface, ILayeredItemModel {

	private GTMaterial material;
	private GTMaterialFlag flag;

	public GTMaterialItem(GTMaterial material, GTMaterialFlag flag) {
		this.material = material;
		this.flag = flag;
		setRegistryName(this.material.getName() + this.flag.getSuffix());
		setUnlocalizedName(GTMod.MODID + "." + this.material.getName() + this.flag.getSuffix());
		setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("Melting: " + material.getTemp() + "K"));
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return GTMaterial.isRadioactive(material) ? true : super.hasEffect(stack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int i) {
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[flag.getTextureID()];
	}

	@Override
	public Color getColor(ItemStack stack, int index) {
		if (index == 0) {
			return this.material.getColor();
		} else {
			return Color.white;
		}
	}

	@Override
	public boolean isLayered(ItemStack var1) {
		return flag.isLayered();
	}

	@Override
	public int getLayers(ItemStack var1) {
		return 2;
	}

	@Override
	public TextureAtlasSprite getTexture(int index, ItemStack var2) {
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[flag.getTextureID() + index];
	}

	/**
	 * Called when a Block is right-clicked with this Item
	 */
	@Override
	public EnumActionResult onItemUse(EntityPlayer e, World w, BlockPos p, EnumHand h, EnumFacing facing, float hitX,
			float hitY, float hitZ) {

		for (GTRecipeCauldron.GTRecipeCauldronEnum recipes : GTRecipeCauldron.GTRecipeCauldronEnum.values()) {
			washDust(e, w, p, h, recipes.getInput(), recipes.getOutputs());
		}
		return EnumActionResult.PASS;
	}

	/**
	 * Creates the behavior of washing dust if the input material matches
	 */
	public EnumActionResult washDust(EntityPlayer player, World world, BlockPos pos, EnumHand hand, GTMaterial input,
			GTMaterial... outputs) {
		IBlockState state = world.getBlockState(pos);
		PropertyInteger level = PropertyInteger.create("level", 0, 3);
		if (this.material == input && this.flag == GTMaterialFlag.DUST) {
			if (state.getBlock() == Blocks.CAULDRON && state.getValue(level).intValue() > 0) {
				player.getHeldItem(hand).shrink(1);
				Blocks.CAULDRON.setWaterLevel(world, pos, state, state.getValue(level).intValue() - 1);
				for (GTMaterial mat : outputs) {
					ItemHandlerHelper.giveItemToPlayer(player, GTMaterialGen.getSmallDust(mat, 1));
				}
				world.playSound((EntityPlayer) null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
			}
		}
		return EnumActionResult.SUCCESS;
	}

	public GTMaterial getMaterial() {
		return this.material;
	}

	public GTMaterialFlag getFlag() {
		return this.flag;
	}
}
