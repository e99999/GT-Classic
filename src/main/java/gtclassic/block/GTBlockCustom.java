package gtclassic.block;

import java.util.ArrayList;
import java.util.List;

import gtclassic.GTBlocks;
import gtclassic.GTMod;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import ic2.core.platform.lang.ILocaleBlock;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Lang;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IBlockTextureModifier;
import ic2.core.platform.textures.obj.ITexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockCustom extends Block implements ITexturedBlock, ILocaleBlock, IBlockTextureModifier {

	String name;
	int height;
	int width;
	int id;
	LocaleComp comp;

	public GTBlockCustom(String name, int id, int width, int height) {
		super(Material.ROCK);
		this.name = name;
		this.id = id;
		this.height = height;
		this.width = width;
		this.comp = Ic2Lang.nullKey;
		setRegistryName(this.name.toLowerCase() + "_block");
		setUnlocalizedName(GTMod.MODID + "." + this.name.toLowerCase() + "_block");
		setCreativeTab(GTMod.creativeTabGT);
		setHardness(2.0F);
		setHarvestLevel("pickaxe", 1);
		setSoundType(SoundType.STONE);
	}

	public float getHeight() {
		return this.height;
		// returns width as 0-16
	}

	public double getHeightBB() {
		return this.height / 16.0D;
		// returns height as 0.0D-1.0D
	}

	public double getOffsetBB() {
		return (1.0D - (this.width / 16.0D)) * .5;
		// returns full block height 1.0 - width (D) as 0.0-1.0D divided to center block
	}

	public float getWidth() {
		return this.width;
		// returns width as 0-16
	}

	public double getWidthBB() {
		return this.width / 16.0D;
		// returns width as 0.0D-1.0D
	}

	public AxisAlignedBB getVariantBoundingBox() {
		return new AxisAlignedBB(this.getOffsetBB(), 0.0D, this.getOffsetBB(), this.getOffsetBB() + this.getWidthBB(),
				this.getHeightBB(), this.getOffsetBB() + this.getWidthBB());
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		// TODO add tooltips
	}

	@Override
	@Deprecated
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	@Deprecated
	public boolean isNormalCube(IBlockState state) {
		return false;
	}

	@Override
	@Deprecated
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox(IBlockState iBlockState) {
		return getVariantBoundingBox();
	}

	@Override
	@Deprecated
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return getVariantBoundingBox();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TextureAtlasSprite getTextureFromState(IBlockState iBlockState, EnumFacing enumFacing) {
		return Ic2Icons.getTextures(GTMod.MODID + "_blocks")[this.id];
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
	public boolean hasTextureRotation(IBlockState var1, EnumFacing var2) {
		return false;
	}

	@Override
	public int getTextureRotation(IBlockState var1, EnumFacing var2) {
		return 0;
	}

	@Override
	public boolean hasCustomTextureUV(IBlockState var1, EnumFacing var2) {
		return true;
	}

	@Override
	public float[] getCustomTextureUV(IBlockState var1, EnumFacing var2) {
		if (var2 == EnumFacing.UP || var2 == EnumFacing.DOWN) {
			return new float[] { 0.0F, 16 - this.getWidth(), this.getWidth(), 16 };
		}
		return new float[] { 0.0F, 16 - this.getHeight(), this.getWidth(), 16 };

	}

	@Override
	@Deprecated
	public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState blockstate, int fortune) {
		ArrayList<ItemStack> drops = new ArrayList<>();
		if (this == GTBlocks.driedLithiumChloride) {
			drops.add(GTMaterialGen.getDust(GTMaterial.LithiumChloride, 1));
			return drops;
		}
		if (this == GTBlocks.driedTailings) {
			drops.add(GTMaterialGen.getDust(GTMaterial.BauxiteTailings, 1));
			return drops;
		} else {
			drops.add(GTMaterialGen.get(this));
			return drops;
		}
	}

	@Override
	@Deprecated
	public EnumPushReaction getMobilityFlag(IBlockState state) {
		return EnumPushReaction.DESTROY;
	}

}
