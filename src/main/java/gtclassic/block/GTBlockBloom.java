package gtclassic.block;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import gtclassic.GTBlocks;
import gtclassic.GTMod;
import gtclassic.color.GTColorBlockInterface;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import ic2.core.platform.lang.ILocaleBlock;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2Lang;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ITexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

public class GTBlockBloom extends Block implements ITexturedBlock, ILocaleBlock, GTColorBlockInterface {

	String name;
	LocaleComp comp;
	GTMaterial mat;
	int count;

	public GTBlockBloom(GTMaterial mat, int count) {
		super(Material.ROCK);
		this.mat = mat;
		this.count = count;
		this.name = mat.getDisplayName();
		this.comp = Ic2Lang.nullKey;
		setRegistryName(this.name.toLowerCase() + "_bloom");
		setUnlocalizedName(GTMod.MODID + "." + this.name.toLowerCase() + "_bloom");
		setCreativeTab(GTMod.creativeTabGT);
		setHardness(1.0F);
		setHarvestLevel("pickaxe", 0);
		setSoundType(SoundType.STONE);
	}

	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state,
			@Nullable TileEntity te, ItemStack stack) {
		super.harvestBlock(worldIn, player, pos, state, te, stack);
		if (this.mat.equals(GTMaterial.RefinedIron))	{
			ItemHandlerHelper.giveItemToPlayer(player, GTMaterialGen.getIc2(Ic2Items.refinedIronIngot, count));
			ItemHandlerHelper.giveItemToPlayer(player, GTMaterialGen.getDust(GTMaterial.Slag, 1));
			return;
		}
		if (this.mat.equals(GTMaterial.Bronze))	{
			ItemHandlerHelper.giveItemToPlayer(player, GTMaterialGen.getIc2(Ic2Items.bronzeIngot, count));
			ItemHandlerHelper.giveItemToPlayer(player, GTMaterialGen.getDust(GTMaterial.Slag, 1));
			return;
		}
		ItemHandlerHelper.giveItemToPlayer(player, GTMaterialGen.getIngot(mat, count));
		ItemHandlerHelper.giveItemToPlayer(player, GTMaterialGen.getDust(GTMaterial.Slag, 1));
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format(this.getUnlocalizedName().replace("tile", "tooltip")));
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox(IBlockState iBlockState) {
		return FULL_BLOCK_AABB;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getTextureFromState(IBlockState iBlockState, EnumFacing enumFacing) {
		return Ic2Icons.getTextures(GTMod.MODID + "_materials")[53];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getParticleTexture(IBlockState state) {
		return this.getTextureFromState(state, EnumFacing.SOUTH);
	}

	@Override
	public List<IBlockState> getValidStates() {
		return this.blockState.getValidStates();
	}

	@Override
	@Deprecated
	public IBlockState getStateFromStack(ItemStack stack) {
		return this.getStateFromMeta(stack.getMetadata());
	}

	public LocaleComp getName() {
		return this.comp;
	}

	public Block setUnlocalizedName(LocaleComp name) {
		this.comp = name;
		return super.setUnlocalizedName(name.getUnlocalized());
	}

	@Override
	public Block setUnlocalizedName(String name) {
		this.comp = new LocaleBlockComp("tile." + name);
		return super.setUnlocalizedName(name);
	}

	@Override
	public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
		Random rand = world instanceof World ? ((World) world).rand : new Random();
		int xp = 0;
		xp = MathHelper.getInt(rand, 1, 5);
		return xp;
	}

	@Override
	public Color getColor(Block block, int index) {
		return this.mat.getColor();
	}
}