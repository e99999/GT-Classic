package gtclassic.common.item;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.api.helpers.GTUtility;
import gtclassic.api.item.GTItemBaseToggleItem;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GTItemForcefield extends GTItemBaseToggleItem implements IStaticTexturedItem {

	public GTItemForcefield() {
		super(4, 10000000, 1000);
		this.setRegistryName("forcefield");
		this.setTranslationKey(GTMod.MODID + "." + "forcefield");
		this.setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public TextureAtlasSprite getTexture(int meta) {
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[33];
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	public boolean onItemActive(ItemStack stack, World worldIn, Entity entityIn, int slot, boolean selected) {
		GTUtility.repelEntitiesInAABBFromPoint(worldIn, entityIn.getEntityBoundingBox().grow(8.0D), entityIn.lastTickPosX, entityIn.lastTickPosY, entityIn.lastTickPosZ);
		return true;
	}
}
