package gtclassic.common.item;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.api.interfaces.IGTScrewdriverable;
import ic2.api.item.ElectricItem;
import ic2.core.item.base.BasicElectricItem;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemScrewdriver extends BasicElectricItem implements IStaticTexturedItem {

	public GTItemScrewdriver() {
		this.maxStackSize = 1;
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setRegistryName("electricscrewdriver");
		this.setUnlocalizedName(GTMod.MODID + ".electricscrewdriver");
		this.maxCharge = 10000;
		this.transferLimit = 32;
		this.tier = 1;
		this.setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public int getRGBDurabilityForDisplay(ItemStack stack) {
		return Color.CYAN.hashCode();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int i) {
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[39];
	}

	@Override
	public int getTextureEntry(int var1) {
		return 0;
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX,
			float hitY, float hitZ, EnumHand hand) {
		if (!canUse(player.getHeldItemMainhand())) {
			return EnumActionResult.PASS;
		}
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof IGTScrewdriverable) {
			IGTScrewdriverable screwable = (IGTScrewdriverable) tile;
			if (screwable.onScrewdriver(player)) {
				ElectricItem.manager.use(player.getHeldItemMainhand(), 1, (EntityLivingBase) null);
			}
		}
		return EnumActionResult.SUCCESS;
	}

	public boolean canUse(ItemStack stack) {
		return ElectricItem.manager.canUse(stack, 1);
	}
}
