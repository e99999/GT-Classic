package gtclassic.common.block;

import java.util.List;

import gtclassic.GTMod;
import gtclassic.api.block.GTBlockBase;
import gtclassic.api.helpers.GTValues;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockCasing extends GTBlockBase {

	String name;
	int id;

	public GTBlockCasing(String name, int id, float resistance) {
		super(Material.IRON);
		this.name = name;
		this.id = id;
		setRegistryName(this.name.toLowerCase());
		setTranslationKey(GTMod.MODID + "." + this.name.toLowerCase());
		setCreativeTab(GTMod.creativeTabGT);
		setSoundType(SoundType.METAL);
		setResistance(resistance);
		setHardness(3.0F);
		setHarvestLevel("pickaxe", 2);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format(GTValues.TOOLTIP_NOMOBS));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getTextureFromState(IBlockState iBlockState, EnumFacing enumFacing) {
		return Ic2Icons.getTextures("gtclassic_terrain")[this.id];
	}

	@Override
	public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, SpawnPlacementType type) {
		return false;
	}
}
