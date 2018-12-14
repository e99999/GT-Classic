package gtclassic.item.tool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTClassic;
import ic2.api.item.ElectricItem;
import ic2.core.IC2;
import ic2.core.item.base.BasicElectricItem;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ITexturedItem;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemElectromagnet extends BasicElectricItem implements ITexturedItem {

	public static final String active = "ImActive";
	int range = 7;
	double speed = 0.033D;
	double energyCost = 1;

	public GTItemElectromagnet() {
		this.maxStackSize = 1;
		this.setRegistryName("electromagnet");
		this.setUnlocalizedName(GTClassic.MODID + ".electroMagnet");
		this.maxCharge = 10000;
		this.transferLimit = 100;
		this.tier = 1;
		this.setCreativeTab(GTClassic.creativeTabGT);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		IC2.audioManager.playOnce(playerIn, Ic2Sounds.forceFieldOp);
		if (IC2.platform.isSimulating()) {
			NBTTagCompound nbt = StackUtil.getOrCreateNbtData(playerIn.getHeldItem(handIn));
			boolean result = !nbt.getBoolean(active);
			nbt.setBoolean(active, result);

		}
		return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int slot, boolean selected) {

		NBTTagCompound nbt = StackUtil.getNbtData((stack));

		if (worldIn.getTotalWorldTime() % 2 == 0) {
			if (!(entityIn instanceof EntityPlayer) || !nbt.getBoolean(active)) {
				return;
			}
			double x = entityIn.posX;
			double y = entityIn.posY + 1.5;
			double z = entityIn.posZ;
			int pulled = 0;
			for (EntityItem item : worldIn.getEntitiesWithinAABB(EntityItem.class,
					new AxisAlignedBB(x, y, z, x + 1, y + 1, z + 1).grow(range))) {
				if (!canPull(stack) || pulled > 200) {
					return;
				}
				item.addVelocity((x - item.posX) * speed, (y - item.posY) * speed, (z - item.posZ) * speed);
				ElectricItem.manager.use(stack, energyCost, (EntityLivingBase) null);
				pulled++;
			}
		}
	}

	public boolean canPull(ItemStack stack) {
		return ElectricItem.manager.canUse(stack, energyCost);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		NBTTagCompound nbt = StackUtil.getOrCreateNbtData((stack));
		return nbt.getBoolean(active);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int meta) {
		return Ic2Icons.getTextures("gtclassic_items")[59];
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	public List<ItemStack> getValidItemVariants() {
		return new ArrayList();
	}

	@Override
	public TextureAtlasSprite getTexture(ItemStack var1) {
		return null;
	}

	@Override
	public int getTextureEntry(int var1) {
		return 0;
	}

}
