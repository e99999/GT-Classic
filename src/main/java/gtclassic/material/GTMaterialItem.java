package gtclassic.material;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.color.GTColorItemInterface;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ILayeredItemModel;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
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

public class GTMaterialItem extends Item implements IStaticTexturedItem, GTColorItemInterface, ILayeredItemModel {

	private GTMaterial material;
	private GTMaterialFlag flag;
	public static final PropertyInteger LEVEL = PropertyInteger.create("level", 0, 3);

	public GTMaterialItem(GTMaterial material, GTMaterialFlag flag) {
		this.material = material;
		this.flag = flag;
		setRegistryName(this.material.getName() + this.flag.getSuffix());
		setUnlocalizedName(GTMod.MODID + "." + this.material.getName() + this.flag.getSuffix());
		setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return this.flag == GTMaterialFlag.PLASMA || material == GTMaterial.Thorium || material == GTMaterial.Uranium
				|| material == GTMaterial.Plutonium;
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
		} else if (index == 1 && this.flag == GTMaterialFlag.PLASMA) {
			return Color.yellow;
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
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		IBlockState state = world.getBlockState(pos);
		// TODO refactor everything below into a cleaner method to add the drops from
		// materials
		if (this.flag == GTMaterialFlag.DUST && state.getBlock() == Blocks.CAULDRON
				&& state.getValue(LEVEL).intValue() > 0) {
			if (this.material == GTMaterial.Sphalerite) {
				player.setHeldItem(hand,
						new ItemStack(player.getHeldItem(hand).getItem(), player.getHeldItem(hand).getCount() - 1));
				Blocks.CAULDRON.setWaterLevel(world, pos, state, state.getValue(LEVEL).intValue() - 1);
				player.dropItem(GTMaterialGen.getSmallDust(GTMaterial.Zinc, 1), false);
				player.dropItem(GTMaterialGen.getSmallDust(GTMaterial.Germanium, 1), false);
				world.playSound((EntityPlayer) null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F,
						1.0F);
				return EnumActionResult.SUCCESS;
			}
			if (this.material == GTMaterial.Cinnabar) {
				player.setHeldItem(hand,
						new ItemStack(player.getHeldItem(hand).getItem(), player.getHeldItem(hand).getCount() - 1));
				Blocks.CAULDRON.setWaterLevel(world, pos, state, state.getValue(LEVEL).intValue() - 1);
				player.dropItem(GTMaterialGen.getSmallDust(GTMaterial.Redstone, 1), false);
				world.playSound((EntityPlayer) null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F,
						1.0F);
				return EnumActionResult.SUCCESS;
			}
			if (this.material == GTMaterial.Galena) {
				player.setHeldItem(hand,
						new ItemStack(player.getHeldItem(hand).getItem(), player.getHeldItem(hand).getCount() - 1));
				Blocks.CAULDRON.setWaterLevel(world, pos, state, state.getValue(LEVEL).intValue() - 1);
				player.dropItem(GTMaterialGen.getSmallDust(GTMaterial.Lead, 1), false);
				player.dropItem(GTMaterialGen.getSmallDust(GTMaterial.Silver, 1), false);
				world.playSound((EntityPlayer) null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F,
						1.0F);
				return EnumActionResult.SUCCESS;
			}
			if (this.material == GTMaterial.Germanite) {
				player.setHeldItem(hand,
						new ItemStack(player.getHeldItem(hand).getItem(), player.getHeldItem(hand).getCount() - 1));
				Blocks.CAULDRON.setWaterLevel(world, pos, state, state.getValue(LEVEL).intValue() - 1);
				player.dropItem(GTMaterialGen.getSmallDust(GTMaterial.Germanium, 1), false);
				player.dropItem(GTMaterialGen.getSmallDust(GTMaterial.Tin, 1), false);
				world.playSound((EntityPlayer) null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F,
						1.0F);
				return EnumActionResult.SUCCESS;
			}
		}

		return EnumActionResult.PASS;
	}

}
