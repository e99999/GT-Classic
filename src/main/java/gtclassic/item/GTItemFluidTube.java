package gtclassic.item;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import gtclassic.GTItems;
import gtclassic.GTMod;
import gtclassic.color.GTColorItemInterface;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import gtclassic.material.GTMaterialGen;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IAdvancedTexturedItem;
import ic2.core.platform.textures.obj.ILayeredItemModel;
import ic2.core.platform.textures.obj.ITexturedItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStackSimple;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemFluidTube extends Item
		implements IAdvancedTexturedItem, ITexturedItem, ILayeredItemModel, GTColorItemInterface {

	private final int size = Fluid.BUCKET_VOLUME;
	private final ItemStack empty = new ItemStack(this);
	public ModelResourceLocation[] model = new ModelResourceLocation[2];

	public GTItemFluidTube() {
		setMaxStackSize(64);
		setRegistryName("test_tube");
		setUnlocalizedName(GTMod.MODID + "." + "test_tube");
		setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(getFluidName(stack));
	}

	public String getFluidName(ItemStack stack) {
		FluidStack fluid = FluidUtil.getFluidContained(stack);
		if (fluid != null) {
			return fluid.amount + "mB of " + fluid.getLocalizedName();
		}
		return "Empty";
	}

	public String getFluidUnlocalName(ItemStack stack) {
		FluidStack fluid = FluidUtil.getFluidContained(stack);
		if (fluid != null) {
			return fluid.getUnlocalizedName();
		}
		return "null";
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new FluidHandlerItemStackSimple.Consumable(stack, size);
	}

	@Override
	public void getSubItems(@Nullable final CreativeTabs tab, final NonNullList subItems) {
		if (this.isInCreativeTab(tab)) {
			subItems.add(empty);
			for (GTMaterial mat : GTMaterial.values()) {
				if (mat.hasFlag(GTMaterialFlag.FLUID)) {
					subItems.add(GTMaterialGen.getFluid(mat, 1));
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public ModelResourceLocation createResourceLocationForStack(ItemStack stack) {
		int damage = stack.getItemDamage();
		ResourceLocation location = this.getRegistryName();
		String name = stack.getUnlocalizedName();
		this.model[damage] = new ModelResourceLocation(
				location.getResourceDomain() + name.substring(name.indexOf(".") + 1) + damage, "inventory");
		return this.model[damage];
	}

	@SideOnly(Side.CLIENT)
	public ModelResourceLocation getResourceLocationForStack(ItemStack stack) {
		int damage = stack.getItemDamage();
		return this.model[damage];
	}

	public boolean isLayered(ItemStack stack) {
		return FluidUtil.getFluidContained(stack) != null;
	}

	public int getLayers(ItemStack stack) {
		return FluidUtil.getFluidContained(stack) != null ? 2 : 1;
	}

	@Override
	public TextureAtlasSprite getTexture(int index, ItemStack stack) {
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[13 + index];
	}

	@Override
	public List<ItemStack> getValidItemVariants() {
		return Arrays.asList(empty, GTMaterialGen.getFluid(GTMaterial.Hydrogen, 1));
	}

	@Override
	public TextureAtlasSprite getTexture(ItemStack var1) {
		// does not do anything since its layered
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[0];
	}

	@Override
	public Color getColor(ItemStack stack, int index) {
		FluidStack fluid = FluidUtil.getFluidContained(stack);
		// trying color
		if (fluid != null && index == 1) {
			return new Color(fluid.getFluid().getColor());
		}
		return Color.white;
	}

}
