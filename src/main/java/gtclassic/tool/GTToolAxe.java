package gtclassic.tool;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.color.GTColorItemInterface;
import gtclassic.material.GTMaterial;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ICustomItemCameraTransform;
import ic2.core.platform.textures.obj.ILayeredItemModel;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTToolAxe extends ItemAxe
		implements IStaticTexturedItem, GTColorItemInterface, ILayeredItemModel, ICustomItemCameraTransform {

	ToolMaterial tmaterial;
	GTMaterial material;

	public GTToolAxe(ToolMaterial tmaterial) {
		super(tmaterial, tmaterial.getAttackDamage(), tmaterial.getEfficiency());
		this.tmaterial = tmaterial;
		this.material = GTToolMaterial.getGTMaterial(tmaterial);
		this.setHarvestLevel("axe", this.material.getLevel());
		setRegistryName(tmaterial.toString().toLowerCase() + "_axe");
		setUnlocalizedName(GTMod.MODID + "." + tmaterial.toString().toLowerCase() + "_axe");
		setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		if (material.equals(material.Plutonium) || material.equals(material.Thorium)
				|| material.equals(material.Uranium)) {
			return true;
		}
		if (material.equals(material.Flint)) {
			return false;
		}
		return super.hasEffect(stack);
	}

	@Override
	public int getLayers(ItemStack arg0) {
		return 2;
	}

	@Override
	public TextureAtlasSprite getTexture(int var1, ItemStack var2) {
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[27 + var1];
	}

	@Override
	public boolean isLayered(ItemStack arg0) {
		return true;
	}

	@Override
	public Color getColor(ItemStack stack, int index) {
		if (index == 0) {
			return GTMaterial.Wood.getColor();
		} else {
			return material.getColor();
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int meta) {
		return Ic2Icons.getTextures("gtclassic_items")[27];
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	public ResourceLocation getCustomTransform(int meta) {
		return new ResourceLocation("minecraft:models/item/handheld");
	}

	@Override
	public boolean hasCustomTransform(int var1) {
		return true;
	}

	public GTMaterial getMaterial() {
		return GTToolMaterial.getGTMaterial(tmaterial);
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
		World worldIn = player.world;
		if (!player.isSneaking()) {
			for (int i = 1; i < 60; i++) {
				BlockPos nextPos = pos.up(i);
				IBlockState nextState = worldIn.getBlockState(nextPos);
				if (nextState.getBlock().isWood(worldIn, nextPos)) {
					breakBlock(nextPos, itemstack, worldIn, pos, player);
				}
			}
		}
		return false;
	}

	public void breakBlock(BlockPos pos, ItemStack saw, World world, BlockPos oldPos, EntityPlayer player) {
		if (oldPos == pos) {
			return;
		}

		IBlockState blockState = world.getBlockState(pos);
		if (blockState.getBlockHardness(world, pos) == -1.0F) {
			return;
		}
		player.getHeldItem(player.swingingHand).damageItem(1, player);
		blockState.getBlock().harvestBlock(world, player, pos, blockState, world.getTileEntity(pos), saw);
		world.setBlockToAir(pos);
		world.removeTileEntity(pos);
	}
}
