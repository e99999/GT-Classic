package gtclassic.common.block;

import gtclassic.GTMod;
import gtclassic.api.block.GTBlockBase;
import gtclassic.api.interfaces.IGTBurnableBlock;
import gtclassic.common.GTBlocks;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class GTBlockBurnable extends GTBlockBase implements IGTBurnableBlock {

	String name;
	int type;
	int burnTime;

	public GTBlockBurnable(String name, int type, int burnTime) {
		super(Material.ROCK);
		this.name = name;
		this.type = type;
		this.burnTime = burnTime;
		setRegistryName(this.name.toLowerCase());
		setTranslationKey(GTMod.MODID + "." + this.name.toLowerCase());
		setCreativeTab(GTMod.creativeTabGT);
		setSoundType(SoundType.STONE);
		setHardness(1.0F);
		setResistance(10.0F);
		setHarvestLevel("pickaxe", 0);
	}

	@Override
	public TextureAtlasSprite getTextureFromState(IBlockState arg0, EnumFacing arg1) {
		return this.type == 0 ? Ic2Icons.getTextures("b0")[50]
				: Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("minecraft:blocks/coal_block");
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state,
			int fortune) {
		if (this == GTBlocks.brittleCharcoal) {
			int bonus = fortune > 0 ? RANDOM.nextInt(fortune + 1) : 0;
			drops.add(new ItemStack(Items.COAL, 1 + RANDOM.nextInt(2) + bonus, 1));
		} else {
			super.getDrops(drops, world, pos, state, fortune);
		}
	}

	@Override
	public int getBlockBurnTime(Block block) {
		return this.burnTime;
	}
}
