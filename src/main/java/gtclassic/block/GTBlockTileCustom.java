package gtclassic.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTBlocks;
import gtclassic.GTMod;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileResinBoard;
import gtclassic.tile.GTTileResinChunk;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IBlockTextureModifier;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class GTBlockTileCustom extends GTBlockMultiID implements IBlockTextureModifier {

	String name;
	int height;
	int width;
	boolean light;

	GTMaterialGen GT;
	GTMaterial M;

	public GTBlockTileCustom(String name, int width, int height, boolean light) {
		super(Material.CLOTH);
		this.name = name;
		this.height = height;
		this.width = width;
		this.light = light;
		setRegistryName(this.name.toLowerCase());
		setUnlocalizedName(GTMod.MODID + "." + this.name.toLowerCase());
		setCreativeTab(GTMod.creativeTabGT);
		setHardness(0.5F);
		setResistance(30.0F);
		setSoundType(SoundType.CLOTH);
		setLightLevel(getVariantLightLevel());
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

	public float getVariantLightLevel() {
		if (this.light) {
			return (float) (this.getWidthBB());
		}
		return 0;
	}

	@Override
	@Deprecated
	public boolean canEntitySpawn(IBlockState state, Entity entityIn) {
		return false;
	}

	@Override
	public TileEntityBlock createNewTileEntity(World arg0, int arg1) {
		if (this == GTBlocks.resinChunk) {
			return new GTTileResinChunk();
		}
		if (this == GTBlocks.resinBoard) {
			return new GTTileResinBoard();
		}
		return new TileEntityBlock();
	}

	@Deprecated
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return getVariantBoundingBox();
	}

	@Override
	public float[] getCustomTextureUV(IBlockState var1, EnumFacing var2) {
		if (var2 == EnumFacing.UP || var2 == EnumFacing.DOWN) {
			return new float[] { 0.0F, 16 - this.getWidth(), this.getWidth(), 16 };
		}
		return new float[] { 0.0F, 16 - this.getHeight(), this.getWidth(), 16 };

	}

	@Override
	public TextureAtlasSprite[] getIconSheet(int arg0) {
		return Ic2Icons.getTextures(this.name);
	}

	@Override
	public int getMaxSheetSize(int meta) {
		return 1;
	}

	public AxisAlignedBB getRenderBoundingBox(IBlockState state) {
		return getVariantBoundingBox();
	}

	@Override
	public int getTextureRotation(IBlockState var1, EnumFacing var2) {
		return 0;
	}

	@Override
	public List<Integer> getValidMetas() {
		return Arrays.asList(0);
	}

	@Override
	public List<IBlockState> getValidStateList() {
		IBlockState def = getDefaultState();
		List<IBlockState> states = new ArrayList<>();
		for (EnumFacing side : EnumFacing.VALUES) {
			states.add(def.withProperty(allFacings, side).withProperty(active, false));
			states.add(def.withProperty(allFacings, side).withProperty(active, true));
		}
		return states;
	}

	@Override
	public List<IBlockState> getValidStates() {
		return getBlockState().getValidStates();
	}

	public AxisAlignedBB getVariantBoundingBox() {
		return new AxisAlignedBB(this.getOffsetBB(), 0.0D, this.getOffsetBB(), this.getOffsetBB() + this.getWidthBB(),
				this.getHeightBB(), this.getOffsetBB() + this.getWidthBB());
	}

	@Override
	public boolean hasCustomTextureUV(IBlockState var1, EnumFacing var2) {
		return true;
	}

	@Override
	public boolean hasFacing() {
		return true;
	}

	@Override
	public boolean hasTextureRotation(IBlockState var1, EnumFacing var2) {
		return false;
	}

	@Deprecated
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Deprecated
	public boolean isNormalCube(IBlockState state) {
		return false;
	}

	@Deprecated
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	/*
	 * Below is just for the mortar which will get moved when the mortar has a
	 * custom model
	 */
	@Override
	public boolean onBlockActivated(World w, BlockPos p, IBlockState state, EntityPlayer e, EnumHand h,
			EnumFacing facing, float hitX, float hitY, float hitZ) {

		if (this != GTBlocks.mortar) {
			return super.onBlockActivated(w, p, state, e, h, facing, hitX, hitY, hitZ);
		}
		// TODO add xp drops and hunger usage
		// Nether Drops cinnabar add redstone, sphalerite yellow garniet
		addDrops(w, p, e, h, "oreCinnabar", GT.getDust(M.Cinnabar, 1), GT.getSmallDust(M.Sulfur, 1));
		addDrops(w, p, e, h, "oreTantalite", GT.getDust(M.Tantalite, 1), GT.getSmallDust(M.Sulfur, 1));
		addDrops(w, p, e, h, "orePyrite", GT.getDust(M.Pyrite, 1), GT.getSmallDust(M.Sulfur, 1));
		addDrops(w, p, e, h, "oreSphalerite", GT.getDust(M.Sphalerite, 1), GT.getSmallDust(M.Sulfur, 1));
		// End Ore drops sodalite add aluminium
		addDrops(w, p, e, h, "oreTungstate", GT.getDust(M.Tungstate, 1), GT.getSmallDust(M.Manganese, 1));
		addDrops(w, p, e, h, "oreSheldonite", GT.getDust(M.Sheldonite, 1), GT.getSmallDust(M.Platinum, 1));
		addDrops(w, p, e, h, "oreSodalite", GT.getDust(M.Sodalite, 6), GT.getSmallDust(M.Aluminium, 1));
		addDrops(w, p, e, h, "oreOlivine", GT.getDust(M.Olivine, 1), GT.getSmallDust(M.Emerald, 1));
		// Overworld ore drops
		addDrops(w, p, e, h, "oreGalena", GT.getDust(M.Galena, 1), GT.getSmallDust(M.Silver, 1));
		addDrops(w, p, e, h, "oreIridium", GT.getIc2(Ic2Items.iridiumOre, 1), GT.getSmallDust(M.Platinum, 1));
		addDrops(w, p, e, h, "oreRuby", GT.getDust(M.Ruby, 1), GT.getSmallDust(M.GarnetRed, 1));
		addDrops(w, p, e, h, "oreSapphire", GT.getDust(M.Sapphire, 1), GT.getSmallDust(M.SapphireGreen, 1));
		addDrops(w, p, e, h, "oreBauxite", GT.getDust(M.Bauxite, 3), GT.getSmallDust(M.Bauxite, 1));
		addDrops(w, p, e, h, "oreCalcite", GT.getDust(M.Calcite, 1), GT.getSmallDust(M.Calcite, 2));
		addDrops(w, p, e, h, "oreIron", GT.getIc2(Ic2Items.ironDust, 1), GT.getSmallDust(M.Nickel, 1));
		addDrops(w, p, e, h, "oreGold", GT.getIc2(Ic2Items.goldDust, 1), GT.getSmallDust(M.Silver, 1));
		addDrops(w, p, e, h, "oreSilver", GT.getIc2(Ic2Items.silverDust, 1), GT.getSmallDust(M.Gold, 1));
		addDrops(w, p, e, h, "oreTin", GT.getIc2(Ic2Items.tinDust, 1), GT.getSmallDust(M.Germanium, 1));
		addDrops(w, p, e, h, "oreCoal", GT.getIc2(Ic2Items.coalDust, 1), GT.getSmallDust(M.Coal, 1));
		addDrops(w, p, e, h, "oreRedstone", GT.get(Items.REDSTONE, 4), GT.getSmallDust(M.Cinnabar, 1));
		addDrops(w, p, e, h, "oreDiamond", GT.getDust(M.Diamond, 1), GT.getSmallDust(M.Graphite, 1));
		addDrops(w, p, e, h, "oreEmerald", GT.getDust(M.Emerald, 1), GT.getSmallDust(M.Olivine, 1));
		addDrops(w, p, e, h, "oreLapis", GT.getDust(M.Lazurite, 8), GT.getSmallDust(M.Sodalite, 1));
		// Material drops
		addDrops(w, p, e, h, "ingotRefinedIron", GT.getIc2(Ic2Items.ironDust, 1));
		addDrops(w, p, e, h, "ingotIron", GT.getIc2(Ic2Items.ironDust, 1));
		addDrops(w, p, e, h, "ingotGold", GT.getIc2(Ic2Items.goldDust, 1));
		addDrops(w, p, e, h, "ingotTin", GT.getIc2(Ic2Items.tinDust, 1));
		addDrops(w, p, e, h, "ingotCopper", GT.getIc2(Ic2Items.copperDust, 1));
		addDrops(w, p, e, h, "ingotAluminum", GT.getDust(M.Aluminium, 1));
		addDrops(w, p, e, h, "bone", new ItemStack(Items.DYE, 4, 15));
		addDrops(w, p, e, h, GT.get(Items.COAL), GT.getIc2(Ic2Items.coalDust, 1));
		addDrops(w, p, e, h, new ItemStack(Items.COAL, 1, 1), GT.getIc2(Ic2Items.charcoalDust, 1));

		for (GTMaterial mat : GTMaterial.values()) {
			if (mat.getSmeltable() && mat.hasFlag(GTMaterialFlag.INGOT) && mat.hasFlag(GTMaterialFlag.DUST)) {
				addDrops(w, p, e, h, "ingot" + mat.getDisplayName(), GT.getDust(mat, 1));
			}
		}

		return true;
	}

	public static String getOreName(ItemStack stack) {
		if (!stack.isEmpty() && (OreDictionary.getOreIDs(stack).length > 0)) {
			return OreDictionary.getOreName(OreDictionary.getOreIDs(stack)[0]);
		} else {
			return "null";
		}

	}

	public boolean addDrops(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand, String input,
			ItemStack... outputs) {
		ItemStack handstack = playerIn.getHeldItemMainhand();

		if (getOreName(handstack).equals(input)) {
			playerIn.getHeldItem(hand).shrink(1);
			for (ItemStack stack : outputs) {
				playerIn.dropItem(stack, false);
			}
			// FoodStats food = playerIn.getFoodStats();
			// food.setFoodLevel(food.getFoodLevel()-1);
			worldIn.playSound(playerIn, pos, SoundEvents.BLOCK_ANVIL_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
		}
		return true;
	}

	public boolean addDrops(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand, ItemStack input,
			ItemStack... outputs) {
		ItemStack handstack = playerIn.getHeldItemMainhand();

		if (ItemStack.areItemsEqual(handstack, input)) {
			playerIn.getHeldItem(hand).shrink(1);
			for (ItemStack stack : outputs) {
				playerIn.dropItem(stack, false);
			}
			worldIn.playSound(playerIn, pos, SoundEvents.BLOCK_ANVIL_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
		}
		return true;
	}

}
