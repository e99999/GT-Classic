package gtclassic.tool;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTBlocks;
import gtclassic.GTItems;
import gtclassic.GTMod;
import gtclassic.color.GTColorItemInterface;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ILayeredItemModel;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

public class GTToolKnife extends ItemSword implements IStaticTexturedItem, GTColorItemInterface, ILayeredItemModel {

	ToolMaterial tmaterial;
	GTMaterial material;

	public GTToolKnife(ToolMaterial tmaterial) {
		super(tmaterial);
		this.tmaterial = tmaterial;
		this.material = GTToolMaterial.getGTMaterial(tmaterial);
		setRegistryName(this.material.getName() + "_knife");
		setUnlocalizedName(GTMod.MODID + "." + this.material.getName() + "_knife");
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
	public boolean hasContainerItem(ItemStack itemStack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		ItemStack copy = itemStack.copy();
		return copy.attemptDamageItem(1, itemRand, null) ? ItemStack.EMPTY : copy;
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int meta) {
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[21];
	}

	@Override
	public Color getColor(ItemStack stack, int index) {
		if (index == 0) {
			return GTMaterial.Wood.getColor();
		} else {
			return GTToolMaterial.getGTMaterial(tmaterial).getColor();
		}
	}

	@Override
	public boolean isLayered(ItemStack var1) {
		return true;
	}

	@Override
	public int getLayers(ItemStack var1) {
		return 2;
	}

	@Override
	public TextureAtlasSprite getTexture(int var1, ItemStack var2) {
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[21 + var1];
	}

	public GTMaterial getMaterial() {
		return this.material;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		IBlockState state = world.getBlockState(pos);
		if (state == GTBlocks.resinBoard.getDefaultState()) {
			world.setBlockToAir(pos);
			ItemHandlerHelper.giveItemToPlayer(player, GTMaterialGen.get(GTItems.resinPCB, 1));
			player.setHeldItem(hand, this.getContainerItem(player.getHeldItem(hand)));
			world.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_ANVIL_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
			return EnumActionResult.SUCCESS;
		}
		return super.onItemUse(player, world, pos, hand, facing, hitX, hitY, hitZ);
	}
}
