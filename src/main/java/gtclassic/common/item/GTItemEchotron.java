package gtclassic.common.item;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.api.item.GTItemBaseToggleItem;
import gtclassic.common.GTSounds;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class GTItemEchotron extends GTItemBaseToggleItem implements IStaticTexturedItem {

	public GTItemEchotron() {
		super(1, 1000, 10);
		this.setRegistryName("echotron");
		this.setTranslationKey(GTMod.MODID + "." + "echotron");
		this.setCreativeTab(GTMod.creativeTabGT);
		this.toggleSound = Ic2Sounds.cutterUse;
	}

	@Override
	public boolean onItemActive(ItemStack stack, World worldIn, Entity entityIn, int slot, boolean selected) {
		if (worldIn.getTotalWorldTime() % 100 == 0) {
			entityIn.playSound(GTSounds.SONAR, 1.0F, 1.0F);
			AxisAlignedBB area = new AxisAlignedBB(entityIn.getPosition()).grow(16);
			List<Entity> list = entityIn.world.getEntitiesInAABBexcluding(entityIn, area, null);
			if (!list.isEmpty()) {
				for (Entity thing : list) {
					if (thing instanceof EntityLiving) {
						((EntityLivingBase) thing).addPotionEffect(new PotionEffect(MobEffects.GLOWING, 80, 0, false, false));
					}
					if (thing instanceof EntityPlayer) {
						((EntityPlayer) thing).addPotionEffect(new PotionEffect(MobEffects.GLOWING, 80, 0, false, false));
					}
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public TextureAtlasSprite getTexture(int meta) {
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[8];
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}
}
