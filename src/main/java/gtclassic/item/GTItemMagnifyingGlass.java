package gtclassic.item;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTMod;
import gtclassic.tile.GTTileDrum;
import gtclassic.tile.multi.GTTileMultiBaseMachine;
import gtclassic.tile.multi.GTTileMultiBloomery;
import gtclassic.tile.multi.GTTileMultiCharcoalPit;
import ic2.core.IC2;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemMagnifyingGlass extends Item implements IStaticTexturedItem {

	public GTItemMagnifyingGlass() {
		setMaxStackSize(1);
		setRegistryName("magnifying_glass");
		setUnlocalizedName(GTMod.MODID + "." + "magnifying_glass");
		setCreativeTab(GTMod.creativeTabGT);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format(this.getUnlocalizedName().replace("item", "tooltip")));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int i) {
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[1];
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX,
			float hitY, float hitZ, EnumHand hand) {
		TileEntity tileEntity = world.getTileEntity(pos);
		IBlockState state = world.getBlockState(pos);

		if (player.isSneaking() || !IC2.platform.isSimulating()) {
			return EnumActionResult.PASS;
		} else {
			IC2.platform.messagePlayer(player, "" + state.getBlock().getLocalizedName());
			world.playSound((EntityPlayer) null, pos, SoundEvents.ENTITY_VILLAGER_AMBIENT, SoundCategory.BLOCKS, 1.0F,
					1.0F);

			if (tileEntity instanceof GTTileMultiBloomery) {
				GTTileMultiBloomery bloom = (GTTileMultiBloomery) tileEntity;
				IC2.platform.messagePlayer(player, "Correct Strucuture: " + bloom.checkStructure());
			}

			if (tileEntity instanceof GTTileMultiCharcoalPit) {
				GTTileMultiCharcoalPit pit = (GTTileMultiCharcoalPit) tileEntity;
				IC2.platform.messagePlayer(player, "Correct Strucuture: " + pit.checkStructure());
				IC2.platform.messagePlayer(player, "Charcoal looks about "
						+ (Math.round((pit.getProgress() / pit.getMaxProgress()) * 100)) + "% finished");
			}

			if (tileEntity instanceof GTTileMultiBaseMachine) {
				GTTileMultiBaseMachine multi = (GTTileMultiBaseMachine) tileEntity;
				IC2.platform.messagePlayer(player, "Correct Strucuture: " + multi.checkStructure());
			}

			if (tileEntity instanceof GTTileDrum) {
				GTTileDrum tank = (GTTileDrum) tileEntity;
				if (!tank.isEmpty()) {
					IC2.platform.messagePlayer(player, tank.getFluidAmount() + "mB of " + tank.getFluidName());
				} else {
					IC2.platform.messagePlayer(player, "Drum is empty");
				}
				IC2.platform.messagePlayer(player, "Auto Output: " + tank.getExport());
			}

			return EnumActionResult.SUCCESS;
		}
	}
}
