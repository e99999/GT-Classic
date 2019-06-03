package gtclassic.item;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import ic2.core.IC2;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IAdvancedTexturedItem;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemMachineSwitch extends Item implements IAdvancedTexturedItem, IStaticTexturedItem {

	public ModelResourceLocation[] model = new ModelResourceLocation[6];

	public GTItemMachineSwitch() {
		this.maxStackSize = 1;
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setRegistryName("machine_switch");
		this.setUnlocalizedName(GTMod.MODID + ".machine_switch");
		this.setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public boolean hasContainerItem(ItemStack itemStack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		return itemStack.copy();
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		NBTTagCompound nbt;
		nbt = StackUtil.getNbtData(stack);
		if (nbt.hasKey("mode")) {
			tooltip.add(I18n.format("Mode: " + nbt.getInteger("mode")));
		} else {
			tooltip.add(I18n.format("Mode: 0"));
		}
		tooltip.add(I18n.format("Helps machines distingquish recipes sub sets"));
		tooltip.add(I18n.format("Right click to cycle"));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		IC2.audioManager.playOnce(playerIn, Ic2Sounds.wrenchUse);
		if (IC2.platform.isSimulating()) {
			NBTTagCompound nbt = StackUtil.getOrCreateNbtData(playerIn.getHeldItem(handIn));
			int mode = nbt.getInteger("mode");
			int result = mode + 1;
			if (result > 5) {
				result = 0;
			}
			nbt.setInteger("mode", result);
			this.setDamage(playerIn.getHeldItem(handIn), mode);
			IC2.platform.messagePlayer(playerIn, "Mode: " + mode);
		}
		return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int meta) {
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[64 + meta];
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0, 1, 2, 3, 4, 5);
	}

	@SideOnly(Side.CLIENT)
	public ModelResourceLocation createResourceLocationForStack(ItemStack stack) {
		int damage = stack.getItemDamage();
		ResourceLocation location = this.getRegistryName();
		String name = stack.getUnlocalizedName();
		this.model[damage] = new ModelResourceLocation(location.getResourceDomain()
				+ name.substring(name.indexOf(".") + 1) + damage, "inventory");
		return this.model[damage];
	}

	@SideOnly(Side.CLIENT)
	public ModelResourceLocation getResourceLocationForStack(ItemStack stack) {
		int damage = stack.getItemDamage();
		return this.model[damage];
	}
}
