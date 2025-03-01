package gtclassic.common.item;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.common.container.GTItemInventoryDestructoPack;
import ic2.core.IC2;
import ic2.core.inventory.base.IHandHeldInventory;
import ic2.core.inventory.base.IHasGui;
import ic2.core.item.base.ItemIC2;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.util.misc.StackUtil;
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
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemDestructoPack extends ItemIC2 implements IHandHeldInventory {

	public GTItemDestructoPack() {
		this.maxStackSize = 1;
		this.setCreativeTab(GTMod.creativeTabGT);
		this.setRegistryName("destructo_pack");
		this.setTranslationKey(GTMod.MODID + ".destructo_pack");
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format(this.getTranslationKey().replace("item", "tooltip")));
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int meta) {
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[1];
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (playerIn.isSneaking() && playerIn.isCreative()) {
			for (int i = 0; i < playerIn.inventory.getSizeInventory(); i++) {
				Item item = playerIn.inventory.getStackInSlot(i).getItem();
				if (!(item instanceof GTItemDestructoPack || item instanceof GTItemCreativeScanner
						|| item instanceof GTItemSurvivalScanner || item instanceof GTItemMagnifyingGlass)) {
					playerIn.inventory.setInventorySlotContents(i, ItemStack.EMPTY);
				}
			}
			return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
		}
		if (IC2.platform.isSimulating()) {
			IC2.platform.launchGui(playerIn, this.getInventory(playerIn, handIn, playerIn.getHeldItem(handIn)), handIn);
		}
		return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}

	@Override
	public int getGuiId(ItemStack stack) {
		NBTTagCompound nbt = StackUtil.getNbtData(stack);
		if (nbt.hasKey("GuiID")) {
			return nbt.getInteger("GuiID");
		}
		return -1;
	}

	@Override
	public IHasGui getInventory(EntityPlayer player, EnumHand hand, ItemStack stack) {
		return new GTItemInventoryDestructoPack(player, this, stack);
	}

	@Override
	public void setGuiID(ItemStack stack, int id) {
		if (id == -1) {
			StackUtil.getOrCreateNbtData(stack).removeTag("GuiID");
			return;
		}
		StackUtil.getOrCreateNbtData(stack).setInteger("GuiID", id);
	}

	@Override
	public int getTextureEntry(int var1) {
		return 0;
	}
}
